package exam2008sjakk;

import java.util.ArrayList;

public class Board {
    
    private static ArrayList<ArrayList<Piece>> board;
    
    public Board() {
        board = new ArrayList<ArrayList<Piece>>();
    }
    
    // ░ ⓐ ⓑ ⓒ ⓓ ⓔ ⓕ ⓖ ⓗ ░
    // 8 ♜ ♞ ♝ ♛ ♚ ♝ ♞ ♜ 8
    // 7 ♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟ 7
    // 6 ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ 6
    // 5 ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ 5
    // 4 ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ 4
    // 3 ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ 3
    // 2 ♙ ♙ ♙ ♙ ♙ ♙ ♙ ♙ 2
    // 1 ♖ ♘ ♗ ♕ ♔ ♗ ♘ ♖ 1
    // ░ ⓐ ⓑ ⓒ ⓓ ⓔ ⓕ ⓖ ⓗ ░
    public void initBoard() {
        for (int i = 0; i < 8; i++) {
            ArrayList<Piece> insideList = new ArrayList<Piece>();
            for (int j = 0; j < 8; j++) {
                insideList.add(null);
            }
            board.add(insideList);
        }
        for (int i = 0; i < 8; i++) {
            board.get(1).set(i, new Pawn("WHITE"));
            board.get(6).set(i, new Pawn("BLACK"));
        }
        board.get(0).set(0, new Rook("WHITE"));
        board.get(0).set(1, new Knight("WHITE"));
        board.get(0).set(2, new Bishop("WHITE"));
        board.get(0).set(3, new Queen("WHITE"));
        board.get(0).set(4, new King("WHITE"));
        board.get(0).set(5, new Bishop("WHITE"));
        board.get(0).set(6, new Knight("WHITE"));
        board.get(0).set(7, new Rook("WHITE"));
        
        board.get(7).set(0, new Rook("BLACK"));
        board.get(7).set(1, new Knight("BLACK"));
        board.get(7).set(2, new Bishop("BLACK"));
        board.get(7).set(3, new Queen("BLACK"));
        board.get(7).set(4, new King("BLACK"));
        board.get(7).set(5, new Bishop("BLACK"));
        board.get(7).set(6, new Knight("BLACK"));
        board.get(7).set(7, new Rook("BLACK"));
    }
    
    public Piece getPiece(String position) {
        int x = position.charAt(0) - 'a';
        int y = position.charAt(1) - '1';
        return board.get(7 - y).get(x);
    }
    
