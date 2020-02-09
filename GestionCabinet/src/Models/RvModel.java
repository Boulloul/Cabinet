package Models;

import java.sql.Date;

public class RvModel {
	private int ID;
	private Date Jour;
	private int ID_Client;
	private int ID_Creneau;
	
	//CTOR
	public RvModel() {}
	
	public RvModel(int iD, Date jour, int ID_client, int ID_creneau) {
		ID = iD;
		Jour = jour;
		ID_Client = ID_client;
		ID_Creneau = ID_creneau;
		
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Date getJour() {
		return Jour;
	}

	public void setJour(Date jour) {
		Jour = jour;
	}

	public int getID_Client() {
		return ID_Client;
	}

	public void setID_Client(int iD_Client) {
		ID_Client = iD_Client;
	}

	public int getID_Creneau() {
		return ID_Creneau;
	}

	public void setID_Creneau(int iD_Creneau) {
		ID_Creneau = iD_Creneau;
	}
}
