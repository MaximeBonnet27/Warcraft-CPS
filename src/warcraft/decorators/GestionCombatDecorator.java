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

	public void init(IVillageoisService attaquant, IVillageoisService defenseur) {
		delegate.init(attaquant, defenseur);
	}

	public void combat() {
		delegate.combat();
	}
	
	
}
