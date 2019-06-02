package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Almacen;
import logica.Empresa;
import logica.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class ModProducto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtpVenta;
	private JTextField txtpCompra;
	private Producto miProducto;
	private Empresa miEmpresa;
	private JComboBox cbxAlmacen;
	private JComboBox cbxTipo;
	private JSpinner spnStockReal;
	private JSpinner spnVenci;
	

	/**
	 * Create the dialog.
	 */
	public ModProducto(Empresa empre, Producto pro) {
		this.miProducto = pro;
		this.miEmpresa = empre;
		setTitle("Modificar Producto");
		setBounds(100, 100, 382, 330);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBounds(0, 0, 370, 249);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("Codigo:");
				label.setBounds(10, 11, 50, 14);
				panel.add(label);
			}
			{
				txtCodigo = new JTextField();
				txtCodigo.setEditable(false);
				txtCodigo.setColumns(10);
				txtCodigo.setBounds(70, 8, 80, 20);
				panel.add(txtCodigo);
			}
			{
				JLabel label = new JLabel("Almacen:");
				label.setBounds(10, 216, 62, 14);
				panel.add(label);
			}
			{
				cbxAlmacen = new JComboBox();
				cbxAlmacen.setBounds(70, 213, 169, 20);
				panel.add(cbxAlmacen);
			}
			{
				JLabel label = new JLabel("Nombre:");
				label.setBounds(10, 36, 169, 14);
				panel.add(label);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setColumns(10);
				txtNombre.setBounds(10, 61, 169, 20);
				panel.add(txtNombre);
			}
			{
				JLabel label = new JLabel("Tipo:");
				label.setBounds(10, 92, 169, 14);
				panel.add(label);
			}
			{
				cbxTipo = new JComboBox();
				cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Electronicos", "Comestibles", "Atuendos"}));
				cbxTipo.setBounds(10, 117, 169, 20);
				panel.add(cbxTipo);
			}
			{
				JLabel label = new JLabel("Precio de Venta:");
				label.setBounds(14, 148, 165, 14);
				panel.add(label);
			}
			{
				txtpVenta = new JTextField();
				txtpVenta.setColumns(10);
				txtpVenta.setBounds(10, 173, 169, 20);
				panel.add(txtpVenta);
			}
			{
				JLabel label = new JLabel("Precio de Compra:");
				label.setBounds(189, 148, 169, 14);
				panel.add(label);
			}
			{
				txtpCompra = new JTextField();
				txtpCompra.setColumns(10);
				txtpCompra.setBounds(189, 173, 169, 20);
				panel.add(txtpCompra);
			}
			{
				JLabel label = new JLabel("Stock:");
				label.setBounds(189, 36, 169, 14);
				panel.add(label);
			}
			{
				spnStockReal = new JSpinner();
				spnStockReal.setBounds(189, 61, 169, 20);
				panel.add(spnStockReal);
			}
			{
				JLabel label = new JLabel("Dias para Vencimiento:");
				label.setBounds(189, 92, 169, 14);
				panel.add(label);
			}
			{
				spnVenci = new JSpinner();
				spnVenci.setBounds(189, 117, 169, 20);
				panel.add(spnVenci);
			}
			loadAlmacenes();
			loadProducto();
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String codigo = txtCodigo.getText();
						String nombre = txtNombre.getText();
						String tipo = cbxTipo.getSelectedItem().toString();
						double pVenta = Double.valueOf(txtpVenta.getText().toString());
						double pCompra = Double.valueOf(txtpCompra.getText().toString());
						int stockInicial = Integer.valueOf(spnStockReal.getValue().toString());
						int diasVenci = Integer.valueOf(spnVenci.getValue().toString());
						String almaCode = cbxAlmacen.getSelectedItem().toString();
						
						miProducto.setCodigo(codigo);
						miProducto.setNombre(nombre);
						miProducto.setTipo(tipo);
						miProducto.setpCompra(pCompra);
						miProducto.setpVenta(pVenta);
						miProducto.setStockReal(stockInicial);
						miProducto.setDiasVenci(diasVenci);
						miProducto.setCodigoAlmacen(almaCode);
						
						JOptionPane.showMessageDialog(null, "Operacion satisfactoria", "Informacion", JOptionPane.INFORMATION_MESSAGE, null);
						dispose();
						main.loadTableProd();
					}
				});
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

	private void loadAlmacenes() {
		cbxAlmacen.removeAllItems();
		for (int i = 0; i < miEmpresa.getCantAlmacenes(); i++) {
			cbxAlmacen.addItem(new String(miEmpresa.getAlmacenes()[i].getCodigo()));
		}
		cbxAlmacen.insertItemAt(new String("<Seleccione>"),0);
		cbxAlmacen.setSelectedIndex(0);		
	}

	private void loadProducto() {
		txtCodigo.setText(miProducto.getCodigo());
		cbxAlmacen.setSelectedItem(miProducto.getCodigoAlmacen().toString());
		txtNombre.setText(miProducto.getNombre());
		cbxTipo.setSelectedItem(miProducto.getTipo().toString());
		txtpVenta.setText(String.valueOf(miProducto.getpVenta()));
		txtpCompra.setText(String.valueOf(miProducto.getpCompra()));
		spnStockReal.setValue(miProducto.getStockReal());
		spnVenci.setValue(miProducto.getDiasVenci());
	}

}
