package com.jcjr.bomberman.Controller.FileReader;

import java.util.List;

public class FileReaderInstructions extends FileReaderAbstract implements InstructionsReader {

    protected String path = "/instructions.txt";
    public FileReaderInstructions() {
        super();
    }
    public List<String> readInfo() {
        return super.readInfo(path);
    }

}
