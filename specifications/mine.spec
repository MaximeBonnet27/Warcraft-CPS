service: Mine

types: int, ETAT{OCCUPE,LIBRE}, boolean

Observators:
    const largeur:          [Mine] -> int
    const hauteur:          [Mine] -> int
    orRestant:              [Mine] -> int
    etat_d_appartenance:    [Mine] -> ETAT
    est_laminee:            [Mine] -> boolean

Constructors:
	init: int x int -> [Mine]
		pre init(largeur,hauteur)
			require largeur>0 ^ hauteur>0

Operators:
	retrait: [Mine] x int -> [Mine]
		pre retrait(M,s)
			require ¬est_laminee(M) ^ s>0

Observations:
[Invariants]
	est_laminee(M) =(min)= orRestant(M)≤0

[init]
	largeur(init(l,h))				= l
	hauteur(init(l,h))				= h
	orRestant(init(l,h))			= 500
	etat_d_appartenance(init(l,h))	= ETAT.LIBRE

[retrait]
	orRestant(retrait(M,s))				= orRestant(M) - s
	etat_d_appartenance(retrait(M,s))	= etat_d_appartenance(M)
