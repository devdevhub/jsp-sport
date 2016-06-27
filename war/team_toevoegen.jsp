		<%@include file="header.jsp"%> - Lid Toevoegen</title>
	</head>
	
	<body>
		<%@include file="nav.jsp"%>
		<h1>Team Toevoegen</h1>
		<form action="sport" method="post">
			<input type="text" name="teamnaam" placeholder="Teamnaam">
			<input type="text" name="teamcode" placeholder="Teamcode">
			<input type="submit" name="verzend_nieuw_team_knop" value="verstuur">
			<input type="hidden" name="redirect" value="teams_overzicht">
		</form>
	</body>
	
	<script src="2.1.4_jquery.min.js"></script>
</html>