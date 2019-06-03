package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Almacen;
import logica.Empresa;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModAlmacen extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtCiudad;
	private JTextField txtMunicipio;
	private JSpinner spnSuperficie;
	private JSpinner spnCapacidad;
	private Almacen miAlma;
	@SuppressWarnings("unused")
	private Empresa miEmpre;

	/**
	 * Create the dialog.
	 */
	public ModAlmacen(Empresa empre, Almacen alma) {
		setResizable(false);
		this.miEmpre = empre;
		this.miAlma = alma;
		setTitle("Modificar Almacen");
		setBounds(100, 100, 299, 370);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			contentPanel.add(panel, BorderLayout.CENTER);
			{
				JLabel label = new JLabel("Codigo:");
				label.setBounds(10, 11, 50, 14);
				panel.add(label);
			}
			{
				txtCodigo = new JTextField();
				txtCodigo.setEditable(false);
				txtCodigo.setColumns(10);
				txtCodigo.setBounds(60, 11, 140, 20);
				panel.add(txtCodigo);
			}
			{
				JLabel label = new JLabel("Ciudad:");
				label.setBounds(10, 46, 200, 14);
				panel.add(label);
			}
			{
				txtCiudad = new JTextField();
				txtCiudad.setColumns(10);
				txtCiudad.setBounds(10, 75, 257, 20);
				panel.add(txtCiudad);
			}
			{
				JLabel label = new JLabel("Municipio:");
				label.setBounds(10, 104, 200, 14);
				panel.add(label);
			}
			{
				txtMunicipio = new JTextField();
				txtMunicipio.setColumns(10);
				txtMunicipio.setBounds(10, 133, 257, 20);
				panel.add(txtMunicipio);
			}
			{
				JLabel label = new JLabel("Capacidad (Toneladas):");
				label.setBounds(10, 162, 200, 14);
				panel.add(label);
			}
			{
				spnCapacidad = new JSpinner();
				spnCapacidad.setBounds(10, 191, 257, 20);
				panel.add(spnCapacidad);
			}
			{
				JLabel label = new JLabel("Supericie (m\u00B2): ");
				label.setBounds(10, 220, 200, 14);
				panel.add(label);
			}
			{
				spnSuperficie = new JSpinner();
				spnSuperficie.setBounds(10, 249, 257, 20);
				panel.add(spnSuperficie);
			}
			loadAlmacen();
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
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
						miAlma.setCodigo(codigo);
						miAlma.setCiudad(ciudad);
						miAlma.setMunicipio(municipio);
						miAlma.setCapacidad(capacidad);
						miAlma.setSuperficie(superficie);
						JOptionPane.showMessageDialog(null, "Operacion exitosa", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						Principal.loadTableAlma();
						}
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

	private void loadAlmacen() {
		txtCodigo.setText(miAlma.getCodigo());
		txtCiudad.setText(miAlma.getCiudad());
		txtMunicipio.setText(miAlma.getMunicipio());
		spnCapacidad.setValue(miAlma.getCapacidad());
		spnSuperficie.setValue(miAlma.getSuperficie());	
	}

}
