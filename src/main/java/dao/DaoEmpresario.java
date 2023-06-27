package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import conexiones.Conexion;
import entidades.Empresario;
import entidades.Servicio;

public class DaoEmpresario {
	
	
	public DaoEmpresario() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return Devuelve un ArrayList de objetos autor con los autores 
	 *         hay actualmente en la tabla AUTOR de nuestra base de datos.
	 * @throws SQLException: Cualquier error en el acceso o en la ejecución
	 */
	
	
	public void inserta(Empresario d) throws SQLException, Exception {
        Connection con=null;
        PreparedStatement st=null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            con.setAutoCommit(false);
            // Las secuencias siguen el formato seq_autor_id
            String ordenSQL = "INSERT INTO EMPRESARIO VALUES(SEQ_EMPRESARIO_ID.NEXTVAL,?,?,?)";
            String generatedColumns[] = {"ID"};
            st = con.prepareStatement(ordenSQL,generatedColumns);
            st.setString(1, d.getNombre());
            //st.setLong(2, d.getTelefono());
            st.setString(2, Long.toString(d.getTelefono()));
            st.setString(3, d.getCorreo());
            st.executeUpdate();
            ResultSet rsgk = st.getGeneratedKeys();
            rsgk.next();
            d.setId(rsgk.getInt(1)); 
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
	
	

	public ArrayList<Empresario> listado() throws SQLException,Exception{
		
		ArrayList<Empresario> listado=new ArrayList<>();
		Conexion conexion=new Conexion();  
		Connection con=null;
		ResultSet rs = null;
		Statement st = null;
		try {
			con=conexion.getConexion();  
			st = con.createStatement();// Creamos un objeto Statement
			 
			String ordenSQL = "SELECT * FROM EMPRESARIO ORDER By NOMBRE"; // sentencia a ejecutar
			rs = st.executeQuery(ordenSQL);	
			while (rs.next()) {
				Empresario miEmpresario = new Empresario();
				miEmpresario.setId(rs.getInt("ID"));
				miEmpresario.setNombre(rs.getString("NOMBRE"));
				miEmpresario.setTelefono(rs.getLong("TELEFONO"));
				miEmpresario.setCorreo(rs.getString("CORREO"));
				
				listado.add(miEmpresario);
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
	
	public Empresario findById(short id)throws SQLException {
		Empresario a=null;
        Connection con=null;
        PreparedStatement st=null;
        ResultSet rs = null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            String ordenSQL = "SELECT * FROM EMPRESARIO"+
            				  " WHERE ID=?,?,?,?";
            st = con.prepareStatement(ordenSQL);
            st.setShort(1, id);
            rs=st.executeQuery();   
            if(rs.next()) {             
            	a=new Empresario();
            	a.setId(rs.getShort("ID"));
            	a.setNombre(rs.getString("NOMBRE"));
            	a.setTelefono(rs.getLong("TELEFONO"));
            	a.setCorreo(rs.getString("CORREO"));
            	
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