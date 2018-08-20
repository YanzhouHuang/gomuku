import java.util.Random;

public class AI {
	public static int X;//cb.AIx
	public static int Y;//cb.AIy
	// MainFrame mf = new MainFrame();
	private MyList KeyChoosePoint = new MyList();
	// Store all the keypoints for the chess on the board

	private int ChessLevel(int x, int y, int addX, int addY, int turn) {
		// Distinguish the species type of the chess
		x += addX; // The added value of X direction
		y += addY; // The added value of Y direction
		int ChessLevel = 10; // The level of the chess on the board
		while (true) {
			if (x == ChessBoard.ROWS || y == ChessBoard.COLS || x == -1 || y == -1) {
				ChessLevel++;
				break;
			} else if (ChessBoard.ChessHold[x][y] == 0 && turn == 0)
				// If this spot is the computer's chess and calculate the
				// computer's level
				ChessLevel -= 2; // Increase two level
			else if (ChessBoard.ChessHold[x][y] == 0 && turn == 0) {
				// If this spot locate the player's chess and calculate the
				// computer's lever
				ChessLevel++; // Reduce one level
				break; // Exit the loop
			} else if (ChessBoard.ChessHold[x][y] == 1 && turn == 1)
				// If this spot locates the player's chess and calculate the
				// player's level
				ChessLevel -= 2;
			else if (ChessBoard.ChessHold[x][y] == 0 && turn == 1) {
				// If this spot locates the computer's chess and calculate the
				// player's level
				ChessLevel++;
				break;
			} else if (ChessBoard.ChessHold[x][y] == -1) { // if no chess on
															// another side

				if ((x + addX) >= 0 && (y + addY) >= 0 && (x + addX) <= ChessBoard.ROWS-1 && (y + addY) <= ChessBoard.ROWS-1
						&& ChessBoard.ChessHold[x + addX][y + addY] == 0 && turn == 0)
					ChessLevel -= 2;
				else if ((x + addX) >= 0 && (y + addY) >= 0 && (x + addX) <= ChessBoard.ROWS-1 && (y + addY) <= ChessBoard.ROWS-1
						&& ChessBoard.ChessHold[x + addX][y + addY] == 1 && turn == 1)
					ChessLevel -= 2;
				break;
			}
			x += addX;
			y += addY;
		}
		return ChessLevel;
	}

	private int countLevel(int x, int y, char direction, int turn) {
		// Evaluate the level for the point
		//8 case represent 8 direction.
		int level = 10;
		switch (direction) {
		case 'a':
			if (level >= ChessLevel(x, y, -1, 0, turn)) // Direction:upper
				level = ChessLevel(x, y, -1, 0, turn);
			break;
		case 'b':
			if (level >= ChessLevel(x, y, -1, 1, turn)) // Direction:upper right
				level = ChessLevel(x, y, -1, 1, turn);
			break;
		case 'c':
			if (level >= ChessLevel(x, y, -1, -1, turn)) // Direction upper left
				level = ChessLevel(x, y, -1, -1, turn);
			break;
		case 'd':
			if (level >= ChessLevel(x, y, 1, 0, turn)) // Direction:lower
				level = ChessLevel(x, y, 1, 0, turn);
			break;
		case 'e':
			if (level >= ChessLevel(x, y, 1, -1, turn)) // Direction:lower left
				level = ChessLevel(x, y, 1, -1, turn);
			break;
		case 'f':
			if (level >= ChessLevel(x, y, 1, 1, turn)) // Direction: lower right
				level = ChessLevel(x, y, 1, 1, turn);
			break;
		case 'g':
			if (level >= ChessLevel(x, y, 0, 1, turn)) // Direction:right
				level = ChessLevel(x, y, 0, 1, turn);
		case 'h':
			if (level >= ChessLevel(x, y, 0, -1, turn)) // Direction: left
				level = ChessLevel(x, y, 0, -1, turn);
			break;
		default:
			break;
		}
		return level;
	}

