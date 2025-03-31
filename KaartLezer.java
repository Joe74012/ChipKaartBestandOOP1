package ChipKaartBestandOOP1;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.text.DecimalFormat;

public class KaartLezer
{
    private static final DecimalFormat BedragAfronding = new DecimalFormat("0.00");
    double BasisTarief = 1.01;
    double KostPerKm = 0.175;
    boolean OpnieuwVragen = true;

    public KaartLezer()
    {
	boolean IsGeldigHoeveelheidKm = false;
	Scanner Scanner = new Scanner(System.in);
	String WilPersoonInchecken;
	String WilPersoonUitchecken;
	Date Datum = new Date();
	System.out.println(new SimpleDateFormat("HH:mm").format(Datum));
	ZoneId TijdPerSysteem = ZoneId.systemDefault();
	ZonedDateTime Tijd = ZonedDateTime.now(TijdPerSysteem);
	double HoeveelheidKmReizen;
	int UurVanDag = Tijd.getHour();
	OVChipKaart OVChipKaartActie = new OVChipKaart();
	String TijdBericht = "Hallo";
	while (OpnieuwVragen == true)
	{
	    if (OVChipKaartActie.IsIngecheckt == false)
	    {
		if (UurVanDag >= 6 && UurVanDag <= 12)
		{
		    TijdBericht = "Goedemorgen";
		}
		else if (UurVanDag > 12 && UurVanDag <= 18)
		{
		    TijdBericht = "Goedemiddag";
		}
		else if (UurVanDag > 18 && UurVanDag <= 24)
		{
		    TijdBericht = "Goedeavond";
		}
		else
		{
		    TijdBericht = "Goedenacht";
		}
		OpnieuwVragen = false;
		System.out.println(TijdBericht + ", Wilt u inchecken?");
		System.out.println("Ja/Nee");
		WilPersoonInchecken = Scanner.next();
		if (WilPersoonInchecken.equalsIgnoreCase("Ja"))
		{
		    System.out.println("Hoeveel km moet u reizen?");
		    HoeveelheidKmReizen = Scanner.nextDouble();
		    if (HoeveelheidKmReizen <= 0)
		    {
			IsGeldigHoeveelheidKm = false;
			System.out.println("Dat is geen geldig antwoord!");
		    }
		    else
		    {
			IsGeldigHoeveelheidKm = true;
		    }
		    while (IsGeldigHoeveelheidKm == false)
		    {
			System.out.println("Hoeveel km moet u reizen?");
			HoeveelheidKmReizen = Scanner.nextDouble();
			if (HoeveelheidKmReizen <= 0)
			{
			    IsGeldigHoeveelheidKm = false;
			    System.out.println("Dat is geen geldig antwoord!");
			}
			else
			{
			    OpnieuwVragen = true;
			    OVChipKaartActie.IsIngecheckt = true;
			    IsGeldigHoeveelheidKm = true;
			}
		    }
		    OpnieuwVragen = true;
		    OVChipKaartActie.IsIngecheckt = true;
		    Inchecken(OVChipKaartActie.Bedrag, HoeveelheidKmReizen);

		}
		else
		{
		    System.out.println("U wilt niet inchecken of u heeft een ongeldig antwoord!");
		}
	    }
	    else
	    {
		// uitchecken:
		if (UurVanDag >= 6 && UurVanDag <= 12)
		{
		    TijdBericht = "Goedemorgen";
		}
		else if (UurVanDag > 12 && UurVanDag <= 18)
		{
		    TijdBericht = "Goedemiddag";
		}
		else if (UurVanDag > 18 && UurVanDag <= 24)
		{
		    TijdBericht = "Goedeavond";
		}
		else
		{
		    TijdBericht = "Goedenacht";
		}
		OpnieuwVragen = false;
		System.out.println(TijdBericht + ", Wilt u uitchecken?");
		System.out.println("Ja/Nee.");
		WilPersoonUitchecken = Scanner.next();
		if (WilPersoonUitchecken.equalsIgnoreCase("Ja"))
		{
		    if (OVChipKaartActie.IsGeldig == true)
		    {
			System.out.println("U bent successvol uitgecheckt!");
			OpnieuwVragen = false;
			OVChipKaartActie.IsIngecheckt = false;
			System.out.println("U heeft nog €" + BedragAfronding.format(OVChipKaartActie.Bedrag) + " over!");
		    }
		    else
		    {
			System.out.println("OV chipkaart is niet geldig!");
			OVChipKaartActie.IsIngecheckt = false;
		    }

		}
		else
		{
		    System.out.println("U wilt niet uitchecken of u heeft een ongeldig antwoord!");
		    OVChipKaartActie.IsIngecheckt = true;
		}
	    }

	}
    }

    public void Inchecken(double x, double y)
    {
	OVChipKaart OVChipKaartActie = new OVChipKaart();
	if (OVChipKaartActie.IsGeldig == true)
	{
	    KostPerKm *= y;
	    double VolBedrag = BasisTarief + KostPerKm;
	    if (x >= VolBedrag)
	    {
		System.out.println("U saldo: €" + x);
		System.out.println("U bent successvol ingecheckt!");
		OVChipKaartActie.Bedrag -= VolBedrag;
		OVChipKaartActie.IsIngecheckt = false;
	    }
	    else
	    {
		System.out.println("U saldo: €" + x);
		System.out.println("U heeft niet genoeg saldo!");
		OVChipKaartActie.IsIngecheckt = false;
		System.out.println("Het Kost: €" + BedragAfronding.format(VolBedrag));
	    }
	}
	else
	{
	    System.out.println("U saldo: €" + x);
	    System.out.println("OV chipkaart is niet geldig!");
	}
    }
}
