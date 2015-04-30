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
    compteurCorvee: [Villageois] -> int
    corveeFini:		[Villageois] -> boolean
    enCorvee: 		[Villageois] -> boolean

Constructors:
	init: RACE x int x int x int x double x int -> [Villageois]
		pre init(race,largeur,hauteur,force,vitesse,pointsDeVie)
			require largeur>0 ^ hauteur>0 ^ force>0 ^ vitesse>0 ^ pointsDeVie>0

Operators:
	retraitPV: [Villageois] x int -> [Villageois]
		pre retraitPV(V,s)
			require ¬estMort(V) ^ s>0

	ajouteOr: [Villageois] x int -> [Villageois]
		pre ajouteOr(V,n)
			require n≥0

	retraitOr: [Villageois] x int -> [Villageois]
		pre retraitOr(V,s)
			require quantiteOr(V)-s≥0 ^ s≥0

	commenceTravaille: [Villageois] -> [Villageois]

	travaille: [Villageois] -> [Villageois]
		pre travaille(V)
			require ¬corveeFini(V)

Observations:
[Invariants]
	estMort(V) =(min)= pointsDeVie(V)≤0
	corveeFini(V) =(min)= (compteurCorvee(V)=16)
	enCorvee(V) =(min)= (compteurCorvee>0)
	quantiteOr(V)≥0
	0≤compteurCorvee(V)≤16

[init]
	race(init(r,l,h,f,v,p))				= r
	largeur(init(r,l,h,f,v,p))			= l
	hauteur(init(r,l,h,f,v,p))			= h
	force(init(r,l,h,f,v,p))			= f
	vitesse(init(r,l,h,f,v,p))			= v
	pointsDeVie(init(r,l,h,f,v,p))		= p
	quantiteOr(init(r,l,h,f,v,p))		= 0
	compteurCorvee(init(r,l,h,f,v,p))	= 0

[retraitPV]
	pointsDeVie(retraitPV(V,s))		= pointsDeVie(V) - s
	quantiteOr(retraitPV(V,s))		= quantiteOr(V)
	compteurCorvee(retraitPV(V,s))	= compteurCorvee(V)

[ajouteOr]
	pointsDeVie(ajouteOr(V,n))		= pointsDeVie(V)
	quantiteOr(ajouteOr(V,n))		= quantiteOr(V) + n
	compteurCorvee(ajouteOr(V,n))	= compteurCorvee(V)

[retraitOr]
	pointsDeVie(retraitOr(V,s))		= pointsDeVie(V)
	quantiteOr(retraitOr(V,s))		= quantiteOr(V) - s
	compteurCorvee(retraitOr(V,s))	= compteurCorvee(V)

[commenceTravaille]
	pointsDeVie(commenceTravaille(V))		= pointsDeVie(V)
	quantiteOr(commenceTravaille(V))		= quantiteOr(V)
	compteurCorvee(commenceTravaille(V))	= (compteurCorvee(V)=1)

[travaille]
	pointsDeVie(travaille(V))		= pointsDeVie(V)
	quantiteOr(travaille(V))		= quantiteOr(V)
	compteurCorvee(travaille(V))	= compteurCorvee(V) + 1
