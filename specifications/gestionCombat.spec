service: GestionCombat

use: Villageois

Observators:
	defenseur : [GestionCombat] -> Villageois
	attaquant : [GestionCombat] -> Villageois
		pre attaquant(GC) require ¬estCombatMultiple(GC)
	setAttaquant : [GestionCombat] -> Villageois
		pre setAttaquant(GC) require estCombatMultiple(GC)
	const estCombatMultiple : [GestionCombat] -> boolean
Constructors:
	init : Villageois x Villageois -> [GestionCombat]
	
	initMultiple : Set<Villageois> x Villageois -> [GestionCombat]
	
Operators:

	combat : [GestionCombat] x Villageois x Villageois-> [GestionCombat]
		combat(GC, attaquant, defenseur)
			require ¬Villageois::estMort(attaquant) ^ ¬Villageois::estMort(defenseur)

	combatMultiple : [GestionCombat] x Set<Villageois> x Villageois -> [GestionCombat]
		combat(GC, attaquants, defenseur)
			require pour tout attaquant dans attaquants, ¬Villageois::estMort(attaquant) 
			^ ¬Villageois::estMort(defenseur)

Observations:
[Invariants]
		
[init]
	estCombatMultiple(init(attaquant, defenseur)) = false
	attaquant(init(attaquant, defenseur)) = attaquant
	defenseur(init(attaquant, defenseur)) = defenseur
	
[initMultiple]
	
	estCombatMultiple(initMultiple(attaquants, defenseur)) = true
	setAttaquant(initMultiple(attaquants, defenseur)) = attaquants
	defenseur(initMultiple(attaquants, defenseur)) = defenseur

[combat]
	defenseur(combat(GC)) = Villageois::retraitPV(defenseur, Villageois::force(attaquant))
	attaquant(combat(GC)) = attaquant

[combatMultiple]
	defenseur(combatMultiple(GC)) = Villageois::retraitPV(defenseur, Somme pour tout attaquant dans attaquants(Villageois::force(attaquant)))
	setAttaquant(combatMultuiple(GC)) = attaquants
