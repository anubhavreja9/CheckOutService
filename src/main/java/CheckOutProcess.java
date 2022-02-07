import java.util.List;
import java.util.Map;

public class CheckOutProcess {

    private Map<Item,Double> prices;
    private Map<Item, List<Promotions>> itemPromotions;
    public void addPrices(Map<Item,Double> prices){
        this.prices = prices;
    }
    public void addPromotions(Map<Item, List<Promotions>> itemPromotions){
     this.itemPromotions = itemPromotions;
    }

    public double processCheckOut(Cart cart){
    if(cart == null || cart.items == null){
        return 0;
    }
    double finalAmount = 0 ;
    for(Map.Entry<Item,List<Promotions>> itemPromo: itemPromotions.entrySet()){

         for(Promotions promotion:itemPromo.getValue()) {
             double amount = promotion.apply(cart);
                finalAmount += amount;
        }
        }
    for(Map.Entry<Item,Integer> item:cart.items.entrySet()){
        finalAmount += item.getValue() * prices.get(item.getKey());
    }
    return finalAmount;
    }
}
