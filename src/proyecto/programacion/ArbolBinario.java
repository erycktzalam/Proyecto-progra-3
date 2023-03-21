package proyecto1prograiiifinalizado;

import java.util.*;

public class ArbolBinario {

    Nodo raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    void crearArbol(String expresion) {
        Stack<Nodo> pila = new Stack<Nodo>();
        String[] tokens = expresion.split(" ");

        for (String token : tokens) {
            Nodo nodo = new Nodo(token);

            if (esOperador(token)) {
                nodo.derecho = pila.pop();
                nodo.izquierdo = pila.pop();
            } else if (token.equals(")")) {
                while (!pila.peek().valor.equals("(")) {
                    Nodo hijo = pila.pop();
                    hijo.derecho = nodo.izquierdo;
                    hijo.izquierdo = nodo.derecho;
                    nodo = hijo;
                }
                pila.pop();
            }
            pila.push(nodo);
        }
        raiz = pila.pop();
    }

    // verificar si es un operador
    boolean esOperador(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^") || token.equals("sqrt");
    }

    public void imprimirNotacionPolaca() {
        System.out.print("Notación Polaca o Preorden: ");
        imprimirNotacionPolaca(raiz);
        System.out.println();
    }

    private void imprimirNotacionPolaca(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.valor + " ");
            imprimirNotacionPolaca(nodo.izquierdo);
            imprimirNotacionPolaca(nodo.derecho);
        }
    }

    public void imprimirInorder() {
        System.out.print("Inorder: ");
        imprimirInorder(raiz);
        System.out.println();
    }

    private void imprimirInorder(Nodo nodo) {
        if (nodo != null) {
            imprimirInorder(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            imprimirInorder(nodo.derecho);
        }
    }

    public void imprimirPostorden() {
        System.out.print("Notación Postorden: ");
        imprimirPostorden(this.raiz);
        System.out.println();
    }

    private void imprimirPostorden(Nodo nodo) {
        if (nodo != null) {
            return;
        } else {
            this.imprimirPostorden(nodo.izquierdo);
            this.imprimirPostorden(nodo.derecho);
            System.out.print(nodo.valor + " ");

        }

    }

    public void imprimirArbolVertical() {
        Map<Integer, List<String>> niveles = new TreeMap<>();
        obtenerNodosPorNivel(raiz, niveles, 0);

        int altura = alturaArbol(raiz);
        int espacios = 4 * (altura - 1);

        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < espacios; j++) {
                System.out.print(" ");
            }
            List<String> nivel = niveles.get(i);
            if (nivel != null) {
                for (String valor : nivel) {
                    System.out.print(valor);
                    for (int j = 0; j < espacios + 1; j++) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
            espacios /= 2;
        }
    }

    private void obtenerNodosPorNivel(Nodo nodo, Map<Integer, List<String>> niveles, int nivel) {
        if (nodo == null) {
            return;
        }

        if (!niveles.containsKey(nivel)) {
            niveles.put(nivel, new ArrayList<>());
        }

        String valor = String.valueOf(nodo.valor);
        niveles.get(nivel).add(valor);
        obtenerNodosPorNivel(nodo.izquierdo, niveles, nivel + 1);
        obtenerNodosPorNivel(nodo.derecho, niveles, nivel + 1);
    }

    private int alturaArbol(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }

        int alturaIzquierdo = alturaArbol(nodo.izquierdo);
        int alturaDerecho = alturaArbol(nodo.derecho);

        return Math.max(alturaIzquierdo, alturaDerecho) + 1;
    }

}
