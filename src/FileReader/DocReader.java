package FileReader;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class DocReader implements MyFileReader {

    @Override
    public String read(String path) {

        File file = null;
        WordExtractor extractor = null;
        StringBuilder sb = new StringBuilder();

        try
        {

            try (HWPFDocument document = new HWPFDocument(POIFSFileSystem.create(new File(path)))) {
                extractor = new WordExtractor(document);
            }
            String[] fileData = extractor.getParagraphText();
            for(int i = 0; i < fileData.length; i++) {
                sb.append(fileData[i]);
            }
        }
        catch (Exception exep)
        {
            exep.printStackTrace();
        }
        return sb.toString();
         }
    }

