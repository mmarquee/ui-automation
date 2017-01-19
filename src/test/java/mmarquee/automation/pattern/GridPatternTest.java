package mmarquee.automation.pattern;

import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import mmarquee.automation.uiautomation.IUIAutomationGridPattern;
import mmarquee.automation.uiautomation.IUIAutomationRangeValuePattern;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

/**
 * Created by Mark Humphreys on 11/01/2017.
 *
 * Tests for the Grid pattern
 */
@RunWith(MockitoJUnitRunner.class)
public class GridPatternTest {
    @Mock
    IUIAutomationGridPattern rawPattern;

    @Spy
    private Unknown mockUnknown;

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

    @Test
    @Ignore("Need to build up the mocking")
    public void test_getItem_Throws_Exception_When_Pattern_Returns_Error_From_GetItem() throws Exception {
        Grid item = new Grid(rawPattern);

        AutomationElement element = item.getItem(0,0);
    }

    @Test(expected=AutomationException.class)
    public void test_That_getPattern_Throws_Exception_When_Pattern_Returns_Error() throws Exception {

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(-1);
            }
        }).when(mockUnknown).QueryInterface(anyObject(), anyObject());

        Grid pattern = new Grid();

        Grid spyPattern = Mockito.spy(new Grid());

        IUIAutomationGridPattern mockGrid = Mockito.mock(IUIAutomationGridPattern.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(anyObject());

        doReturn(mockGrid)
                .when(spyPattern)
                .convertPointerToInterface(anyObject());

        spyPattern.getItem(0,0);

        verify(mockGrid, atLeastOnce()).getItem(anyObject(), anyObject(),anyObject());
    }

    @Test
    @Ignore("Mocking issues, need to be sorted")
    public void test_That_getPattern_Gets_Pattern_When_No_Pattern_Set() throws Exception {

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(0);
            }
        }).when(mockUnknown).QueryInterface(anyObject(), anyObject());

        Grid pattern = new Grid();

        Grid spyPattern = Mockito.spy(new Grid());

        IUIAutomationGridPattern mockGrid = Mockito.mock(IUIAutomationGridPattern.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(anyObject());

        doReturn(mockGrid)
                .when(spyPattern)
                .convertPointerToInterface(anyObject());

        IUIAutomationElement mockElement = Mockito.mock(IUIAutomationElement.class);

        doReturn(mockElement)
                .when(spyPattern)
                .convertPointerToElementInterface(anyObject());

        spyPattern.getItem(0,0);

        verify(mockGrid, atLeastOnce()).getItem(anyObject(), anyObject(),anyObject());
    }
}
