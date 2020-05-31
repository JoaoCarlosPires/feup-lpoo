package com.jcjr.bomberman.Controller;

import java.io.IOException;

public interface GeneralGameController {
    void run() throws IOException;
    void setGameState( GameStates gs);

}
