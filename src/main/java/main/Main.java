package main;

import entities.EBike;
import entities.EcoBikeEntity;
import fileHandler.MyOwnReader;
import userInterface.ConsoleInterface;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
//import reader.MyOwnReader;


public class Main {
    public static void main(String[] args) {
        MyOwnReader reader = new MyOwnReader();

//        EcoBikeEntity ecoBikeEntity = new EBike("Lankeleisi",null,null,
//                null,null,null,null);
//        Arrays.asList(ecoBikeEntity.toString());
//        System.out.println(ecoBikeEntity.getWeight());
//        List<EBike> arr = new ArrayList<>();
//        arr.add((EBike)ecoBikeEntity);
//        Predicate<EBike> nonNullFields = eBike -> eBike.getPrice().equals("null");
//        arr.stream().filter(nonNullFields.).forEach(s-> System.out.println(s));
       // showAllProps(ecoBikeEntity);

//        Stream<String> bas = Stream.of(ecoBikeEntity.toString().split("; "));
//        List<String> res = bas.filter(s -> !s.equals("null")).collect(Collectors.toList());
//        res.forEach(System.out::println);
        //TODO//Arrays.stream(ecoBikeEntity.toString().split("; ")).filter(s -> !s.equals("null")).forEach(System.out::println);

//        //System.out.println(ecoBikeEntity.toString());
//        EcoBikeEntity fold = new FoldingBike("Duster",20,21,
//               20000,false, "green",90);
//        EcoBikeEntity ecoBikeEntity1 = new Speedelec("GrandSpeed",40,10000,
//                false,24444,"red",800);
//        System.out.println(ecoBikeEntity.toString()+"\n"+fold.toString()+"\n"+ecoBikeEntity1.toString());

        Scanner user = new Scanner(System.in);
        System.out.println("Please enter filename");
        String fileName = user.nextLine().trim();
        File file = new File(fileName);
        reader.getFileNameFromUser(file);

        ConsoleInterface consoleInterface = new ConsoleInterface(reader);
        //consoleInterface.getSortedArrayOfBikes();
        //consoleInterface.findFirstBikeOfParticularBrand();
        consoleInterface.display();



    }
    public static void showAllProps(Object o){
        Field[] fields = o.getClass().getDeclaredFields();
        for(Field field: fields){
            try {
                if (field == null) {
                    System.out.println(field.getName());
                }
                field.setAccessible(true);

                System.out.println(field.getName() + ": " + field.get(o));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}

