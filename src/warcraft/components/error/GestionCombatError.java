/**
 * 
 */
package warcraft.components.error;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Set;

import warcraft.services.IGestionCombatService;
import warcraft.services.IMurailleService;
import warcraft.services.IVillageoisService;
import warcraft.services.RequireMineService;
import warcraft.services.RequireMurailleService;
import warcraft.services.RequireVillageoisService;

public class GestionCombatError implements IGestionCombatService,RequireVillageoisService,RequireMurailleService{

	private IVillageoisService attaquant;
	private ArrayList<IVillageoisService> setAttaquant;
	private IVillageoisService defenseur;
	private IMurailleService muraille;
	private boolean estCombatMultiple;
	private boolean estCombatMuraille;
	private boolean attaquantInitialisation;


	public GestionCombatError() {
		setAttaquant=new ArrayList<IVillageoisService>();
		attaquantInitialisation=true;
	}

	@Override
	public void bindVillageoisService(IVillageoisService service) {
		if(attaquantInitialisation){
			if(!estCombatMultiple()){
				attaquant=service;
				attaquantInitialisation=false;
			}else{
				setAttaquant.add(service);
			}
		}else{
			defenseur=service;
		}
	}

	@Override
	public void bindMurailleService(IMurailleService service) {
		muraille=service;

	}

	@Override
	public IVillageoisService attaquant() throws Exception {
		//error
		//if(estCombatMultiple())
		//	throw new Exception("\\pre: !estCombatMultiple()");
		return attaquant;
	}
	@Override
	public IVillageoisService defenseur() throws Exception {
		//error
		//if(estCombatMuraille())
		//	throw new Exception("\\pre: !estCombatMuraille()");
		return defenseur;
	}

	@Override
	public ArrayList<IVillageoisService> setAttaquant() throws Exception {
		if(!estCombatMultiple())
			throw new Exception("\\pre: estCombatMultiple()");
		return setAttaquant;
	}

	@Override
	public IMurailleService muraille() throws Exception {
		if(!estCombatMuraille())
			throw new Exception("\\pre: estCombatMuraille()");
		return muraille;
	}

	@Override
	public boolean estCombatMultiple() {
		return estCombatMultiple;
	}

	@Override
	public boolean estCombatMuraille() {
		return estCombatMuraille;
	}

	@Override
	public void init(IVillageoisService attaquant, IVillageoisService defenseur) {
		estCombatMultiple=false;
		//error
		//estCombatMuraille=false;
		bindVillageoisService(attaquant);
		bindVillageoisService(defenseur);
	}

	@Override
	public void initMultiple(ArrayList<IVillageoisService> attaquants,
			IVillageoisService defenseur) {
		//error
		//estCombatMultiple=true;
		estCombatMuraille=false;
		for(int i=0;i<attaquants.size();i++){
			bindVillageoisService(attaquants.get(i));
		}
		attaquantInitialisation=false;
		bindVillageoisService(defenseur);


	}

	@Override
	public void initMuraille(IVillageoisService attaquant,
			IMurailleService muraille) {
		estCombatMultiple=false;
		estCombatMuraille=true;
		bindVillageoisService(attaquant);
		bindMurailleService(muraille);

	}

	@Override
	public void initMurailleMultiple(ArrayList<IVillageoisService> attaquants,
			IMurailleService muraille) {
		estCombatMultiple=true;
		estCombatMuraille=true;
		for(int i=0;i<attaquants.size();i++){
			bindVillageoisService(attaquants.get(i));
		}
		attaquantInitialisation=false;
		bindMurailleService(muraille);

	}

	@Override
	public void combat() throws Exception {
		if(attaquant().estMort())
			throw new Exception("\\pre : !attaquant().estMort()");

		//error
		//if(defenseur().estMort())
		//	throw new Exception("\\pre : !defenseur().estMort()");

		if(estCombatMultiple())
			throw new Exception("\\pre: !estCombatMultiple()");

		if(estCombatMuraille())
			throw new Exception("\\pre: !estCombatMuraille()");

		//error
		//defenseur().retraitPV(attaquant().force());
	}

	@Override
	public void combatMultiple() throws Exception {
		for(IVillageoisService v: setAttaquant()){
			if(v.estMort())
				throw new Exception("\\pre : \\forall attaquant \\in setAttaquant() : !attaquant.estMort()");
		}

		if(defenseur().estMort())
			throw new Exception("\\pre : !defenseur().estMort()");

		if(!estCombatMultiple())
			throw new Exception("\\pre: estCombatMultiple()");

		if(estCombatMuraille())
			throw new Exception("\\pre: !estCombatMuraille()");

		//error
		//for(IVillageoisService v: setAttaquant()){
		//	if(!defenseur().estMort())
		//		defenseur().retraitPV(v.force());
		//}
	}

	@Override
	public void combatMuraille() throws Exception {
		if(attaquant().estMort())
			throw new Exception("\\pre : !attaquant().estMort()");

		if(muraille().estDetruite())
			throw new Exception("\\pre : !muraille().estDetruite()");

		//error
		//if(estCombatMultiple())
		//	throw new Exception("\\pre: !estCombatMultiple()");

		if(!estCombatMuraille())
			throw new Exception("\\pre: estCombatMuraille()");

		muraille().taper(attaquant().force());
	}

	@Override
	public void combatMurailleMultiple() throws Exception {
		for(IVillageoisService v: setAttaquant()){
			if(v.estMort())
				throw new Exception("\\pre : \\forall attaquant \\in setAttaquant() : !attaquant.estMort()");
		}

		if(muraille().estDetruite())
			throw new Exception("\\pre : !muraille().estDetruite()");

		if(!estCombatMultiple())
			throw new Exception("\\pre: estCombatMultiple()");

		//error
		//if(estCombatMuraille())
		//	throw new Exception("\\pre: !estCombatMuraille()");

		for(IVillageoisService v: setAttaquant()){
			if(!muraille().estDetruite())
				muraille().taper(v.force());
		}
	}





}
