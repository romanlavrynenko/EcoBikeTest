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
    public static void init(){
        MyOwnReader reader = new MyOwnReader();
        Scanner user = new Scanner(System.in);
        System.out.println("Please enter filename");
        String fileName = user.nextLine().trim();
        File file = new File(fileName);
        reader.getFileNameFromUser(file);
        ConsoleInterface consoleInterface = new ConsoleInterface(reader);
        consoleInterface.displayMainMenu();
    }
}





