service: MoteurJeu

use: Villageois, HotelVille, Mine, Route, Muraille, CentreNationalRechercheSpeciale, GestionCombat

types: enum RACE{ORC, HUMAIN}, enum RESULTAT{ORC, HUMAIN, NULL}, enum COMMANDE{RIEN, DEPLACER, ENTRERMINE, ENTRERHOTELVILLE, CONSTRUIRECNRS, RECHERCHECNRS,APPLIQUER_RECHERCHE, ATTAQUER}, enum ETAT{OCCUPE,LIBRE}, int, boolean

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
			require num ∈ numeroesVillageois(M) 

    positionVillageoisX:        [MoteurJeu] x int -> int
		pre positionVillageoisX(M,num) 
			require num ∈ numeroesVillageois(M)

    positionVillageoisY:        [MoteurJeu] x int -> int
		pre positionVillageoisY(M,num) 
			require num ∈ numeroesVillageois(M) 

	getMineVillageois: [MoteurJeur] -> Mine
		pre getMineVillageois(M,num) 
			require num ∈ numeroesVillageois(M) ^ Villageois::enCorvee(getVillageois(M,num))

    const numeroesMine:         [MoteurJeu] -> Set<int>
    getMine:                    [MoteurJeu] x int -> Mine
		pre getMine(M,num) 
			require num ∈ numeroesMine(M)

    const positionMineX:        [MoteurJeu] x int -> int
		pre positionMineX(M,num)
			require num ∈ numeroesMine(M) 

    const positionMineY:        [MoteurJeu] x int -> int
		pre positionMineY(M,num) 
			require num ∈ numeroesMine(M)

    const numeroesRoute:         [MoteurJeu] -> Set<int>
    getRoute:                    [MoteurJeu] x int -> Route
		pre getRoute(M,num) 
			require num ∈ numeroesRoute(M)

    const positionRouteX:        [MoteurJeu] x int -> int
		pre positionRouteX(M,num)
			require num ∈ numeroesRoute(M) 

    const positionRouteY:        [MoteurJeu] x int -> int
		pre positionRouteY(M,num) 
			require num ∈ numeroesRoute(M)

    const numeroesMuraille:         [MoteurJeu] -> Set<int>
    getMuraille:                    [MoteurJeu] x int -> Murraille
		pre getMuraille(M,num) 
			require num ∈ numeroesMuraille(M)

    const positionMurailleX:        [MoteurJeu] x int -> int
		pre positionMurrailleX(M,num)
			require num ∈ numeroesMurraille(M) 

    const positionMurrailleY:        [MoteurJeu] x int -> int
		pre positionMurrailleY(M,num) 
			require num ∈ numeroesMurraille(M)

	villageoisSurRoute: [MoteurJeu] x int x int x int x int x int -> boolean
		pre villageoisSurRoute(M,x,y,l,h,r)
			require r ∈ numeroesRoute(M)

	villageoisSurMuraille: [MoteurJeu] x int x int x int x int x int -> boolean
		pre villageoisSurMuraille(M,x,y,l,h,m)
			require m ∈ numeroesMuraille(M)

    hotelDeVilleA:              [MoteurJeu] -> HotelVille
    hotelDeVilleB:              [MoteurJeu] -> HotelVille
	getHotel:	[MoteurJeu] x int -> HotelVille
		pre getHotel(M,numHotel)
			require numHotel=0 V numHotel=1 

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

	
	CNRSA: [MoteurJeu] -> CentreNationalRechercheSpeciale
	CNRSB: [MoteurJeu] -> CentreNationalRechercheSpeciale

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
					(commandJ1=COMMANDE.CONSTRUIRECNRS => ((argumentJ1 = 0 V argumentJ1 = 1) ^ HotelDeVille::etat_d_appartenance(getHotel(M, argumentJ1)) == ETAT.OCCUPE ^ HotelDeVille::appartenance(getHotel(M, argumentJ1)) == Villageois::race(getVillageois(M, i)))
					(commandJ1=COMMANDE.RECHERCHECNRS => ((argumentJ1 = 0 V argumentJ1 = 1) ^ HotelDeVille::etat_d_appartenance(getHotel(M, argumentJ1)) == ETAT.OCCUPE ^ HotelDeVille::appartenance(getHotel(M, argumentJ1)) == Villageois::race(getVillageois(M, i)))
					(commandJ1=COMMANDE.APPLIQUER_RECHERCHE =>((argumentJ1 = 0 V argumentJ1 = 1) ^ HotelDeVille::etat_d_appartenance(getHotel(M, argumentJ1)) == ETAT.OCCUPE ^ HotelDeVille::appartenance(getHotel(M, argumentJ1)) == Villageois::race(getVillageois(M, i)))
					(commandJ1=COMMANDE.ATTAQUER => (100≤argumentJ1≤199))

				Pour tout i dans numVillageoisJ2
					i ∈ numeroesVillageois(M,i) ^
					Villageois::race(getVillageois(i)) = RACE.ORC ^
					(commandJ2=COMMANDE.DEPLACER => 0 ≤ argumentJ2 ≤ 360) ^
					(commandJ2=COMMANDE.ENTRERMINE => (argumentJ2 ∈ numeroesMines(M) ^ peutEntrer(M,i,argumentJ2))) ^
					(commandJ2=COMMANDE.ENTRERHOTELVILLE => ((argumentJ2 = 0 V argumentJ2 = 1) ^ peutEntrerHotelVille(M,i,argumentJ2)))
					(commandJ2=COMMANDE.CONSTRUIRECNRS => ((argumentJ2 = 0 V argumentJ2 = 1) ^ HotelDeVille::etat_d_appartenance(getHotel(M, argumentJ2)) == ETAT.OCCUPE ^ HotelDeVille::appartenance(getHotel(M, argumentJ2)) == Villageois::race(getVillageois(M, i)))
					(commandJ2=COMMANDE.RECHERCHECNRS => ((argumentJ2 = 0 V argumentJ2 = 1) ^ HotelDeVille::etat_d_appartenance(getHotel(M, argumentJ2)) == ETAT.OCCUPE ^ HotelDeVille::appartenance(getHotel(M, argumentJ2)) == Villageois::race(getVillageois(M, i)))
					(commandJ2=COMMANDE.APPLIQUER_RECHERCHE =>((argumentJ2 = 0 V argumentJ2 = 1) ^ HotelDeVille::etat_d_appartenance(getHotel(M, argumentJ2)) == ETAT.OCCUPE ^ HotelDeVille::appartenance(getHotel(M, argumentJ2)) == Villageois::race(getVillageois(M, i)))
					(commandJ12=COMMANDE.ATTAQUER => (0≤argumentJ2≤99))
					


Observations:                   
[Invariants]
	0 ≤ pasJeuCourant(M) ≤ maxPasJeu(M)

	estFini(M) =(min)= 	(HotelVille::orRestant(hotelDeVilleA(M)) ≥ 1664 ^ HotelVille::etat_d_appartenance(hotelDeVilleA(M)) = ETAT.OCCUPE) V
						(HotelVille::orRestant(hotelDeVilleB(M)) ≥ 1664 ^ HotelVille::etat_d_appartenance(hotelDeVilleB(M)) = ETAT.OCCUPE) V
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

villageoisSurRoute(M,x,y,l,h,r) =(min)= collision(x,y,l,h,positionRouteX(M,r),positionRouteY(M,r),Route::largeur(getRoute(M,r)),Route::hauteur(getRoute(M,r)))
villageoisSurMuraille(M,x,y,l,h,m) =(min)= collision(x,y,l,h,positionMurailleX(M,m),positionMurailleY(M,v),Muraille::largeur(getMuraille(M,v)),Muraille::hauteur(getMuraille(M,v)))
			
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

	CNRSA(init(l,h,m)) = CentreNationalRechercheSpeciale::init(100, 25, 500, 100)
	CNRSB(init(l,h,m)) = CentreNationalRechercheSpeciale::init(100, 25, 500, 100)

[pasJeu]
	//pasJeuCourant
	pasJeuCourant(pasJeu(M, c1, s1, a1, c2, s2, a2)) = pasJeuCourant(M) + 1

	pour tout i dans numeroesVillageois(M):
		// getVillageois
		getVillageois(pasJeu(M, c1, s1, a1, c2, s2, a2),i) =
			si Villageois::estMort(getVillageois(M,i)), alors
				getVillageois(M,i)
			
			sinon si i ∈ s1, alors
				si c1 = COMMANDE.ENTRERMINE, alors
					peutEntrer(M,i,a1) => Villageois::commenceTravaille(getVillageois(M,i))
					¬peutEntrer(M,i,a1)=> getVillageois(M,i)

				sinon si c1 = COMMANDE.ENTRERHOTELVILLE, alors
					peutEntrerHotelVille(M,i,a1) => Villageois::retraitOr(getVillageois(M,i),Villageois::quantiteOr(getVillageois(M,i))
					¬peutEntrerHotelVille(M,i,a1) => getVillageois(M,i)

				sinon si c1= COMMANDE.APPLIQUER_RECHERCHE, alors
					si getHotel(M, a1) == hotelDeVilleA(M) ^ HotelDeVille::etat_d_appartenance(getHotel(M, a1)) == ETAT.OCCUPE ^ HotelDeVille::appartenance(getHotel(M, a1)) == Villageois::race(getVillageois(M, s1[0]) ^ CentreNationalRechercheSpeciale::rechercheFinie(CNRSA(M)), alors
						Villageois::amelioration(getVillageois(M,i),CentreNationalRechercheSpeciale::competenceAugmente(CNRSA(M)),CentreNationalRechercheSpeciale::boost(CNRSA(M)))

					sinon si getHotel(M, a1) == hotelDeVilleB(M) ^ HotelDeVille::etat_d_appartenance(getHotel(M, a1)) == ETAT.OCCUPE ^ HotelDeVille::appartenance(getHotel(M, a1)) == Villageois::race(getVillageois(M, s1[0]) ^ CentreNationalRechercheSpeciale::rechercheFinie(CNRSB(M)), alors
						Villageois::amelioration(getVillageois(M,i),CentreNationalRechercheSpeciale::competenceAugmente(CNRSB(M)),CentreNationalRechercheSpeciale::boost(CNRSB(M)))	
					sinon, alors
						getVillageois(M,i)

				sinon si c1= COMMANDE.ATTAQUER, alors
						GestionCombat::attaquant(GestionCombat::combat(GestionCombat::init(getVillageois(M,i),getVillageois(M,a1))))

				sinon si c2 = COMMANDE.ATTAQUER ^ i == a2 alors
					GestionCombat::defenseur(GestionCombat::combat(GestionCombat::initMultiple(s2,getVillageois(M,i))))
				sinon alors
					getVillageois(M,i)
				
			sinon si i ∈ s2, alors
				si c2 = COMMANDE.ENTRERMINE, alors
					peutEntrer(M,i,a2) => Villageois::commenceTravaille(getVillageois(M,i))
					¬peutEntrer(M,i,a2)=> getVillageois(M,i)

				sinon si c2 = COMMANDE.ENTRERHOTELVILLE, alors
					peutEntrerHotelVille(M,i,a2) => Villageois::retraitOr(getVillageois(M,i),Villageois::quantiteOr(getVillageois(M,i))
					¬peutEntrerHotelVille(M,i,a2) => getVillageois(M,i)

				sinon si c2= COMMANDE.APPLIQUER_RECHERCHE, alors
					si getHotel(M, a2) == hotelDeVilleA(M) ^ HotelDeVille::etat_d_appartenance(getHotel(M, a2)) == ETAT.OCCUPE ^ HotelDeVille::appartenance(getHotel(M, a2)) == Villageois::race(getVillageois(M, s2[0]) ^ CentreNationalRechercheSpeciale::rechercheFinie(CNRSA(M)), alors
						Villageois::amelioration(getVillageois(M,i),CentreNationalRechercheSpeciale::competenceAugmente(CNRSA(M)),CentreNationalRechercheSpeciale::boost(CNRSA(M)))

					sinon si getHotel(M, a2) == hotelDeVilleB(M) ^ HotelDeVille::etat_d_appartenance(getHotel(M, a2)) == ETAT.OCCUPE ^ HotelDeVille::appartenance(getHotel(M, a2)) == Villageois::race(getVillageois(M, s2[0]) ^ CentreNationalRechercheSpeciale::rechercheFinie(CNRSB(M)), alors
						Villageois::amelioration(getVillageois(M,i),CentreNationalRechercheSpeciale::competenceAugmente(CNRSB(M)),CentreNationalRechercheSpeciale::boost(CNRSB(M)))	
					sinon, alors
						getVillageois(M,i)
		
				sinon si c2= COMMANDE.ATTAQUER, alors
						GestionCombat::attaquant(GestionCombat::combat(GestionCombat::init(getVillageois(M,i),getVillageois(M,a2))))

				sinon si c1 = COMMANDE.ATTAQUER ^ i == a1 alors
					GestionCombat::defenseur(GestionCombat::combat(GestionCombat::initMultiple(S,getVillageois(M,i)))), avec pour tout v dans S il existe j dans s1, getVillagois(M,j)=v
				
				sinon alors
					getVillageois(M,i)
	
			sinon si Villageois::enCorvee(getVillageois(M,i)), alors
				si Villageois::corveeFini(getVillageois(M,i)), alors
					si ¬Mine::estLaminee(getMineVillageois(M,i)), alors
							Villageois::ajouteOr(Villageois::finirTravail(getVillageois(M,i)),1)
					sinon alors
						Villageois::finirTravail(getVillageois(M,i)						
				sinon
					 Villageois::travail(getVillageois(M,i))
			sinon
				getVillageois(M,i)

		// positionVillageoisX
		positionVillageoisX(pasJeu(M, c1, s1, a1, c2, s2, a2),i)=
			si i ∈ s1 ^ c1 = COMMANDE.DEPLACER, alors
				si exist num_route telque villageoisSurRoute(M,positionVillageoisX(M,i),positionVillageoisY(M,i),Villageois::largeur(getVillageois(M,i)), Villageois::hauteur(getVillageois(M,i),num_route), alors
					si exist num_muraille telque villageoisSurMuraille(M,positionVillageoisX(M,i) + cos(a1)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route)), positionVillageoisY(M,i) + sin(a1)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route)),Villageois::largeur(getVillageois(M,i)), Villageois::hauteur(getVillageois(M,i),num_muraille), alors
						positionVillageoisX(M)
					sinon alors
						positionVillageoisX(M) + cos(a1)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route))
				sinon alors
	 				si exist num_muraille telque villageoisSurMuraille(M,positionVillageoisX(M,i) + cos(a1)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route)), positionVillageoisY(M,i) + sin(a1)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route)),Villageois::largeur(getVillageois(M,i)), Villageois::hauteur(getVillageois(M,i),num_muraille), alors
						positionVillageoisX(M,i)
					sinon alors
						positionVillageoisX(M,i) + cos(a1)*Villageois::vitesse(getVillageois(M,i))
	
		// positionVillageoisY
		positionVillageoisY(pasJeu(M, c1, s1, a1, c2, s2, a2),i)=
			si i ∈ s1 ^ c1 = COMMANDE.DEPLACER, alors
				si exist num_route telque villageoisSurRoute(M,positionVillageoisX(M,i),positionVillageoisY(M,i),Villageois::largeur(getVillageois(M,i)), Villageois::hauteur(getVillageois(M,i),num_route), alors
					si exist num_muraille telque villageoisSurMuraille(M,positionVillageoisX(M,i) + cos(a1)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route)), positionVillageoisY(M,i) + sin(a1)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route)),Villageois::largeur(getVillageois(M,i)), Villageois::hauteur(getVillageois(M,i),num_muraille), alors
						positionVillageoisY(M)
					sinon alors
						positionVillageoisY(M) + sin(a1)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route))
				sinon alors
	 				si exist num_muraille telque villageoisSurMuraille(M,positionVillageoisX(M,i) + cos(a1)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route)), positionVillageoisY(M,i) + sin(a1)*Villageois::vitesse(getVillageois(M,i))*Route::multiplicateur(getRoute(m,num_route)),Villageois::largeur(getVillageois(M,i)), Villageois::hauteur(getVillageois(M,i),num_muraille), alors
						positionVillageoisY(M,i)
					sinon alors
						positionVillageoisY(M,i) + sin(a1)*Villageois::vitesse(getVillageois(M,i))
	

		// getMineVillageois
		getMineVillageois(pasJeu(M, c1, s1, a1, c2, s2, a2), i)=
			si i ∈ s1 ^ c1 = COMMANDE.ENTRERMINE, alors
				peutEntrer(M,i,a1) => Mine::accueil(getMine(M,a1),Villageois::race(getVillageois(M,i)))
				¬peutEntrer(M,i,a1) => getMine(M,a1)

			sinon si i ∈ s2 ^ c2 = COMMANDE.ENTRERMINE, alors
				peutEntrer(M,i,a2) => Mine::accueil(getMine(M,a2),Villageois::race(getVillageois(M,i)))
				¬peutEntrer(M,i,a2) => getMine(M,a2)

			sinon si Villageois::enCorvee(getVillageois(M,i)), alors
				si Villageois::corveeFini(getVillageois(M,i)), alors
					si ¬Mine::estLaminee(getMineVillageois(M,i)), alors
						si exist n dans numeroesVillageois(M)/i telque  getMineVillageois(M, n) = getMineVillageois(M, i), alors
							Mine::retrait(getMineVillageois(M, i), 1)
						sinon, alors
							Mine::abandoned(Mine::retrait(getMineVillageois(M, i), 1))				
					sinon, alors
						si exist n dans numeroesVillageois(M)/i telque  getMineVillageois(M, n) = getMineVillageois(M, i), alors
							getMineVillageois(M, i)
						sinon, alors
							Mine::abandoned(getMineVillageois(M, i))



	// getMine
	pour tout i dans  numeroesMine(pasJeu(M, c1, s1, a1, c2, s2, a2))
		getMine(pasJeu(M, c1, s1, a1, c2, s2, a2),i) =
			si Mine:etat_d_appartenance(getMine(M,i))==ETAT.OCCUPE, alors
				pour tout num dans numeroesVillageois(M):
			 		si getMineVillageois(M,num) = getMine(M,i), alors
						 si Villageois::enCorvee(getVillageois(M,num)) ^ Villageois::corveeFini(getVillageois(M,num)), alors
							si ¬Mine::estLaminee(getMineVillageois(M,num)), alors
								si exist n dans numeroesVillageois(M)/i telque  getMineVillageois(M, num) = getMineVillageois(M, n), alors
									Mine::retrait(getMine(M, i), 1)
								sinon, alors
									Mine::abandoned(Mine::retrait(getMine(M, i), 1))				
							sinon, alors
								si exist n dans numeroesVillageois(M)/i telque  getMineVillageois(M, num) = getMineVillageois(M, n), alors
									getMine(M, i)
								sinon, alors
									Mine::abandoned(getMine(M, i)
						sinon, alors
							getMine(M,i)
			sinon, alors
				getMine(M, i)

	// getRoute
		pour tout i dans  numeroesRoute(pasJeu(M, c1, s1, a1, c2, s2, a2))
			getRoute(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = getRoute(M,i)

   	// getMuraille
		pour tout i dans  numeroesMuraile(pasJeu(M, c1, s1, a1, c2, s2, a2))
			getMuraille(pasJeu(M, c1, s1, a1, c2, s2, a2),i) = getMuraille(M,i)


	// hotelDeVilleA
		hotelDeVilleA(pasJeu(M, c1, s1, a1, c2, s2, a2))=
			si HotelDeVille::etat_d_appartenance(getHotel(M, a1)) == ETAT.OCCUPE, alors
				si HotelDeVille::appartenance(getHotel(M, a1)) == Villageois::race(getVillageois(M, s1[0]),alors
					si c1 = COMMANDE.CONSTRUIRECNRS ^ ¬CentreNationalRechercheSpeciale::enConstruction(CNRSA(M)) ^ HotelDeVille::orRestant(getHotel(M, a1)) >= CentreNationalRechercheSpeciale::prixConstruction(CNRSA(M)) ^ (getHotel(M, a1) == hotelDeVilleA(M)),alors
						HotelDeVille::abandoned(HotelDeVille::retrait(hotelDeVilleA(M), CentreNationalRechercheSpeciale::prixConstruction(CNRSA(M))))

					sinon si c1 = COMMANDE.RECHERCHECNRS ^ ¬CentreNationalRechercheSpeciale::enRecherche(CNRSA(M))^ CentreNationalRechercheSpeciale::constructionFinie(CNRSA(M))^ HotelDeVille::orRestant(getHotel(M, a1)) >= CentreNationalRechercheSpeciale::prixRecherche(CNRSA(M)) ^ (getHotel(M, a1) == hotelDeVilleA(M)), alors
 						HotelDeVille::abandoned(HotelDeVille::retrait(hotelDeVilleA(M), CentreNationalRechercheSpeciale::prixRecherche(CNRSA(M))))
				
				sinon si HotelDeVille::appartenance(getHotel(M, a2)) == Villageois::race(getVillageois(M, s2[0]),alors
					si c2 = COMMANDE.CONSTRUIRECNRS ^ ¬CentreNationalRechercheSpeciale::enConstruction(CNRSA(M)) ^ HotelDeVille::orRestant(getHotel(M, a2)) >= CentreNationalRechercheSpeciale::prixConstruction(CNRSA(M)) ^ (getHotel(M, a2) == hotelDeVilleA(M)),alors
						HotelDeVille::abandoned(HotelDeVille::retrait(hotelDeVilleA(M), CentreNationalRechercheSpeciale::prixConstruction(CNRSA(M))))

					sinon si c2 = COMMANDE.RECHERCHECNRS ^ ¬CentreNationalRechercheSpeciale::enRecherche(CNRSA(M))^ CentreNationalRechercheSpeciale::constructionFinie(CNRSA(M))^ HotelDeVille::orRestant(getHotel(M, a2)) >= CentreNationalRechercheSpeciale::prixRecherche(CNRSA(M)) ^ (getHotel(M, a2) == hotelDeVilleA(M)), alors
						HotelDeVille::abandoned(HotelDeVille::retrait(hotelDeVilleA(M), CentreNationalRechercheSpeciale::prixRecherche(CNRSA(M))))
			sinon, alors
				hotelDeVilleA(M)
			

    		// hotelDeVilleB
		hotelDeVilleB(pasJeu(M, c1, s1, a1, c2, s2, a2))=
			si HotelDeVille::etat_d_appartenance(getHotel(M, a1)) == ETAT.OCCUPE, alors
				si HotelDeVille::appartenance(getHotel(M, a1)) == Villageois::race(getVillageois(M, s1[0]),alors
					si c1 = COMMANDE.CONSTRUIRECNRS ^ ¬CentreNationalRechercheSpeciale::enConstruction(CNRSB(M)) ^ HotelDeVille::orRestant(getHotel(M, a1)) >= CentreNationalRechercheSpeciale::prixConstruction(CNRSB(M)) ^ (getHotel(M, a1) == hotelDeVilleB(M)),alors
						HotelDeVille::abandoned(HotelDeVille::retrait(hotelDeVilleB(M), CentreNationalRechercheSpeciale::prixConstruction(CNRSB(M))))

					sinon si c1 = COMMANDE.RECHERCHECNRS ^ ¬CentreNationalRechercheSpeciale::enRecherche(CNRSB(M))^ CentreNationalRechercheSpeciale::constructionFinie(CNRSB(M))^ HotelDeVille::orRestant(getHotel(M, a1)) >= CentreNationalRechercheSpeciale::prixRecherche(CNRSB(M)) ^ (getHotel(M, a1) == hotelDeVilleB(M)), alors
 						HotelDeVille::abandoned(HotelDeVille::retrait(hotelDeVilleB(M), CentreNationalRechercheSpeciale::prixRecherche(CNRSB(M))))
				
				sinon si HotelDeVille::appartenance(getHotel(M, a2)) == Villageois::race(getVillageois(M, s2[0]),alors
					si c2 = COMMANDE.CONSTRUIRECNRS ^ ¬CentreNationalRechercheSpeciale::enConstruction(CNRSB(M)) ^ HotelDeVille::orRestant(getHotel(M, a2)) >= CentreNationalRechercheSpeciale::prixConstruction(CNRSB(M)) ^ (getHotel(M, a2) == hotelDeVilleB(M)),alors
						HotelDeVille::abandoned(HotelDeVille::retrait(hotelDeVilleB(M), CentreNationalRechercheSpeciale::prixConstruction(CNRSB(M))))

					sinon si c2 = COMMANDE.RECHERCHECNRS ^ ¬CentreNationalRechercheSpeciale::enRecherche(CNRSB(M))^ CentreNationalRechercheSpeciale::constructionFinie(CNRSB(M))^ HotelDeVille::orRestant(getHotel(M, a2)) >= CentreNationalRechercheSpeciale::prixRecherche(CNRSB(M)) ^ (getHotel(M, a2) == hotelDeVilleB(M)), alors
						HotelDeVille::abandoned(HotelDeVille::retrait(hotelDeVilleB(M), CentreNationalRechercheSpeciale::prixRecherche(CNRSB(M))))
			sinon, alors
				hotelDeVilleB(M)
	

	//CNRSA
	CNRSA(pasJeu(M, c1, s1, a1, c2, s2, a2)) =
			si HotelDeVille::etat_d_appartenance(getHotel(M, a1)) == ETAT.OCCUPE, alors
				si HotelDeVille::appartenance(getHotel(M, a1)) == Villageois::race(getVillageois(M, s1[0]),alors
					si c1 = COMMANDE.CONSTRUIRECNRS ^ ¬CentreNationalRechercheSpeciale::enConstruction(CNRSA(M)) ^ HotelDeVille::orRestant(getHotel(M, a1)) >= CentreNationalRechercheSpeciale::prixConstruction(CNRSA(M)) ^ (getHotel(M, a1) == hotelDeVilleA(M)),alors
						CentreNationalRechercheSpecial::commencerConstruction(CNRSA(M))

					sinon si c1 = COMMANDE.RECHERCHECNRS ^ ¬CentreNationalRechercheSpeciale::enRecherche(CNRSA(M))^ CentreNationalRechercheSpeciale::constructionFinie(CNRSA(M))^ HotelDeVille::orRestant(getHotel(M, a1)) >= CentreNationalRechercheSpeciale::prixRecherche(CNRSA(M)) ^ (getHotel(M, a1) == hotelDeVilleA(M)), alors
						CentreNationalRechercheSpecial::commencerRecherche(CNRSA(M))
				
				sinon si HotelDeVille::appartenance(getHotel(M, a2)) == Villageois::race(getVillageois(M, s2[0]),alors
					si c2 = COMMANDE.CONSTRUIRECNRS ^ ¬CentreNationalRechercheSpeciale::enConstruction(CNRSA(M)) ^ HotelDeVille::orRestant(getHotel(M, a2)) >= CentreNationalRechercheSpeciale::prixConstruction(CNRSA(M)) ^ (getHotel(M, a2) == hotelDeVilleA(M)),alors
						CentreNationalRechercheSpecial::commencerConstruction(CNRSA(M))

					sinon si c2 = COMMANDE.RECHERCHECNRS ^ ¬CentreNationalRechercheSpeciale::enRecherche(CNRSA(M))^ CentreNationalRechercheSpeciale::constructionFinie(CNRSA(M))^ HotelDeVille::orRestant(getHotel(M, a2)) >= CentreNationalRechercheSpeciale::prixRecherche(CNRSA(M)) ^ (getHotel(M, a2) == hotelDeVilleA(M)), alors
						CentreNationalRechercheSpecial::commencerRecherche(CNRSA(M))
				
				sinon si CentreNationalRechercheSpeciale::enConstruction(CNRSA(M)), alors
					si ¬CentreNationalRechercheSpeciale::constructionFinie(CNRSA(M)), alors
						CentreNationalRechercheSpecial::construire(CNRSA(M))
					sinon, alors
						CNRSA(M)

				sinon si CentreNationalRechercheSpeciale::enRecherche(CNRSA(M)), alors
					si ¬CentreNationalRechercheSpeciale::rechercheFinie(CNRSA(M)), alors
						CentreNationalRechercheSpecial::recherche(CNRSA(M))
					sinon, alors
						si c1= COMMANDE.APPLIQUER_RECHERCHE, alors
							si getHotel(M, a1) == hotelDeVilleA(M) ^ HotelDeVille::etat_d_appartenance(getHotel(M, a1)) == ETAT.OCCUPE ^ HotelDeVille::appartenance(getHotel(M, a1)) == Villageois::race(getVillageois(M, s1[0]), alors
						CentreNationalRechercheSpecial::finirRecherche(CNRSA(M))
						
						sinon si c2= COMMANDE.APPLIQUER_RECHERCHE, alors
							si getHotel(M, a2) == hotelDeVilleA(M) ^ HotelDeVille::etat_d_appartenance(getHotel(M, a2)) == ETAT.OCCUPE ^ HotelDeVille::appartenance(getHotel(M, a1)) == Villageois::race(getVillageois(M, s2[0]), alors
						CentreNationalRechercheSpecial::finirRecherche(CNRSA(M))
						
						sinon, alors
							CNRSA(M)
				sinon, alors
				CNRSA(M)

			sinon, alors
				CNRSA(M)
	

	//CNRSB
	CNRSB(pasJeu(M, c1, s1, a1, c2, s2, a2)) =
			si HotelDeVille::etat_d_appartenance(getHotel(M, a1)) == ETAT.OCCUPE, alors
				si HotelDeVille::appartenance(getHotel(M, a1)) == Villageois::race(getVillageois(M, s1[0]),alors
					si c1 = COMMANDE.CONSTRUIRECNRS ^ ¬CentreNationalRechercheSpeciale::enConstruction(CNRSA(M)) ^ HotelDeVille::orRestant(getHotel(M, a1)) >= CentreNationalRechercheSpeciale::prixConstruction(CNRSB(M)) ^ (getHotel(M, a1) == hotelDeVilleB(M)),alors
						CentreNationalRechercheSpecial::commencerConstruction(CNRSA(M))

					sinon si c1 = COMMANDE.RECHERCHECNRS ^ ¬CentreNationalRechercheSpeciale::enRecherche(CNRSB(M))^ CentreNationalRechercheSpeciale::constructionFinie(CNRSB(M))^ HotelDeVille::orRestant(getHotel(M, a1)) >= CentreNationalRechercheSpeciale::prixRecherche(CNRSB(M)) ^ (getHotel(M, a1) == hotelDeVilleB(M)), alors
						CentreNationalRechercheSpecial::commencerRecherche(CNRSB(M))
				
				sinon si HotelDeVille::appartenance(getHotel(M, a2)) == Villageois::race(getVillageois(M, s2[0]),alors
					si c2 = COMMANDE.CONSTRUIRECNRS ^ ¬CentreNationalRechercheSpeciale::enConstruction(CNRSB(M)) ^ HotelDeVille::orRestant(getHotel(M, a2)) >= CentreNationalRechercheSpeciale::prixConstruction(CNRSB(M)) ^ (getHotel(M, a2) == hotelDeVilleB(M)),alors
						CentreNationalRechercheSpecial::commencerConstruction(CNRSB(M))

					sinon si c2 = COMMANDE.RECHERCHECNRS ^ ¬CentreNationalRechercheSpeciale::enRecherche(CNRSB(M))^ CentreNationalRechercheSpeciale::constructionFinie(CNRSB(M))^ HotelDeVille::orRestant(getHotel(M, a2)) >= CentreNationalRechercheSpeciale::prixRecherche(CNRSB(M)) ^ (getHotel(M, a2) == hotelDeVilleB(M)), alors
						CentreNationalRechercheSpecial::commencerRecherche(CNRSB(M))
				
				sinon si CentreNationalRechercheSpeciale::enConstruction(CNRSB(M)), alors
					si ¬CentreNationalRechercheSpeciale::constructionFinie(CNRSB(M)), alors
						CentreNationalRechercheSpecial::construire(CNRSB(M))
					sinon, alors
						CNRSB(M)

				sinon si CentreNationalRechercheSpeciale::enRecherche(CNRSB(M)), alors
					si ¬CentreNationalRechercheSpeciale::rechercheFinie(CNRSB(M)), alors
						CentreNationalRechercheSpecial::recherche(CNRSB(M))
					sinon, alors
						si c1= COMMANDE.APPLIQUER_RECHERCHE, alors
							si getHotel(M, a1) == hotelDeVilleB(M) ^ HotelDeVille::etat_d_appartenance(getHotel(M, a1)) == ETAT.OCCUPE ^ HotelDeVille::appartenance(getHotel(M, a1)) == Villageois::race(getVillageois(M, s1[0]), alors
						CentreNationalRechercheSpecial::finirRecherche(CNRSB(M))
						
						sinon, si c2= COMMANDE.APPLIQUER_RECHERCHE, alors
							si getHotel(M, a2) == hotelDeVilleB(M) ^ HotelDeVille::etat_d_appartenance(getHotel(M, a2)) == ETAT.OCCUPE ^ HotelDeVille::appartenance(getHotel(M, a1)) == Villageois::race(getVillageois(M, s2[0]), alors
						CentreNationalRechercheSpecial::finirRecherche(CNRSB(M))
						
						sinon, alors
							CNRSB(M)
				sinon, alors
				CNRSB(M)

			sinon, alors
				CNRSB(M)
