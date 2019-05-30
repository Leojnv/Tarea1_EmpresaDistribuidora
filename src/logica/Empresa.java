package logica;

public class Empresa {
    private Almacen[] almacenes;
    private int cantAlmacenes;

    public Empresa() {
        super();
        this.almacenes = new Almacen[3];
        this.cantAlmacenes = 0;
    }

    public Almacen[] getAlmacenes() {
        return almacenes;
    }

    public void setAlmacenes(Almacen[] almacenes) {
        this.almacenes = almacenes;
    }

    public int getCantAlmacenes() {
        return cantAlmacenes;
    }

    public void setCantAlmacenes(int cantAlmacenes) {
        this.cantAlmacenes = cantAlmacenes;
    }

    public void insertarAlmancen(Almacen alma) {
        almacenes[cantAlmacenes] = alma;
        cantAlmacenes++;
    }
    public double estGananciasBruta(String codigo){
        double ganancias = 0;
        Almacen alma = buscarAlmaByCode(codigo);
        for (int i = 0; i < alma.getCantProd(); i++) {
            ganancias += alma.getProductos()[i].getStockReal() * (alma.getProductos()[i].getpVenta() - alma.getProductos()[i].getpCompra());
        }
        return ganancias;
    }
    public double estGananciasNeta(String codigo){
        double ganancias = 0;
        /* Condiciones:
         * - Comestibles: diasVenci <= 60 : 50%
         * - Atuendos: diasVenci <= 75 : 35%
         * - Electronicos: diasVenci <= 90 : 20%
         * - stockReal < 10% stockInicial : no se despacha
         * */
        Almacen alma = buscarAlmaByCode(codigo);
        for (int i = 0; i < alma.getCantProd(); i++) {
            if (alma.getProductos()[i].getStockReal() > alma.getProductos()[i].getStockInicial()*0.10){
                if (alma.getProductos()[i].getDiasVenci() <= 60 && alma.getProductos()[i].getTipo().equalsIgnoreCase("Comestibles")){
                    ganancias += alma.getProductos()[i].getStockReal() *(alma.getProductos()[i].getpVenta()*0.50 - alma.getProductos()[i].getpCompra());
                } else if (alma.getProductos()[i].getDiasVenci() <= 75 && alma.getProductos()[i].getTipo().equalsIgnoreCase("Atuendos")){
                    ganancias += alma.getProductos()[i].getStockReal() *(alma.getProductos()[i].getpVenta()*0.65 - alma.getProductos()[i].getpCompra());
                } else if (alma.getProductos()[i].getDiasVenci() <= 90 && alma.getProductos()[i].getTipo().equalsIgnoreCase("Electronicos")){
                    ganancias += alma.getProductos()[i].getStockReal() *(alma.getProductos()[i].getpVenta()*0.80 - alma.getProductos()[i].getpCompra());
                } else {
                    ganancias += alma.getProductos()[i].getStockReal() * (alma.getProductos()[i].getpVenta() - alma.getProductos()[i].getpCompra());
                }
            }
        }
        return ganancias;
    }
    public Almacen buscarAlmaByCode(String codigo) {
        boolean encontrado = false;
        Almacen aux = null;
        int i = 0;
        while (!encontrado &&  i < this.cantAlmacenes) { //Mientras no se haya encontrado e i sea menor a la cantidad de vinos
            if(this.almacenes[i].getCodigo().equalsIgnoreCase(codigo)) {
                encontrado = true;
                aux = this.almacenes[i];
            }
            i++;
        }
        return aux;
    }

    public double perdidas(){
        double perdidas = 0;
        double gananciaNeta = 0;
        double ganaciaBruta = 0;
        for (int i = 0; i < this.getCantAlmacenes(); i++) {
            ganaciaBruta += estGananciasBruta(this.almacenes[i].getCodigo());
            gananciaNeta += estGananciasNeta(this.almacenes[i].getCodigo());
        }
        perdidas = ganaciaBruta - gananciaNeta;
        return perdidas;
    }

    public int cantProdByType(String tipo){
        int cantidad = 0;
        for (int i = 0; i < this.cantAlmacenes; i++) {
            for (int j = 0; j < this.almacenes[i].getCantProd(); j++) {
                if (this.almacenes[i].getProductos()[j].getTipo().equalsIgnoreCase(tipo)){
                    cantidad++;
                }
            }
        }
        return cantidad;
    }
    public String recogerProd(String codigo, int cantidad){
        String codigoAlma = new String("");
        boolean encontrado = false;
        int i = 0;
        Almacen aux = null;
        while (!encontrado && i < this.cantAlmacenes){
            for (int j = 0; j < this.almacenes[i].getCantProd(); j++) {
                if (this.almacenes[i].getProductos()[j].getCodigo().equalsIgnoreCase(codigo) &&
                        this.almacenes[i].getProductos()[j].getStockReal() >= cantidad &&
                        this.almacenes[i].getProductos()[j].getStockReal() > this.almacenes[i].getProductos()[j].getStockInicial()*0.1){

                    encontrado = true;
                    aux = this.almacenes[i];
                }
            }
            i++;
            if (encontrado){
                codigoAlma = aux.getCodigo();
            }else{
                codigoAlma = "No disponible";
            }
        }

        return codigoAlma;
    }
}
