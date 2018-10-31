By default Chrome is opaque to the UI Automation API, but there is a way of turn on the support, and thus being able to navigate through the control hierarchy.

If you navigate to `chrome://accessibility/`, then you can turn accessibility on
globally or in a given page. Starting the application with the
`--force-renderer-accessibility` option will start the application in the
correct mode.

