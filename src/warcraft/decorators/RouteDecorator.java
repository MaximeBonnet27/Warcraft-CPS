package warcraft.decorators;

import warcraft.services.IRouteService;

public abstract class RouteDecorator implements IRouteService{

  private IRouteService delegate;

  public RouteDecorator(IRouteService delegate) {
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

  public IRouteService init(int largeur, int hauteur, double mult) {
    return delegate.init(largeur, hauteur, mult);
  }
  
  
  
}
