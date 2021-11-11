package oblig4r;

import javax.servlet.http.HttpServletRequest;

public class RegSkjema {

	private String fornavn;
	private String fornavnFeilmelding;
	private String etternavn;
	private String etternavnFeilmelding;
	private String mobil;
	private String mobilFeilmelding;
	private String pass;
	private String passFeilmelding;
	private String Rpass;
	private String RpassFeilmelding;
	private String kjonn;
	private String kjonnFeilmelding;
	private Deltager deltager;

	public RegSkjema(HttpServletRequest request) {
		this.fornavn = request.getParameter("fornavn");
		this.etternavn = request.getParameter("etternavn");
		this.mobil = request.getParameter("mobil");
		this.pass = request.getParameter("passord");
		this.Rpass = request.getParameter("passordRepetert");
		this.kjonn = request.getParameter("kjonn");
		this.deltager = (Deltager) request.getSession().getAttribute("deltager");
	}

	public String getFornavn() {
		return fornavn;
	}

	public String getFornavnFeilmelding() {
		return fornavnFeilmelding;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public String getEtternavnFeilmelding() {
		return etternavnFeilmelding;
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

	public String getRpass() {
		return Rpass;
	}

	public String getRpassFeilmelding() {
		return RpassFeilmelding;
	}

	public String getKjonn() {
		return kjonn;
	}

	public String getKjonnFeilmelding() {
		return kjonnFeilmelding;
	}

	public boolean isAllLoginGyldig() {
		if (fornavn == null || etternavn == null || mobil == null || deltager != null || pass == null || Rpass == null
				|| kjonn == null) {
			return false;
		}
		return Validator.isGyldigFornavn(fornavn) && Validator.isGyldigEtternavn(etternavn)
				&& Validator.isGylidigMobilnr(mobil) && !Validator.nrFinnes(deltager)
				&& Validator.isGyldigPassVerdi(pass) && Validator.isGyldigPassVerdi(Rpass)
				&& Validator.isGyldigRpass(pass, Rpass) && kjonn != null;
	}
 
	public void settOppFeilmeldinger() {

		if (!Validator.isGyldigFornavn(fornavn)) {
			fornavn = "";
			fornavnFeilmelding = " Ugyldig fornavn, "
					+ " Gyldige verdier: 2-20 tegn, bindestrek, mellomrom. "
					+ "Første tegn skal være en stor bokstav";
		}
		if (!Validator.isGyldigEtternavn(etternavn)) {
			etternavn = "";
			etternavnFeilmelding = " Ugyldig etternavn, "
					+ " Gyldige verdier: 2-20 tegn, bindestrek. "
					+ "Første tegn skal være en stor bokstav";
		}

		if (!Validator.isGylidigMobilnr(mobil)) {
			mobil = "";
			mobilFeilmelding = " Manglende eller ugyldig mobil nummer, Et mobil skal være eksakt 8 siffer";
		} else if (Validator.nrFinnes(deltager)) {
			mobil = "";
			mobilFeilmelding = " En deltager med dette nummeret er allerede registrert";
		}
		if (!Validator.isGyldigPassVerdi(pass)) {
			pass = "";
			passFeilmelding = " Ugyldig passord, et passord må vere mellom 4 til 20 bokstaver/tall og må inneholde minst et tall";
		}
		if (!Validator.isGyldigPassVerdi(Rpass)) {
			Rpass = "";
			RpassFeilmelding = " Ugyldig verdi";
		} else if (!Validator.isGyldigRpass(pass, Rpass)) {
			Rpass = "";
			RpassFeilmelding = " Repitert passord er ulikt passord";
		}
		if (kjonn == null) {
			kjonnFeilmelding = " Du må oppgi kjonn";
		}
	}
}
