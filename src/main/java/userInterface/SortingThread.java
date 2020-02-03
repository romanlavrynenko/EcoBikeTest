package userInterface;

import entities.EBike;
import entities.EcoBikeEntity;
import entities.FoldingBike;
import entities.Speedelec;

import java.util.*;
import java.util.stream.Collectors;

public class SortingThread extends Thread {
    private String search;
    private ArrayList<EcoBikeEntity> bikes;
    private static SortingThread sortingThread;
    private static ConsoleInterface consoleInterface;

    public SortingThread() {
        SortingThread.consoleInterface = ConsoleInterface.getConsole();
    }

    public  static SortingThread getThread(){
        if (sortingThread ==null)
            sortingThread = new SortingThread();
        else return sortingThread;
        return sortingThread;
    }

    public void run() {
        consoleInterface.getSortedArrayOfBikes();


    }

}