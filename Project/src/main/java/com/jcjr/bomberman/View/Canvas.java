package com.jcjr.bomberman.View;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.jcjr.bomberman.Controller.FileReader.FileReaderAbstract;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public abstract class Canvas implements GUI
{
    protected int width;
    protected int height;
    protected String backgroundColor;
    protected TextGraphics screen;
    protected TerminalScreen screenTerminal;

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }


    public Canvas(int width, int height, String backgroundColor) throws IOException, FontFormatException {
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;

        URL resource = FileReaderAbstract.class.getResource("/square.ttf");
        File fontFile = new File(resource.getFile());
        Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        Font loadedFont = font.deriveFont(Font.LAYOUT_LEFT_TO_RIGHT, 17);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);

        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        this.screenTerminal = new TerminalScreen(terminal);
        this.screenTerminal.setCursorPosition(null);
        this.screenTerminal.startScreen();
        this.screenTerminal.doResizeIfNecessary();

        this.screen = screenTerminal.newTextGraphics();
    }

    /**
     * Display Text in the screen. not interactive.
     * @param col column where the string will start to be written
     * @param row row where the string will start to be written
     * @param stringList list of strings to be written in screen
     */
    public void drawText(int col, int row, List<String> stringList) {
        short i = 0;
        for(String opt : stringList) {
            screen.putString(col, row + i, opt);
            ++i; }
    }

    public void drawText(int col, int row, String simpleText) {
        screen.putString(col, row, simpleText);
    }

    public void drawColoredText(int col, int row, String simpleText, String color) {
        screen.setForegroundColor(TextColor.Factory.fromString(color));
        screen.putString(col, row, simpleText);
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
    }

    public void drawCredits(int col, int row) {
        screen.putString(col, row, "JCJR LPOO 2019/2020");
    }

    /**
     * Returns the input from Keyboard
     * @return input from Keyboard
     */
    public KeyStroke readInput() throws IOException {
        return this.screenTerminal.readInput();
    }

    /**
     * To be used before and after String insert
     */
    public void refresh() throws IOException {
        this.screenTerminal.refresh();
    }
    public void clear() {
        this.screenTerminal.clear();
    }
    public void close() throws IOException {
        this.screenTerminal.close();
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String color) {
        this.screen.setBackgroundColor(TextColor.Factory.fromString(color));
    }





}
