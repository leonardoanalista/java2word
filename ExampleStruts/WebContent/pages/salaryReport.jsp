<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>

<html:html>
<head>
	<title>Java2Word Generator - Struts Application Example</title>
</head>
<body>

<html:link href="/ExampleStruts">Home</html:link>


    <html:form action="/salaryReport.do">
    	<h2>Java2Word Generator - Struts Application Example</h2>
    	<hr></hr>
    	<h2>Employee Salary Report</h2>
    	<h4>Report Filter:</h4>
        <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td>Name:</td>
                <td><html:text property="name" /></td>
            </tr>
			<tr>
                <td>Salary Greater Than:</td>
                <td><html:text property="greaterThan" /> </td>
            </tr>
			<tr>
                <td>Salary Less Than:</td>
                <td><html:text property="lessThan" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <html:submit value="   Get Word Report!   " />
                </td>
            </tr>
        </table>
    </html:form>

<hr>

    <html:form action="/allInOne.do">
    	<h2>All In One</h2>
    	<p><b>All In One</b> is an example where all components/elements in the API are utilized. </p>
    	<p>Always take a look at the 'Table of Suported Tags' in the project's page in <a href="http://code.google.com/p/java2word/">http://code.google.com/p/java2word/</a> </p>
    	 <html:submit value="   Get All In One Word Doc!   " />
    </html:form>



</body>
</html:html>
