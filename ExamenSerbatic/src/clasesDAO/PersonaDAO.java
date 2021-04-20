package clasesDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import bbdd.PoolConexiones;
import clasesVO.PersonaVO;
import controlador.Controlador;

public class PersonaDAO {

	private static Connection conexion;
	private static PreparedStatement ps = null;
	private static DataSource dataSource;

	
	private static Logger logger = LogManager.getLogger(PersonaDAO.class);
	
	public static List<PersonaVO> consultarPersonasCiudad(int idCiudad) {

		List<PersonaVO> listaPersonasVO = new ArrayList<PersonaVO>();
		try {
			dataSource = PoolConexiones.setUpPool(-1); // setUpPool solo se ejecuta 1 vez

			// comprueba si hay conexiones disponibles
			// if (poolConexiones.getConnectionPool().getMaxActive() <= 5) {
			conexion = dataSource.getConnection();
			// }

			String query = "SELECT * FROM personas WHERE id_ciudad= ?";
			ps = conexion.prepareStatement(query);
			ps.setInt(1, idCiudad);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				listaPersonasVO.add(new PersonaVO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			}

			rs.close();

		} catch (Exception e) {
			
			logger.error("Error al consultar las personas de la ciudad");
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (Exception sqlException) {
				logger.error("Error al cerrar la conexion con la BD");
				sqlException.printStackTrace();
			}
		}

		return listaPersonasVO;
	}

	public static boolean insertar(PersonaVO personaVO) {

		boolean insertado = false;
		try {

			conexion = dataSource.getConnection();

			ps = conexion.prepareStatement("INSERT INTO personas_informe (id, id_ciudad, Nombre, Tipo, Infectado) "
					+ "VALUES (?, ?, ?, ?, ?)");

			ps.setInt(1, personaVO.getId());
			ps.setInt(2, personaVO.getId_ciudad());
			ps.setString(3, personaVO.getNombre());
			ps.setString(4, personaVO.getTipo());
			ps.setInt(5, personaVO.getInfectado());

			int resultado = ps.executeUpdate();

			if (resultado == 0) {
				System.out.println("No se ha podido insertar");
				insertado = false;
			} else {
				insertado = true;
			}
			ps.close();

		} catch (Exception sqlException) {
			logger.error("Error al insertar personas en la tabla personas de la BD");
			sqlException.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (Exception sqlException) {
				logger.error("Error al cerrar la conexion con la BD");
				sqlException.printStackTrace();
			}
		}

		return insertado;
	}

	public static int funcionNumInfectados() {

		return 1;
	}

}
