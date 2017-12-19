There is limited support for caching, enough to demonstrate the functionality that is available.

#### Getting properties and patterns into the cache

The following code example shows a function that retrieves items from a window and caches the Selection pattern and Name property for each item. 
By default, the list items are returned with a full reference, so that all current properties are still available.
```
    AutomationWindow window = automation.getDesktopWindow("Form1");

    PointerByReference condition = automation.createTrueCondition();

    CacheRequest cacheRequest = new CacheRequest(automation);

    cacheRequest.addPattern(PatternID.Selection.getValue());
    cacheRequest.addProperty(PropertyID.Name.getValue());

    List<AutomationElement> all =
        window.getElement().findAll(new TreeScope(TreeScope.Children), condition, cacheRequest);

    logger.info(all.size());

    for(AutomationElement item: all) {
        logger.info(" *" +item.getCachedName());
    }
```

## See also
* [How to Use Caching](https://msdn.microsoft.com/en-us/library/windows/desktop/ff625921(v=vs.85).aspx)
