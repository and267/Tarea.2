package org.example;

/**
 * Representacion de una bebida que hereda atributos y metodos de Produto para pasarlos a sus subclases CocaCola y Srpite.
 * @author Darwin Albornoz
 * @version versión 1, 20 de octubre de 2023
 */

abstract class Bebida extends Producto{

    /** Metodo constructor clase Bebida
     *@param serie primero int
     */
    
    public Bebida(int serie){
        super(serie);

    }
}
