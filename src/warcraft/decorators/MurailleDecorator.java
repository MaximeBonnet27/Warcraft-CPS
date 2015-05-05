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

  public IMurailleService init(int largeur, int hauteur, int pv) {
    return delegate.init(largeur, hauteur, pv);
  }

  public IMurailleService taper(int degats) {
    return delegate.taper(degats);
  }

  

}
