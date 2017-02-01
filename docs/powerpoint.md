Powerpoint has a control hierarchy, and uses 'Slides' which are Custom controls. There is some weirdness associated with the contents of each page, as they appear to be Images, even though they are actually text on the screen. 

![UISpy for Powerpoint](https://github.com/mmarquee/ui-automation/blob/gh-pages/powerpoint.png)

```java
  AutomationWindow window = application.getWindow("This is text.pptx - PowerPoint");
  logger.info(window.name());

  AutomationPanel panelX = window.getPanelByClassName(0, "MDIClient");
  logger.info(panelX.name());
  logger.info(panelX.getClassName());

  AutomationPanel panel1 = panelX.getPanel("PowerPoint Edit View - [This is text.pptx]");
  logger.info(panelX.name());
  AutomationPanel panel2 = panel1.getPanel("Slide");
  logger.info(panel2.name());
  AutomationPowerpointSlide slide = panel2.getPowerpointSlide("Slide 1 - This is text");
  logger.info(slide.name());

  // Oddly enough this is an image control, and has text in it's selection
  AutomationImage image = slide.getImage("Title TextBox");
```            

