package ChipKaartBestandOOP1;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class KaartLezer
{
    public KaartLezer()
    {
	Scanner sc = new Scanner(System.in);
	String JaOfNee;
	Date date = new Date();
	System.out.println(new SimpleDateFormat("HH:mm").format(date));
	ZoneId zoneId = ZoneId.systemDefault();
	ZonedDateTime zdt = ZonedDateTime.now(zoneId);
	double km;
	int uur = zdt.getHour();
	if (uur >= 6 && uur <= 12)
	{
	    System.out.println("Goedemorgen, Wilt u inchecken?");
	    System.out.println("Ja/Nee");
	}
	if (uur > 12 && uur <= 18)
	{
	    System.out.println("Goedemiddag, Wilt u inchecken?");
	    System.out.println("Ja/Nee");
	}
	if (uur > 18 && uur <= 24)
	{
	    System.out.println("Goedeavond, Wilt u inchecken?");
	    System.out.println("Ja/Nee");
	}
	if (uur >= 0 && uur < 6)
	{
	    System.out.println("Goedenacht, Wilt u inchecken?");
	    System.out.println("Ja/Nee");
	}
	JaOfNee = sc.next();
	System.out.println("Hoeveel km moet u reizen?");
	km = sc.nextDouble();
	if (JaOfNee.equalsIgnoreCase("Ja"))
	{
	    Incheck IncheckActie = new Incheck();
	    OVChipKaart OVChipKaartActie = new OVChipKaart();
	    IncheckActie.Inchecken(OVChipKaartActie.Bedrag, km);
	}
	else
	{
	    System.out.println("U wilt niet inchecken!");
	}
    }

    public static void main(String[] args)
    {
	KaartLezer KL1 = new KaartLezer();
    }
}
