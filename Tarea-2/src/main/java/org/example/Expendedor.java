package org.example;

/**
 * Un expendedor que almacena productos(bebidas y dulces) para su posterior comercialización.
 * @author Darwin Albornoz
 * @version versión 1, 20 de octubre de 2023
 * @see PagoInsuficienteException,PagoIncorrectoException,NoHayProductoException
 */
class Expendedor {

    /*Constantes utilizadas en la selección del producto.*/
    public static final int COCA = 1;
    public static final int SPRITE = 2;
    public static final int FANTA = 3;
    public static final int SNICKERS = 4;
    public static final int SUPER8 = 5;

    /*Atributos de clase Expendedor*/
    private int precioB; //Almacena el precio de cada bebida.
    private int precioD; //Almacena el precio de cada dulce.

    private Deposito<CocaCola> dCoca;     //Déposito de bebida Cocacola.
    private Deposito<Sprite> dSprite;     //Déposito de bebida Sprite.
    private Deposito<Fanta> dFanta;     //Déposito de bebida Fanta.
    private Deposito<Snickers> dSnickers; //Déposito de dulce Snickers.
    private Deposito<Super8> dSuper8;     //Déposito de dulce Super 8.
    private Deposito<Moneda> dVuelto;     //Déposito de monedas.

    /*Método constructor clase Expendedor
     * @param n primero int
     * @param precioB segundo int
     * @param precioD tercero int
     */
    public Expendedor(int n, int precioB, int precioD){ //Presenta 3 entradas: cantidad de productos, precio de bebidas y precio de dulces.
        this.precioB = precioB;
        this.precioD = precioD;
        this.dVuelto = new Deposito<Moneda>();
        dCoca = new Deposito<CocaCola>();
        dSprite = new Deposito<Sprite>();
        dFanta = new Deposito<Fanta>();
        dSnickers = new Deposito<Snickers>();
        dSuper8 = new Deposito<Super8>();

        for(int i = 0; i < n; i++){
            dCoca.addObj(new CocaCola(100+i));
            dSprite.addObj(new Sprite(200 + i));
            dFanta.addObj(new Fanta(300 + i));
            dSnickers.addObj(new Snickers(400 + i));
            dSuper8.addObj(new Super8(500 + i));
        }
    }

    /*Método comprarProducto
     * @param mon primero Moneda
     * @param sele segundo int
     * @return Producto que desea el cliente siempre que cumpla con las condiciones de compra, sino retorna null.
     * @throws PagoInsuficienteException (si el precio es mayor que el dinero ingresado)
     * @throws PagoIncorrectoException (si el dinero ingresado no es valido (null))
     * @throws NoHayProductoException (si el producto solicitado no esta disponible en el deposito)
     */

    public Producto comprarProducto(Moneda mon, int sele) throws PagoInsuficienteException, PagoIncorrectoException, NoHayProductoException {
        Producto ret = null;
        int vuelto = 0;

        if(((sele==1 || sele==2 || sele==3) && mon.getValor() < precioB) || ((sele==4 || sele==5) && mon.getValor() < precioD)){ //Si el dinero ingresado es menor que el precio lanza una excepcion.
            throw new PagoInsuficienteException("El pago no es suficiente.");
        }
        else if(mon == null){ //Si el dinero ingresado no es valido lanza una excepcion.
            throw new PagoIncorrectoException("No hay moneda para comprar.");
        }

        switch (sele) { //El numero asociado al producto permite su posterior retiro.
            case COCA -> {
                vuelto = mon.getValor() - precioB;
                ret = dCoca.getObj();
            }
            case SPRITE -> {
                vuelto = mon.getValor() - precioB;
                ret = dSprite.getObj();
            }

            case FANTA -> {
                vuelto = mon.getValor() - precioB;
                ret = dFanta.getObj();
            }

            case SNICKERS -> {
                vuelto = mon.getValor() - precioD;
                ret = dSnickers.getObj();
            }

            case SUPER8 -> {
                vuelto = mon.getValor() - precioD;
                ret = dSuper8.getObj();
            }
        }

        if(ret != null){ //Si la compra fue exitosa se crean monedas (deposito de monedas) para la devolucion de vuelto.
            vuelto /= 100;
            for(int i = 0; i < vuelto; i++){
                dVuelto.addObj(new Moneda100());
            }
            mon = null;
        }

        else{ //Si el no hay stock del producto solicitado lanza una excepcion.
            for(int i = 0; i < mon.getValor()/100; i++){
                dVuelto.addObj(new Moneda100());
            }
            mon = null;
            throw new NoHayProductoException("El producto solicitado no se encuentra disponible");
        }

        return ret;
    }

    /*Método getVuelto
     * @return Retira una moneda(100) del deposito de monedas.
     */
    public Moneda getVuelto(){
        return dVuelto.getObj();
    }
}
