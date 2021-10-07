package Controller;

import arboles.ArbolBinario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lista.NodoDobleAVL;

import java.io.IOException;
import java.util.Objects;

public class Controller {
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


    public void crear_action() throws IOException {
        String s = text_Box_1.getText();
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
        LanzarArbol();
        
    }

    public void LanzarArbol() throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/resultado.fxml")));
        rootPane.getChildren().setAll(pane);
    }

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

    public static void main(String[] args) {
        //String s = "2,4,6,3,5,8,1,7";
        String s = "G,F,H,B,A,D,J,I,K,C,E";
        String t = "A,B,F,G,H,C,D,I,J,K,E";

        ArbolBinario a = new ArbolBinario();

        a.construirArbolInordenYPreorden(s,t);
        //a.construirArbolString(s);



        //control hojas
        /*String e = a.ImprimirHojasPorNodo(a.buscarNodo(2));
        while (e.contains(",,")) {
            e = e.replace(",,", ",");
        }
        if(e.charAt(0)==',') e=e.substring(1);
        if(e.charAt(e.length()-1)==',') e=e.substring(0,e.length()-1);
        System.out.println(e);*/

        //control de lado
        /*double d = 2;
        System.out.println(a.esIzqODer(d));*/


        //control de tío
        /*double d = 2;
        NodoDobleAVL t = a.tio(d);
        if(t==null) System.out.println("no tiene tío");
        else System.out.println(t.retornaDato());*/


        //ancestros
        /*double d = 1;
        System.out.println(a.ancestros(d));*/

        //control de abuelo
        /*double d = 1;
        NodoDobleAVL b = a.abuelo(d);
        if(b==null) System.out.println("no tiene abuelo");
        else System.out.println(b.retornaDato());*/


        //control de hermano
        /*double d = 7;
        NodoDobleAVL h = a.hermano(d);
        if(h==null) System.out.println("No tiene hermano");
        else System.out.println(h.retornaDato());*/


        //System.out.println(a.numeroDeHijos(7));


        //control de padre
        /*double d = 1;

        if(a.padre(d)==null){
            System.out.println("no tiene padre");
        }
        else{
            System.out.println(a.padre(d).retornaDato());
        }*/

    }

}
