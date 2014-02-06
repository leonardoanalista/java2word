package java2word.actions;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.opensymphony.xwork2.ActionSupport;

public class Testing extends ActionSupport implements ServletResponseAware, ServletRequestAware {

    @Override
    public String execute() throws Exception {
//		System.out.println("### About to generate Word doc...");

//		System.out.println("XML is: \n" + this.xml + "\n");
//###LEO

        request.setAttribute("res", "@@@ " + new Date());
        request.getSession().setAttribute("xml", xml);

        //'UTF-8', 'ISO-8859-1' or nothing? up to you...
        if (!StringUtils.isEmpty(xml)) {
            response.setContentType("application/msword; charset=UTF-8");
            response.setHeader("Content-disposition",
                    "inline;filename=wordDoc.doc");

            PrintWriter writer = response.getWriter();
            writer.println(xml);
            writer.flush();
//		System.out.println("### Doc generated...");
            return null;
        }else{
            System.out.println("Error: Empty XML field porra!!!" );
        }


        return SUCCESS;
    }

    // ### Getters and setters
    private String xml;
    private HttpServletResponse response;
    private HttpServletRequest request;

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    //@Override
    public void setServletResponse(HttpServletResponse servletResponse) {
        response = servletResponse;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        request = servletRequest;
    }



}
