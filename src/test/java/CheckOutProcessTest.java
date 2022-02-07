import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CheckOutProcessTest {

    public CheckOutProcess processor;
    @BeforeAll
    public void setupAll(){
        processor = new CheckOutProcess();
        addPricesAndPromotions();
    }


    private void addPricesAndPromotions() {
        Item itemA = new Item("A");
        Item itemB = new Item("B");
        Item itemC = new Item("C");
        Item itemD = new Item("D");
        Map<Item,Double> prices = new HashMap<>();
        prices.put(itemA,50.0);
        prices.put(itemB,30.0);
        prices.put(itemC,20.0);
        prices.put(itemD,15.0);
        processor.addPrices(prices);

        SinglePromotion itemAPromotion = new SinglePromotion(itemA,130,3);
        SinglePromotion itemBPromotion = new SinglePromotion(itemB,45,2);
        List<Item> itemsCombined = new ArrayList<>(Arrays.asList(itemC,itemD));
        CombinedPromotion combined = new CombinedPromotion(itemsCombined,30);
        Map<Item,List<Promotions>> promotions = new HashMap<>();
        promotions.put(itemA,new ArrayList<>(Arrays.asList(itemAPromotion)));
        promotions.put(itemB,new ArrayList<>(Arrays.asList(itemBPromotion)));
        promotions.put(itemC,new ArrayList<>(Arrays.asList(combined)));
        promotions.put(itemD,new ArrayList<>(Arrays.asList(combined)));
        processor.addPromotions(promotions);
    }

    @Test
    void processCheckOutSc1() {
        Item itemA = new Item("A");
        Item itemB = new Item("B");
        Item itemC = new Item("C");
        Map<Item,Integer> quantity = new HashMap<>();
        quantity.put(itemA,1);
        quantity.put(itemB,1);
        quantity.put(itemC,1);
        Cart newCart = new Cart(quantity);
        Assertions.assertEquals(processor.processCheckOut(newCart),100.0);
    }
    @Test
    void processCheckOutSc2() {
        Item itemA = new Item("A");
        Item itemB = new Item("B");
        Item itemC = new Item("C");
        Map<Item,Integer> quantity = new HashMap<>();
        quantity.put(itemA,5);
        quantity.put(itemB,5);
        quantity.put(itemC,1);
        Cart newCart = new Cart(quantity);
        Assertions.assertEquals(processor.processCheckOut(newCart),370.0);
    }
    @Test
    void processCheckOutSc3() {
        Item itemA = new Item("A");
        Item itemB = new Item("B");
        Item itemC = new Item("C");
        Item itemD = new Item("D");
        Map<Item,Integer> quantity = new HashMap<>();
        quantity.put(itemA,3);
        quantity.put(itemB,5);
        quantity.put(itemC,1);
        quantity.put(itemD,1);
        Cart newCart = new Cart(quantity);
        Assertions.assertEquals(processor.processCheckOut(newCart),280.0);
    }
}