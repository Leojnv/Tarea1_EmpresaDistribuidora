package logica;

public class Producto {
    public static  int generatedCode = 1;
	private String codigo;
    private String nombre;
    private String tipo; // Electronico, Comestible o Atuendo
    private double pCompra;
    private double pVenta;
    private int diasVenci;
    private int stockReal;
    private int stockInicial;
    private String codigoAlmacen;

	public Producto(String codigo, String nombre, String tipo, double pCompra, double pVenta, int diasVenci,
			int stockReal, int stockInicial, String codigoAlmacen) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.tipo = tipo;
		this.pCompra = pCompra;
		this.pVenta = pVenta;
		this.diasVenci = diasVenci;
		this.stockReal = stockReal;
		this.stockInicial = stockInicial;
		this.codigoAlmacen = codigoAlmacen;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getpCompra() {
		return pCompra;
	}

	public void setpCompra(double pCompra) {
		this.pCompra = pCompra;
	}

	public double getpVenta() {
		return pVenta;
	}

	public void setpVenta(double pVenta) {
		this.pVenta = pVenta;
	}

	public int getDiasVenci() {
		return diasVenci;
	}

	public void setDiasVenci(int diasVenci) {
		this.diasVenci = diasVenci;
	}

	public int getStockReal() {
		return stockReal;
	}

	public void setStockReal(int stockReal) {
		this.stockReal = stockReal;
	}

	public int getStockInicial() {
		return stockInicial;
	}

	public void setStockInicial(int stockInicial) {
		this.stockInicial = stockInicial;
	}

	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}

	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}
    
    
}
