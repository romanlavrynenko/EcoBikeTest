package fileHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyOwnReader {
    private BufferedReader reader;
    private static File file;


    public File getFileName() {
        return file.getAbsoluteFile();
    }

    public List<String> getFileContents() throws IOException {
        List<String> array = new ArrayList<>();
        try {
            while (reader.ready())
                array.add(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //reader.close();
        return array;
    }

    public void getFileNameFromUser(File userFile) {
        Scanner user = new Scanner(System.in);

        while (!userFile.exists()) {
            System.out.println("Invalid file name, please enter correct file name");
            userFile = new File(user.nextLine().trim());

        }
        file = userFile;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
