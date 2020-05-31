package com.jcjr.bomberman.Controller.FileReader;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class FileReaderAbstract{

    protected List<String> textData = new ArrayList<>();

    public FileReaderAbstract(){}

    public List<String> readInfo(String path) {
        textData.clear();
        try {
            URL resource = FileReaderAbstract.class.getResource(path);
            BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
            String line;
            while ( (line = br.readLine()) != null ) {
                textData.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textData;
    }

    public void writeInfo(String path, List<String> info) {
        OutputStream os;
        int dashcounter = 2;
        try {
            URL resource = FileReaderAbstract.class.getResource(path);
            os = new FileOutputStream(resource.getFile(), true);
            for (String str : info) {
                dashcounter--;
                if(dashcounter >= 0)
                    str += "-";

                os.write(str.getBytes(), 0, str.length());
            }
            String newline = "\n";
            os.write(newline.getBytes(), 0 , newline.length());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
