package logica;

public class zProbandoLogica {

	public static void main(String[] args) {
		Almacen A1 = new Almacen("A-1", "Santiago", "Santiago", 432.10,
				250, 0);
		Producto p1 = new Producto("P-1", "bla", "E", 10.00, 15.00,	40, 100, 200, "A-1");
		Producto p2 = new Producto("P-2", "ble", "A", 10.00, 15.00,	40, 100, 200, "A-1");
		Empresa empre = new Empresa();
		Producto pro = new Producto("P-1", "M", "Electronicos", 100, 120, 10, 11, 100, "A-1");
		Producto pro2 = new Producto("P-2", "N", "Atuendos", 100, 120, 10, 9, 100, "A-1");
		empre.insertarAlmancen(A1);
		empre.getAlmacenes()[0].insertarProducto(pro);
		empre.getAlmacenes()[0].insertarProducto(pro);
		//empre.getAlmacenes()[0].insertarProducto(pro2);
		//if (aux != null) { System.out.println("encontrado"); } else { System.out.println("no existe");}
		//System.out.println(empre.getAlmacenes()[0].getProductos()[0].getNombre());
		System.out.println("El estimado de ganancias bruto es de RD$" + empre.estGananciasBruta("A-1"));
		System.out.println("La ganancia neto es de RD$" + empre.estGananciasNeta("A-1"));
		System.out.println("Las perdidas son de de RD$" + empre.perdidas());
		System.out.println(empre.cantProdByType("Atuendos"));
		System.out.println("El producto puede ser recogido en el almacen: " + empre.recogerProd("P-1", 10));
	}

}
