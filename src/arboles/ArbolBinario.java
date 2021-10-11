package arboles;

import lista.NodoDobleAVL;

import java.util.*;

public class ArbolBinario {

    //atributos
    private NodoDobleAVL raiz;
    public static final int INORDEN = 0;
    public static final int PREORDEN = 1;
    public static final int POSORDEN = 2;

    public NodoDobleAVL getRaiz() {
        return raiz;
    }

    //constructor
    public ArbolBinario() {
        raiz = null;
    }

    //métodos de la clase

    public boolean esVacio() {
        return (raiz == null);
    }

    public void agregar(double d) {
        NodoDobleAVL n = new NodoDobleAVL(d);

        /*
         * Si es vacía se puede proceder a hacer el nodo como su único miembro,
         * la raíz.
         *
         */
        if (esVacio()) {
            raiz = n;
            return;
        }
        NodoDobleAVL p = raiz;
        NodoDobleAVL q = null;


        /*
         * Aquí se buscará el lugar donde se ubicará en nuevo nodo y la
         * dirección de su padre (q).
         */
        while (p != null) {
            if ((double) p.retornaDato() == d) {
                return;
            }
            q = p;
            if (d < (double) p.retornaDato()) {
                p = p.retornaLI();
            } else {
                p = p.retornaLD();
            }
        }
        /*
         * Se determina si el lugar donde se insertará es a la izquierda o a la
         * derecha del nodo p y se procede con la inserción.
         *
         */
        if (d > (double) q.retornaDato()) {
            q.asignaLD(n);
        } else {
            q.asignaLI(n);
        }

    }

    /*
    *este método muestra en consola los recorridos del árbol
    */
    public void mostrar(int opcion) {
        System.out.println();
        if (opcion == INORDEN) {
            System.out.println("INORDEN:");
            mostrarInorden(raiz);
        }
        if (opcion == PREORDEN) {
            System.out.println("PREORDEN:");
            mostrarPreorden(raiz);
        }
        if (opcion == POSORDEN) {
            System.out.println("POSORDEN:");
            mostrarPosorden(raiz);
        }
    }

    /*
    estos métodos muestran independientemente por consola los recorridos
     */
    private void mostrarInorden(NodoDobleAVL x) {
        if (x != null) {
            mostrarInorden(x.retornaLI());
            System.out.println(x.retornaDato());
            mostrarInorden(x.retornaLD());
        }
    }

    private void mostrarPreorden(NodoDobleAVL x) {
        if (x == null) {
            return;
        }
        System.out.println(x.retornaDato());
        mostrarPreorden(x.retornaLI());
        mostrarPreorden(x.retornaLD());
    }

    private void mostrarPosorden(NodoDobleAVL x) {
        if (x == null) {
            return;
        }
        mostrarPosorden(x.retornaLI());
        mostrarPosorden(x.retornaLD());
        System.out.println(x.retornaDato());
    }

    /*
    este método retorna en un string el recorrido solicitado
     */
    public String mostrarS(int opcion) {
        String s = "";
        if (opcion == INORDEN) {
            s += mostrarInordenS(raiz);
        }
        if (opcion == PREORDEN) {
            s += mostrarPreordenS(raiz);
        }
        if (opcion == POSORDEN) {

            s += mostrarPosordenS(raiz);
        }
        return s;
    }

    /*
    estos metodos retornan en un String el recorrido independiente buscado
     */
    private String mostrarInordenS(NodoDobleAVL x) {
        String s = "";
        if (x != null) {
            s += mostrarInordenS(x.retornaLI());
            s += (x.retornaDato())+" ";
            s += mostrarInordenS(x.retornaLD());
        }
        return s;
    }
    private String mostrarPreordenS(NodoDobleAVL x) {
        String s = "";
        if (x == null) {
            return "";
        }
        s += (x.retornaDato())+" ";
        s += mostrarPreordenS(x.retornaLI());
        s += mostrarPreordenS(x.retornaLD());
        return s;
    }
    private String mostrarPosordenS(NodoDobleAVL x) {
        String s = "";
        if (x == null) {
            return "";
        }
        s += mostrarPosordenS(x.retornaLI());
        s += mostrarPosordenS(x.retornaLD());
        s += (x.retornaDato())+" ";
        return s;
    }

    /*
    método que retorna el número de hojas del arbol
     */
    public int hojas() {
        if (esVacio()) {
            return 0;
        }
        return (hojasPorNodo(raiz));
    }

    /*
    método recursivo que busca las hojas desplazandose en cada nodo y retorna la cantidad de hojas
     */
    public int hojasPorNodo(NodoDobleAVL x) {
        if (x == null) {
            return 0;
        }
        if (x.retornaLI() == null && x.retornaLD() == null) {
            return 1;
        }
        int izquierda = hojasPorNodo(x.retornaLI());
        int derecha = hojasPorNodo(x.retornaLD());
        return (izquierda + derecha);
    }

