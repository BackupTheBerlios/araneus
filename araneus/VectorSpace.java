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
 *  Importation des packages n�cessaires
 */
import java.util.Vector;

/**
 * Essaie d'implementation de l'article sur "Vector Space"
 *
 * @author  Fabien SCHWOB
 * @version 0.1
 */
public class VectorSpace {
    
    private Vector espaceMot;
    private Vector listeDocument;
    
    public VectorSpace() {

        // On d�finit un certain nombres de documents
        String doc1 = "Il s'agit d'un syst�me assez �sot�rique permettant de mesurer la qualit� d'une �quipe de d�veloppeurs.";
        String doc2 = "Et avec tout le temps que vous y gagnez, vous pouvez faire plein d'autres choses en m�me temps.";
        String doc3 = "le le le Ils r�alis�rent que les chefs de projet avaient tant insist� sur le respect du \"planning\" que les programmeurs se contentaient de se d�p�cher en �crivant du tr�s mauvais code, parce que la phase de  correction ne faisait pas partie int�grante du planning.";

        // On mets dans un tableau la liste des mots
        String[] tableau    = ((doc1 + doc2 + doc3).toLowerCase()).split("(\\s+|[ ,?.;:/!']+)");
        
        //String[] tab = trierAplha(tableau);
        
        
        // On cr�e un nouvel objet de type Vector
        Vector listeMot = new Vector();

        // On parcours la liste des mots de la liste
        for (int i = 0; i < tableau.length; i++) {
            // Si le mot n'existe pas encore dans le Vector, et qu'il
            //n'est pas un mot "stop" on l'ajoute
            if (!estMotCourant(tableau[i])) {
                if (!listeMot.contains(tableau[i]))
                {
                    listeMot.addElement(tableau[i]);
                }
            }
        }
        this.espaceMot = listeMot;
        // On affiche la liste des �l�ments du Vector
        for (int i = 0; i < listeMot.size(); i++) {
            System.out.println(listeMot.elementAt(i));
        }
        System.out.println("--- \n"+listeMot.size()+" mots");
    }
    
    public void ajouterDocument()
    {
        
    }
    
    public void calculerVectorSpace()
    {
            
    }
    
    private boolean estMotCourant(String mot)
    {
        // On d�finit les "stop word"
        
        String[] stop = {"au", "aux", "avec", "ce", "ces", "dans", "de",
                        "des", "du", "elle", "en", "et", "eux", "il",
                        "je", "la", "le", "leur", "lui", "ma", "mais",
                        "me", "m�me", "mes", "moi", "mon","ne", "nos",
                        "notre", "nous", "on", "ou", "par", "pas",
                        "pour", "qu", "que", "qui", "sa", "se", "ses",
                        "son", "sur", "ta", "te", "tes", "toi", "ton",
                        "tu", "un", "une", "vos", "votre", "vous", "c",
                        "d", "j", "l", "�", "m", "n", "s", "t", "y",
                        "�t�", "�t�e", "�t�es", "�t�s", "�tant",
                        "�tante", "�tants", "�tantes", "suis", "es",
                        "est", "sommes", "�tes", "sont", "serai",
                        "seras", "sera", "serons", "serez", "seront",
                        "serais", "serait", "serions", "seriez",
                        "seraient", "�tais", "�tait", "�tions", "�tiez",
                        "�taient", "fus", "fut", "f�mes", "f�tes",
                        "furent", "sois", "soit", "soyons", "soyez",
                        "soient", "fusse", "fusses", "f�t", "fussions",
                        "fussiez", "fussent", "ayant", "ayante",
                        "ayantes", "ayants", "eu", "eue", "eues", "eus",
                        "ai", "as", "avons", "avez", "ont", "aurai",
                        "auras", "aura", "aurons", "aurez", "auront",
                        "aurais", "aurait", "aurions", "auriez",
                        "auraient", "avais", "avait", "avions", "aviez",
                        "avaient", "eut", "e�mes", "e�tes", "eurent",
                        "aie", "aies", "ait", "ayons", "ayez", "aient",
                        "eusse", "eusses", "e�t", "eussions", "eussiez",
        "eussent"};
        
        for (int j = 0; j<stop.length; j++) {
            if (mot.equals(stop[j]))
            {
                return true;
            }
        }
        return false;
                
    }
    
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
}