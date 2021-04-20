package clases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import clasesVO.PersonaVO;

public class Enfermero extends PersonaVO implements Vacunable {

	public Enfermero(int id, int id_ciudad, String nombre, String tipo, int infectado) {
		super(id, id_ciudad, nombre, tipo, infectado);
	}

	@Override //primera pasada
	public void vacunar(Collection<Paciente> listaPacientes) {

		for (Paciente paciente : listaPacientes) {

			if(generarNumeroRandom(2)) {	//vacuna a 2 de cada 5
				paciente.setInfectado(0);
			}
		}
	}
	
	@Override //segunda pasada
	public void vacunar(Paciente infectado) {
		
		if(generarNumeroRandom(1)) {	//vacuna a 1 de cada 5
			infectado.setInfectado(0);
		}
	}

	static boolean generarNumeroRandom(int valorMaximo) {
		
		Random random = new Random();
	    int aleatorio = random.nextInt(5) + 1;
	    
	    if(aleatorio<=valorMaximo) {
	    	return true;
	    }else {
	    	return false;
	    }
	}
	
	/*
	 * 
	 * @Override public void vacunar(Infectable infectado) {
	 * 
	 * }
	 * 
	 * @Override public void porras(Collection<? extends Infectable> porras) { //
	 * TODO Auto-generated method stub
	 * 
	 * }
	 */
}
