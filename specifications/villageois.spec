service: Villageois

types: RACE{ORC, HUMAIN}, int, double, boolean

Observators:
    const race:     [Villageois] -> RACE
    const largeur:  [Villageois] -> int
    const hauteur:  [Villageois] -> int
    const force:    [Villageois] -> int
    const vitesse:  [Villageois] -> double
    pointsDeVie:    [Villageois] -> int
    estMort:        [Villageois] -> boolean
    quantiteOr:     [Villageois] -> int

Constructors:
	init: RACE x int x int x int x double x int -> [Villageois]
		pre init(race,largeur,hauteur,force,vitesse,pointsDeVie)
			require largeur>0 ^ hauteur>0 ^ force>0 ^ vitesse>0 ^ pointsDeVie>0

Operators:
	retrait: [Villageois] x int -> [Villageois]
		pre retrait(V,s)
			require ¬estMort(V) ^ s>0

Observations:
[Invariants]
	estMort(V) =(min)= pointsDeVie(V)≤0

[init]
	race(init(r,l,h,f,v,p))			= r
	largeur(init(r,l,h,f,v,p))		= l
	hauteur(init(r,l,h,f,v,p))		= h
	force(init(r,l,h,f,v,p))		= f
	vitesse(init(r,l,h,f,v,p))		= v
	pointsDeVie(init(r,l,h,f,v,p))	= p
	quantiteOr(init(r,l,h,f,v,p))	= 0

[retrait]
	pointsDeVie(retrait(V,s))		= pointsDeVie(V) - s
	quantiteOr(retrait(V,s))		= quantiteOr(V)