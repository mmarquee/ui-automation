# ui-automation

ui-automation is a framework for automating (via the [MS UIAutomation library](https://msdn.microsoft.com/en-us/library/vstudio/ms753388(v=vs.100).aspx)) rich client applications based on Win32 (including Delphi), WPF and other Windows applications ((including Java SWT)). It is written in Java, using the JNA library to make calls to the COM-based WIndows automation library. 

Tests and automation programs using ui-automation can be written with Java (or other JVM based languages, like Scala) and used in any testing framework available to the JVM.

It provides a consistent, object-oriented API, hiding the complexity of Microsoft's UIAutomation library and windows messages from the user.

# Blog
* [Blog](https://github.com/mmarquee/ui-automation/wiki/Blog-Home)

# Developer documentation
* [Start here](docs/developer.md)

# Continuous Builds
The build is built (for PRs and master) via [travis-ci](https://travis-ci.org/mmarquee/ui-automation). Failed builds cannot be merged.

![build status](https://travis-ci.org/mmarquee/ui-automation.svg?branch=master)

# Contributors
Thanks to everyone that has used the library and contributed ideas and suggestions and issues, in order to improve the library. 

## Code Contributors
* [Mark Humphreys](https://github.com/mmarquee)
* [Pascal Bihler](https://github.com/pbi-qfs)
* [Filipe Dias Lewandowski](https://github.com/diasf)

# License
Apache Version 2.0 Copyright (C) 2016

See LICENCE.txt for details.
  
# See also
* [DelphiUIAutomation](https://github.com/markhumphreysjhc/DelphiUIAutomation)
* [Microsoft Accessibility](https://msdn.microsoft.com/en-us/library/vstudio/ms753388(v=vs.100).aspx)
* [UIAutomation for Powershell](http://uiautomation.codeplex.com/documentation)
* [TestStack.White](https://github.com/TestStack/White)
* [UI Automation - A wrapper around Microsoft's UI Automation libraries.](https://github.com/vijayakumarsuraj/UIAutomation)
* [Functional GUI Testing Automation Patterns](https://www.infoq.com/articles/gui-automation-patterns)

