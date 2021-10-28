package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridLayout;
/**
 * Clase con interfaz gráfica para añadir registros a la base de datos y mostrarlos en la tabla
 * de la ventana principal
 * @author jorge
 *@version 1
 */
public class VentanaAdd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static VentanaTablaPrincipal v=new VentanaTablaPrincipal();
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldEdad;
	
	/**
	 * Lanza la aplicación
	 */
	public static void main(String[] args) {
		try {
			VentanaAdd dialog = new VentanaAdd(v, true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el dialogo
	 */
	public VentanaAdd(JFrame parent, boolean modal) {
		super(parent, modal);
		setTitle("A\u00F1adir");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblTitol = new JLabel("Introduce los datos que quieras a\u00F1adir:");
			lblTitol.setFont(new Font("Tahoma", Font.BOLD, 12));
			contentPanel.add(lblTitol, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(0, 4, 0, 0));
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel lblNombre = new JLabel("Nombre");
				lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(lblNombre);
			}
			{
				textFieldNombre = new JTextField();
				panel.add(textFieldNombre);
				textFieldNombre.setColumns(10);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel lblApellidos = new JLabel("Apellidos");
				lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
				lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(lblApellidos);
			}
			{
				textFieldApellidos = new JTextField();
				panel.add(textFieldApellidos);
				textFieldApellidos.setColumns(10);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel lblNewLabel = new JLabel("Edad");
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(lblNewLabel);
			}
			{
				textFieldEdad = new JTextField();
				panel.add(textFieldEdad);
				textFieldEdad.setColumns(10);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
			{
				JButton btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					/**
					 * Funcion del JButton 'btnAceptar' que inserta el nuevo registro en la base de datos
					 * @see <a href = "https://docs.oracle.com/javase/7/docs/api/javax/swing/JButton.html" />Class JButton</a>
					 *  @param e hace referencia al evento que se produce al hacer click en el botón
					 */
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
												
						v=(VentanaTablaPrincipal)parent;
						DefaultTableModel modelo=v.getModelo();
						try (PreparedStatement sentenciaPreparada = v.conexion
								.prepareStatement("insert into datospersona (Nombre, Apellidos, Edad) values(?,?,?)");) {
							

							sentenciaPreparada.setString(1, textFieldNombre.getText());
							sentenciaPreparada.setString(2, textFieldApellidos.getText());
							sentenciaPreparada.setInt(3, Integer.parseInt(textFieldEdad.getText()));
							
							sentenciaPreparada.executeUpdate();
							System.out.println("Insertado");

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						v.rellenarTabla();
						dispose();
					}
					
				});
				panel.add(btnAceptar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					/**
					 * Función del JButton 'btnCancelar' que destrulle la ventana
					 * @see <a href = "https://docs.oracle.com/javase/7/docs/api/javax/swing/JButton.html" />Class JButton</a>
					 */
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						dispose();
					}
					
				});
				panel.add(btnCancelar);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
		}
		setVisible(true);
	}

}
