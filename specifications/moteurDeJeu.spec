service: MoteurJeu

use: Villageois, HotelVille, Mine, Route, Muraille

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

	getMineVillageois: [MoteurJeur] -> Mine
		pre getMineVillageois(M,num) 
			require num ∈ numeroesVillageois(M,num) ^ Villageois::enCorvee(getVillageois(M,num))

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

    const numeroesRoute:         [MoteurJeu] -> Set<int>
    getRoute:                    [MoteurJeu] x int -> Route
		pre getRoute(M,num) 
			require num ∈ numeroesRoute(M,num)

    const positionRouteX:        [MoteurJeu] x int -> int
		pre positionRouteX(M,num)
			require num ∈ numeroesRoute(M,num) 

    const positionRouteY:        [MoteurJeu] x int -> int
		pre positionRouteY(M,num) 
			require num ∈ numeroesRoute(M,num)

    const numeroesMuraille:         [MoteurJeu] -> Set<int>
    getMuraille:                    [MoteurJeu] x int -> Murraille
		pre getMuraille(M,num) 
			require num ∈ numeroesMuraille(M,num)

    const positionMurailleX:        [MoteurJeu] x int -> int
		pre positionMurrailleX(M,num)
			require num ∈ numeroesMurraille(M,num) 

    const positionMurrailleY:        [MoteurJeu] x int -> int
		pre positionMurrailleY(M,num) 
			require num ∈ numeroesMurraille(M,num)

    hotelDeVilleA:              [MoteurJeu] -> HotelVille
    hotelDeVilleB:              [MoteurJeu] -> HotelVille
	getHotel:	[MoteurJeu] x int -> HotelVille
		pre getHotel(M,numHotel)
			require numHotel=0 V numHotel=1 

    const positionH	otelVilleXA: [MoteurJeu] -> int
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
	pasJeu : [MoteurJeu] x COMMANDE x Set<int> x int x COMMANDE x Set<int> x int -> [MoteurJeu]                   
		pre pasJeu(M,commmandJ1,numsVillgeoisJ1,argumentJ1,commmandJ2,numsVillgeoisJ2,argumentJ2) 
			require ¬estFini(M) ^
				Pour tout i dans numVillageoisJ1
					i ∈ numeroesVillageois(M) ^
					Villageois::race(getVillageois(i)) = RACE.HUMAIN ^
					(commandJ1=COMMANDE.DEPLACER => 0 ≤ argumentJ1 ≤ 360) ^
					(commandJ1=COMMANDE.ENTRERMINE => (argumentJ1 ∈ numeroesMines(M) ^ peutEntrer(M,i,argumentJ1))) ^
					(commandJ1=COMMANDE.ENTRERHOTELVILLE => ((argumentJ1 = 0 V argumentJ1 = 1) ^ peutEntrerHotelVille(M,i,argumentJ1))) ^

				Pour tout i dans numVillageoisJ2
					i ∈ numeroesVillageois(M,i) ^
					Villageois::race(getVillageois(i)) = RACE.ORC ^
					(commandJ2=COMMANDE.DEPLACER => 0 ≤ argumentJ2 ≤ 360) ^
					(commandJ2=COMMANDE.ENTRERMINE => (argumentJ2 ∈ numeroesMines(M) ^ peutEntrer(M,i,argumentJ2))) ^
					(commandJ2=COMMANDE.ENTRERHOTELVILLE => ((argumentJ2 = 0 V argumentJ2 = 1) ^ peutEntrerHotelVille(M,i,argumentJ2)))

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
											(HotelVille::orRestant(hotelDeVilleA(M)) < 1664 ^
											HotelVille::orRestant(hotelDeVilleB(M)) < 1664 ^
											HotelVille::etat_d_appartenance(hotelDeVilleA(M))=ETAT.OCCUPE ^
											HotelVille::etat_d_appartenance(hotelDeVilleB(M))=ETAT.OCCUPE ^
											¬(HotelVille::appartenance(hotelDeVilleA(M))=HotelVille::appartenance(hotelDeVilleB(M))))

	peutEntrer(M,numVillageois,numMine) =(min)= distance(positionVillageoisX(M,numVillageois),positionVillageoisY(M,numVillageois), positionMineX(M,numMine),positionMineY(M,numMine)) ≤ 51 ^
												(Mine::etat_d_appartenance(getMine(M,numMine)) = ETAT.OCCUPE => (Mine::appartenance(getMine(M,numMine)) = Villageois::race(getVillageois(numVillageois))))

	peutEntrerHotelVille(M,numVillageois,numHotel) =(min)=  (numHotel = 0) => 
															(distance(positionVillageoisX(M,numVillageois),positionVillageoisY(M,numVillageois), positionHotelVilleXA(M), positionHotelVilleYA(M)) ≤ 51 ^
															(HotelVille::etat_d_appartenance(hotelDeVilleA(M)) = ETAT.OCCUPE => (HotelVille::appartenance(hotelDeVilleA) = Villageois::race(getVillageois(numVillageois)))))
															^
															(numHotel = 1) => 
															((distance(positionVillageoisX(M,numVillageois),positionVillageoisY(M,numVillageois), positionHotelVilleXB(M), positionHotelVilleYB(M)) ≤ 51 ^
															(HotelVille::etat_d_appartenance(hotelDeVilleB(M)) = ETAT.OCCUPE => (HotelVille::appartenance(hotelDeVilleB) = Villageois::race(getVillageois(numVillageois))))))
	 getHotel(M,num_hotel) =(min)= (num_hotel= 0) => hotelDeVilleA(M)
 									^
									(num_hotel= 1) => hotelDeVilleB(M)