    public String getPiecePosition(Piece piece) {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (piece.equals(board.get(i).get(j))) {
                    char x = (char) (j + 'a');
                    return "" + x + "" + (i + 1);
                }
            }
        }
        return null;
    }
    
    public void setPiece(String position, Piece piece) {
        if (isValidPosition(position)) {
            int x = position.charAt(0) - 'a';
            int y = position.charAt(1) - '0';
            board.get(y).set(x, piece);
        } else {
            throw new IllegalArgumentException("not vaild position");
        }
    }
    
    public boolean isValidPosition(String position) {
        if (position.length() != 2) {
            return false;
        }
        int x = position.charAt(0) - 'a';
        int y = position.charAt(1) - '0';
        if (x >= 0 & x < 8 & y >= 0 & y < 8) {
            return true;
        }
        return false;
    }
    
    public boolean isLegalMove(PieceColor color, String from, String to) {
        if (color.getPieceColor() == this.getPiece(from).getPieceColor()
                .getPieceColor()) {
            return true;
        }
        return false;
    }
    
    public void movePiece(String from, String to) {
        this.setPiece(to, this.getPiece(from));
        this.setPiece(from, null);
    }
    
    // ░░a░ b░c░d ░e░f░░g░h░░
    // 8 ♜ ♞ ♝ ♛ ♚ ♝ ♞ ♜ 8
    // 7 ♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟ 7
    // 6 ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ 6
    // 5 ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ 5
    // 4 ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ 4
    // 3 ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ 3
    // 2 ♙ ♙ ♙ ♙ ♙ ♙ ♙ ♙ 2
    // 1 ♖ ♘ ♗ ♕ ♔ ♗ ♘ ♖ 1
    // ░░a░ b░c░d ░e░f░░g░h░░
    public boolean isCheck(PieceColor color) {
        // getPiecePosition();
        return false;
    }
    
    public static boolean isStraight(String from, String to) {
        if (from.charAt(0) == to.charAt(0)) {
            return true;
        }
        if (from.charAt(1) == to.charAt(1)) {
            return true;
        }
        return false;
    }
    
    public static boolean isDiagonal(String from, String to) {
        int x = from.charAt(0) - 'a';
        int y = from.charAt(1) - '0';
        int tx = to.charAt(0) - 'a';
        int ty = to.charAt(1) - '0';
        // ---+
        // --+-
        // -+--
        // +---
        if (Math.abs((x - ty)) == Math.abs((y - tx))) {// 1
            return true;
        }
        // +---
        // -+--
        // --+-
        // ---+
        if (Math.abs((x - tx)) == Math.abs((y - ty))) {// 2
            return true;
        }
        return false;
    }
    
    public static boolean isOccupiedBetween(String from, String to) {
        int x = from.charAt(0) - 'a';
        int y = from.charAt(1) - '0';
        int tx = to.charAt(0) - 'a';
        int ty = to.charAt(1) - '0';

        if (isStraight(from, to)) {
            if (x == tx) {
                int miniy = Math.min(y, ty);
                for (int i = 0; i < Math.abs((y - ty)) - 1; i++) {
                    if (board.get(miniy).get(x) != null) {
                        return true;
                    }
                }
            }
            if (y == ty) {
                int minix = Math.min(x, tx);
                for (int i = 0; i < Math.abs((x - tx)) - 1; i++) {
                    if (board.get(y).get(minix) != null) {
                        return true;
                    }
                }
            }
        }
        if (isDiagonal(from, to)) {
            if (Math.abs((x - ty)) == Math.abs((y - tx))) {// 1
                if (x < tx) {
                    for (int i = 0; i < tx - x - 1; i++) {
                        if (board.get(y - 1).get(x + 1) != null) {
                            return true;
                        }
                    }
                }
                if (x > tx) {
                    for (int i = 0; i < x - tx - 1; i++) {
                        if (board.get(y + 1).get(x - 1) != null) {
                            return true;
                        }
                    }
                }
            }
            if (Math.abs((x - tx)) == Math.abs((y - ty))) {// 2
                if (x < tx) {
                    for (int i = 0; i < tx - x - 1; i++) {
                        if (board.get(y + 1).get(x + 1) != null) {
                            return true;
                        }
                    }
                }
                if (x > tx) {
                    for (int i = 0; i < x - tx - 1; i++) {
                        if (board.get(y - 1).get(x - 1) != null) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    // ░ ⓐ ⓑ ⓒ ⓓ ⓔ ⓕ ⓖ ⓗ ░
    // 8 ♜ ♞ ♝ ♛ ♚ ♝ ♞ ♜ 8
    // 7 ♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟ 7
    // 6 ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ 6
    // 5 ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ 5
    // 4 ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ 4
    // 3 ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ 3
    // 2 ♙ ♙ ♙ ♙ ♙ ♙ ♙ ♙ 2
    // 1 ♖ ♘ ♗ ♕ ♔ ♗ ♘ ♖ 1
    // ░ ⓐ ⓑ ⓒ ⓓ ⓔ ⓕ ⓖ ⓗ ░
    public String toString() {
        String s = "░ ⓐ ⓑ ⓒ ⓓ ⓔ ⓕ ⓖ ⓗ ░";
        for (int i = 8; i > 0; i--) {
            s += "\n";
            s += " " + i + " ";
            for (int j = 0; j < 8; j++) {
                String p = "" + (char) ((char) j + 'a') + "" + i;
                if (getPiece(p) == null) {
                    s += "▓ ";
                } else {
                    Piece piece = getPiece(p);
                    if (piece.getPieceColor().getPieceColor() == "WHITE") {
                        if (piece instanceof King) {
                            s += "♚ ";
                        } else if (piece instanceof Queen) {
                            s += "♛ ";
                        } else if (piece instanceof Bishop) {
                            s += "♝ ";
                        } else if (piece instanceof Knight) {
                            s += "♞ ";
                        } else if (piece instanceof Rook) {
                            s += "♜ ";
                        } else if (piece instanceof Pawn) {
                            s += "♟ ";
                        }
                    } else if (piece.getPieceColor().getPieceColor() == "WHITE") {
                        if (piece instanceof King) {
                            s += "♔ ";
                        } else if (piece instanceof Queen) {
                            s += "♕ ";
                        } else if (piece instanceof Bishop) {
                            s += "♗ ";
                        } else if (piece instanceof Knight) {
                            s += "♘ ";
                        } else if (piece instanceof Rook) {
                            s += "♖ ";
                        } else if (piece instanceof Pawn) {
                            s += "♙ ";
                        }
                    }
                }
            }
            s += " " + i;
        }
        s += "\n" + "░ ⓐ ⓑ ⓒ ⓓ ⓔ ⓕ ⓖ ⓗ ░";
        return s;
    }
}