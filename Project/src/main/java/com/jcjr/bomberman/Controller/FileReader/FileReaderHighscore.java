package com.jcjr.bomberman.Controller.FileReader;

import java.util.ArrayList;
import java.util.List;

public class FileReaderHighscore extends FileReaderAbstract implements HighScoreReader{

    protected String path = "/highscores.txt";
    public FileReaderHighscore() {
        super();
    }

    public List<String> readInfo() {
        return super.readInfo(path);
    }

    public void saveHighScore(String name, String score, String date) {
        List<String> list = new ArrayList<>();
        while (score.length() != 4)
            score = "0" + score;

        list.add(score);
        list.add(name);
        list.add(date);
        super.writeInfo(path, list);
        }

}
