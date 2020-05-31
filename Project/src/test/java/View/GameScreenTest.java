package View;

import com.jcjr.bomberman.Controller.FileReader.FileReaderHighscore;
import com.jcjr.bomberman.View.GameScreen;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameScreenTest {

    public class  FileReaderHighscoreStub extends FileReaderHighscore {
        public  FileReaderHighscoreStub () {
            super();
            this.path = "/testFiles/FAKEHIGHSCORE.txt";
        }

        String getPath(){return this.path;}
    }

    private FileReaderHighscoreStub fr;
    private List<String> testArray = new ArrayList<>();
    private List<String> FRHighScore = new ArrayList<>();

    public GameScreenTest() {
    }

    public int compare(String s1, String s2) {
        int t1 = Integer.parseInt(s1.substring(0, 2));
        int t2 = Integer.parseInt(s2.substring(0, 2));
        return t2 - t1;
    }


    @Before
    public void setUp() {
        fr = new FileReaderHighscoreStub();
        FRHighScore = fr.readInfo();
        testArray.add("0500-Joao Rocha-2020.06.03");
        testArray.add("0400-Joao Pires-2020.07.04");
    }

    @Test
    public void fetchHighscores() {
        Collections.sort(FRHighScore, this::compare);
        List<String> fetchedResults = new ArrayList<>();
        for (String high : FRHighScore) {
            fetchedResults.add(high);
        }
        Assert.assertEquals(false, fetchedResults.isEmpty());
        Assert.assertEquals(2, testArray.size());
        Assert.assertEquals(testArray.get(0), fetchedResults.get(0));
    }

    @Test
    public void AddHighscores() {
        Collections.sort(FRHighScore, this::compare);
        List<String> fetchedResults = new ArrayList<>();
        for (String high : FRHighScore) {
            fetchedResults.add(high);
        }

        Assert.assertEquals(false, fetchedResults.isEmpty());
        Assert.assertEquals(2, fetchedResults.size());
        Assert.assertEquals(testArray, fetchedResults);
        testArray.add("0433-Pedro-2020.07.04");
        fr.writeInfo(fr.getPath(), testArray);
        fetchedResults = fr.readInfo();
        Assert.assertEquals(2, fetchedResults.size());
    }

}