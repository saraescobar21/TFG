package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

/*
 *  @Autor: F.Ortiz retocado por Julio León
 *  Clase para obtener una conexión (Objeto java.sql.Connection)
 *  a una base de datos Oracle. 
 */
public class Conexion {
	
	public Conexion() {
	
	}
	
	public Connection getConexion() throws SQLException {
		Connection con = null;
		try {
			final String DB_URL = "jdbc:oracle:thin:@s2daw2022_high?TNS_ADMIN=C:/Diurno/oracle/instantclient_19_16/network/admin";
			final String DB_USER = "sara";
			final String DB_PASSWORD = "Azarquiel123";
			Properties info = new Properties();
			info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
			info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
			info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");
			OracleDataSource ods = new OracleDataSource();
			ods.setURL(DB_URL);
			ods.setConnectionProperties(info);
			con = ods.getConnection();
		} catch (SQLException e) {
			// Imprimo el mensaje y propago el error
			e.printStackTrace();
			throw e;
		}
		return con;
	}

}
