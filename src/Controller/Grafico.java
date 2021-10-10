package Controller;

import arboles.ArbolBinario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import lista.NodoDobleAVL;

public class Grafico {

    public static ArbolBinario a;

    @FXML
    private Pane panel_id;
    @FXML
    private Button btnID;

    public void mostrar_action() {
        NodoDobleAVL p = a.getRaiz();
        int x = 300;
        int y = 30;
        int rx = x;
        int ry = y;
        dibujarNodo(p, x, y, rx, ry);

        if (p.retornaLI() != null) {
            agregarLI(p, x, y, x / 2);
        }
        if (p.retornaLD() != null) {
            agregarLD(p, x, y, x / 2);
        }
        btnID.setVisible(false);
    }

    public void dibujarNodo(NodoDobleAVL p, int x, int y, int rx, int ry) {
        Label label = new Label();
        label.setText(p.retornaDato().toString());
        label.setLayoutX(x);
        label.setLayoutY(y);
        Line line = new Line();
        line.setStartX(x + 15);
        line.setStartY(y);
        line.setEndX(rx+15);
        line.setEndY(ry+19);
        if(p==a.getRaiz()){
            line.setEndY(y);
        }
        String s = "-fx-border-color: black; -fx-background-color: #80d0ff";
        label.setStyle(s);
        panel_id.getChildren().add(line);
        panel_id.getChildren().add(label);
    }

    public void agregarLI(NodoDobleAVL p, int x, int y, int dx) {
        int rx = medio(p);
        p = p.retornaLI();
        int nx = x - dx;
        int ny = y + 30;
        dibujarNodo(p, nx, ny, x+rx, y);
        if(p.retornaLI()!=null){
            agregarLI(p,nx,ny,dx/2);
        }
        if(p.retornaLD()!=null){
            agregarLD(p,nx,ny,dx/2);
        }
    }

    public void agregarLD(NodoDobleAVL p, int x, int y, int dx) {
        int rx = medio(p);
        p = p.retornaLD();
        int nx = x + dx;
        int ny = y + 30;
        dibujarNodo(p, nx, ny, x+rx, y);
        if(p.retornaLI()!=null){
            agregarLI(p,nx,ny,dx/2);
        }
        if(p.retornaLD()!=null){
            agregarLD(p,nx,ny,dx/2);
        }
    }

    public int medio(NodoDobleAVL p) {
        Label l = new Label();
        l.setText(p.retornaDato().toString());
        return (int) l.getWidth() / 2;
    }
}
