package mmarquee.automation;

import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.controls.ElementBuilder;
import mmarquee.automation.controls.menu.AutomationMainMenu;
import mmarquee.automation.uiautomation.IUIAutomationCacheRequest;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

public class CacheRequestTest {

    CacheRequest request;

    @Mock
    PointerByReference cache;

    @Spy
    private Unknown mockUnknown;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = AutomationException.class)
    public void constructor_Throws_exception_When_QueryInterface_Fails() throws Exception {
        UIAutomation automation = Mockito.mock(UIAutomation.class);

        doReturn(mockUnknown)
                .when(automation)
                .makeUnknown(any());

        doAnswer(invocation -> new WinNT.HRESULT(-1)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        when(automation.createCacheRequest()).thenReturn(cache);
        request = new CacheRequest(automation);
    }

    @Test
    public void constructor_succeeds_When_QueryInterface_Succeeds() throws Exception {
        UIAutomation automation = Mockito.mock(UIAutomation.class);

        doReturn(mockUnknown)
                .when(automation)
                .makeUnknown(any());

        doAnswer(invocation -> new WinNT.HRESULT(0)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        when(automation.createCacheRequest()).thenReturn(cache);

        request = new CacheRequest(automation);
    }

    @Test
    public void getValue() throws Exception {
        UIAutomation automation = Mockito.mock(UIAutomation.class);

        doReturn(mockUnknown)
                .when(automation)
                .makeUnknown(any());

        doAnswer(invocation -> new WinNT.HRESULT(0)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        when(automation.createCacheRequest()).thenReturn(cache);

        request = new CacheRequest(automation);

        request.getValue();
    }

    @Test
    public void addPattern() throws Exception {
        UIAutomation automation = Mockito.mock(UIAutomation.class);

        doReturn(mockUnknown)
                .when(automation)
                .makeUnknown(any());

        doAnswer(invocation -> new WinNT.HRESULT(0)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        when(automation.createCacheRequest()).thenReturn(cache);

        IUIAutomationCacheRequest mockRequest = Mockito.mock(IUIAutomationCacheRequest.class);

        request = Mockito.mock(CacheRequest.class);

        doReturn(mockRequest)
                .when(request)
                .convertPointerToElementInterface(any());

        request.addPattern(0);

        CacheRequest spy = Mockito.spy(request);

        doReturn(mockRequest)
                .when(spy)
                .convertPointerToElementInterface(any());

        spy.addPattern(0);

        verify(spy, atLeastOnce()).addPattern(any(Integer.class));
    }

    @Test
    public void addProperty() throws Exception {
        UIAutomation automation = Mockito.mock(UIAutomation.class);

        doReturn(mockUnknown)
                .when(automation)
                .makeUnknown(any());

        doAnswer(invocation -> new WinNT.HRESULT(0)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        when(automation.createCacheRequest()).thenReturn(cache);

        IUIAutomationCacheRequest mockRequest = Mockito.mock(IUIAutomationCacheRequest.class);

        request = new CacheRequest(automation);

        CacheRequest spy = Mockito.spy(request);

        doReturn(mockRequest)
                .when(spy)
                .convertPointerToElementInterface(any());

        spy.addProperty(0);

        verify(spy, atLeastOnce()).addProperty(any(Integer.class));
    }
}