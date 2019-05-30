package logica;

public class Almacen {
    private String codigo;
    private String ciudad;
    private String municipio;
    private double capacidad;
    private int superficie;
    private Producto[] productos;
    private int cantProd;
    
	public Almacen(String codigo, String ciudad, String municipio, double capacidad, int superficie, int cantProd) {
		super();
		this.codigo = codigo;
		this.ciudad = ciudad;
		this.municipio = municipio;
		this.capacidad = capacidad;
		this.superficie = superficie;
        this.productos = new Producto[100];
        this.cantProd = cantProd;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public double getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}

	public int getSuperficie() {
		return superficie;
	}

	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}

	public Producto[] getProductos() {
		return productos;
	}

	public void setProductos(Producto[] productos) {
		this.productos = productos;
	}
	public int getCantProd() {
		return cantProd;
	}

	public void setCantProd(int cantProd) {
		this.cantProd = cantProd;
	}

	public void insertarProducto(Producto pro) {
		this.productos[cantProd] = pro;
		cantProd++;
	}

    
}
