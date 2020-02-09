
public class Test {

	public static void main(String[] args) {
		{
			try
			{
			EntNat n1 = new EntNat (1);
			System.out.println("n1 =" + n1.getN( )) ;
			EntNat n2 = new EntNat (-1);
			System.out.println("n2 = "+ n2.getN( )) ;
			}
			catch (ErrConst e)
			{
			System.out.println("erreur !");
			System.exit (-1) ;
			}
			}

	}

}
