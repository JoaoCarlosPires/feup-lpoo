package com.jcjr.bomberman.Controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.jcjr.bomberman.Controller.FileReader.FileReaderLevel;
import com.jcjr.bomberman.Controller.FileReader.LevelReader;
import com.jcjr.bomberman.Controller.FileReader.pseudoPair;
import com.jcjr.bomberman.Model.*;
import com.jcjr.bomberman.View.GUI;

import java.util.*;

import static com.jcjr.bomberman.Controller.GameStates.*;

public class GameEngine extends Score{

    protected GameStates currentState;
    private GeneralGameController gcc;
    private GUI canvas;
    private LevelReader frl;
    private int level;
    protected List<String> gameLevel;
    public List<GamePiece> gameElements = new ArrayList<>();
    protected List<GamePiece> gamePowerups = new ArrayList<>();

    protected List<BombsObserver> bombsLists = new ArrayList<>();
    protected List<pseudoPair<Integer, List<Position>>> explosionList = new ArrayList<>();
    protected PlayerModel player;

    protected int maxBombs;
    protected Tick ticks;
    protected Tick timer;
    protected int explosionRadius;
    protected int explosionDelay;
    protected int explosionDuration;
    protected GamePiece explosionTile;

    protected int key;

    private boolean skipMove = false;

    protected EnemyController enemies;
    public GamePieceFactory GamePieceFactory;

    public GameEngine(GeneralGameController ggc, GUI canvas) {
        this.gcc = ggc;
        this.currentState = PLAYING;
        this.canvas = canvas;
        this.GamePieceFactory = new GamePieceCreator();
        this.explosionTile = GamePieceFactory.createPiece(0,0, "Explosion");
                new Explosion(0,0);   // Todo
        reset(0);
        initScore();
    }

    /**
     * Resets the game to it's initial state (creates new Random enemies)
     */
    private void reset(int level) {
        this.level = level;
        this.explosionRadius = 4;
        this.explosionDelay= 10;
        this.explosionDuration = 3;
        this.maxBombs = 2;
        this.ticks = new Tick(0);
        this.timer = new Tick(300-(3*level));
        this.player = new PlayerModel(8, 6, "#FFFFFF", 3);
        this.enemies = new EnemyController();
        this.key = 0;
        this.gameElements.clear();
        this.bombsLists.clear();
        this.gamePowerups.clear();
        this.explosionList.clear();
        setState(PLAYING);
        readLevel();
        addEnemies();
    }


    private void newLevel() {
        setState(GameStates.ENDLEVEL);
    }

    public GameEngine(GUI canvas) {}
    public GameEngine() {}

    public void readLevel() {
        this.frl = new FileReaderLevel(++this.level);
        gameLevel = this.frl.readInfo();
        this.readElements();
    }

    public void draw() {
        if(this.currentState.equals(PLAYORDER)) {
            this.gcc.setGameState(PLAYING);
            reset(0);
            setState(PLAYING);
        }
        if (this.currentState.equals(PLAYING))
            gameCycle();
        else if (this.currentState.equals(GameStates.PAUSE))
            gameCycle();
        else if(this.currentState.equals(GameStates.GAMEEND)){
            gameCycle();
            if (this.timer.getTicks() == 0) drawTimesUp();
            else drawDeathNotice();
        }
        else if(this.currentState.equals(ENDLEVEL)) {
            gameCycle();
            drawEndLevel();
        }
    }

    private void gameCycle() {
        if(!(currentState.equals(PAUSE)))
        {
            moveAllEnemies();
            cleanExplosions();
            processBombs();
            drawBombs();
            explosionCollisionHandler();
            checkPowerupsCollision();

            checkGameState();
            drawLevel();
            DrawExplosions();
            drawPlayer();
            drawDashBoard();

            if (this.timer.getTicks() == 0) setState(GAMEEND);
            else {
                boolean endOfLevel = endOfGame() || this.key == 3;
                if (endOfLevel) {
                    inscreaseScore(this.timer.getTicks());
                    if (this.level == 10) {
                        setState(GameStates.GAMEEND);
                    } else newLevel();
                }
            }
        }
        else {
            drawLevel();
            drawDashBoard();
            DrawExplosions();
            drawPlayer();
            drawPaused();
        }
    }

    private boolean endOfGame() {
        for (GamePiece piece : gameElements) {

            if (piece.getClass().equals(Enemy.class)) return false;
        }
        return true;
    }

