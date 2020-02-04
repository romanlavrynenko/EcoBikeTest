package userInterface;

import entities.EBike;
import entities.EcoBikeEntity;
import entities.FoldingBike;
import entities.Speedelec;

import java.util.*;
import java.util.stream.Collectors;

public class SortingThread extends Thread {
    private static ConsoleInterface consoleInterface;

    public SortingThread() {
        SortingThread.consoleInterface = ConsoleInterface.getConsole();
    }



    public void run() {

        consoleInterface.getSortedArrayOfBikes();


    }

}