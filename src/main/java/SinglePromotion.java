public class SinglePromotion implements Promotions{

    Item item;
    int quantity;
    double price;
    public SinglePromotion(Item item,double price,int quantity){
        this.item = item;
        this.price = price;
        this.quantity = quantity;
    }

    public double apply(Cart cart){

        int cartQuantity = cart.items.get(item);
        int calcQuan = 0;
        if(quantity<=cartQuantity){
            calcQuan = cartQuantity / quantity;
            cartQuantity %= quantity;
            cart.items.put(item,cartQuantity);

        }


        return calcQuan * price;
    }
}
