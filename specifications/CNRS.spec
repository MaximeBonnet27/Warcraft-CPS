service : CentreNationalRechercheSpeciale

types: int, boolean, COMPETENCE{FORCE,PV,VITESSE,RIEN}

Observators : 
    const prixConstruction:     [CentreNationalRechercheSpeciale] -> int
    const prixRecherche:        [CentreNationalRechercheSpeciale] -> int
    const tempsDeConstruction:  [CentreNationalRechercheSpeciale] -> int
    const tempsDeRecherche:     [CentreNationalRechercheSpeciale] -> int
    tempsCourant:               [CentreNationalRechercheSpeciale] -> int
    enConstruction:             [CentreNationalRechercheSpeciale] -> boolean
    constructionFinie:          [CentreNationalRechercheSpeciale] -> boolean
    rechercheCourante:          [CentreNationalRechercheSpeciale] -> int
    enRecherche:                [CentreNationalRechercheSpeciale] -> boolean
    rechercheFinie:             [CentreNationalRechercheSpeciale] -> boolean
    competenceAugmente:			[CentreNationalRechercheSpeciale] -> COMPETENCE
    	pre competenceAugmente(c)
    		require rechercheFinie(c)
    boost:						[CentreNationalRechercheSpeciale] -> int
    	pre boost(c)
    		require rechercheFinie(c)

Constructors :
	init : int x int x int x int -> [CentreNationalRechercheSpeciale]
		pre init(tempsC, tempsR,prixC,prixR)
			require tempsC>0 ^ tempsR > 0 ^ prixC>0 ^ prixR>0

	commencerConstruction: [CentreNationalRechercheSpeciale] -> [CentreNationalRechercheSpeciale]
		pre commencerConstruction(c)
			require ¬enConstruction(c)

	construire : [CentreNationalRechercheSpeciale] -> [CentreNationalRechercheSpeciale]
		pre construire(c)
			require ¬constructionFinie(c) ^ enConstruction(c)

	commencerRecherche : [CentreNationalRechercheSpeciale] -> [CentreNationalRechercheSpeciale]
		pre recherche(c)
			require constructionFinie(c) ^ ¬enRecherche(c)
	
	recherche : [CentreNationalRechercheSpeciale] -> [CentreNationalRechercheSpeciale]
		pre recherche(c)
			require ¬rechercheFinie(c) ^ enRecherche(c)

	finirRecherche: [CentreNationalRechercheSpeciale] -> [CentreNationalRechercheSpeciale]
		pre finirRecherche(c)
			require enRecherche(c)

Observations :
[invariants]
	0<=tempsCourant<=tempsDeConstruction
	enConstruction(c) =(min)= (tempsCourant(c)>0)
	constructionFinie(c) =(min)= (tempsCourant(c)==tempsDeConstruction)

	0 <= rechercheCourante <= tempsDeRecherche
	enRecherche(c) =(min)= (rechercheCourante(c) > 0)
	rechercheFinie(c) =(min)= (rechercheCourante(c) == tempsDeRecherche)

	competenceAugmente(c) =(min)= random(COMPETENCE)
	boost(c) =(min)= random(10)
	
[init]
	tempsDeConstruction(init(tempsC, tempsR,prixC,prixR)) == tempsC
	tempsDeRecherche(init(tempsC, tempsR, prixC, prixR)) = tempsR
	prixConstruction(init(tempsC, tempsR,prixC,prixR)) == prixC
	prixRecherche(init(tempsC, tempsR,prixC,prixR)) == prixR
	tempsCourant(init(tempsC, tempsR,prixC,prixR)) == 0
	rechercheCourante(init(tempsC, tempsR,prixC,prixR)) == 0

[commencerConstruction]
	tempsCourant(commencerConstruction(c)) == 1
	rechercheCourante(commencerConstruction(c)) == rechercheCourante(c)

[construire]
	tempsCourant(construire(c)) == (tempsCourant(c)+1)
	rechercheCourante(constuire(c)) == rechercheCourante(c)

[finirRecherche]
	tempsCourant(finirConstruction(c)) == tempsCourant(c)
	rechercheCourante(finirConstruction(c)) == 0

[commencerRecherche]
	rechercheCourante(commencerRecherche(c)) == 1
	tempsCourant(commencerRecherche(c)) == tempsCourant(c)

[recherche]
	rechercheCourante(recherche(c)) == (rechercheCourante(c) + 1)
	tempsCourant(recherche(c)) == tempsCourant(c)

