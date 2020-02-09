
public class EntNat {
	private int n;
	public EntNat (int n) throws ErrConst
	{
	if (n<0) throw new ErrConst(n) ;
	this.n = n;
	}
	public int getN() {
		return n;
	}
	}

class ErrConst extends Exception
{
	private int valeur;
	public ErrConst (int valeur) {
		this.valeur = valeur ;
		}
	public int getValeur() {
		return valeur;
		}
}