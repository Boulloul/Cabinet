package DAO;

import java.sql.ResultSet;

import Models.CreneauModel;

public interface IntCreneauDao {
	public void AddCreneau(CreneauModel c);
	public void UpdateCreneau(CreneauModel C);
	public void DeleteCreneau(int id);
	public ResultSet getCreneau();
}
