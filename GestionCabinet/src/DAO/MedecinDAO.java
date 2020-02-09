package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;


import Models.MedecinModel;
import net.proteanit.sql.DbUtils;

public class MedecinDAO implements IntMedecinDao {
	private Connection con ;
	private PreparedStatement prepared = null;
	public MedecinDAO() {
		con = Connexion.getInstance().ConnexionDb();
	}
	
	@Override
	public void AddMedecin(MedecinModel C) {

		String sql="insert into medecins(ID,VERSION,TITRE,NOM,PRENOM) values(?,?,?,?,?)";
		try {
			prepared = con.prepareStatement(sql);
			prepared.setInt(1, C.getID());
			prepared.setInt(2, C.getVersion());
     		prepared.setString(3, C.getTitre());
     		prepared.setString(4,C.getNom());
     		prepared.setString(5, C.getPrenom());
     		prepared.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void UpdateMedecin(MedecinModel C) {
		String sql="Update medecins set VERSION=?,TITRE=?,NOM=?,PRENOM=? where ID='"+C.getID()+"'";

		try {
			prepared = con.prepareStatement(sql);
			prepared.setInt(1, C.getVersion());
			prepared.setString(2, C.getTitre());
			prepared.setString(3, C.getNom());
			prepared.setString(4, C.getPrenom());
		
			prepared.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void DeleteMedecin(int id) {
		String sql="delete from medecins  where ID='"+id+"'";
			try {
				prepared = con.prepareStatement(sql);
				prepared.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
	}

	@Override
	public ResultSet getMedecin() {
		String sql="select * from medecins";
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

}
