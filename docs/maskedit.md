# Discovery 
* See [Element Discovery](element-discovery.md)

The [DelphiUIAutomation](https://github.com/markhumphreysjhc/DelphiUIAutomation) project introduced some Delphi controls that implement IUIAutomation providers, allowing them to be accessed by automation. The Delphi implementation of a masked edit is another of these.

There does not seem to be an equivalent WPF control.

```
  try {
    AutomationMaskedEdit me0 = window.getMaskedEdit(Search.getBuilder("AutomatedMaskEdit1").build());

    // Get the current value
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
