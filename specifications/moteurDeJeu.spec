service: MoteurJeu

use: Villageois, HotelVille, Mine

types: enum RACE{ORC, HUMAIN}, enum RESULTAT{ORC, HUMAIN, NULL}, enum COMMANDE{RIEN, DEPLACER, ENTRERMINE, ENTRERHOTELVILLE}, enum ETAT{OCCUPE,LIBRE}, int, boolean

Observators:                    
    const largeurTerrain:       [MoteurJeu] -> int
    const hauteurTerrain:       [MoteurJeu] -> int
    const maxPasJeu:            [MoteurJeu] -> int
    pasJeuCourant:              [MoteurJeu] -> int
    estFini:                    [MoteurJeu] -> boolean
    resultatFinal:              [MoteurJeu] -> RESULTAT
		pre resultatFinal(M) 
			require estFini(M)
    const numeroesVillageois:   [MoteurJeu] -> Set<int>
    getVillageois:              [MoteurJeu] x int -> Villageois
		pre getVillageois(M,num) 
			require num ∈ numeroesVillageois(M,num) 
    positionVillageoisX:        [MoteurJeu] x int -> int
		pre positionVillageoisX(M,num) 
			require num ∈ numeroesVillageois(M,num)
    positionVillageoisY:        [MoteurJeu] x int -> int
		pre positionVillageoisY(M,num) 
			require num ∈ numeroesVillageois(M,num) 
    const numeroesMine:         [MoteurJeu] -> Set<int>
    getMine:                    [MoteurJeu] x int -> Mine
		pre getMine(M,num) 
			require num ∈ numeroesMine(M,num)
    const positionMineX:        [MoteurJeu] x int -> int
		pre positionMineX(M,num)
			require num ∈ numeroesMine(M,num) 
    const positionMineY:        [MoteurJeu] x int -> int
		pre positionMineY(M,num) 
			require num ∈ numeroesMine(M,num)
    hotelDeVilleA:              [MoteurJeu] -> HotelVille
    hotelDeVilleB:              [MoteurJeu] -> HotelVille
    const positionHotelVilleXA: [MoteurJeu] -> int
    const positionHotelVilleYA: [MoteurJeu] -> int
    const positionHotelVilleXB: [MoteurJeu] -> int
    const positionHotelVilleYB: [MoteurJeu] -> int
    peutEntrer:                 [MoteurJeu] x int x int -> boolean
		pre peutEntrer(M,numVillageois,numMine)
			require numVillageois ∈ numeroesVillageois(M,numVillageois)
					^ numMine ∈ numeroesMine(M,numMine) 
    peutEntrerHotelVille:     	[MoteurJeu] x int x int -> boolean
		pre peutEntrerHotelVille(M,numVillageois,numHotel)
			require numVillageois ∈ numeroesVillageois(M,numVillageois) ^ (numHotel = 0 V numHotel = 1)

Constructors:                   
	init : int x int x int -> [MoteurJeu]
		pre init(largeur,hauteur,maxPas) 
			require largeur ≥ 600 ^ hauteur ≥ 400 ^ maxPas ≥ 0

Operators:  
	pasJeu : [MoteurJeu] x COMMANDE x int x int x COMMANDE x int x int -> [MoteurJeu]                    
	pre pasJeu(M,commmandJ1,numVillgeoisJ1,argumentJ1,commmandJ2,numVillgeoisJ2,argumentJ2) 
		require ¬estFini(M) ^
				numVillageoisJ1 ∈ numeroesVillageois(M,numVillageoisJ1) ^
				Villageois::race(getVillageois(numVillageoisJ1)) = RACE.HUMAIN ^
				numVillageoisJ2 ∈ numeroesVillageois(M,numVillageoisJ2) ^
				Villageois::race(getVillageois(numVillageoisJ2)) = RACE.ORC ^
				(commandJ1=COMMANDE.DEPLACER => 0 ≤ argumentJ1 ≤ 360) ^
				(commandJ1=COMMANDE.ENTRERMINE => (argumentJ1 ∈ numeroesMines(M) ^ peutEntrer(M,numVillageoisJ1,argumentJ1))) ^
				(commandJ1=COMMANDE.ENTRERHOTELVILLE => ((argumentJ1 = 0 V argumentJ1 = 1) ^ peutEntrerHotelVille(M,numVillageois,argumentJ1))) ^
				(commandJ2=COMMANDE.DEPLACER => 0 ≤ argumentJ2 ≤ 360) ^
				(commandJ2=COMMANDE.ENTRERMINE => (argumentJ2 ∈ numeroesMines(M) ^ peutEntrer(M,numVillageoisJ2,argumentJ2))) ^
				(commandJ2=COMMANDE.ENTRERHOTELVILLE => ((argumentJ2 = 0 V argumentJ2 = 1) ^ peutEntrerHotelVille(M,numVillageoisJ2,argumentJ2)))

