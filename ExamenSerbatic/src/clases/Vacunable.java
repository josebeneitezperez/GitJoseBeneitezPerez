package clases;

import java.util.Collection; 

import clasesVO.PersonaVO;

public interface Vacunable {

	//void vacunar(Infectable paciente);					//Bueno 1
	//void vacunar(Collection<Infectable> listaPacientes);	//Bueno 2
	
	
	void vacunar(Paciente paciente);						//mal, en uso
	void vacunar(Collection<Paciente> listaPacientes);		//mal, en uso
}
