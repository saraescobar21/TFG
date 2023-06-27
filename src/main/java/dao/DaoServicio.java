package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Empresario;
import entidades.Servicio;

public class DaoServicio {

	public DaoServicio() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return Devuelve un ArrayList de objetos autor con los autores 
	 *         hay actualmente en la tabla AUTOR de nuestra base de datos.
	 * @throws SQLException: Cualquier error en el acceso o en la ejecución
	 */
	
	
	public void inserta(Servicio s) throws SQLException, Exception {
        Connection con=null;
        PreparedStatement st=null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            con.setAutoCommit(false);
            // Las secuencias siguen el formato seq_autor_id
            String ordenSQL = "INSERT INTO SERVICIO VALUES(SEQ_SERVICIO_ID.NEXTVAL,?,?,?,?)";
            String generatedColumns[] = {"ID"};
            st = con.prepareStatement(ordenSQL,generatedColumns);
            st.setString(1, s.getDescripcion());
            st.setInt(2, s.getCosto());
            st.setInt(3, s.getFactura_id());
            st.setInt(4, s.getEmpresario_id());
            st.executeUpdate();
            ResultSet rsgk = st.getGeneratedKeys();
            rsgk.next();
            s.setId(rsgk.getInt(1)); 
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
	
	

	public ArrayList<Servicio> listado() throws SQLException,Exception{
		
		ArrayList<Servicio> listado=new ArrayList<>();
		Conexion conexion=new Conexion();  
		Connection con=null;
		ResultSet rs = null;
		Statement st = null;
		try {
			con=conexion.getConexion();  
			st = con.createStatement();// Creamos un objeto Statement
			 
			String ordenSQL = "SELECT * FROM SERVICIO ORDER By DESCRIPCION"; // sentencia a ejecutar
			rs = st.executeQuery(ordenSQL);	
			while (rs.next()) {
				Servicio miSerivicio = new Servicio();
				miSerivicio.setId(rs.getInt("ID"));
				miSerivicio.setDescripcion(rs.getString("DESCRIPCION"));
				miSerivicio.setCosto(rs.getInt("COSTO"));
				miSerivicio.setFactura_id (rs.getInt("FACTURA_ID"));
				miSerivicio.setEmpresario_id (rs.getInt("EMPRESARIO_ID"));
				
				listado.add(miSerivicio);
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
	
	
	public Servicio findById(short id)throws SQLException {
		Servicio a=null;
        Connection con=null;
        PreparedStatement st=null;
        ResultSet rs = null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            String ordenSQL = "SELECT * FROM SERVICIO"+
            				  " WHERE ID=?,?,?,?";
            st = con.prepareStatement(ordenSQL);
            st.setShort(1, id);
            rs=st.executeQuery();   
            if(rs.next()) {             
            	a=new Servicio();
            	a.setId(rs.getShort("ID"));
            	a.setDescripcion(rs.getString("DESCRIPCION"));
            	a.setCosto(rs.getInt("COSTO"));
            	a.setFactura_id(rs.getInt("FACTURA_ID"));
            	a.setEmpresario_id(rs.getInt("EMPRESARIO_ID"));
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