    /*
    método que muestra en consola las hojas encontradas
     */
    public String ImprimirHojasPorNodo(NodoDobleAVL x) {
        if (x == null) {
            return "";
        }
        if (x.retornaLI() == null && x.retornaLD() == null) {
            return ("," + (x.retornaDato()));
        }
        String izquierda = ImprimirHojasPorNodo(x.retornaLI());
        String derecha = ImprimirHojasPorNodo(x.retornaLD());
        return ("," + izquierda + "," + derecha);
    }

    /*
    método que retorna el grado general de un arbol
     */
    public int grado() {
        if (esVacio()) {
            return 0;
        }
        return (gradoConRaiz(raiz));
    }

    /*
    método recursivo que busca el grado de un arbol ingresando algun nodo como raíz
     */
    public int gradoConRaiz(NodoDobleAVL x) {
        if (x == null) {
            return 0;
        }
        int g = x.grado();
        int gmax = g;
        g = gradoConRaiz(x.retornaLD());
        if (g > gmax) {
            gmax = g;
        }
        g = gradoConRaiz(x.retornaLI());
        if (g > gmax) {
            gmax = g;
        }
        if (gmax == 2) {
            return 2;
        }
        return gmax;
    }

    /*
    método que retorna la altura de un arbol
     */
    public int altura() {
        return alturaPorNodo(raiz);
    }

    /*
    método recursivo que retorna la altura de un arbol ingresando un nodo como raíz
     */
    public int alturaPorNodo(NodoDobleAVL x) {
        if (x == null) {
            return 0;
        }
        int izquierda = alturaPorNodo(x.retornaLI());
        int derecha = alturaPorNodo(x.retornaLD());
        if (izquierda > derecha) {
            return (izquierda + 1);
        }
        return (derecha + 1);
    }

    /*
    método que construye un arbol cuando se ingresan los valores que sus nodos van a tener
     */
    public void construirArbolString(String s) {
        String[] v = s.split(",");
        Double[] x = new Double[v.length];
        for (int i = 0; i < v.length; i++) {
            x[i] = Double.parseDouble(v[i]);
        }
        for (double d : x) {
            agregar(d);
        }
    }

