package warcraft.components.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;

import warcraft.services.ICentreNationalRechercheSpecialeService;

public abstract class AbstractCentreNationalRechercheSpecialeTests extends AssertionTests {

	protected ICentreNationalRechercheSpecialeService cnrs;

	@Before
	public abstract void before();

	@After
	public void after() {
		cnrs = null;
	}
	
	public void checkInvariants(String obj){
		// \inv :  0 < tempsCourant() && tempsCourant() <= tempsDeConstruction()
		assertion(obj+"\\inv :  0 < tempsCourant() && tempsCourant() <= tempsDeConstruction()",  0 < cnrs.tempsCourant() && cnrs.tempsCourant() <= cnrs.tempsDeConstruction());
		// \inv : enConstruction() == (tempsCourant() > 0)
		assertion(obj+"\\inv : enConstruction() == (tempsCourant() > 0)", cnrs.enConstruction() == (cnrs.tempsCourant() > 0));
		// \inv : constructionFinie() == (tempsCourant() == tempsDeConstruction())
		assertion(obj+"\\inv : constructionFinie() == (tempsCourant() == tempsDeConstruction())", cnrs.constructionFinie() == (cnrs.tempsCourant() == cnrs.tempsDeConstruction()));
		// \inv : 0 < rechercheCourante() && rechercheCourante() <= tempsDeRecherche()
		assertion(obj+"\\inv : 0 < rechercheCourante() && rechercheCourante() <= tempsDeRecherche()", 0 < cnrs.rechercheCourante() && cnrs.rechercheCourante() <= cnrs.tempsDeRecherche());
		// \inv : enRecherche() == (rechercheCourante() > 0)
		assertion(obj+"\\inv : enRecherche() == (rechercheCourante() > 0)", cnrs.enRecherche() == (cnrs.rechercheCourante() > 0));
		// \inv : rechercheFinie() == (rechercheCourante() == tempsDeRecherche())
		assertion(obj+"\\inv : rechercheFinie() == (rechercheCourante() == tempsDeRecherche())", cnrs.rechercheFinie() == (cnrs.rechercheCourante() == cnrs.tempsDeRecherche()));
	}

}
