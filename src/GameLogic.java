/*
 * Name:	Jad Haidar
 * Date:	11/15/2015
 * Build:	1.2
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;
//import java.io.File;
//import java.io.FilenameFilter;

@SuppressWarnings("serial")
public class GameLogic extends UserInterface {
	private boolean enabled = true;	// to disable mouse clicks while the 1 second timer is active
	
	// flags array for each difficulty
	private String[] isEasy = {"Brazil.png", "Canada.png", "France.png", "Germany.png", "GreatBritain.png", "Japan.png", "Lebanon.png", "NorthKorea.png", "Sweden.png"};
	private String[] isNormal = {"Brazil.png", "Canada.png", "Croatia.png", "Cuba.png", "France.png", "Germany.png", "GreatBritain.png", "Jamaica.png", "Japan.png", "Lebanon.png", "NorthKorea.png", "Sweden.png"};
	private String[] isHard = {"Australia.png", "Brazil.png", "Canada.png", "Croatia.png", "Cuba.png", "France.png", "Germany.png", "GreatBritain.png", "Greece.png", "Jamaica.png", "Japan.png", "Kuwait.png", "Lebanon.png", "NorthKorea.png", "Sweden.png"};
	private String[] isInsane = {"Australia.png", "Brazil.png", "Canada.png", "Croatia.png", "Cuba.png", "France.png", "Germany.png", "GreatBritain.png", "Greece.png", "Jamaica.png", "Japan.png", "Kuwait.png", "Lebanon.png", "Malaysia.png", "NorthKorea.png", "Philippines.png", "Sweden.png", "UAE.png"};
	
	protected int firstClick = -1, secondClick = -1;	// to keep track of clicks
	protected int turn, match;							// to keep track how many turns used, and how many cards matched
	protected long startTime;							// to keep track of time
	
	// Generates the flags based on which difficulty was selected
	// As well as sets the game mechanics
	protected void generateFlags(String difficulty) {
		//String[] pngFiles = listPngFiles(filePath);	// old code				
		String[] pngFiles = getFlags(difficulty);					// assigns the correct array (based on difficulty) to the pngFiles array
		List<Integer> shuffle = shuffleImages(pngFiles.length);		// creates a shuffled list of indices with twice the size of the array
		JButton[] cards = new JButton[shuffle.size()];				// initializes an array of buttons, 2 for each flag
		
		// loop through cards array
		for(int i=0; i<cards.length; i++) {
			final int temp = i;	// workaround solution to access actionListener methods
			cards[temp] = new JButton(new ImageIcon(GameLogic.class.getResource("/cardback.png"))); // set all the cards face down
			cards[temp].setContentAreaFilled(false);
			cards[temp].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0){
					if(!enabled) return; 	// disable mouse actions to prevent flip over
											// of additional cards while the timer is running
					
					int selection = 0;
					for(int c=0; c<shuffle.size(); c++)
						if(arg0.getSource() == cards[c]) selection = c;	// get which card was selected
					
					// if first click
					if(firstClick == -1) {
						firstClick = selection;
						cards[firstClick].setIcon(new ImageIcon(GameLogic.class.getResource(pngFiles[shuffle.get(temp)]))); // set 1st flag face up
					}
					
					// if second click
					else {
						secondClick = selection;
						if(firstClick == secondClick) return;	// prevents from clicking the same button twice
						
						turn++;	// new turn
						cards[secondClick].setIcon(new ImageIcon(GameLogic.class.getResource(pngFiles[shuffle.get(temp)])));	// set 2nd flag face up
						
						// if cards match
						if(cards[firstClick].getIcon().toString().equals(cards[secondClick].getIcon().toString())) {
							match++; // counter to know when to end the game
							
							// disable both cards
							cards[firstClick].setEnabled(false);
							cards[secondClick].setEnabled(false);
							
							firstClick = -1;	// resets the mouse clicks counter
							
							// end game if counter reaches the total number of individual flags
							if(match == pngFiles.length){
								long endTime = System.currentTimeMillis() - startTime; // calculates how much time has elapsed since the start of the game
								String successMage = "Game over in " + String.valueOf(turn) + " tries.\nTime: " + timeConverter(endTime);
								JOptionPane.showMessageDialog(contentPane, successMage, " You won!", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						
						// if cards don't match
						else {
							// executes after one second
							Timer time = new Timer(1000, new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									
									// flip both cards back to face down
									cards[firstClick].setIcon(new ImageIcon(GameLogic.class.getResource("/cardback.png")));
									cards[secondClick].setIcon(new ImageIcon(GameLogic.class.getResource("/cardback.png")));
									
									firstClick = -1;	// resets the mouse clicks counter
									enabled = true;		// re-enables mouse click on cards
								}
							});
							
							// executes first
							enabled = false;	// disables mouse clicks on cards
							time.start();
							time.setRepeats(false);
						}
					}
				}
			});
			contentPane.add(cards[i]);	
		}
		contentPane.updateUI();
	}
	
	//=================================================================
	//===================== private helper methods ====================
	
	// Converts game session timer into an appropriate MM minutes and SS seconds format
	private String timeConverter(long endTime) {
		long days = TimeUnit.MILLISECONDS.toDays(endTime);			// days
		endTime -= TimeUnit.DAYS.toMillis(days);
		long hours = TimeUnit.MILLISECONDS.toHours(endTime);		// hours
		endTime -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(endTime);	// minutes
		endTime -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(endTime);	// seconds
        
        StringBuilder sb = new StringBuilder();
        sb.append(minutes);
        sb.append(" minutes and ");
        sb.append(seconds);
        sb.append(" seconds.");
        return(sb.toString());
	}
	
	// Creates a list of shuffled indices
	private List<Integer> shuffleImages(int numOfImages) {
		List<Integer> list = new ArrayList<>();
	    
		for(int i = 0; i < numOfImages; ++i) {
			list.add(i);
			list.add(i);
		}
	    
		Collections.shuffle(list);
	    return list;
	}
	
	// Returns the correct array based on which difficulty was selected
	private String[] getFlags(String difficulty) {
		if(difficulty.equals("easy")) return isEasy;
		else if(difficulty.equals("normal")) return isNormal;
		else if(difficulty.equals("hard")) return isHard;
		else return isInsane;
	}
	
	//=================================================================
	//=================================================================
	
	/*
	 * 	Having to dynamically retrieve the flag images didn't make sense because
	 * 	I had multiple copies of each flag divided in folders by difficulty.
	 * 	Also I don't want someone to replace the png files I specifically chose
	 * 	with one of theirs as that would break the look of the game (the image
	 *	image resolutions would most likely be different, as well as the theme
	 *	of the game e.g country flags). Moreover, I want to export the project 
	 *	into an executable jar file, which includes all the image resources 
	 *	required to run the game, as I didn't want the game to stay confined 
	 *	within Eclipse only. Finally, considering that the resource file musn't
	 *	change often, it made more sense to just hard code the image directories
	 *	inside arrays based on their difficulty.
	 *	That being said, I kept the old code here for future reference.
	 */
	
//	// Converts the file array into a string array
//	private String[] listPngFiles(String filePath) {
//		File[] imagesFileArray = imageReader(filePath);	// file array
//		String[] imagesStringArray = new String[imagesFileArray.length];
//		
//		for(int i=0; i<imagesFileArray.length; i++) {
//			imagesStringArray[i] = "" + imagesFileArray[i];
//			imagesStringArray[i] = imagesStringArray[i].substring(6);	// discards "/images" from directory string
//		}
//			
//		return imagesStringArray;
//	}
//	
//	// Analyzes specified folder and returns a file array
//	// populated with the .png files found in that folder
//	private File[] imageReader(String filePath) {
//		File folder = new File(filePath);
//		return folder.listFiles (new FilenameFilter() { 
//			public boolean accept(File filePath, String filename)
//			{ return filename.endsWith(".png"); }
//		});
//	}
	
	//=================================================================
	//========================= end of code ===========================	
}

