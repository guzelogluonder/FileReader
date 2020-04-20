package FileReader;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class DocReader implements MyFileReader {

    @Override
    public String read(String path) {

        File file = null;
        WordExtractor extractor = null;
        StringBuilder sb = new StringBuilder();
        try {
            file = new File(path);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            HWPFDocument document = new HWPFDocument(fis);
            extractor = new WordExtractor(document);
            String[] fileData = extractor.getParagraphText();
            for (int i = 0; i < fileData.length; i++) {
                if (fileData[i] != null)
                    sb.append(fileData[i]);
            }
        } catch (Exception exep) {
            exep.printStackTrace();
        }
        return sb.toString();
    }
}

