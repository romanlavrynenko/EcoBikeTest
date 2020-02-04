package userInterface;

import entities.EBike;
import entities.EcoBikeEntity;
import entities.FoldingBike;
import entities.Speedelec;
import fileHandler.MyOwnReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleInterface {

    private static MyOwnReader reader;
    private EcoBikeEntity ecoBikeEntity;
    private static ConsoleInterface consoleInterface;

    public ConsoleInterface(MyOwnReader reader) {
        this.reader = reader;
    }

    /**
     * @return entity of ConsoleInterface
     */
    public static ConsoleInterface getConsole() {
        if (consoleInterface == null) {
            consoleInterface = new ConsoleInterface(reader);
        } else return consoleInterface;
        return consoleInterface;
    }

    /**
     * Showing all possibles function in System.out
     * Executing all methods
     */
    public void displayMainMenu() {

        System.out.println("Please make your choice:\n" +
                "1 - Show the entire EcoBike catalog\n" +
                "2 – Add a new folding bike\n" +
                "3 – Add a new speedelec\n" +
                "4 – Add a new e-bike\n" +
                "5 – Find the first item of a particular brand\n" +
                "6 – Write to file\n" +
                "7 – Stop the program\n");

        String pointOfMenu = waitForStringInput();

        if (pointOfMenu.equals("1")) {
            displayEcoBikeCatalog();

        }

        if (pointOfMenu.equals("2")) {
            createNewFoldingBike();

        }

        if (pointOfMenu.equals("3")) {
            createNewSpeedelec();

        }

        if (pointOfMenu.equals("4")) {
            createNewEBike();

        }

        if (pointOfMenu.equals("5")) {
            findFirstBikeOfParticularBrand();

        }

        if (pointOfMenu.equals("6")) {
            saveDataToFile();

        }

        if (pointOfMenu.equals("7")) {
            stopProgram();
            System.exit(0);

        }
    }

    /**
     * Method which waiting for user inputs a String
     *
     * @return String which was inserted by user
     */
    public String waitForStringInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = null;


        try {
            userInput = scanner.nextLine();
            while (userInput.isEmpty()) {
                System.out.println("Cannot be empty");
                userInput = scanner.nextLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return userInput;
    }

    /**
     * Method which waiting for user inputs an Integer
     *
     * @return Integer which was inputed by user
     */
    public Integer waitForIntegerInput(boolean isRequired) {
        Scanner scanner = new Scanner(System.in);
        Integer userInput = 0;

        if (isRequired) {
            do {
                try {

                    userInput = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Please enter an integer");
                }

                scanner.nextLine();

            }
            while (userInput <= 0);
        } else
            do {
                try {

                    userInput = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Please enter an integer");
                }

                scanner.nextLine();

            } while (userInput < 0);


        return userInput;
    }


    /**
     * Converting String from file to Entity
     *
     * @param entity String from ecobike.txt file which equals one line
     * @return EcoBikeEntity of some of 3 type
     */
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

    /**
     * Method which sorting all entities by the brand
     *
     * @return Sorted list of entities
     */
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
        bikesArray.sort(EcoBikeEntity.compareByBrand);


        return bikesArray;
    }

    /**
     * Corresponds to 1 point of display and showing entire catalog
     */
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
        displayMainMenu();
    }

    /**
     * Corresponds to second point of menu
     * Creating new entity of Folding Bike
     */
    public void createNewFoldingBike() {

        FoldingBike foldingBike = new FoldingBike();
        int sizeOfWheels;
        int numberOfGears;
        int weight;
        int price;

        System.out.println("Please enter the brand of the bike");
        String brandName = waitForStringInput();
        foldingBike.setBrand(brandName);


        System.out.println("Please enter the size of wheels (in inch). It can be only positive number");
        sizeOfWheels = waitForIntegerInput(true);
        foldingBike.setSizeOfWheels(sizeOfWheels);


        System.out.println("Please enter the number of gears. It can be only positive number");
        numberOfGears = waitForIntegerInput(true);
        foldingBike.setNumberOfGears(numberOfGears);


        System.out.println("Please enter the weight of bike. It can be only positive number");
        weight = waitForIntegerInput(true);
        foldingBike.setWeight(weight);

        System.out.println("Please enter is lights on bike is available, type 1 if it's available or type 2 if it's not available");
        int isLightsAvailable = waitForIntegerInput(true);
        while (isLightsAvailable != 1 && isLightsAvailable != 2) {
            System.out.println("Please type 1 if lights on bike is available or type 2 if lights on bike is NOT available");
            isLightsAvailable = waitForIntegerInput(true);
        }
        if (isLightsAvailable == 1) {
            foldingBike.setAvailableForLights(true);
        } else {
            foldingBike.setAvailableForLights(false);
        }

        System.out.println("Please enter the color of bike");

        String color = waitForStringInput();
        foldingBike.setColor(color);

        System.out.println("Please enter the price of bike (in euros). It can be only positive number");
        price = waitForIntegerInput(true);
        foldingBike.setPrice(price);
        ecoBikeEntity = foldingBike;
        displayMainMenu();
    }

    /**
     * Corresponds to 3 point of menu
     * Creating new entity of Speedelec
     */
    public void createNewSpeedelec() {
        Scanner scanner = new Scanner(System.in);
        Speedelec speedelec = new Speedelec();
        int maxSpeed;
        int batteryCapacity;
        int weight;
        int price;

        System.out.println("Please enter the brand of the bike");
        String brandName = waitForStringInput();
        speedelec.setBrand(brandName);


        System.out.println("Please enter the maximum speed (in km/h). It can be only positive number");
        maxSpeed = waitForIntegerInput(true);
        speedelec.setMaxSpeed(maxSpeed);


        System.out.println("Please enter the weight of bike. It can be only positive number");
        weight = waitForIntegerInput(true);
        speedelec.setWeight(weight);


        System.out.println("Please enter is lights on bike is available, type 1 if it's available or type 2 if it's not available");
        int isLightsAvailable = waitForIntegerInput(true);
        while (isLightsAvailable != 1 && isLightsAvailable != 2) {
            System.out.println("Please type 1 if lights on bike is available or type 2 if lights on bike is NOT available");
            isLightsAvailable = scanner.nextInt();
        }
        if (isLightsAvailable == 1) {
            speedelec.setAvailableForLights(true);
        } else {
            speedelec.setAvailableForLights(false);
        }

        System.out.println("Please enter the battery capacity. It can be only positive number");
        batteryCapacity = waitForIntegerInput(true);
        speedelec.setBatteryCapacity(batteryCapacity);

        System.out.println("Please enter the color of bike");

        String color = waitForStringInput();
        speedelec.setColor(color);

        System.out.println("Please enter the price of bike (in euros). It can be only positive number");
        price = waitForIntegerInput(true);
        speedelec.setPrice(price);

        ecoBikeEntity = speedelec;
        displayMainMenu();

    }

    /**
     * Corresponds to 4 point of menu
     * Creating new entity of E-Bike
     */
    public void createNewEBike() {
        Scanner scanner = new Scanner(System.in);
        EBike eBike = new EBike();
        int maxSpeed;
        int batteryCapacity;
        int weight;
        int price;

        System.out.println("Please enter the brand of the bike");
        String brandName = waitForStringInput();
        eBike.setBrand(brandName);


        System.out.println("Please enter the maximum speed (in km/h). It can be only positive number");
        maxSpeed = waitForIntegerInput(true);
        eBike.setMaxSpeed(maxSpeed);


        System.out.println("Please enter the weight of bike. It can be only positive number");
        weight = waitForIntegerInput(true);
        eBike.setWeight(weight);


        System.out.println("Please enter is lights on bike is available, type 1 if it's available or type 2 if it's not available");
        int isLightsAvailable = waitForIntegerInput(true);
        while (isLightsAvailable != 1 && isLightsAvailable != 2) {
            System.out.println("Please type 1 if lights on bike is available or type 2 if lights on bike is NOT available");
            isLightsAvailable = scanner.nextInt();
        }
        if (isLightsAvailable == 1) {
            eBike.setAvailableForLights(true);
        } else {
            eBike.setAvailableForLights(false);
        }

        System.out.println("Please enter the battery capacity. It can be only positive number");

        batteryCapacity = waitForIntegerInput(true);
        eBike.setBatteryCapacity(batteryCapacity);


        System.out.println("Please enter the color of bike");

        String color = waitForStringInput();
        eBike.setColor(color);

        System.out.println("Please enter the price of bike (in euros). It can be only positive number");
        price = waitForIntegerInput(true);
        eBike.setPrice(price);

        ecoBikeEntity = eBike;

        displayMainMenu();

    }

    /**
     * Corresponds to 5 point of menu
     * Find the first entity of particular brand in according to searching filters
     */
    public void findFirstBikeOfParticularBrand() {
        SortingThread thread = new SortingThread();
        thread.start();

        List<? extends EcoBikeEntity> bikesArray = getSortedArrayOfBikes();
        EcoBikeEntity ecoBikeEntity;
        int typeOfBike;


        System.out.println("Please select the type of bike:\n" +
                "1 - E-Bike\n" +
                "2 - Folding Bike\n" +
                "3 - Speedelec\n");
        typeOfBike = waitForIntegerInput(true);
        if (typeOfBike == 1) {
            ecoBikeEntity = new EBike();
            int maxSpeed;
            int weight;
            int isAvailableForLights;
            int batteryCapacity;
            int price;

            System.out.println("Please enter the brand of a bike");

            String brand = waitForStringInput();
            ecoBikeEntity.setBrand(brand);

            System.out.println("Please enter the maximum speed of a bike. Can be only a positive number\n"
                    + "Type 0 if u want to skip this parameter");

            maxSpeed = waitForIntegerInput(false);
            if (maxSpeed != 0)
                ((EBike) ecoBikeEntity).setMaxSpeed(maxSpeed);
            else
                ((EBike) ecoBikeEntity).setMaxSpeed(null);


            System.out.println("Please enter the weight of a bike. Can be only a positive number\n"

                    + "Type 0 if u want to skip this parameter");

            weight = waitForIntegerInput(false);
            if (weight != 0)
                ecoBikeEntity.setWeight(weight);
            else
                ecoBikeEntity.setWeight(null);


            System.out.println("Please enter is lights available on bike"
                    + "type 1 if it's available\n"
                    + "type 2 if it's not available\n"
                    + "type 0 if you want to skip this parameter");

            isAvailableForLights = waitForIntegerInput(false);
            if (isAvailableForLights == 1)
                ecoBikeEntity.setAvailableForLights(true);
            else if (isAvailableForLights == 2)
                ecoBikeEntity.setAvailableForLights(false);
            else if (isAvailableForLights == 0)
                ecoBikeEntity.setAvailableForLights(null);


            System.out.println("Please enter the battery capacity. Can be only a positive number\n"
                    + "Type 0 if u want to skip this parameter");

            batteryCapacity = waitForIntegerInput(false);
            if (batteryCapacity != 0)
                ((EBike) ecoBikeEntity).setBatteryCapacity(batteryCapacity);
            else
                ((EBike) ecoBikeEntity).setBatteryCapacity(null);


            System.out.println("Please enter the color\n"
                    + "Type 0 if u want to skip this parameter");

            String color = waitForStringInput();
            if (!color.equals("0"))
                ecoBikeEntity.setColor(color);
            else
                ecoBikeEntity.setColor(null);

            System.out.println("Please enter the price of a bike. Can be only a positive number\n"
                    + "Type 0 if u want to skip this parameter");

            price = waitForIntegerInput(false);
            if (price != 0)
                ecoBikeEntity.setPrice(price);
            else
                ecoBikeEntity.setPrice(null);


            if (thread.isAlive()) {
                try {
                    System.out.println("Waiting while bikes are sorting");

                    thread.join();

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            EBike finalEcoBikeEntity = (EBike) ecoBikeEntity;
            bikesArray = bikesArray.stream().filter(b -> b.getBrand().
                    equals(finalEcoBikeEntity.getBrand())).
                    collect(Collectors.toList());

            if (finalEcoBikeEntity.getMaxSpeed() != null) {
                bikesArray = bikesArray.stream().filter(b -> ((EBike) b).getMaxSpeed().
                        equals(finalEcoBikeEntity.getMaxSpeed())).
                        collect(Collectors.toList());
            }
            if (finalEcoBikeEntity.getWeight() != null) {
                bikesArray = bikesArray.stream().filter(b -> b.getWeight().
                        equals(finalEcoBikeEntity.getWeight())).
                        collect(Collectors.toList());
            }
            if (finalEcoBikeEntity.isAvailableForLights() != null) {
                bikesArray = bikesArray.stream().filter(b -> b.isAvailableForLights().
                        equals(finalEcoBikeEntity.isAvailableForLights())).
                        collect(Collectors.toList());
            }
            if (finalEcoBikeEntity.getBatteryCapacity() != null) {
                bikesArray = bikesArray.stream().filter(b -> ((EBike) b).getBatteryCapacity().
                        equals(finalEcoBikeEntity.getBatteryCapacity())).
                        collect(Collectors.toList());
            }
            if (finalEcoBikeEntity.getColor() != null) {
                bikesArray = bikesArray.stream().filter(b -> b.getColor().
                        equals(finalEcoBikeEntity.getColor())).
                        collect(Collectors.toList());
            }
            if (finalEcoBikeEntity.getPrice() != null) {
                bikesArray = bikesArray.stream().filter(b -> b.getPrice().
                        equals(finalEcoBikeEntity.getPrice())).
                        collect(Collectors.toList());
            }
            boolean isPresent = bikesArray.stream().findFirst().isPresent();

            if (isPresent) {
                System.out.println(bikesArray.stream().findFirst().get());
            } else {
                System.out.println("No bikes found by the provided parameters");
            }


        } else if (typeOfBike == 2) {
            ecoBikeEntity = new FoldingBike();
            int sizeOfWheels;
            int weight;
            int isAvailableForLights;
            int numberOfGears;
            int price;

            System.out.println("Please enter the brand of a bike");

            String brand = waitForStringInput();
            ecoBikeEntity.setBrand(brand);

            System.out.println("Please enter the size of wheels. Can be only a positive number\n"
                    + "Type 0 if u want to skip this parameter");

            sizeOfWheels = waitForIntegerInput(false);
            if (sizeOfWheels != 0)
                ((FoldingBike) ecoBikeEntity).setSizeOfWheels(sizeOfWheels);
            else
                ((FoldingBike) ecoBikeEntity).setSizeOfWheels(null);


            System.out.println("Please enter the number of gears capacity. Can be only a positive number\n"
                    + "Type 0 if u want to skip this parameter");

            numberOfGears = waitForIntegerInput(false);
            if (numberOfGears != 0)
                ((FoldingBike) ecoBikeEntity).setNumberOfGears(numberOfGears);
            else
                ((FoldingBike) ecoBikeEntity).setNumberOfGears(null);


            System.out.println("Please enter the weight of a bike. Can be only a positive number\n"

                    + "Type 0 if u want to skip this parameter");

            weight = waitForIntegerInput(false);
            if (weight != 0)
                ecoBikeEntity.setWeight(weight);
            else
                ecoBikeEntity.setWeight(null);

            System.out.println("Please enter is lights available on bike"
                    + "type 1 if it's available\n"
                    + "type 2 if it's not available\n"
                    + "type 0 if you want to skip this parameter");

            isAvailableForLights = waitForIntegerInput(false);
            if (isAvailableForLights == 1)
                ecoBikeEntity.setAvailableForLights(true);
            else if (isAvailableForLights == 2)
                ecoBikeEntity.setAvailableForLights(false);
            else if (isAvailableForLights == 0)
                ecoBikeEntity.setAvailableForLights(null);


            System.out.println("Please enter the color\n"
                    + "Type 0 if u want to skip this parameter");

            String color = waitForStringInput();
            if (!color.equals("0"))
                ecoBikeEntity.setColor(color);
            else
                ecoBikeEntity.setColor(null);

            System.out.println("Please enter the price of a bike. Can be only a positive number\n"
                    + "Type 0 if u want to skip this parameter");

            price = waitForIntegerInput(false);
            if (price != 0)
                ecoBikeEntity.setPrice(price);
            else
                ecoBikeEntity.setPrice(null);


            if (!thread.isInterrupted()) {

                try {
                    System.out.println("Waiting while bikes are sorting");
                    thread.join();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            FoldingBike finalEcoBikeEntity = (FoldingBike) ecoBikeEntity;
            bikesArray = bikesArray.stream().filter(b -> b.getBrand().
                    equals(finalEcoBikeEntity.getBrand())).
                    collect(Collectors.toList());

            if (finalEcoBikeEntity.getSizeOfWheels() != null) {
                bikesArray = bikesArray.stream().filter(b -> ((FoldingBike) b).getSizeOfWheels().
                        equals(finalEcoBikeEntity.getSizeOfWheels())).
                        collect(Collectors.toList());

            }
            if (finalEcoBikeEntity.getNumberOfGears() != null) {
                bikesArray = bikesArray.stream().filter(b -> ((FoldingBike) b).getNumberOfGears().
                        equals(finalEcoBikeEntity.getNumberOfGears())).
                        collect(Collectors.toList());
            }
            if (finalEcoBikeEntity.getWeight() != null) {
                bikesArray = bikesArray.stream().filter(b -> b.getWeight().
                        equals(finalEcoBikeEntity.getWeight())).
                        collect(Collectors.toList());
            }
            if (finalEcoBikeEntity.isAvailableForLights() != null) {
                bikesArray = bikesArray.stream().filter(b -> b.isAvailableForLights().
                        equals(finalEcoBikeEntity.isAvailableForLights())).
                        collect(Collectors.toList());
            }

            if (finalEcoBikeEntity.getColor() != null) {
                bikesArray = bikesArray.stream().filter(b -> b.getColor().
                        equals(finalEcoBikeEntity.getColor())).
                        collect(Collectors.toList());
            }
            if (finalEcoBikeEntity.getPrice() != null) {
                bikesArray = bikesArray.stream().filter(b -> b.getPrice().
                        equals(finalEcoBikeEntity.getPrice())).
                        collect(Collectors.toList());
            }
            boolean isPresent = bikesArray.stream().findFirst().isPresent();
            if (isPresent) {
                System.out.println(bikesArray.stream().findFirst().get());
            } else {
                System.out.println("No bikes found by the provided parameters");
            }


        } else if (typeOfBike == 3) {
            ecoBikeEntity = new Speedelec();
            int maxSpeed;
            int weight;
            int isAvailableForLights;
            int batteryCapacity;
            int price;

            System.out.println("Please enter the brand of a bike");

            String brand = waitForStringInput();
            ecoBikeEntity.setBrand(brand);

            System.out.println("Please enter the maximum speed of a bike. Can be only a positive number\n"
                    + "Type 0 if u want to skip this parameter");

            maxSpeed = waitForIntegerInput(false);
            if (maxSpeed != 0)
                ((Speedelec) ecoBikeEntity).setMaxSpeed(maxSpeed);
            else
                ((Speedelec) ecoBikeEntity).setMaxSpeed(null);


            System.out.println("Please enter the weight of a bike. Can be only a positive number\n"

                    + "Type 0 if u want to skip this parameter");

            weight = waitForIntegerInput(false);
            if (weight != 0)
                ecoBikeEntity.setWeight(weight);
            else
                ecoBikeEntity.setWeight(null);


            System.out.println("Please enter is lights available on bike"
                    + "type 1 if it's available\n"
                    + "type 2 if it's not available\n"
                    + "type 0 if you want to skip this parameter");

            isAvailableForLights = waitForIntegerInput(false);
            if (isAvailableForLights == 1)
                ecoBikeEntity.setAvailableForLights(true);
            else if (isAvailableForLights == 2)
                ecoBikeEntity.setAvailableForLights(false);
            else if (isAvailableForLights == 0)
                ecoBikeEntity.setAvailableForLights(null);


            System.out.println("Please enter the battery capacity. Can be only a positive number\n"
                    + "Type 0 if u want to skip this parameter");

            batteryCapacity = waitForIntegerInput(false);
            if (batteryCapacity != 0)
                ((Speedelec) ecoBikeEntity).setBatteryCapacity(batteryCapacity);
            else
                ((Speedelec) ecoBikeEntity).setBatteryCapacity(null);


            System.out.println("Please enter the color\n"
                    + "Type 0 if u want to skip this parameter");

            String color = waitForStringInput();
            if (!color.equals("0"))
                ecoBikeEntity.setColor(color);
            else
                ecoBikeEntity.setColor(null);

            System.out.println("Please enter the price of a bike. Can be only a positive number\n"
                    + "Type 0 if u want to skip this parameter");

            price = waitForIntegerInput(false);
            if (price != 0)
                ecoBikeEntity.setPrice(price);
            else
                ecoBikeEntity.setPrice(null);


            if (!thread.isInterrupted()) {

                try {
                    System.out.println("Waiting while bikes are sorting");
                    thread.join();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            Speedelec finalEcoBikeEntity = (Speedelec) ecoBikeEntity;
            bikesArray = bikesArray.stream().filter(b -> b.getBrand().
                    equals(finalEcoBikeEntity.getBrand())).
                    collect(Collectors.toList());

            if (finalEcoBikeEntity.getMaxSpeed() != null) {
                bikesArray = bikesArray.stream().filter(b -> ((Speedelec) b).getMaxSpeed().
                        equals(finalEcoBikeEntity.getMaxSpeed())).
                        collect(Collectors.toList());
            }
            if (finalEcoBikeEntity.getWeight() != null) {
                bikesArray = bikesArray.stream().filter(b -> b.getWeight().
                        equals(finalEcoBikeEntity.getWeight())).
                        collect(Collectors.toList());
            }
            if (finalEcoBikeEntity.isAvailableForLights() != null) {
                bikesArray = bikesArray.stream().filter(b -> b.isAvailableForLights().
                        equals(finalEcoBikeEntity.isAvailableForLights())).
                        collect(Collectors.toList());
            }
            if (finalEcoBikeEntity.getBatteryCapacity() != null) {
                bikesArray = bikesArray.stream().filter(b -> ((Speedelec) b).getBatteryCapacity().
                        equals(finalEcoBikeEntity.getBatteryCapacity())).
                        collect(Collectors.toList());
            }
            if (finalEcoBikeEntity.getColor() != null) {
                bikesArray = bikesArray.stream().filter(b -> b.getColor().
                        equals(finalEcoBikeEntity.getColor())).
                        collect(Collectors.toList());
            }
            if (finalEcoBikeEntity.getPrice() != null) {
                bikesArray = bikesArray.stream().filter(b -> b.getPrice().
                        equals(finalEcoBikeEntity.getPrice())).
                        collect(Collectors.toList());
            }
            boolean isPresent = bikesArray.stream().findFirst().isPresent();
            System.out.println(isPresent);
            if (isPresent) {
                System.out.println(bikesArray.stream().findFirst().get());
            } else {
                System.out.println("No bikes found by the provided parameters");
            }

        }


        displayMainMenu();
    }

    /**
     * Corresponds to 6 point of menu
     * Save inserted from point 2, 3 or 4 to file
     */
    public void saveDataToFile() {

        try {
            File file = new File(String.valueOf(reader.getFileName()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            if (reader.getFileContents().contains(ecoBikeEntity.toString())) {
                System.out.println("Provided bike is already exists");
                ecoBikeEntity = null;
            } else {
                writer.write(ecoBikeEntity.toString());
                writer.newLine();
                writer.close();
                System.out.println("Bike has been successfully added\n");
                ecoBikeEntity = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            displayMainMenu();
        }
    }

    /**
     * Method which asks about needing of saving unsaved data
     */
    public void stopProgram() {

        if (ecoBikeEntity != null) {
            System.out.println("Warning, you have unsaved data\n" + "Do you want to save unsaved data? (y/n)");

            String isSave = waitForStringInput();
            while (true) {
                if (isSave.equals("y") || isSave.equals("Y")) {
                    saveDataToFile();
                    break;


                } else if (isSave.equals("n") || isSave.equals("N")) {
                    break;

                } else {
                    System.out.println("Please enter y or n");
                    isSave = waitForStringInput();
                }
            }
        }

    }
}
