import java.time.LocalDate;
import java.util.HashMap;

class myService {
    private String name;
    private int id;
    private int useHour;

    private int type;
    private int price;

    private int buyCnt;
    private int comment;
    private int haveCnt;


    private String imagePath;

    public myService(){}
    public myService(int id) {
        this.id = id;
    }

    public myService(String name, int id, int useHour, int type, int price, int buyCnt, int comment, int haveCnt) {
        this.name = name;
        this.id = id;
        this.useHour = useHour;
        this.type = type;
        this.price = price;
        this.buyCnt = buyCnt;
        this.comment = comment;
        this.haveCnt = haveCnt;
    }

    public myService(String name, int id, int useHour, int type, int price, int buyCnt, int comment, int haveCnt, String path) {
        this.name = name;
        this.id = id;
        this.useHour = useHour;
        this.type = type;
        this.price = price;
        this.buyCnt = buyCnt;
        this.comment = comment;
        this.haveCnt = haveCnt;
    this.imagePath=path;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUseHour() {
        return useHour;
    }

    public void setUseHour(int useHour) {
        this.useHour = useHour;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBuyCnt() {
        return buyCnt;
    }

    public void setBuyCnt(int buyCnt) {
        this.buyCnt = buyCnt;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getHaveCnt() {
        return haveCnt;
    }

    public void setHaveCnt(int haveCnt) {
        this.haveCnt = haveCnt;
    }
}
