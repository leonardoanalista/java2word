#Java2word#


The missing library to generate MS Word Documents from Java code without any special components.

_Coding for fun - it has to be enjoyable._


_* When I say no third part or external libraries, I mean there isn't any Microsoft library or special components. Please refer to "Dependencies" section below._

project has been migrated from: 
* https://code.google.com/p/java2word/
* http://java2word.blogspot.com.au/


##WHEN TO USE JAVA2WORD##

Suitable when you have that particular clients/customers/project where you want to export or create MS Word reports and make changes easily. Another situation is to generate "Release Notes" document as a part of the build.

If you need to create MS Word document that looks like a reports and data comes from a database, this is the perfect library for it.

You could use it in a web or non-web application.


##Just a quick taste of java2word##

```
IDocument myDoc = new Document2004();
myDoc.addEle(Heading1.with("Heading01").create());
myDoc.addEle(BreakLine.times(2).create()); //two break lines
myDoc.addEle(Paragraph.with("This document is an example of paragraph").create());

//then get the XML representation of the MS Word document
String myWord = myDoc.getContent(); 
```


##You can utilize the following elements:##

* Cover page with images or text
* Table of content based on Document Headings *
* Headings/Sessions/Title or Subtitles. Eg.: Heading1, Heading2
* Header with company's logo
* Footer with page numbers
* Paragraphs
* Page break
* Table with data coming from your database queries


**Believe me**: We first implemented this using Jasper Reports and the final result wasn't great. We created about 20 little reports, put all together as sub-reports, export them to RTF and opened it in MS Word. The RTF file had 5 MB and after saving as .DOC, file jumped to 40MB!

Jasper generates some dodge tables which are really annoying to edit their content.

It is so painful to implement this in Jasper. In addition to that, it requires a lot of manual work to put them together and you also have to manually convert to MS Word.


##WHEN TO NOT USE##

* Too fancy word documents with rich format/style.



##JAVA2WORD IS SIMPLE LIKETHIS##

Here is the code snippets:

```
 IDocument myDoc = new Document2004();
myDoc.addEle(Heading1.with("Heading01").create());
myDoc.addEle(Paragraph.with("This document is an example of paragraph").create());

myDoc.addEle(new Image("http://your_web_app/images/logo1w.png", ImageLocation.WEB_URL));


myDoc.addEle(new BreakLine(2)); //two break lines
```

Then:

```         
String myWord = myDoc.getContent();
```


Now you can add this to your ServletResponse:

```     
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
    
```    

Take a look at the example projects to have a better understanding how to use it in a real world application.



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

* xstream-1.3.1.jar : Only if you use Image component in your doc (needed for Base64 encoding). 

If you use maven, read the section "Java2Word with with Maven" below.



##WITH MAVEN##

At the moment, there is no Maven repository available for this jar. If you know any free place to publish the Jar file please let me know.

If you follow steps in "Set up your Development Environment" and successfully build the project, you should have in your local **~/.m2** folder some jar like: **~/.m2/repository/java2word/java2word/3.x/java2word-3.x.jar**.




##WITHOUT MAVEN?##

You just need to drop the the *java2word-X.X.jar* (obviously) into the lib folder of your web app. If you use Image in your doc you need *xstream-1.3.1.jar*.



##EXAMPLE PROJECTS##

Java project NOT using maven. Drop the jar (java2word-1.0.jar) and xstream-1.3.1.jar files in the lib directory. I utilized Struts in this example:


Take a look at the Struts Example project. There is a readme file there with steps for deployment.



##ALL-IN-ONE EXAMPLE - SEE EVERYTHING YOU CAN DO WITH JAVA2WORD.##

Please check out this page:
https://github.com/leonardoanalista/java2word/wiki/all-in-one-example


##SPECIAL CHARACTERS##

Please check out this page:
https://github.com/leonardoanalista/java2word/wiki/Encoding-Tips-and-Tricks
If there is a better way to do a replacement I am happy to hear about it.



##CREATING A NEW WORDELEMENT##

Lets imagine that you need some element that hasn't been implemented. Eg.: Bold text, Image, List...
Take a look at this page:
https://github.com/leonardoanalista/java2word/wiki/how-to-implement-a-new-element



##TEMPLATES WITHJAVA2WORD (BETA)##

I created a experimental solution for templates. Take a look at this page: 
https://github.com/leonardoanalista/java2word/wiki/templates



##I WANT TO HELPAND##
* build the project
* follow current pattern. Feel free to improve things. I wrote the code about 3 or 4 years ago, very late at night.
* write unit tests, check coverage, cyclomatic complexity, pmd and findbugs.
* test is in a real MS word document.
* raise a pull request




##JAVADOC##

I don't know how to host this in google code. The good thing is I commented in almost all methods. When you are writing your code, Eclipse (or whatever) should display the comment for you.

TODO: link to a component library reference.


####CURIOSITIES####

There was a real world project needed. However I really wanted to practice TDD skills and apply all code quality tools in java back in 2009. I decided to host the code at Google's SVN. I created the project and gave literally one line description just because field was required. After 5 days, one guy asked me about the documentation - I had to quickly add examples.

* I "forced" the usage some design patterns GOF (Strategy) just because I wanted to practice.
* I haven't used Windows since 2008.
* I use Mac OS and Linux and currently don't have MS Word installed. I use VM and Word Viewer on Wine' 
* I am now a front-end developer. I don't have a lot of time to alocate to this library. Feel free to send a pull request and contribute.  




_abraco a todos!_
_Leonardo Correa_


