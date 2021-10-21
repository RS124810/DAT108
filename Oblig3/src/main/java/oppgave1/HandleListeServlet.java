package oppgave1;

import static oppgave1.UrlMap.HANDLELISTE_URL;
import static oppgave1.UrlMap.LOGIN_URL;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/" + HANDLELISTE_URL)
public class HandleListeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!LoggInnVerkty.erInnlogget(request)) {
			response.sendRedirect(LOGIN_URL);
		}

		response.setContentType("text/html; charset=ISO-8859-1");
		PrintWriter out = response.getWriter();

		Cookie[] cookies = request.getCookies();
		List<String> vareliste = Stream.of(cookies).filter(c -> c.getValue().equals("vareCK")).map(c -> c.getName())
				.collect(Collectors.toList());

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");

		out.println("<title>Handleliste</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action=\"" + HANDLELISTE_URL + "\" method=\"post\">");
		out.println("<fieldset>");
		out.println("<b>Min Handleliste</b>");
		out.println("<p><input type=\"submit\" value=\"Legg til\" /> <input type=\"text\" name=\"vare\"</p>");

		if (cookies.length > 0) {
			vareliste.forEach(v -> {
				try {
					out.println("<p><button type=\"submit\" value=\"" + v + "\" name=\"slettVaren\"> Slett </button>"
							+ URLDecoder.decode(v, "ISO-8859-1") + "</p>");
				} catch (UnsupportedEncodingException e) {

					e.printStackTrace();
				}
			});
		}
		out.println("</fieldset>");
		out.println("<from>");
		out.println("</body>");
		out.println("</html>");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String varen = request.getParameter("vare");
		String SlettVaren = request.getParameter("slettVaren");
		System.out.println(varen);

		if (!varen.isBlank() && LoggInnVerkty.erInnlogget(request)) {
			varen = URLEncoder.encode(varen, "ISO-8859-1"); // koder ugyldig input

			Cookie ck_vare = new Cookie(varen, "vareCK");
			response.addCookie(ck_vare);
		}

		if (SlettVaren != null) {

			System.out.println("Slettet Vare " + SlettVaren);

			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (SlettVaren.equals(cookie.getName())) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}

			}
		}

		response.sendRedirect(HANDLELISTE_URL);

	}

}
