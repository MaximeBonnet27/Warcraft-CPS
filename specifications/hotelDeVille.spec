service: HotelDeVille

types: int, ETAT{OCCUPE,LIBRE}, boolean

Observators:
    const largeur:          [HotelDeVille] -> int
    const hauteur:          [HotelDeVille] -> int
    orRestant:              [HotelDeVille] -> int
    etat_d_appartenance:    [HotelDeVille] -> ETAT
    est_laminee:            [HotelDeVille] -> boolean

Constructors:
	init: int x int -> [HotelDeVille]
		pre init(largeur,hauteur)
			require largeur>0 ^ hauteur>0

Operators:
	retrait: [HotelDeVille] x int -> [HotelDeVille]
		pre retrait(H,s)
			require ¬est_laminee(H) ^ s>0

	depot: [HotelDeVille] x int -> [HotelDeVille]
		pre depot(H,s)
			require d>0

Observations:
[Invariants]
	est_laminee(H) =(min)= orRestant(H)≤0

[init]
	largeur(init(l,h))				= l
	hauteur(init(l,h))				= h
	orRestant(init(l,h))			= 16
	etat_d_appartenance(init(l,h))	= ETAT.OCCUPE

[retrait]
	orRestant(retrait(H,s))				= orRestant(H) - s
	etat_d_appartenance(retrait(H,s))	= etat_d_appartenance(H)

[depot]
	orRestant(depot(H,d))				= orRestant(H) + d
	etat_d_appartenance(depot(H,d))		= etat_d_appartenance(H)