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
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class RobotHttp extends Robot
{
    public RobotHttp()
    {
        
    }
    
    public void extraireUrl()
    {
        // Création de l'expression régulière devant reconnaitre un lien
        
        String regexp = "<a href=(?:\"|'| )(http[\\S\\.\\/:]*)(?:\"|'| )>([\\S\\.\\/: ]*)<\\/a>";
        
        Pattern regHref = Pattern.compile(regexp);

        Adresse test = new Adresse("http://www.izidoo.com");
        String page = test.telecharger();
        
        Matcher matcherLien        = regHref.matcher(page);

        while (matcherLien.find()) {
            System.out.println(matcherLien.group(2));    
            System.out.println(matcherLien.group(1));
            System.out.println("-- ");
        }
    } 

    /**
     *  La fonction ajouterLien() permet d'ajouter un nouveau lien à la liste
     *  existante en évitant les doublons.
     **/
    private String[] ajouterLien(String lien, String[] liens) {
        boolean existe = false;
        for (int j = 0; j < liens.length; j++) {
            if (liens[j] == lien) {
                existe = true;
                break;
            }
        }

        if (existe == false) {
            liens[liens.length + 1] = lien;
        }

        return liens;
    }

    public String[] extraireLiens(String url, String texte) {
        //  Formatage des liens (on enlève l'espace éventuel entre href et = et "
        //  (pas plus de 1 espace, au-delà c'est louche...) 
        texte = texte.replace("'", "\"");
        texte = texte.replaceAll("href([[:space:]]{0,1})=", "href=");
        texte = texte.replaceAll("href=([[:space:]]{0,1})\"", "href=\"");
        String[] exp_texte = split("href=\"", texte);

        String[] liens = String[500];
        liens[0] = url;
        for (int i = 1; i <= exp_texte.length; i++) {
            ligne = "href=\"".exp_texte[i];
     
            start = strpos(ligne, "href=\"") + 6;
            ligne = substr(ligne, start);
            length = strpos(ligne, "\"");
            href = substr(ligne, 0, length);
     
            if ((eregi("http:", href) || eregi("ftp:", href)) && !eregi("#", href)) {
                // Cas normal avec URL absolue 
                liens = ajouterLien(href, liens);
            }
            elseif (!eregi("mailto:", href)
                    && !eregi("javascript:", href)
                    && !eregi("http:", href)
                    && !eregi("ftp:", href)
                    && !eregi("../", href)
                    && !eregi("#", href)) {
                // URL relative 
                if (eregi("^./", href)) {
                    href = str_replace("./", "", href);
                }
     
                nb_slashs = substr_count(url, "/");
                if (nb_slashs <= 2) {
                    href = url."/".href;
                } else {
                    length = strrpos(url, '/') + 1;
                    url_base = substr(url, 0, length);
                    href = url_base.href;
                }
     
                liens = ajouterLien(href, liens);
            }
            elseif (!eregi("mailto:", href)
                    && !eregi("javascript:", href)
                    && !eregi("http:", href)
                    && !eregi("ftp:", href)
                    && eregi("^../", href)
                    && !eregi("#", href)) {
                // URL relative précédée de ../ 
                nb_slashs = substr_count($url, "/");
                if (nb_slashs <= 2) {
                    //Il s'agit forcément d'une erreur dans le lien, puisque nous sommes déjà à la racine. On la prend pas en compte ici
                    //URL type http://www.serveur.com 
                    href = url;
                }
                elseif (nb_slashs == 3) {
                    // Encore une erreur
                    //URL type http://www.serveur.com/ 
                    href = url;
                } else {
                    length = strrpos(url, "/") + 1;
                    url_base = substr(url, 0, length);
                    href = url_base.href;
     
                    while (strpos(href, "../")) {
                        start_length = strpos(href, "/../");
                        href1 = substr(href, 0, start_length);
                        href2 = substr(href, start_length);
     
                        length = strrpos(href1, "/");
                        href1 = substr(href1, 0, length);
                        start = strpos(href2, "../") + 2;
                        href2 = substr(href2, start);
                        href = href1+href2;
                    }
                }
     
                liens = ajouterLien(href, liens);
            }
        }
        return liens;
    }

    
}