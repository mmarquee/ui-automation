Edit boxes can be located by index, or by name.

```java
  AutomationEditBox user = window.getEditBox(0);
  user.setText("USER1");
```

```java
  AutomationEditBox user = window.getEditBox("edtBox1");
  user.setText("USER1");
```