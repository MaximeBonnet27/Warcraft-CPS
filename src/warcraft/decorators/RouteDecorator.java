package warcraft.decorators;

import warcraft.services.IRoute;

public class RouteDecorator implements IRoute{

  private IRoute delegate;

  public RouteDecorator(IRoute delegate) {
    super();
    this.delegate = delegate;
  }

  public int largeur() {
    return delegate.largeur();
  }

  public int hauteur() {
    return delegate.hauteur();
  }

  public double multiplicateur() {
    return delegate.multiplicateur();
  }

  public IRoute init(int largeur, int hauteur, double mult) {
    return delegate.init(largeur, hauteur, mult);
  }
  
  
  
}
