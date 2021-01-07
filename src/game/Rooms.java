package game;

public class Rooms {

    private String roomName;
    private String description;
    private Integer n,s,w,e;




    public Rooms(String roomName, String description, Integer n, Integer s, Integer w, Integer e) {
        this.roomName = roomName;
        this.description = description;
        this.n = n;
        this.s = s;
        this.w = w;
        this.e = e;
    }
//    public static  void  createRooms(){
//        /HashMap<Integer,String> rooms = new HashMap<Integer, String>();
//        rooms.put(1,"Suspension bridge");
//        rooms.put(2,"Long halls of eternal darkness");
//        rooms.put(3,"Infected wine cellars" );
//        rooms.put(4, "Armory of heroes of the past");
//        rooms.put(5, "The source of our fear");
//        rooms.put(6, "The warehouse of the cursed king");
//        rooms.put(7, "The Dragon's mine");//
//        System.out.println(rooms);

//    }
public String getRoomName() {
    return roomName;
}

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public Integer getS() {
        return s;
    }

    public void setS(Integer s) {
        this.s = s;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getE() {
        return e;
    }

    public void setE(Integer e) {
        this.e = e;
    }
}
