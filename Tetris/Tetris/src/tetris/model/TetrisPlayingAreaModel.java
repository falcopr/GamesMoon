package tetris.model;

public class TetrisPlayingAreaModel
{
    private int m_Score;
    private int m_Level;
    private String m_UserName;
    private TetrisMatrixModel m_TetrisMatrixModel;
    private boolean m_Pause;
    private int m_Speed;
    

    public TetrisPlayingAreaModel() {
        m_UserName = "Anonymous";
        m_Pause = true;
        m_Score = 0;
        m_Level = 1;
        this.m_TetrisMatrixModel = new TetrisMatrixModel();
    }
    
    public int getScore()
    {
        return m_Score;
    }

    public void setScore(int score)
    {
        this.m_Score = score;
    }

    public int getLevel()
    {
        return m_Level;
    }

    public void setLevel(int level)
    {
        this.m_Level = level;
    }

    public String getUserName()
    {
        return m_UserName;
    }

    public void setUserName(String userName)
    {
        this.m_UserName = userName;
    }

    public TetrisMatrixModel getTetrisMatrixModel()
    {
        return m_TetrisMatrixModel;
    }

    public void setTetrisMatrixModel(TetrisMatrixModel tetrisMatrixModel)
    {
        this.m_TetrisMatrixModel = tetrisMatrixModel;
    }

    public boolean isPaused()
    {
        return m_Pause;
    }

    public void setPause(boolean pause)
    {
        this.m_Pause = pause;
    }

    public int getSpeed()
    {
        return m_Speed;
    }

    public void setSpeed(int speed)
    {
        this.m_Speed = speed;
    }
}
