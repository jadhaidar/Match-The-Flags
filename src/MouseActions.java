/*
 * Name:	Jad Haidar
 * Date:	11/15/2015
 * Build:	1.2
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MouseActions extends GameLogic {
	
	//=================================================================
	//==================== overloaded constructor =====================
	
	public MouseActions() {
		newgameAction();
		newgameEasyAction();
		newgameNormalAction();
		newgameHardAction();
		newgameInsaneAction();
		exitAction();
		aboutAction();
	}

	//=================================================================
	//=================== actionListener methods ======================
	
	// New Game (landing screen)
	private void newgameAction() {
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selection = (String) comboBox.getSelectedItem();
					
				switch(selection) {
				case "Easy":
					generateGame("easy");
					break;
				case "Normal":
					generateGame("normal");
					break;
				case "Hard":
					generateGame("hard");
					break;
				case "Insane":
					generateGame("insane");
					break;
				}
			}
		});
	}
	
	// New Game - Easy (menu)
	private void newgameEasyAction() {
		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				generateGame("easy");
			}
		});
	}
	
	// New Game - Normal (menu)
	private void newgameNormalAction() {
		normal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				generateGame("normal");
			}
		});
	}
	
	// New Game - Hard (menu)
	private void newgameHardAction() {
		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				generateGame("hard");
			}
		});
	}
	
	// New Game - Insane (menu)
	private void newgameInsaneAction() {
		insane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				generateGame("insane");
			}
		});
	}
	
	// About (menu)
	private void aboutAction() {
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				String aboutMessage = "Developed by Jad Haidar\nVersion 1.2\nLast updated on 11/15/2015";
				JOptionPane.showMessageDialog(contentPane, aboutMessage, "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	// Exit (menu)
	private void exitAction() {
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				 System.exit(0);
			}
		});
	}
	
	//=================================================================
	//===================== private helper method =====================
	
	// Resets game variables and generates the cards
	private void generateGame(String filePath) {
		turn = 0;
		match = 0;
		firstClick = -1;
		secondClick = -1;
		startTime = System.currentTimeMillis();
		contentPane.removeAll();
		contentPane.setLayout(new GridLayout(6,3));
		generateFlags(filePath);
	}

	//=================================================================
	//========================= end of code ===========================
}

