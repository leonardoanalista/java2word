/*******************************************************************************
 * Copyright (c) 2007 Exadel, Inc. and Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Exadel, Inc. and Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
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
import word.w2004.elements.Image;
import word.w2004.elements.ImageType;
import word.w2004.elements.Paragraph;
import word.w2004.elements.Table;
import word.w2004.elements.tableElements.TableEle;
import demo.business.EmployeeManager;
import demo.domain.Employee;

public class SalaryReportAction extends org.apache.struts.action.Action {

	private String fileName = "leoWordStruts.doc";
	private static java.util.logging.Logger log = Logger.getLogger("SalaryReportAction");

	public SalaryReportAction() {
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("@@@ Generating report...");

		String filter = this.getFilter((SalaryReportForm) form);
		request.setAttribute("filter", filter);

		buildReport(response, filter);

		response.flushBuffer();

		log.info("Document has been written to the servlet response...");
		return null; //return mapping.findForward("ok");
	}

	private void buildReport(HttpServletResponse response, String filter) throws IOException{
		log.info("Filter is: " + filter);

		response.setContentType("application/msword");
		response.setHeader("Content-disposition", "inline; filename="
				+ fileName);

		PrintWriter writer = response.getWriter();

		IDocument mydoc = new Document2004();
		mydoc.getBody().addEle(new Heading1("Employee Salary Report "));
		mydoc.getBody().addEle(
				new Paragraph("Brings up all employee and the respectively salary."));
		mydoc.getBody().addEle(new BreakLine());

		//This is an example of local file. I comment it out because I don't know where you will be running this.
		//mydoc.getBody().addEle(new Paragraph("This bellow image uses full path from root (Local Server): /Users/leonardo_correa/aajava/myworkspaces/leo/ExampleStruts/WebContent/img/dtpick.gif"));
		//mydoc.getBody().addEle(new Image("/Users/leonardo_correa/aajava/myworkspaces/leo/ExampleStruts/WebContent/img/dtpick.gif", ImageType.FULL_LOCAL_PATH));
		//mydoc.getBody().addEle(new BreakLine());

		mydoc.getBody().addEle(new Paragraph("This one, comes from the internet (Out of the server): http://www.google.com.au/intl/en_com/images/srpr/logo1w.png"));
		mydoc.getBody().addEle(new Image("http://www.google.com.au/intl/en_com/images/srpr/logo1w.png", ImageType.WEB_URL));

		mydoc.getBody().addEle(new BreakLine(2));

		Table tbl = new Table();
		tbl.addTableEle(TableEle.TH, "Name", "Salary");
		List<Employee> lst = EmployeeManager.getResultList(); //Filter could be applied here...
		for (Employee person : lst) {
			tbl.addTableEle(TableEle.TD, person.getName(), person.getSalary());
		}
		tbl.addTableEle(TableEle.TF, "Total", "1,100,000.00");

		mydoc.getBody().addEle(tbl);//add my table to the document

		writer.println(mydoc.getContent());
	}

	private String getFilter(SalaryReportForm form) {
		String filter = "";
		if (!"".equals(form.getName())) {
			filter += "name = " + form.getName() + ", ";
		}
		if (!"".equals(form.getGreaterThan())) {
			filter += "greaterThan = " + form.getGreaterThan() + ",";
		}
		if (!"".equals(form.getLessThan())) {
			filter += "lessThan = " + form.getLessThan();
		}
		return filter;
	}

}