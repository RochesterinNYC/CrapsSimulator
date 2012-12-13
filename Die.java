/**
 * <b>Die Class</b>
 * <p>
 * Models a die with 6 faces that can roll and be compared to other dice.
 * <p>
 * @author James Wen - jrw2175
 */

public class Die
{
   private final int MAX = 6;  // maximum face value
   private int faceValue;  // current value showing on the die
   
   /**
    * Constructs a die with 6 faces whose currently shown face value is 
    * chosen at random.
    */
   
   public Die(){
	   this.faceValue = (int)(Math.random() * MAX) + 1;;
   }
   
   /**
    * Constructs a die with 6 faces whose currently shown face value is 
    * specifically chosen.
    * <p>
    * @param faceValue - the value to be the currently shown face
    * <p>
    * @throws IllegalArgumentException - if the desired value for face is not in 
    * the range of 1-6
    */
   public Die(int faceValue) throws IllegalArgumentException
   {
	   if(faceValue >0 && faceValue <= MAX){
		   this.faceValue = faceValue;
	   }
	   else{
		   throw new IllegalArgumentException();
	   }
	   
   }
   
   /**
    * Constructs a die with 6 faces whose current shown face value is that of a 
    * specific other die's currently shown face value.
    * <p>
    * @param die - the Die whose currently shown face value is to be the value of 
    * the currently shown face of this new Die object
    * <p>
    * @throws IllegalArgumentException - if the currently shown face value of the 
    * input Die is not in the range of 1-6
    */
   public Die(Die die) throws IllegalArgumentException
   {
      if(die.getFaceValue() > 0 && die.getFaceValue() <= MAX){
    	  this.faceValue = die.getFaceValue();
      }
      else{
    	  throw new IllegalArgumentException();
      }
   }
   
   /**
    * Roll
    * <p>
    * Simulates the rolling of a die by randomly changing the face value of the 
    * die to an integer in the range of 1-6.
    * <p>
    * @return faceValue - the current face value of the die after rolling
    */
   public int roll()
   {
      faceValue = (int)(Math.random() * MAX) + 1;

      return faceValue;
   }

   /**
    * setFaceValue
    * <p>
    * Changes the face value of the die to an integer in the range of 1-6.
    * <p>
    * @param value - the desired face value of the die
    * <p>
    * @throws IllegalArgumentException - if the desired face value for the die is 
    * not in the range of 1-6
    */
   public void setFaceValue (int value) throws IllegalArgumentException
   {
	   if(value > 0 && value <= MAX){
		   this.faceValue = value;
	   }
	   else{
		   throw new IllegalArgumentException();
	   }
   }

   /**
    * getFaceValue
    * <p>
    * Returns the current face value of the die.
    * <p>
    * @return faceValue - current face value of die
    */
   public int getFaceValue()
   {
      return faceValue;
   }

   /**
    * equals
    * <p>
    * Compares two dice through their current face values. Acts on theoretical
    * assumption that all dice dealt with are essentially the same except for their
    * current possible face values.
    * <p>
    * Note: Does not compare whether two dice objects are actually the same object
    * by comparing locations in memory. Such a method is unnecessary for this
    * Craps game.
    * @param otherDie - the other die object that this die object is being compared 
    * to
    * <p>
    * @return boolean - whether the face values of the two die are the same/equal
    */
   public boolean equals(Die otherDie){
	   return (otherDie.getFaceValue()== this.faceValue);
   }
   
   /**
    * toString
    * <p>
    * Returns a string representation of the die's current face.
    * <p>
    * Html tags used to assist with formatting, br tags used for line breaks, &nb sp
    * characters used for blank spaces.
    * @return dieRepresentation - visual String representation of current die face
    */
   public String toString()
   {
	  String dieRepresentation = "";
	  if (faceValue == 1){
		  dieRepresentation = "<html>&nbsp &nbsp X <br /></html>";
	  }
	  if (faceValue == 2){
		  dieRepresentation = "<html>X <br /><br />&nbsp &nbsp &nbsp &nbsp X</html>";
	  }	  
	  if (faceValue == 3){
		  dieRepresentation = "<html>X<br />&nbsp &nbsp X &nbsp &nbsp<br />&nbsp  " +
		 					  "&nbsp &nbsp &nbsp X</html>";
	  }
	  if (faceValue == 4){
		  dieRepresentation = "<html>X &nbsp &nbsp X<br /><br />X &nbsp &nbsp " +
		  					  "X</html>";
	  }
	  if (faceValue == 5){
		  dieRepresentation = "<html>X &nbsp &nbsp X<br />&nbsp &nbsp X <br />X " +
		  					  "&nbsp &nbsp X</html>";
	  }	  
	  if (faceValue == 6){
		  dieRepresentation = "<html>X &nbsp &nbsp X<br />X &nbsp &nbsp X<br />X " +
		  					  "&nbsp &nbsp X</html>";
	  }	  
      return dieRepresentation;
   }
} //End of Die class
