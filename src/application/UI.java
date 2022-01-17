package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    public static final String ANSI_RESET = "\u001b[0m";
    public static final String ANSI_BLACK = "\u001b[30m";
    public static final String ANSI_RED = "\u001b[31m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_YELLOW = "\u001b[33m";
    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_PURPLE = "\u001b[35m";
    public static final String ANSI_CYAN = "\u001b[36m";
    public static final String ANSI_WHITE = "\u001b[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001b[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001b[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001b[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001b[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001b[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001b[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001b[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001b[47m";

    public static void clearScreen() {

        System.out.print("\033[h\033[2j");
        System.out.flush();
    }

    public static void click(int x, int y) throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public static ChessPosition readChessPosition(Scanner sc) {
        try {
            String s = sc.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Error reading chessPosition. Valid values are from a1 to h8.");
        }
    }

    public static void printBoard(ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces[i].length; j++) {
                printPiece(pieces[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  A B C D E F G H");
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces[i].length; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.println("  A B C D E F G H");
    }

    public static void printMatch(ChessMatch chessMatch , List<ChessPiece> captured) {
        printBoard(chessMatch.getPieces());
        System.out.println();
        printCapturedPieces(captured);
        System.out.println();
        System.out.println("Turn : " + chessMatch.getTurn());
        System.out.println("Waiting player : " + chessMatch.getCurrentPlayer());
    }

    private static void printPiece(ChessPiece piece, boolean backgrou) {
        if (backgrou) {
            System.out.print(ANSI_WHITE_BACKGROUND);
        }
        if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        } else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_BLACK + piece + ANSI_RESET);
            } else {
                System.out.print(ANSI_RED + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    private static void printCapturedPieces(List<ChessPiece> captured) {
        List<ChessPiece> whit = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
        List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
        System.out.println("Captured pieces:");
        System.out.print("Whit: ");
        System.out.print(ANSI_BLACK);
        System.out.println(Arrays.toString(whit.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("Black: ");
        System.out.print(ANSI_RED);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);
    }
}
