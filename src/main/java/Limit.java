import java.util.Scanner;

public class Limit {


    // Scanner keyboard = bruger indtaster med given værdi fra keyboard

    //Hvad gør de tre metoder, vi har? Den konfigurerer programmet fra start af.
    Scanner keyboard = new Scanner(System.in);


    // Fra Scanner keyboard indsættes kritisk temp værdi i terminal
    public double askForUrgentTemp() {
        System.out.println("Indtast kritisk temperatur:");
        try {
            double urgentTemp = keyboard.nextDouble();
            System.out.println("Brugeren indtastede: " + urgentTemp);
            return urgentTemp; //Hvis der ikke bliver returneret en værdi, bliver der returnet 0.0
            //Kan ændres til et gennemsnits værdi
        } catch (Exception e) {
            System.out.println("Skal være tal!");
        } return 0.0;

    }
    public double askForUrgentBPM() {
        System.out.println("Indtast Kritsk Puls");
        try {
            double urgentBPM = keyboard.nextDouble();
            System.out.println("Brugeren indtastede: " + urgentBPM);
            return urgentBPM;
        } catch (Exception e) {
            System.out.println("Skal være tal!");
        } return 0.0;
    }

    public double askForUrgentSPO2() {
        System.out.println("Indtast kritisk iltmætning");
        try {
            double urgentSPO2 = keyboard.nextDouble();
            System.out.println("Brugeren indtastede: " + urgentSPO2);
            return urgentSPO2;
        } catch (Exception e){
            System.out.println("Skal være tal!");
        } return 0.0;
    }


    }



