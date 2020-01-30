package userInterface;

import entities.EBike;
import entities.EcoBikeEntity;
import entities.FoldingBike;
import entities.Speedelec;
import fileHandler.MyOwnReader;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleInterface {


    private static MyOwnReader reader;
    private EcoBikeEntity ecoBikeEntity;
    private static ConsoleInterface consoleInterface;

    public ConsoleInterface(MyOwnReader reader) {
        this.reader = reader;
    }


    public static ConsoleInterface getConsole(){
        if (consoleInterface==null){
            consoleInterface = new ConsoleInterface(reader);
        }
        else return consoleInterface;
        return consoleInterface;
    }
    public void display() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please make your choice:\n" +
                "1 - Show the entire EcoBike catalog\n" +
                "2 – Add a new folding bike\n" +
                "3 – Add a new speedelec\n" +
                "4 – Add a new e-bike\n" +
                "5 – Find the first item of a particular brand\n" +
                "6 – Write to file\n" +
                "7 – Stop the program\n");

        String string;
        while (true) {

            string = scanner.nextLine();
            if (string.equals("1"))
                displayEcoBikeCatalog();

            if (string.equals("2"))
                createNewFoldingBike();

            if (string.equals("3"))
                createNewSpeedelec();

            if (string.equals("4"))
                createNewEBike();

            if (string.equals("5"))
                findFirstBikeOfParticularBrand();

            if (string.equals("6"))
                saveDataToFile();

            if (string.equals("7")) {
                scanner.close();
                break;
            }
        }

    }

    public <T> T convertFileContentToEntity(String entity) {
        EcoBikeEntity ecoBikeEntity = null;

        if (entity.startsWith("E-BIKE")) {
            ecoBikeEntity = new EBike();
            String ebikeRegex = "E-BIKE ";
            List<String> array = new ArrayList<>();
            array.addAll(Arrays.asList(entity.replaceAll(ebikeRegex, "").split("; ")));
            ecoBikeEntity.setBrand(array.get(0));
            ((EBike) ecoBikeEntity).setMaxSpeed(Integer.parseInt(array.get(1)));
            ecoBikeEntity.setWeight(Integer.parseInt(array.get(2)));
            ecoBikeEntity.setAvailableForLights(Boolean.parseBoolean(array.get(3)));
            ((EBike) ecoBikeEntity).setBatteryCapacity(Integer.parseInt(array.get(4)));
            ecoBikeEntity.setColor(array.get(5));
            ecoBikeEntity.setPrice(Integer.parseInt(array.get(6)));

        } else if (entity.startsWith("SPEEDELEC")) {
            ecoBikeEntity = new Speedelec();
            String ebikeRegex = "SPEEDELEC ";
            List<String> array = new ArrayList<>();
            array.addAll(Arrays.asList(entity.replaceAll(ebikeRegex, "").split("; ")));
            ecoBikeEntity.setBrand(array.get(0));
            ((Speedelec) ecoBikeEntity).setMaxSpeed(Integer.parseInt(array.get(1)));
            ecoBikeEntity.setWeight(Integer.parseInt(array.get(2)));
            ecoBikeEntity.setAvailableForLights(Boolean.parseBoolean(array.get(3)));
            ((Speedelec) ecoBikeEntity).setBatteryCapacity(Integer.parseInt(array.get(4)));
            ecoBikeEntity.setColor(array.get(5));
            ecoBikeEntity.setPrice(Integer.parseInt(array.get(6)));

        } else if (entity.startsWith("FOLDING BIKE")) {
            ecoBikeEntity = new FoldingBike();
            String ebikeRegex = "FOLDING BIKE ";
            List<String> array = new ArrayList<>();
            array.addAll(Arrays.asList(entity.replaceAll(ebikeRegex, "").split("; ")));
            ecoBikeEntity.setBrand(array.get(0));
            ((FoldingBike) ecoBikeEntity).setSizeOfWheels(Integer.parseInt(array.get(1)));
            ((FoldingBike) ecoBikeEntity).setNumberOfGears(Integer.parseInt(array.get(2)));
            ecoBikeEntity.setWeight(Integer.parseInt(array.get(3)));
            ecoBikeEntity.setAvailableForLights(Boolean.parseBoolean(array.get(4)));
            ecoBikeEntity.setColor(array.get(5));
            ecoBikeEntity.setPrice(Integer.parseInt(array.get(6)));
        }

        return (T) ecoBikeEntity;

    }

    public List<EcoBikeEntity> getSortedArrayOfBikes() {
        List<EcoBikeEntity> bikesArray = new ArrayList<>();
        try {
            for (String bike : reader.getFileContents()) {
                EcoBikeEntity ecoBikeEntity = convertFileContentToEntity(bike);
                bikesArray.add(ecoBikeEntity);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(bikesArray, EcoBikeEntity.compareByBrand);
//        for (EcoBikeEntity q:bikesArray) {
//            System.out.println(q.toString());
//
//        }


        return bikesArray;
    }


    public void displayEcoBikeCatalog() {

        try {
            for (String s : reader.getFileContents()) {
                String messageToShow = null;
                EcoBikeEntity ecoBikeEntity = convertFileContentToEntity(s);

                if (ecoBikeEntity instanceof EBike) {

                    if (ecoBikeEntity.isAvailableForLights()) {
                        messageToShow = ((EBike) ecoBikeEntity).getType() + " " + ecoBikeEntity.getBrand() + " with "
                                + ((EBike) ecoBikeEntity).getBatteryCapacity() + " mAh battery " + "and head/tail light.\n"
                                + "Price: " + ecoBikeEntity.getPrice() + " euros";
                    } else {
                        messageToShow = ((EBike) ecoBikeEntity).getType() + " " + ecoBikeEntity.getBrand() + " with "
                                + ((EBike) ecoBikeEntity).getBatteryCapacity() + " mAh battery " + "and no head/tail light.\n"
                                + "Price: " + ecoBikeEntity.getPrice() + " euros";
                    }

                } else if (ecoBikeEntity instanceof Speedelec) {

                    if (ecoBikeEntity.isAvailableForLights()) {
                        messageToShow = ((Speedelec) ecoBikeEntity).getType() + " " + ecoBikeEntity.getBrand() + " with "
                                + ((Speedelec) ecoBikeEntity).getBatteryCapacity() + " mAh battery " + "and head/tail light.\n"
                                + "Price: " + ecoBikeEntity.getPrice() + " euros";
                    } else {
                        messageToShow = ((Speedelec) ecoBikeEntity).getType() + " " + ecoBikeEntity.getBrand() + " with "
                                + ((Speedelec) ecoBikeEntity).getBatteryCapacity() + " mAh battery " + "and no head/tail light.\n"
                                + "Price: " + ecoBikeEntity.getPrice() + " euros";
                    }

                } else if (ecoBikeEntity instanceof FoldingBike) {

                    if (ecoBikeEntity.isAvailableForLights()) {
                        messageToShow = ((FoldingBike) ecoBikeEntity).getType() + " " + ecoBikeEntity.getBrand() + " with "
                                + ((FoldingBike) ecoBikeEntity).getNumberOfGears() + " gear(s) " + "and head/tail light.\n"
                                + "Price: " + ecoBikeEntity.getPrice() + " euros";
                    } else {
                        messageToShow = ((FoldingBike) ecoBikeEntity).getType() + " " + ecoBikeEntity.getBrand() + " with "
                                + ((FoldingBike) ecoBikeEntity).getNumberOfGears() + " gear(s) " + "and no head/tail light.\n"
                                + "Price: " + ecoBikeEntity.getPrice() + " euros";
                    }

                }
                System.out.println(messageToShow);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNewFoldingBike() {
        Scanner scanner = new Scanner(System.in);
        FoldingBike foldingBike = new FoldingBike();
        int sizeOfWheels = 0;
        int numberOfGears = 0;
        int weight = 0;
        int price = 0;

        System.out.println("Please enter the brand of the bike");
        String brandName = null;
        try {
            brandName = scanner.nextLine();
            while (brandName.isEmpty()) {
                System.out.println("Brand cannot be empty");
                brandName = scanner.nextLine();
            }
            foldingBike.setBrand(brandName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Please enter the size of wheels (in inch). It can be only positive number");
        do {
            try {
                sizeOfWheels = scanner.nextInt();
                foldingBike.setSizeOfWheels(sizeOfWheels);

            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
            scanner.nextLine();
        } while (sizeOfWheels <= 0);

        System.out.println("Please enter the number of gears. It can be only positive number");
        do {
            try {

                numberOfGears = scanner.nextInt();
                foldingBike.setNumberOfGears(numberOfGears);
            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
            scanner.nextLine();
        } while (numberOfGears <= 0);

        System.out.println("Please enter the weight of bike. It can be only positive number");
        do {
            try {

                weight = scanner.nextInt();
                foldingBike.setWeight(weight);
            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
            scanner.nextLine();
        } while (weight <= 0);


        System.out.println("Please enter is lights on bike is available, type 1 if it's available or type 2 if it's not available");
        int isLightsAvailable = scanner.nextInt();
        while (isLightsAvailable != 1 && isLightsAvailable != 2) {
            System.out.println("Please type 1 if lights on bike is available or type 2 if lights on bike is NOT available");
            isLightsAvailable = scanner.nextInt();
        }
        if (isLightsAvailable == 1) {
            foldingBike.setAvailableForLights(true);
        } else if (isLightsAvailable == 2) {
            foldingBike.setAvailableForLights(false);
        }

        System.out.println("Please enter the color of bike");
        String color = scanner.nextLine();
        while (color.isEmpty()) {
            System.out.println("Color cannot be empty");
            color = scanner.nextLine();
        }
        foldingBike.setColor(color);

        System.out.println("Please enter the price of bike (in euros). It can be only positive number");
        do {
            try {

                price = scanner.nextInt();
                foldingBike.setPrice(price);
            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
            scanner.nextLine();
        } while (price <= 0);
        ecoBikeEntity = foldingBike;
        display();


    }

    public void createNewSpeedelec() {
        Scanner scanner = new Scanner(System.in);
        Speedelec speedelec = new Speedelec();
        int maxSpeed = 0;
        int batteryCapacity = 0;
        int weight = 0;
        int price = 0;

        System.out.println("Please enter the brand of the bike");
        String brandName = null;
        try {
            brandName = scanner.nextLine();
            while (brandName.isEmpty()) {
                System.out.println("Brand cannot be empty");
                brandName = scanner.nextLine();
            }
            speedelec.setBrand(brandName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Please enter the maximum speed (in km/h). It can be only positive number");
        do {
            try {
                maxSpeed = scanner.nextInt();
                speedelec.setMaxSpeed(maxSpeed);

            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
            scanner.nextLine();
        } while (maxSpeed <= 0);


        System.out.println("Please enter the weight of bike. It can be only positive number");
        do {
            try {

                weight = scanner.nextInt();
                speedelec.setWeight(weight);
            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
            scanner.nextLine();
        } while (weight <= 0);


        System.out.println("Please enter is lights on bike is available, type 1 if it's available or type 2 if it's not available");
        int isLightsAvailable = scanner.nextInt();
        while (isLightsAvailable != 1 && isLightsAvailable != 2) {
            System.out.println("Please type 1 if lights on bike is available or type 2 if lights on bike is NOT available");
            isLightsAvailable = scanner.nextInt();
        }
        if (isLightsAvailable == 1) {
            speedelec.setAvailableForLights(true);
        } else if (isLightsAvailable == 2) {
            speedelec.setAvailableForLights(false);
        }

        System.out.println("Please enter the battery capacity. It can be only positive number");
        do {
            try {

                batteryCapacity = scanner.nextInt();
                speedelec.setBatteryCapacity(batteryCapacity);
            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
            scanner.nextLine();
        } while (batteryCapacity <= 0);

        System.out.println("Please enter the color of bike");
        String color = scanner.nextLine();
        while (color.isEmpty()) {
            System.out.println("Color cannot be empty");
            color = scanner.nextLine();
        }
        speedelec.setColor(color);

        System.out.println("Please enter the price of bike (in euros). It can be only positive number");
        do {
            try {

                price = scanner.nextInt();
                speedelec.setPrice(price);
            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
            scanner.nextLine();
        } while (price <= 0);

        ecoBikeEntity = speedelec;
        display();

    }



    public void createNewEBike() {
        Scanner scanner = new Scanner(System.in);
        EBike eBike = new EBike();
        int maxSpeed = 0;
        int batteryCapacity = 0;
        int weight = 0;
        int price = 0;

        System.out.println("Please enter the brand of the bike");
        String brandName = null;
        try {
            brandName = scanner.nextLine();
            while (brandName.isEmpty()) {
                System.out.println("Brand cannot be empty");
                brandName = scanner.nextLine();
            }
            eBike.setBrand(brandName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Please enter the maximum speed (in km/h). It can be only positive number");
        do {
            try {
                maxSpeed = scanner.nextInt();
                eBike.setMaxSpeed(maxSpeed);

            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
            scanner.nextLine();
        } while (maxSpeed <= 0);


        System.out.println("Please enter the weight of bike. It can be only positive number");
        do {
            try {

                weight = scanner.nextInt();
                eBike.setWeight(weight);
            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
            scanner.nextLine();
        } while (weight <= 0);


        System.out.println("Please enter is lights on bike is available, type 1 if it's available or type 2 if it's not available");
        int isLightsAvailable = scanner.nextInt();
        while (isLightsAvailable != 1 && isLightsAvailable != 2) {
            System.out.println("Please type 1 if lights on bike is available or type 2 if lights on bike is NOT available");
            isLightsAvailable = scanner.nextInt();
        }
        if (isLightsAvailable == 1) {
            eBike.setAvailableForLights(true);
        } else if (isLightsAvailable == 2) {
            eBike.setAvailableForLights(false);
        }

        System.out.println("Please enter the battery capacity. It can be only positive number");
        do {
            try {

                batteryCapacity = scanner.nextInt();
                eBike.setBatteryCapacity(batteryCapacity);
            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
            scanner.nextLine();
        } while (batteryCapacity <= 0);

        System.out.println("Please enter the color of bike");
        String color = scanner.nextLine();
        while (color.isEmpty()) {
            System.out.println("Color cannot be empty");
            color = scanner.nextLine();
        }
        eBike.setColor(color);

        System.out.println("Please enter the price of bike (in euros). It can be only positive number");
        do {
            try {

                price = scanner.nextInt();
                eBike.setPrice(price);
            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
            scanner.nextLine();
        } while (price <= 0);
        ecoBikeEntity = eBike;
        display();

    }

    public void findFirstBikeOfParticularBrand() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);

        EcoBikeEntity ecoBikeEntity;
        int typeOfBike = 0;
        List<? extends EcoBikeEntity> bikesArray = getSortedArrayOfBikes();


        do {
            try {
                System.out.println("Please select the type of bike:\n"+
                        "1 - E-Bike\n"+
                        "2 - Folding Bike\n"+
                        "3 - Speedelec\n");
                typeOfBike = scanner.nextInt();
                if (typeOfBike==1) {


                    System.out.println("Please enter the brand of a bike");
                    ecoBikeEntity = new EBike();
                    String bra = scanner.nextLine();

                    ecoBikeEntity.setBrand(bra);

                    System.out.println("Please enter the maximum speed of a bike");
                    //Integer q = Integer.parseInt(reader.readLine());
                    ((EBike)ecoBikeEntity).setMaxSpeed(Integer.parseInt(reader.readLine()));

                    System.out.println("Please enter the weight of a bike");
                    ecoBikeEntity.setWeight(scanner.nextInt());

                    System.out.println("Please enter is lights available on bike\n"
                            +"type true if it's available\n"
                            +"type false if it's not available");
                    ecoBikeEntity.setAvailableForLights(scanner.nextBoolean());

                    System.out.println("Please enter the battery capacity");
                    ((EBike) ecoBikeEntity).setBatteryCapacity(scanner.nextInt());

                    System.out.println("Please enter the color");
                    String color = scanner.nextLine();
                    String color1 =scanner.nextLine();
                    ecoBikeEntity.setColor(color1);

                    System.out.println("Please enter the price of a bike");
                    ecoBikeEntity.setPrice(scanner.nextInt());

                    EBike finalEcoBikeEntity = (EBike) ecoBikeEntity;
                    bikesArray = bikesArray.stream().filter(b->b.getBrand().
                            equals(finalEcoBikeEntity.getBrand())).
                            collect(Collectors.toList());

                    if (finalEcoBikeEntity.getMaxSpeed()!=null){
                        bikesArray = bikesArray.stream().filter(b->((EBike)b).getMaxSpeed().
                                equals(finalEcoBikeEntity.getMaxSpeed())).
                                collect(Collectors.toList());
                    }
                    if (finalEcoBikeEntity.getWeight()!=null){
                        bikesArray = bikesArray.stream().filter(b->b.getWeight().
                                equals(finalEcoBikeEntity.getWeight())).
                                collect(Collectors.toList());
                    }
                    if (finalEcoBikeEntity.isAvailableForLights()!=null){
                        bikesArray = bikesArray.stream().filter(b->b.isAvailableForLights().
                                equals(finalEcoBikeEntity.isAvailableForLights())).
                                collect(Collectors.toList());
                    }
                    if (finalEcoBikeEntity.getBatteryCapacity()!=null){
                        bikesArray = bikesArray.stream().filter(b -> ((EBike)b).getBatteryCapacity().
                                equals(finalEcoBikeEntity.getBatteryCapacity())).
                                collect(Collectors.toList());
                    }
                    if (finalEcoBikeEntity.getColor()!=null){
                        bikesArray = bikesArray.stream().filter(b -> b.getColor().
                                equals(finalEcoBikeEntity.getColor())).
                                collect(Collectors.toList());
                    }
                    if (finalEcoBikeEntity.getPrice()!=null){
                        bikesArray = bikesArray.stream().filter(b->b.getPrice().
                                equals(finalEcoBikeEntity.getPrice())).
                                collect(Collectors.toList());
                    }
                    System.out.println(bikesArray.stream().findAny().isPresent());
                    System.out.println(bikesArray.stream().findFirst());

                }
                else if (typeOfBike==2) {
                    ecoBikeEntity = new FoldingBike();
                }
                else if (typeOfBike==3){
                    ecoBikeEntity = new Speedelec();
                }
                //typeOfBike = scanner.nextInt();

            } catch (Exception e) {
                System.out.println("EXCEPTION" +
                        "\nPlease enter an integer");
            }
            scanner.nextLine();
        } while (typeOfBike <= 0);

    }

    public void saveDataToFile() {

        try {
            File file = new File(String.valueOf(reader.getFileName()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            if (reader.getFileContents().contains(ecoBikeEntity.toString())) {
                System.out.println("Provided bike is already exists");
            } else {
                writer.write(ecoBikeEntity.toString());
                writer.newLine();
                writer.close();
                System.out.println("Bike has been successfully added\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            display();
        }
    }

}
