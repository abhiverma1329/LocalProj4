package in.co.rays.proj4.util;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.omg.CORBA.portable.ApplicationException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC DataSource is a Data Connection Pool
 *
 * @author Abhishek Verma
 *
 */
public final class JDBCDataSource {

	/**
     * JDBC Database connection pool ( DCP )
     */
	private static JDBCDataSource datasource;

	private JDBCDataSource() {
	}

	private ComboPooledDataSource cpds = null;

	 /**
     * Create instance of Connection Pool
     *
     * @return
     */
	public static JDBCDataSource getInstance() {
		System.out.println("jdbc get instance");
		if (datasource == null) {
			System.out.println("jdbc condition");
			ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.proj4.bundle.system");
			System.out.println("resource bundle");

			datasource = new JDBCDataSource();
			datasource.cpds = new ComboPooledDataSource();

			try {
				datasource.cpds.setDriverClass(rb.getString("driver"));
				System.out.println("driver");
			} catch (Exception e) {
				e.printStackTrace();
			}
			datasource.cpds.setJdbcUrl(rb.getString("url"));
			System.out.println("url");
			datasource.cpds.setUser(rb.getString("username"));
			System.out.println("username");
			datasource.cpds.setPassword(rb.getString("password"));
			System.out.println("password");
			datasource.cpds.setInitialPoolSize(new Integer((String) rb.getString("initialPoolSize")));
			datasource.cpds.setAcquireIncrement(new Integer((String) rb.getString("acquireIncrement")));
			datasource.cpds.setMaxPoolSize(new Integer((String) rb.getString("maxPoolSize")));
			datasource.cpds.setMaxIdleTime(DataUtility.getInt(rb.getString("timeout")));
			datasource.cpds.setMinPoolSize(new Integer((String) rb.getString("minPoolSize")));
			System.out.println("connections");
		}
		return datasource;
	}

	 /**
     * Gets the connection from ComboPooledDataSource
     *
     * @return connection
     */
	public static Connection getConnection() throws Exception {
		System.out.println("jdbc get connection");
		return getInstance().cpds.getConnection();

	}

	 /**
     * Closes a connection
     *
     * @param connection
     * @throws Exception
     */
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public static void trnRollback(Connection connection) throws ApplicationException {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
				// throw new ApplicationException(ex.toString());
			}
		}
	}
}
