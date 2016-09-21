# ui-automation

ui-automation is a framework for automating (via the [MS UIAutomation library](https://msdn.microsoft.com/en-us/library/vstudio/ms753388(v=vs.100).aspx)) rich client applications based on Win32 (including Delphi), WPF and other Windows applications. It is written in Java, using the JNA library to make calls to the COM-based WIndows automation library. 

Tests and automation programs using ui-automation can be written with Java (or other JVM based languages, like Scala) and used in any testing framework available to the JVM.

It provides a consistent object-oriented API, hiding the complexity of Microsoft's UIAutomation library and windows messages from the user.

## A bit of history
The code here is used to test applications written in Delphi (specifically Delphi XE5 and 10.1), there are assumptions about the names of classes that have been created, in order to provide automation interfaces that are not part of the standard Delphi controls.

# Developer documentation
The library is a programmers tool, and they will either need to have the source code of the application under test, or be able to 'explore'`the application to get the names of controls, etc.
 
The MS UIAutomation Library is a COM control, and the classes that represent this have been wrapped using native JNA calls (currently using 4.3.0-SNAPSHOT).

## Maven
The library is held in the Sonatype OSS repository, so is available for download via Maven (or other tools, such as SBT for Scala). As the library depends on the 4.3.0-SNAPSHOT, then don't specify the JNA library version in YOUR dependencies, the library will guide the dependencies and bring down the correct version. You will also need to allow the download of snapshots in Maven, so look ([read the this Stack Overflow question and answer to find out how to do this ](http://stackoverflow.com/questions/7715321/how-to-download-snapshot-version-from-maven-snapshot-repository)). 

```
<dependencies>
  <!-- Other dependencies -->
  <groupId>com.github.mmarquee</groupId>
  <artifactId>ui-automation</artifactId>
  <version>0.2.0</version>
<dependencies>  
```

## Getting started

The ui-automation library is a wrapper for the UIAutomationClient library, which has been extracted using com4j. As the generated code is large and complex, this has been wrapped up in a number of classes, each providing classes that encapsulate part of this library (together with other utility methods as necessary).

### Initialising

In order to get access to the automation API, an UIAutomation instance needs to be created, this is done as follows.

```java
  UIAutomation automation = UIAutomation.getInstance();
```

### Launching an application

The AutomationApplication class provides functionality to start and attach to an application. There are 3 class methods provided to do this.

* Launch - this will launch the application supplied, and pass in any supplied arguments
* Attach - this will attach to an already launched application, based on the executable name
* LaunchOrAttach - this will either attach to an already launched application, or launch the application.

The snippet below will check whether Project1.exe is running, attaching to it if it is, or launch it if not.

```java
  AutomationApplication application = automation.launchOrAttach("...\\Project1.exe");
```

### Getting hold of a window

To get a 'desktop' window (i.e. one that appears in the Windows tasks bar), then the TAutomationDesktop class provides a class function that returns a TAutomationWindow object.

```java
  AutomationWindow window = automation.getDesktopWindow("Form1");
  window.focus();
```

This will find (it is there) a window that has the given title, and set focus to it. This window is independent of the overalll application, and might not even be associated with the same application that is being automated.

### Finding a control

Each control contained in a window can be identified by the index of that control OR sometimes (this depends on the control type) by the text associated with it. For example, in order to get the textbox associated with the connection window (and assuming that it is the 1st Edit box on the window), the following code will find the editbox, and change the text to be USER1.

```java
  AutomationEditBox user = window.getEditBoxByIndex(0);
  user.setText("USER1");
```

### Invoking actions on controls

In order to click the 'OK' button associated with the connection window, it can be found by the text associated with the button, the following code will find the button, and call the click event.

```java
  AutomationButton button1 = window.getButtonByName("OK");
  button1.click();
```

# Current supported controls

The currently supported controls are ...

* Button
* CheckBox
* ComboBox
* EditBox
* RadioButton
* ToggleButton
* StatusBar
* DataGrid and DataGridCell (see below)
* PageControl
* Tab
* TextBox
* TreeView and TreeViewItem
* Menu and MenuItem
* SplitButton (partially)
* Some Ribbon implementations (see below)
* Hyperlink
* Panel
* Toolbar (Delphi Toolbars do not seem to be like other ToolBars)
* ProgressBar
* MaskedEdit (see below)

## TAutomatedDataGrid

### WFP
```java
  AutomationDataGrid grid = window.getDataGrid(0);
  AutomationDataGridItem item = grid.getCell(0,0);
  String itemName = item.name();
```

### Delphi

The [DelphiUIAutomation](https://github.com/markhumphreysjhc/DelphiUIAutomation) project introduced some Delphi controls that implement IUIAutomation providers, allowing them to be accessed by automation. The TAutomatedStringGrid is one of these, as the base Delphi (as of XE5 at least) control does not implement the Grid or Table interfaces and so is opaque to automation. In order to get the element associated with the specific TAutomationStringGrid, then this is done in the following manner.

```java
  AutomationDataGrid grid = window.getDataGrid(0, "TAutomationStringGrid");
  AutomationDataGridItem item = grid.getCell(0,0);
  String itemName = item.name();
```

This specifically looks controls with a control name of "TAutomationStringGrid", which is the name of the Delphi control introduced in the above project.

## MaskedEdit

As noted above the [DelphiUIAutomation](https://github.com/markhumphreysjhc/DelphiUIAutomation) project introduced some Delphi controls that implement IUIAutomation providers, allowing them to be accessed by automation. The Delphi implementation of a masked edit is another of these.

There does not seem to be an equivalent WPF control.

```java
  try {
    AutomationMaskedEdit me0 = window.getMaskedEdit("AutomatedMaskEdit1");

    // Get the current vslue
    String value = me0.getValue();

    // Set the value
    me0.setValue("12/12/99");

    // Get the value again
    String value1 = me0.getValue();

  } catch (ElementNotFoundException ex) {
    ..
  }
```

This specifically looks controls with a control name of "TAutomatedMaskEdit", which is the name of the Delphi control introduced in the above project.

## Menus

There appear to be 2 different types of menus, depending on the framework that is being used, MenuBar and Menu based, VCL/Delphi and 'classic' windows applications menus start with a MenuBar. where as .NET applications start with Menus. Examples are shown below and in the examples provided.

### Delphi / VCL Menus

VCL menus are rather tricky, as they need to be expanded and collapsed before the menuitems can be seen via automation, also these menu items do not belong to the parent item, but the overall menu.

The example below shows the current (as of 25/04/2016) support for 2 level menus. If either of these text items are not found the ElementNotFoundException exception will be thrown.

```java
  try {
    AutomationMenuItem exit = menu.getMenuItem("File", "Exit");
    exit.click();
  } catch (ElementNotFoundException ex) {
    ..
  }
```

#### Menu fudge
There has been one odd menu that we have found in our applications, and at the moment this is encapsulated in a fudge method, as shown below. This finds the menu and clicks it, as here doesn't seem to be a nice way of doing this with the other methods.

```java
  // Find the Help | About and click it
  menu.menuClickFudge("Help", KeyEvent.VK_A);
```

### WPF Menus

```java
  AutomationMainMenu mainMenu = window.getMenu(0);

  // Get the first menu item (i.e. "File")
  AutomationMenuItem file = mainMenu.getItems().get(0);
  file.expand();

  // A short wait for the expand to work
  try {
    Thread.sleep(500);
  } catch (Exception ex) {
    logger.info("Interrupted");
  }

  // Look for a specific menu item (in this case 'exit' is the 4th entry)
  AutomationMenuItem exit = file.getItems().get(3);

  exit.click();
```

## Popup Menus
A popup menu is just another menu, and can be accessed in the same manner. In the WPF example, there is a button that has an associated context menu, the code below illustrates how to get at this menu and associated menu items

```java
  AutomationMouse mouse = new AutomationMouse();
  mouse.setLocation(1119, 896);
  mouse.rightClick();

  // Wait to make sure menu is displayed
  try {
    Thread.sleep(500);
  } catch (Exception ex) {
    logger.info("Interrupted");
  }

  // Should be able to get the popup menu here
  AutomationMenu popup = window.getMenu(0)
  
```

## The Ribbon control

The ribbon control is a complex structure, but the tree of controls is navigable, as the snippet below shows (automating the Windows Explorer), finding the button associated with the Preview Pane and clicking on it to turn it on/off.

```java
  AutomationRibbonBar ribbon = window.getRibbonBar();
  AutomationRibbonCommandBar commandBar = ribbon.getRibbonCommandBar();
  AutomationRibbonWorkPane pane = commandBar.getRibbonWorkPane();
  logger.info("First work pane is " + pane.name());

  AutomationNUIPane uiPane = pane.getNUIPane(0);
  logger.info("First NUIPane is " + uiPane.name());

  AutomationNetUIHWND uiHWND = uiPane.getNetUIHWND(0);
  AutomationButton btn = uiHWND.getButton("Minimise the Ribbon");

  AutomationTab tab = uiHWND.getTab(0);
  tab.selectTabPage("View");

  AutomationPanel panel = uiHWND.getPanel("Lower Ribbon");

  AutomationToolBar panes = panel.getToolBar("Panes");
  AutomationToolBar panes = panel.getToolBar("Panes");

  panes.getButton("Preview pane").click();
```

## Caching

TODO: Not yet implemented

## Event Handling
TODO: Not yet implemented

# Scala

## sbt
To add the library as a prerequisite, use the following entry in the build.sbt file

```scala
  libraryDependencies += "com.github.mmarquee" % "ui-automation " & "0.1.10"
```

## Examples

This class encapsulates some simple automation of the Notepad program, starting the application, adding text, exiting the application and confirming exit

```scala
  class NotepadAutomation {
    private var automation = UIAutomation.getElement
    private var application: AutomationApplication = null
    private var window: AutomationWindow = _

    def launch(): Unit = {
        this.application = this.automation.launch("notepad.exe")

        this.window = automation.getDesktopWindow("Untitled - Notepad")
    }

    def addText(text: String): Unit = {
        val editor = this.window.getEditBoc(0)
        editor.setValue(text)
    }

    def clickExit(): Unit = {
        val menu = this.window.getMainMenu(1)
        menu.getMenuItem ("File", "Exit").click
    }

    def confirmExit(): Unit = {
        val confirm = this.window.getWindow("Notepad")
        confirm.getButton("Don't Save").click
    }
  }

  ...
  val notepad = NotepadAutomation()
  notepad.launch
  notepad.addText("This is automated from Scala")
  notepad.clickExit
  notepad.confirmExit
  ...

```

# Contributors
[Mark Humphreys](https://github.com/mmarquee)

# License
Apache Version 2.0 Copyright (C) 2016

See LICENCE.txt for details.
  
# See also
* [DelphiUIAutomation](https://github.com/markhumphreysjhc/DelphiUIAutomation)
* [Microsoft Accessibility](https://msdn.microsoft.com/en-us/library/vstudio/ms753388(v=vs.100).aspx)
* [UIAutomation for Powershell](http://uiautomation.codeplex.com/documentation)
* [TestStack.White](https://github.com/TestStack/White)
* [UI Automation - A wrapper around Microsoft's UI Automation libraries.](https://github.com/vijayakumarsuraj/UIAutomation)

