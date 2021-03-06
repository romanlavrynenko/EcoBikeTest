package fileHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyOwnReader {
    private File file;

    public MyOwnReader(File file) {
        this.file = file;
    }

    public MyOwnReader() {
    }

    public File getFileName() {
        return file.getAbsoluteFile();
    }

    public List<String> getFileContents() throws IOException {
        List<String> array = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
            while (reader.ready())
                array.add(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return array;
    }

    public void getFileNameFromUser() {
        Scanner user = new Scanner(System.in);

        System.out.println("Please enter filename");
        String fileName = user.nextLine().trim();
        File userFile = new File(fileName);

        while (!userFile.exists()) {
            System.out.println("Invalid file name, please enter correct file name");
            userFile = new File(user.nextLine().trim());
        }
        file = userFile;

    }

}
