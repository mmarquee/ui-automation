# Discovery 
* See [Element Discovery](element-discovery.md)

# Example
```
  AutomationTreeView tree = window.getTreeView(0);

  AutomationTreeViewItem treeItem = tree.getItem("Sub-SubItem");
  treeItem.select();
```