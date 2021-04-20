package clases;

import controlador.Controlador;

public class HiloInsertar extends Thread{
	
	private int inicio;
	private int fin;
	
	public HiloInsertar(int inicio, int fin){
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public void run() {
		
		for (int i = inicio; i<=fin;i++) {
			System.out.println("i vale:"+i);
			Controlador.insertarEnBD(Controlador.getPacienteSync(i));
		}
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}
}

