package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Almacen;
import logica.Empresa;
import logica.Producto;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

public class main extends JFrame {

	private JPanel contentPane;
	private boolean auxAlmacenes = false;
	private boolean auxProductos = false;
	private JTextField txtCodigo;
	private JTextField txtCiudad;
	private JTextField txtMunicipio;
	private JSpinner spnSuperficie;
	private JSpinner spnCapacidad;
	private JPanel Option1;
	private JPanel Option2;
	private static Empresa miEmpresa;
	private JTable table1;
	private static DefaultTableModel model1;
	private static Object[] fila1;
	private static Almacen miAlma;
	private JPanel Almacenes;
	private JPanel Productos;
	private String codigoAlma;
	private String codigoProd;
	private JTextField txtCodigoP;
	private JTextField txtNombreP;
	private JTextField txtPrecioVenta;
	private JTextField txtPrecioCompra;
	private JComboBox cbxTipo;
	private JSpinner spnStockInial;
	private JSpinner spnVencimiento;
	private static JComboBox cbxAlmacen;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] fila;
	private JButton btnRegistrar;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Empresa empre = new Empresa();
					main frame = new main(empre);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main(Empresa empre) {
		this.miEmpresa = empre;
		setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Empresa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Principal = new JPanel();
		Principal.setBounds(358, 0, 916, 691);
		contentPane.add(Principal);
		Principal.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 458, 345);
		Principal.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(458, 0, 455, 345);
		Principal.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 345, 458, 345);
		Principal.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(458, 345, 455, 345);
		Principal.add(panel_5);

		JPanel MenuPanel = new JPanel();
		MenuPanel.setBackground(Color.GRAY);
		MenuPanel.setBounds(0, 0, 360, 691);
		contentPane.add(MenuPanel);
		MenuPanel.setLayout(null);
		{
			model1 = new DefaultTableModel();
			String[] header = {"Codigo", "Ciudad", "Municipio", "Capacidad", "Superficie"};
			model1.setColumnIdentifiers(header);
			loadTableAlma();
			model = new DefaultTableModel();
			String[] header1 = {"Codigo", "Nombre", "Tipo", "Compra", "Venta", "Vencimiento", "Stock Real", "Stock Inicial", "Almacen"};
			model.setColumnIdentifiers(header1);
			loadTableAlma();
		}

		Option1 = new JPanel();
		Option1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Option1.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Almacenes.setVisible(true);
				Option1.setBackground(Color.LIGHT_GRAY);
				auxAlmacenes = true;
				Productos.setVisible(false);
				Option2.setBackground(Color.gray);
				auxProductos = false;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (!auxAlmacenes) {
					Option1.setBackground(Color.gray);
				}
			}
		});

		JLabel lblEmpresaQsy = new JLabel("Empresa QSY");
		lblEmpresaQsy.setForeground(Color.WHITE);
		lblEmpresaQsy.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpresaQsy.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		lblEmpresaQsy.setBounds(10, 11, 340, 52);
		MenuPanel.add(lblEmpresaQsy);
		Option1.setBackground(Color.gray);
		Option1.setBounds(0, 120, 360, 52);
		MenuPanel.add(Option1);
		Option1.setLayout(new BorderLayout(0, 0));

		JLabel lblAlmacenes = new JLabel("Almacenes");
		lblAlmacenes.setForeground(Color.WHITE);
		lblAlmacenes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAlmacenes.setHorizontalAlignment(SwingConstants.CENTER);
		Option1.add(lblAlmacenes);

		Option2 = new JPanel();
		Option2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Option2.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				loadAlmacenes();
				Productos.setVisible(true);
				Option2.setBackground(Color.LIGHT_GRAY);
				auxProductos = true;
				Almacenes.setVisible(false);
				Option1.setBackground(Color.gray);
				auxAlmacenes = false;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (!auxProductos) {
					Option2.setBackground(Color.gray);
				}
			}
		});
		Option2.setBackground(Color.GRAY);
		Option2.setBounds(0, 181, 360, 52);
		MenuPanel.add(Option2);
		Option2.setLayout(new BorderLayout(0, 0));

		JLabel lblProductos = new JLabel("Productos");
		lblProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductos.setForeground(Color.WHITE);
		lblProductos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Option2.add(lblProductos, BorderLayout.CENTER);

		Productos = new JPanel();
		Productos.setBackground(Color.LIGHT_GRAY);
		Productos.setBounds(358, 0, 916, 691);
		Productos.setVisible(false);
		
				Almacenes = new JPanel();
				Almacenes.setBackground(Color.LIGHT_GRAY);
				Almacenes.setBounds(358, 0, 916, 691);
				contentPane.add(Almacenes);
				Almacenes.setLayout(new BorderLayout(0, 0));
				
						JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
						tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
						tabbedPane.setBackground(Color.WHITE);
						Almacenes.add(tabbedPane, BorderLayout.CENTER);
						
								JPanel ListadoAlma = new JPanel();
								ListadoAlma.setBackground(Color.LIGHT_GRAY);
								ListadoAlma.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
								tabbedPane.addTab("Listado", null, ListadoAlma, null);
								ListadoAlma.setLayout(new BorderLayout(0, 0));
								
										JPanel Lista = new JPanel();
										FlowLayout flowLayout = (FlowLayout) Lista.getLayout();
										flowLayout.setAlignment(FlowLayout.RIGHT);
										ListadoAlma.add(Lista, BorderLayout.SOUTH);
										
												JButton btnModificar = new JButton("Modificar");
												btnModificar.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														Almacen aux = miEmpresa.buscarAlmaByCode(codigoAlma);
														ModAlmacen modAlma = new ModAlmacen(miEmpresa,aux);
														modAlma.setModal(true);
														modAlma.setVisible(true);
													}
												});
												btnModificar.setEnabled(false);
												Lista.add(btnModificar);
												
														JButton btnEliminar = new JButton("Eliminar");
														btnEliminar.setEnabled(false);
														btnEliminar.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent e) {
																miEmpresa.eliminarAlmacen(codigoAlma);
																JOptionPane.showMessageDialog(null, "Operacion satisfactoria", "Informacion", JOptionPane.INFORMATION_MESSAGE, null);
																loadTableAlma();
																btnEliminar.setEnabled(false);
																btnModificar.setEnabled(false);
																btnRegistrar.setEnabled(true);
															}
														});
														Lista.add(btnEliminar);
														
																JScrollPane scrollPane = new JScrollPane();
																ListadoAlma.add(scrollPane, BorderLayout.CENTER);
																table1 = new JTable();
																table1.addMouseListener(new MouseAdapter() {
																	@Override
																	public void mouseClicked(MouseEvent e) {
																		btnModificar.setEnabled(true);
																		btnEliminar.setEnabled(true);
																		int index = table1.getSelectedRow();
																		codigoAlma = (String) table1.getValueAt(index, 0);
																	}
																});
																table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
																table1.setModel(model1);
																scrollPane.setViewportView(table1);
																
																		JPanel RegistrarAlma = new JPanel();
																		RegistrarAlma.setBackground(Color.LIGHT_GRAY);
																		tabbedPane.addTab("Registrar", null, RegistrarAlma, null);
																		RegistrarAlma.setLayout(null);
																		
																				JPanel regAlma = new JPanel();
																				regAlma.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, Color.LIGHT_GRAY, null));
																				regAlma.setBounds(325, 101, 279, 352);
																				RegistrarAlma.add(regAlma);
																				regAlma.setLayout(new BorderLayout(0, 0));
																				
																						JPanel panelBtnRegistrar = new JPanel();
																						regAlma.add(panelBtnRegistrar, BorderLayout.SOUTH);
																						panelBtnRegistrar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
																						
																								btnRegistrar = new JButton("Registrar");
																								btnRegistrar.addActionListener(new ActionListener() {
																									public void actionPerformed(ActionEvent e) {
																											String codigo = txtCodigo.getText().toString();
																											String ciudad = txtCiudad.getText().toString();
																											String municipio = txtMunicipio.getText().toString();
																											double capacidad = Double.valueOf(spnCapacidad.getValue().toString());
																											int superficie = Integer.valueOf(spnSuperficie.getValue().toString());
																											Almacen aux = new Almacen(codigo, ciudad, municipio, capacidad, superficie);
																											miEmpresa.insertarAlmancen(aux);
																											JOptionPane.showMessageDialog(null, "Operacion satisfactoria", "Informacion", JOptionPane.INFORMATION_MESSAGE, null);
																											loadTableAlma();
																											cleanAlma();
																											if (miEmpresa.getCantAlmacenes() == 3) {
																												btnRegistrar.setEnabled(false);
																												JOptionPane.showMessageDialog(null, "No se pueden agregar mas almacenes", "Informacion", JOptionPane.INFORMATION_MESSAGE, null);
																												tabbedPane.setSelectedIndex(0);
																											}
																									}
																								});
																								panelBtnRegistrar.add(btnRegistrar);
																								
																										JPanel panelinfo = new JPanel();
																										regAlma.add(panelinfo, BorderLayout.CENTER);
																										panelinfo.setLayout(null);
																										
																												JLabel label = new JLabel("Codigo:");
																												label.setBounds(10, 11, 50, 14);
																												panelinfo.add(label);
																												
																														txtCodigo = new JTextField();
																														txtCodigo.setText("A-"+ Almacen.generatedCode);
																														txtCodigo.setEditable(false);
																														txtCodigo.setColumns(10);
																														txtCodigo.setBounds(60, 11, 140, 20);
																														panelinfo.add(txtCodigo);
																														
																																JLabel label_1 = new JLabel("Ciudad:");
																																label_1.setBounds(10, 46, 200, 14);
																																panelinfo.add(label_1);
																																
																																		txtCiudad = new JTextField();
																																		txtCiudad.setColumns(10);
																																		txtCiudad.setBounds(10, 75, 257, 20);
																																		panelinfo.add(txtCiudad);
																																		
																																				JLabel label_2 = new JLabel("Municipio:");
																																				label_2.setBounds(10, 104, 200, 14);
																																				panelinfo.add(label_2);
																																				
																																						txtMunicipio = new JTextField();
																																						txtMunicipio.setColumns(10);
																																						txtMunicipio.setBounds(10, 133, 257, 20);
																																						panelinfo.add(txtMunicipio);
																																						
																																								JLabel label_3 = new JLabel("Capacidad (Toneladas):");
																																								label_3.setBounds(10, 162, 200, 14);
																																								panelinfo.add(label_3);
																																								
																																										spnCapacidad = new JSpinner();
																																										spnCapacidad.setBounds(10, 191, 257, 20);
																																										panelinfo.add(spnCapacidad);
																																										
																																												JLabel label_4 = new JLabel("Supericie (m\u00B2): ");
																																												label_4.setBounds(10, 220, 200, 14);
																																												panelinfo.add(label_4);
																																												
																																														spnSuperficie = new JSpinner();
																																														spnSuperficie.setBounds(10, 249, 257, 20);
																																														panelinfo.add(spnSuperficie);
																																														
																																																JPanel panel = new JPanel();
																																																panel.setBackground(Color.LIGHT_GRAY);
																																																panel.setBorder(null);
																																																Almacenes.add(panel, BorderLayout.SOUTH);
																																																panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
																																																
																																																		Almacenes.setVisible(false);
																																																		
																																																				JButton btnPrincipal = new JButton("Volver a la pagina principal");
																																																				btnPrincipal.addActionListener(new ActionListener() {
																																																					public void actionPerformed(ActionEvent e) {
																																																						Almacenes.setVisible(false);
																																																						Option1.setBackground(Color.gray);
																																																						Option2.setBackground(Color.gray);
																																																						auxAlmacenes = false;
																																																						auxProductos = false;
																																																					}
																																																				});
																																																				panel.add(btnPrincipal);
		contentPane.add(Productos);
		Productos.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		Productos.add(panel_1, BorderLayout.SOUTH);

		JButton button = new JButton("Volver a la pagina principal");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Productos.setVisible(false);
				Option1.setBackground(Color.gray);
				Option2.setBackground(Color.gray);
				auxAlmacenes = false;
				auxProductos = false;
			}
		});
		panel_1.add(button);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBorder(null);
		tabbedPane_1.setBackground(Color.WHITE);
		Productos.add(tabbedPane_1, BorderLayout.CENTER);

		JPanel ListadoProductos = new JPanel();
		ListadoProductos.setBackground(Color.LIGHT_GRAY);
		tabbedPane_1.addTab("Listado", null, ListadoProductos, null);
		ListadoProductos.setLayout(new BorderLayout(0, 0));

		JPanel ListaP = new JPanel();
		ListaP.setBackground(Color.LIGHT_GRAY);
		ListadoProductos.add(ListaP, BorderLayout.SOUTH);
		ListaP.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JButton btnModificarP = new JButton("Modificar");
		btnModificarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Almacen aux = miEmpresa.buscarAlmaByCode(codigoAlma);
				int index = aux.buscarProdIndexByCode(codigoProd);
				ModProducto modP = new ModProducto(miEmpresa,aux.getProductos()[index]);
				modP.setModal(true);
				modP.setVisible(true);
			}
		});
		btnModificarP.setEnabled(false);
		ListaP.add(btnModificarP);

		JButton btnEliminarP = new JButton("Eliminar");
		btnEliminarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = miEmpresa.buscarAlmaIndexByCode(codigoAlma);
				miEmpresa.getAlmacenes()[i].eliminarProducto(codigoProd);
				JOptionPane.showMessageDialog(null, "Operacion satisfactoria", "Informacion", JOptionPane.INFORMATION_MESSAGE, null);
				loadTableProd();
				btnEliminarP.setEnabled(false);
				btnModificarP.setEnabled(false);
			}
		});
		btnEliminarP.setEnabled(false);
		ListaP.add(btnEliminarP);

		JScrollPane scrollPane_1 = new JScrollPane();
		ListadoProductos.add(scrollPane_1, BorderLayout.CENTER);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnModificarP.setEnabled(true);
				btnEliminarP.setEnabled(true);
				int index = table.getSelectedRow();
				codigoProd = (String) table.getValueAt(index, 0);
				codigoAlma = (String) table.getValueAt(index, 8);
			}
		});
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(table);

		JPanel RegistrarProducto = new JPanel();
		RegistrarProducto.setBackground(Color.LIGHT_GRAY);
		tabbedPane_1.addTab("Registrar", null, RegistrarProducto, null);
		RegistrarProducto.setLayout(null);

		JPanel regProd = new JPanel();
		regProd.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, Color.LIGHT_GRAY, null));
		regProd.setBounds(291, 123, 374, 286);
		RegistrarProducto.add(regProd);
		regProd.setLayout(new BorderLayout(0, 0));

		JPanel panelBtnReg = new JPanel();
		regProd.add(panelBtnReg, BorderLayout.SOUTH);
		panelBtnReg.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton button_1 = new JButton("Registrar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = txtCodigoP.getText();
				String nombre = txtNombreP.getText();
				String tipo = cbxTipo.getSelectedItem().toString();
				double pVenta = Double.valueOf(txtPrecioVenta.getText().toString());
				double pCompra = Double.valueOf(txtPrecioCompra.getText().toString());
				int stockInicial = Integer.valueOf(spnStockInial.getValue().toString());
				int diasVenci = Integer.valueOf(spnVencimiento.getValue().toString());
				String almaCode = cbxAlmacen.getSelectedItem().toString();
				Almacen aux = miEmpresa.buscarAlmaByCode(almaCode);
				Producto pro = new Producto(codigo, nombre, tipo, pCompra, pVenta, diasVenci, stockInicial, stockInicial, almaCode);
				aux.insertarProducto(pro);
				JOptionPane.showMessageDialog(null, "Operacion satisfactoria", "Informacion", JOptionPane.INFORMATION_MESSAGE, null);
				loadTableProd();
				cleanProd();
			}
		});
		panelBtnReg.add(button_1);

		JPanel panelInfoProd = new JPanel();
		regProd.add(panelInfoProd, BorderLayout.CENTER);
		panelInfoProd.setLayout(null);

		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(10, 11, 50, 14);
		panelInfoProd.add(lblCodigo);

		txtCodigoP = new JTextField();
		txtCodigoP.setText("P-"+Producto.generatedCode);
		txtCodigoP.setEditable(false);
		txtCodigoP.setBounds(70, 8, 80, 20);
		panelInfoProd.add(txtCodigoP);
		txtCodigoP.setColumns(10);

		JLabel lblAlmacen = new JLabel("Almacen:");
		lblAlmacen.setBounds(10, 216, 62, 14);
		panelInfoProd.add(lblAlmacen);

		cbxAlmacen = new JComboBox();
		cbxAlmacen.setBounds(70, 213, 169, 20);
		panelInfoProd.add(cbxAlmacen);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 36, 169, 14);
		panelInfoProd.add(lblNombre);

		txtNombreP = new JTextField();
		txtNombreP.setBounds(10, 61, 169, 20);
		panelInfoProd.add(txtNombreP);
		txtNombreP.setColumns(10);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 92, 169, 14);
		panelInfoProd.add(lblTipo);

		cbxTipo = new JComboBox();
		cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Electronicos", "Comestibles", "Atuendos"}));
		cbxTipo.setBounds(10, 117, 169, 20);
		panelInfoProd.add(cbxTipo);

		JLabel lblPrecioDeVenta = new JLabel("Precio de Venta:");
		lblPrecioDeVenta.setBounds(14, 148, 165, 14);
		panelInfoProd.add(lblPrecioDeVenta);

		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setBounds(10, 173, 169, 20);
		panelInfoProd.add(txtPrecioVenta);
		txtPrecioVenta.setColumns(10);

		JLabel lblPrecioDeCompra = new JLabel("Precio de Compra:");
		lblPrecioDeCompra.setBounds(189, 148, 169, 14);
		panelInfoProd.add(lblPrecioDeCompra);

		txtPrecioCompra = new JTextField();
		txtPrecioCompra.setBounds(189, 173, 169, 20);
		panelInfoProd.add(txtPrecioCompra);
		txtPrecioCompra.setColumns(10);

		JLabel lblStockInicial = new JLabel("Stock:");
		lblStockInicial.setBounds(189, 36, 169, 14);
		panelInfoProd.add(lblStockInicial);

		spnStockInial = new JSpinner();
		spnStockInial.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
		spnStockInial.setBounds(189, 61, 169, 20);
		panelInfoProd.add(spnStockInial);

		JLabel lblDiasParaVencimiento = new JLabel("Dias para Vencimiento:");
		lblDiasParaVencimiento.setBounds(189, 92, 169, 14);
		panelInfoProd.add(lblDiasParaVencimiento);

		spnVencimiento = new JSpinner();
		spnVencimiento.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnVencimiento.setBounds(189, 117, 169, 20);
		panelInfoProd.add(spnVencimiento);
	}

	public static void loadTableProd() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < miEmpresa.getCantAlmacenes(); i++) {
			for (int j = 0; j < miEmpresa.getAlmacenes()[i].getCantProd(); j++) {
				fila[0] = miEmpresa.getAlmacenes()[i].getProductos()[j].getCodigo();
				fila[1] = miEmpresa.getAlmacenes()[i].getProductos()[j].getNombre();
				fila[2] = miEmpresa.getAlmacenes()[i].getProductos()[j].getTipo();
				fila[3] = miEmpresa.getAlmacenes()[i].getProductos()[j].getpCompra();
				fila[4] = miEmpresa.getAlmacenes()[i].getProductos()[j].getpVenta();
				fila[5] = miEmpresa.getAlmacenes()[i].getProductos()[j].getDiasVenci();
				fila[6] = miEmpresa.getAlmacenes()[i].getProductos()[j].getStockReal();
				fila[7] = miEmpresa.getAlmacenes()[i].getProductos()[j].getStockInicial();
				fila[8] = miEmpresa.getAlmacenes()[i].getProductos()[j].getCodigoAlmacen();
				model.addRow(fila);
			}
		}
	}

	public static void loadAlmacenes() {
		cbxAlmacen.removeAllItems();
		for (int i = 0; i < miEmpresa.getCantAlmacenes(); i++) {
			cbxAlmacen.addItem(new String(miEmpresa.getAlmacenes()[i].getCodigo()));
		}
		cbxAlmacen.insertItemAt(new String("<Seleccione>"),0);
		cbxAlmacen.setSelectedIndex(0);

	}

	protected void cleanProd() {
		txtCodigoP.setText("P-"+Producto.generatedCode);
		txtNombreP.setText("");
		cbxTipo.setSelectedIndex(0);
		txtPrecioVenta.setText("");
		txtPrecioCompra.setText("");
		spnStockInial.setValue(new Integer(0));
		spnVencimiento.setValue(new Integer(0));
		cbxAlmacen.setSelectedIndex(0);

	}

	public void cleanAlma() {
		txtCodigo.setText("A-"+Almacen.generatedCode);
		txtCiudad.setText("");
		txtMunicipio.setText("");
		spnCapacidad.setValue(new Double(0));
		spnSuperficie.setValue(new Integer(0));
	}

	public static void loadTableAlma() {
		model1.setRowCount(0);
		fila1 = new Object[model1.getColumnCount()];
		for (int i = 0; i < miEmpresa.getCantAlmacenes(); i++) {
			fila1[0] = miEmpresa.getAlmacenes()[i].getCodigo();
			fila1[1] = miEmpresa.getAlmacenes()[i].getCiudad();
			fila1[2] = miEmpresa.getAlmacenes()[i].getMunicipio();
			fila1[3] = miEmpresa.getAlmacenes()[i].getCapacidad();
			fila1[4] = miEmpresa.getAlmacenes()[i].getSuperficie();
			model1.addRow(fila1);
		}
	}
}
