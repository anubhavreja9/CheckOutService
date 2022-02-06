public class Item {

    String id;

    public Item(String id){
        this.id = id;
    }
    @Override
    public boolean equals(Object otherItem){
        if(otherItem == this){
            return true;
        }
        if(otherItem == null || otherItem.getClass() != Item.class){
            return false;
        }
        Item other = (Item)otherItem;
        if(other.id == null || !other.id.equals(id)){
            return false;
        }
        return true;
    }
    @Override
    public int hashCode(){
        int result = 31;
        result += result * id.hashCode();
        return result;
    }

}
