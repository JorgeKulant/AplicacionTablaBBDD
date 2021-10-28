package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import conexionBBDD.Conector;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Clase que muestra la información de la base de datos en una tabla
 * @author jorge
 * @version 1
 */
public class VentanaTablaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static VentanaLogin v;
	private static VentanaTablaPrincipal frame;
	static Connection conexion;
	DefaultTableModel modelo;
	
	/**
	 * Lanza la aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					frame = new VentanaTablaPrincipal();
					frame.setVisible(true);
					v=new VentanaLogin(frame);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea el marco
	 */
	public VentanaTablaPrincipal() {
		setTitle("Tablas Frames");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new DesconectarBBDD());
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		/**
		 * btnAnyadir llama al dialogo para añadir registros usando como padre este mismo JFrame
		 */
		JButton btnAnyadir = new JButton("A\u00F1adir");
		btnAnyadir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaAdd va=new VentanaAdd(frame, true);
				
			}
			
		});
		
		panel.add(btnAnyadir);
		/**
		 * btnEliminar llama al dialogo para eliminar registros de la base de datos usando como padre este mismo frame
		 */
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaDelete vd=new VentanaDelete(frame, true);
				
			}
			
		});
		panel.add(btnEliminar);
		
		modelo=new DefaultTableModel();
		table = new JTable(modelo);
		table.setAutoCreateRowSorter(true);
		JScrollPane scroll=new JScrollPane(table);
		contentPane.add(scroll, BorderLayout.CENTER);
		
		JLabel lblTitol = new JLabel("Tabla:");
		lblTitol.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblTitol, BorderLayout.NORTH);
		
		
		
	}
	/**
	 * Metodo que devuelve el modelo de la traba	
	 * @return devuelve el modelo de la tabla que usamos
	 * @see <a href = "https://docs.oracle.com/javase/7/docs/api/javax/swing/table/DefaultTableModel.html" />Class DefaultTableModel</a>
	 */
	public DefaultTableModel getModelo() {
		return modelo;
	}
	/**
	 * Metodo que establece un nuevo modelo y rellena la tabla con los datos de la base de datos
	 */
	void rellenarTabla() {
		modelo=new DefaultTableModel();
		table.setModel(modelo);
		conexion=VentanaLogin.getConexion().getConn();
		Object[] row;
		try (Statement sentencia = conexion.createStatement();
				ResultSet rs = sentencia.executeQuery("Select * from datospersona");) {
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			

			for (int i = 0; i < numberOfColumns; i++) {
				modelo.addColumn(rsmd.getColumnName(i + 1)); 
			}
			
			while (rs.next()) {
				row = new Object[numberOfColumns];
				for (int i = 0; i < numberOfColumns; i++) {
					row[i] = rs.getObject(i + 1);
					
				}
				modelo.addRow(row);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	/**
	 * Clase que extiende de WindowsAdapter desconecta la base de datos al cerrar el JFrame
	 * @author jorge
	 *
	 */
	class DesconectarBBDD extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			try {
				conexion.close();
				System.out.println("Base de datos desconectada");

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				System.exit(0);
			}

		}
		
	}
	
}
