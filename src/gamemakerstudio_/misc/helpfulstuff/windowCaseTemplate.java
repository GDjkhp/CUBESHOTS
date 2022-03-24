package gamemakerstudio_.misc.helpfulstuff;

public class windowCaseTemplate {
    public static void main(String[] args) {
        int var = 500;

        for (; var <= 1000; var++){
            System.out.println("case " + var + ":");
            System.out.println("\treturn \"\";");
        }
    }
}
