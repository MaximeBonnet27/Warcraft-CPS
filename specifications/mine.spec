service: Mine

types: int, enum ETAT{OCCUPE,LIBRE}, boolean, RACE{ORC, HUMAIN}

Observators:
    const largeur:          [Mine] -> int
    const hauteur:          [Mine] -> int
    orRestant:              [Mine] -> int
    estLaminee:             [Mine] -> boolean
    compteurAbandon:		[Mine] -> int
    etat_d_appartenance:    [Mine] -> ETAT
    appartenance:			[Mine] -> RACE
    	pre appartenance(M)
    		require etat_d_appartenance(M)=ETAT.OCCUPE 
    
Constructors:
	init: int x int -> [Mine]
		pre init(largeur,hauteur)
			require largeur>0 ^ hauteur>0

Operators:
	retrait: [Mine] x int -> [Mine]
		pre retrait(M,s)
			require orRestant(M)-s≥0 ^ s≥0

	accueil: [Mine] x RACE -> [Mine]
		pre accueil(M,race)
			require etat_d_appartenance(M)=ETAT.OCCUPE => race=appartenance(M)

	abandoned: [Mine] -> [Mine]
		pre abandoned(M)
			require etat_d_appartenance(M)=ETAT.LIBRE

Observations:
[Invariants]
	estLaminee(M) =(min)= (orRestant(M)=0)
	(etat_d_appartenance(M)=ETAT.LIBRE) =(min)= (compteurAbandon(M)=51)
	(etat_d_appartenance(M)=ETAT.OCCUPE) =(min)= (0≤compteurAbandon(M)<51)
	orRestant(M)≥0
	0≤compteurAbandon(M)≤51

[init]
	largeur(init(l,h))				= l
	hauteur(init(l,h))				= h
	orRestant(init(l,h))			= 500
	compteurAbandon(init(l,h))		= 51

[retrait]
	orRestant(retrait(M,s))				  = orRestant(M) - s
	compteurAbandon(retrait(M,s))         = compteurAbandon(M)
	etat_d_appartenance(M)=ETAT.OCCUPE => appartenance(retrait(M,s)) = appartenance(M)

[accueil]
	orRestant(accueil(M,r))				= orRestant(M)
	compteurAbandon(accueil(M,r))		= 0
	appartenance(accueil(M,r)) 			= r
	
[abandoned]
	orRestant(abandoned(M))				= orRestant(M)
	compteurAbandon(abandoned(M))		= compteurAbandon(M) + 1
	etat_d_appartenance(abandoned(M)) = ETAT.OCCUPE => appartenance(abandoned(M))  = appartenance(M)
	