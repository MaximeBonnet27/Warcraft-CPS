/**
 * 
 */
package warcraft.contracts;

import java.util.Random;

import warcraft.decorators.CentreNationalRechercheSpecialeDecorator;
import warcraft.enums.ECompetence;
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
	public ECompetence competenceAugmente() throws Exception{
		assertTrue("\\pre rechercheFinie()", rechercheFinie());
		return super.competenceAugmente();
	}

	@Override
	public int boost() throws Exception {
		assertTrue("\\pre rechercheFinie()", rechercheFinie());
		return super.boost();
	}


	@Override
	public void init(
			int tempsDeConstruction, int tempsDeRecherche,
			int prixConstruction, int prixRecherche) throws Exception {

		// Pré-Conditions

		// \pre : tempsDeConstruction > 0
		assertTrue("\\pre : tempsDeConstruction > 0", tempsDeConstruction > 0);
		// \pre : tempsDeRecherche > 0
		assertTrue("\\pre : tempsDeRecherche > 0", tempsDeRecherche > 0);
		// \pre : prixConstruction > 0
		assertTrue("\\pre : prixConstruction > 0", prixConstruction > 0);
		// \pre : prixRecherche > 0
		assertTrue("\\pre : prixRecherche > 0", prixRecherche > 0);

		super.init(tempsDeConstruction, tempsDeRecherche, prixConstruction, prixRecherche);

		checkInvariants();

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

	}



	@Override
	public void commencerConstruction() throws Exception {
		//Captures
		int oldRechercheCourante=super.rechercheCourante();

		// \pre : !enConstruction()
		assertTrue("\\pre : !enConstruction()", !super.enConstruction());

		checkInvariants();
		super.commencerConstruction();
		checkInvariants();

		// \post : tempsCourant() == 1
		assertTrue("\\post : tempsCourant() == 1", super.tempsCourant() == 1);

		// \post : rechercheCourante() == rechercheCourante()@pre
		assertTrue("\\post : rechercheCourante() == rechercheCourante()@pre", super.rechercheCourante()==oldRechercheCourante);
	}


	@Override
	public void construire() throws Exception {
		// Capture
		int oldTempsCourant = super.tempsCourant();
		int oldRechercheCourante=super.rechercheCourante();

		// \pre : enConstruction()
		assertTrue("\\pre : enConstruction()", super.enConstruction());
		// \pre : !constructionFinie()
		assertTrue("\\pre : !constructionFinie()", !super.constructionFinie());

		checkInvariants();
		super.construire();
		checkInvariants();

		// \post : tempsCourant() == tempsCourant()@pre + 1
		assertTrue("\\post : tempsCourant() == tempsCourant()@pre + 1", super.tempsCourant() == oldTempsCourant + 1);

		// \post : rechercheCourante() == rechercheCourante()@pre
		assertTrue("\\post : rechercheCourante() == rechercheCourante()@pre", super.rechercheCourante()==oldRechercheCourante);
	}


	@Override
	public void commencerRecherche() throws Exception {
		// Capture
		int oldTempsCourant = super.tempsCourant();

		// \pre : constructionFinie()
		assertTrue("\\pre : constructionFinie()", super.constructionFinie());

		// \pre : !enRecherche()
		assertTrue("\\pre : !enRecherche()", !super.enRecherche());

		checkInvariants();
		super.commencerRecherche();
		checkInvariants();

		// \post : tempsCourant() == tempsCourant()@pre
		assertTrue("\\post : tempsCourant() == tempsCourant()@pre", super.tempsCourant() == oldTempsCourant);
		// \post : rechercheCourante() == 1
		assertTrue("\\post : rechercheCourante() == 1", super.rechercheCourante() == 1);

	}


	@Override
	public void recherche() throws Exception {
		// Capture
		int oldRechercheCourante = super.rechercheCourante();
		int oldTempsCourant = super.tempsCourant();

		// \pre : enRecherche()
		assertTrue("\\pre : enRecherche()", !super.enRecherche());
		// \pre : !rechercheFinie()
		assertTrue("\\pre : !rechercheFinie()", !super.rechercheFinie());

		checkInvariants();
		super.recherche();
		checkInvariants();

		// \post : rechercheCourante() == rechercheCourante()@pre + 1
		assertTrue("\\post : rechercheCourante() == rechercheCourante()@pre + 1", super.rechercheCourante() == oldRechercheCourante + 1);
		// \post : tempsCourant() == tempsCourant()@pre
		assertTrue("\\post : tempsCourant() == tempsCourant()@pre", super.tempsCourant() == oldTempsCourant);
	}

	@Override
	public void finirRecherche() throws Exception {
		// Capture
		int oldTempsCourant = super.tempsCourant();

		// \pre : enRecherche()
		assertTrue("\\pre : enRecherche()", super.enRecherche());

		checkInvariants();
		super.finirRecherche();
		checkInvariants();

		// \post : rechercheCourante() == 0
		assertTrue("\\post : rechercheCourante() == 0", super.rechercheCourante()==0);

		// \post : tempsCourant() == tempsCourant()@pre
		assertTrue("\\post : tempsCourant() == tempsCourant()@pre ", super.tempsCourant() == oldTempsCourant);

	}




}
