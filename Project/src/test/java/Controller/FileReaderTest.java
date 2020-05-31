package Controller;

import com.jcjr.bomberman.Controller.FileReader.FileReaderHighscore;
import com.jcjr.bomberman.Controller.FileReader.FileReaderInstructions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class FileReaderTest {

    public class FileReaderTestHighscoreClass extends FileReaderHighscore
    {
        public FileReaderTestHighscoreClass() {
        }
        public void setPath(String newPath) {
        this.path = newPath;
        }
    }

    public class FileReaderTestInstructionsClass extends FileReaderInstructions
    {
        public FileReaderTestInstructionsClass() {
        }
        public void setPath(String newPath) {
            this.path = newPath;
        }
    }

    @Test
    public void testFileReadingHighScore() {
        FileReaderTestHighscoreClass frtest = Mockito.mock(FileReaderTestHighscoreClass.class);
        frtest.setPath("/FAKE_Highscore.txt");
        List<String> test1 =Arrays.asList("line1x","line2x");
        List<String> info = frtest.readInfo();
        assertEquals(false, info.equals(test1));
    }

    @Test
    public void testFileReadingInstructions() {
        FileReaderTestInstructionsClass frtest = Mockito.mock(FileReaderTestInstructionsClass.class);
        frtest.setPath("src\\test\\java\\Controller\\FAKE_Highscore.txt");
        List<String> test1 =Arrays.asList("line1x","line2x");
        List<String> info = frtest.readInfo();
        assertEquals(false, info.equals(test1));
    }

    @Test
    public void testSaveHighscore() {
        FileReaderTestHighscoreClass frtest = new FileReaderTestHighscoreClass();
        String name = "Antonio";
        String score = "40";
        String date = "2020.05.31";
        String record = "0040-Antonio-2020.05.31";
        frtest.saveHighScore(name, score, date);
        String last = "";
        for (String records : frtest.readInfo())
            last = records;
        assertEquals(record, last);
    }





 }
