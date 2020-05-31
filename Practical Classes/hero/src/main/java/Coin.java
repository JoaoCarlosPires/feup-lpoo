import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin {

    private int width;
    private int height;
    private Position position;

    public Coin(int width, int height) {
        this.width = width;
        this.position = new Position(width, height);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void draw(TextGraphics txtGra) {
        txtGra.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        txtGra.enableModifiers(SGR.BOLD);
        txtGra.putString(new TerminalPosition(position.getX(), position.getY()), "€");
    }
}
