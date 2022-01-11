package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws AWTException {

        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();

        while (true) {
            try {
                UI.printBoard(chessMatch.getPieces());
                System.out.println();
                System.out.print("Source : ");
                ChessPosition source = UI.readChessPosition(sc);
                System.out.println();
                System.out.print("Target : ");
                ChessPosition target = UI.readChessPosition(sc);
                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                UI.click(73, 780);
                sc.nextLine();

            } catch (ChessException | InputMismatchException e) {
                System.out.println(e);
                sc.nextLine();
            }
        }
    }
}
