package oppgave1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/testhandleliste")
public class TestHandlelisteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String passord = "handle";

	private boolean ValidInput(String s) {
		return s != null && s.matches(passord);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String input = request.getParameter("pass");

		if (ValidInput(input)) {
			response.setContentType("text/html; charset=ISO-8859-1");

			PrintWriter out = response.getWriter();

			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"ISO-8859-1\">");
			out.println("<title>Innlogget</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<b>Min Handleliste</b>");
			out.println("<p><input type=\"submit\" value=\"Legg til\" /> <input type=\"text\" name=\"vare\"</p>");
			out.println("</body>");
			out.println("</html>");
		}else {
			response.setContentType("text/html; charset=ISO-8859-1");
			
			PrintWriter out = response.getWriter();

			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"ISO-8859-1\">");
			out.println("<title>login</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form action=\"handleliste\" method=\"post\">");
			out.println(" <fieldset>");
			out.println(" <legend>Ugylidg passord, vennligst prøv igjen: </legend>");
			out.println("<p><input type=\"password\" name=\"pass\" /></p>");
			out.println("<p><input type=\"submit\" value=\"Logg inn\" /></p>");
			out.println("</fieldset>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
			
		}

	}

}
