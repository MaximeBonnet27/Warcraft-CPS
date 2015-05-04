package warcraft.exceptions;

public class PostConditionError extends RuntimeException{

  private static final long serialVersionUID = 7636629133879967323L;

  public PostConditionError(String message) {
    super(message + " : Post-Condition non respéctée");
  }

  
}
