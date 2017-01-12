package mmarquee.automation.pattern;

import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationStylesPattern;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

/**
 * Created by Mark Humphreys on 12/01/2017.
 */
public class StylesPatternTest {
    @Mock
    IUIAutomationStylesPattern rawPattern;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_getStyleId_Calls_getCurrentStyleId_From_Pattern() throws Exception {
        Styles pattern = new Styles(rawPattern);

        pattern.getStyleId();

        verify(rawPattern, atLeastOnce()).getCurrentStyleId(anyObject());
    }

    @Test(expected= AutomationException.class)
    public void test_getStyleId_Throws_Exception_When_Pattern_Returns_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return 1;
            }
        }).when(rawPattern).getCurrentStyleId(anyObject());

        Styles pattern = new Styles(rawPattern);

        pattern.getStyleId();
    }

    @Test
    @Ignore("Needs further work")
    public void test_getStyleName_Calls_getCurrentStyleName_From_Pattern() throws Exception {
        Styles pattern = new Styles(rawPattern);

        pattern.getStyleName();

        verify(rawPattern, atLeastOnce()).getCurrentStyleName(anyObject());
    }

    @Test(expected= AutomationException.class)
    public void test_getStyleName_Throws_Exception_When_Pattern_Returns_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return 1;
            }
        }).when(rawPattern).getCurrentStyleName(anyObject());

        Styles pattern = new Styles(rawPattern);

        pattern.getStyleName();
    }
}
