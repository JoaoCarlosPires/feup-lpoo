package com.jcjr.bomberman.Controller.FileReader;

import java.util.List;

public class FileReaderLevel extends FileReaderAbstract implements LevelReader {

    private String path;
    public FileReaderLevel(int level) {
        super();
        path = "/Levels/" + level + ".txt";
    }

    public List<String> readInfo() {
        return super.readInfo(path);
    }
}
