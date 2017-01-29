# ui-automation

ui-automation is a framework for automating (via the [MS UIAutomation library](https://msdn.microsoft.com/en-us/library/vstudio/ms753388(v=vs.100).aspx)) rich client applications based on Win32 (including Delphi), WPF and other Windows applications. It is written in Java, using the JNA library to make calls to the COM-based WIndows automation library. 

Tests and automation programs using ui-automation can be written with Java (or other JVM based languages, like Scala) and used in any testing framework available to the JVM.

It provides a consistent object-oriented API, hiding the complexity of Microsoft's UIAutomation library and windows messages from the user.

## A bit of history
The code here is used to test applications written in Delphi (specifically Delphi XE5 and 10.1), there are assumptions about the names of classes that have been created, in order to provide automation interfaces that are not part of the standard Delphi controls.

# Developer documentation
The library is a programmers tool, and they will either need to have the source code of the application under test, or be able to 'explore'`the application to get the names of controls, etc.
 
The MS UIAutomation Library is a COM control, and the classes that represent this have been wrapped using native JNA calls (currently using 4.3.0).

## Maven
<!--
The library is held in the Sonatype OSS repository, so is available for download via Maven (or other tools, such as SBT for Scala). As the library depends on the 4.3.0-SNAPSHOT, then don't specify the JNA library version in YOUR dependencies, the library will guide the dependencies and bring down the correct version. You will also need to allow the download of snapshots in Maven, so look ([read the this Stack Overflow question and answer to find out how to do this ](http://stackoverflow.com/questions/7715321/how-to-download-snapshot-version-from-maven-snapshot-repository)). 
-->
The library is held in the Sonatype OSS repository, so is 
available for download via Maven (or other tools, 
such as SBT for Scala). 

As the library depends on jna version 4.3.0, then don't specify the JNA library version in YOUR dependencies, the library will guide the dependencies and bring down the correct version. 

```
<dependencies>
  <!-- Other dependencies -->
  <groupId>com.github.mmarquee</groupId>
  <artifactId>ui-automation</artifactId>
  <version>0.3.10</version>
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

Each control contained in a window can be identified by the index of that control, sometimes (this depends on the control type) by the text associated with it, OR by the Automation Id. For example, in order to get the textbox associated with the connection window (and assuming that it is the 1st Edit box on the window), the following code will find the editbox, and change the text to be USER1.

```java
  AutomationEditBox user = window.getEditBox(0);
  user.setText("USER1");
```

### Invoking actions on controls

In order to click the 'OK' button associated with the connection window, it can be found by the text associated with the button, the following code will find the button, and call the click event.

```java
  // Get button by index
  AutomationButton button1 = window.getButton(0);
  button1.click();
```

```java
  // Get button by name
  AutomationButton button1 = window.getButton("OK");
  button1.click();
```

```java
  // Get button by automation id
  AutomationButton button1 = window.getButtonByAutomationId("OK");
  button1.click();
```

# Current supported controls

The controls that have been implemented reflect the requirements for automating the applications that we are testing ourselves, so some controls have not been implemented, or only partially. The currently supported controls are ...

* Button
* CheckBox
* ComboBox
* EditBox
* RadioButton
* ToggleButton
* StatusBar
* DataGrid
* PageControl
* Tab
* TextBox
* TreeView 
* Menu and MenuItem
* SplitButton
* Ribbon
* Hyperlink
* Panel
* Toolbar 
* ProgressBar
* MaskedEdit 
* Custom

## Caching

[See wiki](https://github.com/mmarquee/ui-automation/wiki/caching)

## Event Handling
[See wiki](https://github.com/mmarquee/ui-automation/wiki/events)

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

