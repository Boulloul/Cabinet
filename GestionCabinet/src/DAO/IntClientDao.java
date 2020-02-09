package DAO;

import java.sql.ResultSet;
import Models.ClientModel;

public interface IntClientDao {
	public void AddClient(ClientModel c);
	public void UpdateClient(ClientModel C);
	public void DeleteClient(int id);
	public ResultSet getClients();
}
