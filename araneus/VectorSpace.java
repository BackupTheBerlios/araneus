import java.util.Vector;

public class VectorSpace {
    public static void main(String[] args) {

        // On définit un certain nombres de documents
        String doc1 = "Il s'agit d'un système assez ésotérique permettant de mesurer la qualité d'une équipe de développeurs.";
        String doc2 = "Et avec tout le temps que vous y gagnez, vous pouvez faire plein d'autres choses.";
        String doc3 = "le le le Ils réalisèrent que les chefs de projet avaient tant insisté sur le respect du \"planning\" que les programmeurs se contentaient de se dépêcher en écrivant du très mauvais code, parce que la phase de  correction ne faisait pas partie intégrante du planning.";

        // On mets dans un tableau la liste des mots
        String[] tableau    = ((doc1 + doc2 + doc3).toLowerCase()).split("(\\s+|[ ,?.;:/!']+)");
        
        //String[] tab = trierAplha(tableau);
        
        
        // On crée un nouvel objet de type Vector
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
        
        // On affiche la liste des éléments du Vector
        for (int i = 0; i < listeMot.size(); i++) {
            System.out.println(listeMot.elementAt(i));
        }
        System.out.println("--- \n"+listeMot.size()+" mots");
    }
    
    /**
     * Trie un tableau de Strind dans l'ordre alpha (hors accent et
     * caractères spéciaux. Utile pour visualisé l'elimination effective
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
                return false;
            }
        }
        return true;
                
    }
}