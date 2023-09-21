module it.saimao.taiglishconverter.gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens it.saimao.converter.shan_translit to javafx.fxml;
    exports it.saimao.converter.shan_translit;
}