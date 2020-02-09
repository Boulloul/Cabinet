package DAO;

import java.sql.ResultSet;

import Models.RvModel;

public interface IntRvDao {
	public void AddRv(RvModel c);
	public void UpdateRv(RvModel C);
	public void DeleteRv(int id);
	public ResultSet getRv();
}
