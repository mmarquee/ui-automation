Edit boxes can be located by index, or by name.

```
  AutomationEditBox user = window.getEditBox(0);
  user.setText("USER1");
```

```
  AutomationEditBox user = window.getEditBox("edtBox1");
  user.setText("USER1");
```