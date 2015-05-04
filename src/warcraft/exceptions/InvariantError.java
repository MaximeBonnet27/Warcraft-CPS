package warcraft.exceptions;

public class InvariantError extends RuntimeException{

  private static final long serialVersionUID = 8839409876595862309L;

  public InvariantError(String message) {
    super(message + " : Invariant non respécté");
  }

}
