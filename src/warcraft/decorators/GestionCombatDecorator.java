/**
 * 
 */
package warcraft.decorators;

import java.util.ArrayList;
import java.util.Set;

import warcraft.services.IGestionCombatService;
import warcraft.services.IMurailleService;
import warcraft.services.IVillageoisService;

public abstract class GestionCombatDecorator implements IGestionCombatService{

	private IGestionCombatService delegate;

	public GestionCombatDecorator(IGestionCombatService delegate) {
		this.delegate = delegate;
	}

	public IVillageoisService attaquant() throws Exception {
		return delegate.attaquant();
	}

	public IVillageoisService defenseur() throws Exception {
		return delegate.defenseur();
	}

	public ArrayList<IVillageoisService> setAttaquant() throws Exception {
		return delegate.setAttaquant();
	}

	public IMurailleService muraille() throws Exception {
		return delegate.muraille();
	}

	public boolean estCombatMultiple() {
		return delegate.estCombatMultiple();
	}

	public boolean estCombatMuraille() {
		return delegate.estCombatMuraille();
	}

	public void init(IVillageoisService attaquant, IVillageoisService defenseur) {
		delegate.init(attaquant, defenseur);
	}

	public void initMultiple(ArrayList<IVillageoisService> attaquants,
			IVillageoisService defenseur) {
		delegate.initMultiple(attaquants, defenseur);
	}

	public void initMuraille(IVillageoisService attaquant,
			IMurailleService muraille) {
		delegate.initMuraille(attaquant, muraille);
	}

	public void initMurailleMultiple(ArrayList<IVillageoisService> attaquants,
			IMurailleService muraille) {
		delegate.initMurailleMultiple(attaquants, muraille);
	}

	public void combat() throws Exception {
		delegate.combat();
	}

	public void combatMultiple() throws Exception {
		delegate.combatMultiple();
	}

	public void combatMuraille() throws Exception {
		delegate.combatMuraille();
	}

	public void combatMurailleMultiple() throws Exception {
		delegate.combatMurailleMultiple();
	}
	
	
}
