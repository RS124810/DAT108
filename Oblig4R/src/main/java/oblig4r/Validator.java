package oblig4r;

public class Validator {

	// kun tall og eksakt 8 siffer
	public static final String EIGHTDIGIT = "^[\\d]{8}$";
	// første bokstav stor, 2-20 tegn + mellomrom og bindestrek (-)
	public static final String FORNAVN = "^[A-ZÆØÅ]{1}[A-Za-zÆØÅæøå\\- ]{1,19}$";
	// første bokstav stor, 2-20 tegn + ikke mellomrom og bindestrek
	public static final String ETTERNAVN = "^[A-ZÆØÅ]{1}[A-Za-zÆØÅæøå\\-]{1,19}$";
	// passord må vere 4 til 20 bokstaver og inneholde minst et tall
	public static final String PASSORD = "^(?=.*\\d)(?=.*[A-Za-zÆØÅæøå]).{4,20}$";

	// passord validator
	public static boolean isGyldigPassVerdi(String s) {

		if (s == null) {
			return false;
		}
		return s.matches(PASSORD);
	}

	// mobilnr validator
	public static boolean isGylidigMobilnr(String s) {
		if (s == null) {
			return false;
		}
		return s.matches(EIGHTDIGIT);
	}

	// gyldig fornavn
	public static boolean isGyldigFornavn(String s) {
		if (s == null) {
			return false;
		}
		return s.matches(FORNAVN);
	}

	public static boolean isGyldigEtternavn(String s) {
		if (s == null) {
			return false;
		}
		return s.matches(ETTERNAVN);
	}

	public static boolean nrFinnes(Deltager d) {

		if (d == null) { // er null om det ikke nummeret finnes i DB
			return false;
		} else

			return (true);
	}

	public static boolean passFinnes(Deltager deltager, String p) {

		if (deltager == null) {
			return false;
		}

		return PassordUtil.validerMedSalt(p, deltager.getDeltagerPassord().getPwd_salt(),
				deltager.getDeltagerPassord().getPwd_hash());
	}

	public static boolean isGyldigRpass(String p, String r) {
		if (p == null || r == null) {
			return false;
		} else
			return p.matches(r);
	}

}
