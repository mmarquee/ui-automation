# ui-automation

A Java library that wraps the [MS UIAutomation library](https://msdn.microsoft.com/en-us/library/vstudio/ms753388(v=vs.100).aspx).

ui-automation is a framework for automating rich client applications based on Win32. It is written in Java, using the jna library to provide wrappers for the native Windows type-library.

Tests and automation programs using ui-automation can be written with Java and used in any testing framework available to the JVM.

It provides a consistent object-oriented API, hiding the complexity of Microsoft's UIAutomation library and windows messages.

## A bit of history
The code here is used to test applications written in Delphi (specifically Delphi XE5), there are assumptions about the names of classes that have been created, in order to provide automation interfaces that are not part of the standard Delphi controls.

The MS UIAutomation Library is a COM control, and the classes that represent this have been extracted using com4j.

## Getting started

The ui-automation library is a wrapper for the UIAutomationClient library, which has been extracted using com4j. As the generated code is large and complex, this has been wrapped up in a number of classes, each providing classes that encapsulate part of this library (together with other utility methods as necessary).

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
* StatusBar
* StringGrid and StringGridItem (see below)
* PageControl
* Tab
* TextBox
* TreeView and TreeViewItem
* Menu and MenuItem
* SplitButton (partially)
* Some Ribbon implementations
* Hyperlink
* Panel
* Toolbar

## TAutomatedStringGrid

The [DelphiUIAutomation](https://github.com/markhumphreysjhc/DelphiUIAutomation) project introduced some Delphi controls that implement IUIAutomation providers, allowing them to be accessed by automation. The TAutomatedStringGrid is one of these, as the base Delphi (as of XE5 at least) control does not implement the Grid or Table interfaces and so is opaque to automation.

```java
    AutomationStringGrid grid = window.getStringGridByIndex(0);
    AutomationStringGridItem item = grid.getItem(0,0);
	String itemName = item.name();
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

