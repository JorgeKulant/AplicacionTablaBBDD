package conexionBBDD;
/**Clase para conectarse a la base de datos
 * @author jorge
 * @version 1
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
	
	private String nombreBD="IpMoLNiazp";
	private String usuarioBD;
	private String password;
	private String url="jdbc:mysql://remotemysql.com:3306/IpMoLNiazp";
	
	Connection conn=null;
	/**
	 * Constructor 
	 * @param nombre hace referencia al usuario para acceder a la base de datos
	 * @param contra hace referencia ala contraseña para entrar a la base de datos
	 */
	public Conector(String nombre, String contra) {
		
		this.usuarioBD=nombre;
		this.password=contra;
		/**
		 * Se Conecta a la base de datos
		 * @throws SQLException
		 * @see <a href = "https://docs.oracle.com/javase/7/docs/api/java/sql/SQLException.html" /> SQLException </a>
		 */
		try {
			
			conn=DriverManager.getConnection(url, usuarioBD, password);
			
			if(conn!=null) {
				System.out.println("Conectado a la base de datos "+nombreBD);
			}
		
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Metodo para desconectarse de la base de datos
	 */
	public void desconectar() {
		conn=null;
		
	}
	/**
	 * Metodo para obtener la conexión
	 * @return retorna la conexión
	 */
	public Connection getConn() {
		return conn;
	}
	
	
	}
