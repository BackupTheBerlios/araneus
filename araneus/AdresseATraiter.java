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