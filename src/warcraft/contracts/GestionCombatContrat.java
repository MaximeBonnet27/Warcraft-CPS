package warcraft.contracts;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Set;

import warcraft.decorators.GestionCombatDecorator;
import warcraft.services.IGestionCombatService;
import warcraft.services.IMurailleService;
import warcraft.services.IVillageoisService;

public class GestionCombatContrat extends GestionCombatDecorator{

	public GestionCombatContrat(IGestionCombatService delegate) {
		super(delegate);

	}

	public void checkInvariants(){
		// pas d'invariants
	}

	@Override
	public IVillageoisService attaquant()  throws Exception{
		// \pre: !estCombatMultiple()
		assertTrue("\\pre: !estCombatMultiple()", !super.estCombatMultiple());

		return super.attaquant();
	}

	@Override
	public IVillageoisService defenseur()  throws Exception{
		// \pre: !estCombatMuraille()
		assertTrue("\\pre: !estCombatMuraille()", !super.estCombatMuraille());
		return super.defenseur();
	}

	@Override
	public ArrayList<IVillageoisService> setAttaquant() throws Exception{
		// \pre: estCombatMultiple()
		assertTrue("\\pre: estCombatMultiple()", super.estCombatMultiple());
		return super.setAttaquant();
	}

	@Override
	public IMurailleService muraille() throws Exception{
		// \pre: estCombatMuraille()
		assertTrue("\\pre: estCombatMuraille()", super.estCombatMuraille());

		return super.muraille();
	}

	@Override
	public boolean estCombatMultiple(){
		return  super.estCombatMultiple();
	}

	@Override
	public boolean estCombatMuraille(){
		return super.estCombatMuraille();
	}

	@Override
	public void init(IVillageoisService attaquant,IVillageoisService defenseur) {
		super.init(attaquant, defenseur);

		checkInvariants();

		// \post : attaquant() == attaquant
		try {
			assertTrue("\\post : attaquant() == attaquant", super.attaquant() == attaquant);
		} catch (Exception e) {
			assertTrue("\\post : attaquant() == attaquant",false);
		}
		// \post : defenseur() == defenseur
		try {
			assertTrue("\\post : defenseur() == defenseur", super.defenseur() == defenseur);
		} catch (Exception e) {
			assertTrue("\\post : defenseur() == defenseur",false);
		}
		// \post: estCombatMultiple() == false
		assertTrue("\\post: estCombatMultiple() == false", super.estCombatMultiple()==false);
		// \post: estCombatMuraille() == false
		assertTrue("\\post: estCombatMuraille() == false", super.estCombatMuraille()==false);
	}

	public void initMultiple(ArrayList<IVillageoisService> attaquants, IVillageoisService defenseur){
		super.initMultiple(attaquants, defenseur);

		checkInvariants();

		// \post : setAttaquant() == attaquants
		try {
			assertTrue("\\post : setAttaquant() == attaquants", super.setAttaquant().equals(attaquants));
		} catch (Exception e) {
			assertTrue("\\post : setAttaquant() == attaquants",false);
		}

		// \post : defenseur() == defenseur
		try {
			assertTrue("\\post : defenseur() == defenseur", super.defenseur() == defenseur);
		} catch (Exception e) {
			assertTrue("\\post : defenseur() == defenseur",false);
		}

		// \post : estCombatMultiple() == true
		assertTrue("\\post: estCombatMultiple() == true", super.estCombatMultiple()==true);
		// \post : estCombatMuraille() == false
		assertTrue("\\post: estCombatMuraille() == false", super.estCombatMuraille()==false);
	}

	public void initMuraille(IVillageoisService attaquant, IMurailleService muraille){
		super.initMuraille(attaquant, muraille);

		checkInvariants();

		// \post : attaquant() == attaquant
		try {
			assertTrue("\\post : attaquant() == attaquant", super.attaquant() == attaquant);
		} catch (Exception e) {
			assertTrue("\\post : attaquant() == attaquant",false);
		}
		// \post : muraille() == muraille
		try {
			assertTrue("\\post : muraille() == muraille", super.muraille() == muraille);
		} catch (Exception e) {
			assertTrue("\\post : muraille() == muraille",false);
		}
		// \post: estCombatMultiple() == false
		assertTrue("\\post: estCombatMultiple() == false", super.estCombatMultiple()==false);
		// \post: estCombatMuraille() == true
		assertTrue("\\post: estCombatMuraille() == true", super.estCombatMuraille()==true);
	}

	public void initMurailleMultiple(ArrayList<IVillageoisService> attaquants, IMurailleService muraille){
		super.initMurailleMultiple(attaquants, muraille);

		checkInvariants();

		// \post : setAttaquant() == attaquants
		try {
			assertTrue("\\post : setAttaquant() == attaquants", super.setAttaquant().equals(attaquants));
		} catch (Exception e) {
			assertTrue("\\post : setAttaquant() == attaquants",false);
		}

		// \post : muraille() == muraille
		try {
			assertTrue("\\post : muraille() == muraille", super.muraille() == muraille);
		} catch (Exception e) {
			assertTrue("\\post : muraille() == muraille",false);
		}

		// \post : estCombatMultiple() == true
		assertTrue("\\post: estCombatMultiple() == true", super.estCombatMultiple()==true);
		// \post: estCombatMuraille() == true
		assertTrue("\\post: estCombatMuraille() == true", super.estCombatMuraille()==true);
	}

