

import java.io.IOException;

public class Main2 {
    public static void main(String[] args) throws IOException {
        String  file = "Mini-Projet2/src/graphe.txt";
        Graphe graphe = new Graphe(file);
        Conversion conversion = new Conversion(graphe,3);
        conversion.generationDuCNF();
        graphe.affichage();
    }
}
