package org.example;

/**
 * Representacion de un dulce que hereda atributos y metodos de Produto para pasarlos a sus subclases Snickers y Super8.
 * @author Darwin Albornoz
 * @version versión 1, 20 de octubre de 2023
 */

abstract class Dulce extends Producto {

     /** Metodo constructor clase Dulce
     *@param serie primero int
     */
    
    public Dulce(int serie){
        super(serie);
    }
}