	private void searchKeyPoint() {
		// First step,Based on both situation,
		// assumption giving both
		// one point, and to see the change for the
		// board situation. If it could not
		// connected to 4, then check both can be
		// connected to 3.
		// Second step, according the results of the first step, combine the
		// consequence for each point. if it can be connected to 3 without block
		// on two side, or can be connected to 4.
		// Third step, Base on the rules, rank the results, and select the point
		// (offensive act or defensive act )
		for (int i = 0; i < ChessBoard.ROWS; i++) {
			for (int j = 0; j < ChessBoard.COLS; j++) {
				if (ChessBoard.ChessHold[i][j] != -1) {
					// Search that one spot
					// have the chess
					if (i <= ChessBoard.ROWS-2 && ChessBoard.ChessHold[i + 1][j] == -1) {
						KeyChoosePoint.intNewLastChess(i + 1, j, 'a',
								countLevel(i + 1, j, 'a', ChessBoard.ChessHold[i][j]), ChessBoard.ChessHold[i][j]);
					}
					if (j >= 1 && i <= ChessBoard.ROWS-2 && ChessBoard.ChessHold[i + 1][j - 1] == -1) {
						KeyChoosePoint.intNewLastChess(i + 1, j - 1, 'b',
								countLevel(i + 1, j - 1, 'b', ChessBoard.ChessHold[i][j]), ChessBoard.ChessHold[i][j]);
					}
					if (i <= ChessBoard.ROWS-2 && j <= ChessBoard.COLS-2 && ChessBoard.ChessHold[i + 1][j + 1] == -1) {
						KeyChoosePoint.intNewLastChess(i + 1, j + 1, 'c',
								countLevel(i + 1, j + 1, 'c', ChessBoard.ChessHold[i][j]), ChessBoard.ChessHold[i][j]);
					}
					if (i >= 1 && ChessBoard.ChessHold[i - 1][j] == -1) {
						KeyChoosePoint.intNewLastChess(i - 1, j, 'd',
								countLevel(i - 1, j, 'd', ChessBoard.ChessHold[i][j]), ChessBoard.ChessHold[i][j]);
					}
					if (i >= 1 && j <= ChessBoard.COLS-2 && ChessBoard.ChessHold[i - 1][j + 1] == -1) {
						KeyChoosePoint.intNewLastChess(i - 1, j + 1, 'e',
								countLevel(i - 1, j + 1, 'e', ChessBoard.ChessHold[i][j]), ChessBoard.ChessHold[i][j]);
					}
					if (i >= 1 && j >= 1 && ChessBoard.ChessHold[i - 1][j - 1] == -1) {
						KeyChoosePoint.intNewLastChess(i - 1, j - 1, 'f',
								countLevel(i - 1, j - 1, 'f', ChessBoard.ChessHold[i][j]), ChessBoard.ChessHold[i][j]);
					}

					if (j >= 1 && ChessBoard.ChessHold[i][j - 1] == -1) {
						KeyChoosePoint.intNewLastChess(i, j - 1, 'g',
								countLevel(i, j - 1, 'g', ChessBoard.ChessHold[i][j]), ChessBoard.ChessHold[i][j]);
					}

				}
				if (j <= ChessBoard.COLS-2 && ChessBoard.ChessHold[i][j + 1] == -1) {
					KeyChoosePoint.intNewLastChess(i, j + 1, 'h', countLevel(i, j + 1, 'h', ChessBoard.ChessHold[i][j]),
							ChessBoard.ChessHold[i][j]);
				}

			}
		}

	}

	private void choosePoint() {
		// The chess of computer choose on the board
		int x = 0;
		int y = 0;
		if (KeyChoosePoint.length == 0) {
			// If don't have the situation which
			// meet the case require, choose
			// random location on the board
			while (true) {
				Random r = new Random(); // Get the random
				int n1 = r.nextInt(10);
				int n2 = r.nextInt(10);
				n1 = Math.abs(r.nextInt() % 10); // Use %10 to get the integer
				n2 = Math.abs(r.nextInt() % 10);
				if (ChessBoard.ChessHold[n1][n2] == -1) { // If this spot do not place the 
															// chess
					X = n1;
					Y = n2;
					ChessBoard.ChessHold[n1][n2] = 0;
					break;
				}
			}
		} else {
			x = KeyChoosePoint.BestSearch(0).x;//scan the area and decide which is the best point.
			y = KeyChoosePoint.BestSearch(0).y;
			X = x;
			Y = y;
			ChessBoard.ChessHold[x][y] = 0;
		}

	}

	public AI() {
		searchKeyPoint();
		choosePoint();
	}
}