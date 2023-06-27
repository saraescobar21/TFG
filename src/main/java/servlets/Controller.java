package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.el.parser.ParseException;

import conexiones.Conexion;
import dao.DaoEmpresario;
import dao.DaoFactura;
import dao.DaoServicio;
import entidades.Empresario;
import entidades.Factura;
import entidades.Servicio;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

 // CONTROL DE ERRORES
 	protected void procesarError(HttpServletRequest request, HttpServletResponse response, Exception e)
 			throws ServletException, IOException {
 		String mensajeError = e.getMessage();
 		request.setAttribute("error", mensajeError);
 		request.getRequestDispatcher("/error.jsp").forward(request, response);

 	}

 	protected void procesarErrorSQL(HttpServletRequest request, HttpServletResponse response, SQLException e)
 			throws ServletException, IOException {
 		int codigoError = e.getErrorCode();
 		String mensajeError;
 		switch (codigoError) {
 		// >>>> Tenemos que ver los códigos de error y lo que vamos a hacer
 		default:
 			mensajeError = e.getMessage();
 		}
     	request.setAttribute("error", mensajeError);
 		request.getRequestDispatcher("/error.jsp").forward(request,response);
 	}    
 	
 	protected void doGet(HttpServletRequest request, HttpServletResponse response)
 			throws ServletException, IOException {
 		String operacion = request.getParameter("operacion");
 		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
 		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
 		response.setDateHeader("Expires", 0); // Proxies.
 		DaoEmpresario daoempresario;
 		DaoFactura daofactura;
 		DaoServicio daoservicio;
 		switch (operacion) {
 		
 		
 		case "insertaempresario":
 			Empresario empresario = new Empresario();
 			String nombreEmpresario=request.getParameter("nombre");
 			empresario.setNombre(nombreEmpresario);
 			String telefonoParam = request.getParameter("telefono");
			long telefono = Long.parseLong(telefonoParam);
			empresario.setTelefono(telefono);
 			
 			String correo = request.getParameter("correo");
 			empresario.setCorreo(correo);
 			
 					
 			daoempresario = new DaoEmpresario();      
 			
 			try {
 				daoempresario.inserta(empresario);
 			} 
 			catch (SQLException e) {procesarErrorSQL(request, response, e);} 
 			catch (Exception e) {	procesarError(request, response, e);}
 			request.setAttribute("confirmaroperacion", "Empresario creado satisfactoriamente");
 			request.getRequestDispatcher("/altaempresario.jsp").forward(request, response);
 			break;
 			
 		case "insertafactura":
 			Factura factura = new Factura();
 			String clienteFactura=request.getParameter("cliente");
 			factura.setCliente(clienteFactura);
 			String fecha=request.getParameter("fecha"); 
 			System.out.println(fecha);
 			String importeFactura = request.getParameter("importe");
 			int importeEntero = Integer.parseInt(importeFactura);
 			factura.setImporte(importeEntero);
 			
 			
 			//formatear la fecha de tipo date a String
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date parsed;
			try {
				parsed = sdf.parse(fecha);
				Date sql = new java.sql.Date(parsed.getTime());
				factura.setFecha(sql);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
 			
	
 			daofactura = new DaoFactura();      
 			
 			try {
 				daofactura.inserta(factura);
 			} 
 			catch (SQLException e) {procesarErrorSQL(request, response, e);} 
 			catch (Exception e) {	procesarError(request, response, e);}
 			request.setAttribute("confirmaroperacion", "Factura creada satisfactoriamente");
 			request.getRequestDispatcher("/altafactura.jsp").forward(request, response);
 			break;
 			

 			
 			
 		case "insertaservicio":
			int factura_id = toInteger(request.getParameter("factura_id"));
			int empresario_id =  toInteger(request.getParameter("empresario_id"));
			try {
				if (factura_id==0  && empresario_id==0) {
					daofactura=new DaoFactura();
					ArrayList<Factura> listadofacturas = daofactura.listado();
					request.setAttribute("listadofacturas", listadofacturas);
					daoempresario=new DaoEmpresario();
					ArrayList<Empresario> listadoempresarios = daoempresario.listado();
					request.setAttribute("listadoempresarios", listadoempresarios);
				}	
				else {
					
					Servicio servicio = new Servicio();
							
					String descripcion=request.getParameter("Descripcion");
					String costo = request.getParameter("costo");

					servicio.setDescripcion(descripcion);
					int costoEntero = Integer.parseInt(costo);
					servicio.setFactura_id(factura_id);
					servicio.setEmpresario_id(empresario_id);
				

					daoservicio = new DaoServicio();
					daoservicio.inserta(servicio);
					request.setAttribute("confirmaroperacion", "Servicio creado satisfactoriamente");
				} 
				request.getRequestDispatcher("/altaservicio.jsp").forward(request, response);
			} catch (SQLException e) {procesarErrorSQL(request, response, e);} 
			catch (Exception e) {	procesarError(request, response, e);}
			break;
 			
 		case "listarEmpresarios":
			daoempresario=new DaoEmpresario();
			try {
				ArrayList<Empresario> listadoempresarios = daoempresario.listado();
				request.setAttribute("listadoempresarios", listadoempresarios);
				request.getRequestDispatcher("listadoempresarios.jsp").forward(request, response);
			} catch (SQLException e) {procesarErrorSQL(request, response, e);} 
			  catch (Exception e) {	procesarError(request, response, e);}
			break;
			
 		case "listarServicios":
			daoservicio=new DaoServicio();
			try {
				ArrayList<Servicio> listadoservicios = daoservicio.listado();
				request.setAttribute("listadoservicios", listadoservicios);
				request.getRequestDispatcher("listadoservicios.jsp").forward(request, response);
			} catch (SQLException e) {procesarErrorSQL(request, response, e);} 
			  catch (Exception e) {	procesarError(request, response, e);}
			break;
			
 		case "listarFacturas":
			daofactura=new DaoFactura();
			try {
				ArrayList<Factura> listadofacturas = daofactura.listado();
				request.setAttribute("listadofacturas", listadofacturas);
				request.getRequestDispatcher("listadofacturas.jsp").forward(request, response);
			} catch (SQLException e) {procesarErrorSQL(request, response, e);} 
			  catch (Exception e) {	procesarError(request, response, e);}
			break;
 			
 			
 			
 		case "logout":
			HttpSession sesion = request.getSession();
			sesion.invalidate();
			response.sendRedirect("index.jsp");
			break;
		
		case "pruebaconexion":
			Conexion conexion=new Conexion();
			Connection con=null;
			try {
				 con=conexion.getConexion();
				 if (con!=null) {
					 System.out.println("He conectado");
					 con.close();
				 }
			}
			catch (SQLException e) {procesarErrorSQL(request, response, e);} 
			catch (Exception e) {	procesarError(request, response, e);}
			request.setAttribute("error", "Me he conectado correctamente");
			request.getRequestDispatcher("error.jsp").forward(request,response);
			break;
		default:
			request.setAttribute("error", "No existe esa opción en controller");
			request.getRequestDispatcher("error.jsp").forward(request,response);
			break;
		}
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// Recibe un String o null y parsea a Short, pone 0 si se pasa null
		private int toInteger(String s) {
			if (s==null) return 0;
			return Integer.parseInt(s);
		}

}
