import java.util.List;

public class Formule {

    private int clausesNumber;
    private int variablesNumber;
    private List<List<String>> formule;

    public Formule(int clausesNumber, int variablesNumber, List<List<String>> formule) {
        this.clausesNumber = clausesNumber;
        this.variablesNumber = variablesNumber;
        this.formule = formule;
    }

    public int getClausesNumber() {
        return clausesNumber;
    }

    public int getVariablesNumber() {
        return variablesNumber;
    }

    public List<List<String>> getFormule() {
        return formule;
    }

    public void setClausesNumber(int clausesNumber) {
        this.clausesNumber = clausesNumber;
    }

    public void setVariablesNumber(int variablesNumber) {
        this.variablesNumber = variablesNumber;
    }

    public void setFormule(List<List<String>> formule) {
        this.formule = formule;
    }


    public void printClauses(){
        System.out.println("Votre formule en clauses : ");
        for (List<String> clause : formule)
            System.out.println(clause);
    }




}

