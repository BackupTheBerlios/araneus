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
import java.util.Date;

public class TestVector
{
    public static void main(String[] args) {
        VectorSpace test = new VectorSpace();
        Date temps = new Date();
        long debut = temps.getTime();
        int x = 0;
        for (x=0; x <= 11000; x++) {
            // On d�finit un certain nombres de documents
            test.ajouterDocument("Il s'agit d'un syst�me assez �sot�rique permettant de mesurer la qualit� d'une �quipe de d�veloppeurs.");
            test.ajouterDocument("Et avec tout le temps que vous y gagnez, vous pouvez faire plein d'autres choses en m�me temps.");
            test.ajouterDocument("le le le Ils r�alis�rent que les chefs de projet avaient tant insist� sur le respect du \"planning\" que les programmeurs se contentaient de se d�p�cher en �crivant du tr�s mauvais code, parce que la phase de  correction ne faisait pas partie int�grante du planning.");
        }
        
        test.calculerVectorSpace();
        
       test.recherche(args[0]);
       temps = new Date();
       long fin = temps.getTime();
       System.out.println("Bench : "+(fin-debut)+" milliseconds. elements :"+(x*3));
    }
}