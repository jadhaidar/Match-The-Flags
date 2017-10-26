/*
 * Name:	Jad Haidar
 * Date:	11/15/2015
 * Build:	1.2
 */

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings({ "serial" })
public class UserInterface extends JFrame {
	protected JPanel contentPane;
	protected JMenuBar menuBar;
	protected JMenu menuFile;
	protected JMenu menuHelp;
	protected JMenu newgame;
	protected JMenuItem easy;
	protected JMenuItem normal;
	protected JMenuItem hard;
	protected JMenuItem insane;
	protected JMenuItem pause;
	protected JMenuItem exit;
	protected JMenuItem about;
	protected JLabel lblSort;
	protected JButton newGame;
	protected JButton buttonEasy;
	protected JButton buttonNormal;
	protected JButton buttonHard;
	protected JButton buttonInsane;
	protected String[] difficulty = {"Easy", "Normal", "Hard", "Insane"};
	protected JComboBox<String> comboBox;
	
	//=================================================================
	//==================== overloaded constructor =====================
		
	public UserInterface() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setBounds(100, 100, 850, 550);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Match The Flags");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//======================== menu items =========================
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuFile = new JMenu("File");
		menuBar.add(menuFile);

		newgame = new JMenu("New Game");
		menuFile.add(newgame);
		
		easy = new JMenuItem("Easy");
		newgame.add(easy);
		
		normal = new JMenuItem("Normal");
		newgame.add(normal);

		hard = new JMenuItem("Hard");
		newgame.add(hard);
		
		insane = new JMenuItem("Insane");
		newgame.add(insane);

		exit = new JMenuItem("Exit");
		menuFile.add(exit);

		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		
		about = new JMenuItem("About");
		menuHelp.add(about);
		
		//========================= landing screen ========================
		
		// Choose Difficulty label:
		JLabel chooseDifficulty = new JLabel("Choose Difficulty:");
		chooseDifficulty.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chooseDifficulty.setHorizontalAlignment(SwingConstants.CENTER);
		chooseDifficulty.setBounds(253, 271, 144, 30);
		contentPane.add(chooseDifficulty);
		
		// Easy / Normal / Hard / Insane choices
		comboBox = new JComboBox<String>(difficulty);
		comboBox.setBounds(413, 273, 160, 30);
		contentPane.add(comboBox);
		
		// New Game button
		newGame = new JButton("New Game");
		newGame.setBounds(262, 320, 311, 30);
		contentPane.add(newGame);
		
		// Background image
		JLabel background = new JLabel(new ImageIcon(UserInterface.class.getResource("/landing.png")));
		background.setBounds(0, 0, 845, 510);
		contentPane.add(background);
		
		//=================================================================
		//========================= end of code ===========================
	}
}

