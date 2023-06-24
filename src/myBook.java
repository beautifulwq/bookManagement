import java.time.LocalDate;
import java.util.HashMap;

public class myBook {
    private String name;
    private int id;
    private String author;
    private String publisher;
    private LocalDate publishDate;
    private int type;
    private int price;
    private int pageCnt;
    private int buyCnt;
    private int comment;
    private int haveCnt;

    public myBook(int id) {
        this.id = id;
    }

    public myBook(String name, String author, String publisher, LocalDate publishDate, int type, int price, int pageCnt, int buyCnt, int haveCnt) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.type = type;
        this.price = price;
        this.pageCnt = pageCnt;
        this.buyCnt = buyCnt;
        this.haveCnt = haveCnt;
    }

    public int getHaveCnt() {
        return haveCnt;
    }

    public void setHaveCnt(int haveCnt) {
        this.haveCnt = haveCnt;
    }

    public String getName() {
        return name;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPageCnt() {
        return pageCnt;
    }

    public void setPageCnt(int pageCnt) {
        this.pageCnt = pageCnt;
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
}
