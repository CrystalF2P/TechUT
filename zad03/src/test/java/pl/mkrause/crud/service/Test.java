package pl.mkrause.crud.service;

import pl.mkrause.crud.domain.Fish;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {

    private static FishServiceJDBC fishService = new FishServiceJDBC();

    public static void main(String[] args) {
        fishService.clear();
        AddDeleteCheck();
        System.out.println("-------\n");
        fishService.clear();
        AddAllFishCheck();
        PrintAll();
        System.out.println("-------\n");
        FindFishCheck();
    }

    private static void AddDeleteCheck() {
    	final ParsePosition parse = new ParsePosition(0);
    	Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-01", parse);
        Fish fish = new Fish("Okon", date, 16.12);
        fishService.addFish(fish);
        Fish newFish = new Fish("Szprot", date, 12.19);
        fishService.addFish(newFish);
        PrintAll();
        fishService.deleteFish(fish.getId());
        PrintAll();

        if(fishService.getAllFish().size() == 1) System.out.println("> Add/DeleteCheck works\n");
        else System.out.println("> Add/DeleteCheck does NOT work\n");
    }

    private static void AddAllFishCheck() {
        List<Fish> list = new ArrayList<>();
        try {
            Date date = new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2019-01-03" );

            list.add(new Fish("Losos", date, 11.11));
            list.add(new Fish("Sum", date, 12.12));
            list.add(new Fish("Rekin", date, 13.13));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        fishService.addAllFish(list);

        if(fishService.getAllFish().size() == 3) System.out.println("> AddAll works");
        else System.out.println("> AddAll does NOT work");

    }

    private static void FindFishCheck() {
        List<Fish> idList = fishService.findFishById(3);
        System.out.println("obecny stan tabeli:\n");
        Print(idList);
        if(idList.size() > 0) System.out.println("> FindByID works");
        else System.out.println("> FindByID does NOT work");

        System.out.println("-------\n");
        
        List<Fish> gatunekList = fishService.findFishByGatunek("Sum");
        System.out.println("obecny stan tabeli:\n");
        Print(gatunekList);
        if(gatunekList.size() > 0) System.out.println("> FindByGatunek works");
        else System.out.println("> FindByGatunek does NOT work");
    }

    private static void Print(List<Fish> list) {
        for(Fish fish: list) {
            System.out.println(fish);
        }
        System.out.println();
    }

    private static void PrintAll() {
        List<Fish> list = fishService.getAllFish();
        System.out.println("obecny stan tabeli:\n");
        Print(list);
    }
}
