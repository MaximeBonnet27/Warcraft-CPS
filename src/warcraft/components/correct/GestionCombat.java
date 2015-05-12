/**
 * 
 */
package warcraft.components.correct;

import java.util.ArrayList;
import java.util.Set;

import warcraft.services.IGestionCombatService;
import warcraft.services.IMurailleService;
import warcraft.services.IVillageoisService;
import warcraft.services.RequireMineService;
import warcraft.services.RequireMurailleService;
import warcraft.services.RequireVillageoisService;

public class GestionCombat implements IGestionCombatService,RequireVillageoisService,RequireMurailleService{

	private IVillageoisService attaquant;
	private ArrayList<IVillageoisService> setAttaquant;
	private IVillageoisService defenseur;
	private IMurailleService muraille;
	private boolean estCombatMultiple;
	private boolean estCombatMuraille;
	private boolean attaquantInitialisation;
	private boolean defenseurInitialisation;
	
	
	public GestionCombat() {
		setAttaquant=new ArrayList<IVillageoisService>();
		attaquantInitialisation=true;
		defenseurInitialisation=false;
	}
	
	@Override
	public void bindVillageoisService(IVillageoisService service) {
		if(attaquantInitialisation){
			if(!estCombatMultiple()){
			attaquant=service;
			defenseurInitialisation=true;
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
		
		return attaquant
	}
	@Override
	public IVillageoisService defenseur() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<IVillageoisService> setAttaquant() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public IMurailleService muraille() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean estCombatMultiple() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean estCombatMuraille() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void init(IVillageoisService attaquant, IVillageoisService defenseur) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void initMultiple(Set<IVillageoisService> attaquants,
			IVillageoisService defenseur) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void initMuraille(IVillageoisService attaquant,
			IMurailleService muraille) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void initMurailleMultiple(Set<IVillageoisService> attaquants,
			IMurailleService muraille) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void combat() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void combatMultiple() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void combatMuraille() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void combatMurailleMultiple() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	


}
