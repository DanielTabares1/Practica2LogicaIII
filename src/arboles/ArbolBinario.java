/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import lista.NodoDobleAVL;

import java.util.*;

/**
 * @author camilo
 */
public class ArbolBinario {

    private NodoDobleAVL raiz;
    public static final int INORDEN = 0;
    public static final int PREORDEN = 1;
    public static final int POSORDEN = 2;

    public NodoDobleAVL getRaiz() {
        return raiz;
    }

    public ArbolBinario() {
        raiz = null;
    }

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
            System.out.println("No existían más nodos.");
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
                System.out.println("Dato ya existe");
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
            System.out.println(n.retornaDato() + "Agregado a la derecha de " + q.retornaDato());
        } else {
            q.asignaLI(n);
            System.out.println(n.retornaDato() + "Agregado a la izquierda de " + q.retornaDato());
        }

    }

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

    public int hojas() {
        if (esVacio()) {
            return 0;
        }
        return (hojasPorNodo(raiz));
    }

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

    public String ImprimirHojasPorNodo(NodoDobleAVL x) {
        if (x == null) {
            return "";
        }
        if (x.retornaLI() == null && x.retornaLD() == null) {
            return (","+(x.retornaDato()));
        }
        String izquierda = ImprimirHojasPorNodo(x.retornaLI());
        String derecha = ImprimirHojasPorNodo(x.retornaLD());
        return (","+izquierda +","+ derecha);
    }

    public int grado() {
        if (esVacio()) {
            return 0;
        }
        return (gradoConRaiz(raiz));
    }

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

    public int altura() {
        return alturaPorNodo(raiz);
    }

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

    public void construirArbolTamaño(int n) {
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            double d = r.nextDouble();
            agregar(d);
        }
    }

    public NodoDobleAVL construirArbolPreInRecursion(String[] inorden, String[] preorden) {
        NodoDobleAVL x = new NodoDobleAVL(preorden[0]);
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

    public void construirArbolInordenYPreorden(String inorden, String preorden) {
        String[] in = removerComas(inorden);
        String[] pre = removerComas(preorden);
        NodoDobleAVL r = new NodoDobleAVL(pre[0]);
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

    public NodoDobleAVL construirArbolPosInRecursion(String[] inorden, String[] posorden) {
        NodoDobleAVL x = new NodoDobleAVL(posorden[posorden.length - 1]);
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

    public void construirArbolInordenYPosorden(String inorden, String posorden) {
        String[] in = removerComas(inorden);
        String[] pos = removerComas(posorden);
        NodoDobleAVL r = new NodoDobleAVL(pos[pos.length - 1]);
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

    public String[] subVector(String[] x, int n, int m) {
        String[] s = new String[m];
        for (int i = 0; i < m; i++) {
            s[i] = x[n + i];
        }
        return s;
    }

    public String[] removerComas(String x) {
        String[] y = x.split(",");
        return y;
    }

    public int indice(String[] x, String y) {
        for (int i = 0; i < x.length; i++) {
            if (x[i].equals(y)) {
                return i;
            }
        }
        return -1;
    }

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

    public int numeroDeHijos(double d) {
        NodoDobleAVL p = buscarNodo(d);
        if (p.retornaLI() != null && p.retornaLD() != null) {
            return 2;
        } else if (p.retornaLI() != null || p.retornaLD() != null) {
            return 1;
        } else return 0;
    }

    public NodoDobleAVL hermano(double d) {
        NodoDobleAVL x = buscarNodo(d);
        NodoDobleAVL p = padre((double) x.retornaDato());

        if (p.retornaLD() == x) return p.retornaLI();
        else return p.retornaLD();
    }

    public NodoDobleAVL abuelo(double d) {
        NodoDobleAVL x = buscarNodo(d);
        if (x == raiz || x == raiz.retornaLI() || x == raiz.retornaLD()) {
            return null;
        }
        return padre((double) padre((double) x.retornaDato()).retornaDato());
    }

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

    public NodoDobleAVL tio(double d) {
        NodoDobleAVL x = buscarNodo(d);
        if (x == raiz || x == raiz.retornaLI() || x == raiz.retornaLD()) {
            return null;
        }
        return hermano((double) padre(d).retornaDato());
    }

    public String esIzqODer(double d) {
        NodoDobleAVL p = padre(d);
        if (p == null) return "Es la Raiz";
        if (p.retornaLI() == buscarNodo(d)) return "Izquierdo";
        return "Derecho";
    }


    //        __2__
    //      1     __4__
    //          3     __6__
    //              5     __8
    //                   7
}
