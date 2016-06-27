		<%@include file="header.jsp"%> - Lid Toevoegen</title>
	</head>
	
	<body>
		<%@include file="nav.jsp"%>
		<h1>Lid Toevoegen</h1>
		<form action="sport" method="post">
			<input type="text" name="roepnaam" placeholder="Roepnaam">
			<input type="text" name="tussenvoegsels" placeholder="Tussenvoegsels">
			<input type="text" name="achternaam" placeholder="Achternaam">
			<input type="text" name="email" placeholder="E-mail">
			<input type="text" name="geb_datum" placeholder="Geboortedatum (dd-mm-jjjj)">
			<input type="submit" name="verzend_nieuw_lid_knop" value="verstuur">
			<input type="hidden" name="redirect" value="leden_overzicht">
		</form>
	</body>
	
	<script src="2.1.4_jquery.min.js"></script>
</html>