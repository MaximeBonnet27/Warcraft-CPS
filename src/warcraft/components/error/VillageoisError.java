package warcraft.components.error;

import warcraft.enums.ECompetence;
import warcraft.enums.ERace;
import warcraft.services.IVillageoisService;

public class VillageoisError implements IVillageoisService{

	private ERace race;
	private int largeur, hauteur;
	private int force;
	private double vitesse;
	private int pointsDeVie;
	private int quantiteOr;
	private int compteurCorvee;

	
	public VillageoisError() {
	}

	@Override
	public ERace race() {
		return race;
	}

	@Override
	public int largeur() {
		return largeur;
	}

	@Override
	public int hauteur() {
		return hauteur;
	}

	@Override
	public int force() {
		return force;
	}

	@Override
	public double vitesse() {
		return vitesse;
	}

	@Override
	public int pointsDeVie() {
		return pointsDeVie;
	}

	@Override
	public boolean estMort() {
		return pointsDeVie <= 0;
	}

	@Override
	public int quantiteOr() {
		return quantiteOr;
	}

	@Override
	public int compteurCorvee() {
		return compteurCorvee;
	}

	@Override
	public boolean corveeFinie() {
		return compteurCorvee == 16;
	}

	@Override
	public boolean enCorvee() {
		return compteurCorvee > 0;
	}

	@Override
	public void init(ERace race, int largeur, int hauteur, int force,
			double vitesse, int pointsDeVie) throws Exception {

		//errors
		/*if(!(largeur > 0))
			throw new Exception("\\pre : largeur > 0");
		if(!(hauteur>0))
			throw new Exception("\\pre : hauteur>0 ");
		if(!(force>0))
			throw new Exception("\\pre : force>0 ");
		if(!(vitesse>0))
			throw new Exception("\\pre : vitesse>0");
		if(!(pointsDeVie>0))
			throw new Exception("\\pre : pointsDeVie>0 ");*/
		this.race = race;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.force = force;
		this.vitesse = 1;//error
		this.pointsDeVie = pointsDeVie;
	}

	@Override
	public void retraitPV(int degats) throws Exception {
		if(estMort())
			throw new Exception("\\pre : !estMort()");

		//error
		//if(!(degats>0))
		//	throw new Exception("\\pre: degats>0");

		pointsDeVie -= degats;
	}

	@Override
	public void ajouterOr(int somme) throws Exception {
		//error
		//if(estMort())
		//	throw new Exception("\\pre : !estMort()");
		if(!(somme>=0))
			throw new Exception("\\pre: somme>=0");
		quantiteOr += somme;
	}

	@Override
	public void retraitOr(int somme) throws Exception {
		if(estMort())
			throw new Exception("\\pre : !estMort()");
		if(!(somme>=0))
			throw new Exception("\\pre: somme>=0");
		//error
		//if(!(quantiteOr-somme>=0))
		//	throw new Exception("\\pre : quantiteOr()-somme >= 0");

		quantiteOr -= somme;
	}

	@Override
	public void commenceTravaille() throws Exception {
		if(estMort())
			throw new Exception("\\pre : !estMort()");
		compteurCorvee = 0;//error
	}

	@Override
	public void finirTravail() throws Exception {
		if(estMort())
			throw new Exception("\\pre : !estMort()");

		//error
		//if(!enCorvee())
		//	throw new Exception("\\pre : enCorvee()");
		compteurCorvee=0;
	}
	
	@Override
	public void travaille() throws Exception {
		//error
		/*if(estMort())
			throw new Exception("\\pre : !estMort()");

		if(corveeFinie())
			throw new Exception("\\pre : !corveeFinie()");
			*/
		compteurCorvee += 1;
	}

	@Override
	public void amelioration(ECompetence competence, int val) throws Exception {
		if(estMort())
			throw new Exception("\\pre : !estMort()");

		//error
		/*if(!(val>0))
			throw new Exception("\\pre : val>0");
			*/

		//error
		switch (competence) {
		case  PV: force += val; break;
		case VITESSE : pointsDeVie += val; break;
		case FORCE : vitesse += val; break;
		default:
			break;
		}
	}



}
