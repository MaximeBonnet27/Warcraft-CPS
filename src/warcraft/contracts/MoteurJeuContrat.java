package warcraft.contracts;

import java.util.Set;

import warcraft.decorators.MoteurJeuDecorator;
import warcraft.enums.ECommande;
import warcraft.enums.EResultat;
import warcraft.services.ICentreNationalRechercheSpecialeService;
import warcraft.services.IHotelDeVilleService;
import warcraft.services.IMineService;
import warcraft.services.IMoteurJeuService;
import warcraft.services.IMurailleService;
import warcraft.services.IRouteService;
import warcraft.services.IVillageoisService;

public class MoteurJeuContrat extends MoteurJeuDecorator {

	public MoteurJeuContrat(IMoteurJeuService moteur) {
		super(moteur);
	}

	@Override
	public int largeurTerrain() {
		// TODO Auto-generated method stub
		return super.largeurTerrain();
	}

	@Override
	public int hauteurTerrain() {
		// TODO Auto-generated method stub
		return super.hauteurTerrain();
	}

	@Override
	public int maxPasJeu() {
		// TODO Auto-generated method stub
		return super.maxPasJeu();
	}

	@Override
	public int pasJeuCourant() {
		// TODO Auto-generated method stub
		return super.pasJeuCourant();
	}

	@Override
	public boolean estFini() {
		// TODO Auto-generated method stub
		return super.estFini();
	}

	@Override
	public EResultat resultatFinal() {
		// TODO Auto-generated method stub
		return super.resultatFinal();
	}

	@Override
	public Set<Integer> numeroesVillageois() {
		// TODO Auto-generated method stub
		return super.numeroesVillageois();
	}

	@Override
	public IVillageoisService getVillageois(int num) {
		// TODO Auto-generated method stub
		return super.getVillageois(num);
	}

	@Override
	public int positionVillageoisX(int num) {
		// TODO Auto-generated method stub
		return super.positionVillageoisX(num);
	}

	@Override
	public int positionVillageoisY(int num) {
		// TODO Auto-generated method stub
		return super.positionVillageoisY(num);
	}

	@Override
	public IMineService getMineVillageois(int num) {
		// TODO Auto-generated method stub
		return super.getMineVillageois(num);
	}

	@Override
	public Set<Integer> numeroesMine() {
		// TODO Auto-generated method stub
		return super.numeroesMine();
	}

	@Override
	public IMineService getMine(int num) {
		// TODO Auto-generated method stub
		return super.getMine(num);
	}

	@Override
	public int positionMineX(int num) {
		// TODO Auto-generated method stub
		return super.positionMineX(num);
	}

	@Override
	public int positionMineY(int num) {
		// TODO Auto-generated method stub
		return super.positionMineY(num);
	}

	@Override
	public Set<Integer> numeroesRoute() {
		// TODO Auto-generated method stub
		return super.numeroesRoute();
	}

	@Override
	public IRouteService getRoute(int num) {
		// TODO Auto-generated method stub
		return super.getRoute(num);
	}

	@Override
	public int positionRouteX(int num) {
		// TODO Auto-generated method stub
		return super.positionRouteX(num);
	}

	@Override
	public int positionRouteY(int num) {
		// TODO Auto-generated method stub
		return super.positionRouteY(num);
	}

	@Override
	public Set<Integer> numeroesMuraille() {
		// TODO Auto-generated method stub
		return super.numeroesMuraille();
	}

	@Override
	public IMurailleService getMuraille(int num) {
		// TODO Auto-generated method stub
		return super.getMuraille(num);
	}

	@Override
	public int positionMurailleX(int num) {
		// TODO Auto-generated method stub
		return super.positionMurailleX(num);
	}

	@Override
	public int positionMurailleY(int num) {
		// TODO Auto-generated method stub
		return super.positionMurailleY(num);
	}

	@Override
	public boolean villageoisSurRoute(int x, int y, int l, int h, int r) {
		// TODO Auto-generated method stub
		return super.villageoisSurRoute(x, y, l, h, r);
	}

	@Override
	public boolean villageoisSurMuraille(int x, int y, int l, int h, int m) {
		// TODO Auto-generated method stub
		return super.villageoisSurMuraille(x, y, l, h, m);
	}

	@Override
	public IHotelDeVilleService hotelDeVilleA() {
		// TODO Auto-generated method stub
		return super.hotelDeVilleA();
	}

	@Override
	public IHotelDeVilleService hotelDeVilleB() {
		// TODO Auto-generated method stub
		return super.hotelDeVilleB();
	}

	@Override
	public IHotelDeVilleService getHotel(int numHotel) {
		// TODO Auto-generated method stub
		return super.getHotel(numHotel);
	}

	@Override
	public int positionHotelVilleXA() {
		// TODO Auto-generated method stub
		return super.positionHotelVilleXA();
	}

	@Override
	public int positionHotelVilleYA() {
		// TODO Auto-generated method stub
		return super.positionHotelVilleYA();
	}

	@Override
	public int positionHotelVilleXB() {
		// TODO Auto-generated method stub
		return super.positionHotelVilleXB();
	}

	@Override
	public int positionHotelVilleYB() {
		// TODO Auto-generated method stub
		return super.positionHotelVilleYB();
	}

	@Override
	public boolean peutEntrer(int numVillageois, int numMine) {
		// TODO Auto-generated method stub
		return super.peutEntrer(numVillageois, numMine);
	}

	@Override
	public boolean peutEntrerHotelVille(int numVillageois, int numHotel) {
		// TODO Auto-generated method stub
		return super.peutEntrerHotelVille(numVillageois, numHotel);
	}

	@Override
	public ICentreNationalRechercheSpecialeService CNRSA() {
		// TODO Auto-generated method stub
		return super.CNRSA();
	}

	@Override
	public ICentreNationalRechercheSpecialeService CNRSB() {
		// TODO Auto-generated method stub
		return super.CNRSB();
	}

	@Override
	public void init(int largeur, int hauteur, int maxPas) {
		// TODO Auto-generated method stub
		super.init(largeur, hauteur, maxPas);
	}

	@Override
	public void pasJeu(ECommande c1, Set<Integer> s1, int a1, ECommande c2,
			Set<Integer> s2, int a2) {
		// TODO Auto-generated method stub
		super.pasJeu(c1, s1, a1, c2, s2, a2);
	}

	
	
}
