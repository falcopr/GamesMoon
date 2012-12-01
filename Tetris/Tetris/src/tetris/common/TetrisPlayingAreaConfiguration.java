package tetris.common;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;

public class TetrisPlayingAreaConfiguration
{
    public final static String HEADERAREA_ORIENTATION = BorderLayout.PAGE_START;
    public final static String TETRISMATRIXAREA_ORIENTATION = BorderLayout.CENTER;
    public final static String INFOAREA_ORIENTATION = BorderLayout.LINE_END;
    public final static String INFOGAMEAREA_ORIENTATION = BorderLayout.CENTER;
    public final static int INFOGAMEAREALABEL_ORIENTATION = GridBagConstraints.LINE_START;
    
    public final static int MAXHEIGHT = 640;
    public final static int MAXWIDTH = 480;
    
    public final static int TETRISBLOCK_WIDTH = 10;
    public final static int TETRISBLOCK_HEIGHT = 25;
    public final static int TETRISBLOCK_LENGTH = 20;
    
    public final static int TETRISMATRIXAREA_WIDTH = TETRISBLOCK_WIDTH * TETRISBLOCK_LENGTH;
    public final static int TETRISMATRIXAREA_HEIGHT = TETRISBLOCK_HEIGHT * TETRISBLOCK_LENGTH;
    
    public final static int HEADERAREA_WIDTH = MAXWIDTH;
    public final static int HEADERAREA_HEIGHT = 640 - TETRISMATRIXAREA_HEIGHT - 46;
    
    public final static int INFOAREA_WIDTH = MAXWIDTH - TETRISMATRIXAREA_WIDTH - 1;
    public final static int INFOAREA_HEIGHT = MAXHEIGHT - HEADERAREA_HEIGHT;
    
    public final static String SCORE_LABELPREFIX = "Score: ";
    public final static String USERNAME_LABELPREFIX = "Player: ";
    public final static String LEVEL_LABELPREFIX = "Level: ";
    
    public final static String HEADER_TEXT = "TETRIS";
    
    public final static String HEADER_FONTNAME = "Serif";
    public final static int HEADER_FONTSTYLE = Font.BOLD;
    public final static int HEADER_FONTSIZE = 70;
    
    public final static String INFO_FONTNAME = "Serif";
    public final static int INFO_FONTSTYLE = Font.PLAIN;
    public final static int INFO_FONTSIZE = 30;
    
    public final static String HEADER_ORIENTATION = BorderLayout.CENTER;
    public final static String INFO_ORIENTATION = BorderLayout.CENTER;
    
    public final static int TETRISBLOCKMODELCOMPOSITION_MAXLENGTH = 4;
    
    // Keyboard Configuration
    public final static int SHIFTLEFT = 37; // Left Arrow
    public final static int SHIFTRIGHT = 39; // Right Arrow
    public final static int SOFTDROP = 40; // Down Arrow
    public final static int ROTATELEFT = 65; // A
    public final static int ROTATERIGHT = 68; // D
    
    public final static int TIMERDELAY_DEFAULT = 200;
}
