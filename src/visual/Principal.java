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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	@SuppressWarnings("unused")
	private static Almacen miAlma;
	private JPanel Almacenes;
	private JPanel Productos;
	private String codigoAlma;
	private String codigoProd;
	private JTextField txtCodigoP;
	private JTextField txtNombreP;
	private JTextField txtPrecioVenta;
	private JTextField txtPrecioCompra;
	private JComboBox<String> cbxTipo;
	private JSpinner spnStockInial;
	private JSpinner spnVencimiento;
	private static JComboBox<String> cbxAlmacen;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] fila;
	private JButton btnRegistrar;
	private static JComboBox<String> cbxAlmacenB;
	private JTextField txtGananciaBruta;
	private JTextField txtPerdidas;
	private JButton btnBuscarAlmacen;
	private JSpinner spnCantidad;
	private JComboBox<String> cbxProductos;
	private JTextField txtCantByType;
	private JComboBox<String> cbxProdType;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Empresa empre = new Empresa();
					Principal frame = new Principal(empre);
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
	public Principal(Empresa empre) {
		Principal.miEmpresa = empre;
		setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Estudio de Mercado");
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
		
		JPanel Perdidas = new JPanel();
		Perdidas.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		Perdidas.setBounds(0, 0, 458, 345);
		Principal.add(Perdidas);
		Perdidas.setLayout(null);
		
		JLabel lblPerdidas = new JLabel("Perdidas por Productos Vencidos");
		lblPerdidas.setToolTipText("");
		lblPerdidas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerdidas.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPerdidas.setBounds(10, 116, 438, 20);
		Perdidas.add(lblPerdidas);
		
		txtPerdidas = new JTextField();
		txtPerdidas.setText("0.00");
		txtPerdidas.setEditable(false);
		txtPerdidas.setHorizontalAlignment(SwingConstants.CENTER);
		txtPerdidas.setBounds(131, 147, 196, 20);
		Perdidas.add(txtPerdidas);
		txtPerdidas.setColumns(10);
		
		JButton btnActualizar_1 = new JButton("Actualizar");
		btnActualizar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean cierto = false;
				int i = 0;
				while (!cierto && i<miEmpresa.getCantAlmacenes()) {
					if (miEmpresa.getAlmacenes()[i].getCantProd() > 0) {
						cierto = true;
					}
					i++;
				}
				if (cierto) {
					double aux = miEmpresa.perdidas();
					txtPerdidas.setText(String.valueOf(aux));
				}				
			}
		});
		btnActualizar_1.setBounds(166, 178, 125, 23);
		Perdidas.add(btnActualizar_1);
		
		JPanel listaPdado = new JPanel();
		listaPdado.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		listaPdado.setBounds(458, 0, 458, 345);
		Principal.add(listaPdado);
		listaPdado.setLayout(null);
		
		JLabel lblListTipo = new JLabel("Productos por Tipo");
		lblListTipo.setToolTipText("");
		lblListTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblListTipo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblListTipo.setBounds(117, 80, 224, 20);
		listaPdado.add(lblListTipo);
		
		JLabel lblTipoDeProducto = new JLabel("Tipo de Producto:");
		lblTipoDeProducto.setBounds(148, 111, 162, 14);
		listaPdado.add(lblTipoDeProducto);
		
		cbxProdType = new JComboBox<String>();
		cbxProdType.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Comestibles", "Electronicos", "Atuendos"}));
		cbxProdType.setBounds(148, 136, 162, 20);
		listaPdado.add(cbxProdType);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(148, 167, 162, 14);
		listaPdado.add(lblCantidad);
		
		txtCantByType = new JTextField();
		txtCantByType.setHorizontalAlignment(SwingConstants.CENTER);
		txtCantByType.setText("0");
		txtCantByType.setEditable(false);
		txtCantByType.setBounds(148, 192, 162, 20);
		listaPdado.add(txtCantByType);
		txtCantByType.setColumns(10);
		
		JButton btnUpdateCant = new JButton("Actualizar");
		btnUpdateCant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = cbxProdType.getSelectedItem().toString();
				int cant = miEmpresa.cantProdByType(tipo);
				txtCantByType.setText(String.valueOf(cant));
			}
		});
		btnUpdateCant.setBounds(166, 223, 125, 23);
		listaPdado.add(btnUpdateCant);
		
		JPanel gBruta = new JPanel();
		gBruta.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		gBruta.setBounds(0, 345, 458, 345);
		Principal.add(gBruta);
		gBruta.setLayout(null);
		
		JLabel lblGananciaBruta = new JLabel("Ganancia Bruta");
		lblGananciaBruta.setBounds(166, 64, 125, 20);
		gBruta.add(lblGananciaBruta);
		lblGananciaBruta.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGananciaBruta.setToolTipText("");
		lblGananciaBruta.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblAlmacen_1 = new JLabel("Almacen:");
		lblAlmacen_1.setBounds(166, 95, 125, 14);
		gBruta.add(lblAlmacen_1);
		
		cbxAlmacenB = new JComboBox<String>();
		cbxAlmacenB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loadAlmacenesB();
			}
		});
		cbxAlmacenB.setBounds(166, 120, 125, 20);
		cbxAlmacenB.insertItemAt(new String("<Seleccione>"),0);
		cbxAlmacenB.setSelectedIndex(0);
		gBruta.add(cbxAlmacenB);
		
		JLabel lblGanancias = new JLabel("Ganancias:");
		lblGanancias.setBounds(166, 151, 125, 14);
		gBruta.add(lblGanancias);
		
		txtGananciaBruta = new JTextField();
		txtGananciaBruta.setHorizontalAlignment(SwingConstants.CENTER);
		txtGananciaBruta.setText("0.00");
		txtGananciaBruta.setEditable(false);
		txtGananciaBruta.setBounds(166, 179, 125, 20);
		gBruta.add(txtGananciaBruta);
		txtGananciaBruta.setColumns(10);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbxAlmacenB.getSelectedIndex() != 0) {
					Almacen aux = miEmpresa.buscarAlmaByCode(cbxAlmacenB.getSelectedItem().toString());
					if (aux.getCantProd() > 0) {
						txtGananciaBruta.setText(String.valueOf(miEmpresa.estGananciasBruta(cbxAlmacenB.getSelectedItem().toString())));
					}
				}
			}
		});
		btnActualizar.setBounds(166, 210, 125, 20);
		gBruta.add(btnActualizar);
		
		JPanel despachar = new JPanel();
		despachar.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		despachar.setBounds(458, 345, 458, 345);
		Principal.add(despachar);
		despachar.setLayout(null);
		
		JLabel lblDespachar = new JLabel("Despachar Producto");
		lblDespachar.setToolTipText("");
		lblDespachar.setHorizontalAlignment(SwingConstants.CENTER);
		lblDespachar.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDespachar.setBounds(141, 63, 175, 20);
		despachar.add(lblDespachar);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(141, 94, 175, 14);
		despachar.add(lblProducto);
		
		cbxProductos = new JComboBox<String>();
		cbxProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loadProductosB();
			}
		});
		cbxProductos.setBounds(141, 119, 175, 20);
		despachar.add(cbxProductos);
		
		JLabel lblCantidadDeseada = new JLabel("Cantidad deseada:");
		lblCantidadDeseada.setBounds(141, 150, 175, 14);
		despachar.add(lblCantidadDeseada);
		
		spnCantidad = new JSpinner();
		spnCantidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnCantidad.setBounds(141, 175, 175, 20);
		despachar.add(spnCantidad);
		
		btnBuscarAlmacen = new JButton("Buscar Almacen");
		btnBuscarAlmacen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigoAlmaR = miEmpresa.recogerProd(cbxProductos.getSelectedItem().toString(), Integer.valueOf(spnCantidad.getValue().toString()));
				JOptionPane.showMessageDialog(null, "El producto deseado se puede recoger en el almacen: " + codigoAlmaR, "Informacion", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnBuscarAlmacen.setBounds(154, 206, 149, 23);
		despachar.add(btnBuscarAlmacen);

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
				Principal.setVisible(false);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (!auxAlmacenes) {
					Option1.setBackground(Color.gray);
				}
			}
		});

		JLabel lblEmpresaQsy = new JLabel("");
		java.awt.Image img = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		lblEmpresaQsy.setIcon(new ImageIcon(img));
		lblEmpresaQsy.setForeground(Color.WHITE);
		lblEmpresaQsy.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpresaQsy.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		lblEmpresaQsy.setBounds(10, 347, 340, 200);
		MenuPanel.add(lblEmpresaQsy);
		Option1.setBackground(Color.gray);
		Option1.setBounds(0, 120, 358, 52);
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
				Principal.setVisible(false);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (!auxProductos) {
					Option2.setBackground(Color.gray);
				}
			}
		});
		Option2.setBackground(Color.GRAY);
		Option2.setBounds(0, 181, 358, 52);
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
																											
																											if (codigo.equalsIgnoreCase("") || ciudad.equalsIgnoreCase("") || municipio.equalsIgnoreCase("") || capacidad < 1 ||
																													superficie < 1) {
																												JOptionPane.showMessageDialog(null, "Revise los campos", "Informacion", JOptionPane.WARNING_MESSAGE, null);
																											}else {
																												Almacen aux = new Almacen(codigo, ciudad, municipio, capacidad, superficie);
																												miEmpresa.insertarAlmancen(aux);
																												JOptionPane.showMessageDialog(null, "Operacion satisfactoria", "Informacion", JOptionPane.INFORMATION_MESSAGE, null);
																												loadTableAlma();
																												cleanAlma();
																											}
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
																																																						Principal.setVisible(true);
																																																						loadAlmacenesB();
																																																						loadProductosB();
																																																						cbxProdType.setSelectedIndex(0);
																																																						tabbedPane.setSelectedIndex(0);
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
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBorder(null);
		tabbedPane_1.setBackground(Color.WHITE);
		Productos.add(tabbedPane_1, BorderLayout.CENTER);

		JButton button = new JButton("Volver a la pagina principal");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Productos.setVisible(false);
				Option1.setBackground(Color.gray);
				Option2.setBackground(Color.gray);
				auxAlmacenes = false;
				auxProductos = false;
				Principal.setVisible(true);
				loadAlmacenesB();
				loadProductosB();
				cbxProdType.setSelectedIndex(0);
				tabbedPane_1.setSelectedIndex(0);
			}
		});
		panel_1.add(button);

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
				if (codigo.equalsIgnoreCase("") || nombre.equalsIgnoreCase("") || tipo.equalsIgnoreCase("<Seleccione>") || pVenta < 0 || pCompra < 0 
						|| stockInicial < 0 || diasVenci < 0 || almaCode.equalsIgnoreCase("<Seleccione>")) {
					JOptionPane.showMessageDialog(null, "Revise los campos", "Informacion", JOptionPane.INFORMATION_MESSAGE, null);
				}else {
					Producto pro = new Producto(codigo, nombre, tipo, pCompra, pVenta, diasVenci, stockInicial, stockInicial, almaCode);
					aux.insertarProducto(pro);
					JOptionPane.showMessageDialog(null, "Operacion satisfactoria", "Informacion", JOptionPane.INFORMATION_MESSAGE, null);
					loadTableProd();
					cleanProd();
				}
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

		cbxAlmacen = new JComboBox<String>();
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

		cbxTipo = new JComboBox<String>();
		cbxTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Electronicos", "Comestibles", "Atuendos"}));
		cbxTipo.setBounds(10, 117, 169, 20);
		panelInfoProd.add(cbxTipo);

		JLabel lblPrecioDeVenta = new JLabel("Precio de Venta:");
		lblPrecioDeVenta.setBounds(14, 148, 165, 14);
		panelInfoProd.add(lblPrecioDeVenta);

		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setText("0");
		txtPrecioVenta.setBounds(10, 173, 169, 20);
		panelInfoProd.add(txtPrecioVenta);
		txtPrecioVenta.setColumns(10);

		JLabel lblPrecioDeCompra = new JLabel("Precio de Compra:");
		lblPrecioDeCompra.setBounds(189, 148, 169, 14);
		panelInfoProd.add(lblPrecioDeCompra);

		txtPrecioCompra = new JTextField();
		txtPrecioCompra.setText("0");
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
	public void loadAlmacenesB() {
		cbxAlmacenB.removeAllItems();
		for (int i = 0; i < miEmpresa.getCantAlmacenes(); i++) {
			cbxAlmacenB.addItem(new String(miEmpresa.getAlmacenes()[i].getCodigo()));
		}
		cbxAlmacenB.insertItemAt(new String("<Seleccione>"),0);
		cbxAlmacenB.setSelectedIndex(0);

	}
	public void loadProductosB() {
		cbxProductos.removeAllItems();
		for (int i = 0; i < miEmpresa.getCantAlmacenes(); i++) {
			for (int j = 0; j < miEmpresa.getAlmacenes()[i].getCantProd(); j++) {
				cbxProductos.addItem(new String(miEmpresa.getAlmacenes()[i].getProductos()[j].getCodigo()));
			}
		}
		cbxProductos.insertItemAt(new String("<Seleccione>"),0);
		cbxProductos.setSelectedIndex(0);

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
