package ca.mcmaster.se2aa4.mazerunner;


public class ExplorerLogger implements ExplorerObserver {
    @Override
    public void onStep(String action, int x, int y) {
        System.out.println("Action: " + action + " → Position: (" + x + ", " + y + ")");
    }
}
