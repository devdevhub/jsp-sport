<%@ page import="sport.vereniging.Lid"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>

		<%@include file="header.jsp"%> - Leden</title>
	</head>
	
	<body>
		<%@include file="nav.jsp"%>
		<%
			if (request.getAttribute("leden") == null && request.getAttribute("lid") == null && request.getAttribute("toevoegen") == null) {
				response.sendRedirect("sport?redirect=leden_overzicht");
			}
			else if (request.getAttribute("leden") != null) {
				if (request.getAttribute("change") != null) {
					response.sendRedirect("sport?redirect=leden_overzicht");
				}
				ArrayList<Lid> leden = (ArrayList<Lid>)(request.getAttribute("leden"));
				out.print("<h1>Leden | Overzicht</h1>");
				out.print(" + <a href='sport?redirect=lid_toevoegen'>Nieuw Lid</a><br><br>");
				out.print("<table id='leden-table'>");
					%>
						<tr>
							<th>Spelerscode</th>
							<th>Naam</th>
							<th>E-mail</th>
							<th>Geboortedatum</th>
						</tr>
					<%
					for (Lid lid : leden) {
						%>
						<tr>
							<td><a href="sport?redirect=lid_gegevens&spelerscode=<%=lid.getSpelerscode()%>"><%=lid.getSpelerscode()%></a></td>
							<td><%=lid.getNaam()%></td>
							<td><%=lid.getEmail()%></td>
							<td><%=new SimpleDateFormat("dd-MM-yyyy").format(lid.getGeb_datum())%></td>
						</tr>
						<%
						//out.print("<a href='sport?spelerscode="+lid.getSpelerscode()+"'>"+lid.getNaam()+"</a><br>");
					}
				out.print("</table>");
				out.print("<br>");
				/*if (request.getAttribute("gewijzigd") != null) {
					//out.print("De gegevens van "+((Lid)(request.getAttribute("gewijzigd"))).getNaam()+" zijn succesvol gewijzigd.");
					//request.setAttribute("change", "update");
					response.sendRedirect("sport?redirect=leden_overzicht");
				}
				if (request.getAttribute("verwijderd") != null) {
					//out.print("De gegevens van "+((Lid)(request.getAttribute("verwijderd"))).getNaam()+" zijn succesvol verwijderd.");
					//request.setAttribute("change", "delete");
					response.sendRedirect("sport?redirect=leden_overzicht");
				}*/
			}
			else if (request.getAttribute("lid") != null) {
				Lid lid = (Lid)(request.getAttribute("lid"));
				out.print("<h1>Leden | "+lid.getNaam()+"</h1>");
				%>
					<form action="sport?redirect=leden_overzicht" method="post">
						<table>
							<tr>
								<td>Spelerscode</td>
								<td><input type="text" name="spelerscode" value="<%=lid.getSpelerscode()%>"></td>
							</tr>
							<tr>
								<td>Roepnaam</td>
								<td><input type="text" name="roepnaam" value="<%=lid.getRoepnaam()%>"></td>
							</tr>
							<tr>
								<td>Tussenvoegsels</td>
								<td><input type="text" name="tussenvoegsels" value="<%=lid.getTussenvoegsels()%>"></td>
							</tr>
							<tr>
								<td>Achternaam</td>
								<td><input type="text" name="achternaam" value="<%=lid.getAchternaam()%>"></td>
							</tr>
							<tr>
								<td>E-mail</td>
								<td><input type="text" name="email" value="<%=lid.getEmail()%>"></td>
							</tr>
							<tr>
								<td>Geboortedatum (dd-mm-jjjj)</td>
								<td><input type="text" name="geb_datum" value="<%=new SimpleDateFormat("dd-MM-yyyy").format(lid.getGeb_datum())%>"></td>
							</tr>
							<tr>
								<td>
								</td>
								<td>
									<input type="submit" name="wijzig_lid_knop" value="wijzig">
								    <input type="submit" name="verwijder_lid_knop" value="verwijder">
							    </td>
							</tr>
						</table>
					</form>
				<%
			}
			else if (request.getAttribute("toevoegen").equals("lid")) {
				%>
					<h1>Lid Toevoegen</h1>
					<form action="sport?redirect=leden_overzicht" method="post">
						<input type="text" name="roepnaam" placeholder="Roepnaam">
						<input type="text" name="tussenvoegsels" placeholder="Tussenvoegsels">
						<input type="text" name="achternaam" placeholder="Achternaam">
						<input type="text" name="email" placeholder="E-mail">
						<input type="text" name="geb_datum" placeholder="Geboortedatum (dd-mm-jjjj)">
						<input type="submit" name="verzend_nieuw_lid_knop" value="verstuur">
					</form>
				<%
			}
		%>
	</body>
</html>