import java.util.regex.*;
public class Robot 
{
  public static void main( String[] args ) {
    String lienRetourne, urlRetourne;
    String url  = "Ceci est mon site web: <a href=\"http://www.IZI-doo3.com/admin/?p=bobo\">. Pas mal, non ? Vous pouvez visiter <a href=http://www.izidoo.com/ target=blank> <a href='/newsletter/index.php'>";
    int debutLien, finLien, debutUrl, finUrl;

// Recherche de tous les liens hypertexte (de type href='*******')

    Pattern motifLien          = Pattern.compile("href=[\"|']{0,1}[^\\s>]*[\"|']{0,1}");
    Matcher matcherLien        = motifLien.matcher(url);

    while (matcherLien.find()) {
      debutLien                = matcherLien.start();
      finLien                  = matcherLien.end();
      lienRetourne             = url.substring(debutLien, finLien);

      Pattern motifUrl          = Pattern.compile("href=[\"|']{0,1}");
      Matcher matcherUrl        = motifUrl.matcher(lienRetourne);
      lienRetourne              = matcherUrl.replaceAll("");
      
      motifUrl                  = Pattern.compile("[\"|']{0,1}$");
      matcherUrl                = motifUrl.matcher(lienRetourne);
      urlRetourne               = matcherUrl.replaceAll("");

      System.out.println(urlRetourne);
      
// Transformation des urls relatives en absolues


      
    }
  }
}
