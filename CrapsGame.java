import javax.swing.JFrame;

/**
 * <b>CrapsGame Class</b>
 * <p>
 * Creates a Craps game with a visual interface.
 * <p>
 * IllegalArgumentExceptions that are caught early on in the Die class/objects are
 * passed up to this class and handled here accordingly. Any attempts to use or
 * pass illegal dice as parameters will result in a warning message and program exit.
 * <p>
 * Note: The first actual parameter (boolean) used in the construction of the 
 * CrapsPanel is what enables cheat mode or not. True is on and false is off. A 
 * variable is not used to alleviate suspicion. If authorities or gamblers get 
 * suspicious, change the value to false and any statistical tests that you then
 * carry out will be legitimate.
 * @author James Wen - jrw2175
 */

public class CrapsGame {

	/**
	* The main method for the CrapsGame program.
	* 
	* @throws IllegalArgumentException - if illegal dice are used
	*/
	public static void main(String[] args){
		try {
		boolean testingOn = false;//Set to true for autoplay, false for manual play
		JFrame crapsFrame = new JFrame("Craps Game");
		crapsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		crapsFrame.getContentPane().add(new CrapsPanel(true, testingOn));
		crapsFrame.pack();
		crapsFrame.setVisible(true);
		}
		catch (IllegalArgumentException e){
			System.out.println("Please confirm that all die objects have correct " +
							   "faces (1-6). Thanks.");
			System.exit(0);	
		}
	}
}

