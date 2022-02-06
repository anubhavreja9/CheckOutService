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
    Map<Item,Integer> itemsMap = cart.items;
    double finalAmount = 0 ;
    for(Map.Entry<Item,Integer> itemCart: itemsMap.entrySet()){

        List<Promotions> promotions = itemPromotions.get(itemCart.getKey());

        for(Promotions promotion:promotions) {
            double amount = promotion.apply(cart);
            if (amount != 0) {
                finalAmount += amount;
                break;
            }
        }
        finalAmount += itemCart.getValue() * prices.get(itemCart.getKey());
        }
    return finalAmount;
    }
}
