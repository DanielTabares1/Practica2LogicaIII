package Controller;

import arboles.ArbolBinario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ResultadoController {

    @FXML
    private AnchorPane rootPane;

    public static ArbolBinario Arbol;

    public static void setA(ArbolBinario a) {
        Arbol = a;
    }

    public void cerrar_action() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../View/sample.fxml"));
        rootPane.getChildren().setAll(pane);
    }

}