    /*
    método que construye un árbol solo entregando la cantidad de nodos a agregar
     */
    public void construirArbolTamaño(int n) {
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            double d = r.nextInt(100);
            agregar(d);
        }
    }


    //métodos para construir un árbol entregando un par de recorridos, siempre el inorden acompañado por otro
    /*
    método recursivo para construir con inorden y preorden
     */
    public NodoDobleAVL construirArbolPreInRecursion(String[] inorden, String[] preorden) {
        NodoDobleAVL x = new NodoDobleAVL(Double.valueOf(preorden[0]));
        if (inorden.length == 1) {
            return x;
        }
        int n, l1, l2;
        n = indice(inorden, preorden[0]);
        l1 = n;
        l2 = inorden.length - n - 1;
        String[] aii, aip, adi, adp;
        if (l1 != 0) {
            aii = subVector(inorden, 0, l1);
            aip = subVector(preorden, 1, l1);
            x.asignaLI(construirArbolPreInRecursion(aii, aip));
        }
        if (l2 != 0) {
            adi = subVector(inorden, n + 1, l2);
            adp = subVector(preorden, n + 1, l2);
            x.asignaLD(construirArbolPreInRecursion(adi, adp));
        }
        return x;
    }

    /*
    método general para construir un arbol con recorrido inorden y preorden
     */
    public void construirArbolInordenYPreorden(String inorden, String preorden) {
        String[] in = removerComas(inorden);
        String[] pre = removerComas(preorden);
        NodoDobleAVL r = new NodoDobleAVL(Double.valueOf(pre[0]));
        raiz = r;
        int n, l1, l2;
        n = indice(in, pre[0]);
        l1 = n;
        l2 = in.length - n - 1;
        String[] aii, aip, adi, adp;
        if (l1 != 0) {
            aii = subVector(in, 0, l1);
            aip = subVector(pre, 1, l1);
            r.asignaLI(construirArbolPreInRecursion(aii, aip));
        }
        if (l2 != 0) {
            adi = subVector(in, n + 1, l2);
            adp = subVector(pre, n + 1, l2);
            r.asignaLD(construirArbolPreInRecursion(adi, adp));
        }
    }

    /*
    método recursivo para construir con inorden y posorden
     */
    public NodoDobleAVL construirArbolPosInRecursion(String[] inorden, String[] posorden) {
        NodoDobleAVL x = new NodoDobleAVL(Double.valueOf(posorden[posorden.length - 1]));
        if (inorden.length == 1) {
            return x;
        }
        int n, l1, l2;
        n = indice(inorden, posorden[posorden.length - 1]);
        l1 = n;
        l2 = inorden.length - n - 1;
        String[] aii, aip, adi, adp;
        if (l1 != 0) {
            aii = subVector(inorden, 0, l1);
            aip = subVector(posorden, 0, l1);
            x.asignaLI(construirArbolPosInRecursion(aii, aip));
        }
        if (l2 != 0) {
            adi = subVector(inorden, n + 1, l2);
            adp = subVector(posorden, n, l2);
            x.asignaLD(construirArbolPosInRecursion(adi, adp));
        }
        return x;
    }

    /*
    método general para construir un arbol con recorrido inorden y posorden
     */
    public void construirArbolInordenYPosorden(String inorden, String posorden) {
        String[] in = removerComas(inorden);
        String[] pos = removerComas(posorden);
        NodoDobleAVL r = new NodoDobleAVL(Double.valueOf(pos[pos.length - 1]));
        raiz = r;
        int n, l1, l2;
        n = indice(in, pos[pos.length - 1]);
        l1 = n;
        l2 = in.length - n - 1;
        String[] aii, aip, adi, adp;
        if (l1 != 0) {
            aii = subVector(in, 0, l1);
            aip = subVector(pos, 0, l1);
            r.asignaLI(construirArbolPosInRecursion(aii, aip));
        }
        if (l2 != 0) {
            adi = subVector(in, n + 1, l2);
            adp = subVector(pos, n, l2);
            r.asignaLD(construirArbolPosInRecursion(adi, adp));
        }
    }

    /*
    método auxiliar para crear un sub vector recibiendo otro vector como parámetro
     */
    public String[] subVector(String[] x, int n, int m) {
        String[] s = new String[m];
        for (int i = 0; i < m; i++) {
            s[i] = x[n + i];
        }
        return s;
    }

    /*
    método auxiliar que convierte un string de nodos separados con comas a vector
     */
    public String[] removerComas(String x) {
        String[] y = x.split(",");
        return y;
    }

    /*
    método auxiliar para retornar la ubicación en un vector de un string que entra como parámetro
     */
    public int indice(String[] x, String y) {
        for (int i = 0; i < x.length; i++) {
            if (x[i].equals(y)) {
                return i;
            }
        }
        return -1;
    }

    /*
    método que busca en el arbol un nodo que tenga el valor ingresado
     */
    public NodoDobleAVL buscarNodo(double d) {
        NodoDobleAVL p = raiz;
        if ((double) p.retornaDato() == d) return p;
        while (p != null) {
            if (d < (double) p.retornaDato()) {
                p = p.retornaLI();
            } else p = p.retornaLD();
            if ((double) p.retornaDato() == d) return p;
        }
        return null;
    }

    /*
    método que busca el nodo padre de un valor ingresado
     */
    public NodoDobleAVL padre(double d) {
        NodoDobleAVL p = raiz;
        if ((double) p.retornaDato() == d) return null;
        NodoDobleAVL q;
        while (p != null) {
            q = p;
            if (d < (double) p.retornaDato()) {
                p = p.retornaLI();
            } else p = p.retornaLD();
            if ((double) p.retornaDato() == d) {
                return q;
            }
        }
        return null;
    }

    /*
    método que retorna el número de hijos del nodo cuyo dato es ingresado
     */
    public int numeroDeHijos(double d) {
        NodoDobleAVL p = buscarNodo(d);
        if (p.retornaLI() != null && p.retornaLD() != null) {
            return 2;
        } else if (p.retornaLI() != null || p.retornaLD() != null) {
            return 1;
        } else return 0;
    }

    /*
    método que retorna el nodo hermano del nodo cuyo dato es ingresado
     */
    public NodoDobleAVL hermano(double d) {
        NodoDobleAVL x = buscarNodo(d);
        NodoDobleAVL p = padre((double) x.retornaDato());

        if (p == null) return null;
        if (p.retornaLD() != null && p.retornaLD() == x) return p.retornaLI();
        else return p.retornaLD();
    }

    /*
    método que retorna el nodo abuelo de un nodo cuyo valor es ingresado
     */
    public NodoDobleAVL abuelo(double d) {
        NodoDobleAVL x = buscarNodo(d);
        if (x == raiz || x == raiz.retornaLI() || x == raiz.retornaLD()) {
            return null;
        }
        return (padre((double) padre((double) x.retornaDato()).retornaDato()));
    }

    /*
    método que retorna una cadena con los datos de los nodos ancestros del nodo
    cuyo valor es ingresado
     */
    public String ancestros(double d) {
        String s = "";
        NodoDobleAVL x = buscarNodo(d);
        if (x == raiz) return "No tiene ancestros";
        x = padre((double) x.retornaDato());
        while (x != null) {
            s += (x.retornaDato() + ",");
            x = padre((double) x.retornaDato());
        }
        s = s.substring(0, s.length() - 1);
        return s;
    }

    /*
    método que retorna el nodo tío del nodo cuyo valor es ingresado
     */
    public NodoDobleAVL tio(double d) {
        NodoDobleAVL x = buscarNodo(d);
        if (x == raiz || x == raiz.retornaLI() || x == raiz.retornaLD()) {
            return null;
        }
        return hermano((double) padre(d).retornaDato());
    }

    /*
    método que retorna en un string si el nodo cuyo valor es ingresado
    es hijo izquierdo o hijo derecho, de ser la raíz lo indica
     */
    public String esIzqODer(double d) {
        NodoDobleAVL p = padre(d);
        if (p == null) return "Es la Raiz";
        if (p.retornaLI() == buscarNodo(d)) return "Izquierdo";
        return "Derecho";
    }
}
