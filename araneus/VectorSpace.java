import java.util.Vector;

public class VectorSpace {
    public static void main(String[] args) {

        // On d�finit un certain nombres de documents
        String doc1 = "Il s'agit d'un syst�me assez �sot�rique permettant de mesurer la qualit� d'une �quipe de d�veloppeurs.";
        String doc2 = "Et avec tout le temps que vous y gagnez, vous pouvez faire plein d'autres choses.";
        String doc3 = "le le le Ils r�alis�rent que les chefs de projet avaient tant insist� sur le respect du \"planning\" que les programmeurs se contentaient de se d�p�cher en �crivant du tr�s mauvais code, parce que la phase de  correction ne faisait pas partie int�grante du planning.";

        // On mets dans un tableau la liste des mots
        String[] tableau    = ((doc1 + doc2 + doc3).toLowerCase()).split("(\\s+|[ ,?.;:/!']+)");
        
        //String[] tab = trierAplha(tableau);
        
        
        // On cr�e un nouvel objet de type Vector
        Vector listeMot = new Vector();

        // On parcours la liste des mots de la liste
        for (int i = 0; i < tableau.length; i++) {
            // Si le mot n'existe pas encore dans le Vector, on l'ajoute
            if (motStop(tableau[i])) {
                if (!listeMot.contains(tableau[i]))
                {
                    listeMot.addElement(tableau[i]);
                }
            }
        }
        
        // On affiche la liste des �l�ments du Vector
        for (int i = 0; i < listeMot.size(); i++) {
            System.out.println(listeMot.elementAt(i));
        }
        System.out.println("--- \n"+listeMot.size()+" mots");
    }
    
    /**
     * Trie un tableau de Strind dans l'ordre alpha (hors accent et
     * caract�res sp�ciaux. Utile pour visualis� l'elimination effective
     * des mots en doublons.
     */
    private static String[] trierAplha(String[] tableau)
    {
        boolean permute;
        String temp;
        do
        {
            permute = false;
            for (int i = 0; i < (tableau.length - 1); i++) {
                if ((tableau[i].compareTo(tableau[i + 1])) > 0)
                {
                        permute = true;
                        temp = tableau[i]
                               ;
                        tableau[i] = tableau[i + 1];
                        tableau[i + 1] = temp;
                }
            }
        } while (permute == true);    
        return tableau;
    }
    
    private static boolean motStop(String mot)
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
                return false;
            }
        }
        return true;
                
    }
}