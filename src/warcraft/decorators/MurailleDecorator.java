package warcraft.decorators;

import warcraft.services.IMurailleService;

public abstract class MurailleDecorator  implements IMurailleService{

  private IMurailleService delegate;

  public MurailleDecorator(IMurailleService delegate) {
    super();
    this.delegate = delegate;
  }

  public int largeur() {
    return delegate.largeur();
  }

  public int hauteur() {
    return delegate.hauteur();
  }

  public int pointsDeVie() {
    return delegate.pointsDeVie();
  }

  public boolean estDetruite() {
    return delegate.estDetruite();
  }

public void init(int largeur, int hauteur, int pv) {
	delegate.init(largeur, hauteur, pv);
}

public void taper(int degats) {
	delegate.taper(degats);
}

  

}
