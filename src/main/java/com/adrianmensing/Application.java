package com.adrianmensing;

import com.adrianmensing.lingo.game.Game;
import com.adrianmensing.lingo.game.GameState;
import com.adrianmensing.lingo.game.Word;
import com.adrianmensing.lingo.game.action.GuessAction;
import com.adrianmensing.lingo.game.response.GameResponse;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Game game = new Game();

        Scanner scanner = new Scanner(System.in);

        while (game.state != GameState.COMPLETED) {
            System.out.print("Guess: ");
            Word word = Word.of(scanner.next());
            GuessAction action = new GuessAction(word);

            GameResponse response = game.handle(action);

            System.out.println();
            System.out.println(response.message());
            System.out.println(response.status().toString());
            System.out.println();
            System.out.println();
        }

        scanner.close();
    }
}
