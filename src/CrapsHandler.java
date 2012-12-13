/**
 * <b>CrapsHandler Class</b>
 * <p>
 * Handles and carries out all Craps game operations, including rolling dice and
 * applying the game rules.
 * <p>
 *
 * @author James Wen - jrw2175
 */
public class CrapsHandler {
	private final int KEYVALUE = 7; //notable value for dice
	private int rollToWin;
	private int diceValue;
	private String gameStatus;
	private String playerStatus;
	private String specialStatus;
	private boolean playerBonus; //cheat mode
	private PairOfDice dicePair;
	
	/**
	 * Constructs a Craps Hander, creates a pair of dice, and starts a new game.
	 */
	public CrapsHandler(){
		this.dicePair = new PairOfDice();
		newGame();
	}
	/**
	  * newGame
	  * <p>
	  * Starts a new game by resetting all the game label values and dice
    */
	public void newGame(){
		dicePair.resetDice();
		this.gameStatus = "New Game";
		this.playerStatus = "Should Roll";
		this.specialStatus = "None";
		this.diceValue = dicePair.getSum();
		this.rollToWin = KEYVALUE;
	}
	
	/**
	  * Roll
	  * <p>
	  * Simulates the rolling of a pair of dice by calling the roll methods of 
	  * the pair of dice
     */
	public void rollDice(){
		dicePair.roll();
		diceValue = dicePair.getSum();
	}
	
	/**
	  * ApplyRules
	  * <p>
	  * Applies the rules of Craps to the current status of the player and the 
	  * pair of dice in the game.
	  * <p>
	  * This method ascertains the current status of the player and game through the
	  * gameStatus and playerStatus strings and manipulates these accordingly to
	  * emulate game and player states.
	  * <p>
	  * Cheating: If the cheat mode is on, the cheating occurs in the case of the 
	  * player initially rolling a non-7 value and he/she has to keep on rolling. 
	  * With cheat mode on, there is a 25% chance that the cheat dice will activate
	  * and the next roll will automatically be a 7, causing the player to lose.
	  * <p>
	  * The implementation of the 25% chance aspect is to prevent patterns from
	  * forming and to alleviate player suspicion. If the chance of cheat dice 
	  * activating with cheat mode on was 100%, then the player would always lose 
	  * after the first roll. If the chance of cheat dice activating was similarly
	  * very high, then the house edge would be too unnaturally and also suspicious.
    */
	public void applyRules(){
		//If the player has started a new game
		if (("New Game").equals(gameStatus)){
			if(dicePair.isSeven()){//Player rolls 7 and wins
				gameStatus = "New Game?";
				playerStatus = "Has Won!";
				rollToWin = KEYVALUE;
			}
			else{//Player rolls non-7 value and has to keep rolling
				gameStatus = "In Progress";
				playerStatus = "Should Roll";
				rollToWin = diceValue;
				if (playerBonus){//25% chance of cheating when cheat mode is on
					if (((int)((Math.random() * 4) + 1))==1){
						dicePair.cheatDice();
					}
				}
			}
		}
		//If the player is in a currently ongoing game
		else if (("In Progress").equals(gameStatus)){
			if(dicePair.isSeven()){//Player rolls a 7 and loses					 
				gameStatus = "New Game?";
				playerStatus = "Has Lost.";
			}
			else if(diceValue==rollToWin){//Player rolls the target value and wins	
				gameStatus = "New Game?";
				playerStatus = "Has Won!";
			}
			else{//Player rolls a non-target and non-7 value and keeps rolling
				gameStatus = "In Progress";
				playerStatus = "Should Roll";
			}
		}
		
	}
	
	/**
	  * bonusSwitch
	  * <p>
	  * Switches the value of playerBonus to true or false depending on whether 
	  * the cheat mode is on. On is true and off is false.
    */
	public void bonusSwitch(boolean isOn){
		this.playerBonus = isOn;
	}
	
	/**
	  * getGameStatus
	  * <p>
	  * Returns at what state the current game is at, can be a "new game," 
	  * "in progress," or "new game?" (game is over).
	  * @return gameStatus - current status of the new or ongoing game
   */
	public String getGameStatus(){
		return gameStatus;
	}
	
	/**
	  * getPlayerStatus
	  * <p>
	  * Returns at what state the player is at, can be "Should Roll" (game is in 
	  * progress), "Has Won!", or "Has Lost." (last two indicate game is over).
	  * @return gameStatus - current status of the player in game
	*/
	public String getPlayerStatus(){
		return playerStatus;
	}
	
	/**
	  * getWinningRoll
	  * <p>
	  * Returns the total value of the pair of dice that the player needs to roll
	  * to win the game. Fluctuates between 7 (when a new game is started and the
	  * player is rolling for the first time that game) and the non-7 value that a
	  * player rolled in the first round of a game (needs to roll that to win on
	  * subsequent rolls for that game).
	  * @return rollToWin - the value that the player needs to roll to win
	*/
	public int getWinningRoll(){
		return rollToWin;
	}
	
	/**
	  * getDiceSum
	  * <p>
	  * Returns the current total value of the faces of the pair of dice
	  * @return diceValue - current total value of the pair of dice
	*/
	public int getDiceSum(){
		return diceValue;
	}
	
	/**
	 * getDie1
	 * <p>
	 * Returns a string representation of die1's current face
	 * @return visual String representation of die1's current die face
	 */
	public String getDie1(){
		return dicePair.visualDie1();
	}
	
	/**
	 * getDie2
	 * <p>
	 * Returns a string representation of die2's current face
	 * @return visual String representation of die2's current die face
	 */
	public String getDie2(){
		return dicePair.visualDie2();
	}
	
	/**
	 * specialRoll
	 * <p>
	 * Returns what the current special status of the pair of dice is. Can be "None"
	 * (no special value), "Box Cars" (two 6s = 12), "Snake Eyes" (two 1s = 2), 
	 * "Seven" (any two values that = 7).
	 * @return specialStatus - the current special status of the pair of dice
	 */
	public String specialRoll(){
		specialStatus = "None";
		if (dicePair.isBoxCars()){
			specialStatus = "Box Cars";
		}
		if (dicePair.isSnakeEyes()){
			specialStatus = "Snake Eyes";
		}
		if (dicePair.isSeven()){
			specialStatus = "Seven";
		}
		return specialStatus;
	}
} //End of CrapsHandler class
