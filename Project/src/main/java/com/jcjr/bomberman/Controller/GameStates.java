package com.jcjr.bomberman.Controller;

public enum GameStates
{
    //Connection Codes
    SHOWMENU,
    SHOWINGMENU,
    PLAYING,
    C_PLAY,
    C_GAMEENDMENU,

    // GameEngine Exclusives
    PLAYORDER,
    PAUSE,
    ENDLEVEL,
    GAMEEND,

    // MenuEngine exclusives
    STARTMENU,
    HIGHSCORE,
    SHOWINSTRUCTIONS,
    GAMEENDMENU,
    GAMEEND_RUNNING,

}