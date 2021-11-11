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
<title>P�melding</title>
</head>
<body>
	<h2>P�melding</h2>
	<p>
		<font color="red">${feilmelding}</font>
	</p>
	<form method="post" class="pure-form pure-form-aligned">
		<fieldset>
			<div class="pure-control-group">
				<label for="fornavn">Fornavn:</label> <input type="text"
					name="fornavn" value="${regskjema.fornavn}" /> 
					<font color="red">${regskjema.fornavnFeilmelding}</font>
			</div>
			<div class="pure-control-group">
				<label for="etternavn">Etternavn:</label> <input type="text"
					name="etternavn" value="${regskjema.etternavn}" /> 
					<font color="red">${regskjema.etternavnFeilmelding}</font>
			</div>
			<div class="pure-control-group">
				<label for="mobil">Mobil (8 siffer):</label> <input type="text"
					name="mobil" value="${regskjema.mobil}" /> 
					<font color="red">${regskjema.mobilFeilmelding}</font>
			</div>
			<div class="pure-control-group">
				<label for="password">Passord:</label> <input type="password"
					name="passord" value="${regskjema.pass}" /> 
					<font color="red">${regskjema.passFeilmelding}</font>
			</div>
			<div class="pure-control-group">
				<label for="passordRepetert">Passord repetert:</label> <input
					type="password" name="passordRepetert"
					value="${regskjema.rpass}" /> 
					<font color="red">${regskjema.rpassFeilmelding}</font>
			</div>
			<div class="pure-control-group">
				<label for="kjonn">Kj�nn:</label> <input type="radio" name="kjonn"
					value="mann"
					 />mann
				<input type="radio" name="kjonn" value="kvinne"
					 />kvinne
				<font color="red">${regskjema.kjonnFeilmelding}</font>
			</div>
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-button-primary">Meld
					meg p�</button>
			</div>
		</fieldset>
	</form>
</body>
</html>