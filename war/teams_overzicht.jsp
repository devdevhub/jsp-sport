<%@ page import="sport.vereniging.Team"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>

		<%@include file="header.jsp"%> - Teams</title>
	</head>
	
	<body>
		<%@include file="nav.jsp"%>
		<%
			if (request.getAttribute("teams") == null && request.getAttribute("team") == null && request.getAttribute("toevoegen") == null) {
				response.sendRedirect("sport?redirect=teams_overzicht");
			}
			else if (request.getAttribute("teams") != null) {
				if (request.getAttribute("change") != null) {
					response.sendRedirect("sport?redirect=teams_overzicht");
				}
				ArrayList<Team> teams = (ArrayList<Team>)(request.getAttribute("teams"));
				out.print("<h1>Teams | Overzicht</h1>");
				out.print(" + <a href='sport?redirect=team_toevoegen'>Nieuw Team</a><br><br>");
				out.print("<table id='leden-table'>");
					%>
						<tr>
							<th>Teamcode</th>
							<th>Teamnaam</th>
						</tr>
					<%
					for (Team team : teams) {
						%>
						<tr>
							<td><a href="sport?redirect=team_gegevens&teamcode=<%=team.getTeamcode()%>"><%=team.getTeamcode()%></a></td>
							<td><%=team.getTeamnaam()%></td>
						</tr>
						<%
					}
				out.print("</table>");
				out.print("<br>");
			}
			else if (request.getAttribute("team") != null) {
				Team team = (Team)(request.getAttribute("team"));
				out.print("<h1>Teams | "+team.getTeamnaam()+"</h1>");
				%>
					<form action="sport?redirect=teams_overzicht" method="post">
						<table>
							<tr>
								<td>Teamcode</td>
								<td><input type="text" name="teamcode" value="<%=team.getTeamcode()%>"></td>
							</tr>
							<tr>
								<td>Teamnaam</td>
								<td><input type="text" name="teamnaam" value="<%=team.getTeamnaam()%>"></td>
							</tr>
							<tr>
								<td>
								</td>
								<td>
									<input type="submit" name="wijzig_team_knop" value="wijzig">
								    <input type="submit" name="verwijder_team_knop" value="verwijder">
							    </td>
							</tr>
						</table>
					</form>
				<%
			}
			else if (request.getAttribute("toevoegen").equals("team")) {
				%>
					<h1>Team Toevoegen</h1>
					<form action="sport?redirect=teams_overzicht" method="post">
						<input type="text" name="teamcode" placeholder="Teamcode">
						<input type="text" name="teamnaam" placeholder="Teamnaam">
						<input type="submit" name="verzend_nieuw_team_knop" value="verstuur">
					</form>
				<%
			}
		%>
	</body>
</html>