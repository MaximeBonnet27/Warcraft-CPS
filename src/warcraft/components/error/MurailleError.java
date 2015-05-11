package warcraft.components.error;

import warcraft.services.IMurailleService;

public class MurailleError implements IMurailleService{

  private int largeur, hauteur;
  private int pointsDeVie;
  
  public MurailleError(){
	  
  }
  
  @Override
  public int largeur() {
    return largeur;
  }

  @Override
  public int hauteur() {
    return hauteur;
  }

  @Override
  public int pointsDeVie() {
    return pointsDeVie;
  }

  @Override
  public boolean estDetruite() {
    return pointsDeVie <= 0;
  }

  @Override
  public void init(int largeur, int hauteur, int pv) throws Exception{
	//error
	//if(!(largeur>0))
	//	throw new Exception("\\pre : largeur > 0");
	
	//error
	//if(!(hauteur>0))
	//	throw new Exception("\\pre : hauteur > 0");
	
	//error
	//if(!(pv>0))
	//	throw new Exception("\\pre : pv > 0");
	
    this.largeur = largeur;
    this.hauteur = hauteur;
    //error
    this.pointsDeVie=0;
  
  }

  @Override
  public void taper(int degats) throws Exception{
	  //error
	  //if(estDetruite())
	  //	throw new Exception("\\pre : !estDetruite() ");
	  
	  
	  //error
	  //if(!(degats>0))
	//		throw new Exception("\\pre : degats > 0");
    pointsDeVie -= degats;
  }

}
