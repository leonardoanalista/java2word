package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import word.api.interfaces.IDocument;
import word.w2004.Document2004;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading2;
import word.w2004.elements.Heading3;
import word.w2004.elements.Image;
import word.w2004.elements.ImageType;
import word.w2004.elements.PageBreak;
import word.w2004.elements.Paragraph;
import word.w2004.elements.Table;
import word.w2004.elements.tableElements.TableEle;
import demo.business.EmployeeManager;
import demo.domain.Employee;

/**
 * @author leonardo
 *
 * For demonstration purposes I didn't optimized the code... not worried about it here...
 * For real project, REUSE (or abstract) the method buildReport() or break it down according to your needs
 *
 * Elements are W2004
 */
public class AllInOneAction extends org.apache.struts.action.Action {

	private String fileName = "java2word_AllInOne.doc";
	private static java.util.logging.Logger log = Logger.getLogger("AllInOneAction");

	public AllInOneAction() {
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("@@@ Generating report...");

		buildReport(response);

		response.flushBuffer();

		log.info("@@@ Document has been written to the servlet response...");
		return null; //or maybe: return mapping.findForward("ok");
	}

	private void buildReport(HttpServletResponse response) throws IOException{
		log.info("@@@ Building report...");

		response.setContentType("application/msword");
		response.setHeader("Content-disposition", "inline; filename="
				+ fileName);

		PrintWriter writer = response.getWriter();

		IDocument mydoc = new Document2004();
		mydoc.getBody().addEle(new Heading1("All-In-One Report"));

		mydoc.getBody().addEle(
				new Paragraph("All-In-One uses a little bit of everything in the API. Always take a look at the 'Table of Suported Tags' in the project's page in http://code.google.com/p/java2word/."));
		mydoc.getBody().addEle(
				new Paragraph("There is a BreakLine under this Paragraph. And of course I am a Paragraph."));

		mydoc.getBody().addEle(new BreakLine());

		mydoc.getBody().addEle(new Heading1("I am a Heading1"));
		mydoc.getBody().addEle(new Heading2("I am a Heading2"));
		mydoc.getBody().addEle(new Heading3("I am a Heading3"));

		mydoc.getBody().addEle(new BreakLine());

		mydoc.getBody().addEle(new Heading2("Image"));
		mydoc.getBody().addEle(
				new Paragraph("Images can be configured in tree ways: Local File, Web URL or Classpath"));

		mydoc.getBody().addEle(new Heading3("Image from a Web URL"));
		Image img01 = new Image("http://www.google.com.au/intl/en_com/images/srpr/logo1w.png", ImageType.WEB_URL);
		img01.setWidth("200"); // this will override the default values
		img01.setHeight("30"); // this will override the default values
		mydoc.getBody().addEle(img01);

		mydoc.getBody().addEle(new Paragraph("This one, comes from the internet (Out of the server): http://www.google.com.au/intl/en_com/images/srpr/logo1w.png"));
		mydoc.getBody().addEle(new Paragraph("I changed width and height of the picture."));

		mydoc.getBody().addEle(new BreakLine(2));//Two break lines...

		mydoc.getBody().addEle(new Heading2("There is a PageBreak down here!!!"));
		mydoc.getBody().addEle(new PageBreak());

		mydoc.getBody().addEle(new Paragraph("Lets take a look at a Table example. Table takes default style. A new style framework will have to be designed in the future."));
		Table tbl = new Table();
		tbl.addTableEle(TableEle.TH, "Name", "Salary");
		List<Employee> lst = EmployeeManager.getResultList(); //Filter could be applied here...
		for (Employee person : lst) {
			tbl.addTableEle(TableEle.TD, person.getName(), person.getSalary());
		}
		tbl.addTableEle(TableEle.TF, "Total", "1,100,000.00");

		mydoc.getBody().addEle(tbl);//add my table to the document

		mydoc.getHeader().addEle(new Paragraph("This is a Header in all pages. Can be suppressed in the first page calling mydoc.getHeader().setHideHeaderAndFooterFirstPage(true) "));
		mydoc.getFooter().addEle(new Paragraph("This is the footer... if you hide Header in the first page, Footer will be hide as well"));
		mydoc.getFooter().showPageNumber(true); //default is true ok...


		mydoc.getBody().addEle(new Heading3("I will implement Bold and Italic soon... If you have any suggestion just go to the project's page and create an Issue for the suggestion and we can discuss about it!"));
		mydoc.getBody().addEle(new BreakLine());
		mydoc.getBody().addEle(new Heading3("If you have any suggestion just go to the project's page and create an Issue for the suggestion and we can discuss about it!"));


		writer.println(mydoc.getContent());//now you can call mydoc.getContent()
	}


}