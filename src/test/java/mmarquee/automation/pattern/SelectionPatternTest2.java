package mmarquee.automation.pattern;

import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.WinNT;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationSelectionPattern;
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

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

/**
 * Created by Mark Humphreys on 15/01/2017.
 *
 * Tests for the lower level calls to COM used in the Selection pattern.
 */
@RunWith(MockitoJUnitRunner.class)
public class SelectionPatternTest2 {
    @Mock
    IUIAutomationSelectionPattern rawPattern;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Spy
    Unknown mockUnknown;

    @Test
    @Ignore("Unsure how to mock this")
    public void test_getCurrentSelection() throws Exception {

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                //   Object[] args = invocation.getArguments();
                //   IntByReference reference = (IntByReference)args[0];
//
                //              // Simulate a pointer
                //            reference.setValue(12345);

                return 0;
            }
        }).when(rawPattern).getCurrentSelection(anyObject());

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {

                //   Object[] args = invocation.getArguments();
                //   IntByReference reference = (IntByReference)args[0];
//
                //              // Simulate a pointer
                //            reference.setValue(12345);

                return new WinNT.HRESULT(0);
            }
        }).when(mockUnknown).QueryInterface(anyObject(), anyObject());

        Selection pattern = new Selection(rawPattern);

        Selection spyPattern = Mockito.spy(new Selection(rawPattern));

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(anyObject());

        spyPattern.getCurrentSelection();
    }

    @Test(expected=AutomationException.class)
    public void test_getCurrentSelection_Throws_Error_When_Query_Interface_Returns_Error() throws Exception {

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return 0;
            }
        }).when(rawPattern).getCurrentSelection(anyObject());

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(-1);
            }
        }).when(mockUnknown).QueryInterface(anyObject(), anyObject());

        Selection pattern = new Selection(rawPattern);

        Selection spyPattern = Mockito.spy(new Selection(rawPattern));

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(anyObject());

        spyPattern.getCurrentSelection();
    }
}
