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
import java.sql.*;

public class AdresseATraiter {
    
    private String username = "root";
    private String password = null ;
    private String url = "jdbc:mysql://localhost:3306/araneus";
    
    private String tempAdresse;
    
    public AdresseATraiter()
    {
        // On essaie d'instancier la classe com.mysql.jdbc.Driver
        try {
             Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }  
    }
    
    public void ajouterAdresse(Adresse adresse)
    {
        try {
            // On se connecte à la base
            Connection con;
            Statement stmt;
            con = DriverManager.getConnection(url, username, password);
            
            // Create a Statement Object
            stmt = con.createStatement();
            
            // On crée la requete pour supprimer  l'enregistrement recuperer
            String requete = "INSERT INTO adresse (adresse) VALUES ('"+adresse.getAdresse()+"');";
            boolean rs2 = stmt.execute(requete);
            
            // On ferme la connection
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("==> SQLException: ");
            while (ex != null) {
                System.out.println("Message: " + ex.getMessage ());
                System.out.println("SQLState: " + ex.getSQLState ());
                System.out.println("ErrorCode: " + ex.getErrorCode ());
                ex = ex.getNextException();
                System.out.println("");
            }
        }
    }
    
    public Adresse recupererAdresse()
    {
        try {
            // On se connecte à la base
            Connection con;
            Statement stmt;
            con = DriverManager.getConnection(url, username, password);
            
            // Create a Statement Object
            stmt = con.createStatement();
            
            // On crée et on execute la requete
            String query = "SELECT idadresse, adresse FROM adresse ORDER BY idadresse ASC LIMIT 1;";
            ResultSet rs = (ResultSet) stmt.executeQuery(query);
            
            // On récupère les resultats
            rs.next();
            String id = rs.getString("idadresse");
            this.tempAdresse = rs.getString("adresse");
            
            // On crée la requete pour supprimer  l'enregistrement recuperer
            String requete = "DELETE FROM adresse WHERE idadresse ='"+id+"';";
            boolean rs2 = stmt.execute(requete);
            
            // On ferme la connection
            stmt.close();
            con.close();
        }
        // print out decent error messages
        catch (SQLException ex) {
            System.err.println("==> SQLException: ");
            while (ex != null) {
                System.out.println("Message: " + ex.getMessage ());
                System.out.println("SQLState: " + ex.getSQLState ());
                System.out.println("ErrorCode: " + ex.getErrorCode ());
                ex = ex.getNextException();
                System.out.println("");
            }

        }
        return new Adresse(this.tempAdresse);
    }
    
}