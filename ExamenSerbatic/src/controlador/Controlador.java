package controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import clases.Enfermero;
import clases.Paciente;
import clasesDAO.PersonaDAO;
import clasesVO.PersonaVO;

public class Controlador {

	public static List<PersonaVO> listaPersonasVO = null;

	public static Enfermero enfermero = null;

	public static List<Paciente> listaPacientes = new ArrayList<Paciente>();
	
	public static synchronized Paciente getPacienteSync(int indexPaciente) {
		
		return listaPacientes.get(indexPaciente);
	}
	
	public static void getListaPersonasCiudad(int idCiudad) {

		listaPersonasVO = PersonaDAO.consultarPersonasCiudad(idCiudad);
	}

	public static void insertarEnBD(PersonaVO personaVO) {
		listaPacientes = Collections.synchronizedList(listaPacientes);
		PersonaDAO.insertar(personaVO);
	}

	public static void dividirListaPersonas() {

		for (PersonaVO unaPersona : listaPersonasVO) {

			// guardamos el último enfermero de listaPersonas
			if (unaPersona.getTipo().equalsIgnoreCase("E")) {
				enfermero = new Enfermero(unaPersona.getId(), unaPersona.getId_ciudad(), unaPersona.getNombre(),
						unaPersona.getTipo(), unaPersona.getInfectado());
			} else {
				listaPacientes.add(new Paciente(unaPersona.getId(), unaPersona.getId_ciudad(), unaPersona.getNombre(),
						unaPersona.getTipo(), unaPersona.getInfectado()));
			}
		}
	}

	public static void simularDia() {

		for (Paciente paciente : listaPacientes) {

			paciente.visitarSuper();

			if (paciente.getInfectado() != 1) {
				paciente.visitarTrabajo();
			}
			if (paciente.getInfectado() != 1) {
				paciente.cogerTransporte();
			}
		}
	}

	public static void enfermeroPrimeraPasada() {

		enfermero.vacunar(listaPacientes);
		listaPacientes = Collections.synchronizedList(listaPacientes);
	}

	public static void enfermeroSegundaPasada(Paciente unPaciente) {
		enfermero.vacunar(unPaciente);
		listaPacientes = Collections.synchronizedList(listaPacientes);
	}

	public static int funcionNumInfectados() {

		return PersonaDAO.funcionNumInfectados();
	}

}
