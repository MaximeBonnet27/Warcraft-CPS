service : CentreNationalRechercheSpeciale

types: int, boolean, COMPETENCE{FORCE,PV,VITESSE,RIEN}

Observators : 
	const prixConstruction: [CentreNationalRechercheSpeciale] -> int
	const prixRecherche: [CentreNationalRechercheSpeciale] -> int
	const tempsDeConstruction : [CentreNationalRechercheSpeciale] -> int
	const tempsDeRecherche : [CentreNationalRechercheSpeciale] -> int
	tempsCourant : [CentreNationalRechercheSpeciale] -> int	
	enConstruction : [CentreNationalRechercheSpeciale] -> boolean
	constructionFinie : [CentreNationalRechercheSpeciale] -> boolean
	rechercheCourante : [CentreNationalRechercheSpeciale] -> int
	enRecherche : [CentreNationalRechercheSpeciale] -> boolean
	rechercheFinie : [CentreNationalRechercheSpeciale] -> boolean 

Constructors :
	init : int x int x int x int -> [CentreNationalRechercheSpeciale]
		pre init(tempsC, tempsR,prixC,prixR)
			require tempsC>0 ^ tempsR > 0 ^ prixC>0 ^ prixR>0

	commencerConstruction: [CentreNationalRechercheSpeciale] x int -> [CentreNationalRechercheSpeciale]
		pre commencerConstruction(c,p)
			require ¬enConstruction(c) ^ (p==prixConstruction(c))

	construire : [CentreNationalRechercheSpeciale] -> [CentreNationalRechercheSpeciale]
		pre construire(c)
			require ¬constructionFinie(c)

	commencerRecherche : [CentreNationalRechercheSpeciale] x int -> [CentreNationalRechercheSpeciale]
		pre recherche(c,p)
			require constructionFinie(c) ^ (p==prixRecherche(c))
	

	recherche : [CentreNationalRechercheSpeciale] -> [CentreNationalRechercheSpeciale]
		pre recherche(c)
			require ¬rechercheFinie(c)

Observations :
[invariants]
	0<tempsCourant<=tempsDeConstruction
	enConstruction(c) =(min)= (tempsCourant(c)>0)
	constructionFinie(c) =(min)= (tempsCourant(c)==tempsDeConstruction)
	0 < rechercheCourante <= tempsDeRecherche
	enRecherche(c) =(min)=(rechercheCourante(c) > 0)
	rechercheFinie(c) =(min)=(rechercheCourante(c) == tempsDeRecherche)
	
[init]
	tempsDeConstruction(init(tempsC, tempsR,prixC,prixR)) == tempsC
	tempsDeRecherche(init(tempsC, tempsR, prixC, prixR)) = tempsR
	prixConstruction(init(tempsC, tempsR,prixC,prixR)) == prixC
	prixRecherche(init(tempsC, tempsR,prixC,prixR)) == prixR
	tempsCourant(init(tempsC, tempsR,prixC,prixR)) == 0
	rechercheCourante(init(tempsC, tempsR,prixC,prixR)) == 0

[commencerConstruction]
	tempsCourant(commencerconstruction(c)) == 1

[construire]
	tempsCourant(construire(c)) == (tempsCourant(c)+1)

[commencerRecherche]
	rechercheCourante(commencerRecherche(c)) == 1

[recherche]
	rechercheCourante(recherche(c)) == (rechercheCourante(c) + 1)	

