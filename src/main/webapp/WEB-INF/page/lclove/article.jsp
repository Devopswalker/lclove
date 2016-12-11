<%--
  Created by IntelliJ IDEA.
  User: shao
  Date: 2016/12/5
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Article</title>
</head>
<body>
<h2>Article Information</h2>
<form:form method="POST" action="/Lclove/addArticle">
  <table>
    <tr>
      <td><form:label path="title">Name</form:label></td>
      <td><form:input path="title" /></td>
    </tr>
    <tr>
      <td><form:label path="content">content</form:label></td>
      <td><form:input path="content" /></td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" value="Submit"/>
      </td>
    </tr>
  </table>
</form:form>

</body>
</html>
