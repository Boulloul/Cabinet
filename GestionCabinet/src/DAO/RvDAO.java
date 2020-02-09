package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

import Models.RvModel;
import net.proteanit.sql.DbUtils;

public class RvDAO implements IntRvDao {
	private Connection con ;
	private PreparedStatement prepared = null;
	public RvDAO() {
		con = Connexion.getInstance().ConnexionDb();
	}
	
	

	

	@Override
	public void DeleteRv(int id) {
		String sql="delete from rv  where ID='"+id+"'";
			try {
				prepared = con.prepareStatement(sql);
				prepared.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
	}

	@Override
	public ResultSet getRv() {
		String sql="select * from rv";
		ResultSet rs = null;
		try {
			prepared=con.prepareStatement(sql);
			rs =  prepared.executeQuery();
			
		}
		catch(Exception ex) {
				ex.printStackTrace();
		}
		
		
		return rs;
	}


	
	@Override
	public void AddRv(RvModel c) {
		// TODO Auto-generated method stub
		

		String sql="insert into rv(ID,JOUR,ID_CLIENT,ID_CRENEAU) values(?,?,?,?)";
		try {
			prepared = con.prepareStatement(sql);
			prepared.setInt(1, c.getID());
			prepared.setDate(2,java.sql.Date.valueOf(c.getJour().toLocalDate()));
     		prepared.setInt(3, c.getID_Client());
     		prepared.setInt(4,c.getID_Creneau());
     		
     		prepared.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void UpdateRv(RvModel c) {
		// TODO Auto-generated method stub
		String sql="Update rv set JOUR=?,ID_CLIENT=?,ID_CRENEAU=? where ID='"+c.getID()+"'";

		try {
			prepared = con.prepareStatement(sql);
			prepared.setDate(1, (Date) c.getJour());
     		prepared.setInt(2, c.getID_Client());
     		prepared.setInt(3,c.getID_Creneau());
     		
		
			prepared.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	

}
