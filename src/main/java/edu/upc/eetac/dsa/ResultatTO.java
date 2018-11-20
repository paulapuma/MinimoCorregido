package edu.upc.eetac.dsa;

public class ResultatTO {
    public int getNumStations() {
        return numStations;
    }

    public void setNumStations(int numStations) {
        this.numStations = numStations;
    }

    private int numStations;

    public ResultatTO() {

    }

    public ResultatTO( int numStations){
        this.numStations =numStations;
    }
}
