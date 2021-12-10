

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

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        int[][] graph;
        try (Scanner scanner = new Scanner(bufferedReader)) {
            nbrsommets = 10;
            graph = new int[nbrsommets][nbrsommets];

            while (scanner.hasNextLine()) {
                for (int i = 0; i < graph.length; i++) {
                    String[] line = scanner.nextLine().trim().split(" ");
                    for (int j = 0; j < line.length; j++) {
                        graph[i][j] = Integer.parseInt(line[j]);
                    }
                }
            }
        }
        return graph;
    }
    public void affichage() {
        for (int[] ints : matrice) {
            List<Integer> integers = new ArrayList<>();
            for (int anInt : ints) {
                integers.add(anInt);
            }
            System.out.println(integers);
        }

    }

    public void setGraph(int[][] graph) {
        this.matrice = graph;
    }

    public void setNbrsommets(int [][] graph){
        this.nbrsommets = graph.length;
    }
}
