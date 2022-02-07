import java.util.List;

public class CombinedPromotion implements Promotions{

    List<Item> itemsQuan;
    double price;
    public CombinedPromotion(List<Item> itemsQuan, double price){
        this.itemsQuan = itemsQuan;
        this.price = price;
    }

    public double apply(Cart cart){
        int calcQuan = 0;
        int minQuant = Integer.MAX_VALUE;
        for(Item item:itemsQuan) {
            minQuant = Math.min(minQuant,cart.items.get(item)==null?0:cart.items.get(item));
        }
        calcQuan = minQuant == Integer.MAX_VALUE?0:minQuant;
        for(Item item:itemsQuan){
            int q = cart.items.get(item)==null?0:cart.items.get(item);
            cart.items.put(item,q-minQuant);
        }


        return calcQuan * price;
    }
}
