module it.saimao.taiglishconverter.gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens it.saimao.taiglishconverter.gui to javafx.fxml;
    exports it.saimao.taiglishconverter.gui;
}