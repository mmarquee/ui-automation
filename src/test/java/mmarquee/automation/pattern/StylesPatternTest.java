package mmarquee.automation.pattern;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationStylesPattern;
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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

/**
 * Created by Mark Humphreys on 12/01/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class StylesPatternTest {
    @Mock
    IUIAutomationStylesPattern rawPattern;

    @Spy
    private Unknown mockUnknown;

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
//    @Ignore("Needs further work")
    public void test_getStyleName_Calls_getCurrentStyleName_From_Pattern() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[1];

                String value = "Hello";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        }).when(rawPattern).getCurrentStyleName(anyObject());

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

    @Test(expected=AutomationException.class)
    public void test_That_getPattern_Throws_Exception_When_Pattern_Returns_Error() throws Exception {

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(-1);
            }
        }).when(mockUnknown).QueryInterface(anyObject(), anyObject());

        Styles pattern = new Styles();

        Styles spyPattern = Mockito.spy(pattern);

        IUIAutomationStylesPattern mockPattern = Mockito.mock(IUIAutomationStylesPattern.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(anyObject());

        doReturn(mockPattern)
                .when(spyPattern)
                .convertPointerToInterface(anyObject());

        spyPattern.getStyleName();

        verify(spyPattern, atLeastOnce()).getStyleName();
    }

    @Test
    public void test_That_getPattern_Gets_Pattern_When_No_Pattern_Set() throws Exception {

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(1);
            }
        }).when(mockUnknown).QueryInterface(anyObject(), anyObject());

        Styles pattern = new Styles();

        Styles spyPattern = Mockito.spy(pattern);

        IUIAutomationStylesPattern mockPattern = Mockito.mock(IUIAutomationStylesPattern.class);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[1];

                String value = "Hello";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        }).when(rawPattern).getCurrentStyleName(anyObject());

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(anyObject());

        doReturn(mockPattern)
                .when(spyPattern)
                .convertPointerToInterface(anyObject());

        spyPattern.getStyleName();

        verify(spyPattern, atLeastOnce()).getStyleName();
    }
}
