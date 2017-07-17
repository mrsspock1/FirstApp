package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.*;


public class Controller implements Initializable {
    @FXML
    private Button buttonHello;
    @FXML
    private Label labelWords;
    @FXML
    private Button addWords;
    @FXML
    private TextField enterWords;
    private List<String> words;
    private List<String> randedWords = new ArrayList<>();
    private Random random;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        words = new ArrayList<>();
        words.add("Życie jest piękne");
        words.add("Jutra nie będzie");
        words.add("Akademia Kodu uczy jak żyć");
        random = new Random();

        createDialog("Witaj!", "", "Witamy w programie!");


        buttonHello.setOnMouseClicked(event -> handleRandomSentence(event));
        addWords.setOnMouseClicked(event -> handleButtonAdd(event));
        enterWords.setOnKeyPressed(event -> handleKeyPressed(event));
    }

    private void handleRandomSentence(MouseEvent e) {
        String randedWord = words.get(random.nextInt(words.size()));
        while (randedWords.contains(randedWord)) {
            randedWord = words.get(random.nextInt(words.size()));
            if (randedWords.size() == words.size()) {
                createDialog("Błąd", "", "Koniec sentencji");
                return;
            }
        }

        labelWords.setText(randedWord);
        randedWords.add(randedWord);
    }

    private void handleKeyPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER && !words.contains(enterWords.getText())) {
            words.add(enterWords.getText());
            enterWords.clear();
        }

    }
    private void handleButtonAdd(MouseEvent e){
        words.add(enterWords.getText());
        enterWords.clear();

    }
    private static void createDialog(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.show();
    }

}
