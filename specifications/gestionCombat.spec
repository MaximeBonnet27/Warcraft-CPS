service: GestionCombat

use: Villageois

Observators:
	attaquant : [GestionCombat] -> Villageois
	defenseur : [GestionCombat] -> Villageois
	
Constructors:
	init : Villageois x Villageois : [GestionCombat]
		init(attaquant, defenseur) 
			require ¬Villageois::estMort(attaquant) ^ ¬Villageois::estMort(defenseur)

Operators:

	combat : [GestionCombat] -> [GestionCombat]
		combat(GC)
			require ¬Villageois::estMort(attaquant(GC)) ^ ¬Villageois::estMort(defenseur(GC))

Observations:
[Invariants]
		
[init]
	attaquant(init(attaquant, defenseur)) = attaquant
	defenseur(init(attaquant, defenseur)) = defenseur

[combat]
	Villageois::pointsDeVie(attaquant(combat(GC))) = Villageois::pointsDeVie(attaquant(GC))
	Villageois::pointsDeVie(defenseur(combat(GC))) = Villageois::pointsDeVie(defenseur(GC)) - Villageois::force(attaquant(GC))
