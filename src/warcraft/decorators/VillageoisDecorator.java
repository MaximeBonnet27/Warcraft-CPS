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

	public IVillageoisService init(ERace race, int largeur, int hauteur, int force,
			double vitesse, int pointsDeVie) {
		return delegate.init(race, largeur, hauteur, force, vitesse, pointsDeVie);
	}

	public IVillageoisService retraitPV(int degats) {
		return delegate.retraitPV(degats);
	}

	public IVillageoisService ajouterOr(int somme) {
		return delegate.ajouterOr(somme);
	}

	public IVillageoisService retraitOr(int somme) {
		return delegate.retraitOr(somme);
	}

	public IVillageoisService commenceTravaille() {
		return delegate.commenceTravaille();
	}

	public IVillageoisService travaille() {
		return delegate.travaille();
	}

	public IVillageoisService amelioration(ECompetence competence, int val) {
		return delegate.amelioration(competence, val);
	}



}
