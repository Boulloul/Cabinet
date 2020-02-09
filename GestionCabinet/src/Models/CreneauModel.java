package Models;

public class CreneauModel {
	private int ID;
	private int Version;
	private int Hdebut;
	private int Mdebut;
	private int Hfin;
	private int Mfin;
	private int ID_Medecin;

	//CTOR
    public CreneauModel() {
		
	}
	
	public CreneauModel(int iD, int version, int hdebut, int mdebut, int hfin,int mfin,int ID_med) {
		ID = iD;
		Version = version;
		Hdebut = hdebut;
		Mdebut = mdebut;
		Hfin = hfin;
		Mfin=mfin;
		ID_Medecin=ID_med;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getVersion() {
		return Version;
	}

	public void setVersion(int version) {
		Version = version;
	}

	public int getHdebut() {
		return Hdebut;
	}

	public void setHdebut(int hdebut) {
		Hdebut = hdebut;
	}

	public int getMdebut() {
		return Mdebut;
	}

	public void setMdebut(int mdebut) {
		Mdebut = mdebut;
	}

	public int getHfin() {
		return Hfin;
	}

	public void setHfin(int hfin) {
		Hfin = hfin;
	}

	public int getMfin() {
		return Mfin;
	}

	public void setMfin(int mfin) {
		Mfin = mfin;
	}

	public int getID_Medecin() {
		return ID_Medecin;
	}

	public void setID_Medecin(int iD_Medecin) {
		ID_Medecin = iD_Medecin;
	}
}
