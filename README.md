#Java2word#

Coding for fun - it has to be enjoyable.
The missing library to generate MS Word Documents from Java code without any special components.

** When I say no third part or external libraries, I mean there isn't any Microsoft library or special components. Please refer to "Dependencies" section below.*


##WHEN TO USE JAVA2WORD##

Suitable when you have that particular clients/customers who want to export huge reports to Microsoft Word and make changes easily. Or when you have a "Release Notes" document as a part of the build.

Or if you need to create Word Document Reports (data coming from a database).

You can utilize the following elements:

* Cover page with images or text
* Table of content based on Document Headings *
* Headings/Sessions/Title or Subtitles. Eg.: Heading1, Heading2
* Header with company's logo
* Footer with page numbers
* Paragraphs
* Page break
* Table with data coming from your database queries
And the end user might want to edit this document.


**Believe me**: We first implemented this using Jasper Reports and the final result was a just horrible. We created about 20 little reports, put all together as sub-reports, export them to RTF and opened it in MS Word. The RTF file had 5 MB and after saving as .DOC, file had 40MB!

Jasper generates some dodge tables which are really annoying to edit their content.

It is so painful to implement this in Jasper. In addition to that, it demands a lot of manual work to put them together and you also have to manually convert to MS Word. I know it sounds very inefficient because it is!


##WHEN TO NOT USE##

Fancy word documents with rich format/style.



##JAVA2WORD IS SIMPLE LIKETHIS##

Here is the code snippets:

```
 IDocument myDoc = new Document2004();
myDoc.addEle(Heading1.with("Heading01").create());
myDoc.addEle(Paragraph.with("This document is an example of paragraph").create());

myDoc.addEle(new Image("http://your_web_app/images/logo1w.png", ImageLocation.WEB_URL));


myDoc.addEle(new BreakLine(2)); //two break lines
    
Then:

         
String myWord = myDoc.getContent();
    
Now you can add this to your ServletResponse:

     
PrintWriter writer = servletResponse.getWriter();
writer.println(myWord);    
    
Then you will have your Microsoft Word document ready to be downloaded!

For non-web applications, you could create a local file:

 File fileObj = new File("/home/leonardo/Desktop/Java2word_allInOne.doc");

PrintWriter writer = null;
try {
    writer = new PrintWriter(fileObj);
} catch (FileNotFoundException e) {
    e.printStackTrace();
}
String myWord = myDoc.getContent();

writer.println(myWord);
writer.close();            
    
Take a look at the example projects in order to have an idea about how to use it in a real world application.
```


##HOW TO GETTHE SERVLETRESPONSE REFERENCE##


###Using JBoss Seam###

If you are using JBoss Seam, you'll have something like (Full Working Class Example):


```
import java.io.PrintWriter;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import word.api.interfaces.IDocument;
import word.w2004.Document2004;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Paragraph;
//probably other imports...


@Name("mySeamComponent")
public class reportCreator {

       public void getReport(){
                FacesContext fc = FacesContext.getCurrentInstance();
                
                HttpServletResponse servletResponse = (HttpServletResponse) fc
                                .getExternalContext().getResponse();

                //'UTF-8', 'ISO-8859-1' or nothing? up to you...
                //servletResponse.setContentType("application/msword; charset=UTF-8");
                servletResponse.setHeader("Content-disposition", "inline; filename="
                                + "myWordDoc.doc");
                
                PrintWriter writer = servletResponse.getWriter();


                //Create the word document
                IDocument myDoc = new Document2004();
                myDoc.addEle(Heading1.with("Heading01").create());
                myDoc.addEle(new BreakLine(2)); //two break lines
                myDoc.addEle(Paragraph.with("This document is an example of paragraph").create());

                String myWord = myDoc.getContent();


                writer.println(myWord);
                fc.responseComplete();
       }

}            
```



###Using Struts###

 public ActionForward execute(ActionMapping mapping, ActionForm form,
                HttpServletRequest request, HttpServletResponse response)
                throws Exception {



        //'UTF-8', 'ISO-8859-1' or nothing? up to you...
        //servletResponse.setContentType("application/msword; charset=UTF-8");
        response.setHeader("Content-disposition", "inline; filename="
                        + "myWordDoc.doc");
        
        PrintWriter writer = response.getWriter();

        writer.println(myWord);

        response.flushBuffer();

        return null;
}            
    
##DEPENDENCIES##

xstream-1.3.1.jar : if you use Image component in your doc (needed for Base64 encoding). 

If you use maven, read the section "Java2Word with with Maven" below in this page.



##WITH MAVEN##

At the moment, there is no Maven repository available for this jar. I will try to find some free place to publish it.

If you follow steps in Set up your Development Environment, you should have in your local ~/.m2 folder some jar like: ~/.m2/repository/java2word/java2word/3.x/java2word-3.x.jar.

java2word.jar declares xstream as a dependency.



##WITHOUT MAVEN?##

You just need to drop the the java2word-X.X.jar (obviously) into the lib folder of your web app. If you use Image in your doc you need xstream-1.3.1.jar.



##EXAMPLE PROJECTS##

There is a Java project where I am NOT using maven. So I dropped the jar (java2word-1.0.jar) and xstream-1.3.1.jar files in the lib directory. I utilized Struts in this example:

Take a look at the Struts Example project There is a readme file there with steps for deployment.



##ALL-IN-ONE EXAMPLE - SEE EVERYTHING YOU CAN DO WITH JAVA2WORD.##
TODO:
Please check this page out. 

##SPECIAL CHARACTERS##

http://java2word.blogspot.com/p/encoding-tips-and-tricks.html If there is a better way to do a replacement I am happy to hear about it.


##CREATING A NEW WORDELEMENT##

Lets imagine that you need some element that hasn't been implemented. Eg.: Bold text, Image, List...

http://java2word.blogspot.com/p/how-to-implement-new-java2word-element.html



##TEMPLATES WITHJAVA2WORD (BETA)##

I created a experimental solution for templates. take a look at this example in the wiki: TemplatesWithJava2word



##I WANT TO HELPAND BECOME A COMMITTER##
TODO:
It will later write some guidelines about the elements design, unit tests, coverage, cyclomatic complexity, pmd, findbugs...

If you are really keen to help, get started by reading this page and see how easily you will be able to help.



##I WANT TO SUGGESTSOMETHING##
TODO:
Please open a new issue with Status = Suggestion and Label = Type-Enhancement or New-Development. The suggestion can be promoted to a "Task" to be done.



##I WANT TO REPORT A BUG##
TODO:
Please open a new issue with "Status = New" and "Label = Type-Defect". Bug is different to new dev or suggestion. Please make sure you don't send suggestion as a bug.



##JAVADOC##

I don't know how to host this in google code. The good thing is I commented in almost all methods. When you are writing your code, Eclipse (or whatever) should display the comment for you.



##CURIOSITIES##

There was a real world project necessity described above but I really wanted to practice TDD skills and apply all code quality tools in java. I decided to host the code at Google's SVN. I created the project and gave a really one line description just because it was a required field and 5 days after, one guy asked me if I had any documentation - I had to quickly write some and add examples...
I "forced" the usage some design patterns GOF (Strategy) just because I wanted to practice.
I (Leonardo) haven't used Windows since 2008.
I use Mac OS and Linux and currently don't have MS Word installed. I use VM and Word Viewer on Wine' 


