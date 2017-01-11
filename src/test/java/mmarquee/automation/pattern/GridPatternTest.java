package mmarquee.automation.pattern;

import com.sun.jna.ptr.IntByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationExpandCollapsePattern;
import mmarquee.automation.uiautomation.IUIAutomationGridPattern;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

/**
 * Created by Mark Humphreys on 11/01/2017.
 */
public class GridPatternTest {
    @Mock
    IUIAutomationGridPattern rawPattern;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRowCount_Gets_Value_From_Pattern() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(2);

                return 0;
            }
        }).when(rawPattern).getCurrentRowCount(anyObject());

        Grid item = new Grid(rawPattern);

        int count = item.rowCount();

        assertTrue(count == 2);
    }

    @Test
    public void testGetColumnCount_Gets_Value_From_Pattern() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(99);

                return 0;
            }
        }).when(rawPattern).getCurrentColumnCount(anyObject());

        Grid item = new Grid(rawPattern);

        int count = item.columnCount();

        assertTrue(count == 99);
    }

    @Test(expected= AutomationException.class)
    public void testGetColumnCount_Throws_Exception_When_Pattern_Returns_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(99);

                return 1;
            }
        }).when(rawPattern).getCurrentColumnCount(anyObject());

        Grid item = new Grid(rawPattern);

        int count = item.columnCount();

        assertTrue(count == 99);
    }

    @Test(expected=AutomationException.class)
    public void testGetRowCount_Throws_Exception_When_Pattern_Returns_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(99);

                return 1;
            }
        }).when(rawPattern).getCurrentRowCount(anyObject());

        Grid item = new Grid(rawPattern);

        int count = item.rowCount();

        assertTrue(count == 99);
    }
}
