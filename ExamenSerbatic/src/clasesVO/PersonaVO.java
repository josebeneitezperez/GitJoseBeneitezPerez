package clasesVO;

import clases.Paciente;

public class PersonaVO {

	private int id;
	private int id_ciudad;
	private String nombre;
	private String tipo;
	private int infectado;
	
	public PersonaVO() {
		super();
	}

	public PersonaVO(int id, int id_ciudad, String nombre, String tipo, int infectado) {
		super();
		this.id = id;
		this.id_ciudad = id_ciudad;
		this.nombre = nombre;
		this.tipo = tipo;
		this.infectado = infectado;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_ciudad() {
		return id_ciudad;
	}
	public void setId_ciudad(int id_ciudad) {
		this.id_ciudad = id_ciudad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getInfectado() {
		return infectado;
	}
	public void setInfectado(int infectado) {
		this.infectado = infectado;
	}
}
