//package userInterface;
//
//import fileHandler.MyOwnReader;
//import org.junit.jupiter.api.Test;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//class ConsoleInterfaceTest {
//
//    private static MyOwnReader reader = new MyOwnReader(new File("D:\\ecobike\\ecobike.txt"));
//    ConsoleInterface consoleInterface = new ConsoleInterface(reader);
//
//
//
//    @Test
//    void getConsole() {
//        assertNotEquals(consoleInterface, ConsoleInterface.getConsole(),"Console interfaces should not be the same");
//    }
//
//
//
//
//    @Test
//    public void display(){
//        String data = "1";
//        String exit = "7";
//        InputStream stdin = System.in;
//        //ByteArrayOutputStream output = new ByteArrayOutputStream(new PrintStream(System.out));
//        try {
//
//            System.setIn(new ByteArrayInputStream(data.getBytes()));
//            consoleInterface.displayMainMenu();
//            System.setIn(new ByteArrayInputStream(exit.getBytes()));
//            Scanner scanner = new Scanner(System.in);
//
//            System.out.println(scanner.nextLine());
//            //
//
//
////            assertEquals("Please make your choice:\n" +
////                    "1 - Show the entire EcoBike catalog\n" +
////                    "2 – Add a new folding bike\n" +
////                    "3 – Add a new speedelec\n" +
////                    "4 – Add a new e-bike\n" +
////                    "5 – Find the first item of a particular brand\n" +
////                    "6 – Write to file\n" +
////                    "7 – Stop the program\n", );
////            Scanner scanner = new Scanner(System.in);
////            System.out.println(scanner.nextLine());
//
//        } finally {
//            System.setIn(stdin);
//        }
//    }
//
//    @Test
//    void convertFileContentToEntity() {
//
//    }
//
//    @Test
//    void getSortedArrayOfBikes() {
//        assertNotNull(consoleInterface.getSortedArrayOfBikes());
//        assertEquals(consoleInterface.getSortedArrayOfBikes().getClass(), ArrayList.class);
//        assertNotEquals(consoleInterface.getSortedArrayOfBikes().size(),0);
//
//    }
//
//    @Test
//    void displayEcoBikeCatalog() {
//    }
//
//    @Test
//    void createNewFoldingBike() {
//    }
//
//    @Test
//    void createNewSpeedelec() {
//    }
//
//    @Test
//    void createNewEBike() {
//    }
//
//    @Test
//    void findFirstBikeOfParticularBrand() {
//    }
//
//    @Test
//    void saveDataToFile() {
//    }
//
//    @Test
//    void stopProgram() {
//    }
//}