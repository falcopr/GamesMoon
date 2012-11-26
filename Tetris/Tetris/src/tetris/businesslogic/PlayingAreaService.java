package tetris.businesslogic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tetris.businesslogic.interfaces.IPlayingAreaService;
import tetris.model.TetrisPlayingAreaModel;
import tetris.view.interfaces.IPlayingAreaView;

import static tetris.common.TetrisPlayingAreaConfiguration.*;

public class PlayingAreaService implements IPlayingAreaService
{
    // view is a JPanel at this moment --> should be constructed generic later
    public void ConfiguratePlayingAreaView(IPlayingAreaView view, TetrisPlayingAreaModel model) throws Exception {
        if (!(view instanceof JPanel)) {
            throw new Exception("The IPlayingAreaView object is not a JPanel");
        }
        
        JPanel viewPanel = (JPanel) view;
        BorderLayout viewLayoutManager = new BorderLayout();
        viewPanel.setLayout(viewLayoutManager);
        
        JPanel headerArea = new JPanel();
        JPanel tetrisMatrixArea = new JPanel();
        JPanel infoArea = new JPanel();
        
        headerArea.setLayout(null);
        tetrisMatrixArea.setLayout(null);
        infoArea.setLayout(null);
        
        JLabel score = new JLabel();
        JLabel userName = new JLabel();
        JLabel level = new JLabel();
        
        score.setText(SCORE_LABELPREFIX + model.getScore());
        userName.setText(USERNAME_LABELPREFIX + model.getUserName());
        level.setText(LEVEL_LABELPREFIX + model.getLevel());
        
        JLabel headerText = new JLabel();
        headerText.setText(HEADER_TEXT);
        
        // sets the JPanels to View
        view.setHeaderArea(headerArea);
        view.setTetrisMatrixArea(tetrisMatrixArea);
        view.setInfoArea(infoArea);
        
        // sets the JLabels to View
        view.setHeaderText(headerText);
        view.setLevelLabel(level);
        view.setScoreLabel(score);
        view.setUserNameLabel(userName);
        
        // test for colors to show panel proportions
        headerArea.setBackground(Color.GREEN);
        tetrisMatrixArea.setBackground(Color.BLUE);
        infoArea.setBackground(Color.YELLOW);
        
        viewPanel.setPreferredSize(new Dimension(MAXWIDTH, MAXHEIGHT));
        headerArea.setPreferredSize(new Dimension(HEADERAREA_WIDTH, HEADERAREA_HEIGHT));
        tetrisMatrixArea.setPreferredSize(new Dimension(TETRISMATRIXAREA_WIDTH, TETRISMATRIXAREA_HEIGHT));
        infoArea.setPreferredSize(new Dimension(INFOAREA_WIDTH, INFOAREA_HEIGHT));
        
        // adding the panels to the MainPanel
        viewPanel.add(headerArea, HEADERAREA_ORIENTATION);
        viewPanel.add(tetrisMatrixArea, TETRISMATRIXAREA_ORIENTATION);
        viewPanel.add(infoArea, INFOAREA_ORIENTATION);
        
        viewPanel.setVisible(true);
    }
}
