import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionPostgre {

	//
	private static String URL = "jdbc:postgresql://localhost:5432/afi";

	private static String USERNAME = "...";
	
	private static String PASSWORD = "...";
	
	private static  Connection con;

	/**
	 * vraca konekciju koju treba zatvoriti u finally blocku
	 * kada se zavrsi rad sa istom
	 * 
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws Exception{
		
		if(con == null){
			Class.forName("org.postgresql.Driver").newInstance();
			System.out.println(URL);
		  con =  DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		return con;
	}
	
	
}