[init]
	largeurTerrain(init(l,h,m)) = l
	hauteurTerrain(init(l,h,m)) = h
	maxPasJeu(init(l,h,m))		= m
	pasJeuCourant(init(l,h,m))  = 0
	
	numeroesVillageois(init(l,h,m))  = {0,...,199}
	pour tout i dans numeroesVillageois(init(l,h,m)),
		si i < 100, 
			getVillageois(init(l,h,m), i) = Villageois::init(RACE.ORC, 3, 3, 20, 1, 50)
			^ (distance(positionVillageoisX(init(l,h,m),i),positionVillageoisY(init(l,h,m),i), positionHotelVilleXA(init(l,h,m)), positionHotelVilleYA(init(l,h,m))) ≤ 51
		sinon
			getVillageois(init(l,h,m), i) = Villageois::init(RACE.HUMAIN, 3, 3, 10, 1, 100)
			^ (distance(positionVillageoisX(init(l,h,m),i),positionVillageoisY(init(l,h,m),i), positionHotelVilleXB(init(l,h,m)), positionHotelVilleYB(init(l,h,m))) ≤ 51

	numeroesMines(init(l,h,m)) = {0,...,19}
	pour tout i dans numeroesMines(init(l,h,m)),
		getMine(init(l,h,m), i) = Mine::init(50,50)
		^ positionMineX(init(l,h,m), i) = random(largeurTerrain(init(l,h,m))
		^ positionMineY(init(l,h,m), i) = random(hauteurTerrain(init(l,h,m))

	numeroesRoute(init(l,h,m)) = {0,...,99}
	pour tout i dans numeroesRoute(init(l,h,m)),
		getRoute(init(l,h,m), i) = Route::init(5,5,2)
		^ positionRouteX(init(l,h,m), i) = random(largeurTerrain(init(l,h,m))
		^ positionRouteY(init(l,h,m), i) = random(hauteurTerrain(init(l,h,m))
	
	numeroesMurraille(init(l,h,m)) = {0,...,49}
	pour tout i dans numeroesMurraille(init(l,h,m)),
		getMurraille(init(l,h,m), i) = Murraille::init(5,5,10)
		^ positionMurrailleX(init(l,h,m), i) = random(largeurTerrain(init(l,h,m))
		^ positionMurrailleY(init(l,h,m), i) = random(hauteurTerrain(init(l,h,m))

	hotelDeVilleA = HotelDeVille::init(Race.ORC, 100, 100)
	hotelDeVilleB = HotelDeVille::init(Race.HUMAIN, 100, 100)
	
	positionHotelDeVilleXA(init(l,h,m)) = 0
	positionHotelDeVilleYA(init(l,h,m)) = 0
	
	positionHotelDeVilleXB(init(l,h,m)) = largeurTerrain(init(l,h,m)) - HotelDeVille::largeur(hotelDeVilleB) - 1
	positionHotelDeVilleYB(init(l,h,m)) = hauteurTerrain(init(l,h,m)) - HotelDeVille::hauteur(hotelDeVilleB) - 1

[pasJeu]
	//pasJeuCourant
	pasJeuCourant(pasJeu(M, c1, s1, a1, c2, s2, a2)) = pasJeuCourant(M) + 1

	pour tout i dans numeroesVillageois(pasJeu(M, c1, s1, a1, c2, s2, a2)) 
		// getVillageois
		getVillageois(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = getVillageois(M,i)

		// positionVillageoisX
   		// positionVillageoisY
		si i ∈ s1 ^ c1 = COMMANDE.DEPLACER
			alors
				si exist num_route telque positionRouteX(M,num_route) = positionVillageoisX(M) ^ positionRouteY(M,num_route) = positionVillageoisY(M)
					alors
					si exist num_muraille telque positionMurailleX(M,num_muraille) = positionVillageoisX(M) + cos(a1)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route)) 
						^ positionMurailleY(M,num_muraille) = positionVillageoisY(M) + cos(a1)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route))
						alors
						positionVillageoisX(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisX(M)
						positionVillageoisY(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisY(M)
					sinon alors
						positionVillageoisX(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisX(M) + cos(a1)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route))
						positionVillageoisY(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisY(M) + cos(a1)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route))
				sinon alors
	 				si exist num_muraille telque positionMurailleX(M,num_muraille) = positionVillageoisX(M) + cos(a1)*Villageois::vitesse(getVillageois(M,i))
						^ positionMurailleY(M,num_muraille) = positionVillageoisY(M) + cos(a1)*Villageois::vitesse(getVillageois(M,i))
						alors
						positionVillageoisX(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisX(M)
						positionVillageoisY(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisY(M)
					sinon alors
						positionVillageoisX(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisX(M) + cos(a1)*Villageois::vitesse(getVillageois(M,i))
						positionVillageoisY(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisY(M) + cos(a1)*Villageois::vitesse(getVillageois(M,i))

		sinon si i ∈ s2 ^ c2 = COMMANDE.DEPLACER
			alors
				si exist num_route telque positionRouteX(M,num_route) = positionVillageoisX(M) ^ positionRouteY(M,num_route) = positionVillageoisY(M)
					alors
					si exist num_muraille telque positionMurailleX(M,num_muraille) = positionVillageoisX(M) + cos(a2)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route)) 
						^ positionMurailleY(M,num_muraille) = positionVillageoisY(M) + cos(a2)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route))
						alors
						positionVillageoisX(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisX(M)
						positionVillageoisY(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisY(M)
					sinon alors
						positionVillageoisX(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisX(M) + cos(a2)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route))
						positionVillageoisY(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisY(M) + cos(a2)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route))
				sinon alors
	 				si exist num_muraille telque positionMurailleX(M,num_muraille) = positionVillageoisX(M) + cos(a2)*Villageois::vitesse(getVillageois(M,i))
						^ positionMurailleY(M,num_muraille) = positionVillageoisY(M) + cos(a1)*Villageois::vitesse(getVillageois(M,i))
						alors
						positionVillageoisX(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisX(M)
						positionVillageoisY(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisY(M)
					sinon alors
						positionVillageoisX(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisX(M) + cos(a2)*Villageois::vitesse(getVillageois(M,i))
						positionVillageoisY(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisY(M) + cos(a2)*Villageois::vitesse(getVillageois(M,i))

		sinon alors
			positionVillageoisX(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisX(M)
			positionVillageoisY(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = positionVillageoisY(M)

		// getMineVillageois
		si i ∈ s1 ^ c1 = COMMANDE.ENTRERMINE 
			alors
				peutEntrer(M,i,a1) => Villageois::commenceTravaille(getVillageois(M,i)) ^ Mine::accueil(getMine(M,a1),Villageois::race(getVillageois(M,i))) ^ getMineVillageois(pasJeu(M, c1, i, a1, c2, n2, a2), i) = a1
		
		si i ∈ s2 ^ c2 = COMMANDE.ENTRERMINE 
			alors
				peutEntrer(M,i,a2) => Villageois::commenceTravaille(getVillageois(M,i)) ^ Mine::accueil(getMine(M,a2),Villageois::race(getVillageois(M,i))) ^ getMineVillageois(pasJeu(M, c1, n1, a1, c2, i, a2), i) = a2
		
		sinon si Villageois::enCorvee(getVillageois(M,i))
			alors
				si Villageois::corveeFini(getVillageois(M,i))
				alors
					si ¬Mine::estLaminee(getMineVillageois(M,i))
					alors
						si exist n dans numeroesVillageois(M)/i telque  getMineVillageois(M, n) = getMineVillageois(M, i)
						alors
							Villageois::ajouteOr(getVillageois(M,i),1) ^ Mine::retrait(getMineVillageois(M, i), 1) ^ ¬Villageois::enCorvee(getVillageois(pasJeu(M, c1, s1, a1, c2, s2, a2),i))
					sinon alors
						Villageois::ajouteOr(getVillageois(M,i),1) ^ Mine::retrait(getMineVillageois(M, i), 1) ^ Mine::abandoned(getMineVillageois(M, i)) ^ ¬Villageois::enCorvee(getVillageois(pasJeu(M, c1, s1, a1, c2, s2, a2),i))						
				sinon alors
					si exist n dans numeroesVillageois(M)/i telque  getMineVillageois(M, n) = getMineVillageois(M, i)
						alors
							¬Villageois::enCorvee(getVillageois(pasJeu(M, c1, s1, a1, c2, s2, a2),i))
					sinon alors
						Mine::abandoned(getMineVillageois(M, i)) ^ ¬Villageois::enCorvee(getVillageois(pasJeu(M, c1, s1, a1, c2, s2, a2),i))
		sinon alors							 
				 Villageois::travaille(getVillageois(M,i))

		si i ∈ s1 ^ c1 = COMMANDE.ENTRERHOTELVILLE
			alors
				peutEntrerHotelVille(M,i,a1) => HotelDeVille::depot(getHotel(M,a1),Villageois::quantiteOr(getVillageois(M,i))^ Villageois::retraitOr(getVillageois(M,i),Villageois::quantiteOr(getVillageois(M,i)) ^ HotelDeVille::accueil(getHotel(M,a1),Villageois::race(getVillageois(M,i))) ^ HotelDeVille::abandoned(getHotel(M,a1),Villageois::race(getVillageois(M,i)))
		
		si i ∈ s2 ^ c2 = COMMANDE.ENTRERHOTELVILLE
			alors
				peutEntrerHotelVille(M,i,a2) => HotelDeVille::depot(getHotel(M,a2),Villageois::quantiteOr(getVillageois(M,i))^ Villageois::retraitOr(getVillageois(M,i),Villageois::quantiteOr(getVillageois(M,i)) ^ HotelDeVille::accueil(getHotel(M,a2),Villageois::race(getVillageois(M,i))) ^ HotelDeVille::abandoned(getHotel(M,a2),Villageois::race(getVillageois(M,i)))

	// getMine
	pour tout i dans  numeroesMine(pasJeu(M, c1, s1, a1, c2, s2, a2))
		getMine(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = getMine(M,i)

   // getRoute
	pour tout i dans  numeroesRoute(pasJeu(M, c1, s1, a1, c2, s2, a2))
		getRoute(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = getRoute(M,i)

   // getMuraille
	pour tout i dans  numeroesMuraile(pasJeu(M, c1, s1, a1, c2, s2, a2))
		getMuraille(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = getMuraille(M,i)

	// hotelDeVilleA
		hotelDeVilleA(pasJeu(M, c1, s1, a1, c2, s2, a2))=hotelDeVilleA(M)

    //hotelDeVilleB
		hotelDeVilleB(pasJeu(M, c1, s1, a1, c2, s2, a2))=hotelDeVilleB(M)





