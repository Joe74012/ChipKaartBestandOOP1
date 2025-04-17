package ChipKaartBestandOOP1;

public class OVChipKaart
{
    double bedrag = 10.00;
    String naam = "Joe Martin";
    boolean isGeldig = true;
    boolean isIngecheckt = false;
    int id = (int) ((Math.random() * 10000) + 1);

    public void Opwaarderen(double z)
    {	
	bedrag += z;
	System.out.println("U heeft het geld succesvol erop gezet!");
    }
}
