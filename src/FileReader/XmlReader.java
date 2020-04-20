package FileReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class XmlReader implements MyFileReader {


    @Override
    public String read(String path) {

        StringBuilder docBuilder = new StringBuilder(path);
        try (Stream<String> stream = Files.lines( Paths.get(path), StandardCharsets.UTF_8)) {
            stream.forEach(s -> docBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return docBuilder.toString();

    }
    }

