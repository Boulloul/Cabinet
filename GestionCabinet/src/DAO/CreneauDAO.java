package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;


import net.proteanit.sql.DbUtils;

public class CreneauDAO implements IntCreneauDao {
	private Connection con ;
	private PreparedStatement prepared = null;
	public CreneauDAO() {
		con = Connexion.getInstance().ConnexionDb();
	}
	
	

	

	@Override
	public void DeleteCreneau(int id) {
		String sql="delete from creneaux  where ID='"+id+"'";
			try {
				prepared = con.prepareStatement(sql);
				prepared.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
	}

	@Override
	public ResultSet getCreneau() {
		String sql="select * from creneaux";
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
	public void AddCreneau(Models.CreneauModel c) {
		// TODO Auto-generated method stub

		String sql="insert into creneaux(ID,VERSION,HDEBUT,MDEBUT,HFIN,MFIN,ID_MEDECIN) values(?,?,?,?,?,?,?)";
		try {
			prepared = con.prepareStatement(sql);
			prepared.setInt(1, c.getID());
			prepared.setInt(2, c.getVersion());
     		prepared.setInt(3, c.getHdebut());
     		prepared.setInt(4,c.getMdebut());
     		prepared.setInt(5, c.getHfin());
     		prepared.setInt(6, c.getMfin());
     		prepared.setInt(7, c.getID_Medecin());
     		prepared.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void UpdateCreneau(Models.CreneauModel c) {
		// TODO Auto-generated method stub
		String sql="Update creneaux set VERSION=?,HDEBUT=?,MDEBUT=?,HFIN=?,MFIN=?,ID_MEDECIN=? where ID='"+c.getID()+"'";

		try {
			prepared = con.prepareStatement(sql);
			prepared.setInt(1, c.getVersion());
     		prepared.setInt(2, c.getHdebut());
     		prepared.setInt(3,c.getMdebut());
     		prepared.setInt(4, c.getHfin());
     		prepared.setInt(5, c.getMfin());
     		prepared.setInt(6, c.getID_Medecin());
		
			prepared.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	

}
