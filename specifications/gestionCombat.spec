service: GestionCombat

use: Villageois

Observators:
	
	
Constructors:
	init : -> [GestionCombat]
		
Operators:

	combat : [GestionCombat] x Villageois x Villageois-> [GestionCombat]
		combat(GC, attaquant, defenseur)
			require ¬Villageois::estMort(attaquant) ^ ¬Villageois::estMort(defenseur)

Observations:
[Invariants]
		
[init]

[combat]
