

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Conversion {
    Graphe graphe;
    int k;
    int taille;
    List<List<String>> cnf = new ArrayList<>();

    public Conversion(Graphe graphe, int k) {
        this.graphe = graphe;
        this.k=k;
        this.taille=graphe.matrice.length;
    }

    public void generationDuCNF(){
        laCliqueMinKSommets();
        pasDeDoublons();
        CliqueCompletementConnectee();
        remplirList();

    }

    private void remplirList() {
        File file= new File("CNF.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
            bufferedWriter.write("p cnf "+(int)taille*taille+" "+(cnf.size())+"\n");
            int i=0;
            while (i<cnf.size()){
                bufferedWriter.write(listToString(cnf.get(i)));
                bufferedWriter.write(" 0\n");
                i++;
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String listToString(List<String> s) {
        StringBuilder sBuilder = new StringBuilder();
        for (int i=0;i<s.size();i++){
            sBuilder.append(s.get(i));
            if (i<s.size()-1){
                sBuilder.append(" ");
            }
        }
        return sBuilder.toString();
    }

    public void laCliqueMinKSommets() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < taille; j++) {
                    list.add(transformation(i, j, taille, true));
                }
                cnf.add(copie(list));
                list.clear();
            }
        }

    private void pasDeDoublons() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= k; j++) {
                int k = 0;
                while( k < taille) {
                        if (j != i) {
                            list.add(transformation(i, k, taille, false));
                            list.add(transformation(j, k, taille, false));
                            cnf.add(copie(list));
                            list.clear();
                        }
                        k++;
                    }
                }
            }
        }
    private void CliqueCompletementConnectee() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= k; j++) {
                int k = 0;
                while ( k < taille) {
                    int l = 0;
                    while (l < taille) {
                        if (j != i && k != l) {
                            if (graphe.matrice[l][k] == 0) {
                                list.add(transformation(i, k, taille, false));
                                list.add(transformation(j, l, taille, false));
                                cnf.add(copie(list));
                                list.clear();
                            }
                        }
                        l++;
                    }
                    k++;
                }
            }
        }
    }



    public static String transformation(int a, int b, int n, boolean bool) {
        String s = String.valueOf((int)(( (a) * Math.pow(n, 1) + (b) * Math.pow(n, 0)) +1 ));
        if (!bool) {
            s = "-" + s;
        }
        return s;
    }

    public List copie(List liste) {
        List liste1 = new ArrayList();
        liste1.addAll(liste);
        return liste1;
    }

}
