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

	commenceTravail: [Villageois] -> [Villageois]
		pre commenceTravail(V) 
			require ¬estMort(V)

	finirTravail: [Villageois] -> [Villageois]
		pre finirTravail(V) 
			require ¬estMort(V) ^ enCorvee(V)

	travail: [Villageois] -> [Villageois]
		pre travail(V)
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

[commenceTravail]
	pointsDeVie(commenceTravail(V))		= pointsDeVie(V)
	quantiteOr(commenceTravail(V))		= quantiteOr(V)
	compteurCorvee(commenceTravail(V))	= 1
    force(commenceTravail(V))      		= force(V)
    vitesse(commenceTravail(V))    		= vitesse(V)

[finirTravail]
	pointsDeVie(finirTravail(V))		= pointsDeVie(V)
	quantiteOr(finirTravail(V))		= quantiteOr(V)
	compteurCorvee(finirTravail(V))	= 0
    force(finirTravail(V) )     		= force(V)
    vitesse(finirTravail(V))    		= vitesse(V)

[travail]
	pointsDeVie(travail(V))		= pointsDeVie(V)
	quantiteOr(travail(V))		= quantiteOr(V)
	compteurCorvee(travail(V))	= compteurCorvee(V) + 1
    force(travail(V))     		= force(V)
    vitesse(travail(V))   		= vitesse(V)

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
