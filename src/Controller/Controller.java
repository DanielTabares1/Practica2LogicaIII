package Controller;

import arboles.ArbolBinario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class Controller {
    //parámetros y conección con la vista
    private ArbolBinario a = new ArbolBinario();
    private int opcion;

    @FXML
    public AnchorPane rootPane;
    @FXML
    private Button crear_Button;
    @FXML
    private Button nodos_Button;
    @FXML
    private Button aleatorio_Button;
    @FXML
    private Button recorridos_Button;
    @FXML
    private TextField text_Box_1;
    @FXML
    private TextField text_Box_2;
    @FXML
    private RadioButton radiob1;
    @FXML
    private RadioButton radiob2;
    @FXML
    public Label label1id;
    @FXML
    public Label label2id;
    @FXML
    public Label label3id;

    //método invocado cuando se presiona el botón para crear el árbol
    public void crear_action() throws IOException {
        String s = text_Box_1.getText();    //se toma el dato ingresado por el usuario
        //casos de opción ingresada para la forma de crear el árbol
        switch (opcion){
            case 0:
                a.construirArbolString(s);
                break;
            case 1:
                a.construirArbolTamaño(Integer.parseInt(s));
                break;
            case 2:
                String t = text_Box_2.getText();
                if(radiob1.isSelected()){
                    a.construirArbolInordenYPreorden(s,t);
                }
                else{
                    a.construirArbolInordenYPosorden(s,t);
                }
            default: break;
        }
        LanzarArbol();  //se llama el método que muestra los resultados
    }

    //método que carga los datos necesarios para mostrar la información del árbol creado
    //y lo muestra en pantalla
    public void LanzarArbol() throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/resultado.fxml")));
        rootPane.getChildren().setAll(pane);
        ResultadoController.setA(a);
    }

    //métodos que configuran los espacios para ingreso de datos del usuario al momento de crear el árbol
    public void nodos_action(){
        opcion = 0;
       forma1();
       label1id.setText("Ingrese la cadena de texto correspondiente al árbol (cada nodo separado por una coma)");
    }
    public void aleatorio_action(){
        opcion = 1;
        forma1();
        label1id.setText("Digite el número de nodos que va a contener el árbol");
    }
    public void recorridos_action(){
        opcion = 2;
        forma2();
        label1id.setText("Ingresar recorrido Inorden (cada nodo separado por una coma)");
    }

    //métodos que adaptan la apariencia de la interfaz de usuario según la opción que este elija
    public void forma1(){
        label1id.setDisable(false);
        text_Box_1.setDisable(false);
        label2id.setDisable(true);
        radiob1.setDisable(true);
        radiob2.setDisable(true);
        text_Box_2.setDisable(true);
        label3id.setDisable(true);
        crear_Button.setDisable(false);
    }
    public void forma2(){
        label1id.setDisable(false);
        text_Box_1.setDisable(false);
        label2id.setDisable(false);
        radiob1.setDisable(false);
        radiob2.setDisable(false);
        text_Box_2.setDisable(false);
        label3id.setDisable(false);
        crear_Button.setDisable(false);
    }
}
