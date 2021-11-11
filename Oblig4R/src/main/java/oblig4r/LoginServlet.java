package oblig4r;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DeltagerDAO deltagerDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String feilmelding = "";

//      Bruker timedout
		if (request.getParameter("time") !=null) {
			feilmelding = "Tidsavbrudd, venligst login paa nytt";
		}
		request.setAttribute("feilmelding", feilmelding);
		
		request.getRequestDispatcher("WEB-INF/jsp/logginn.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String mobil = request.getParameter("mobil");		
		Deltager d = deltagerDAO.hentDeltager(mobil);
		request.getSession().setAttribute("d", d);
		
		LoginSkjema loginskjema = new LoginSkjema(request);
		
		if (loginskjema.isAllLoginGyldig()){
			request.getSession().removeAttribute("loginskjema");
			
			LoginVerkty.loggInn(request, mobil);
			//henter data til jsp
			List<Deltager> deltagere = deltagerDAO.hentAlleBrukere();
			request.setAttribute("deltagere", deltagere);
		
			
			request.getRequestDispatcher("WEB-INF/jsp/deltagerliste.jsp")
			.forward(request, response);
			
		}else {
			loginskjema.settOppFeilmeldinger();
		
			request.getSession().setAttribute("loginskjema", loginskjema);
			response.sendRedirect("LoginServlet");
		}		
	}

}
