package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//import org.apache.tomcat.dbcp.dbcp2.ConnectionFactory;
import connection.ConnectionFactory;


public class DAO {
	protected String sql;
	protected PreparedStatement ps;
	protected ResultSet rs;
	protected Connection connection;
	
	public DAO() {
		this.connection = ConnectionFactory.getInstance().getConnection();
	}
}
