# Discovery 
* See [Element Discovery](element-discovery.md)

```
AutomationComboBox cb1 = window.getCombobox(Search.getBuilder("AutomatedCombobox1").build());
String oldTxt = cb1.text();
cb1.setText("Replacements");
String txt = cb1.text();
```

# Methods

## Name

Gets the name of the button control

## Value

Gets the value of the button, i.e. the text associated with it