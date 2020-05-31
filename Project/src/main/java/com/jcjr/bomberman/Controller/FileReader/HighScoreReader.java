package com.jcjr.bomberman.Controller.FileReader;

import java.io.IOException;

public interface HighScoreReader extends FileRead {

    void saveHighScore( String name, String score, String date) throws IOException;

}
