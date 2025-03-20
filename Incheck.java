package ChipKaartBestandOOP1;
import java.text.DecimalFormat;


public class Incheck
{
    private static final DecimalFormat df = new DecimalFormat("0.00");
    double BasisTarief = 1.01;
    double KostPerKm = 0.175;
    public void Inchecken(double x, double y)
    {
	OVChipKaart OVChipKaartActie = new OVChipKaart();
	if (OVChipKaartActie.Geldig == true)
	{
	    KostPerKm *= y;
	    double VolBedrag = BasisTarief + KostPerKm;
	    if (x >= BasisTarief)
	    {
		System.out.println("U saldo: €" + x);
		System.out.println("U bent successvol ingechecked!");
		OVChipKaartActie.Bedrag -= VolBedrag;
		OVChipKaartActie.IsIngechekt = true;

	    }
	    else
	    {
		System.out.println("U saldo: €" + x);
		System.out.println("U heeft niet genoeg saldo!");
		OVChipKaartActie.IsIngechekt = false;
		System.out.println("Het Kost: €"+ df.format(VolBedrag)); 
	    }
	}
	else
	{
	    System.out.println("U saldo: €" + x);
	    System.out.println("OV chipkaart is niet geldig!");
	}
    }
}
