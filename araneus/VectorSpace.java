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
 *  along with Araneus; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

/**
 *  Importation des packages nécessaires
 */
import java.util.ArrayList;
import java.util.Vector;
import java.util.HashSet;
import java.util.Date;

/**
 * Essaie d'implementation de l'article sur "Vector Space"
 *
 * @author  Fabien SCHWOB
 * @version 0.1.1
 */
public class VectorSpace {
    
    private Vector espaceMot;
    private ArrayList listeDocument;
    private ArrayList listeVecteur;
    private Date temps;
    private long tps1, tps2, tps3, tps4;
    
    public VectorSpace() {
        // On initialise le vecteur contenant la liste des documents
        this.listeDocument = new ArrayList(1600);
        this.temps = new Date();
        tps1 = temps.getTime();
    }

    /**
     * Permet d'ajouter un document à l'espace vectoriel
     */
    public void ajouterDocument(String document)
    {
        this.listeDocument.add(document);
    }
    
    /**
     * Calcul la liste des mots différents dans l'espace vectoriel
     */
    public void calculerVectorSpace()
    {
        // Bench
        this.temps = new Date();
        tps2 = temps.getTime();
        System.out.println("Bench : "+(tps2-tps1)+" milliseconds. (Fin ajout doc, debut calcul vector");
        
        // On récupère le contenu de tous les documents 
        StringBuffer tousLesDocuments = new StringBuffer();
        for (int i = 0; i < this.listeDocument.size(); i++) {
            tousLesDocuments.append(this.listeDocument.get(i));
        }
        
        // On mets dans un tableau la liste des mots
        String[] tableau    = (tousLesDocuments.toString().toLowerCase()).split("(\\s+|[ ,?.;:/!']+)");
        tousLesDocuments = null;    // Permet plus ou moins de dire au GarbageCOllector de supprimer l'objet de la memoire
        // On crée un nouvel objet de type Vector
        HashSet listeMot = new HashSet(tableau.length/2);

        // On parcours la liste des mots de la liste
        for (int i = 0; i < tableau.length; i++) {
            // Si le mot n'existe pas encore dans le Vector, et qu'il
            // n'est pas un mot courant on l'ajoute
            if (!estMotCourant(tableau[i])) {
                listeMot.add(tableau[i]);
            }
        }
        this.espaceMot = new Vector (listeMot.size());
        this.espaceMot.copyInto(listeMot.toArray());
        
        // Bench
        this.temps = new Date();
        tps3 = temps.getTime();
        System.out.println("Bench : "+(tps3-tps2)+" milliseconds. (fin calcul vector, debut vecteur document");
        
        // On crée un recepteur pour les vecteurs des documents
        this.listeVecteur = new ArrayList(this.listeDocument.size());
        
        // On crée maintenant des vecteurs pour chacun des documents
        for (int i = 0; i < this.listeDocument.size(); i++) {
            this.listeVecteur.add(i, creerVecteur( (String) this.listeDocument.get(i)));
        }
        
        // Bench
        temps = new Date();
        tps4 = temps.getTime();
        System.out.println("Bench : "+(tps4-tps3)+" milliseconds. (Fin calcul vector");
    }
    
    /**
     *  Permet de vérifier que le mot n'est pas un mot courant
     *  Le test est pour le moment valide que pour les mots de la langue
     *  française
     */ 
    private boolean estMotCourant(String mot)
    {
        // On définit les "stop word"
        
        String[] stop = {"au", "aux", "avec", "ce", "ces", "dans", "de",
                        "des", "du", "elle", "en", "et", "eux", "il",
                        "je", "la", "le", "leur", "lui", "ma", "mais",
                        "me", "même", "mes", "moi", "mon","ne", "nos",
                        "notre", "nous", "on", "ou", "par", "pas",
                        "pour", "qu", "que", "qui", "sa", "se", "ses",
                        "son", "sur", "ta", "te", "tes", "toi", "ton",
                        "tu", "un", "une", "vos", "votre", "vous", "c",
                        "d", "j", "l", "à", "m", "n", "s", "t", "y",
                        "été", "étée", "étées", "étés", "étant",
                        "étante", "étants", "étantes", "suis", "es",
                        "est", "sommes", "êtes", "sont", "serai",
                        "seras", "sera", "serons", "serez", "seront",
                        "serais", "serait", "serions", "seriez",
                        "seraient", "étais", "était", "étions", "étiez",
                        "étaient", "fus", "fut", "fûmes", "fûtes",
                        "furent", "sois", "soit", "soyons", "soyez",
                        "soient", "fusse", "fusses", "fût", "fussions",
                        "fussiez", "fussent", "ayant", "ayante",
                        "ayantes", "ayants", "eu", "eue", "eues", "eus",
                        "ai", "as", "avons", "avez", "ont", "aurai",
                        "auras", "aura", "aurons", "aurez", "auront",
                        "aurais", "aurait", "aurions", "auriez",
                        "auraient", "avais", "avait", "avions", "aviez",
                        "avaient", "eut", "eûmes", "eûtes", "eurent",
                        "aie", "aies", "ait", "ayons", "ayez", "aient",
                        "eusse", "eusses", "eût", "eussions", "eussiez",
        "eussent"};
        
        for (int j = 0; j<stop.length; j++) {
            if (mot.equals(stop[j]))
            {
                return true;
            }
        }
        return false;
                
    }
    
    /**
     *  Permet de créer un vecteur dans l'espace vectoriel à partir
     *  d'une chaine de caractères.
     */
    public int[] creerVecteur(String chaine)
    {
        int[] document = new int[this.espaceMot.size()];
        // on parcourt la chaine de base
            //pour chaque mot, si le mot existe dans la liste des mots 
                //on ajouter 1 dans le vecteur
        String[] motDocument = chaine.toLowerCase().split("(\\s+|[ ,?.;:/!']+)");
        int temp;
        for (int i = 0; i < motDocument.length; i++) {
            if (this.espaceMot.contains(motDocument[i])) {
                temp = this.espaceMot.indexOf(motDocument[i]);
                document[temp] = (document[temp])+1;
            }
        }
        return document;
    }
    
    public double norme(int[] vecteur)
    {
        int somme = 0;
        for (int i = 0; i < vecteur.length; i++) {
            somme = somme + (int) Math.pow((double)vecteur[i], (double)2);
        }
        return Math.sqrt(somme);
    }
    
    public double cosinus(int[] v1, int[] v2)
    {
        // cosinus = (V1.V2) / (||V1|| . ||V2||)
        // u.v  = x x' + y y' + zz'
        int somme = 0;
        for (int i = 0; i < v1.length; i++) {
            somme += (v1[i]*v2[i]);
        }
        return (somme/(this.norme(v1)*this.norme(v2)));
    }
    
    public void recherche(String requete)
    {
        int[] vecteurRequete = creerVecteur(requete);
        
        // On parcours la liste des vecteurs de documents
        for (int i = 0; i < this.listeDocument.size(); i++) {
            cosinus(vecteurRequete, (int[])this.listeVecteur.get(i));
            //System.out.println("Cosinus entre la recherche et le document "+i+" : "+cosinus(vecteurRequete, (int[])this.listeVecteur.elementAt(i)));
        }
    }
}