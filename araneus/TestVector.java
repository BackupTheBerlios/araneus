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

public class TestVector
{
    public static void main(String[] args) {
        VectorSpace test = new VectorSpace();
        int[] doc = test.creerVecteur("Et avec tout le temps que vous y gagnez, vous pouvez faire plein temps d'autres choses.");
        int[] doc2 = test.creerVecteur("bonjour, je suis fabien temps");
        /*for (int i = 0; i < doc.length; i++) {
            System.out.print(doc[i]+", ");
        }*/
        /*System.out.println(test.norme(doc));*/
        System.out.println(test.cosinus(doc, doc2));
    }
}