package DAO;

import java.sql.ResultSet;

import Models.MedecinModel;

public interface IntMedecinDao {
	public void AddMedecin(MedecinModel c);
	public void UpdateMedecin(MedecinModel C);
	public void DeleteMedecin(int id);
	public ResultSet getMedecin();
}
