<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>Java2word - Web Test Appliction</title>
</head>

<body>
	<p><a href="<s:url action='testing'/>">Hello World</a></p>	

<h3>Java2word - Web Test Appliction</h3>
<hr>

<table border="0">
<tr>
<td>
	
	<p>Enter your XML:</p>
	<s:form action="testing">
		<s:submit value="Get Word Document!"/>
		<s:textarea rows="30" cols="100" name="xml"/>
		<s:submit value="Get Word Document!"/>
	</s:form>
	
</td>
<td align="top">
		
	<hr/>
	<h2>How to use this App?</h2>
	<ol>
        <li>In your application, write your unit test for the Word Doc.</li>
        <li>In your unit test, print in the console your Document2004.getContent().</li>
        <li>Copy and paste here your XML W2004.</li>
        <li>Click in "Get Word Document!"</li>
        <li>Save the generated word doc and see if it is what you expected.</li>
	</ol>
	<h4>Productivity Tips</h4>
	<p>Obiviuslly you will print the XML in the console until you see the Word Document the way you want. </p>
	<p>As soon as you realise that you have achieved what you want, remove the print.out</p>
	<p>This will avoid you to deploy your Bussiness Application to see the final result. </p>
	<p>If you use the Web Test App, you will see the Word Document result in a matter of seconds.</p>
	
	<br/>
	<p>If you don't know how and where to start, fair enough. Take a look at the Example Web Applications.</p>
	<p>Don't forget to read the wiki <a target="_blank" href="http://code.google.com/p/java2word/wiki/Elements_Reference">Elements Reference</a></p>
	<br/>
	<hr/>
		
</td>
</tr>	
</table>
	
	

</body>
</html>
