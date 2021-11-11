package oblig4r;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DeltagerDAO deltagerDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/jsp/paameldingsskjema.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fornavn = request.getParameter("fornavn");
		String etternavn = request.getParameter("etternavn");
		String mobil = request.getParameter("mobil");
		String pass = request.getParameter("passord");
		String Rpass = request.getParameter("passordRepetert");
		String kjonn = request.getParameter("kjonn");

		Deltager deltager = deltagerDAO.hentDeltager(mobil);
		request.getSession().setAttribute("deltager", deltager);

		RegSkjema regskjema = new RegSkjema(request);

		if (regskjema.isAllLoginGyldig()) {

			Deltager d = new Deltager(fornavn, etternavn, mobil, kjonn, Passord.lagPassord(pass));
			deltagerDAO.lagreNyDeltager(d);

			// login
			LoginVerkty.loggInn(request, mobil);

			System.out.println(fornavn + " " + etternavn + " " + mobil + " " + pass + " " + Rpass + " " + kjonn);

			request.getRequestDispatcher("WEB-INF/jsp/paameldingsbekreftelse.jsp").forward(request, response);
			
		} else {
			
			regskjema.settOppFeilmeldinger();
			request.getSession().setAttribute("regskjema", regskjema);
			response.sendRedirect("RegServlet");
		}

	}
}
