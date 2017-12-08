# Support for legacy Windows versions

The underlying windows COM library is versioned, and uses different versions of the interfaces for different Windows releases.

These versions are:

## Windows 7
* IUIAutomation
* IUIAutomationElement
## Windows 8
* IUIAutomation2
* IUIAutomationElement2
## Windows 8.1
* IUIAutomation3
* IUIAutomationElement3

The functionality supported by this library is only important issue is the addition of the `showContextMenu()` method that is available in
IUIAutomationElement3.

## Windows 10
* IUIAutomationElement4
## Windows 10 (Build 1703)
* IUIAutomationElement5
* IUIAutomationElement6
* IUIAutomationElement7


