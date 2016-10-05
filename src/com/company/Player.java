package com.company;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

public class Player extends Creature {

    public Player(float x, float y){
        super(x, y);
    }

    public void movePlayer(Terminal terminal, Frame frame) throws InterruptedException {

        // Lyssna efter tangenttryck med jämna mellanrum.
        Key key;
        do {
            Thread.sleep(5);
            key = terminal.readInput();
        }
        while (key == null);

        // Kontrollera vilken tangent och flytta spelaren (inom spelplanens gräns).
        switch (key.getKind()) {
            case ArrowUp:
                if(y > 1)
                    this.y--;
                break;
            case ArrowDown:
                if(y < frame.height-1)
                    this.y++;
                break;
            case ArrowLeft:
                if(x > 1)
                    this.x--;
                break;
            case ArrowRight:
                if(x < frame.width-1)
                    this.x++;
                break;
        }
    }
}
