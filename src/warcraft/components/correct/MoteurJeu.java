package warcraft.components.correct;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import warcraft.enums.ECommande;
import warcraft.enums.EResultat;
import warcraft.services.ICentreNationalRechercheSpecialeService;
import warcraft.services.IGestionCombatService;
import warcraft.services.IHotelDeVilleService;
import warcraft.services.IMineService;
import warcraft.services.IMoteurJeuService;
import warcraft.services.IMurailleService;
import warcraft.services.IRouteService;
import warcraft.services.IVillageoisService;
import warcraft.services.RequireCentreNationalRechercheSpecialeService;
import warcraft.services.RequireGestionCombatService;
import warcraft.services.RequireHotelDeVilleService;
import warcraft.services.RequireMineService;
import warcraft.services.RequireMurailleService;
import warcraft.services.RequireRouteService;
import warcraft.services.RequireVillageoisService;

public class MoteurJeu implements IMoteurJeuService,
RequireMineService,
RequireMurailleService,
RequireVillageoisService,
RequireCentreNationalRechercheSpecialeService,
RequireGestionCombatService,
RequireHotelDeVilleService,
RequireRouteService{

	private int largeurTerrain;
	private int hauteurTerrain;
	private int maxPasJeu;
	private int pasJeuCourant;
	
	private ArrayList<Integer> numeroesVillageois;
	private ArrayList<IVillageoisService> villageois;
	private ArrayList<Integer> positionVillageoisX,positionVillageoisY;
	
	private ArrayList<Integer> numeroesMine;
	private ArrayList<IMineService> mines;
	private ArrayList<Integer> positionMinesX,positionMinesY;
	
	private ArrayList<Integer> numeroesRoute;
	private ArrayList<IRouteService> routes;
	private ArrayList<Integer> positionRouteX,positionRouteY;
	
	private ArrayList<Integer> numeroesMuraille;
	private ArrayList<IMurailleService> murailles;
	private ArrayList<Integer> positionMurailleX,positionMurailleY;
	@Override
	public int largeurTerrain() {
		return largeurTerrain;
	}

	@Override
	public int hauteurTerrain() {
		return hauteurTerrain;
	}

	@Override
	public int maxPasJeu() {
		return maxPasJeu;
	}

	@Override
	public int pasJeuCourant() {
		return pasJeuCourant;
	}

	@Override
	public boolean estFini() {
		return false;
	}

	@Override
	public EResultat resultatFinal() {
		return null;
	}

	@Override
	public Set<Integer> numeroesVillageois() {
		return new HashSet<Integer>(numeroesVillageois);
	}

	@Override
	public IVillageoisService getVillageois(int num) {
		return villageois.get(num);
	}

	@Override
	public int positionVillageoisX(int num) {
		return positionVillageoisX.get(num);
	}

	@Override
	public int positionVillageoisY(int num) {
		return positionVillageoisY.get(num);
	}

	@Override
	public IMineService getMineVillageois(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Integer> numeroesMine() {
		return new HashSet<Integer>(numeroesMine);
	}

	@Override
	public IMineService getMine(int num) {
		return mines.get(num);
	}

	@Override
	public int positionMineX(int num) {
		return positionMinesX.get(num);
	}

	@Override
	public int positionMineY(int num) {
		return positionMinesY.get(num);
	}

	@Override
	public Set<Integer> numeroesRoute() {
		return new HashSet<Integer>(numeroesRoute);
	}

	@Override
	public IRouteService getRoute(int num) {
		return routes.get(num);
	}

	@Override
	public int positionRouteX(int num) {
		return positionRouteX.get(num);
	}

	@Override
	public int positionRouteY(int num) {
		return positionRouteY.get(num);
	}

	@Override
	public Set<Integer> numeroesMuraille() {
		return new HashSet<Integer>(numeroesMuraille);
	}

	@Override
	public IMurailleService getMuraille(int num) {
		return murailles.get(num);
	}

	@Override
	public int positionMurailleX(int num) {
		return positionMurailleX.get(num);
	}

	@Override
	public int positionMurailleY(int num) {
		return positionMurailleY.get(num);
	}

	@Override
	public boolean villageoisSurRoute(int x, int y, int l, int h, int r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean villageoisSurMuraille(int x, int y, int l, int h, int m) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IHotelDeVilleService hotelDeVilleA() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IHotelDeVilleService hotelDeVilleB() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IHotelDeVilleService getHotel(int numHotel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int positionHotelVilleXA() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int positionHotelVilleYA() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int positionHotelVilleXB() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int positionHotelVilleYB() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean peutEntrer(int numVillageois, int numMine) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean peutEntrerHotelVille(int numVillageois, int numHotel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ICentreNationalRechercheSpecialeService CNRSA() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICentreNationalRechercheSpecialeService CNRSB() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean collision(int x1, int y1, int l1, int h1, int x2, int y2,
			int l2, int h2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void init(int largeur, int hauteur, int maxPas) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pasJeu(ECommande c1, Set<Integer> s1, int a1, ECommande c2,
			Set<Integer> s2, int a2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void bindRouteService(IRouteService service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void bindHotelDeVilleService(IHotelDeVilleService service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void bindGestionCombatService(IGestionCombatService service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void bindCentreNationalRechercheSpecialeServie(
			ICentreNationalRechercheSpecialeService service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void bindVillageoisService(IVillageoisService service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void bindMurailleService(IMurailleService service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void bindMineService(IMineService service) {
		// TODO Auto-generated method stub

	}

}
