service: Villageois

types: enum RACE{ORC, HUMAIN}, int, double, boolean, COMPETENCE{FORCE,PV,VITESSE,RIEN}

Observators:
    const race:     [Villageois] -> RACE
    const largeur:  [Villageois] -> int
    const hauteur:  [Villageois] -> int
    force:    		[Villageois] -> int
    vitesse:  		[Villageois] -> double
    pointsDeVie:    [Villageois] -> int
    estMort:        [Villageois] -> boolean
    quantiteOr:     [Villageois] -> int
    compteurCorvee: [Villageois] -> int
    corveeFinie:	[Villageois] -> boolean
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
			require ¬estMort(V) ^ n≥0

	retraitOr: [Villageois] x int -> [Villageois]
		pre retraitOr(V,s)
			require ¬estMort(V) ^ quantiteOr(V)-s≥0 ^ s≥0

	commenceTravaille: [Villageois] -> [Villageois]
		pre commenceTravaille(V) 
			require ¬estMort(V)

	travaille: [Villageois] -> [Villageois]
		pre travaille(V)
			require ¬estMort(V) ^ ¬corveeFini(V)

	amelioration : [Villageois] x COMPETENCE x int -> [Villageois]
		pre amelioration(V, comp, val)
			require ¬estMort(V) ^ val > 0

Observations:
[Invariants]
	estMort(V) =(min)= pointsDeVie(V)≤0
	corveeFini(V) =(min)= (compteurCorvee(V)=16)
	enCorvee(V) =(min)= (compteurCorvee>0)
	quantiteOr(V)≥0
	0≤compteurCorvee(V)≤16
	force(V) > 0
	vitesse(V) > 0

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
    force(retraitPV(V,s))      		= force(V)
    vitesse(retraitPV(V,s))     	= vitesse(V)

[ajouteOr]
	pointsDeVie(ajouteOr(V,n))		= pointsDeVie(V)
	quantiteOr(ajouteOr(V,n))		= quantiteOr(V) + n
	compteurCorvee(ajouteOr(V,n))	= compteurCorvee(V)
    force(ajouteOr(V,n))    		= force(V)
    vitesse(ajouteOr(V,n))  		= vitesse(V)

[retraitOr]
	pointsDeVie(retraitOr(V,s))		= pointsDeVie(V)
	quantiteOr(retraitOr(V,s))		= quantiteOr(V) - s
	compteurCorvee(retraitOr(V,s))	= compteurCorvee(V)
    force(retraitOr(V,s))       	= force(V)
    vitesse(retraitOr(V,s))     	= vitesse(V)

[commenceTravaille]
	pointsDeVie(commenceTravaille(V))		= pointsDeVie(V)
	quantiteOr(commenceTravaille(V))		= quantiteOr(V)
	compteurCorvee(commenceTravaille(V))	= 1
    force(commenceTravaille(V)      		= force(V)
    vitesse(commenceTravaille(V)    		= vitesse(V)

[travaille]
	pointsDeVie(travaille(V))		= pointsDeVie(V)
	quantiteOr(travaille(V))		= quantiteOr(V)
	compteurCorvee(travaille(V))	= compteurCorvee(V) + 1
    force(travaille(V))     		= force(V)
    vitesse(travaille(V))   		= vitesse(V)

[amelioration]
	pointsDeVie(amelioration(V, comp, val)) == 
		si comp = COMPETENCE.PV,
		 		pointsDeVie(V) + val
		sinon
			pointsDeVie(V)

	force(amelioration(V, comp, val)) == 
		si comp == COMPETENCE.FORCE,
			force(V) + val
		sinon
			force(V) 

	vitesse(amelioration(V, comp, val)) ==
		si comp == COMPETENCE.VITESSE,
			vitesse(V) + val 
		sinon
			vitesse(V)


	quantiteOr(amelioration(V, comp, val)) = quantiteOr(V)
	compteurCorvee(amelioration(V, comp, val)) = compteurCorvee(V)
