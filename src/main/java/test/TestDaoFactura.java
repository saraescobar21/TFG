package test;

import java.sql.SQLException;


import dao.DaoServicio;
import entidades.Factura;
import entidades.Servicio;

public class TestDaoFactura {

	public static void main(String[] args) {
		DaoServicio daoServicio=new DaoServicio();
		try {
			System.out.println(" Prueba de inserción");
			Factura a=new Factura();
			a.setCliente("Jesus");
			try {
				daoFactura.inserta(a);
				System.out.println(a);
			} 
			catch (SQLException e) {System.out.println(e.toString());}
			catch (Exception e) {e.printStackTrace();}
			System.out.println(" Prueba find ");
			a=daoFactura.findById((short)1);
			if(a!=null)
				System.out.println(a.toString());
			else
				System.out.println("*** No existe un Servicio con ese código ****");
			System.out.println(" Prueba listado ");
			daoFactura.listado().forEach(System.out::println);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
