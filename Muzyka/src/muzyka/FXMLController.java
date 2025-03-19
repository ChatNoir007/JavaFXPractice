package muzyka;

import java.io.*;
import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLController implements Initializable {

    @FXML
    private Label artist, album, songsNumber, year, downloadNumber;
    
    private List<String[]> albums = new ArrayList<>();
    private int currentIndex = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        updateUI();
    }
    
    private void loadData() {
        File file = new File("src\\muzyka\\data.txt");
        try (Scanner scanner = new Scanner(file)) {    
            List<String> lines = new ArrayList<>();
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
            for (int i = 0; i < lines.size(); i += 5) {
                if (i + 4 < lines.size()) {
                    albums.add(new String[]{
                        lines.get(i),
                        lines.get(i+1),
                        lines.get(i+2),
                        lines.get(i+3),
                        lines.get(i+4)
                    });
                }
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Plik nie znaleziony: " + e.getMessage());
        }
    }

    private void updateUI() {
        if (!albums.isEmpty() && currentIndex >= 0 && currentIndex < albums.size()) {
            String[] albumData = albums.get(currentIndex);
            artist.setText(albumData[0]);
            album.setText(albumData[1]);
            songsNumber.setText(albumData[2] + " utworów");
            year.setText(albumData[3]);
            downloadNumber.setText(albumData[4]);
        }
    }
    
    @FXML
    private void btnLeft() {
        if (currentIndex > 0) {
            currentIndex--;
            updateUI();
        }
    }
    
    @FXML
    private void btnRight() {
        if (currentIndex < albums.size() - 1) {
            currentIndex++;
            updateUI();
        }
    }
    
    @FXML
    private void btnDownload() {
        if (!albums.isEmpty()) {
            int downloads = Integer.parseInt(albums.get(currentIndex)[4]);
            downloads++;
            albums.get(currentIndex)[4] = String.valueOf(downloads);
            downloadNumber.setText(String.valueOf(downloads));
        }
    }
    
}
