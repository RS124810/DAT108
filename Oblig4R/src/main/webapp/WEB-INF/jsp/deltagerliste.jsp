<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<title>Deltagerliste</title>
</head>
<body>
	<h2>Deltagerliste</h2>
		
	<table class="pure-table">
		<tr bgcolor="#cccccc">
			<th>Kjønn</th>
			<th align="left">Navn</th>
			<th align="left">Mobil</th>
		</tr>
	<c:forEach items="${deltagere}" var="d">
		<tr bgcolor="#ffffff">
		<c:if test="${d.mobilNr == mobilnr}"><tr bgcolor="#aaffaa"></c:if>
			<td align="center">
			<c:if test="${d.kjonn eq 'mann'}">&#9794;</c:if>
			<c:if test="${d.kjonn eq 'kvinne'}">&#9792;</c:if></td>
			<td>${d.fornavn} ${d.etternavn} </td>
			<td> ${d.mobilNr}</td>
		</tr>
	</c:forEach>
	</table>
	
	<%--<p>${d.fornavn} ${d.etternavn} ${d.mobilNr}</p> --%>
	<%-- if d.mobilNr == mobilnr ? bgcolor="#aaffaa" : bgcolor="#ffffff"   --%>
	
	
	
	<p>
		<a href="LogutServlet">Ferdig</a>
	</p>
</body>
</html>