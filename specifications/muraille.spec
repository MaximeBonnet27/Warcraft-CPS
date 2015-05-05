service: Muraille

types: int, boolean

Observators:
	const largeur: 	[Muraille] -> int
	const hauteur: 	[Muraille] -> int
	pointsDeVie: 	[Muraille] -> int
	estDetruite: 	[Muraille] -> boolean

Constructors:
	init : int x int x int-> [Muraille]
		pre init(l, h, p)
			require l > 0 ^ h > 0 ^ p > 0

Operators:
	taper : [Muraille] x int -> [Muraille]
		pre taper(M,degat)
			require ¬estDetruite(M) ^ degat > 0

Observations:
[Invariants]
	estDetruite(M) =(min)= (pointsDeVie(M) ≤ 0)

[init]
	largeur(init(l,h,p)) 		= l
	hauteur(init(l,h,p)) 		= h
	pointsDeVie(init(l,h,p)) 	= p

[taper]
	pointsDeVie(taper(M, x)) = pointsDeVie(M) - x
	
