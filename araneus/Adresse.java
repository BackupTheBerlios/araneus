import java.net.*;
import java.io.*;

public class Adresse{
    
    private String adresseSite;
    private String page;
    
    public Adresse(String adresse)
    {
        this.adresseSite = adresse;
    }
    
    public String telecharger()
    {
        try {
            URL yahoo = new URL(this.adresseSite);
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                    yc.getInputStream()));
            String inputLine;
            
    
            while ((inputLine = in.readLine()) != null) 
                this.page += inputLine+"\n";
            in.close();
            
            
        } catch (Exception e) {
            System.out.println("Erreur lors du telechargement");
        }
        System.out.println(page);
        return this.page;
    }
    
    public String getAdresse()
    {
        return this.adresseSite;    
    }
    
    public String toString()
    {
        return "Adresse : "+this.adresseSite;
    }
}