package Controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.jcjr.bomberman.View.GUI;

import java.io.IOException;
import java.util.List;

public class StubGui implements GUI
{
    @Override
    public KeyStroke readInput() throws IOException {
        KeyStroke K = new KeyStroke('p',false,false,false);
        return K;
    }

    // O resto não interessa
    @Override
    public void drawTitle(int col) throws IOException {
    }

    @Override
    public void drawOptions(int col, int row, java.util.List<String> menuOptions, int arrowPosition) {
    }

    @Override
    public void drawText(int col, int row, java.util.List<String> stringList) {

    }

    @Override
    public void drawText(int col, int row, String str) {

    }

    @Override
    public void drawColoredText(int col, int row, String str, String color) {

    }

    @Override
    public void drawHighscore(int col, int row, List<String> highscores) {

    }

    @Override
    public void drawCredits(int col, int row) {

    }



    @Override
    public void refresh() throws IOException {
    }

    @Override
    public void clear() throws IOException {
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public int getWidth() {
        return 80;
    }

    @Override
    public int getHeight() {
        return 120;
    }

    @Override
    public String getBackgroundColor() {
        return "#000000";
    }

    @Override
    public void setBackgroundColor(String color) {
    }
}
