package it.saimao.taiglishconverter.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

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

    }
}
