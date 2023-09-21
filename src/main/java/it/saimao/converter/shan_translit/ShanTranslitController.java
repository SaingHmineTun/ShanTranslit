package it.saimao.converter.shan_translit;

import it.saimao.converter.shan_translit.converter.ShanTranslit;
import it.saimao.converter.shan_translit.converter.ShanUnicodeDetector;
import it.saimao.converter.shan_translit.utils.ToastHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ShanTranslitController implements Initializable {


    @FXML
    private Button btClear;

    @FXML
    private Button btConvert;

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


    private final Clipboard clipboard = Clipboard.getSystemClipboard();
    private final ClipboardContent content = new ClipboardContent();

    private void convert(ActionEvent actionEvent) {
        if (ShanUnicodeDetector.isShanUnicode(etInput.getText())) {
            convertFromTaiToEng();
        } else {
            convertFromEngToTai();
        }
    }

    private void clear(ActionEvent actionEvent) {
        etInput.clear();
        etOutput.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btConvert.setOnAction(this::convert);
        btClear.setOnAction(this::clear);
        btCopy.setOnAction(this::copy);
        btCopyInput.setOnAction(this::copyInput);
        btCopyOutput.setOnAction(this::copyOutput);
    }

    private void convertFromEngToTai() {
        etOutput.setText(ShanTranslit.engToTai(etInput.getText()));
    }

    private void convertFromTaiToEng() {
        String output = ShanTranslit.taiToEng(etInput.getText());
        etOutput.setText(output);
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
        if (!etInput.getText().isEmpty() && !etOutput.getText().isEmpty()) {
            String text =
                    "#Input\n" +
                            etInput.getText() + "\n" +
                            "#Output#\n" +
                            etOutput.getText();
            content.putString(text);
            clipboard.setContent(content);
            ToastHelper.makeText((Stage) etOutput.getScene().getWindow(), "Copy Both Input and Output texts success!", 300, 100, 100);
        }
    }


}
