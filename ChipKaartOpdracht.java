package ChipKaartBestandOOP1;

import java.util.Scanner;

public class ChipKaartOpdracht
{

    public static void main(String[] args)
    {
	double GeldOpKaartZetten;
	String KaartNaam;
	Scanner sc = new Scanner(System.in);
	OVKaart Acties = new OVKaart();
	System.out.println("Wat is u naam?");
	KaartNaam = sc.next();
	Acties.NaamInfo(KaartNaam);
	System.out.println("Hoeveel geld wil je op je OVkaart plaatsen?");
	GeldOpKaartZetten = sc.nextDouble();
	Acties.SaldoInfo(GeldOpKaartZetten);
    }

}
