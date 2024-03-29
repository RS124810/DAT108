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
<title>Logg inn</title>
</head>
<body>
	<h2>Logg inn</h2>
	<p>Det er kun registrerte deltagere som f�r se deltagerlisten.</p>
	<p>
		<font color="red">${feilmelding}</font>
	</p>
	<form method="post" class="pure-form pure-form-aligned">
		<fieldset>
			<div class="pure-control-group">
				<label for="mobil">Mobil:</label> <input type="text" name="mobil" value = "${loginskjema.mobil}" /><font color="red">${loginskjema.mobilFeilmelding}</font><br>
			</div>
			<div class="pure-control-group">
				<label for="passord">Passord:</label> <input type="password"
					name="passord" value = "${loginskjema.pass}"/><font color="red">${loginskjema.passFeilmelding}</font><br>
			</div>
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-button-primary">Logg
					inn</button>
			</div>
		</fieldset>
	</form>
<a href="RegServlet"> trykk her for registrere en ny deltager</a>
</body>
</html>