package tetris.businesslogic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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
        JPanel playingAreaPanel = view.getPlayingAreaPanel();
        BorderLayout viewLayoutManager = new BorderLayout();
        playingAreaPanel.setLayout(viewLayoutManager);
        
        JPanel headerArea = view.getHeaderArea();
        JPanel tetrisMatrixArea = view.getTetrisMatrixArea();
        JPanel infoArea = view.getInfoArea();
        
        BorderLayout infoAreaBorderLayout = new BorderLayout();
        
        headerArea.setLayout(null);
        tetrisMatrixArea.setLayout(null);
        infoArea.setLayout(infoAreaBorderLayout);
        
        JLabel score = view.getScoreLabel();
        JLabel userName = view.getUserNameLabel();
        JLabel level = view.getLevelLabel();
        
        score.setFont(new Font(INFO_FONTNAME, INFO_FONTSTYLE, INFO_FONTSIZE));
        userName.setFont(new Font(INFO_FONTNAME, INFO_FONTSTYLE, INFO_FONTSIZE));
        level.setFont(new Font(INFO_FONTNAME, INFO_FONTSTYLE, INFO_FONTSIZE));
        
        score.setText(SCORE_LABELPREFIX + model.getScore());
        userName.setText(USERNAME_LABELPREFIX + model.getUserName());
        level.setText(LEVEL_LABELPREFIX + model.getLevel());
        
        JLabel headerText = view.getHeaderText();
        headerText.setFont(new Font(HEADER_FONTNAME, HEADER_FONTSTYLE, HEADER_FONTSIZE));
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
        tetrisMatrixArea.setBackground(Color.GRAY);
        infoArea.setBackground(Color.YELLOW);
        
        playingAreaPanel.setPreferredSize(new Dimension(MAXWIDTH, MAXHEIGHT));
        headerArea.setPreferredSize(new Dimension(HEADERAREA_WIDTH, HEADERAREA_HEIGHT));
        tetrisMatrixArea.setPreferredSize(new Dimension(TETRISMATRIXAREA_WIDTH, TETRISMATRIXAREA_HEIGHT));
        infoArea.setPreferredSize(new Dimension(INFOAREA_WIDTH, INFOAREA_HEIGHT));
        
        // adding the panels to the MainPanel
        playingAreaPanel.add(headerArea, HEADERAREA_ORIENTATION);
        playingAreaPanel.add(tetrisMatrixArea, TETRISMATRIXAREA_ORIENTATION);
        playingAreaPanel.add(infoArea, INFOAREA_ORIENTATION);
        
        // Position for Header Label in HeaderPanel
        headerArea.add(headerText, HEADER_ORIENTATION);
        Dimension headerAreaDimension = headerArea.getPreferredSize();
        // Center the Label
        Dimension headerTextDimension = headerText.getPreferredSize();
        int headerTextPositionTop = ((headerAreaDimension.height - headerTextDimension.height) / 2);
        int headerTextPositionLeft = ((headerAreaDimension.width - headerTextDimension.width) / 2);
        headerText.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerText.setBounds(headerTextPositionLeft, headerTextPositionTop, headerTextDimension.width, headerTextDimension.height);
        
        GridBagLayout infoGameInfoLayout = new GridBagLayout();
        GridBagConstraints infoGameInfoConstraint = new GridBagConstraints();
        
        JPanel infoGameInfoArea = view.getInfoGameInfoArea();
        infoGameInfoArea.setLayout(infoGameInfoLayout);
        
        infoGameInfoConstraint.fill = GridBagConstraints.VERTICAL;
        infoGameInfoConstraint.weightx = 1.0f;
        infoGameInfoConstraint.gridy = 0;
        infoGameInfoConstraint.insets = new Insets(0, 8, 0, 8);
        infoGameInfoConstraint.anchor = GridBagConstraints.LINE_START;
        infoGameInfoLayout.setConstraints(userName, infoGameInfoConstraint);
        infoGameInfoArea.add(userName);
        
        infoGameInfoConstraint.gridy = 1;
        infoGameInfoConstraint.anchor = GridBagConstraints.LINE_START;
        infoGameInfoLayout.setConstraints(score, infoGameInfoConstraint);
        infoGameInfoArea.add(score);
        
        infoGameInfoConstraint.gridy = 2;
        infoGameInfoConstraint.anchor = GridBagConstraints.LINE_START;
        infoGameInfoLayout.setConstraints(level, infoGameInfoConstraint);
        infoGameInfoArea.add(level);
        
        infoArea.add(infoGameInfoArea, INFO_ORIENTATION);
        
        Dimension scoreDimension = score.getPreferredSize();
        Dimension userNameDimension = userName.getPreferredSize();
        Dimension levelDimension = level.getPreferredSize();
        
        // Position for Info Labels in InfoPanel
        score.setBounds(0, 0, scoreDimension.width, scoreDimension.height);
        userName.setBounds(0, 0, userNameDimension.width, userNameDimension.height);
        level.setBounds(0, 0, levelDimension.width, levelDimension.height);
        
        // Panel must be focused, or else it wont react to keylisteners
        playingAreaPanel.setFocusable(true);
        playingAreaPanel.requestFocusInWindow();
        playingAreaPanel.setVisible(true);
    }
}
