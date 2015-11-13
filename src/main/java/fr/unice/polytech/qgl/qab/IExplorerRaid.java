package fr.unice.polytech.qgl.qab;

interface IExplorerRaid{
    public void initialize(String context);


    public String takeDecision();


    public void acknowledgeResults(String results);
}