package warcraft.decorators;

import warcraft.enums.ECompetence;
import warcraft.enums.ERace;
import warcraft.services.IVillageoisService;

public abstract class VillageoisDecorator implements IVillageoisService{

	private IVillageoisService delegate;

	public VillageoisDecorator(IVillageoisService delegate) {
		super();
		this.delegate = delegate;
	}

	public ERace race() {
		return delegate.race();
	}

	public int largeur() {
		return delegate.largeur();
	}

	public int hauteur() {
		return delegate.hauteur();
	}

	public int force() {
		return delegate.force();
	}

	public double vitesse() {
		return delegate.vitesse();
	}

	public int pointsDeVie() {
		return delegate.pointsDeVie();
	}

	public boolean estMort() {
		return delegate.estMort();
	}

	public int quantiteOr() {
		return delegate.quantiteOr();
	}

	public int compteurCorvee() {
		return delegate.compteurCorvee();
	}

	public boolean corveeFinie() {
		return delegate.corveeFinie();
	}

	public boolean enCorvee() {
		return delegate.enCorvee();
	}

	public void init(ERace race, int largeur, int hauteur, int force,
			double vitesse, int pointsDeVie) throws Exception {
		delegate.init(race, largeur, hauteur, force, vitesse, pointsDeVie);
	}

	public void retraitPV(int degats) throws Exception {
		delegate.retraitPV(degats);
	}

	public void ajouterOr(int somme) throws Exception {
		delegate.ajouterOr(somme);
	}

	public void retraitOr(int somme) throws Exception {
		delegate.retraitOr(somme);
	}

	public void commenceTravaille() throws Exception {
		delegate.commenceTravaille();
	}
	
	public void finirTravail() throws Exception {
		delegate.finirTravail();
	}

	public void travaille() throws Exception {
		delegate.travaille();
	}

	public void amelioration(ECompetence competence, int val) throws Exception {
		delegate.amelioration(competence, val);
	}

	


}
