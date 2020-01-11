package game.gameSaves;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameSaveManagement {

    private String username;
    private FileManager fileManager = new FileManager();
    private final String scoreFileRecordPrefix = "score:", createDateFileRecordPrefix = "createDate:";

    public GameSaveManagement(String username) {
        this.username = username;
    }

    public void saveGame(GameSave save) throws IOException {
        String data = scoreFileRecordPrefix + save.getMaxScore() + " " + createDateFileRecordPrefix + save.getCreateDateAsString();
        fileManager.writeToFile(username, data);
    }

    private String extractScore(String fileRecord) {

        return  fileRecord.substring(fileRecord.indexOf(scoreFileRecordPrefix) + scoreFileRecordPrefix.length(),
                fileRecord.indexOf(" "));

    }

    private String extractCreateDate(String fileRecord) {
        return fileRecord.
                substring(fileRecord.indexOf(createDateFileRecordPrefix) + createDateFileRecordPrefix.length());
    }

    public List<GameSave> getLast5Records() throws IOException, ParseException {

        List<String> records = fileManager.getLast5Data(username);

        List<GameSave> result = new ArrayList<>();

        int score;
        Date createDate;
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

        for (String data : records) {
            score = Integer.parseInt(extractScore(data));
            createDate = formatter.parse(extractCreateDate(data));
            GameSave save = new GameSave(score);
            save.setCreateDate(createDate);
            result.add(save);
        }

        return result;
    }

}
