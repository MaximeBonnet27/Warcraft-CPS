package warcraft.contracts;
import static org.junit.Assert.assertTrue;
import warcraft.decorators.VillageoisDecorator;
import warcraft.enums.ERace;
import warcraft.services.IVillageoisService;

public class VillageoisContrat extends VillageoisDecorator{

	public VillageoisContrat(IVillageoisService delegate) {
		super(delegate);
	}
	public void checkInvariants(){
		// \inv : estMort() == (pointsDeVie() <= 0)
		assertTrue("\\inv : estMort() == (pointsDeVie() <= 0)", super.estMort() == (super.pointsDeVie() <= 0));
		// \inv : corveeFinie() == (compteurCorvee()==16)
		assertTrue("\\inv : corveeFini() == (compteurCorvee()==16)", super.corveeFinie() == (super.compteurCorvee()==16));
		// \inv : enCorvee() == (compteurCorvee()>0)
		assertTrue("\\inv : enCorvee() == (compteurCorvee()>0)", super.enCorvee() == (super.compteurCorvee()>0));
		// \inv : quantiteOr() >= 0
		assertTrue("\\inv : quantiteOr() >= 0",super.quantiteOr() >= 0);
		// \inv :  0 <= compteurCorvee() && compteurCorvee() <= 16
		assertTrue("\\inv : 0 <= compteurCorvee() && compteurCorvee() <= 16",  0 <= super.compteurCorvee() && super.compteurCorvee() <= 16);

	}

	@Override
	public ERace race() {
		return super.race();
	}

	@Override
	public int largeur() {
		return super.largeur();
	}

	@Override
	public int hauteur() {
		return super.hauteur();
	}

	@Override
	public int force() {
		return super.force();
	}

	@Override
	public double vitesse() {
		return super.vitesse();
	}

	@Override
	public int pointsDeVie() {
		return super.pointsDeVie();
	}

	@Override
	public boolean estMort() {
		return super.estMort();
	}

	@Override
	public int quantiteOr() {
		return super.quantiteOr();
	}

	@Override
	public int compteurCorvee() {
		return super.compteurCorvee();
	}

	@Override
	public boolean corveeFinie() {
		return super.corveeFinie();
	}

	@Override
	public boolean enCorvee() {
		return super.enCorvee();
	}

	@Override
	public IVillageoisService init(ERace race, int largeur, int hauteur, int force,
			double vitesse, int pointsDeVie) {
		// Pre-Conditions
		
		// \pre : largeur>0 
		assertTrue("\\pre : largeur > 0", largeur > 0);
		// \pre : hauteur>0 
		assertTrue("\\pre : hauteur>0 ", hauteur>0 );
		// \pre : force>0 
		assertTrue("\\pre : force>0 ", force>0 );
		// \pre : vitesse>0 
		assertTrue("\\pre : vitesse>0", vitesse>0);
		// \pre : pointsDeVie>0 
		assertTrue("\\pre : pointsDeVie>0 ", pointsDeVie>0 );
		
		// Pas d'invariants avant initialisation

		// Appel
		super.init(race, largeur, hauteur, force, vitesse, pointsDeVie);

		// Invariants
		checkInvariants();
		
		// Post-Conditions
		
		// \post : race() == race 
		assertTrue("\\post : race() == r", race() == race );
		// \post : largeur() == largeur
		assertTrue("\\post : largeur() == largeur", largeur() == largeur);
		// \post : hauteur() == hauteur
		assertTrue("\\post : hauteur() == hauteur", hauteur() == hauteur);
		// \post : force() == force
		assertTrue("\\post : force() == force", force() == force);
		// \post : vitesse() == vitesse
		assertTrue("\\post : vitesse() == vitesse", vitesse() == vitesse);
		// \post : pointsDeVie() == pointsDeVie
		assertTrue("\\post : pointsDevie() == pointsDeVie", pointsDeVie() == pointsDeVie);
		// \post : quantiteOr() == 0
		assertTrue("\\post : quantiteOr() == 0", quantiteOr() == 0);
		// \post : compteurCorvee() == 0
		assertTrue("\\post : compteurCorvee() == 0", compteurCorvee() == 0);
		return this;
	}

	@Override
	public IVillageoisService retraitPV(int degats) {
		// Capture
		int oldPointsDeVie = super.pointsDeVie();
		int oldQuantiteOr = super.quantiteOr();
		int oldCompteurCorvee = super.compteurCorvee();
		
		// Pré-Conditions
		 
		// \pre : !estMort() 
		assertTrue("\\pre : !estMort()", !super.estMort() );
		// \pre : degats>0
		assertTrue("degats>0", degats>0);
		
		// Invariants
		checkInvariants();

		// Appel
		super.retraitPV(degats);

		// Invariants
		checkInvariants();

		// Post-Conditions
		
		// \post : pointsDeVie() == pointsDeVie()@pre - degats
		assertTrue("\\post : pointsDeVie() == pointsDeVie()@pre - s", super.pointsDeVie() == oldPointsDeVie - degats);
		// \post : quantiteOr() == quantiteOr()@pre 
		assertTrue("\\post : quantiteOr() == quantiteOr()@pre", super.quantiteOr() == oldQuantiteOr);
		// \post : compteurCorvee() = compteurCorvee()@pre
		assertTrue("\\post : compteurCorvee() == compteurCorvee()@pre", super.compteurCorvee() == oldCompteurCorvee);
		return this;
	}

