public class TestAdresseATraiter {
    public static void main(String[] args) {
        AdresseATraiter contenaire = new AdresseATraiter();

        Adresse skink = new Adresse("http://www.skink.org");
        Adresse schwob = new Adresse("http://www.schwob.org");
        contenaire.ajouterAdresse(skink);
        contenaire.ajouterAdresse(schwob);
        
        System.out.println(contenaire.recupererAdresse());
    }
}