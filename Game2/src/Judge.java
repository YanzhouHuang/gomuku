public class Judge {
	public int direct[][] = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { -1, 0 }, { -1, -1 }, { 0, -1 }, { -1, 1 } };
	
	// input eight possible connection way to win the game right and left
	// horizontally,up and down vertically, left and right diagonally and the
	// reverse dircetion.
	public int win(int x, int y, int role) {
		int turn = -1;
		for (int i = 0; i < 8; i++) {
			if (directSearch(x, y, direct[i][0], direct[i][1], role) == 5) { // the
																				// connect
																				// amount
																				// of
																				// the
																				// chess
																				// is
																				// five
				if (role == 0) { // white is 0
					System.out.println("White Win！");
					turn = 0;
				} else if (role == 1) { // black is 1
					System.out.println("Black Win！！");
					turn = 1;
				}
			}
		}
		return turn;
	}

	public int directSearch(int x, int y, int dx, int dy, int role) { // Search
																		// for
																		// eight
																		// directions
		int length = 1;
		int x_ = x;
		int y_ = y; // Hold the location of the beginning
		for (int i = 1; i <= 5; i++) {
			x += dx; // Forward
			y += dy;
			if (x == 15 || y == 15 || x == -1 || y == -1) {
				break;
			}
			// Arrive the edge
			else if (ChessBoard.ChessHold[y][x] == role) {
				length++;
			} else if (ChessBoard.ChessHold[y][x] != role) {
				break;
			}
		}
		x = x_;
		y = y_;
		for (int i = 1; i <= 5; i++) { // Reverse operation to find the direct
			x -= dx; // Reverse
			y -= dy;
			if (x == 15 || y == 15 || x == -1 || y == -1) {
				break;
			} else if (ChessBoard.ChessHold[y][x] == role) {
				length++;
			} else if (ChessBoard.ChessHold[y][x] != role) {
				break;
			}
		}
		return length;
	}

}