import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class myConsumer {

    public static void main(String[] args) {
        myConsumer c1 = new myConsumer();
        c1.checkService();
        //c1.showInfo();
        //c1.addComment();
    }

    private ArrayList<myService> serviceArrayList;

    public myConsumer() {
        String sql = "select * from myService";
        serviceArrayList = TestQuery.showMyQuery(sql);
    }
    
    void showInfo(){
        String sql = "select * from myService";
        serviceArrayList = TestQuery.showMyQuery(sql);
    }

    void buyService() {
        if (serviceArrayList.size() == 0) {
            System.out.println("no book,can not buy");
            return;
        }

        System.out.println("choose buy id");
        Scanner in = new Scanner(System.in);

        try {
            int buyId = in.nextInt();
            while (buyId < 0 || buyId >= serviceArrayList.size()) {
                System.out.printf("error index id, should be [0,%d)\n", serviceArrayList.size());
                System.out.println("scan in new buy id");
                buyId = in.nextInt();
            }
            myService book = serviceArrayList.get(buyId);
            int haveCnt = book.getHaveCnt();
            if (haveCnt <= 0) {
                System.out.println("have cnt=0,can not buy");
                return;
            }
            else book.setHaveCnt(haveCnt - 1);
            int buyCnt = book.getBuyCnt();
            book.setBuyCnt(buyCnt + 1);
            System.out.println("buy success");
            String sql1 = String.format("update myService set havecnt=havecnt-1 where id=%d", buyId);
            String sql2 = String.format("update myService set buycnt=buycnt+1 where id=%d", buyId);
            try {
                TestManage.updateRecord(sql1);
                TestManage.updateRecord(sql2);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (InputMismatchException e) {
            System.out.println("error input kind, should be int");
            System.out.println("exit buy");
            e.printStackTrace();
        }
        finally {
            System.out.printf("exit programme");
        }
    }

    void checkService(){
        System.out.printf("choose check id,should be [0,%d)\n",serviceArrayList.size());
        try(Scanner in=new Scanner(System.in)){
            int id=in.nextInt();
            while (id<0||id>=serviceArrayList.size()){
                System.out.printf("error id,should be [0,%d)\nchoose comment id\n",serviceArrayList.size());
                id=in.nextInt();
            }
            String sql=String.format("select * from myservice natural join typetable where myservice.id=%d",id);
            TestQuery.showMyQuery(sql);
        }
        catch (InputMismatchException e){
            e.printStackTrace();
        }

    }

    void addComment() {
        if (serviceArrayList.size() == 0) {
            System.out.println("no service,can not comment");
            return;
        }

        System.out.println("choose comment star,should be [0 5]");
        try (Scanner in = new Scanner(System.in);) {
            System.out.println("choose comment id");
            int id=in.nextInt();
            while (id<0||id>=serviceArrayList.size()){
              System.out.printf("error id,should be [0,%d)\nchoose comment id",serviceArrayList.size());
                id=in.nextInt();
            }

            System.out.println("choose star,should be [0,5]");
            int commentStar = in.nextInt();
            while (commentStar > 5 || commentStar < 0) {
                System.out.println("error star range,should be [0,5]");
                commentStar = in.nextInt();
            }
            String sql=String.format("UPDATE `study`.`myservice` SET `comment` = '%d' WHERE (`id` = '%d');",commentStar,id);
            TestManage.updateRecord(sql);
        }
        catch (InputMismatchException e) {
            System.out.println("error input kind, should be int");
            System.out.println("exit comment");
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
