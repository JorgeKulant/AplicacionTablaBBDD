package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
/**
 * Clase con interfaz gráfica que elimina registros de la base de datos
 * @author jorge
 *@version 1
 */
public class VentanaDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static VentanaTablaPrincipal v=new VentanaTablaPrincipal();
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEdad;

	/**
	 * Lanza la aplicación
	 */
	public static void main(String[] args) {
		try {
			VentanaDelete dialog = new VentanaDelete(v, true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el dialogo
	 */
	public VentanaDelete(JFrame parent, boolean modal) {
		super(parent, modal);
		setTitle("Eliminar");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(5, 5, 141, 31);
		contentPanel.add(label);
		
		JLabel lblNewLabel_2 = new JLabel("Introduce los datos del registro que quieras eliminar:");
		lblNewLabel_2.setBounds(5, 5, 372, 31);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPanel.add(lblNewLabel_2);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(287, 5, 141, 31);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(5, 36, 141, 31);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(146, 36, 141, 31);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(287, 36, 141, 31);
		contentPanel.add(label_4);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(5, 66, 141, 31);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPanel.add(lblNewLabel);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(146, 67, 141, 31);
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(287, 67, 141, 31);
		contentPanel.add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(5, 98, 141, 31);
		contentPanel.add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setBounds(146, 98, 141, 31);
		contentPanel.add(label_7);
		
		JLabel label_8 = new JLabel("");
		label_8.setBounds(287, 98, 141, 31);
		contentPanel.add(label_8);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(5, 129, 141, 31);
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPanel.add(lblApellido);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(146, 129, 141, 31);
		contentPanel.add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel label_9 = new JLabel("");
		label_9.setBounds(287, 129, 141, 31);
		contentPanel.add(label_9);
		
		JLabel label_10 = new JLabel("");
		label_10.setBounds(5, 160, 141, 31);
		contentPanel.add(label_10);
		
		JLabel label_11 = new JLabel("");
		label_11.setBounds(146, 160, 141, 31);
		contentPanel.add(label_11);
		
		JLabel label_12 = new JLabel("");
		label_12.setBounds(287, 160, 141, 31);
		contentPanel.add(label_12);
		
		JLabel lblNewLabel_1 = new JLabel("Edad:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(5, 191, 141, 31);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPanel.add(lblNewLabel_1);
		
		textFieldEdad = new JTextField();
		textFieldEdad.setBounds(146, 191, 141, 31);
		contentPanel.add(textFieldEdad);
		textFieldEdad.setColumns(10);
		
		JLabel label_13 = new JLabel("");
		label_13.setBounds(287, 191, 141, 31);
		contentPanel.add(label_13);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAceptar = new JButton("Aceptar");
				btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnAceptar.setActionCommand("OK");
				btnAceptar.addActionListener(new ActionListener() {
					/**
					 * Funcion del JButton 'btnAceptar' que elimina registro en la base de datos
					 * @see <a href = "https://docs.oracle.com/javase/7/docs/api/javax/swing/JButton.html" />Class JButton</a>
					 * @param e hace referencia al evento que se produce al hacer click en el botón
					 */
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						v=(VentanaTablaPrincipal)parent;
						DefaultTableModel modelo=v.getModelo();
						try (PreparedStatement sentenciaPreparada = v.conexion
								.prepareStatement("delete from datospersona where Nombre=? and Apellidos=? and Edad=?");) {
							

							sentenciaPreparada.setString(1, textFieldNombre.getText());
							sentenciaPreparada.setString(2, textFieldApellido.getText());
							sentenciaPreparada.setInt(3, Integer.parseInt(textFieldEdad.getText()));

							
							sentenciaPreparada.executeUpdate();
							System.out.println("Eliminado");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						v.rellenarTabla();
						dispose();
					}
					
				});
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				buttonPane.add(btnAceptar);
				
				getRootPane().setDefaultButton(btnAceptar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
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
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
	}
}
