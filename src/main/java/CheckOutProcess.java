import java.util.List;
import java.util.Map;

public class CheckOutProcess {

    Map<Item,Double> prices;
    Map<Item, List<Promotions>> itemPromotions;
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
            if (amount != 0) {
                finalAmount += amount;
                break;
            }
        }
        }
    for(Map.Entry<Item,Integer> item:cart.items.entrySet()){
        finalAmount += item.getValue() * prices.get(item.getKey());
    }
    return finalAmount;
    }
}
