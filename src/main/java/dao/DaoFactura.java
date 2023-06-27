package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Connection;

import conexiones.Conexion;
import entidades.Empresario;
import entidades.Factura;
import entidades.Servicio;

public class DaoFactura {
	
	public DaoFactura() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return Devuelve un ArrayList de objetos autor con los autores 
	 *         hay actualmente en la tabla AUTOR de nuestra base de datos.
	 * @throws SQLException: Cualquier error en el acceso o en la ejecución
	 */
	
	
	public void inserta(Factura f) throws SQLException, Exception {
        Connection con=null;
        PreparedStatement st=null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            con.setAutoCommit(false);
            DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd/MM/yy");;
            // Las secuencias siguen el formato seq_autor_id
            String ordenSQL = "INSERT INTO FACTURA VALUES(SEQ_FACTURA_ID.NEXTVAL,?,?,?)";
            String generatedColumns[] = {"ID"};
            st = con.prepareStatement(ordenSQL,generatedColumns);
            st.setString(1, f.getCliente());
            st.setDate(2, f.getFecha());
            st.setInt(3, f.getImporte());
            st.executeUpdate();
            ResultSet rsgk = st.getGeneratedKeys();
            rsgk.next();
            f.setId(rsgk.getInt(1)); 
            con.commit();
            st.close();
            con.close();
        } catch (SQLException se) {
            throw se;
        } catch (Exception e) {
            throw e;
        }
        finally{
         	if(st!=null)
                st.close();
         	if(con!=null)
                con.close();
        }
    }
	
	

	public ArrayList<Factura> listado() throws SQLException,Exception{
		
		ArrayList<Factura> listado=new ArrayList<>();
		Conexion conexion=new Conexion();  
		Connection con=null;
		ResultSet rs = null;
		Statement st = null;
		try {
			con=conexion.getConexion();  
			st = con.createStatement();// Creamos un objeto Statement
			 
			String ordenSQL = "SELECT * FROM FACTURA ORDER By CLIENTE"; // sentencia a ejecutar
			rs = st.executeQuery(ordenSQL);	
			while (rs.next()) {
				Factura miFactura = new Factura();
				miFactura.setId(rs.getInt("ID"));
				miFactura.setCliente(rs.getString("CLIENTE"));
				miFactura.setFecha(rs.getDate("FECHA"));
				miFactura.setImporte(rs.getInt("IMPORTE"));
				
				listado.add(miFactura);
			}			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw e;
		} catch (Exception ex) {
			//ex.printStackTrace();
			throw ex;
		}
		finally {      
			// liberamos los recursos en un finally para asegurarnos que se cierra
			// todo lo abierto
			if(rs!=null)
				rs.close();
			if(st!=null)
				st.close();
			if(con!=null)
				con.close();
		}
		return listado; // retornamos el resultado en forma de array
	}
	
	
	public Factura findById(short id)throws SQLException {
		Factura a=null;
        Connection con=null;
        PreparedStatement st=null;
        ResultSet rs = null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            String ordenSQL = "SELECT * FROM FACTURA"+
            				  " WHERE ID=?,?,?,?";
            st = con.prepareStatement(ordenSQL);
            st.setShort(1, id);
            rs=st.executeQuery();   
            if(rs.next()) {             
            	a=new Factura();
            	a.setId(rs.getShort("ID"));
            	a.setCliente(rs.getString("CLIENTE"));
            	a.setFecha(rs.getDate("FECHA"));
            	a.setImporte(rs.getInt("IMPORTE"));
            	
            }
        } catch (SQLException se) {
            throw se;
        } finally{
         	if(rs!=null)
                rs.close();
         	if(st!=null)
                st.close();
         	if(con!=null)
                con.close();
        }
        return a;
	}	
	
}

