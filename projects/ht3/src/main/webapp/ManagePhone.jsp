<?xml version="1.0" encoding="UTF-8" ?>
<%@ page import="app.Person" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="strings.Attributes" %>
<%@ page import="app.Phone" %>
<%@ page import="app.Phonebook" %>
<%@ page import="strings.Request" %>
<%@ page import="java.sql.SQLException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Управление данными о номере</title>
</head>
<body>
<%
    HashMap<String, String> jspParameters = new HashMap<>();
    Person person = new Person();
    Phone phone = new Phone();
    String errorMessage;

    if (request.getAttribute(Attributes.JSP_PARAMETERS) != null) {
        jspParameters = (HashMap<String, String>) request.getAttribute(Attributes.JSP_PARAMETERS);
    }
    if (request.getParameter(Request.OWNER_ID) != null) {
        try {
            person = Phonebook.getInstance().getPerson(request.getParameter(Request.OWNER_ID));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    if (request.getAttribute(Attributes.PHONE) != null) {
        phone = (Phone) request.getAttribute(Attributes.PHONE);
    }

    errorMessage = jspParameters.get(Attributes.ERROR_MSG);
%>
<form action="<%=request.getContextPath()%>/phone/" method="post">
    <input type="hidden" name="ownerId" value="<%=person.getId()%>"/>
    <input type="hidden" name="id" value="<%=phone.getId()%>"/>

    <table align="center" border="1" width="70%">
        <%
            if ((errorMessage != null) && (!errorMessage.equals(""))) {
        %>
        <tr>
            <td colspan="2" align="center"><span style="color:red"><%=errorMessage%></span></td>
        </tr>
        <%
            }
        %>
        <tr>
            <td colspan="2" align="center">Информация о телефоне владельца: <%=person.getFML()%>
            </td>
        </tr>
        <tr>
            <td>Номер:</td>
            <td><input type="text" name="number" value="<%=phone.getNumber()%>"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" name="<%=jspParameters.get(Attributes.PARAM_NEXT_ACTION)%>"
                       value="<%=jspParameters.get(Attributes.PARAM_NEXT_ACTION_LABEL)%>"/>
                <br><a
                    href="<%=request.getContextPath()%>/?action=<%=Attributes.ACTION_EDIT_PERSON%>&id=<%=request.getParameter(Request.OWNER_ID)%>">Вернуться
                к данным о человеке</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>