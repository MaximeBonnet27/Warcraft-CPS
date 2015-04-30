service: Mine

types: int, ETAT{OCCUPE,LIBRE}, boolean

Observators:
    const largeur:          [Mine] -> int
    const hauteur:          [Mine] -> int
    orRestant:              [Mine] -> int
    etat_d_appartenance:    [Mine] -> ETAT
    estLaminee:             [Mine] -> boolean
    compteurAbandon			[Mine] -> int
    estAbandonnee			[Mine] -> boolean

Constructors:
	init: int x int -> [Mine]
		pre init(largeur,hauteur)
			require largeur>0 ^ hauteur>0

Operators:
	retrait: [Mine] x int -> [Mine]
		pre retrait(M,s)
			require ¬estLaminee(M) ^ s>0

Observations:
[Invariants]
	estLaminee(M) =(min)= (orRestant(M)=0)
	estAbandonnee(M) =(min)= (compteurAbandon(M)=51)
	orRestant(M)≥0
	0≤compteurAbandon(M)≤51

[init]
	largeur(init(l,h))				= l
	hauteur(init(l,h))				= h
	orRestant(init(l,h))			= 500
	etat_d_appartenance(init(l,h))	= ETAT.LIBRE

[retrait]
	orRestant(retrait(M,s))				= orRestant(M) - s
	etat_d_appartenance(retrait(M,s))	= etat_d_appartenance(M)
