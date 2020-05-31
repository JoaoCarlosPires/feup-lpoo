import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena;

    public Game() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary

            screen.clear();
            screen.setCharacter(10, 10, new TextCharacter('H'));
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

        arena = new Arena(80, 24);
    }


    private void draw() throws IOException {

        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }


    public void run() throws IOException {
        while (true) {
            this.draw();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.EOF) {
                break;
            } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                screen.close();
                break;
            } else {
                if (!arena.processKey(key)){
                    screen.close();
                    System.out.println("Game Over\n");
                    break;
                }
            }
        }
    }


}
