/**
 * 
 */
package warcraft.implem;

import warcraft.services.IGestionCombatService;
import warcraft.services.IVillageoisService;

public class GestionCombat implements IGestionCombatService{

	private IVillageoisService attaquant;
	private IVillageoisService defenseur;
	
	@Override
	public IVillageoisService attaquant() {
		return attaquant;
	}

	@Override
	public IVillageoisService defenseur() {
		return defenseur;
	}

	@Override
	public IGestionCombatService init(IVillageoisService attaquant,
			IVillageoisService defenseur) {
		GestionCombat gc = new GestionCombat();
		gc.attaquant = attaquant;
		gc.defenseur = defenseur;
		return gc;
	}

	@Override
	public IGestionCombatService combat() {
		defenseur.retraitPV(attaquant.force());
		return this;
	}

}
