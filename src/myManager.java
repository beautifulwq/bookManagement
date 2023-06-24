import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class myManager {
    public static void main(String[] args) {
        myManager m1 = new myManager();
        m1.changePrice();
    }

    private ArrayList<myService> serviceArrayList;

    public myManager() {
        String sql = "select * from myService";
        serviceArrayList = TestQuery.showMyQuery(sql);
    }

    void showInfo() {
        String sql = "select * from myService";
        serviceArrayList = TestQuery.showMyQuery(sql);
    }

    void checkService() {
        System.out.printf("choose check id,should be [0,%d)\n", serviceArrayList.size());
        try (Scanner in = new Scanner(System.in)) {
            int id = in.nextInt();
            while (id < 0 || id >= serviceArrayList.size()) {
                System.out.printf("error id,should be [0,%d)\nchoose comment id\n", serviceArrayList.size());
                id = in.nextInt();
            }
            String sql = String.format("select * from myservice natural join typetable where myservice.id=%d", id);
            TestQuery.showMyQuery(sql);
        }
        catch (InputMismatchException e) {
            e.printStackTrace();
        }

    }

    void sellService() {
        if (serviceArrayList.size() == 0) {
            System.out.println("no book,can not sell");
            return;
        }

        System.out.println("choose sell id");
        Scanner in = new Scanner(System.in);

        try {
            int delId = in.nextInt();
            while (delId < 0 || delId >= serviceArrayList.size()) {
                System.out.printf("error index id, should be [0,%d)\n", serviceArrayList.size());
                System.out.println("scan in new sell id");
                delId = in.nextInt();
            }
            myService book = serviceArrayList.get(delId);
            int haveCnt = book.getHaveCnt();
            if (haveCnt <= 0) {
                System.out.println("have cnt=0,can not sell");
                return;
            }
            else book.setHaveCnt(haveCnt - 1);
            int buyCnt = book.getBuyCnt();
            book.setBuyCnt(buyCnt + 1);
            System.out.println("sell success");
            String sql1 = String.format("update myService set havecnt=havecnt-1 where id=%d", delId);
            String sql2 = String.format("update myService set buycnt=buycnt+1 where id=%d", delId);
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
            System.out.println("exit sell");
            e.printStackTrace();
        }
        finally {
            System.out.printf("exit programme\n");
        }
    }

    void deleteService() {
        if (serviceArrayList.size() == 0) {
            System.out.println("no book,can not delete");
            return;
        }

        System.out.println("choose delete id");
        Scanner in = new Scanner(System.in);

        try {
            int delId = in.nextInt();
            while (delId < 0 || delId >= serviceArrayList.size()) {
                System.out.printf("error index id, should be [0,%d)\n", serviceArrayList.size());
                System.out.println("scan in new delete id");
                delId = in.nextInt();
            }
            myService book = serviceArrayList.get(delId);
            int haveCnt = book.getHaveCnt();
            if (haveCnt <= 0) {
                System.out.println("have cnt=0,can not delete");
                return;
            }
            else book.setHaveCnt(haveCnt - 1);
            System.out.println("delete success");
            String sql1 = String.format("update myService set havecnt=havecnt-1 where id=%d", delId);
            try {
                TestManage.updateRecord(sql1);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (InputMismatchException e) {
            System.out.println("error input kind, should be int");
            System.out.println("exit delete");
            e.printStackTrace();
        }
        finally {
            System.out.printf("exit programme\n");
        }
    }

    void changePrice() {
        if (serviceArrayList.size() == 0) {
            System.out.println("no book,can not change");
            return;
        }

        System.out.println("choose change id");
        Scanner in = new Scanner(System.in);

        try {
            int changeId = in.nextInt();
            while (changeId < 0 || changeId >= serviceArrayList.size()) {
                System.out.printf("error index id, should be [0,%d)\n", serviceArrayList.size());
                System.out.println("scan in new change id");
                changeId = in.nextInt();
            }
            myService book = serviceArrayList.get(changeId);
            System.out.println("choose new price");
            int price = in.nextInt();
            while (price < 0) {
                System.out.println("price<0 error,re in");
                price = in.nextInt();
            }
            book.setPrice(price);
            System.out.println("change price success");
            String sql1 = String.format("update myService set price=%d where id=%d", price, changeId);
            try {
                TestManage.updateRecord(sql1);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (InputMismatchException e) {
            System.out.println("error input kind, should be int");
            System.out.println("exit change");
            e.printStackTrace();
        }
        finally {
            System.out.printf("exit programme\n");
        }
    }
}

