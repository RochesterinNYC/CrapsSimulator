/**
 * <b>PairOfDice Class</b>
 * <p>
 * Models a set of two dice each with 6 faces that can roll and make special sets.
 * <p>
 * Constructors and methods in this class do not need to throw 
 * IllegalArgumentExceptions because the exceptions thrown in the Die class/objects
 * would eventually get passed up all the way to the CrapsGame class and get 
 * handled there. 
 * @author James Wen - jrw2175
 */

public class PairOfDice {
	private Die die1;
	private Die die2;
	private int diceSum; 
	private boolean cheatOn;
	
	/**
	 * Constructs two dice each with 6 faces whose currently shown face values are
	 * chosen at random.
	 */
	public PairOfDice(){
		this.die1 = new Die();
		this.die2 = new Die();
		diceSum = die1.getFaceValue()+die2.getFaceValue();
	}
	
	/**
	 * Constructs two dice each with 6 faces whose currently shown face values are
	 * specifically chosen.
	 */
	public PairOfDice(int dieValue1, int dieValue2){
		this.die1= new Die(dieValue1);
		this.die2= new Die(dieValue2);
		diceSum = die1.getFaceValue()+die2.getFaceValue();
	}
	
	/**
	 * Constructs two dice each with 6 faces whose currently shown face values are
	 * those of two other Dice's current face values.
	 */
	public PairOfDice(Die die1, Die die2){
		this.die1= new Die(die1.getFaceValue());
		this.die2= new Die(die2.getFaceValue());
		diceSum = this.die1.getFaceValue()+ this.die2.getFaceValue();
	}
	/**
	  * Roll
	  * <p>
	  * Simulates the rolling of two dice by randomly changing the face values of the 
	  * dice to integers in the range of 1-6.
	  * <p>
	  * Cheating: If the cheat mode is on, then visualSelector is randomly assigned
	  * as an integer in the range of 1-6. Depending on the value of visualSelector,
	  * a certain permutation of dice face values is chosen to add up to 7.
	  * <p>
	  * The purpose of this is to alleviate gambler/user suspicion. If they were
	  * constantly losing on the exact same permutation of dice (say always die1 = 3,
	  * die 2 = 4), then they would start to suspect foul play.
      * @return diceSum - the sum of the current face values of the dice 
      * after rolling
      */
	public int roll(){
		if(cheatOn){//If cheat mode is on
			int visualSelector = ((int)(Math.random() * 6) + 1);
			if (visualSelector==1){
				die1.setFaceValue(3);
				die2.setFaceValue(4);
			}
			if (visualSelector==2){
				die1.setFaceValue(4);
				die2.setFaceValue(3);
			}
			if (visualSelector==3){
				die1.setFaceValue(2);
				die2.setFaceValue(5);
			}
			if (visualSelector==4){
				die1.setFaceValue(5);
				die2.setFaceValue(2);
			}
			if (visualSelector==5){
				die1.setFaceValue(6);
				die2.setFaceValue(1);
			}
			if (visualSelector==6){
				die1.setFaceValue(1);
				die2.setFaceValue(6);
			}
			cheatOn=false; //Resets the cheat dice as normal
		}
		else{
			die1.roll();
			die2.roll();
		}
		diceSum = die1.getFaceValue()+die2.getFaceValue();
		return (die1.getFaceValue()+die2.getFaceValue());
	}
	
	/**
	  * cheatDice
	  * <p>
	  * Switches whether dice rolls involving cheating or not.
	  * <p>
	  * Cheating: The cheating algorithm involves cheating randomly occurring (refer
	  * to CrapsHandler class's applyRules method) to alleviate suspicion and 
	  * prevent patterns from occurring. Hence, a switch is needed to turn on and 
	  * off whether the dice rolls are currently being manipulated or not.
     */
	public void cheatDice(){
		if (cheatOn){
			cheatOn = false;
		}
		else{
			cheatOn=true;
		}
	}
	
	/**
	  * resetDice
	  * <p>
	  * Resets the faces of the two dice by randomly changing the values to integers 
	  * in the range of 1-6.
	  * <p>
	  * Similar to the roll method, but without the cheating algorithm implemented. 
	  * This method is primarily used to reset the dice when a new game is started.
      */
	public void resetDice(){
		die1.roll();
		die2.roll();
		diceSum = die1.getFaceValue()+die2.getFaceValue();
	}
	
	/**
	 * toString
	 * <p>
	 * Returns a string representation of the current faces of the two dice
	 * <p>
	 * Single visualDie methods are used in lieu of this method for ease 
	 * of formatting. This method produces the dice vertically on top of each other.
	 * <p>
	 * Also, NEWLINE constant is used to ensure that a new line is produced no
	 * matter what OS this program is being run on.
	 * @return visual String representation of the two dice faces
	 */
	public String toString(){
		final String NEWLINE = System.getProperty("line.separator");
		return die1.toString() + NEWLINE + die2.toString();
	}
	
	/**
	 * visualDie1
	 * <p>
	 * Returns a string representation of die1's current face
	 * @return visual String representation of die1's current die face
	 */
	public String visualDie1(){
		return die1.toString();
	}
	
	/**
	 * visualDie2
	 * <p>
	 * Returns a string representation of die2's current face
	 * @return visual String representation of die2's current die face
	 */
	public String visualDie2(){
		return die2.toString();
	}
	
	/**
	 * isSnakeEyes
	 * <p>
	 * Returns whether the two dice both currently have 1s as their face value
  	 * <p>
	 * @return boolean - whether the sum of the two die face values is 2
	 */
	public boolean isSnakeEyes(){
		return (diceSum == 2);
	}
	
	/**
	 * isBoxCars
	 * <p>
	 * Returns whether the two dice both currently have 6s as their face value
  	 * <p>
	 * @return boolean - whether the sum of the two die face values is 12
	 */
	public boolean isBoxCars(){
		return (diceSum == 12);
	}
	
	/**
	 * isSeven
	 * <p>
	 * Returns whether the face values of the two dice add up to 7
  	 * <p>
	 * @return boolean - whether the sum of the two dice face values is 7
	 */
	public boolean isSeven(){
		return (diceSum == 7);
	}
	
	/**
	 * getSum
	 * <p>
	 * Returns the total value of the two current dice faces
  	 * <p>
	 * @return diceSum - sum of the two dice face values
	 */
	public int getSum(){
		return diceSum;
	}	
	
	/**
	 * getDie1
	 * <p>
	 * Returns the actual die1 being used.
  	 * <p>
	 * @return die1 - the actual die1 Die object
	 */
	public Die getDie1(){
		return die1;
	}
	
	/**
	 * getDie2
	 * <p>
	 * Returns the actual die2 being used.
  	 * <p>
	 * @return die2 - the actual die2 Die object
	 */
	public Die getDie2(){
		return die2;
	}

}
