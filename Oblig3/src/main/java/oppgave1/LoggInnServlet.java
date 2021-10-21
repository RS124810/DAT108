package oppgave1;

import static oppgave1.UrlMap.LOGIN_URL;
import static oppgave1.UrlMap.HANDLELISTE_URL;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "loginServlet", urlPatterns = "/loginn")
//@WebServlet("/" + LOGIN_URL)
public class LoggInnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String Tpassord;
	String Time;
	
	@Override
	public void init() throws ServletException {
		//henter passordet ("handle") og logginn tidsgrensen på 60 sekund fra XML
		Tpassord = getServletConfig().getInitParameter("passord");
		Time = getServletContext().getInitParameter("logut");		
	}
		
	private boolean ValidInput(String s) {
		return s != null && s.matches(Tpassord);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String feilmelding = "";
		String feilkode = request.getParameter("feilkode");
		if (feilkode != null && feilkode.equals("feilpassord")) {
			feilmelding = "<p style=\"color:Tomato\">Ugyldig passord. Prøv igjen.";
		} else if (feilkode != null && feilkode.equals("ingeninput")) {
			feilmelding = "<p style=\"color:Tomato\">Passordfeltet må fylles ut. Prøv igjen.";
		}

		response.setContentType("text/html; charset=ISO-8859-1");

		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>login</title>");
		out.println("</head>");
		out.println("<body>");
		out.println(feilmelding);
		out.println("<form action=\"" + LOGIN_URL + "\"method=\"post\">");
		out.println(" <fieldset>");
		out.println(" <legend>Tast inn passord:  </legend>");
		out.println("<p><input type=\"password\" name=\"pass\" /></p>");
		out.println("<p><input type=\"submit\" value=\"Logg inn\" /></p>");
		out.println("</fieldset>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String input = request.getParameter("pass");
	
		if (ValidInput(input)) {
			LoggInnVerkty.loggInn(request, input, Time);
			response.sendRedirect(HANDLELISTE_URL);

		} else if (input.isBlank()) {
			response.sendRedirect(LOGIN_URL + "?feilkode=ingeninput");
		} else {
			response.sendRedirect(LOGIN_URL + "?feilkode=feilpassord");
		}
	}

}
