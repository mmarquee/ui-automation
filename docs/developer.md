The library is a programmers tool, in order to automate an application under tests developers will need to 'explore' 
the application to get the names of controls, etc. using a tool like[inspect](https://msdn.microsoft.com/en-us/library/windows/desktop/dd318521(v=vs.85).aspx). 
It will also help to have the original source code, but it is not essential.
 
The MS UIAutomation Library is a COM library, and the classes that represent this have been wrapped using native 
JNA calls (currently using 4.5.0).

## Maven
The library is held in the Sonatype OSS repository, so is 
available for download via Maven (or other tools, 
such as SBT for Scala). 

As the library depends on jna (currently version 4.5.0), then there is no need to specify the JNA library version in YOUR project's dependencies, the library will guide the dependencies and bring down the correct version. 

```
<dependencies>
  <!-- Other dependencies -->
  <dependency>
    <groupId>com.github.mmarquee</groupId>
    <artifactId>ui-automation</artifactId>
    <version>0.5.0</version>
  </dependency>  
<dependencies>  
```

## Snapshot build

Currently the 'stable' release of the library is 0.5.0, so the development 
branch is being snapshotted, so to get the latest development version, use the the following in the pom.xml.

```
<dependencies>
  <!-- Other dependencies -->
  <dependency>
    <groupId>com.github.mmarquee</groupId>
    <artifactId>ui-automation</artifactId>
    <version>0.5.1-SNAPSHOT</version>
  </dependency>  
<dependencies>  
```


## Getting started

The ui-automation library is a wrapper for the UIAutomationClient library, which has been extracted using com4j. As the generated code is large and complex, this has been wrapped up in a number of classes, each providing classes that encapsulate part of this library (together with other utility methods as necessary).

### Initialising

In order to get access to the automation API, an UIAutomation instance needs to be created, this is done as follows.

```
  UIAutomation automation = UIAutomation.getInstance();
```

### Launching an application

The AutomationApplication class provides functionality to start and attach to an application. There are 3 class methods provided to do this.

* Launch - this will launch the application supplied, and pass in any supplied arguments
* Attach - this will attach to an already launched application, based on the executable name
* LaunchOrAttach - this will either attach to an already launched application, or launch the application.

The snippet below will check whether Project1.exe is running, attaching to it if it is, or launch it if not.

```
  AutomationApplication application = automation.launchOrAttach("...\\Project1.exe");
```

### Getting hold of a window
* See [Element Discovery](element-discovery.md) for more details.

The root element of the tree of automation elements is the user’s desktop. 

To get a 'desktop' window (i.e. one that appears in the Windows tasks bar), then the AutomationDesktop class provides a class function that returns a AutomationWindow object.

```
  AutomationWindow window = automation.getDesktopWindow("Form1");
  window.focus();
```

This will find (if it is there) a window that has the given title, and set focus to it. This window is independent of the overall application, and might not even be associated with the same application that is being automated.

### Finding a control

Each control contained in a container (such as a window or panel) can be identified by the index of that control, sometimes (this depends on the control type), by the text associated with it, OR by the Automation Id. For example, in order to get the textbox associated with the connection window (and assuming that it is the 1st Edit box on the window), the following code will find the editbox, and change the text to be USER1.

```
  AutomationEditBox user = window.getEditBox(Search.getBuilder(0).build());
  user.setText("USER1");
```

### Invoking actions on controls

In order to click the 'OK' button associated with a given window, it can be found by the text associated with the button, the following examples will all find the same button, and call the click event for it.

```
  // Get button by index
  AutomationButton button1 = window.getButton(Search.getBuilder(0).build());
  button1.click();
```

```
  // Get button by name
  AutomationButton button1 = window.getButton(Search.getBuilder("OK").build());
  button1.click();
```

```
  // Get button by automation id
  AutomationButton button1 = window.getButton(Search.getBuilder().automationId("btnOK").build());
  button1.click();
```

# Current supported controls

The controls that have been implemented reflect the requirements for automating the applications that we are testing ourselves, so some controls have not been implemented, or only partially. The currently supported controls are ...

* [Button](button.md)
* [Grids](grids.md)
* [Ribbon](ribbon.md)
* [MaskEdit](maskedit.md)
* [Menus](menus.md)
* [Editbox](editbox.md)
* [CheckBox](checkbox.md)
* [ComboBox](combobox.md)
* [RadioButton](radiobutton.md)
* [StatusBar](statusbar.md)
* [Tab](tab.md)
* [TextBox](textbox.md)
* [TreeView](treeview.md) 
* [SplitButton](splitbutton.md)
* [Hyperlink](hyperlink.md)
* [Panel](panel.md)
* [Toolbar](toolbar.md)
* [ProgressBar](progressbar.md)
* [Custom](custom.md)
* [Calendar](calendar.md)
* [Document](document.md)
* [Slider](slider.md)
* [Window](window.md)

# Windows support details
[Windows versions](windows-support.md)

## Real world examples
* [Tree Walker](treewalker.md)
* [Real world examples](examples.md)

## Caching
* [Caching](caching.md)

## Event Handling
* [Events](events.md)

## Other languages
* [Scala](scala.md)