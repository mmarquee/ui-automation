package mmarquee.automation.pattern;

import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.automation.uiautomation.IUIAutomationElementArray;
import mmarquee.automation.uiautomation.IUIAutomationSelectionPattern;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

/**
 * @author Mark Humphreys
 * Date 15/01/2017.
 *
 * Tests for the lower level calls to COM used in the Selection pattern.
 */
@RunWith(MockitoJUnitRunner.class)
public class SelectionPatternTest2 {
    @Mock
    IUIAutomationSelectionPattern rawPattern;
    @Mock
    AutomationElement element;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        BaseAutomationTest.declarePatternAvailable(element, 
        		PatternID.Selection, PropertyID.IsSelectionPatternAvailable);
    }

    @Spy
    private Unknown mockUnknown;

    @Test
    @Ignore("Needs better tests")
    public void test_getCurrentSelection() throws Exception {

        doAnswer(invocation -> 0).when(rawPattern).getCurrentSelection(any());

        doAnswer(invocation -> new WinNT.HRESULT(0)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Selection pattern = new Selection(element);
        pattern.rawPattern = rawPattern;

        Selection spyPattern = Mockito.spy(pattern);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        IUIAutomationElementArray mockArray = Mockito.mock(IUIAutomationElementArray.class);

        doReturn(mockArray)
                .when(spyPattern)
                .convertPointerToElementArray(any());

        spyPattern.getCurrentSelection();
    }

    @Test
    public void test_canSelectMultiple_Throws_Error_When_Query_Interface_Returns_Error() throws Exception {

        doAnswer(invocation -> 0).when(rawPattern).getCurrentCanSelectMultiple(any());

        Selection pattern = new Selection(element);
        pattern.rawPattern = rawPattern;

        Selection spyPattern = Mockito.spy(pattern);

        spyPattern.canSelectMultiple();
    }

    @Test(expected=AutomationException.class)
    public void test_getCurrentSelection_Throws_Error_When_Query_Interface_Returns_Error() throws Exception {

        doAnswer(invocation -> 1).when(rawPattern).getCurrentSelection(any());

        Selection pattern = new Selection(element);
        pattern.rawPattern = rawPattern;

        Selection spyPattern = Mockito.spy(pattern);

        spyPattern.getCurrentSelection();
    }

    public void test_canSelectMultiple_Returns_False_When_Interface_Returns_True() throws Exception {

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            IntByReference reference = (IntByReference)args[0];

            reference.setValue(0);

            return 0;
        }).when(rawPattern).getCurrentCanSelectMultiple(any());

        doAnswer(invocation -> new WinNT.HRESULT(-1)).when(mockUnknown).QueryInterface(any(), any());

        Selection pattern = new Selection(element);
        pattern.rawPattern = rawPattern;

        Selection spyPattern = Mockito.spy(pattern);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        Boolean value = pattern.canSelectMultiple();

        assertFalse(value);
    }

    @Test(expected = AutomationException.class)
    public void test_canSelectMultiple_Throws_Exception_When_Interface_Returns_Error() throws Exception {

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            IntByReference reference = (IntByReference)args[0];

            reference.setValue(0);

            return -1;
        }).when(rawPattern).getCurrentCanSelectMultiple(any());

        Selection pattern = new Selection(element);
        pattern.rawPattern = rawPattern;

        Selection spyPattern = Mockito.spy(pattern);

        Boolean value = pattern.canSelectMultiple();

        assertFalse(value);
    }

    public void test_canSelectMultiple_Returns_True_When_Interface_Returns_True() throws Exception {

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            IntByReference reference = (IntByReference)args[0];

            reference.setValue(1);

            return 0;
        }).when(rawPattern).getCurrentCanSelectMultiple(any());

        doAnswer(invocation -> new WinNT.HRESULT(-1)).when(mockUnknown).QueryInterface(any(), any());

        Selection pattern = new Selection(element);
        pattern.rawPattern = rawPattern;

        Selection spyPattern = Mockito.spy(pattern);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        Boolean value = pattern.canSelectMultiple();

        assertTrue(value);
    }
}
