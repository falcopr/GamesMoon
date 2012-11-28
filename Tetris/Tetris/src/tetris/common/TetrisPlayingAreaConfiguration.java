package tetris.common;

import java.awt.BorderLayout;

public class TetrisPlayingAreaConfiguration
{
    public final static String HEADERAREA_ORIENTATION = BorderLayout.PAGE_START;
    public final static String TETRISMATRIXAREA_ORIENTATION = BorderLayout.CENTER;
    public final static String INFOAREA_ORIENTATION = BorderLayout.LINE_END;
    
    public final static int MAXHEIGHT = 640;
    public final static int MAXWIDTH = 480;
    
    public final static int TETRISBLOCK_WIDTH = 10;
    public final static int TETRISBLOCK_HEIGHT = 25;
    public final static int TETRISBLOCK_LENGTH = 23;
    
    public final static int HEADERAREA_WIDTH = MAXHEIGHT;
    public final static int HEADERAREA_HEIGHT = 100;
    
    public final static int TETRISMATRIXAREA_WIDTH = TETRISBLOCK_WIDTH * TETRISBLOCK_LENGTH;
    public final static int TETRISMATRIXAREA_HEIGHT = TETRISBLOCK_HEIGHT * TETRISBLOCK_LENGTH;
    
    public final static int INFOAREA_WIDTH = MAXWIDTH - TETRISMATRIXAREA_WIDTH;
    public final static int INFOAREA_HEIGHT = MAXHEIGHT - HEADERAREA_HEIGHT;
    
    public final static String SCORE_LABELPREFIX = "Score: ";
    public final static String USERNAME_LABELPREFIX = "Player: ";
    public final static String LEVEL_LABELPREFIX = "Level: ";
    
    public final static String HEADER_TEXT = "TETRIS";
}
