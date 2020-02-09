package DAO;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
	private  Connection con;
	private static Connexion Instance=null;
	private Connexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet","root","Mehdi0601010046");
			System.out.println("Connexion Created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Connection ConnexionDb(){
			return con;
	}
	
	public static Connexion getInstance () {
		if(Instance==null) {
			System.out.println("Null");
			Instance=  new Connexion();
			return Instance;
		}
		return Instance;
	}
}
