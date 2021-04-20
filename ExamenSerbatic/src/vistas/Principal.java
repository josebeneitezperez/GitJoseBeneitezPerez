package vistas;

import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import clases.HiloInsertar;
import clases.Paciente;
import controlador.Controlador;

public class Principal {

	public static String ruta = "C:\\Users\\Formacion\\workspace Jose\\ExamenSerbatic\\src\\resources\\log4j.properties";
	private static Logger logger = LogManager.getLogger(Principal.class);
	
	public static void main (String[]args) {
		
		//logger
		PropertyConfigurator.configure(ruta);
		logger.info("Aplicación iniciada");
		
		
		System.out.println("Controlador.getListaPersonasCiudad().size() vale: ");
		int idCiudadPrueba=7;
		Controlador.getListaPersonasCiudad(idCiudadPrueba);
		Controlador.dividirListaPersonas();
		
		Controlador.simularDia();
		
		Controlador.enfermeroPrimeraPasada();
		
		Paciente pacienteAleatorio = elegirPacienteAleatorio();
		Controlador.enfermeroSegundaPasada(pacienteAleatorio);
		
		//array con las posiciones de inicio y fin que insertarán cada uno de los 4 hilos
		int [][] inicioFin = segmentarHilos();
		HiloInsertar hilo1 = new HiloInsertar(inicioFin[0][0], inicioFin[0][1]);
		HiloInsertar hilo2 = new HiloInsertar(inicioFin[1][0], inicioFin[1][1]);
		HiloInsertar hilo3 = new HiloInsertar(inicioFin[2][0], inicioFin[2][1]);
		HiloInsertar hilo4 = new HiloInsertar(inicioFin[3][0], inicioFin[3][1]);
		hilo1.start();
		hilo2.start();
		//hilo3.start();
		//hilo4.start();

		//Espera a que se inserten todas las personas
		boolean enCurso = true;
		while(enCurso) {
			if(!hilo1.isAlive() && !hilo2.isAlive() && !hilo3.isAlive() && !hilo4.isAlive()) {
				enCurso = false;
			}
		}
		int numInfectador= Controlador.funcionNumInfectados();
		System.out.println("El número de infectados que hay es: "+numInfectador);
		
		logger.info("Fin del programa");
	}
	
	static Paciente elegirPacienteAleatorio() {
		
		int max = Controlador.listaPacientes.size();
		
		Random random = new Random();
	    int aleatorio = random.nextInt(max); //tenemos en cuenta la posición 0 por lo que no hacemos "+ 1"
		return Controlador.listaPacientes.get(aleatorio);
	}
	
	static int[][] segmentarHilos(){
		
		int[][] arrayInicioFin = new int[4][2];
		
		if(Controlador.listaPacientes.size()%4==0) { //si es divisible entre 4 todos los hilos tendrán las mismas posiciones
			
			int segmento = (Controlador.listaPacientes.size())/4;
			System.out.println("listaPacientes.size: "+Controlador.listaPacientes.size());
			System.out.println("segmeno vale: "+segmento);
			
			arrayInicioFin[0][0]= 0;
			arrayInicioFin[0][1]= segmento-1;
			
			arrayInicioFin[1][0]= segmento;
			arrayInicioFin[1][1]= (segmento*2)-1;
			
			arrayInicioFin[2][0]= segmento*2;
			arrayInicioFin[2][1]= (segmento*3)-1;
			
			arrayInicioFin[3][0]= segmento*3;
			arrayInicioFin[3][1]= (segmento*4)-1;
			
		}else {	//si no es divisible entre 4 entonces el último hilo tendrá más posiciones
			
			int segmento = (int) Math.floor(Controlador.listaPacientes.size()/4);	//redondea hacia abajo (5.99 será 5)
			arrayInicioFin[0][0]= 0;
			arrayInicioFin[0][1]= segmento-1;
			
			arrayInicioFin[1][0]= segmento;
			arrayInicioFin[1][1]= (segmento*2)-1;
			
			arrayInicioFin[2][0]= segmento*2;
			arrayInicioFin[2][1]= (segmento*3)-1;
			
			arrayInicioFin[3][0]= segmento*3;
			
			int posicionesExtras =Controlador.listaPacientes.size()-(segmento*4);
			arrayInicioFin[3][1]= posicionesExtras+(segmento*4)-1;
		}
		
		return arrayInicioFin;
	}
}








