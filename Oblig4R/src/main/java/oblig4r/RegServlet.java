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

		String feilmelding = "flet merket med stjerne * maa fylles ut";
		String feilfornavn = "*";
		String feiletternavn = "*";
		String feilmobil = "*";
		String feilpass = "*";
		String feilpassM = "*";
		String feilkjonn = "* velg kjonn";

		// mye tekst if/ if else, men bruker bør få korrekt respons 
		if (request.getParameter("invalidFornavn") != null) {
			feilmelding = "Fornavn skal være 2-20 bokstaver og kan inneholde alle norske bokstaver,"
					+ " bindestrek og mellomrom. Første tegn skal være en stor bokstav";
			feilfornavn = "Ugyldig fornavn";

		} else if (request.getParameter("invalidEtternavn") != null) {
			feilmelding = "Etternavn skal være 2-20 bokstaver og kan inneholde alle norske bokstaver,"
					+ " bindestrek. Mellomrom ikke tillatt og første tegn skal være en stor bokstav";
			feiletternavn = "Ugyldig etternavn";
		} else if (request.getParameter("invalidMobilNr") != null) {
			feilmelding = "Et mobil skal være eksakt 8 siffer";
			feilmobil = "Ugyldig mobil nummer eller en deltager med dette nummeret er allerede registrert";
		} else if (request.getParameter("invalidPass") != null) {
			feilmelding = "Et passord må vere mellom 4 til 20 bokstaver/tall og må inneholde minst et tall";
			feilpass = "Ugyldig passord";
		} else if (request.getParameter("invalidPassM") != null) {
			feilmelding = "Repitert passord er ulikt passord";
			feilpassM = "Repitert passord må vere det samme som oppgitt passord";	
		} else if (request.getParameter("invalidKjonn") != null) {
			feilmelding = "Venligst oppgi kjonn";
			feilkjonn = "Du må oppgi kjonn";
		}
		
		request.setAttribute("feilkjonn", feilkjonn);
		request.setAttribute("feilpassM", feilpassM);
		request.setAttribute("feilpass", feilpass);
		request.setAttribute("feilmobil", feilmobil);
		request.setAttribute("feiletternavn", feiletternavn);
		request.setAttribute("feilmelding", feilmelding);
		request.setAttribute("feilfornavn", feilfornavn);

		request.getRequestDispatcher("WEB-INF/jsp/paameldingsskjema.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fornavn = request.getParameter("fornavn");
		String etternavn = request.getParameter("etternavn");
		String mobilNr = request.getParameter("mobil");
		String pass = request.getParameter("passord");
		String Rpass = request.getParameter("passordRepetert");
		String kjonn = request.getParameter("kjonn");

		if (!Validator.isGyldigFornavn(fornavn)) {
			response.sendRedirect("RegServlet" + "?invalidFornavn");
		} else if (!Validator.isGyldigEtternavn(etternavn)) {
			response.sendRedirect("RegServlet" + "?invalidEtternavn");
		} else if (!Validator.isGylidigMobilnr(mobilNr) || Validator.nrFinnes(deltagerDAO.hentDeltager(mobilNr))) {
			response.sendRedirect("RegServlet" + "?invalidMobilNr");
		} else if (!Validator.isGyldigPassVerdi(pass)) {
			response.sendRedirect("RegServlet" + "?invalidPass");
		} else if(!Validator.isGyldigRpass(pass, Rpass) || !Validator.isGyldigPassVerdi(Rpass)) {
			response.sendRedirect("RegServlet" + "?invalidPassM");
		} else if(kjonn == null) {
			response.sendRedirect("RegServlet" + "?invalidKjonn");
		} else {
			
			Deltager d = new Deltager (fornavn, etternavn, mobilNr, kjonn, Passord.lagPassord(pass));
			deltagerDAO.lagreNyDeltager(d);

			System.out.println(fornavn + " " + etternavn + " " + mobilNr + " " + pass + " " + Rpass + " " + kjonn);
			
			request.setAttribute("fornavn", fornavn);
			request.setAttribute("etternavn", etternavn);
			request.setAttribute("mobil", mobilNr);
			request.setAttribute("kjonn", kjonn);
			
			//login
			LoginVerkty.loggInn(request, mobilNr);
						
			request.getRequestDispatcher("WEB-INF/jsp/paameldingsbekreftelse.jsp").forward(request, response);
			//response.sendRedirect("RegServlet");
		}
	}

}
