service: Muraille

Observators:
	const largeur: [Muraille] -> int
	const hauteur: [Muraille] -> int
	durabilite: [Muraille] -> int
	estDetruite: [Muraille] -> boolean
Constructors:
	init : int x int x int-> [Muraille]
		pre init(l, h, d)
			require l > 0 ^ h > 0 ^ d > 0

Operators:
	taper : [Muraille] x int -> [Muraille]
		pre taper(M,degat)
			require ¬estDetruite(M) ^ degat > 0

Observations:
[Invariants]
	estDetruite(M) =(min)= (durabilite = 0)
	durabilite(M) >= 0

[init]
	largeur(init(l,h,d)) = l
	hauteur(init(l,h,d)) = h
	durabilite(init(l,h,d)) = d

[taper]
	durabilite(taper(M, x)) = max(0, durabilite(M) - x)
	