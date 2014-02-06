package java2word;

import java2word.actions.Testing;

import org.apache.struts2.StrutsTestCase;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.junit.Test;

import com.opensymphony.xwork2.ActionProxy;

import static com.opensymphony.xwork2.ActionSupport.*;

public class TestingTest extends StrutsTestCase{

    @Test
    public void testSanity() throws Exception{
        //pre requirements
        request.setParameter("xml", "this is the xml");

        //kinda of replay()
        ActionProxy proxy = getActionProxy("/testing");
        Testing testingAction = (Testing) proxy.getAction();

        String result = proxy.execute();


        assertEquals("XML entered should be the same in SESSION after execution", "this is the xml", request.getSession().getAttribute("xml"));
        assertEquals("XML entered should be the same after execution", "this is the xml", testingAction.getXml());
        assertNotNull("Request attribute 'res' shouldn't be null", request.getAttribute("res"));
        assertTrue("There shouldn't be any field error.", testingAction.getFieldErrors().size() == 0);
        assertEquals("Result returned when there is an XML should have been 'null'.", null, result);

        //assertTrue("Problem field account.userName not present in fieldErrors but it should have been",
        //        testingAction.getFieldErrors().containsKey("xml") );

    }

}