	@Override
	public void combat()  throws Exception{

		// Captures
		int oldAttaquant_Force = super.attaquant().force();
		int oldDefenseur_PointsDeVie = super.defenseur().pointsDeVie();

		// Pré-Conditions

		// \pre : !attaquant.estMort()
		assertTrue("\\pre : !attaquant().estMort()", !super.attaquant().estMort());
		// \pre : !defenseur.estMort()
		assertTrue("\\pre : !defenseur().estMort()", !super.defenseur().estMort());
		// \pre: !estCombatMultiple()
		assertTrue("\\pre: !estCombatMultiple()", !super.estCombatMultiple());
		// \pre: !estCombatMuraille()
		assertTrue("\\pre: !estCombatMuraille()", !super.estCombatMuraille());

		// Invariants
		checkInvariants();

		// Appel
		super.combat();

		// Invariants
		checkInvariants();

		// Post-Conditions

		try{
			// \post : defenseur().pointsDeVie() == defenseur().pointsDeVie()@pre - attaquant().force()@pre
			assertTrue("\\post: defenseur().pointsDeVie() == defenseur().pointsDeVie()@pre - attaquant().force()", super.defenseur().pointsDeVie() == oldDefenseur_PointsDeVie - oldAttaquant_Force);
		}catch(Exception e){
			assertTrue("\\post: defenseur().pointsDeVie() == defenseur().pointsDeVie()@pre - attaquant().force()", false);
		}
	}

	public void combatMultiple() throws Exception{

		// Pré-Conditions

		// \pre : \forall attaquant \in setAttaquant() : !attaquant.estMort()
		for(IVillageoisService v: setAttaquant())
			assertTrue("\\pre : \\forall attaquant \\in setAttaquant() : !attaquant.estMort()", !v.estMort());

		// \pre : !defenseur().estMort()
		assertTrue("\\pre : !defenseur.()estMort()", !super.defenseur().estMort());
		// \pre: estCombatMultiple()
		assertTrue("\\pre: estCombatMultiple()", super.estCombatMultiple());
		// \pre: !estCombatMuraille()
		assertTrue("\\pre: !estCombatMuraille()", !super.estCombatMuraille());

		checkInvariants();
		super.combatMultiple();
		checkInvariants();

		//\post : defenseur() ==  defenseur()@pre.retraitPV(\sum \forall attaquant in setAttaquant()@pre : attaquant.force())
		//appel a un autre service

	}

	@Override
	public void combatMuraille()  throws Exception{

		// Captures
		int oldAttaquant_Force = super.attaquant().force();
		int oldMuraille_PointsDeVie = super.muraille().pointsDeVie();

		// Pré-Conditions

		// \pre : !attaquant().estMort()
		assertTrue("\\pre : !attaquant().estMort()", !super.attaquant().estMort());
		// \pre : !muraille().estDetruite()
		assertTrue("\\pre : !muraille().estDetruite()", !super.muraille().estDetruite());
		// \pre: !estCombatMultiple()
		assertTrue("\\pre: !estCombatMultiple()", !super.estCombatMultiple());
		// \pre: estCombatMuraille()
		assertTrue("\\pre: estCombatMuraille()", super.estCombatMuraille());

		// Invariants
		checkInvariants();

		// Appel
		super.combatMuraille();

		// Invariants
		checkInvariants();

		// Post-Conditions

		try{
			// \post : muraille().pointsDeVie() == muraille()@pre.taper(attaquant()@pre.force())
			assertTrue("\\post : muraille().pointsDeVie() == muraille()@pre.taper(attaquant()@pre.force())", super.muraille().pointsDeVie() == oldMuraille_PointsDeVie - oldAttaquant_Force);
		}catch(Exception e){
			assertTrue("\\post : muraille().pointsDeVie() == muraille()@pre.taper(attaquant()@pre.force())", false);
		}
	}

	public void combatMurailleMultiple() throws Exception{

		// Pré-Conditions

		// \pre : \forall attaquant \in setAttaquant() : !attaquant.estMort()
		for(IVillageoisService v: setAttaquant())
			assertTrue("\\pre : \\forall attaquant \\in setAttaquant() : !attaquant.estMort()", !v.estMort());

		// \pre : !muraille().estDetruite()
		assertTrue("\\pre : !muraille().estDetruite()", !super.muraille().estDetruite());
		// \pre: estCombatMultiple()
		assertTrue("\\pre: estCombatMultiple()", super.estCombatMultiple());
		// \pre: estCombatMuraille()
		assertTrue("\\pre: estCombatMuraille()", super.estCombatMuraille());

		checkInvariants();
		super.combatMurailleMultiple();
		checkInvariants();

		//\post : muraille().pointsDeVie() == muraille()@pre.taper(\sum \forall attaquant in setAttaquant()@pre : attaquant.force())
		//appel a un autre service

	}


}
