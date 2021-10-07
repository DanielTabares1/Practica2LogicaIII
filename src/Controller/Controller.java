package Controller;

import arboles.ArbolBinario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import lista.NodoDobleAVL;

public class Controller {
    private ArbolBinario a = new ArbolBinario();
    private int opcion;

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


    public void crear_action(){
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
    }

    public void nodos_action(){
        opcion = 0;
    }
    public void aleatorio_action(){
        opcion = 1;
    }
    public void recorridos_action(){
        opcion = 2;
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
