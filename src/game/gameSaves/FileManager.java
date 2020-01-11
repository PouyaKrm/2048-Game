package game.gameSaves;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileManager {

    public void writeToFile(String fileName, String data) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

            data = data + "\n";
            writer.append(data);
        }
    }

    public List<String> getLast5Data(String fileName) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(fileName));

        Collections.reverse(lines);

        return  lines.size() <= 5 ? lines.subList(0, lines.size()) : lines.subList(0, 5);

    }


}
