/**
 * 
 */
package warcraft.contracts;

import warcraft.decorators.CentreNationalRechercheSpecialeDecorator;
import warcraft.services.ICentreNationalRechercheSpecialeService;
import static org.junit.Assert.assertTrue;

public class CentreNationalRechercheSpecialeContrat extends CentreNationalRechercheSpecialeDecorator{

	public CentreNationalRechercheSpecialeContrat(
			ICentreNationalRechercheSpecialeService delegate) {
		super(delegate);
	}

	public void checkInvariants(){
		// \inv :  0 < tempsCourant() && tempsCourant() <= tempsDeConstruction()
		assertTrue("\\inv :  0 < tempsCourant() && tempsCourant() <= tempsDeConstruction()",  0 < super.tempsCourant() && super.tempsCourant() <= super.tempsDeConstruction());
		// \inv : enConstruction() == (tempsCourant() > 0)
		assertTrue("\\inv : enConstruction() == (tempsCourant() > 0)", super.enConstruction() == (super.tempsCourant() > 0));
		// \inv : constructionFinie() == (tempsCourant() == tempsDeConstruction())
		assertTrue("\\inv : constructionFinie() == (tempsCourant() == tempsDeConstruction())", super.constructionFinie() == (super.tempsCourant() == super.tempsDeConstruction()));
		// \inv : 0 < rechercheCourante() && rechercheCourante() <= tempsDeRecherche()
		assertTrue("\\inv : 0 < rechercheCourante() && rechercheCourante() <= tempsDeRecherche()", 0 < super.rechercheCourante() && super.rechercheCourante() <= super.tempsDeRecherche());
		// \inv : enRecherche() == (rechercheCourante() > 0)
		assertTrue("\\inv : enRecherche() == (rechercheCourante() > 0)", super.enRecherche() == (super.rechercheCourante() > 0));
		// \inv : rechercheFinie() == (rechercheCourante() == tempsDeRecherche())
		assertTrue("\\inv : rechercheFinie() == (rechercheCourante() == tempsDeRecherche())", super.rechercheFinie() == (super.rechercheCourante() == super.tempsDeRecherche()));

	}

	@Override
	public int prixConstruction() {
		return super.prixConstruction();
	}

	@Override
	public int prixRecherche() {
		return super.prixRecherche();
	}

	@Override
	public int tempsDeConstruction() {
		return super.tempsDeConstruction();
	}

	@Override
	public int tempsDeRecherche() {
		return super.tempsDeRecherche();
	}

	@Override
	public int tempsCourant() {
		return super.tempsCourant();
	}

	@Override
	public boolean enConstruction() {
		return super.enConstruction();
	}

	@Override
	public boolean constructionFinie() {
		return super.constructionFinie();
	}

	@Override
	public int rechercheCourante() {
		return super.rechercheCourante();
	}

	@Override
	public boolean enRecherche() {
		return super.enRecherche();
	}

	@Override
	public boolean rechercheFinie() {
		return super.rechercheFinie();
	}
	@Override
	public ICentreNationalRechercheSpecialeService init(
			int tempsDeConstruction, int tempsDeRecherche,
			int prixConstruction, int prixRecherche) {

		// Pré-Conditions

		// \pre : tempsDeConstruction > 0
		assertTrue("\\pre : tempsDeConstruction > 0", tempsDeConstruction > 0);
		// \pre : tempsDeRecherche > 0
		assertTrue("\\pre : tempsDeRecherche > 0", tempsDeRecherche > 0);
		// \pre : prixConstruction > 0
		assertTrue("\\pre : prixConstruction > 0", prixConstruction > 0);
		// \pre : prixRecherche > 0
		assertTrue("\\pre : prixRecherche > 0", prixRecherche > 0);


		// Pas d'invariants avant initialisation

		// Appel
		super.init(tempsDeConstruction, tempsDeRecherche, prixConstruction, prixRecherche);

		// Invariants
		checkInvariants();

		// Post-Conditions

		// \post : tempsDeConstruction() == tempsDeConstruction
		assertTrue("\\post : tempsDeConstruction() == tempsDeConstruction", super.tempsDeConstruction() == tempsDeConstruction);
		// \post : tempsDeRecherche() == tempsDeRecherche
		assertTrue("\\post : tempsDeRecherche() == tempsDeRecherche",super.tempsDeRecherche() == tempsDeRecherche);
		// \post : prixConstruction() == prixConstruction
		assertTrue("\\post : prixConstruction() == prixConstruction", super.prixConstruction() == prixConstruction);
		// \post : prixRecherche() == prixRecherche
		assertTrue("\\post : prixRecherche() == prixRecherche", super.prixRecherche() == prixRecherche);
		// \post : tempsCourant() == 0
		assertTrue("\\post : tempsCourant() == 0", super.tempsCourant() == 0);
		// \post : rechercheCourante() == 0
		assertTrue("\\post : rechercheCourante() == 0", super.rechercheCourante() == 0);

		return this;
	}


