import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;

    private Hero hero;

    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero(10, 10);
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonster();
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return coins;
    }

    private List<Monster> createMonster() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return monsters;
    }

    private void moveMonsters() {
        for (Monster monster : retrieveMonsters()) {
            monster.setPosition(monster.move());
        }
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }

        return walls;
    }

    private List<Coin> retrieveCoins() {
        return coins;
    }

    private List<Monster> retrieveMonsters() {
        return monsters;
    }

    private boolean canHeroMove(Position position) {
        if (position.getX() < width-1 && position.getY() < height-1 & position.getX() > 0 & position.getY() > 0) {
            return true;
        }
        return false;
    }

    public boolean moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
        List<Coin> coins_aux = retrieveCoins();
        for (Coin coin : coins_aux) {
            if (coin.getPosition().getX() == hero.getX() && coin.getPosition().getY() == hero.getY()) {
                coins_aux.remove(coin);
                break;
            }
        }
        List<Monster> monsters_aux = retrieveMonsters();
        for (Monster monster : monsters_aux) {
            if (monster.getPosition().getX() == hero.getX() && monster.getPosition().getY() == hero.getY()) {
                return false;
            }
        }
        return true;
    }

    public void draw(TextGraphics txtGra) {
        txtGra.setBackgroundColor(TextColor.Factory.fromString("#8B8680"));
        txtGra.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(80, 24), ' ');
        for (Wall wall : walls)
            wall.draw(txtGra);
        for (Coin coin : coins)
            coin.draw(txtGra);
        for (Monster monster : monsters)
            monster.draw(txtGra);
        hero.draw(txtGra);
    }

    public boolean processKey(KeyStroke key) {
        boolean toReturn = true;
        if (key.getKeyType() == KeyType.ArrowUp) {
            toReturn = moveHero(hero.moveUp());
            moveMonsters();
        }
        else if (key.getKeyType() == KeyType.ArrowDown) {
            toReturn = moveHero(hero.moveDown());
            moveMonsters();
        }
        else if (key.getKeyType() == KeyType.ArrowLeft) {
            toReturn = moveHero(hero.moveLeft());
            moveMonsters();
        }
        else if (key.getKeyType() == KeyType.ArrowRight) {
            toReturn = moveHero(hero.moveRight());
            moveMonsters();
        }
        return toReturn;
    }
}
