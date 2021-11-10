package oblig4r;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class DeltagerDAO {
	
	@PersistenceContext (name = "deltagerPU")
	private EntityManager em;
	
	public synchronized List<Deltager> hentAlleBrukere() {
		return em.createQuery("SELECT b FROM Deltager b ORDER by b.fornavn", Deltager.class).getResultList();
	}
	public Deltager hentDeltager(String mobilNr) {
		return em.find(Deltager.class, mobilNr);
	}
	public synchronized void lagreNyDeltager(Deltager nydeltager) {
		em.persist(nydeltager);
	}

}
