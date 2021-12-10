
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graphe {
    private int nbrsommets = 0;
    public int matrice[][] = new int[nbrsommets][nbrsommets];

    public Graphe(String file) throws IOException {
        setGraph(readFile(file));
        setNbrsommets(this.matrice);
    }

    public int[][] readFile(String file) throws IOException {

        BufferedReader buf = new BufferedReader(new FileReader(file));
        int[][] graphe;
        try (Scanner scan = new Scanner(buf)) {
            nbrsommets = 10;
            graphe = new int[nbrsommets][nbrsommets];

            while (scan.hasNextLine()) {
                for (int i = 0; i < graphe.length; i++) {
                    String[] line = scan.nextLine().trim().split(" ");
                    for (int j = 0; j < line.length; j++) {
                        graphe[i][j] = Integer.parseInt(line[j]);
                    }
                }
            }
        }
        return graphe;
    }
    public void affichage() {
        for (int[] ints : matrice) {
            List<Integer> entiers = new ArrayList<>();
            for (int anInt : ints) {
                entiers.add(anInt);
            }
            System.out.println(entiers);
        }

    }

    public void setGraph(int[][] graphe) {
        this.matrice = graphe;
    }

    public void setNbrsommets(int [][] graphe){
        this.nbrsommets = graphe.length;
    }
}
