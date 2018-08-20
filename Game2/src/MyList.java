class MyList {// linked list store the chess
	int length = 0;
	Chess firstChess;

	public int size() {
		return length;
	}

	public Chess BestSearch(int turn) {// scan the area then point the first
										// chess, if the degree is higher than
										// first chess, switch it.
		Chess C;
		Chess best;
		C = firstChess;
		best = firstChess;
		while (C != null) {
			if (best.level > C.level) {
				best = C;
			} else if (best.level == C.level) {
				if (C.turn == turn)
					best = C;
			}
			C = C.next;
		} // it is the most important, must point this one.
		return best;
	}

	// the result of Analysis
	// location
	// connection of direction and the connection count
	// point location
	// the degree of the choose point
	public void intNewLastChess(int x, int y, char direction, int level, int turn) {
		Chess C = new Chess();
		C.x = x;
		C.y = y;
		C.direction = direction;
		C.level = level;
		C.turn = turn;
		C.next = null;
		if (firstChess == null) {
			firstChess = C;
		} else {
			Chess P = firstChess;
			while (P.next != null) {
				P = P.next;
			}
			P.next = C;
		}
		length++;
	}
}