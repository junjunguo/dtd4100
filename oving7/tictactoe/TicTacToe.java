package oving7.tictactoe;

public class TicTacToe {
	private int			sumere	= 0;
	private char[][]	tabel;
	private int			M;
	private int			NxN;

	private boolean		isNxN;

	public TicTacToe() {
		isNxN = false;
		init();
		this.M = 3;
		this.NxN = 3;
	}

	public TicTacToe(int M, int NxN) {
		if (M == 3 && NxN == 3) {
			isNxN = false;
			init();
		} else {
			this.NxN = NxN;
			this.M = M;
			isNxN = true;
			init();
		}
	}

	public void init() {
		if (isNxN) {
			tabel = new char[NxN][NxN];
			for (int i = 0; i < NxN; i++) {
				for (int j = 0; j < NxN; j++) {
					this.tabel[i][j] = ' ';
				}
			}

		} else {
			char[][] tabel = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' },
					{ ' ', ' ', ' ' }, };
			this.tabel = tabel;
		}
	}

	public char[][] getTabel() {
		return tabel;
	}

	public void setNy(char[][] tabel, int M, int NxN, int sumere) {
		this.tabel = tabel;
		this.sumere = sumere;
		this.M = M;
		this.NxN = NxN;
	}

	public char turen() {// kunne dele med to en øke sumere en ruren

		if (sumere % 2 == 0) {
			return 'O';
		}
		return 'X';
	}

	public void setInn(int x, int y) {
		if (sumere % 2 == 0) {
			tabel[x][y] = 'O';
		} else {
			tabel[x][y] = 'X';
		}
		sumere++;
	}

	public boolean isValid(String s) {
		if (s.length() != 2) {
			return false;
		}
		String tall = "0123456789";
		char x = s.charAt(0);
		char y = s.charAt(1);
		if ((tall.indexOf(x) != -1) && (tall.indexOf(y) != -1)
				&& Integer.parseInt("" + x) < NxN
				&& Integer.parseInt("" + y) < NxN) {
			if (tabel[Integer.parseInt("" + x)][Integer.parseInt("" + y)] == ' ') {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		if (isNxN) {
			String s = "";
			for (int i = 0; i < NxN; i++) {
				s += "\n" + " | ";
				for (int j = 0; j < NxN; j++) {
					s += tabel[i][j] + " | ";
				}
			}
			return "spileren : " + turen() + "\n" +s;
		} else {
			if (vinne() != ' ') {
				return "spileren : " + vinne() + " vinne!" + "\n" + tabel[0][0]
						+ " | " + tabel[0][1] + " | " + tabel[0][2] + "\n"
						+ tabel[1][0] + " | " + tabel[1][1] + " | "
						+ tabel[1][2] + "\n" + tabel[2][0] + " | "
						+ tabel[2][1] + " | " + tabel[2][2];
			}
			if (sumere == 9) {
				return "ingen vinne!" + "\n" + tabel[0][0] + " | "
						+ tabel[0][1] + " | " + tabel[0][2] + "\n"
						+ tabel[1][0] + " | " + tabel[1][1] + " | "
						+ tabel[1][2] + "\n" + tabel[2][0] + " | "
						+ tabel[2][1] + " | " + tabel[2][2];
			}
			return "spileren : " + turen() + "\n" + tabel[0][0] + " | "
					+ tabel[0][1] + " | " + tabel[0][2] + "\n" + tabel[1][0]
					+ " | " + tabel[1][1] + " | " + tabel[1][2] + "\n"
					+ tabel[2][0] + " | " + tabel[2][1] + " | " + tabel[2][2];
		}
	}

	public char vinne() {
		if (isNxN) {
			for (int i = 0; i < NxN; i++) {
				int x = 0;
				int o = 0;
				for (int j = 0; j < NxN; j++) {

					// sjekk rad > =M
					if (tabel[i][j] == 'X') {
						x++;
						o = 0;
						if (x >= M) {
							return 'X';
						}
					}
					if (tabel[i][j] == 'O') {
						o++;
						x = 0;
						if (o >= M) {
							return 'O';
						}
					}

					// sjekk colum > = M
					if (tabel[j][i] == 'X') {
						x++;
						o = 0;
						if (x >= M) {
							return 'X';
						}
					}
					if (tabel[j][i] == 'O') {
						o++;
						x = 0;
						if (o >= M) {
							return 'O';
						}
					}

					// sjekk diagonal >= M
				}
				// return ' ';
			}
		}
		if (tabel[0][0] == tabel[0][1] && tabel[0][1] == tabel[0][2]) {
			return tabel[0][0];
		}
		if (tabel[1][0] == tabel[1][1] && tabel[1][1] == tabel[1][2]) {
			return tabel[1][0];
		}
		if (tabel[2][0] == tabel[2][1] && tabel[2][1] == tabel[2][2]) {
			return tabel[2][0];
		}

		if (tabel[0][0] == tabel[1][0] && tabel[1][0] == tabel[2][0]) {
			return tabel[0][0];
		}
		if (tabel[0][1] == tabel[1][1] && tabel[1][1] == tabel[2][1]) {
			return tabel[0][1];
		}
		if (tabel[0][2] == tabel[1][2] && tabel[1][2] == tabel[2][2]) {
			return tabel[0][2];
		}

		if (tabel[0][0] == tabel[1][1] && tabel[1][1] == tabel[2][2]) {
			return tabel[0][0];
		}
		if (tabel[0][2] == tabel[1][1] && tabel[1][1] == tabel[2][0]) {
			return tabel[0][2];
		}
		return ' ';

	}

	public int getSumere() {
		return sumere;
	}

	public void setSumere(int i) {
		sumere = i;
	}

	public int getBlock() {
		return NxN * NxN;
	}

	public int getM() {
		return M;
	}

	public int getNxN() {
		return NxN;
	}
}
