package warcraft.components.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;

import warcraft.services.IVillageoisService;

public abstract class AbstractVillageoisTests extends AssertionTests {
	protected IVillageoisService villageois;

	@Before
	public abstract void before();

	@After
	public void after() {
		villageois = null;
	}
	
	public void checkInvariants(String obj){
		// \inv : estMort() == (pointsDeVie() <= 0)
		assertion(obj+": \\inv : estMort() == (pointsDeVie() <= 0)", villageois.estMort() == (villageois.pointsDeVie() <= 0));
		// \inv : corveeFinie() == (compteurCorvee()==16)
		assertion(obj+": \\inv : corveeFini() == (compteurCorvee()==16)", villageois.corveeFinie() == (villageois.compteurCorvee()==16));
		// \inv : enCorvee() == (compteurCorvee()>0)
		assertion(obj+": \\inv : enCorvee() == (compteurCorvee()>0)", villageois.enCorvee() == (villageois.compteurCorvee()>0));
		// \inv : quantiteOr() >= 0
		assertion(obj+": \\inv : quantiteOr() >= 0",villageois.quantiteOr() >= 0);
		// \inv :  0 <= compteurCorvee() && compteurCorvee() <= 16
		assertion(obj+": \\inv : 0 <= compteurCorvee() && compteurCorvee() <= 16",  0 <= villageois.compteurCorvee() && villageois.compteurCorvee() <= 16);
		// \inv : force() > 0
		assertion(obj+": force() > 0", villageois.force() > 0);
		// \inv : vitesse() > 0
		assertion(obj+": vitesse() > 0", villageois.vitesse() > 0);
	}
}
