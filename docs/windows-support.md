# Support for legacy Windows versions

The underlying windows COM library is versioned, and uses different versions of the interfaces for different Windows releases.

These versions are:

## Windows 7

The library seems to be present in Windows 7, but doesn't seem to work as it does in later version, so it is not possible to use much of _this_ library for Windows 7 automation. 

* `IUIAutomation`
* `IUIAutomationElement`
## Windows 8
* `IUIAutomation2`
* `IUIAutomationElement2`
## Windows 8.1
* `IUIAutomation3`
* `IUIAutomationElement3`

The functionality supported by this library is only important issue is the addition of the `showContextMenu()` method that is available in
`IUIAutomationElement3`.
       
## Windows 10
* `IUIAutomationElement4`
## Windows 10 (Build 1703)
* `IUIAutomationElement5`
* `IUIAutomationElement6`

The functionality supported by this library is only important issue is the addition of the `getFullDescription()` method that is available in
`IUIAutomationElement6`.

* `IUIAutomationElement7`

## Windows 10 (Build 1709)
* `IUIAutomationSelectionPattern2`

