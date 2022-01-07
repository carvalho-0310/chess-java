package application;

import boardgame.Board;
import boardgame.Position;

public class Program {
    public static void main(String[] args) {
        Position p =new Position(3, 3);
        Board v = new Board(8,8);

        System.out.println(p);
    }
}
