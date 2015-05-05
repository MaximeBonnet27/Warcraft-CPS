/**
 * 
 */
package warcraft.decorators;

import warcraft.services.IGestionCombatService;
import warcraft.services.IVillageoisService;

public abstract class GestionCombatDecorator implements IGestionCombatService{

	private IGestionCombatService delegate;

	public GestionCombatDecorator(IGestionCombatService delegate) {
		super();
		this.delegate = delegate;
	}

	public IVillageoisService attaquant() {
		return delegate.attaquant();
	}

	public IVillageoisService defenseur() {
		return delegate.defenseur();
	}

	public IGestionCombatService init(IVillageoisService attaquant,
			IVillageoisService defenseur) {
		return delegate.init(attaquant, defenseur);
	}

	public IGestionCombatService combat() {
		return delegate.combat();
	}
	
	
	
}
