package userInterface;

import entities.EBike;
import entities.EcoBikeEntity;
import entities.FoldingBike;
import entities.Speedelec;
import fileHandler.MyOwnReader;

import java.util.*;
import java.util.stream.Collectors;

public class SearchingThread extends Thread {
    private String search;
    private ArrayList<EcoBikeEntity> bikes;
    private static SearchingThread searchingThread;
    private static ConsoleInterface consoleInterface;

    public SearchingThread(ConsoleInterface consoleInterface) {
        SearchingThread.consoleInterface = ConsoleInterface.getConsole();
    }

    public  static SearchingThread getThread(){
        if (searchingThread==null)
            searchingThread = new SearchingThread(consoleInterface);
        else return searchingThread;
        return searchingThread;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        EcoBikeEntity ecoBikeEntity;
        int typeOfBike = 0;
        List<? extends EcoBikeEntity> bikesArray = consoleInterface.getSortedArrayOfBikes();



        //Optional<EcoBikeEntity> ecoBikeEntity1 = (Optional<EcoBikeEntity>) bikesArray.stream().findFirst();

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
                    ((EBike)ecoBikeEntity).setMaxSpeed(scanner.nextInt());

                    System.out.println("Please enter the weight of a bike");
                    ecoBikeEntity.setWeight(scanner.nextInt());

                    System.out.println("Please enter is lights available on bike\n"
                            +"type true if it's available\n"
                            +"type false if it's not available");
                    ecoBikeEntity.setAvailableForLights(scanner.nextBoolean());

                    System.out.println("Please enter the battery capacity");
                    ((EBike) ecoBikeEntity).setBatteryCapacity(scanner.nextInt());

                    System.out.println("Please enter the color");
                    ecoBikeEntity.setColor(scanner.nextLine());

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
                    System.out.println(bikesArray.stream().findAny());
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
                System.out.println("Please enter an integer");
            }
            scanner.nextLine();
        } while (typeOfBike <= 0);

//
        //List<EcoBikeEntity> bikesArray = new ArrayList<>();
//        try {
//            for (String bike : reader.getFileContents()) {
//                EcoBikeEntity ecoBikeEntity = convertFileContentToEntity(bike);
//                bikesArray.add(ecoBikeEntity);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Collections.sort(bikesArray, EcoBikeEntity.compareByBrand);
////        for (EcoBikeEntity q:bikesArray
////             ) {
////            System.out.println(q.toString());
////
////        }
//
//        return bikesArray;

       // System.out.println(ecoBikeEntity1.get().toString());
//        Comparator<Vehicle> comp = new Comparator<Vehicle>() {
//
//            @Override
//            public int compare(Vehicle o1, Vehicle o2) {
//                return o1.getModel().compareTo(o2.getModel());
//            }
//
//        };

    }

}