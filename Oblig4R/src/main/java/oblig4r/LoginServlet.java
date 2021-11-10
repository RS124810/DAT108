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

		if(request.getParameter("invalidNr") != null) {
			feilmelding = "Manglende eller ugyldig mobil nummer, prøv igjen";
		}else if (request.getParameter("invalidPass") !=null) {
			feilmelding = "Manglende eller ugyldig passord, prøv igjen";
		}else if (request.getParameter("finnesikke") !=null) {
			feilmelding = "Kunne ikke finne en deltager på dette mobil nummeret, prøv igjen";
		}else if (request.getParameter("wrongPass") !=null) {
			feilmelding = "Feil passord, prøv igjen";
		}else if (request.getParameter("time") !=null) {
			feilmelding = "Tidsavbrudd, venligst login paa nytt";
		}
		request.setAttribute("feilmelding", feilmelding);

		// request.setAttribute("delt", lista);
		request.getRequestDispatcher("WEB-INF/jsp/logginn.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mobil = request.getParameter("mobil");
		String pass = request.getParameter("passord");

		if (!Validator.isGylidigMobilnr(mobil)) {
			response.sendRedirect("LoginServlet"+"?invalidNr");
			
		} else if (!Validator.isGyldigPassVerdi(pass)) {
			response.sendRedirect("LoginServlet"+"?invalidPass");
			
		} else if (!Validator.nrFinnes(deltagerDAO.hentDeltager(mobil))) {
			response.sendRedirect("LoginServlet"+"?finnesikke");
		
		} else if (!Validator.passFinnes(deltagerDAO.hentDeltager(mobil), pass)) {
			response.sendRedirect("LoginServlet"+"?wrongPass");
		} else if (Validator.passFinnes(deltagerDAO.hentDeltager(mobil), pass)) {
			
			LoginVerkty.loggInn(request, mobil);
			//henter data til jsp
			List<Deltager> deltagere = deltagerDAO.hentAlleBrukere();
			request.setAttribute("deltagere", deltagere);
			//response.sendRedirect("DeltagerServlet");
			
			request.getRequestDispatcher("WEB-INF/jsp/deltagerliste.jsp")
			.forward(request, response);
						
			System.out.println("alt ok");
		} else {

			response.sendRedirect("LoginServlet");
		}
		// doGet(request, response);
	}

}
