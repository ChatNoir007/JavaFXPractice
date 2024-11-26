package kostki;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {

    @FXML
    private Label dane, suma;
    
    @FXML
    private TextField wejscie;
    

    @FXML
    private void initialize() {
        wejscie.setText("");
        dane.setText(""); 
        suma.setText("");
    }
    
    @FXML
    private void btnGet() {
        try {
            int ile = Integer.parseInt(wejscie.getText());
            
            if (ile >= 3 && ile <= 10) {
                Random generator = new Random();

                int[] wyniki = new int[ile];
                String text = "";

                for (int i = 0; i < ile; i++) {
                    wyniki[i] = generator.nextInt((6-1) + 1) + 1;
                    text += "Kostka " + (i+1) + ": " + wyniki[i] + "\n";
                }
                System.out.println(text);

                dane.setText(text);
                int punkty = obliczPunkty(wyniki);        
                suma.setText("Liczba uzyskanych punktów: " + punkty); 
            } else {
                dane.setText("Wpisz ilość kostek między 3 a 10");
            }  
        } catch(NumberFormatException e) {
            dane.setText("Nic nie wpisano, ponów próbę");
        }
    }
    
    @FXML
    private void btnYes() {
        wejscie.setText("");
        dane.setText(""); 
        suma.setText("");
    }
    
    @FXML
    private void btnNo() {
        Platform.exit();
    }
    
    
    private int obliczPunkty(int[] wyniki) {
        int[] licznik = new int[6];
        
        for (int wynik : wyniki) {
            licznik[wynik - 1]++;
        }
        
        int punkty = 0;
        for (int i = 0; i < licznik.length; i++) {
            if (licznik[i] > 1) {
                punkty += (i + 1) * licznik[i];
            }
        }
        
        return punkty;
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    }    
}
