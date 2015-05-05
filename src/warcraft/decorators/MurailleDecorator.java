package warcraft.decorators;

import warcraft.services.IMuraille;

public abstract class MurailleDecorator  implements IMuraille{

  private IMuraille delegate;

  public MurailleDecorator(IMuraille delegate) {
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

  public IMuraille init(int largeur, int hauteur, int pv) {
    return delegate.init(largeur, hauteur, pv);
  }

  public IMuraille taper(int degats) {
    return delegate.taper(degats);
  }

  

}
