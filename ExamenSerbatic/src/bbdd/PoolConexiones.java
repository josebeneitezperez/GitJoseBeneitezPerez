package bbdd;

import java.sql.Connection;   
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

public class PoolConexiones {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306/curso";
    static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306/prueba_serbatic";

    static final String JDBC_USER = "root";
    static final String JDBC_PASS = "";

    private static GenericObjectPool gPool = null;	//pool generica, en este caso la instanciamos con Conexiones

    //@SuppressWarnings("unused")
    public static DataSource setUpPool(int maxConexiones) throws Exception {
        Class.forName(JDBC_DRIVER);

        // Creates an Instance of GenericObjectPool That Holds Our Pool of Connections Object!
        gPool = new GenericObjectPool();
        
        if(maxConexiones>0) {
        	gPool.setMaxActive(maxConexiones);	//maximas conexiones al mismo tiempo
        }
        // Creates a ConnectionFactory Object Which Will Be Use by the Pool to Create the Connection Object!
        ConnectionFactory cf = new DriverManagerConnectionFactory(JDBC_DB_URL, JDBC_USER, JDBC_PASS);

        // Creates a PoolableConnectionFactory That Will Wraps the Connection Object Created by the ConnectionFactory to Add Object Pooling Functionality!
        PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);
        
        System.out.println("Pool recien creado, status:");
        printDbStatus();
        
        return new PoolingDataSource(gPool);
    }

    public static GenericObjectPool getConnectionPool() throws Exception {

    	if(gPool==null) {
    		setUpPool(-1);
    	}
    	
        return gPool;
    }

	public static void printDbStatus() throws Exception { //Connection Pool Status
        System.out.println("Max.: " + getConnectionPool().getMaxActive() + "; Active: " + getConnectionPool().getNumActive() + "; Idle: " + getConnectionPool().getNumIdle());

    }
}
