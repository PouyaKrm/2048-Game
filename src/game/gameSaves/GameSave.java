package game.gameSaves;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameSave {

    private int maxScore;

    private Date createDate;

    public GameSave(int maxScore) {
        this.maxScore = maxScore;
        createDate = new Date();
    }


    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateDateAsString() {
        DateFormat formater = new SimpleDateFormat("yyyy-mm-dd");
        return formater.format(createDate);
    }

    @Override
    public String toString() {
        return "GameSave{" +
                "maxScore=" + maxScore +
                ", createDate=" + createDate +
                '}';
    }
}
