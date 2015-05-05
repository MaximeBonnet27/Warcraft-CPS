service: Route

types: int, double

Observators:
    const largeur:          [Route] -> int
    const hauteur:          [Route] -> int
	const multiplicateur: 	[Route] -> double

Constructors:
	init : int x int x double -> [Route]
		pre init(l, h, m)
			require l > 0 ^ h > 0 ^ m >= 1.0

Operators:

Observations:
[Invariants]
	
[init]
	largeur(init(l,h,m)) 		= l
	hauteur(init(l,h,m)) 		= h
	multiplicateur(init(l,h,m)) = m