    private void checkGameState() {
        if(!this.player.isAlive()){
            setState(GameStates.GAMEEND);
        }
    }

    /* ------------------------------------------------------------
                                Read level
    -------------------------------------------------------------- */


    public void readElements() {
        int x,  y = 5;
        for (String line : gameLevel) {
            x = 7;
            for (char ch: line.toCharArray()) {
                getElement(ch, x, y);
                x++;
            }
            y++;
        }
    }

    public void getElement(char ch, int x, int y) {
        switch (ch) {
            case 'W':
                gameElements.add(GamePieceFactory.createPiece(x,y, "FixedWall"));
                break;
            case 'D':
                gameElements.add(GamePieceFactory.createPiece(x, y, "DestructiveWall"));
                break;
            default:
                break;
        }
    }

    /*------------------------------------------------------------
                Inputs
    -------------------------------------------------------------- */


    public void processInput(KeyStroke key) {
        KeyType myKey = key.getKeyType();
        if (myKey == KeyType.Character && (key.getCharacter() == 'P' || key.getCharacter() == 'p'))
            pauseGame();
        else

        if(currentState.equals(PAUSE)) {
            return;
        }

        else if( currentState.equals(ENDLEVEL)) {
            if (myKey == KeyType.Enter)
                reset(this.level++);
        }
        else if(!(currentState.equals(GameStates.GAMEEND))) {
            processKeyInputGameplay(key);
        }
        else
            if (myKey == KeyType.Enter)
                this.gcc.setGameState(C_GAMEENDMENU);

    }

    /**
     * Para evitar um metodo longo.
     * @param key - Input
     */
    private void processKeyInputGameplay(KeyStroke key) {
        KeyType myKey = key.getKeyType();
        ticks.increase();
        timer.decrease();
        if (myKey == KeyType.ArrowDown && canMoveDown((player.getPosition())))
            this.player.moveDown();
        else if (myKey == KeyType.ArrowUp && canMoveUp((player.getPosition())))
            this.player.moveUp();
        else if (myKey == KeyType.ArrowRight && canMoveRight((player.getPosition())))
            this.player.moveRight();
        else if (myKey == KeyType.ArrowLeft && canMoveLeft(player.getPosition()))
            this.player.moveLeft();
        else if (myKey == KeyType.Character && (key.getCharacter() == 'b' || key.getCharacter() == 'B'))
            dropBomb();
    }

    private void pauseGame() {
       if(currentState.equals(PLAYING)){
           setState(PAUSE);
            decreaseScore(50);
       }
       else if(currentState.equals(PAUSE)) {
           setState(PLAYING);
            skipMove = true;
       }
    }


    /* ------------------------------------------------------------
                                Gameplay
    -------------------------------------------------------------- */


    /**
     * Deve criar bomba, considerando o atual ExplosionRadius e explosionDuration.
     */
    private void dropBomb(){
        this.timer.decrease();
        if(maxBombs < 1)
            return;

        for(BombsObserver bomb : bombsLists)
            if(player.getPosition().equals(bomb.getPosition()))
                return;

            BombsObserver b1 = new BombsObserver(this.player.getX(),
                    this.player.getY(),  explosionRadius,ticks.getTicks()+explosionDelay);
            ticks.register(b1);
            bombsLists.add(b1);
            maxBombs--;
        }

    /* ------------------------------------------------------------
                            Draw Logic
    -------------------------------------------------------------- */

    private void drawBombs(){
        Iterator<BombsObserver> itr = bombsLists.iterator();
        while (itr.hasNext()) {
            BombsObserver bomb = itr.next();
            if (!bomb.explosion()) bomb.draw(canvas);
        }
    }

    /**
     * After the explosionDuration ends, it deletes the explosions from existence...
     */
    private void cleanExplosions() {
        explosionList.removeIf(posx -> ticks.getTicks() >= posx.getKey());
    }

    /**
     * Draws the explosions
     */
    private void DrawExplosions(){
        for(pseudoPair<Integer, List<Position>> posx : explosionList) {
            List<Position> posl = posx.getValue();
            for(Position p1 : posl){
                explosionTile.setPosition(p1);
                explosionTile.draw(canvas);
            }
        }
    }

    /**
     * Draws Walls and enemies.
     */
    private void drawLevel() {
        for(GamePiece powerup : gamePowerups)
            powerup.draw(canvas);
        for (GamePiece piece : gameElements)
            piece.draw(canvas);
    }

