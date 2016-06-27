package control;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sport.vereniging.Lid;
import sport.vereniging.Team;
import sportIO.SportIO;

@SuppressWarnings("serial")
public class SportServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SportIO io = new SportIO();
		if (req.getParameter("redirect") != null) {
			if (req.getParameter("redirect").equals("lid_gegevens")) {
				req.setAttribute("lid", io.getLid(req.getParameter("spelerscode")));
				req.getRequestDispatcher("leden_overzicht.jsp").forward(req, resp);
			}
			else if (req.getParameter("redirect").equals("team_gegevens")) {
				req.setAttribute("team", io.getTeam(req.getParameter("teamcode")));
				req.getRequestDispatcher("teams_overzicht.jsp").forward(req, resp);
			}
			if (req.getParameter("redirect").equals("lid_toevoegen")) {
				req.setAttribute("toevoegen", "lid");
				req.getRequestDispatcher("leden_overzicht.jsp").forward(req, resp);
			}
			else if (req.getParameter("redirect").equals("team_toevoegen")) {
				req.setAttribute("toevoegen", "team");
				req.getRequestDispatcher("teams_overzicht.jsp").forward(req, resp);
			}
			else if (req.getParameter("redirect").equals("leden_overzicht")) {
				req.setAttribute("leden", io.getAlleLeden());
				updateLid(io, req, resp);
				req.getRequestDispatcher("leden_overzicht.jsp").forward(req, resp);
			}
			else if (req.getParameter("redirect").equals("teams_overzicht")) {
				req.setAttribute("teams", io.getAlleTeams());
				updateTeam(io, req, resp);
				req.getRequestDispatcher("teams_overzicht.jsp").forward(req, resp);
			}
		}
		else {
			req.getRequestDispatcher("leden_overzicht.jsp").forward(req, resp);
		}
	}
	
	private void addLid(SportIO io, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			io.updateLid(new Lid(
				req.getParameter("roepnaam"),
				req.getParameter("tussenvoegsels"),
				req.getParameter("achternaam"),
				req.getParameter("email"),
				new SimpleDateFormat("dd-MM-yyyy").parse(req.getParameter("geb_datum"))
			));
		}
		catch (ParseException e) {
			io.updateLid(new Lid(
				req.getParameter("roepnaam"),
				req.getParameter("tussenvoegsels"),
				req.getParameter("achternaam"),
				req.getParameter("email"),
				new Date()
			));
		}
	}
	
	private void addTeam(SportIO io, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		io.updateTeam(new Team(
			req.getParameter("teamcode"),
			req.getParameter("teamnaam")
		));
	}
	
	private void updateLid(SportIO io, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("verzend_nieuw_lid_knop") != null) {
			req.setAttribute("change", "nieuw");
			addLid(io, req, resp);
		}
		else if (req.getParameter("wijzig_lid_knop") != null) {
			req.setAttribute("change", "gewijzigd");
			Lid lid = new Lid();
			lid.setSpelerscode(req.getParameter("spelerscode"));
			lid.setRoepnaam(req.getParameter("roepnaam"));
			lid.setTussenvoegsels(req.getParameter("tussenvoegsels"));
			lid.setAchternaam(req.getParameter("achternaam"));
			lid.setEmail(req.getParameter("email"));
			try {
				lid.setGeb_datum(new SimpleDateFormat("dd-MM-yyyy").parse(req.getParameter("geb_datum")));
			}
			catch (ParseException e) {}
			io.updateLid(lid);
//			req.setAttribute("leden", io.getAlleLeden());
		}
		else if (req.getParameter("verwijder_lid_knop") != null) {
			req.setAttribute("change", "verwijderd");
			io.verwijderLid(req.getParameter("spelerscode"));
		}
	}
	
	private void updateTeam(SportIO io, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("verzend_nieuw_team_knop") != null) {
			req.setAttribute("change", "nieuw");
			addTeam(io, req, resp);
		}
		else if (req.getParameter("wijzig_team_knop") != null) {
			req.setAttribute("change", "gewijzigd");
			addTeam(io, req, resp);
//			req.setAttribute("teams", io.getAlleTeams());
//			resp.sendRedirect("teams_overzicht.jsp");
		}
		else if (req.getParameter("verwijder_team_knop") != null) {
			req.setAttribute("change", "verwijderd");
			io.verwijderTeam(req.getParameter("teamcode"));
		}
	}

}
