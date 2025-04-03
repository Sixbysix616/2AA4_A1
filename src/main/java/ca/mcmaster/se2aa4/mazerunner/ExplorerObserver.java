package ca.mcmaster.se2aa4.mazerunner;

public interface ExplorerObserver {
    void onStep(String action, int x, int y);
}
