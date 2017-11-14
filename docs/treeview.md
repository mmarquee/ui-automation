# Discovery 
* See [Element Discovery](element-discovery.md)

# Example
```
  AutomationTreeView tree = window.getTreeView(Search.getBuilder(0).build());

  AutomationTreeViewItem treeItem = tree.getItem(Search.getBuilder("Sub-SubItem").build());
  treeItem.select();
```