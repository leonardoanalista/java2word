package java2word;


import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import word.api.interfaces.IDocument;
import word.w2004.Document2004;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Paragraph;

import java.io.IOException;
import java.io.PrintWriter;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

@Name("reportCaller")
public class ReportCaller {

	@Logger
	protected Log log;

	public void runReport() throws IOException {
		log.info("Running Word Report...");

		FacesContext fc = FacesContext.getCurrentInstance();

		HttpServletResponse servletResponse = (HttpServletResponse) fc
				.getExternalContext().getResponse();

		servletResponse.setContentType("application/msword");
		servletResponse.setHeader("Content-disposition", "inline; filename="
				+ "myWordDocFromSeam.doc");

		PrintWriter writer = servletResponse.getWriter();

		//Create the word document
        IDocument myDoc = new Document2004();
        myDoc.getBody().addEle(new Heading1("Heading01"));
        myDoc.getBody().addEle(new BreakLine(2)); //two break lines
        myDoc.getBody().addEle(new Paragraph("This document is an example of paragraph"));

        String myWord = myDoc.getContent();

		writer.println(myWord);
		fc.responseComplete();

	}

}
