import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessBoard extends Panel implements MouseListener, ActionListener {
	public static void main(String args[]) {
		MainFrame mf = new MainFrame();
	}

	public static final int Edge = 100;// The distance between the edge of the
										// background to the edge of board.
	public static final int Length_square = 60;// The length of the spots. spots
												// are set in a square.
	public static final int ROWS = 15;// The rows of the board
	public static final int COLS = 15;// The cols of the board
	public static int ChessHold[][] = new int[ROWS][COLS];// use array to hold the
														// chess
	Boolean over = false;
	int i = 0;
	int j = 0;
	int x = 0;
	int y = 0;
	int count = 0; // Get the amount of the chess on the board.
	int AIx = 0;
	int AIy = 0;
	String chessColor = "black";
	 TextField turn = new TextField("Black First!");//player always hold black
	// Button newGamevscom = new Button("VS COM again");
	// Button newGamevsplayer = new Button("VS player again");
	Button newgame = new Button("New Game");

	Judge judge = new Judge();
	// public boolean tie;

	public ChessBoard() {
		setSize(1100, 1100);
		setLayout(null);
		setBackground(new Color(244, 164, 96));
		
		
		addMouseListener(this);

		turn.setBounds(10, 0, 200, 50);//show the chess turn.
		turn.setFont(new Font("Arial Narrow", Font.BOLD, 30));
		turn.setBackground(new Color(240 ,255 ,255));
		 add(turn);

		// newGamevscom.setBounds(900, 0, 200, 50);
		// newGamevscom.addActionListener(this);
		// add(newGamevscom, null);

		// newGamevsplayer.setBounds(10, 0, 200, 50);
		// newGamevsplayer.addActionListener(this);
		// add(newGamevsplayer, null);

		newgame.setBounds(400, 10, 300, 50);
		newgame.addActionListener(this);
		newgame.setFont(new Font("Arial Narrow", Font.BOLD, 30));
		newgame.setBackground(new Color(240 ,255 ,255));
		//newgame.setForeground(new Color(250, 255, 255));
		add(newgame);

		for (i = 0; i < 15; i++) {
			for (j = 0; j < 15; j++) {
				ChessHold[i][j] = -1;//not chess on the board
			}
		}
		turn.setEditable(false);//don't let player input some words on the frame
	}

	public void paint(Graphics g) {
		for (int i = 0; i <= ROWS; i++) {// use loop to draw fifteen the
											// Horizontal line
			g.drawLine(Edge, Edge + i * Length_square, Edge + COLS * Length_square, Edge + i * Length_square);
		}
		for (int i = 0; i <= COLS; i++) {// use loop to draw fifteen the
											// vertical line
			g.drawLine(Edge + i * Length_square, Edge, Edge + i * Length_square, Edge + ROWS * Length_square);

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int a = 0;
		int b = 0;
		Black blackChess = new Black(this);
		White whiteChess = new White(this);

		if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
			x = (int) e.getX();
			y = (int) e.getY();
			// System.out.println("x"+ x +" "+"y"+ y);// test the coordinates
			if (x < Edge || x > Edge+15*Length_square || y < Edge || y > Edge+15*Length_square) {// Judge the pressed point in the board area.
				JOptionPane.showMessageDialog(this, "Out of the board!", "OPPS!", JOptionPane.INFORMATION_MESSAGE);//If out of the board, show the Message dialog
				this.paint(null); // Don't point chess on the board.
			} else {
				count++;
				a = (x + Length_square / 2) / Length_square;

				b = (y + Length_square / 2) / Length_square;
				// System.out.println(a + " " + b); test the location of the
				// chess
			}

			if (chessColor == "black" && MainFrame.AI == false) {
				this.add(blackChess);
				blackChess.setBounds(a * Length_square - 10, b * Length_square - 10, 40, 40);
				chessColor = "white";
				turn.setText("White turn");
				ChessHold[b - 2][a - 2] = 1;// black chess is 1
				if ((judge.win(a - 2, b - 2, 1)) == 1) {//show win message
					JOptionPane.showMessageDialog(this, "Black win", "The Winner is Black",
							JOptionPane.INFORMATION_MESSAGE);
					this.removeMouseListener(this);//don't let the player do some action on the board. lock the board. remove the mouselistener
				} else if ((judge.win(a - 2, b - 2, 1)) == 0) {
					JOptionPane.showMessageDialog(this, "White win", "The Winner is White",
							JOptionPane.INFORMATION_MESSAGE);
					this.removeMouseListener(this);
					
				} //else if (count >4){//use a small number to test the tie is working
				else if (count >= (ROWS * COLS - 1))// rows*cols equal to the
														// spot of the board.
				{// if the chess fill all the board, and then no one have five in row, that mean it is a tie. the game is draw.

					JOptionPane.showMessageDialog(this, "It is a tie. No winner.", "Draw",
							JOptionPane.INFORMATION_MESSAGE);
					this.removeMouseListener(this);
				}

			} 
			else if (chessColor == "black" && MainFrame.AI == true) {
				this.add(blackChess);
				blackChess.setBounds(a * Length_square - 10, b * Length_square - 10, 40, 40);//set the black chess on the center of the square
				chessColor = "white";
				 turn.setText("white turn");
				ChessHold[b - 2][a - 2] = 1;// black chess is 1
				if ((judge.win(a - 2, b - 2, 1)) == 1) {
					JOptionPane.showMessageDialog(this, "Player win", "The Winner is Black",
							JOptionPane.INFORMATION_MESSAGE);
					over = true;
					this.removeMouseListener(this);
				} else if ((judge.win(a - 2, b - 2, 1)) == 0) {
					JOptionPane.showMessageDialog(this, "Computer win", "The Winner is White",
							JOptionPane.INFORMATION_MESSAGE);
					this.removeMouseListener(this);}
					
				if (over == false) {
					new AI();
					AIx = AI.X;
					AIy = AI.Y;
					this.add(whiteChess);
					whiteChess.setBounds((AIy + 2) * Length_square - 10, (AIx + 2) * Length_square - 10, 40, 40);
					chessColor = "black";
					 turn.setText("Black turn");
					ChessHold[AIx][AIy] = 0;
					if ((judge.win(AIy, AIx, 0)) == 1) {
						JOptionPane.showMessageDialog(this, "Player win", "The Player is win",
								JOptionPane.INFORMATION_MESSAGE);
						this.removeMouseListener(this);
					} else if ((judge.win(AIy, AIx, 0)) == 0) {
						JOptionPane.showMessageDialog(this, "COM win", "The COM is win",
								JOptionPane.INFORMATION_MESSAGE);

						this.removeMouseListener(this);
					} //else if(count>=2){
					else if (count >= ((ROWS * COLS - 1) / 2))// rows*cols
																// equal to the
																// spots of the
																// board.
					{ // make a function to determining the tie when filled all
						// the board when Player vs AI
						// the count i used is just count the chess which player point, not include the computer point. so on player vs com it should be /2.
						JOptionPane.showMessageDialog(this, "It is a tie. No winner.", "Draw",
								JOptionPane.INFORMATION_MESSAGE);
						this.removeMouseListener(this);
					}
				}

			} else if (chessColor == "white" && MainFrame.AI == false) {
				this.add(whiteChess);
				whiteChess.setBounds(a * Length_square - 10, b * Length_square - 10, 40, 40);
				chessColor = "black";
				 turn.setText("Black turn");
				// white.setText("");
				ChessHold[b - 2][a - 2] = 0; // white chess is 0
				if ((judge.win(a - 2, b - 2, 0)) == 1) {
					JOptionPane.showMessageDialog(this, "Black Win", "The Winner is Black",
							JOptionPane.INFORMATION_MESSAGE);
					this.removeMouseListener(this);
				} else if ((judge.win(a - 2, b - 2, 0)) == 0) {
					JOptionPane.showMessageDialog(this, "White Win", "The Winner is White",
							JOptionPane.INFORMATION_MESSAGE);
					this.removeMouseListener(this);

				}

				else if (count >= (ROWS * COLS - 1))// rows*cols equal to the
													// spot of the board.
				{

					JOptionPane.showMessageDialog(this, "It is a tie. No winner.", "Draw",
							JOptionPane.INFORMATION_MESSAGE);
					this.removeMouseListener(this);
				}

			}

		} else {
			JOptionPane.showMessageDialog(this, "Error", "Sorry it have a error.", JOptionPane.INFORMATION_MESSAGE);//if have a error show the error dialog. the game willn't allow to move.please start a new game.
			this.removeMouseListener(this);
		}
		// System.out.println("Error!");
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	class Black extends Canvas implements MouseListener {

		
		public Black(ChessBoard chessboard) {
		
			addMouseListener(this);
			
		}

		public void paint(Graphics g) {
			g.setColor(Color.black);// set the color of the chess
			g.fillOval(0, 0, 40, 40);// set the size of the chess
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			

		}

	}

	class White extends Canvas implements MouseListener {

		

		public White(ChessBoard chessboard) {
		
			addMouseListener(this);
			
		}

		public void paint(Graphics g) {
			g.setColor(Color.white);
			g.fillOval(0, 0, 40, 40);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	

	public void actionPerformed(ActionEvent e) {
		MainFrame mf = new MainFrame();
		if (e.getSource() == newgame) {// Use the new game button to return the
										// MainFrame
			//MainFrame mf = new MainFrame();
			this.setVisible(false);
			mf.setVisible(true);

		}
	}

	

}
