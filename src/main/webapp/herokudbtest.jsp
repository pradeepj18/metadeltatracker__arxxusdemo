<%@page import="repository.PsqlDataHouse"%>
<%@page import="java.sql.Connection"%>
<%@page import="credentials.DBManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Heroku db test</title>
</head>
<body>
	<script type="text/javascript">
		function createCORSRequest(method, url) {
			var xhr = new XMLHttpRequest();
			if ("withCredentials" in xhr) {
				xhr.open(method, url, true);
			} else if (typeof XDomainRequest != "undefined") {
				xhr = new XDomainRequest();
				xhr.open(method, url);
			} else {
				xhr = null;
			}
			return xhr;
		}
		var url = 'https://sfdcmetadatapsql.herokuapp.com/metadataresources/sfdcmetadataPSQL/';
		var xhr = createCORSRequest('GET', url);
		xhr.send();
		xhr.onload = function() {
			var text = xhr.responseText;
			var title = getTitle(text);
			alert('Response from CORS request to ' + url + ': ' + title);
		};

		xhr.onerror = function(xhr) {
			alert('Woops, there was an error making the request.'+xhr.responseText);
		};
	</script>
</body>
</html>