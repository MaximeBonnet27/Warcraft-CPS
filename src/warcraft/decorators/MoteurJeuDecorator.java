package warcraft.decorators;

import java.util.Set;

import warcraft.enums.ECommande;
import warcraft.enums.EResultat;
import warcraft.services.ICentreNationalRechercheSpecialeService;
import warcraft.services.IHotelDeVilleService;
import warcraft.services.IMineService;
import warcraft.services.IMoteurJeuService;
import warcraft.services.IMurailleService;
import warcraft.services.IRouteService;
import warcraft.services.IVillageoisService;

public class MoteurJeuDecorator {
	private IMoteurJeuService delegate;

	public MoteurJeuDecorator(IMoteurJeuService moteur) {
		super();
		this.delegate = moteur;
	}

	
	public int largeurTerrain() {
		return delegate.largeurTerrain();
	}

	public int hauteurTerrain() {
		return delegate.hauteurTerrain();
	}

	public int maxPasJeu() {
		return delegate.maxPasJeu();
	}

	public int pasJeuCourant() {
		return delegate.pasJeuCourant();
	}

	public boolean estFini() {
		return delegate.estFini();
	}

	public EResultat resultatFinal() {
		return delegate.resultatFinal();
	}

	public Set<Integer> numeroesVillageois() {
		return delegate.numeroesVillageois();
	}

	public IVillageoisService getVillageois(int num) {
		return delegate.getVillageois(num);
	}

	public int positionVillageoisX(int num) {
		return delegate.positionVillageoisX(num);
	}

	public int positionVillageoisY(int num) {
		return delegate.positionVillageoisY(num);
	}

	public IMineService getMineVillageois(int num) {
		return delegate.getMineVillageois(num);
	}

	public Set<Integer> numeroesMine() {
		return delegate.numeroesMine();
	}

	public IMineService getMine(int num) {
		return delegate.getMine(num);
	}

	public int positionMineX(int num) {
		return delegate.positionMineX(num);
	}

	public int positionMineY(int num) {
		return delegate.positionMineY(num);
	}

	public Set<Integer> numeroesRoute() {
		return delegate.numeroesRoute();
	}

	public IRouteService getRoute(int num) {
		return delegate.getRoute(num);
	}

	public int positionRouteX(int num) {
		return delegate.positionRouteX(num);
	}

	public int positionRouteY(int num) {
		return delegate.positionRouteY(num);
	}

	public Set<Integer> numeroesMuraille() {
		return delegate.numeroesMuraille();
	}

	public IMurailleService getMuraille(int num) {
		return delegate.getMuraille(num);
	}

	public int positionMurailleX(int num) {
		return delegate.positionMurailleX(num);
	}

	public int positionMurailleY(int num) {
		return delegate.positionMurailleY(num);
	}

	public boolean villageoisSurRoute(int x, int y, int l, int h, int r) {
		return delegate.villageoisSurRoute(x, y, l, h, r);
	}

	public boolean villageoisSurMuraille(int x, int y, int l, int h, int m) {
		return delegate.villageoisSurMuraille(x, y, l, h, m);
	}

	public IHotelDeVilleService hotelDeVilleA() {
		return delegate.hotelDeVilleA();
	}

	public IHotelDeVilleService hotelDeVilleB() {
		return delegate.hotelDeVilleB();
	}

	public IHotelDeVilleService getHotel(int numHotel) {
		return delegate.getHotel(numHotel);
	}

	public int positionHotelVilleXA() {
		return delegate.positionHotelVilleXA();
	}

	public int positionHotelVilleYA() {
		return delegate.positionHotelVilleYA();
	}

	public int positionHotelVilleXB() {
		return delegate.positionHotelVilleXB();
	}

	public int positionHotelVilleYB() {
		return delegate.positionHotelVilleYB();
	}

	public boolean peutEntrer(int numVillageois, int numMine) {
		return delegate.peutEntrer(numVillageois, numMine);
	}

	public boolean peutEntrerHotelVille(int numVillageois, int numHotel) {
		return delegate.peutEntrerHotelVille(numVillageois, numHotel);
	}

	public ICentreNationalRechercheSpecialeService CNRSA() {
		return delegate.CNRSA();
	}

	public ICentreNationalRechercheSpecialeService CNRSB() {
		return delegate.CNRSB();
	}

	public void init(int largeur, int hauteur, int maxPas) {
		delegate.init(largeur, hauteur, maxPas);
	}

	public void pasJeu(ECommande c1, Set<Integer> s1, int a1, ECommande c2,
			Set<Integer> s2, int a2) {
		delegate.pasJeu(c1, s1, a1, c2, s2, a2);
	}
	
	public boolean collision(int x1,int y1,int l1,int h1,int x2,int y2,int l2,int h2){
		return delegate.collision(x1, y1, l1, h1, x2, y2, l2, h2);
	}
	
}
