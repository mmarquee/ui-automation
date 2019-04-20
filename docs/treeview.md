# Discovery 
* See [Element Discovery](element-discovery.md)

# Example
```
  TreeView tree = window.getTreeView(Search.getBuilder(0).build());

  TreeViewItem treeItem = tree.getItem(Search.getBuilder("Sub-SubItem").build());
  treeItem.select();
```