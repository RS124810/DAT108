package oppgave1;

public class Validator {

	public static final String ANY_LETTER = "[A-Za-z]";
	
	public static boolean erGyldiginput(String s){
		return s.matches("^" 
    			+ ANY_LETTER     			
    			+ "$");
	}
}
