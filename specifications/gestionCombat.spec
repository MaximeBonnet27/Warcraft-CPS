service: GestionCombat

use: Villageois, Muraille

Observators:
	defenseur : [GestionCombat] -> Villageois
	const attaquant : [GestionCombat] -> Villageois
		pre attaquant(GC) require ¬estCombatMultiple(GC)
	const setAttaquant : [GestionCombat] -> Villageois
		pre setAttaquant(GC) require estCombatMultiple(GC)
	muraille : [GestionCombat] -> Muraille
	const estCombatMultiple : [GestionCombat] -> boolean
	const estCombatMuraille : [GestionCombat] -> boolean
Constructors:
	init : Villageois x Villageois -> [GestionCombat]
	
	initMultiple : Set<Villageois> x Villageois -> [GestionCombat]
	
	initMuraille : Villageois x Muraille -> [GestionCombat]

	initMultipleMuraille : Set<Villageois> x Muraille -> [GestionCombat]
Operators:

	combat : [GestionCombat] -> [GestionCombat]
		combat(GC)
			require ¬estCombatMultiple(GC) ^ ¬estCombatMuraille(GC) ¬Villageois::estMort(attaquant(GC)) ^ ¬Villageois::estMort(defenseur(GC))

	combatMultiple : [GestionCombat] -> [GestionCombat]
		combatMultiple(GC)
			require estCombatMultiple(GC) ^ ¬estCombatMuraille(GC)
			^ pour tout attaquant dans setAttaquant(GC), ¬Villageois::estMort(attaquant) 
			^ ¬Villageois::estMort(defenseur(GC))

	combatMuraille : [GestionCombat] -> [GestionCombat] 
		combatMuraille(GC)
			require ¬estCombatMultiple(GC) ^ estCombatMuraille(GC)
			^ ¬Villageois::estMort(attaquant(GC)) ^ ¬Muraille::estDetruite(muraille(GC))

	combatMurailleMultiple : [GestionCombat] -> [GestionCombat] 
		combatMuraille(GC)
			require estCombatMultiple(GC) ^ estCombatMuraille(GC)
			^ pour tout attaquant dans setAttaquant(GC), ¬Villageois::estMort(attaquant)
			^ ¬Muraille::estDetruite(muraille(GC))

Observations:
[Invariants]
		
[init]
	estCombatMultiple(init(attaquant, defenseur)) = false
	estCombatMuraille(init(attaquant, defenseur)) = false
	attaquant(init(attaquant, defenseur)) = attaquant
	defenseur(init(attaquant, defenseur)) = defenseur
	
[initMultiple]
	
	estCombatMultiple(initMultiple(attaquants, defenseur)) = true
	estCombatMuraille(init(attaquant, defenseur)) = false
	setAttaquant(initMultiple(attaquants, defenseur)) = attaquants
	defenseur(initMultiple(attaquants, defenseur)) = defenseur

[initMuraille]
	estCombatMultiple(initMuraille(attaquant, muraille)) = false
	estCombatMuraille(initMuraille(attaquant, muraille)) = true
	attaquant(initMuraille(attaquant, muraille)) = attaquant
	muraille(initMuraille(attaquant, muraille)) = muraille

[initMurailleMultiple]
	estCombatMultipleMultiple(initMuraille(attaquants, muraille)) = true
	estCombatMurailleMultiple(initMuraille(attaquants, muraille)) = true
	setAttaquant(initMurailleMultiple(attaquants, muraille)) = attaquants
	muraille(initMurailleMultiple(attaquants, muraille)) = muraille
[combat]
	defenseur(combat(GC)) = Villageois::retraitPV(defenseur(GC), Villageois::force(attaquant(GC))

[combatMultiple]
	defenseur(combatMultiple(GC)) = Villageois::retraitPV(defenseur(GC), Somme pour tout attaquant dans setAttaquant(GC) de (Villageois::force(attaquant)))

[combatMuraille]
	muraille(combatMuraille(GC)) = Muraille::taper(muraille(GC), Villageois::force(attaquant(GC)))

[combatMurailleMultiple]
	muraille(combatMuraille(GC)) = Muraille::taper(muraille(GC), Somme pour tout attaquant dans setAttaquant(GC) de (Villageois::force(attaquant)))
