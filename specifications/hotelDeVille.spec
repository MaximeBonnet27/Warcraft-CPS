service: HotelDeVille

types: int, enum ETAT{OCCUPE,LIBRE}, boolean, enum RACE{ORC, HUMAIN}

Observators:
    const largeur:          [HotelDeVille] -> int
    const hauteur:          [HotelDeVille] -> int
    orRestant:              [HotelDeVille] -> int
    est_laminee:            [HotelDeVille] -> boolean
    compteurAbandon:		[HotelDeVille] -> int
    etat_d_appartenance:    [HotelDeVille] -> ETAT
    appartenance:			[HotelDeVille] -> RACE
    	pre appartenance(H)
    		require etat_d_appartenance(H)=ETAT.OCCUPE 

Constructors:
	init: RACE x int x int-> [HotelDeVille]
		pre init(race,largeur,hauteur)
			require largeur>0 ^ hauteur>0

Operators:
	retrait: [HotelDeVille] x int -> [HotelDeVille]
		pre retrait(H,s)
			require orRestant(H)-s≥0 ^ s≥0

	depot: [HotelDeVille] x int -> [HotelDeVille]
		pre depot(H,s)
			require d≥0

	accueil: [HotelDeVille] x RACE -> [HotelDeVille]
		pre accueil(H,race)
			require etat_d_appartenance(H)=ETAT.OCCUPE => race=appartenance(H)

	abandoned: [HotelDeVille] -> [HotelDeVille]
		pre abandoned(H)
			require etat_d_appartenance(H)=ETAT.OCCUPE

Observations:
[Invariants]
	est_laminee(H) =(min)= orRestant(H)≤0
	(etat_d_appartenance(H)=ETAT.LIBRE) =(min)= (compteurAbandon(H)=51)
	(etat_d_appartenance(H)=ETAT.OCCUPE) =(min)= (0≤compteurAbandon(H)<51)
	orRestant(H)≥0
	0≤compteurAbandon(H)≤51

[init]
	largeur(init(r,l,h))				= l
	hauteur(init(r,l,h))				= h
	orRestant(init(r,l,h))				= 16
	compteurAbandon(init(r,l,h))		= 0
	appartenance(init(r,l,h))			= r

[retrait]
	orRestant(retrait(H,s))				= orRestant(H) - s
	compteurAbandon(retrait(H,s))       = compteurAbandon(H)
	etat_d_appartenance(H)=ETAT.OCCUPE  => appartenance(retrait(H,s)) = appartenance(H)

[depot]
	orRestant(depot(H,d))				= orRestant(H) + d
	compteurAbandon(depot(H,d))			= compteurAbandon(H)
	appartenance(depot(H,d)) 			= appartenance(H)

[accueil]
	orRestant(accueil(H,r)) 		= orRestant(H)
	compteurAbandon(accueil(H,r)) 	= 0
	appartenance(accueil(H,r))		= r

[abandoned]
	orRestant(abandoned(H))				= orRestant(H)
	compteurAbandon(abandoned(H))		= compteurAbandon(H) + 1
	etat_d_appartenance(abandoned(H)) = ETAT.OCCUPE => appartenance(abandoned(H))  = appartenance(H)

