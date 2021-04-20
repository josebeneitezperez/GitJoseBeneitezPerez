package clases;

import java.util.Random;

import clasesVO.PersonaVO;

public class Paciente extends PersonaVO implements Infectable, Moveable{

	static final int PORCENTAJE_SUPER = 10;
	static final int PORCENTAJE_TRABAJO = 12;
	static final int PORCENTAJE_TRANSPORTE = 16;
	public Paciente(int id, int id_ciudad, String nombre, String tipo, int infectado) {
		super(id, id_ciudad, nombre, tipo, infectado);
	}

	@Override
	public void visitarSuper() {
		
		if(generarNumeroRandom(PORCENTAJE_SUPER)) {
			setInfectado(1);
		}
	}

	@Override
	public void visitarTrabajo() {
		
		if(generarNumeroRandom(PORCENTAJE_TRABAJO)) {
			setInfectado(1);
		}
	}

	@Override
	public void cogerTransporte() {
		
		if(generarNumeroRandom(PORCENTAJE_TRANSPORTE)) {
			setInfectado(1);
		}
	}
	
	static boolean generarNumeroRandom(int valorMaximo) {
		
		Random random = new Random();
	    int aleatorio = random.nextInt(100) + 1;
	    
	    if(aleatorio<=valorMaximo) {
	    	return true;
	    }else {
	    	return false;
	    }
	}
	

	@Override
	public void infectar() {
		
		setInfectado(1);
	}
	
}
