
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{

    public static void main(String[] args) throws IOException {

        String sat1 =  "Mini-Projet1/src/sat.txt";
        String sol1 =  "Mini-Projet1/src/sol.txt";

        BufferedReader bufferedReader1;
        BufferedReader bufferedReader2;

        bufferedReader1 = new BufferedReader(new FileReader(sat1));
        bufferedReader2 = new BufferedReader(new FileReader(sol1));
        String line = bufferedReader1.readLine();


        String[] premiereLigne = line.split(" ");

        if (premiereLigne.length != 4) {
            System.out.println("Format Dimac non réspcté");
            System.exit(1);
        }

        int clausesNumber;
        int variablesNumber;
        List<List<String>> clauses;

        variablesNumber = Integer.parseInt(premiereLigne[2]);
        clausesNumber = Integer.parseInt(premiereLigne[3]);

        clauses = new ArrayList<>();
        line = bufferedReader1.readLine();

        while (line != null) {
            String[] clausess = line.split(" ");
            List<String> litt = new ArrayList<>();
            for (String c : clausess) {
                if (!c.equals("0"))
                    litt.add(c);
            }
            clauses.add(litt);

            line = bufferedReader1.readLine();
        }
        Formule formule = new Formule(clausesNumber,variablesNumber,clauses);

        System.out.print("La Valution de ");
        formule.printClauses();
        bufferedReader1.close();


        List<String> affectations;
        String[] line1 = bufferedReader2.readLine().split(" ");
        affectations = new ArrayList<>();
        affectations.addAll(Arrays.asList(line1));
        System.out.println("En utilisant les valeurs " + affectations);
        bufferedReader2.close();


        ///////////////Valuation de la formule en question ///////////////////

        boolean[] valuation_table = new boolean[formule.getClausesNumber()];
        int compt = 0;
        for (List<String> clause : formule.getFormule()) {
            for (String value : clause) {
                for (String affectValue : affectations) {
                    if (value.equals(affectValue)) {
                        valuation_table[compt] = true;
                        compt++;
                        break;
                    }
                    if (valuation_table[compt] || !valuation_table[compt]) {
                        break;
                    }
                }
            }
        }

        boolean solution = false;

        for (boolean bool : valuation_table) {
            if (bool) {
                solution = true;
                break;
            }
        }




        System.out.println("EST  :  " + solution);









    }

}

