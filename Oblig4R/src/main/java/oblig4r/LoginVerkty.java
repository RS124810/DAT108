package oblig4r;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginVerkty {
	
	//Logge ut
		public static void loggUt(HttpServletRequest request) {
				HttpSession sesjon = request.getSession(false);
				if (sesjon != null) {
					sesjon.invalidate();
				}
			}
		
		public static void loggInn(HttpServletRequest request, String mobilnr) {
			//logge ut forst er god vane
			loggUt(request);
			String formatmobilnr = String.valueOf(mobilnr).replaceFirst("(\\d{3})(\\d{2})(\\d+)", "$1 $2 $3");
			HttpSession sesjon = request.getSession(true);
			sesjon.setMaxInactiveInterval(60); //logger ut etter 1 min
			sesjon.setAttribute("mobilnr", formatmobilnr);			
			
		}
		
		public static boolean erInnlogget(HttpServletRequest request) {
			HttpSession sesjon = request.getSession(false);
			return sesjon != null && sesjon.getAttribute("mobilnr") != null;
		}

}
