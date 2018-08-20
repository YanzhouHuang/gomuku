
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener {
	int Screenwidth = Toolkit.getDefaultToolkit().getScreenSize().width;// Get
																		// the
																		// Screen
																		// Width
	int Screenheight = Toolkit.getDefaultToolkit().getScreenSize().height;// Get
																			// the
																			// Screen
																			// Height
	// private static final long serialVersionUID = 1L;
	ChessBoard cb = new ChessBoard();
	JLabel banner;// Create the banner
	JLabel title;
	JLabel background1;
	JLabel background2;
	JLabel background3;
//	JPanel panel;
	JButton vsplayer;
	JButton vscom;
	JButton newgame;
	JButton exit;
	public static Boolean AI = false;
	public int AIturn = 0;// Input the computer's chess is 0.
	public int Userturn = 1;// Input the player's chess is 1.

	public MainFrame() {
		super("Gomoku Game");
		setSize(1500, 1500);// Set up the size of the window
		setLayout(null);
		setLocation((Screenwidth - 1500) / 2, (Screenheight - 1500) / 2);// Set
																			// the
																			// location
																			// of
																			// the
																			// game
		banner = new JLabel("CS 396 Project", JLabel.CENTER);// Set up the banner 
		banner.setFont(new Font("Arial", Font.BOLD, 40));
		banner.setForeground(Color.white);
		banner.setLayout(null);
		banner.setBackground(new Color(255, 222, 173));
		banner.setOpaque(true);// let the content pane to be transparent
		banner.setBounds(0, 0, 1500, 100);
		add(banner); 
		title = new JLabel("Gomoku", JLabel.CENTER);
		title.setLayout(null);
		title.setOpaque(false);
		title.setFont(new Font("Bauhaus 93", Font.ITALIC, 200));
		title.setForeground(new Color(70, 130, 180));
		title.setBounds(240, 400, 1000, 250);
		add(title); // game name
		vsplayer = new JButton("Player VS Player");// Set up the button- player vs player
		vsplayer.addActionListener(this);
		vsplayer.setLayout(null);
		vsplayer.setFont(new Font("Arial Narrow", Font.BOLD, 40));
		vsplayer.setBounds(560, 800, 360, 60);
		//vsplayer.setForeground(new Color(193, 255, 193));
		
		vscom = new JButton("Player VS COM"); // Set up the button-vscom
		vscom.addActionListener(this);
		vscom.setLayout(null);
		vscom.setFont(new Font("Arial Narrow", Font.BOLD, 40));
		vscom.setBounds(560, 860, 360, 60);
		//vscom.setForeground(new Color(193, 255, 193));
		
		exit = new JButton("Quit"); // Set up the button-Quit the game, back to the window.
		exit.addActionListener(this);
		exit.setLayout(null);
		exit.setFont(new Font("Arial Narrow", Font.BOLD, 40));
		exit.setBounds(560, 920, 360, 60);
		//exit.setForeground(new Color(193, 255, 193));
		add(vsplayer);
		add(vscom);
		add(exit);
	//	panel = new JPanel();
	//	panel.setLayout(null);
		background1 = new JLabel();
		ImageIcon icon1 = new ImageIcon("D:/image/thinking.png");// i have put the images in the image fold in game2 zip.file. you may need to change the image path.
		icon1.setImage(
				icon1.getImage().getScaledInstance(icon1.getIconWidth(), icon1.getIconHeight(), Image.SCALE_DEFAULT));
		background1.setIcon(icon1);
		 background1.setLayout(null);
		background1.setOpaque(true);
		background1.setBounds(0, 000, 200,1500);
		background1.setBackground(new Color(255, 222, 173));
		background1.setHorizontalAlignment(0);
		add(background1);
		background2 = new JLabel();
		ImageIcon icon2 = new ImageIcon("D:/image/sad.png");
		icon2.setImage(
				icon2.getImage().getScaledInstance(icon1.getIconWidth(), icon1.getIconHeight(), Image.SCALE_DEFAULT));
		background2.setIcon(icon2);
		 background2.setLayout(null);
		background2.setOpaque(true);
		background2.setBounds(1300, 000, 200, 1500);
		background2.setBackground(new Color(255, 222, 173));
		background2.setHorizontalAlignment(0);
		add(background2);
		background3 = new JLabel();	
		ImageIcon icon3 = new ImageIcon("D:/image/background3.jpg");
		icon3.setImage(
				icon3.getImage().getScaledInstance(icon3.getIconWidth(), icon3.getIconHeight(), Image.SCALE_DEFAULT));
		background3.setIcon(icon3);
		 background3.setLayout(null);
		background3.setOpaque(true);
		background3.setBounds(000, 1200, 1500,300);
		background3.setBackground(new Color(255, 222, 173));
		background3.setHorizontalAlignment(0);
		add(background3);
		
		/*newgame = new JButton();
		 * newgame.setBounds(400, 10, 300, 50);
		newgame.addActionListener(this);
		newgame.setFont(new Font("Arial Narrow", Font.BOLD, 30));
		newgame.setBackground(new Color(240 ,255 ,255));
		newgame.setForeground(new Color(250, 255, 255));
		add(newgame);*/
		cb.setBounds(200, 100, 1100, 1100);
		
		add(vsplayer);
		add(vscom);
		

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Close the window exit
														// the game
		setResizable(false);// Lock the size of the game window

	}

	public static void main(String args[]) {
		MainFrame mf = new MainFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vsplayer) {// Press the vsplayer button to enter
										// the ChessPanel

			add(cb);
			AI = false; // Turn off AI
			MainFrame mf = new MainFrame();
			mf.setVisible(false);
			cb.setVisible(true);// Only show the chess board
			vsplayer.setVisible(false);
			vscom.setVisible(false);
			title.setVisible(false);
		//	banner.setVisible(false);
			//background1.setVisible(false);
			exit.setVisible(false);
			

		} else if (e.getSource() == vscom) { // Press the vscom button to enter
												// the ChessPanel

			AI = true;// Turn on AI
			add(cb);
			MainFrame mf = new MainFrame();
			mf.setVisible(false);
			cb.setVisible(true);// Only show the chess board
			vscom.setVisible(false);
			vsplayer.setVisible(false);
			title.setVisible(false);
			exit.setVisible(false);
		//	banner.setVisible(false);
			//background1.setVisible(false);

		}
		// else if (e.getSource() == newgame) {
		// this.removeAll();
		// add(cp);
		// }

		else if (e.getSource()==exit){
			System.exit(0);
		}
	}
}
