import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * <b>CrapsPanel Class</b>
 * <p>
 * Creates and maintains a visual interface for the Craps game.
 * <p>
 *
 * @author James Wen - jrw2175
 */
public class CrapsPanel extends JPanel
{
	private JButton roll;
	private JButton newGame;
	private JLabel specialLabel;
	private JLabel diceSum;
	private JLabel dieLabel1;
	private JLabel dieLabel2;
	private JLabel gameProgress;
	private JLabel rollStatus;
	private JLabel valueToWin;
	private CrapsHandler casino;
	
	/**
	 * Constructs a Craps visual interface panel through labels and buttons.
	 * <p>
	 * Also initiates automatic play for testing purposes if autoPlay is on/true.
	 * @param playerBonus - whether cheat mode is on or not
	 * @param autoPlay - whether automatic play for testing purposes is on or not
	 */
	public CrapsPanel(boolean playerBonus, boolean autoPlay){
		//Creates all the required buttons and labels
		setLayout(null);
		casino = new CrapsHandler();
		roll = new JButton ("Roll Dice");
		roll.addActionListener(new CrapsListener());
		newGame = new JButton ("New Game");
		newGame.addActionListener(new NewGameListener());
		diceSum = new JLabel ("Dice Value: " + casino.getDiceSum());
		specialLabel = new JLabel ("Special Roll: " + casino.specialRoll());
		dieLabel1 = new JLabel (casino.getDie1());
		dieLabel2 = new JLabel (casino.getDie2());
		gameProgress = new JLabel ("Game Status: " + "New Game");
		rollStatus = new JLabel ("Player Status: " + "Should Roll");
		valueToWin = new JLabel ("Roll needed to Win: " + "7");
		
		casino.bonusSwitch(playerBonus);//Turns cheat mode on or off
		
		//Change the label font color to white for visibility against background
		diceSum.setForeground(Color.white);
		specialLabel.setForeground(Color.white);
		dieLabel1.setForeground(Color.white);
		dieLabel2.setForeground(Color.white);
		gameProgress.setForeground(Color.white);
		rollStatus.setForeground(Color.white);
		valueToWin.setForeground(Color.white);
		
		//Arranges the buttons and labels using absolute positioning
		gameProgress.setBounds(75, 10, 200, 20);
		rollStatus.setBounds(75, 30, 150, 20);
		valueToWin.setBounds(75, 50, 150, 20);
		diceSum.setBounds(75, 80, 100, 20);
		specialLabel.setBounds(75, 100, 200, 20);
		dieLabel1.setBounds(80, 125, 100, 100);
		dieLabel2.setBounds(180, 125, 100, 100);
		roll.setBounds(160, 225, 100, 50);
		newGame.setBounds(40, 225, 100, 50);
		
		//Adds all the buttons and labels to the panel
		add(roll);
		add(newGame);
		add(diceSum);
		add(specialLabel);
		add(dieLabel1);
		add(dieLabel2);
		add(gameProgress);
		add(rollStatus);
		add(valueToWin);
		
		//Sets panel size and background color
		setPreferredSize (new Dimension (300,300));
		Color casinoVelvet = new Color(16, 101, 46);//emulates casino table velvet
		setBackground(casinoVelvet);
		
		//Initiates Autoplay
		if (autoPlay){
			testGame();
		}
	}
	
	/**
	 * testGame
	 * <p>
	 * Facilitates automatic play against the computer (1000 games) and records
	 * the number of games and player wins (Player is also CPU).
	 * <p>
	 * The visual interface for the results of this automatic play have the buttons
	 * replaced with the labels describing the number of games played and 
	 * player wins.
	 */
	private void testGame(){
		//Removes buttons from interface (unnecessary)
		remove(roll);
		remove(newGame);
		
		//Autoplays 1000 games
		int gameNumber = 0;
		int playerWins = 0;
		while (gameNumber<1000){//Autoplays until 1000 games have been played
			casino.newGame();
			updateGame();
			//Plays through a game until it is over and game status is "New Game?"
			while (!("New Game?").equals(casino.getGameStatus())){
				casino.rollDice();
				casino.applyRules();
				updateGame();
			}
			if (("Has Won!").equals(casino.getPlayerStatus())){
				playerWins+=1;//Records player wins
			}
			gameNumber+=1;//Records number of games
		}
		//Creates player wins and number of games played labels
		JLabel numPlayerWins = new JLabel ("Number of Player Wins: " + playerWins);
		JLabel numPlays = new JLabel ("Number of Games: " + gameNumber);
		
		//Makes new label fonts white for visibility
		numPlayerWins.setForeground(Color.white);
		numPlays.setForeground(Color.white);
		
		//Adds new labels
		add(numPlayerWins);
		add(numPlays);
		
		//Arranges new labels using absolute positioning
		numPlays.setBounds(75, 215, 200, 50);
		numPlayerWins.setBounds(75, 240, 200, 50);
	}
	
	/**
	 * updateGame
	 * <p>
	 * Updates all labels to represent current state of player, dice, and game.
	 */
	private void updateGame(){
		specialLabel.setText("Special Roll: " + casino.specialRoll());
		diceSum.setText("Dice Value: " + casino.getDiceSum());
		dieLabel1.setText(casino.getDie1());
		dieLabel2.setText(casino.getDie2());
		gameProgress.setText("Game Status: " + casino.getGameStatus());
		rollStatus.setText("Player Status: " + casino.getPlayerStatus());
		valueToWin.setText("Roll needed to Win: " + casino.getWinningRoll());
	}
	
	/**
	 * <b>CrapsListener Class</b>
	 * <p>
	 * An action listener that rolls the dice, applies Craps rules, and updates 
	 * visual interface accordingly when the roll button is clicked.
	 * <p>
	 * If the current game is over and played is being prompted to start a new game,
	 * the roll button will be faded out and unable to be clicked because the 
	 * player should not be rolling the dice when the game is over.
	 * @author James Wen - jrw2175
	 */
	private class CrapsListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			casino.rollDice();
			casino.applyRules();
			updateGame();
			if (("New Game?").equals(casino.getGameStatus())){
				roll.setEnabled(false);//Fades out and disables roll button click
			}
		}
	}
	
	/**
	 * <b>NewGame Class</b>
	 * <p>
	 * An action listener that starts a new game and updates visual interface 
	 * accordingly when the roll button is clicked.
	 * <p>
	 * If a new game is started, then the roll button is made active and able to be
	 * clicked again.
	 * @author James Wen - jrw2175
	 */
	private class NewGameListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			casino.newGame();
			updateGame();
			roll.setEnabled(true); //Enables the roll button
		}
	}
} //End of CrapsPanel class