Observations:                   
[Invariants]
	0 ≤ pasJeuCourant(M) ≤ maxPasJeu(M)

	estFini(M) =(min)= 	HotelVille::orRestant(hotelDeVilleA(M)) ≥ 1664 V
						HotelVille::orRestant(hotelDeVilleB(M)) ≥ 1664 V
						(HotelVille::etat_d_appartenance(hotelDeVilleA(M)) = ETAT.OCCUPE ^
						HotelVille::etat_d_appartenance(hotelDeVilleB(M)) = ETAT.OCCUPE ^
						HotelVille::appartenance(hotelDeVilleA(M)) = HotelVille::appartenance(hotelDeVilleB(M))) V
						pasJeuCourant(M) = maxPasJeu(M)

	resultatFinal(M)=RESULTAT.ORC =(min)= 	(HotelVille::orRestant(hotelDeVilleA(M)) ≥ 1664 ^
											HotelVille::etat_d_appartenance(hotelDeVilleA(M)) = ETAT.OCCUPE ^
											HotelVille::appartenance(hotelDeVilleA(M)) = RACE.ORC) V
											(HotelVille::orRestant(hotelDeVilleB(M)) ≥ 1664 ^
											HotelVille::etat_d_appartenance(hotelDeVilleB(M)) = ETAT.OCCUPE ^
											HotelVille::appartenance(hotelDeVilleB(M)) = RACE.ORC) V
											(HotelVille::etat_d_appartenance(hotelDeVilleA(M)) = ETAT.OCCUPE ^
											HotelVille::etat_d_appartenance(hotelDeVilleB(M)) = ETAT.OCCUPE ^
											HotelVille::appartenance(hotelDeVilleA(M)) = RACE.ORC ^
											HotelVille::appartenance(hotelDeVilleB(M)) = RACE.ORC)

	resultatFinal(M)=RESULTAT.HUMAIN =(min)= 	(HotelVille::orRestant(hotelDeVilleA(M)) ≥ 1664 ^
												HotelVille::etat_d_appartenance(hotelDeVilleA(M)) = ETAT.OCCUPE ^
												HotelVille::appartenance(hotelDeVilleA(M)) = RACE.HUMAIN) V
												(HotelVille::orRestant(hotelDeVilleB(M)) ≥ 1664 ^
												HotelVille::etat_d_appartenance(hotelDeVilleB(M)) = ETAT.OCCUPE ^
												HotelVille::appartenance(hotelDeVilleB(M)) = RACE.HUMAIN) V
												(HotelVille::etat_d_appartenance(hotelDeVilleA(M)) = ETAT.OCCUPE ^
												HotelVille::etat_d_appartenance(hotelDeVilleB(M)) = ETAT.OCCUPE ^
												HotelVille::appartenance(hotelDeVilleA(M)) = RACE.HUMAIN ^
												HotelVille::appartenance(hotelDeVilleB(M)) = RACE.HUMAIN)

	resultatFinal(M)=RESULTAT.NULL =(min)= 	pasJeuCourant(M) = maxPasJeu(M) V
											(HotelVille::orRestant(hotelDeVilleA(M))≥1664 ^
											HotelVille::orRestant(hotelDeVilleB(M))≥1664 ^
											HotelVille::etat_d_appartenance(hotelDeVilleA(M))=ETAT.OCCUPE ^
											HotelVille::etat_d_appartenance(hotelDeVilleB(M))=ETAT.OCCUPE ^
											¬(HotelVille::appartenance(hotelDeVilleA(M))=HotelVille::appartenance(hotelDeVilleB(M))))

	peutEntrer(M,numVillageois,numMine) =(min)= distance(positionVillageoisX(M,numVillageois),positionVillageoisY(M,numVillageois), positionMineX(M,numMine),positionMineY(M,numMine)) ≤ 51 ^
												(Mine::etat_d_appartenance(getMine(M,numMine)) = ETAT.OCCUPE => (Mine::appartenance(getMine(M,numMine)) = Villageois::race(getVillageois(numVillageois))))

	peutEntrerHotelVille(M,numVillageois,numHotel) =(min)=  numHotel = 0 => 
															(distance(positionVillageoisX(M,numVillageois),positionVillageoisY(M,numVillageois), positionHotelVilleXA(M), positionHotelVilleYA(M)) ≤ 51 ^
															(HotelVille::etat_d_appartenance(hotelDeVilleA(M)) = ETAT.OCCUPE => (HotelVille::appartenance(hotelDeVilleA) = Villageois::race(getVillageois(numVillageois)))))
															^
															numHotel = 1 => 
															((distance(positionVillageoisX(M,numVillageois),positionVillageoisY(M,numVillageois), positionHotelVilleXB(M), positionHotelVilleYB(M)) ≤ 51 ^
															(HotelVille::etat_d_appartenance(hotelDeVilleB(M)) = ETAT.OCCUPE => (HotelVille::appartenance(hotelDeVilleB) = Villageois::race(getVillageois(numVillageois))))))
	
[init]
	largeurTerrain(init(l,h,m)) = l
	hauteurTerrain(init(l,h,m)) = h
	maxPasJeu(init(l,h,m))		= m
	pasJeuCourant(init(l,h,m))  = 0
	numVillageois(init(l,h,m))  = {0,...,199}