    /**
     * Draws the PlayerModel
     */
    private void drawPlayer() {
        player.draw(canvas);
    }


    /**
     * Draws the DashBoard
     */
    private void drawDashBoard() {
        canvas.drawColoredText(3, 2,
                "Level:" + this.level +
                "\t\tBombs:" + this.maxBombs +
                "\t\tTime Left: " + formatTimeForPrint(this.timer) +
                "\t\tLives:" + this.player.getLifes()  +
                "\t\tScore:" + getScore(), "#FFFF00");
    }

    /**
     * Devolve string to tempo formatada pronta para print
     * @param t countdown to tempo
     * @return String com tempo formatado -> 00:00
     */
    String formatTimeForPrint(Tick t){
        String timerString =  Double.toString(Math.floor(t.getTicks()/60));
        timerString = timerString.substring(0, timerString.indexOf(".")) + ":";
        if(t.getTicks()%60 < 10)
            timerString += "0";
        timerString+= timer.getTicks()%60;
        return timerString;
    }

    /**
     * Draws a notice about Player being dead.
     */
    private void drawDeathNotice()
    {
        canvas.drawText(canvas.getWidth()/6, canvas.getHeight()/3,getDeathNoticeWindow());
    }
    private List<String> getDeathNoticeWindow() {
        List<String> deathNotice = Arrays.asList(
                "                                           ",
                "  +-------------------------------------+  ",
                "  |                                     |  ",
                "  |               YOU DIED              |  ",
                "  |                                     |  ",
                "  |      press \"Enter\" to Go Back       |  ",
                "  +-------------------------------------+  ",
                "                                           ");
      return deathNotice;
    }



    /**
     * To display when user finishes a level
     */
    private void drawEndLevel() {
        canvas.drawText(canvas.getWidth()/6, canvas.getHeight()/3,getEndLevelWindow());
    }

    private List<String> getEndLevelWindow() {
        List<String> endLevel = Arrays.asList(
                "                                           ",
                "  +-------------------------------------+  ",
                "  |                                     |  ",
                "  |              NICE JOB!              |  ",
                "  |                                     |  ",
                "  |       press \"Enter\" to Continue     |  ",
                "  +-------------------------------------+  ",
                "                                           ");
        return endLevel;
    }


    /**
     * Draws when paused
     */
    private void drawPaused(){
        canvas.drawText(canvas.getWidth()/6, canvas.getHeight()/3,getPausedWindow());
    }


    private List<String> getPausedWindow() {
        List<String> pauseNotice = Arrays.asList(
                "                                           ",
                "  +-------------------------------------+  ",
                "  |                                     |  ",
                "  |               Paused                |  ",
                "  |                                     |  ",
                "  |         press \"P\" to UnPause        |  ",
                "  +-------------------------------------+  ",
                "                                           ");
        return pauseNotice;
    }


    /**
     * Desenha quando tempo acaba
     */
    private void drawTimesUp()
    {
        canvas.drawText(canvas.getWidth()/6, canvas.getHeight()/3,getTimeAlert());
    }


    private List<String> getTimeAlert() {
        List<String> timesNotice = Arrays.asList(
                "                                           ",
                "  +-------------------------------------+  ",
                "  |                                     |  ",
                "  |               Time's Up!            |  ",
                "  |                                     |  ",
                "  |     press \"Enter\" to Continue       |  ",
                "  +-------------------------------------+  ",
                "                                           ");
        return timesNotice;
    }



    /* ------------------------------------------------------------
                                Bomb Logic
    -------------------------------------------------------------- */

    private void processBombs(){
        Iterator<BombsObserver> itr = bombsLists.iterator();
        while (itr.hasNext()) {
            BombsObserver bomb = itr.next();
            if (bomb.explosion()) {
                transformExplosion(bomb);
                itr.remove();
            }
        }
    }

    /**
     * Transforms the Bomb object into Explosions.
     * não dá para simplificar muito. Tem demasiadas verificações
     */
    public void transformExplosion(BombsObserver bomb) {
        explosionList.add(transformExplosionIterating(bomb));
    }

