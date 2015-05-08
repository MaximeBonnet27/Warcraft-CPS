/**
 * 
 */
package warcraft.components.correct;

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
	public void init(IVillageoisService attaquant,
			IVillageoisService defenseur) {
		this.attaquant = attaquant;
		this.defenseur = defenseur;
	}

	@Override
	public void combat() {
		defenseur.retraitPV(attaquant.force());
	}

}
