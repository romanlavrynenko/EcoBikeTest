package main;

import fileHandler.MyOwnReader;
import userInterface.ConsoleInterface;

import java.io.File;
import java.util.*;
//import reader.MyOwnReader;


public class Main {
    public static void main(String[] args) {
        init();
    }

    public static void init() {
        MyOwnReader reader = new MyOwnReader();
        reader.getFileNameFromUser();
        ConsoleInterface consoleInterface = new ConsoleInterface(reader);
        consoleInterface.displayMainMenu();
    }
}





