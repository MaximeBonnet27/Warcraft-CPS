package warcraft.exceptions;

public class PreConditionError extends RuntimeException{
  private static final long serialVersionUID = 7305763132414410930L;
  
  public PreConditionError(String message) {
    super(message + " : Pré-Condition   non respéctée");
  }

}
