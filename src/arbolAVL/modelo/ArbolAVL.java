package arbolAVL.modelo;

/**
 *
 * @author Maria Alejandra Solarte Astaiza
 * @author Juan Pablo Vasquez Jaramillo
 */
public class ArbolAVL {

    private Nodo raiz;

    public ArbolAVL() {
        raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * ROTACIONES
     */
    /**
     * Rotacion simple: Izquierda-Izquierda
     */
    private Nodo rotacionII(Nodo n, Nodo n1) {
        n.setIzquierdo(n1.getDerecho());
        n1.setDerecho(n);
        //Actualización de los factores de equilibrio
        if (n1.getFe() == -1)//Se cumple en la insercion
        {
            n.setFe(0);
            n1.setFe(0);
        } else {
            n.setFe(-1);
            n1.setFe(1);
        }
        return n1;
    }

    /**
     * Rotacion simple: Derecha-Derecha
     *
     * @param n
     * @param n1
     * @return
     */
    private Nodo rotacionDD(Nodo n, Nodo n1) {
        n.setDerecho(n1.getIzquierdo());
        n1.setIzquierdo(n);
        //Actualización de los factores de equilibrio
        if (n1.getFe() == +1)//Se cumple en la inserción
        {
            n.setFe(0);
            n1.setFe(0);
        } else {
            n.setFe(+1);
            n1.setFe(-1);
        }
        return n1;
    }

    /**
     * Rotacion doble: Izquierda-Derecha
     *
     * @param n
     * @param n1
     * @return
     */
    private Nodo rotacionID(Nodo n, Nodo n1) {
        Nodo n2;
        n2 = (Nodo) n1.getDerecho();
        n.setIzquierdo(n2.getDerecho());
        n2.setDerecho(n);
        n1.setDerecho(n2.getIzquierdo());
        n2.setIzquierdo(n1);
        //Actualización de los factores de equilibrio
        if (n2.getFe() == +1) {
            n1.setFe(-1);
        } else {
            n1.setFe(0);
        }
        if (n2.getFe() == -1) {
            n.setFe(1);
        } else {
            n.setFe(0);
        }
        n2.setFe(0);
        return n2;
    }

    /**
     * Rotacion doble: Derecha-Izquierda
     *
     * @param n
     * @param n1
     * @return
     */
    private Nodo rotacionDI(Nodo n, Nodo n1) {
        Nodo n2;
        n2 = (Nodo) n1.getIzquierdo();
        n.setDerecho(n2.getIzquierdo());
        n2.setIzquierdo(n);
        n1.setIzquierdo(n2.getDerecho());
        n2.setDerecho(n1);
        //Actualización de los factores de equilibrio
        if (n2.getFe() == +1) {
            n.setFe(-1);
        } else {
            n.setFe(0);
        }
        if (n2.getFe() == -1) {
            n1.setFe(1);
        } else {
            n1.setFe(0);
        }
        n2.setFe(0);
        return n2;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Inserta un elemento en el arbol avl, llama al metodo recursivo
     * insertarAvl
     *
     * @param valor
     * @throws Exception
     */
    public void insertar(Object valor) throws Exception {
        Comparable dato;
        Logical h = new Logical(false);//intercambio un valor booleano
        dato = (Comparable) valor;
        raiz = insertarAvl(raiz, dato, h);
    }

    /**
     * Metodo recursivo
     *
     * @param prmNodo, prmNodo
     * @param dato dato
     * @param h
     * @return Nodo
     * @throws Exception
     */
    private Nodo insertarAvl(Nodo prmNodo, Comparable dato, Logical h) throws Exception {
        Nodo n1;
        if (prmNodo == null) {
            prmNodo = new Nodo(dato);
            h.setLogical(true);

        } else if (dato.esMenor(prmNodo.getValor())) {
            Nodo iz;
            iz = insertarAvl((Nodo) prmNodo.getIzquierdo(), dato, h);
            prmNodo.setIzquierdo(iz);
            //regreso por los nodos del camino de búsqueda
            if (h.booleanValue()) {
                //decrementa el fe por aumentar la altura de rama izquireda
                switch (prmNodo.getFe()) {
                    case 1:
                        prmNodo.setFe(0);
                        h.setLogical(false);
                        break;
                    case 0:
                        prmNodo.setFe(-1);
                        break;
                    case -1://aplicar rotación a la izquierda
                        n1 = (Nodo) prmNodo.getIzquierdo();
                        if (n1.getFe() == -1) {
                            prmNodo = rotacionII(prmNodo, n1);
                        } else {
                            prmNodo = rotacionID(prmNodo, n1);
                        }
                        h.setLogical(false);
                }
            }
        } else if (dato.esMayor(prmNodo.getValor())) {
            Nodo dr;
            dr = insertarAvl((Nodo) prmNodo.getDerecho(), dato, h);
            prmNodo.setDerecho(dr);
            //regreso por los nodos del camino de búsqueda
            if (h.booleanValue()) {
                //incrementa el fe por aumentar la altura de rama izquireda
                switch (prmNodo.getFe()) {
                    case 1://aplicar rotacion a la derecha
                        n1 = (Nodo) prmNodo.getDerecho();
                        if (n1.getFe() == +1) {
                            prmNodo = rotacionDD(prmNodo, n1);
                        } else {
                            prmNodo = rotacionDI(prmNodo, n1);
                        }
                        h.setLogical(false);
                        break;
                    case 0:
                        prmNodo.setFe(+1);
                        break;
                    case -1:
                        prmNodo.setFe(0);
                        h.setLogical(false);
                }
            }

        } else {
            throw new Exception("No pueden haber claves repetidas");
        }
        return prmNodo;
    }

    //
    public int max(int a, int b) {
        return Math.max(a, b);
    }
    ////////////////////////////////////////////////

    /*
  Eliminar de manera recursiva un nodo
  
     */
    public void eliminar(Object valor) {
        Comparable dato;
        dato = (Comparable) valor;
        raiz = eliminarRecursivo(raiz, dato);
    }

    public Nodo eliminarRecursivo(Nodo aux, Comparable dato) {

        if (aux == null) {
            return aux;
        }

        //  Entero valor = new Entero();
        //  valor1.setDato(Integer.parseInt(root.getValor().toString()));
        //*/
        if (aux.getValor().esMayor(dato)) {
            aux.setIzquierdo(eliminarRecursivo(aux.getIzquierdo(), dato));
        } else if (aux.getValor().esMenor(dato)) {
            aux.setDerecho(eliminarRecursivo(aux.getDerecho(), dato));
        } else {
            // Nodo encontrado, realizar la eliminación

            // Caso 1: Nodo con 0 o 1 hijo
            if (aux.getIzquierdo() == null || aux.getDerecho() == null) {
                Nodo temp = null;
                if (aux.getIzquierdo() == null) {
                    temp = aux.getDerecho();
                } else {
                    temp = aux.getIzquierdo();
                }

                // Caso de nodo sin hijos
                if (temp == null) {
                    temp = aux;
                    aux = null;
                } else // Caso de nodo con un hijo
                {
                    aux = temp;
                }
            } else {
                // Caso 2: Nodo con 2 hijos
                Nodo temp = menorMayor(aux.getIzquierdo());

                // Copiar el valor del nodo mínimo
                aux.setValor(temp.getValor());

                // Eliminar el nodo mínimo
                Entero valor = new Entero();
                valor.setDato(Integer.parseInt(temp.getValor().toString()));
                aux.setIzquierdo(eliminarRecursivo(aux.getIzquierdo(), valor));
            }
        }

        aux = equilibrarNodo(aux);
        return aux;
    }
    ///////////////////////////////////////////////////////////

    Nodo menorMayor(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.getDerecho() != null) {
            actual = actual.getDerecho();
        }
        return actual;
    }
    /////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////
    public Nodo equilibrarNodo(Nodo prmNodo) {
        Nodo n1;
        if (prmNodo != null) {
            prmNodo.setFe(obtenerFactorEquilibrio(prmNodo));

            if (obtenerFactorEquilibrio(prmNodo) >= 2) {
                n1 = (Nodo) prmNodo.getDerecho();
                if (n1.getFe() == 1) {
                    prmNodo = rotacionDD(prmNodo, n1);
                } else {
                    prmNodo = rotacionDI(prmNodo, n1);
                }

            }

            if (-2 >= obtenerFactorEquilibrio(prmNodo)) {

                n1 = (Nodo) prmNodo.getIzquierdo();
                if (n1.getFe() == -1) {
                    prmNodo = rotacionII(prmNodo, n1);

                } else {
                    prmNodo = rotacionID(prmNodo, n1);

                }

            }

        }
        return prmNodo;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////

    public void preorden() {
        preorden(raiz);
    }

    private void preorden(Nodo aux) {
        if (aux != null) {
            visitar(aux);
            preorden(aux.getIzquierdo());
            preorden(aux.getDerecho());
        }
    }

    private void visitar(Nodo aux) {
        System.out.print(aux.getValor() + " ");
    }

    public int altura(Nodo prmNodo) {

        if (prmNodo == null) {
            return 0;
        }
        return 1 + Math.max(altura(prmNodo.getIzquierdo()), altura(prmNodo.getDerecho())); //Implementado de un repositorio
    }
/////////////////////////////////////////////////////////////////////////////////

    public int obtenerFactorEquilibrio(Nodo prmNodo) {
        int dr = 0;
        int izq = 0;
        if (prmNodo.getDerecho() != null) {
            dr = altura(prmNodo.getDerecho());
        }
        if (prmNodo.getIzquierdo() != null) {
            izq = altura(prmNodo.getIzquierdo());
        }
        return dr - izq;
    }
//////////////////////////////////////////////////////////////////////////

    public void feArbol(Nodo prmNodo) {
        if (prmNodo != null) {
            System.out.print("Para el nodo: " + prmNodo.getValor() + " el fe es: ");
            System.out.println(obtenerFactorEquilibrio(prmNodo));

            feArbol(prmNodo.getIzquierdo());

            feArbol(prmNodo.getDerecho());

        }
    }

}
