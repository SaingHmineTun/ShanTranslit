package it.saimao.converter.shan_translit;

import com.jfoenix.controls.JFXButton;
import it.saimao.converter.shan_translit.converter.ShanTranslit;
import it.saimao.converter.shan_translit.converter.ShanUnicodeDetector;
import it.saimao.converter.shan_translit.utils.ToastHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ShanTranslitController implements Initializable {


    @FXML
    private Button btClear;

    @FXML
    private JFXButton btConvert;

    @FXML
    private TextArea etOutput;

    @FXML
    private TextArea etInput;

    @FXML
    private Button btCopy;

    @FXML
    private Button btCopyInput;

    @FXML
    private Button btCopyOutput;

    @FXML
    private ToggleGroup tg;
    @FXML
    private RadioButton rbShn2Eng;

    @FXML
    private RadioButton rbEng2Shn;

    @FXML
    private CheckBox cbShowTone;

    private static final int LONG_CLICK_DURATION = 1000;
    private boolean longClickDetected;


    private final Clipboard clipboard = Clipboard.getSystemClipboard();
    private final ClipboardContent content = new ClipboardContent();

    private void convert() {
        if (rbShn2Eng.isSelected()) {
            convertFromShanToEng();
        } else {
            convertFromEngToShan();
        }
    }

    private void clear(ActionEvent actionEvent) {
        etInput.clear();
        etOutput.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        btConvert.setOnAction(this::convert);
//        btConvert.setOnMousePressed(this::convertFromTaiToEngWithoutTone);


        tg.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue == rbShn2Eng) {
                cbShowTone.setVisible(true);
            } else {
                cbShowTone.setVisible(false);
            }
        });

        // btConvert LONG CLICK
        btConvert.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                longClickDetected = false; // Reset the flag on each new press
                new Thread(() -> {
                    try {
                        Thread.sleep(LONG_CLICK_DURATION);
                        // Check if the mouse is still pressed after the duration
                        if (btConvert.isPressed()) {
                            longClickDetected = true;
                            convertFromTaiToEngWithoutTone();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        });

        btConvert.setOnMouseReleased(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                // Check if long click was not detected before firing short click action
                if (!longClickDetected) {
                    convert();
                }
                longClickDetected = false; // Reset the flag on release
            }
        });

        btClear.setOnAction(this::clear);
        btCopy.setOnAction(this::copy);
        btCopyInput.setOnAction(this::copyInput);
        btCopyOutput.setOnAction(this::copyOutput);
    }

    private void convertFromTaiToEngWithoutTone() {

        if (ShanUnicodeDetector.isShanUnicode(etInput.getText())) {
            etOutput.setText(ShanTranslit.taiToEngWithoutTone(etInput.getText()));
        } else {
            convertFromEngToShan();
        }
    }

    private void convertFromEngToShan() {
        etOutput.setText(ShanTranslit.engToTai(etInput.getText()));
    }

    private void convertFromShanToEng() {
        if (cbShowTone.isSelected()) {
            etOutput.setText(ShanTranslit.taiToEng(etInput.getText()));
        } else {
            etOutput.setText(ShanTranslit.taiToEngWithoutTone(etInput.getText()));
        }
    }

    private void copyOutput(ActionEvent actionEvent) {
        if (!etOutput.getText().isEmpty()) {
            content.putString(etOutput.getText());
            clipboard.setContent(content);
            ToastHelper.makeText((Stage) etOutput.getScene().getWindow(), "Copy Output text success!", 300, 100, 100);
        }
    }


    private void copyInput(ActionEvent actionEvent) {

        if (!etInput.getText().isEmpty()) {
            content.putString(etInput.getText());
            clipboard.setContent(content);
            ToastHelper.makeText((Stage) etInput.getScene().getWindow(), "Copy Input text success!", 300, 100, 100);
        }
    }

    private void copy(ActionEvent actionEvent) {
        String input, output;
        if (ShanUnicodeDetector.isShanUnicode(etInput.getText())) {
            input = "##လိၵ်ႈတႆး##\n";
            output = "##English##\n";
        } else {
            input = "##English##\n";
            output = "##လိၵ်ႈတႆး##\n";
        }

        if (!etInput.getText().isEmpty() && !etOutput.getText().isEmpty()) {
            String text =
                    input +
                            etInput.getText() + "\n" +
                            output +
                            etOutput.getText();
            content.putString(text);
            clipboard.setContent(content);
            ToastHelper.makeText((Stage) etOutput.getScene().getWindow(), "Copy Both Input and Output texts success!", 300, 100, 100);
        }
    }


}
