package entidades;

public class Servicio {
	
	private int id; 
	private String descripcion;
	private int costo; 
	private int factura_id ;
	private int empresario_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	public int getFactura_id() {
		return factura_id;
	}
	@Override
	public String toString() {
		return "Servicio [id=" + id + ", descripcion=" + descripcion + ", costo=" + costo + ", factura_id=" + factura_id
				+ ", empresario_id=" + empresario_id + "]";
	}
	public void setFactura_id(int factura_id) {
		this.factura_id = factura_id;
	}
	public int getEmpresario_id() {
		return empresario_id;
	}
	public void setEmpresario_id(int empresario_id) {
		this.empresario_id = empresario_id;
	}
	
	

	 
}
