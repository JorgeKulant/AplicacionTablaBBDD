package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import conexionBBDD.Conector;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Clase para entrar en la base de datos
 * @author jorge
 *@version 1
 */
public class VentanaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textContra;
	private static VentanaTablaPrincipal v;
	/**
	 * Lanza la aplicación
	 */
	//static VentanaLogin frame;
	public static Conector conexion;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin(v);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea el marco
	 */
	public VentanaLogin(JFrame ventana) {
		this.v=(VentanaTablaPrincipal) ventana;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Base de Datos");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label = new JLabel("");
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		contentPane.add(label_1);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNombre);
		
		textNombre = new JTextField();
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel label_2 = new JLabel("");
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("");
		contentPane.add(label_3);
		
		JLabel lblContra = new JLabel("Contrase\u00F1a");
		lblContra.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblContra);
		
		textContra = new JTextField();
		contentPane.add(textContra);
		textContra.setColumns(10);
		
		JLabel label_4 = new JLabel("");
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("");
		contentPane.add(label_5);
		/**
		 * Botón para conectarse a la base de datos
		 */
		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				conexion= new Conector(textNombre.getText(), textContra.getText());
				System.out.println(textNombre.getText());
				if(conexion.getConn()!=null) {
					setVisible(false);
					//VentanaTabla t=new VentanaTabla();
					v.rellenarTabla();
					
				}else {
					
					
					JOptionPane.showMessageDialog(null,"No ha sido posible establecer la conexión", "ERROR DE CONEXIÓN", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
			
		});
		contentPane.add(btnConectar);
		/**
		 * Este botón oculta la ventana
		 */
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				
			}
		});
		contentPane.add(btnCancelar);
		
		JLabel label_6 = new JLabel("");
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("");
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("");
		contentPane.add(label_8);
		
		setVisible(true);
	}

	public static Conector getConexion() {
		return conexion;
	}
	
	
	
	

}
