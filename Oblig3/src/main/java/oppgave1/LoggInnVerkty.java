package oppgave1;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class LoggInnVerkty {
	
	//Loginn
	public static void loggInn(HttpServletRequest request, String pass,String tid) {
		//logge ut forst er god vane
		loggUt(request);
		
		HttpSession sesjon = request.getSession(true);
		sesjon.setMaxInactiveInterval(Integer.parseInt(tid)); //logger ut etter 1 min
		sesjon.setAttribute("pass", sesjon);
	}
	public static boolean erInnlogget(HttpServletRequest request) {
		HttpSession sesjon = request.getSession(false);
		return sesjon != null && sesjon.getAttribute("pass") != null;
	}
	
	//Logge ut
	public static void loggUt(HttpServletRequest request) {
			HttpSession sesjon = request.getSession(false);
			if (sesjon != null) {
				sesjon.invalidate();
			}
		}

}
