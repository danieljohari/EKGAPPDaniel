package data;

// Skal bruges til at sample data
// Et hvert objekt der ønsker at blive underrettet
//Når tilstanden af et andet objekt ændres.
public interface BpmSampler { //Observer
    void register(BpmListener bpmListener);
}
//Hver gang der bliver ændret i stadiet af