package test;

import java.sql.SQLException;

import dao.DaoEmpresario;
import dao.DaoServicio;

import entidades.Servicio;

public class TestDaoEmpresario {

	public static void main(String[] args) {
		DaoEmpresario daoEmpresario=new DaoEmpresario();
		try {
			System.out.println(" Prueba de inserción");
			Servicio a=new Servicio();
			a.setNombre("Sara");
			try {
				daoEmpresario.inserta(a);
				System.out.println(a);
			} 
			catch (SQLException e) {System.out.println(e.toString());}
			catch (Exception e) {e.printStackTrace();}
			System.out.println(" Prueba find ");
			a=daoEmpresario.findById((short)1);
			if(a!=null)
				System.out.println(a.toString());
			else
				System.out.println("*** No existe un Servicio con ese código ****");
			System.out.println(" Prueba listado ");
			daoEmpresario.listado().forEach(System.out::println);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
