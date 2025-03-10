package ChipKaartBestandOOP1;

public class OVKaart extends ChipKaartOpdracht
{
    double Saldo = 0;
    String Naam;
    
    public void SaldoInfo(double x)
    {
	Saldo += x;
	System.out.println(Saldo);
    }
    public void NaamInfo(String x)
    {
	Naam = x;
	System.out.println(Naam);
    }
}
