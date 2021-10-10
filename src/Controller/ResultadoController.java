package Controller;

import arboles.ArbolBinario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lista.NodoDobleAVL;

import java.io.IOException;
import java.util.*;

public class ResultadoController {


    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView tabla_id;
    @FXML
    private Button cargar_Button;
    @FXML
    private Label altura_label;
    @FXML
    private Label grado_label;
    @FXML
    private Label hojas_label;
    @FXML
    private Label inorden_label;
    @FXML
    private Label preorden_label;
    @FXML
    private Label posorden_label;


     @FXML
    private TableColumn<Resultado, Integer> indice_columna;
    @FXML
    private TableColumn<Resultado, String> nodo_columna;
    @FXML
    private TableColumn<Resultado, Integer> hijos_columna;
    @FXML
    private TableColumn<Resultado, String> hermano_columna;
    @FXML
    private TableColumn<Resultado, String> lado_columna;
    @FXML
    private TableColumn<Resultado, String> padre_columna;
    @FXML
    private TableColumn<Resultado, String> ancestros_columna;
    @FXML
    private TableColumn<Resultado, String> tio_columna;
    @FXML
    private TableColumn<Resultado, String> abuelo_columna;

    public static ArbolBinario Arbol;
    public ObservableList<Resultado> lista = FXCollections.observableArrayList();

    public static void setA(ArbolBinario a) {
        Arbol = a;
    }

    public void cerrar_action() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../View/sample.fxml"));
        rootPane.getChildren().setAll(pane);
    }


    public void cargar_action() {
        //inicializar columnas
        indice_columna.setCellValueFactory(new PropertyValueFactory<>("indice"));
        nodo_columna.setCellValueFactory(new PropertyValueFactory<>("nodo"));
        hijos_columna.setCellValueFactory(new PropertyValueFactory<>("hijos"));
        hermano_columna.setCellValueFactory(new PropertyValueFactory<>("hermano"));
        lado_columna.setCellValueFactory(new PropertyValueFactory<>("izq_der"));
        padre_columna.setCellValueFactory(new PropertyValueFactory<>("padre"));
        ancestros_columna.setCellValueFactory(new PropertyValueFactory<>("ancestros"));
        tio_columna.setCellValueFactory(new PropertyValueFactory<>("tio"));
        abuelo_columna.setCellValueFactory(new PropertyValueFactory<>("abuelo"));
        tabla_id.setItems(lista);

        //inicializar valores y desaparecer botón
        cargar_Button.setVisible(false);
        NodoDobleAVL p;
        p = Arbol.getRaiz();
        Queue<NodoDobleAVL> cola = new LinkedList<>();
        cola.add(p);
        int indice=0;
        String nodo;
        int hijos;
        String hermano;
        String lado;
        String padre;
        String ancestros;
        String tio;
        String abuelo;

        //recorrer arbol
        while (!cola.isEmpty()){
            p=cola.poll();
            if(p.retornaLI()!=null) cola.add(p.retornaLI());
            if(p.retornaLD()!=null) cola.add(p.retornaLD());
            indice++;
            nodo = p.retornaDato().toString();
            hijos = Arbol.numeroDeHijos((double) p.retornaDato());
            hermano = cargarHermano(p);
            lado = Arbol.esIzqODer((double) p.retornaDato());
            padre = cargarPadre(p);
            ancestros = Arbol.ancestros((double) p.retornaDato());
            abuelo = cargarAbuelo(p);
            tio = cargarTio(p);
            lista.add(new Resultado(indice,nodo,hijos,hermano,lado,padre,ancestros,tio,abuelo));
        }

        altura_label.setText(altura_label.getText()+Arbol.altura());
        hojas_label.setText(hojas_label.getText()+Arbol.hojas());
        grado_label.setText(grado_label.getText()+Arbol.grado());
        inorden_label.setText(inorden_label.getText()+Arbol.mostrarS(0));
        preorden_label.setText(preorden_label.getText()+Arbol.mostrarS(1));
        posorden_label.setText(posorden_label.getText()+Arbol.mostrarS(2));
    }

    public String cargarTio(NodoDobleAVL p) {
        NodoDobleAVL x = Arbol.tio((double) p.retornaDato());
        String s;
        if (x == null) s = "¬";
        else s = x.retornaDato().toString();
        return s;
    }
    public String cargarAbuelo(NodoDobleAVL p) {
        NodoDobleAVL x = Arbol.abuelo((double) p.retornaDato());
        String a;
        if (x == null) a = "¬";
        else a = x.retornaDato().toString();
        return a;
    }
    public String cargarHermano(NodoDobleAVL p) {
        NodoDobleAVL bro = Arbol.hermano((double) p.retornaDato());
        String hermano;
        if (bro == null) hermano = "¬";
        else hermano = bro.retornaDato().toString();
        return hermano;
    }
    public String cargarPadre(NodoDobleAVL p) {
        NodoDobleAVL padre = Arbol.padre((double) p.retornaDato());
        String s;
        if (padre == null) s = "¬";
        else s = padre.retornaDato().toString();
        return s;
    }

    public void graficar_action() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/grafico.fxml"));
        Parent root = loader.load();
        Grafico g = loader.getController();
        g.a = Arbol;
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }


}
