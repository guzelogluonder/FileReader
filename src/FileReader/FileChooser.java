package FileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileChooser extends JFrame {

    String fileType;
    String filePath;
    JTextArea currentTextArea = new JTextArea("");
    JScrollPane scroll;

    public FileChooser() {
        initializeUI();
    }

    private void initializeUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        this.setLayout(new BorderLayout());
        JButton button = new JButton("Select File");
        button.setSize(100, 100);
        button.setFont(new Font("Monaco", Font.PLAIN, 20));
        currentTextArea.setFont(new Font("Monaco", Font.PLAIN, 20)); // will only change size to 12pt


        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String fileName = selectedFile.getName();
                    fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                    filePath = selectedFile.getAbsolutePath();
                    /*Buraya fıle ın ıcınde ne varsa onu yazacagız*/
                    /*Dosyanın ıcındekılerı yaz.*/

                    String fileContent = "";
                    if (fileType.equals(".txt")) {
                        TxtReader myReader = new TxtReader();
                        fileContent = myReader.read(filePath);
                    } else if (fileType.equals(".pdf")) {
                        PdfReader myPdfReader = new PdfReader();
                        fileContent = myPdfReader.read(filePath);
                    } else if (fileType.equals(".xml")){
                        XmlReader myXmlReader = new XmlReader();
                        fileContent = myXmlReader.read(filePath);
                    } else if (fileType.equals(".doc")){
                        DocReader myDocReader = new DocReader();
                        fileContent = myDocReader.read(filePath);
                    }
                    else{
                        System.out.println("dosya bulunamadı.");
                    }
                    /*lse if(fileType.equals(".pdf")){
                        fileContent = PdfReader.read(filePath);
                    }*/

                    currentTextArea.setText(fileContent);
                    add(currentTextArea, BorderLayout.CENTER);
                    add(new JScrollPane(currentTextArea,
                            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
                    validate();
                }
            }
        });


        this.setPreferredSize(new Dimension(800, 500));
        this.setTitle("FILE READER 2020");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.add(button, BorderLayout.NORTH);
        this.pack();
        this.setVisible(true);
    }

}
