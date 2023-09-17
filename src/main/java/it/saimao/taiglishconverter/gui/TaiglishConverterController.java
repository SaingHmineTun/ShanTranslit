package it.saimao.taiglishconverter.gui;

import it.saimao.taiglishconverter.gui.utils.ToastHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static it.saimao.taiglishconverter.gui.converter.TaiglishConverter.taiToEng;

public class TaiglishConverterController implements Initializable {


    @FXML
    private Button btClear;

    @FXML
    private Button btConvert;

    @FXML
    private TextArea etEng;

    @FXML
    private TextArea etTai;

    @FXML
    private Button btCopy;


    private final Clipboard clipboard = Clipboard.getSystemClipboard();
    private final ClipboardContent content = new ClipboardContent();

    private void convert(ActionEvent actionEvent) {
        etEng.setText(taiToEng(etTai.getText()));
    }

    private void clear(ActionEvent actionEvent) {
        etTai.clear();
        etEng.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btConvert.setOnAction(this::convert);
        btClear.setOnAction(this::clear);
        btCopy.setOnAction(this::copy);

    }

    private void copy(ActionEvent actionEvent) {
        if (!etEng.getText().isEmpty()) {
            content.putString(etEng.getText());
            clipboard.setContent(content);
            ToastHelper.makeText((Stage) etEng.getScene().getWindow(), "Copy English text success!", 300, 100, 100);
        }}



}