	@Override
	public ICentreNationalRechercheSpecialeService commencerConstruction(
			int prix) {
		// Pré-Conditions
		
		// \pre : !enConstruction()
		assertTrue("\\pre : !enConstruction()", !super.enConstruction());
		// \pre : prix == prixConstruction()
		assertTrue("\\pre : prix == prixConstruction()", prix == super.prixConstruction());
		
		// Invariants
		checkInvariants();
		
		// Appel
		super.commencerConstruction(prix);
		
		// Invariants
		checkInvariants();
		
		// Post-Conditions
		// \post : tempsCourant() == 1
		assertTrue("\\post : tempsCourant() == 1", super.tempsCourant() == 1);
		
		return this;
	}
	/**
	 * \post : tempsCourant() == tempsCourant()@pre + 1
	 */
	@Override
	public ICentreNationalRechercheSpecialeService construire() {
		// Capture
		int oldTempsCourant = super.tempsCourant();
		
		// Pré-Conditions
		
		// \pre : !constructionFinie()
		assertTrue("\\pre : !constructionFinie()", !super.constructionFinie());
		
		// Invariants
		checkInvariants();
		// Appel
		super.construire();
		
		// Invariants
		checkInvariants();
		
		// Post-Conditions
		
		// \post : tempsCourant() == tempsCourant()@pre + 1
		assertTrue("\\post : tempsCourant() == tempsCourant()@pre + 1", super.tempsCourant() == oldTempsCourant + 1);
		
		return this;
	}

	
	@Override
	public ICentreNationalRechercheSpecialeService commencerRecherche(int prix) {
		// Pré-Conditions
		
		// \pre : constructionFinie()
		assertTrue("\\pre : constructionFinie()", super.constructionFinie());
		// \pre : prix == prixRecherche()
		assertTrue("\\pre : prix == prixRecherche()", prix == super.prixRecherche());
		
		// Invariants
		checkInvariants();
		
		// Appel
		super.commencerRecherche(prix);
		
		// Invariants
		checkInvariants();
		
		// Post-Conditions
		
		// \post : rechercheCourante() == 1
		assertTrue("\\post : rechercheCourante() == 1", super.rechercheCourante() == 1);
		
		return this;
	}


	@Override
	public ICentreNationalRechercheSpecialeService recherche() {
		// Capture
		int oldRechercheCourante = super.rechercheCourante();
		
		// Pré-Conditions
		
		// \pre : !rechercheFinie()
		assertTrue("\\pre : !rechercheFinie()", !super.rechercheFinie());
		
		// Invariants
		checkInvariants();
		
		// Appel
		super.recherche();
		
		// Invariants
		checkInvariants();
		
		// Post-Conditions
		// \post : rechercheCourante() == rechercheCourante()@pre + 1
		assertTrue("\\post : rechercheCourante() == rechercheCourante()@pre + 1", super.rechercheCourante() == oldRechercheCourante + 1);
		
		return this;
	}



}