    /**
     * Transforma uma bomba num par com o tempo até a bomba dissipar +
     * Lista de Posições de explosões
     * @param bomb bomba a transformar
     * @return Par tempo de dissipação da explosao + Posições que a explosão afeta
     */
    private pseudoPair<Integer, List<Position>> transformExplosionIterating(BombsObserver bomb) {
        maxBombs++;
        List<Position> posList = new ArrayList<>();
        Position pos = new Position(bomb.getX(), bomb.getY());
        posList.add(pos);
        int i;
        for(i = 1; i <= explosionRadius; ++i) {
            pos = new Position(bomb.getX() + i, bomb.getY());
            if (!(isPositionOverlappingFixedWall(pos)))
                posList.add(pos);
            else
                break;
        }


        for(i = 1; i <= explosionRadius; ++i) {
            pos = new Position(bomb.getX() - i, bomb.getY());
            if (!(isPositionOverlappingFixedWall(pos)))
                posList.add(pos);
            else
                break;
        }

        for(i = 1; i <= explosionRadius; ++i) {
            pos = new Position(bomb.getX(), bomb.getY() + i);
            if (!(isPositionOverlappingFixedWall(pos)))
                posList.add(pos);
            else
                break;
        }

        for(i = 1; i <= explosionRadius; ++i) {
            pos = new Position(bomb.getX(), bomb.getY() - i);
            if (!(isPositionOverlappingFixedWall(pos)))
                posList.add(pos);
            else
                break;
        }
        return new pseudoPair<>(ticks.getTicks() + explosionDuration, posList);
    }



    /**
     * Percorre todos os tiles com explosão e delega os handlers caso encontre.
     */
    private void explosionCollisionHandler() {
        ListIterator<pseudoPair<Integer, List<Position>>> iter = explosionList.listIterator();
        while(iter.hasNext()){
            pseudoPair<Integer, List<Position>> pair = iter.next();

            List<Position> posl = pair.getValue();
                for (Position p1 : posl) {
                    ListIterator<BombsObserver> itrb = bombsLists.listIterator();
                    while (itrb.hasNext()) {
                    BombsObserver b1 = itrb.next();
                        if(b1.getPosition().equals(p1)){
                            iter.add(transformExplosionIterating(b1));
                            iter.previous();
                            itrb.remove();
                        }
                    }

                    if (p1.equals(player.getPosition()))
                        killPlayer();

                    ListIterator<GamePiece> itr = gameElements.listIterator();
                    while (itr.hasNext()) {
                        GamePiece piece = itr.next();
                        if (p1.equals(piece.getPosition())) {
                            if (piece.getClass().equals(Enemy.class)) {
                                destroyEnemy(itr);
                            }
                            else if (piece.getClass().equals(DestructibleWall.class)) {
                                addRandomPowerUp(piece.getX(), piece.getY());
                                destroyWall(itr);
                            }
                        }
                    }
                }
            }
    }


    /**
     * Checks if the given Position is overlapping any Fixed Wall.
     * Useful for bomb explosion
     * @param  position  Position to check
     * @return True if is overlapping a Fixed Wall. False if not
     */
    private boolean isPositionOverlappingFixedWall(Position position) {
        for(GamePiece piece : gameElements) {
            if(piece.getClass().equals(FixedWall.class))
                if(piece.getPosition().equals(position))
                    return true;
        }
        return false;
    }


    private void addRandomPowerUp(int x, int y) {
        Random rand = new Random();
        int randomNumber;
        randomNumber = rand.nextInt(100);

        if(randomNumber > 80)
            gamePowerups.add(GamePieceFactory.createPiece(x, y, "PUExtraBomb"));
        else if (randomNumber > 50)
            gamePowerups.add(GamePieceFactory.createPiece(x, y, "PUExplosionRadius"));
        else if (randomNumber > 30) {
            if (this.key == 0) {
                gamePowerups.add(GamePieceFactory.createPiece(x, y, "PUKey"));
                this.key++;
            } else if (this.key == 2) {
                gamePowerups.add(GamePieceFactory.createPiece(x, y, "PUPortal"));
            }
        }
        else gamePowerups.add(GamePieceFactory.createPiece(x,y, "PUExtraLife"));
    }


    /**
     * Decreases the lifes on the player.
     */
    private void killPlayer() {
        this.player.decreaseLifes();
    }

    /**
     * Destroys a Wall, adding points to the score.
     */
    private void destroyWall(ListIterator<GamePiece> piece) {
        inscreaseScore(10);
        piece.remove();
    }

