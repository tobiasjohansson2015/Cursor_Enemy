package com.company;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();

        // Storlek på spelplanen.
        int frameWidth = 40;
        int frameHeight = 25;

        // Initiera ram.
        Frame frame = new Frame(frameWidth, frameHeight);

        // Initiera spelare i mitten.
        Player player = new Player(frameWidth/2,frameHeight/2);

        // Initiera fiender i hörnen. Sätt också deras hastighet.
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy(1, 1, 0.5f));
        enemies.add(new Enemy(1, frameHeight-1, 0.5f));
        enemies.add(new Enemy(frameWidth-1, 1, 0.5f));
        enemies.add(new Enemy(frameWidth-1, frameHeight-1, 0.5f));

        // Kör så länge gameLogic är true, dvs spelare och fiender är på skilda rutor.
        do {
            updateScreen(player, enemies, terminal, frame);
            player.movePlayer(terminal, frame);
        } while(gameLogic(player, enemies));

        // Game Over. Skriv ut det!
        printText("Game Over!",terminal, frame);
    }

    private static boolean gameLogic(Player player, ArrayList<Enemy> enemies) {

        boolean stillAlive = true;

        // Iterera genom listan med fiender och flytta närmare spelaren i angiven hastighet.
        for(Enemy enemy : enemies) {
            if ((int)enemy.x < player.x)
                enemy.x += enemy.howFastEnemy;
            else if ((int)enemy.x > player.x)
                enemy.x -= enemy.howFastEnemy;
            if ((int)enemy.y < player.y)
                enemy.y += enemy.howFastEnemy;
            else if ((int)enemy.y > player.y)
                enemy.y -= enemy.howFastEnemy;

            // Kontrollera om fiende och spelare hamnat på samma ruta. Om så - Game Over!
            if (player.x == (int)enemy.x && player.y == (int)enemy.y)
                stillAlive = false;
        }
        return stillAlive;
    }

    private static void updateScreen(Player player, ArrayList<Enemy> enemies, Terminal terminal, Frame frame) {

        // Töm skärmen, göm pekaren, rita ram, rita ut gul spelare, rita ut vita fiender.
        terminal.clearScreen();
        terminal.setCursorVisible(false);
        frame.drawFrame(terminal);
        terminal.moveCursor(Math.round(player.x), Math.round(player.y));
        terminal.applyForegroundColor(Terminal.Color.YELLOW);
        terminal.putCharacter('\u263B');
        terminal.applyForegroundColor(Terminal.Color.WHITE);
        for(Enemy enemy : enemies) {
            terminal.moveCursor(Math.round(enemy.x), Math.round(enemy.y));
            terminal.putCharacter('\u2617');
        }
        terminal.moveCursor(0,0);
    }

    private static void printText(String message, Terminal terminal, Frame frame) {

        // Töm skärmen, göm pekaren, rita ram och skriv ut meddelandet centrerat på spelplanen, ett tecken i taget.
        terminal.clearScreen();
        terminal.setCursorVisible(false);
        frame.drawFrame(terminal);
        int x = frame.width/2-message.length()/2;
        int y = frame.height/2;
        terminal.moveCursor(x, y);
        terminal.applyForegroundColor(Terminal.Color.WHITE);
        for(char letter : message.toCharArray()) {
            terminal.putCharacter(letter);
            x++;
            terminal.moveCursor(x, y);
        }
    }
}