public class TestAdresse {
    public static void main(String[] args) {
        Adresse test = new Adresse("http://www.linuxfr.org");
        String page = test.telecharger();
        System.out.println(page);
    }
}