    /* ------------------------------------------------------------
                               Enemies
    -------------------------------------------------------------- */

    /**
     * Call this function to randomly move all Enemies
     */
    private void moveAllEnemies() {
        if(skipMove) {
            skipMove = false;
        return;}
        for(GamePiece enemy : gameElements) {
            if(enemy.getClass().equals(Enemy.class)) {
                randomMove(enemy);
                if(enemy.getPosition().equals(player.getPosition()))
                    killPlayer();
            }
        }
    }

    /**
     * Generates (9 + level) enemies.
     */
    public void addEnemies() {
        gameElements.addAll(enemies.generateEnemies(this.level, this.GamePieceFactory));
    }


    /**
     * Random moves (or not) the Enemies.
     */
    private void randomMove(GamePiece piece) {
        Random rand = new Random();
        int randomNumber;
            randomNumber = rand.nextInt(4);
            switch (randomNumber){
                case 0:
                    if(canMoveDown(piece.getPosition()))
                        piece.moveDown();
                        break;
                case 1:
                    if(canMoveUp(piece.getPosition()))
                        piece.moveUp();
                        break;
                case 2:
                    if(canMoveLeft(piece.getPosition()))
                        piece.moveLeft();
                    break;
                case 3:
                    if(canMoveRight(piece.getPosition()))
                        piece.moveRight();
                    break;
            }
    }

    /**
     * Deletes a Enemy, adding to score.
     */
    private void destroyEnemy(ListIterator<GamePiece> itr) {
        inscreaseScore(100);
        itr.remove();
    }



    /* ------------------------------------------------------------
                                Movement Logic
    -------------------------------------------------------------- */

    /**
     * Se checka se o player está em cima de algum Powerup
     */
    private void checkPowerupsCollision() {
        for (Iterator<GamePiece> itr = gamePowerups.listIterator(); itr.hasNext(); ) {
            GamePiece powerup = itr.next();
            if(player.getPosition().equals(powerup.getPosition())) {
                processPowerup(powerup);
                itr.remove();
            }
        }
    }

    /**
     * Se adicionares algum power-up tens de meter aqui o seu efeito
     * @param powerup - o powerup em questão.
     */
    private void processPowerup(GamePiece powerup){
        if(powerup.getClass().equals(PUExtraBomb.class))
            maxBombs++;
        else if(powerup.getClass().equals(PUExplosionRadius.class)) {
            explosionRadius++;
            explosionDelay++;
        } else if(powerup.getClass().equals(PUExtraLife.class))
            this.player.increaseLifes();
        else if (powerup.getClass().equals(Key.class))
            this.key++;
        else if (powerup.getClass().equals(Portal.class))
            this.key++;
    }


    private boolean canMoveLeft(Position pos) {
        for (GamePiece piece : gameElements)
            if (piece.getX() == pos.getX()-1 && piece.getY() == pos.getY()) return false;
        for (GamePiece piece : bombsLists)
            if (piece.getX() == pos.getX()-1 && piece.getY() == pos.getY()) return false;

        return true;
    }

    private boolean canMoveRight( Position pos) {
        for (GamePiece piece : gameElements)
            if (piece.getX() == pos.getX()+1 && piece.getY() == pos.getY()) return false;
        for (GamePiece piece : bombsLists)
            if (piece.getX() == pos.getX()+1 && piece.getY() == pos.getY()) return false;

        return true;
    }

    private boolean canMoveUp(Position pos) {
        for (GamePiece piece : gameElements)
            if (piece.getY() == pos.getY()-1 && piece.getX() == pos.getX()) return false;
        for (GamePiece piece : bombsLists)
            if (piece.getY() == pos.getY()-1 && piece.getX() == pos.getX()) return false;
        return true;
    }

    private boolean canMoveDown(Position pos) {
        for (GamePiece piece : gameElements)
            if (piece.getY() == pos.getY()+1 && piece.getX() == pos.getX() ) return false;
        for (GamePiece piece : bombsLists)
            if (piece.getY() == pos.getY()+1 && piece.getX() == pos.getX() ) return false;
        return true;
    }

    /**
     * Checks if the given Position is overlapping any other piece.
     * @param  position  Position to check
     * @return True if is overlapping. False if not
     */
    public boolean isPositionOverlapping(Position position) {
        for(GamePiece piece : gameElements) {
            if(piece.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public void setState(GameStates state) {
        this.currentState = state;
    }

}