	@Override
	public IVillageoisService ajouterOr(int somme) {
		// Capture
		int oldPointsDeVie = super.pointsDeVie();
		int oldQuantiteOr = super.quantiteOr();
		int oldCompteurCorvee = super.compteurCorvee();

		// Pré-Conditions
		// \pre: somme >= 0
		assertTrue("\\pre: somme >= 0", somme >= 0);
		
		// Invariants
		checkInvariants();

		// Appel
		super.ajouterOr(somme);

		// Invariants
		checkInvariants();
		
		// Post-Conditions
		
		// \post : pointsDeVie() == pointsDeVie()@pre
		assertTrue("\\post : pointsDeVie() == pointsDeVie()@pre", super.pointsDeVie() == oldPointsDeVie);
		// \post : quantiteOr() == quantiteOr()@pre + somme 
		assertTrue("\\post : quantiteOr() == quantiteOr()@pre + somme", super.quantiteOr() == oldQuantiteOr + somme);
		// \post : compteurCorvee() == compteurCorvee()@pre
		assertTrue("\\post : compteurCorvee() == compteurCorvee()@pre", super.compteurCorvee() == oldCompteurCorvee);
		
		return this;
	}

	@Override
	public IVillageoisService retraitOr(int somme) {
		// Capture
		int oldPointsDeVie = super.pointsDeVie();
		int oldQuantiteOr = super.quantiteOr();
		int oldCompteurCorvee = super.compteurCorvee();
		
		// Pré-Conditions
		// \pre : quantiteOr()-somme >= 0
		assertTrue("\\pre : quantiteOr()-somme >= 0", super.quantiteOr() - somme >= 0);
		// \pre :  somme >= 0
		assertTrue("\\pre :  somme >= 0", somme >= 0);
		
		// Invariants
		checkInvariants();

		// Appel
		super.retraitOr(somme);

		// Invariants
		checkInvariants();

		// Post-Conditions
		// \post : pointsDeVie() == pointsDeVie()@pre
		assertTrue("\\post : pointsDeVie() == pointsDeVie()@pre", super.pointsDeVie() == oldPointsDeVie);
		// \post : quantiteOr() == quantiteOr()@pre - somme
		assertTrue("\\post : quantiteOr() == quantiteOr()@pre - somme", super.quantiteOr() == oldQuantiteOr - somme);
		// \post : compteurCorvee() == compteurCorvee()@pre
		assertTrue("\\post : compteurCorvee() == compteurCorvee()@pre", super.compteurCorvee() == oldCompteurCorvee);
		return this;
	}

	@Override
	public IVillageoisService commenceTravaille() {
		// Capture
		int oldPointsDeVie = super.pointsDeVie();
		int oldQuantiteOr = super.quantiteOr();

		// Invariants
		checkInvariants();

		// Appel
		super.commenceTravaille();

		// Invariants
		checkInvariants();

		// Post-Conditions

		// \post: pointsDeVie() == pointsDeVie()@pre
		assertTrue("\\post: pointsDeVie() == pointsDeVie()@pre", super.pointsDeVie() == oldPointsDeVie);
		// \post : quantiteOr() == quantiteOr()@pre
		assertTrue("\\post : quantiteOr() == quantiteOr()@pre", super.quantiteOr() == oldQuantiteOr);
		// \post : compteurCorvee() == 1
		assertTrue("\\post : compteurCorvee() == 1", super.compteurCorvee() == 1);
		
		return this;

	}

	@Override
	public IVillageoisService travaille() {
		// Capture
		int oldPointsDeVie = super.pointsDeVie();
		int oldQuantiteOr = super.quantiteOr();
		int oldCompteurCorvee = super.compteurCorvee();

		// Pré-Conditions
		
		// \pre : !corveeFinie()
		assertTrue("\\pre : !corveeFinie()", !corveeFinie());
		
		// Invariants
		checkInvariants();

		// Appel
		super.travaille();

		// Invariants
		checkInvariants();

		// Post-Conditions
		// \post : pointsDeVie() == pointsDeVie()@pre
		assertTrue("\\post : pointsDeVie() == pointsDeVie()@pre", super.pointsDeVie() == oldPointsDeVie);
		
		// \post : quantiteOr() == quantiteOr()@pre 
		assertTrue("\\post : quantiteOr() == quantiteOr()@pre", super.quantiteOr() == oldQuantiteOr);
		// \post : compteurCorvee() == compteurCorvee()@pre + 1
		assertTrue("\\post : compteurCorvee() == compteurCorvee()@pre + 1", super.compteurCorvee() == oldCompteurCorvee + 1);
		return this;
	}

}
