package com.company;

import com.googlecode.lanterna.terminal.Terminal;

public class Frame {
    public int width;
    public int height;

    public Frame(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void drawFrame(Terminal terminal) {

        terminal.applyForegroundColor(Terminal.Color.WHITE);

        // Iterera runt kanterna på spelplanen och rita en polkaram.
        for(int i=0; i<width+1; i++) {
            if(i % 2 == 0)
                terminal.applyForegroundColor(Terminal.Color.WHITE);
            else
                terminal.applyForegroundColor(Terminal.Color.RED);
            terminal.moveCursor(i, 0);
            terminal.putCharacter('\u2588');
            terminal.moveCursor(i, height);
            terminal.putCharacter('\u2588');
        }
        for(int i=0; i<height+1; i++) {
            if(i % 2 == 0)
                terminal.applyForegroundColor(Terminal.Color.WHITE);
            else
                terminal.applyForegroundColor(Terminal.Color.RED);
            terminal.moveCursor(0, i);
            terminal.putCharacter('\u2588');
            terminal.moveCursor(width, i);
            terminal.putCharacter('\u2588');
        }
    }
}
