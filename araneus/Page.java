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
 
public class Page{
    
    private String adresse;
    private String titre;
    private String contenu;
    private float indiceDePopularite;
    
    // dictionnaire contenant les metas    
        
    public Page()
    {

    }
    
    public void definirAdresse(String adresse)
    {
        this.adresse = adresse;    
    }
    
    private void nettoyerPage()
    {
        // nettoie this.contenu
    }
    
    public void calculerIndiceDePopularite()
    {
        
    }
}