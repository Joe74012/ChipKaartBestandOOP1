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
    boolean opnieuwVragen = true;
    Scanner scanner = new Scanner(System.in);
    OVChipKaart ovChipKaartActie = new OVChipKaart();
    double basisTarief = 1.01;
    double kostPerKm = 0.175;

    public KaartLezer()
    {
	int herhaal = 1;
	while (herhaal == 1)
	{
	    kostPerKm = 0.175;
	    basisTarief = 1.01;
	    boolean isGeldigHoeveelheidKm = false;
	    Date datum = new Date();
	    System.out.println(new SimpleDateFormat("HH:mm").format(datum));
	    ZoneId tijdPerSysteem = ZoneId.systemDefault();
	    ZonedDateTime tijd = ZonedDateTime.now(tijdPerSysteem);
	    double hoeveelheidKmReizen, aantalWaarderen = 0;
	    int uurVanDag = tijd.getHour();
	    String tijdBericht = "";
	    int watPersoonWiltDoen;
	    if (uurVanDag >= 6 && uurVanDag <= 12)
	    {
		tijdBericht = "Goedemorgen";
	    }
	    else if (uurVanDag > 12 && uurVanDag <= 18)
	    {
		tijdBericht = "Goedemiddag";
	    }
	    else if (uurVanDag > 18 && uurVanDag <= 24)
	    {
		tijdBericht = "Goedenavond";
	    }
	    else
	    {
		tijdBericht = "Goedenacht";
	    }
	    opnieuwVragen = false;
	    System.out.println(tijdBericht + " " + ovChipKaartActie.naam + ".");
	    if(ovChipKaartActie.isIngecheckt == true) {
		 System.out.println("Typ 1 om uit te checken.");
	    } else {
		 System.out.println("Typ 1 om in te checken.");
	    }
	    System.out.println("Typ 2 voor instructies.");
	    System.out.println("Typ 3 om te opwaarderen.");
	    watPersoonWiltDoen = scanner.nextInt();
	    switch (watPersoonWiltDoen)
	    {
		case 1:
		    if (ovChipKaartActie.isIngecheckt == false)
		    {
			System.out.println("U heeft gekozen om in te checken.");
			isGeldigHoeveelheidKm = false;
			while (isGeldigHoeveelheidKm == false)
			{
			    System.out.println("Hoeveel km moet u reizen?");
			    hoeveelheidKmReizen = scanner.nextDouble();
			    if (hoeveelheidKmReizen <= 0)
			    {
				isGeldigHoeveelheidKm = false;
				System.out.println("Dat is geen geldig antwoord!");
			    }
			    else
			    {
				opnieuwVragen = true;
				ovChipKaartActie.isIngecheckt = true;
				isGeldigHoeveelheidKm = true;
				Inchecken(ovChipKaartActie.bedrag, hoeveelheidKmReizen);
			    }
			}
		    }
		    else
		    {
			System.out.println("U heeft gekozen om uit te checken.");
			Uitchecken();
		    }
		    break;

		case 2:
		    herhaal = 0;
		    while (herhaal != 1)
		    {
			Instructie();
			System.out.println("typ 1 om terug te gaan.");
			herhaal = scanner.nextInt();
			if (herhaal != 1)
			{
			    System.out.println("Die waarde is ongeldig.");
			}
		    }

		    break;
		case 3:
		    System.out.println("U heeft gekozen om te opwaarderen.");
		    System.out.println("Hoeveel geld wilt u opwaarderen?");
		    aantalWaarderen = scanner.nextDouble();
		    while (aantalWaarderen <= 0)
		    {
			System.out.println("Die waarde is ongeldig.");
			System.out.println("U heeft gekozen om te opwaarderen.");
			System.out.println("Hoeveel geld wilt u opwaarderen?");
			aantalWaarderen = scanner.nextDouble();
		    }
		    ovChipKaartActie.Opwaarderen(aantalWaarderen);
		    System.out.println("Het saldo op u kaart: €" + ovChipKaartActie.bedrag);
		    herhaal = 1;
		default:
		    System.out.println("Die waarde is ongeldig.");
		    herhaal = 1;
	    }
	}

    }

    public void Inchecken(double x, double y)
    {
	boolean opnieuwVragenSaldo = true;
	kostPerKm = kostPerKm * y;
	double VolBedrag = basisTarief + kostPerKm;
	while (opnieuwVragenSaldo == true)
	{
	    opnieuwVragenSaldo = false;
	    if (ovChipKaartActie.isGeldig == true)
	    {
		if (ovChipKaartActie.bedrag >= VolBedrag)
		{
		    System.out.println("U saldo: €" + BedragAfronding.format(ovChipKaartActie.bedrag));

		    System.out.println("U bent successvol ingecheckt!");
		    ovChipKaartActie.bedrag -= VolBedrag;
		    ovChipKaartActie.isIngecheckt = true;
		}
		else
		{
		    System.out.println("U saldo: €" + ovChipKaartActie.bedrag);
		    System.out.println("U heeft niet genoeg saldo!");
		    ovChipKaartActie.isIngecheckt = false;
		    System.out.println("Het Kost: €" + BedragAfronding.format(VolBedrag));
		    opnieuwVragen = false;
		}
	    }
	    else
	    {
		System.out.println("U saldo: €" + ovChipKaartActie.bedrag);
		System.out.println("OV chipkaart is niet geldig!");
		opnieuwVragen = false;
	    }
	}
    }

    public void Uitchecken()
    {
	if (ovChipKaartActie.isGeldig == true)
	{
	    System.out.println("U bent successvol uitgecheckt!");
	    opnieuwVragen = false;
	    ovChipKaartActie.isIngecheckt = false;
	    System.out.println("U heeft nog €" + BedragAfronding.format(ovChipKaartActie.bedrag) + " over!");
	}
	else
	{
	    System.out.println("OV chipkaart is niet geldig!");
	    ovChipKaartActie.isIngecheckt = false;
	    opnieuwVragen = false;
	}
    }

    public void Instructie()
    {
	System.out.println("U heeft gekozen voor een instructie.");
	System.out.println("Instructie voor inchecken/uitchecken: Als u wilt inchecken of uitchecken typ dan 1 op het begin scherm en volg de instructies die op het scherm staan.");
	System.out.println("Instructie voor opwaarderen: Als u wilt opwaarderen typ dan 1 op het begin scherm en typ hoeveel geld u op u kaart wil opwaarderen.");

    }
}
