

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
        File file= new File("graphCNF");
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

    private String listToString(List<String> strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<strings.size();i++){
            stringBuilder.append(strings.get(i));
            if (i<strings.size()-1){
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    public void laCliqueMinKSommets() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            for (int v = 0; v < taille; v++) {
                    list.add(transformation(i, v, taille, true));
                }
                cnf.add(copie(list));
                list.clear();
            }
        }

    private void pasDeDoublons() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= k; j++) {
                for (int k = 0; k < taille; k++) {
                        if (j != i) {
                            list.add(transformation(i, k, taille, false));
                            list.add(transformation(j, k, taille, false));
                            cnf.add(copie(list));
                            list.clear();
                        }
                    }
                }
            }
        }
    private void CliqueCompletementConnectee() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= k; j++) {
                for (int k = 0; k < taille; k++) {
                    for (int l = 0; l < taille; l++) {
                        if (j != i && k != l) {
                            if (graphe.matrice[l][k] == 0) {
                                list.add(transformation(i, k, taille, false));
                                list.add(transformation(j, l, taille, false));
                                cnf.add(copie(list));
                                list.clear();
                            }
                        }
                    }
                }
            }
        }
    }



    public static String transformation(int i, int v, int n, boolean bool) {
        String string = String.valueOf((int)(( (i) * Math.pow(n, 1) + (v) * Math.pow(n, 0)) +1 ));
        if (!bool) {
            string = "-" + string;
        }
        return string;
    }

    public List copie(List liste) {
        List liste1 = new ArrayList();
        liste1.addAll(liste);
        return liste1;
    }


}
