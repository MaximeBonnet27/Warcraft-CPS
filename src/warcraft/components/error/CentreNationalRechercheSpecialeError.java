/**
 * 
 */
package warcraft.components.error;

import java.util.Random;

import warcraft.enums.ECompetence;
import warcraft.services.ICentreNationalRechercheSpecialeService;

/**
 * @author 3100381
 *
 */
public class CentreNationalRechercheSpecialeError implements ICentreNationalRechercheSpecialeService{

	private int prixConstruction;
	private int prixRecherche;
	private int tempsDeConstruction;
	private int tempsDeRecherche;
	private int tempsCourant;
	private int rechercheCourante;
	
	@Override
	public int prixConstruction() {
		return prixConstruction;
	}

	@Override
	public int prixRecherche() {
		return prixRecherche;
	}

	@Override
	public int tempsDeConstruction() {
		return tempsDeConstruction;
	}

	@Override
	public int tempsDeRecherche() {
		return tempsDeRecherche;
	}

	@Override
	public int tempsCourant() {
		return tempsCourant;
	}

	@Override
	public boolean enConstruction() {
		//error
		return tempsCourant == 0;
	}

	@Override
	public boolean constructionFinie() {
		return tempsCourant == tempsDeConstruction;
	}

	@Override
	public int rechercheCourante() {
		return rechercheCourante;
	}

	@Override
	public boolean enRecherche() {
		return rechercheCourante > 0;
	}

	@Override
	public boolean rechercheFinie() {
		return rechercheCourante == tempsDeRecherche;
	}
	
	@Override
	public ECompetence competenceAugmente() throws Exception{
		//error
		//if(!(rechercheFinie()))
		//	throw new Exception("\\pre rechercheFinie()");
		Random rand=new Random();
		int i=rand.nextInt(ECompetence.values().length);
		return ECompetence.values()[i];
	}

	@Override
	public int boost() throws Exception {
		if(!(rechercheFinie()))
			throw new Exception("\\pre rechercheFinie()");
		Random rand=new Random();
		return rand.nextInt(10);
	}
	
	@Override
	public void init(int tempsDeConstruction, int tempsDeRecherche,int prixConstruction, int prixRecherche) throws Exception {
			if(!(tempsDeConstruction>0))
				throw new Exception("\\pre : tempsDeConstruction > 0");
			
			//error
			//if(!(tempsDeRecherche>0))
			//	throw new Exception("\\pre : tempsDeRecherche > 0");
			
			if(!(prixConstruction>0))
				throw new Exception("\\pre : prixConstruction > 0");
			
			if(!(prixRecherche > 0))
				throw new Exception("\\pre : prixRecherche > 0");
			
			this.tempsDeConstruction = tempsDeConstruction;
			this.tempsDeRecherche = tempsDeRecherche;
			this.prixConstruction = prixConstruction;
			this.prixRecherche = prixRecherche;
			this.tempsCourant = 10; 		//error
			this.rechercheCourante = 0;
	}

	@Override
	public void commencerConstruction() throws Exception {
		if(enConstruction())
			throw new Exception("\\pre : !enConstruction()");
		
		tempsCourant = 0;		//error
	}

	@Override
	public void construire() throws Exception {
		if(constructionFinie())
			throw new Exception("\\pre : !constructionFinie()");
		tempsCourant += 1;
	}

	@Override
	public void commencerRecherche() throws Exception {
		if(!constructionFinie())
			throw new Exception("\\pre : constructionFinie()");
		if(enRecherche())
			throw new Exception("\\pre : !enRecherche()");
		//rechercheCourante = 1;		//error
	}

	@Override
	public void recherche() throws Exception {
		//error
		//if(rechercheFinie())
		//	throw new Exception("\\pre : !rechercheFinie()");
		rechercheCourante += 1;
	}

	@Override
	public void finirRecherche() throws Exception {
		if(!enRecherche())
			throw new Exception("\\pre : enRecherche()");
		rechercheCourante=0;
		
	}
	
	

}
