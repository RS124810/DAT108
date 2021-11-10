package oblig4r;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "deltagere" ,schema = "oblig4r")

public class Deltager {

	@Id
	private String mobilNr;
	@Embedded
	private Passord passord;	
	
	private String fornavn;
	private String etternavn;
	private String kjonn;

	public Deltager(String fornavn, String etternavn, String mobilNr, String kjonn, Passord passord) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.mobilNr = mobilNr;
		this.kjonn = kjonn;
		this.passord = passord;
	}

	public Deltager() {

	}
	public String getMobilNr() {
		return String.valueOf(mobilNr).replaceFirst("(\\d{3})(\\d{2})(\\d+)", "$1 $2 $3");
	//	return mobilNr;
	}
	
	public String getFornavn() {
		return fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public String getKjonn() {
		return kjonn;
	}

	public Passord getDeltagerPassord() {
		return passord;
	}
	@Override
	public String toString() {
		return "Deltager [fornavn= " + fornavn + ", etternavn= " + etternavn +
				" mobilNR= "+mobilNr+", kjonn= "+ kjonn +", passord=" + passord + "]";
	}
}
