package userInterface;

import fileHandler.MyOwnReader;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleInterfaceTest {
    private static MyOwnReader reader = new MyOwnReader(new File("D:\\ecobike\\ecobike.txt"));
    private ConsoleInterface consoleInterface = new ConsoleInterface(reader);





    @Test
    void waitForStringInput() {
        String data = "testString";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals("testString", consoleInterface.waitForStringInput(), "String should be same");
        System.setIn(stdin);

    }


}