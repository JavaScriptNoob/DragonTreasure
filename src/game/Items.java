package game;

public class Items {
    private String itemsName;
    private String itemsDescription;
    private int itemsRoom;

    public Items(String itemsName, String  itemsDescription, int itemsRoom) {
        this.itemsName=itemsName;
        this.itemsDescription=itemsDescription;
        this.itemsRoom = itemsRoom;
    }
    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public String getItemsDescription() {
        return itemsDescription;
    }

    public void setItemsDescription(String itemsDescription) {
        this.itemsDescription = itemsDescription;
    }

    public int getItemsRoom() {
        return itemsRoom;
    }

    public void setItemsRoom(int itemsRoom) {
        this.itemsRoom = itemsRoom;
    }

//    @Override
//    public String toString() {
//        return ("ItemName:"+this.getItemsName()+
//                " Items Description "+ this.getItemsDescription() +
//                " Item soom: "+ this.getItemsRoom());
//    }
}
