package Controller;

public class Resultado {
    //atributos
    int indice;
    String nodo;
    int hijos;
    String hermano;
    String izq_der;
    String padre;
    String ancestros;
    String tio;
    String abuelo;

    //constructor

    public Resultado(int indice, String nodo, int hijos, String hermano, String izq_der, String padre, String ancestros, String tio, String abuelo) {
        this.indice = indice;
        this.nodo = nodo;
        this.hijos = hijos;
        this.hermano = hermano;
        this.izq_der = izq_der;
        this.padre = padre;
        this.ancestros = ancestros;
        this.tio = tio;
        this.abuelo = abuelo;
    }


    //getters & setters

    public int getIndice() {
        return indice;
    }
    public void setIndice(int indice) {
        this.indice = indice;
    }
    public String getNodo() {
        return nodo;
    }
    public void setNodo(String nodo) {
        this.nodo = nodo;
    }
    public int getHijos() {
        return hijos;
    }
    public void setHijos(int hijos) {
        this.hijos = hijos;
    }
    public String getHermano() {
        return hermano;
    }
    public void setHermano(String hermano) {
        this.hermano = hermano;
    }
    public String getIzq_der() {
        return izq_der;
    }
    public void setIzq_der(String izq_der) {
        this.izq_der = izq_der;
    }
    public String getPadre() {
        return padre;
    }
    public void setPadre(String padre) {
        this.padre = padre;
    }
    public String getAncestros() {
        return ancestros;
    }
    public void setAncestros(String ancestros) {
        this.ancestros = ancestros;
    }
    public String getTio() {
        return tio;
    }
    public void setTio(String tio) {
        this.tio = tio;
    }
    public String getAbuelo() {
        return abuelo;
    }
    public void setAbuelo(String abuelo) {
        this.abuelo = abuelo;
    }
}
