/**
 *  Copyright 2004 Fabien SCHWOB
 *  
 *  This file is part of Araneus.
 *
 *  Araneus is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  Araneus is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Foobar; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

/**
 *  Importation des packages nécessaires
 */
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