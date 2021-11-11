package oblig4r;

//import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

public class LoginSkjema {

	private String mobil;
	private String mobilFeilmelding;
	private String pass;
	private String passFeilmelding;
	private Deltager d;

//	@EJB
//	public DeltagerDAO deltagerDAO;

	public LoginSkjema(HttpServletRequest request) {
		this.mobil = request.getParameter("mobil");
		this.pass = request.getParameter("passord");
		this.d = (Deltager) request.getSession().getAttribute("d");
		
	}

	public String getMobil() {
		return mobil;
	}

	public String getMobilFeilmelding() {
		return mobilFeilmelding;
	}

	public String getPass() {
		return pass;
	}

	public String getPassFeilmelding() {
		return passFeilmelding;
	}

	public boolean isAllLoginGyldig() {

		if (mobil == null || pass == null) {
			return false;
		}
		return Validator.isGylidigMobilnr(mobil) && Validator.nrFinnes(d) && Validator.isGyldigPassVerdi(pass)
				&& Validator.passFinnes(d, pass);
	}

	public void settOppFeilmeldinger() {

		if (!Validator.isGylidigMobilnr(mobil)) {
			mobil = "";
			
			mobilFeilmelding = " Manglende eller ugyldig mobil nummer, prøv igjen";
 
		}
		else if (!Validator.nrFinnes(d)) {
			mobil = "";
			
			mobilFeilmelding = " Kunne ikke finne en deltager på dette mobil nummeret, prøv igjen";
		}
		if (!Validator.isGyldigPassVerdi(pass)) {
			pass = "";
			passFeilmelding = " Manglende eller ugyldig passord, prøv igjen";
		}
		else if (!Validator.passFinnes(d, pass) && Validator.nrFinnes(d)) {
			pass = "";
			passFeilmelding = " Feil passord, prøv igjen";
		}
	}

}
