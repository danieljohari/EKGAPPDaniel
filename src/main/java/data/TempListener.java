package data;

// interface temp simulatoren, med notify() metoden
//notify() metoden trækker en enkelt tråd ud, her er det temp.
public interface TempListener {
    public void notifyTemp(TempDTO temp);
}
