package role;

import sqlManage.TestManage;
import sqlManage.TestQuery;
import tools.showWelcome;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import tools.showImage;
public class myConsumer extends commonRole{

    public static void main(String[] args) {
        myConsumer c1 = new myConsumer();
        myConsumer c2 = new myConsumer();
        c1.buyService();
        c2.buyService();
        //c1.showInfo();
        //c1.addComment();
    }

   // Scanner in =new Scanner(System.in);
   // private ArrayList<myService> serviceArrayList;

    public myConsumer() {
        String sql = "select * from myservice order by id";
        serviceArrayList = (ArrayList<myService>) TestQuery.showMyQuery(sql);
    }

//    public void showInfo() {
//        String sql = "select * from myservice";
//        serviceArrayList = (ArrayList<myService>) TestQuery.showMyQuery(sql);
//    }

    public void buyService() {
        if (serviceArrayList.size() == 0) {
            System.out.println("no book,can not buy");
            return;
        }

        System.out.println("choose buy id");
        Scanner in = new Scanner(System.in);

        try {
            int buyId = getValidInt();
            while (buyId < 0 || buyId >= serviceArrayList.size()) {
                System.out.printf("error index id, should be [0,%d)\n", serviceArrayList.size());
                System.out.println("scan in new buy id");
                buyId = getValidInt();
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
            String sql1 = String.format("update myservice set havecnt=havecnt-1 where id=%d", buyId);
            String sql2 = String.format("update myservice set buycnt=buycnt+1 where id=%d", buyId);
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
    }

//    public int getValidInt() {
//        int id = -1;
//        try {
//
////            String tem = in.next();
////            id = Integer.parseInt(tem);
//            id = in.nextInt();
//            while (id < 0 || id >= serviceArrayList.size()) {
//                System.out.printf("error id,should be [0,%d)\nchoose new id\n", serviceArrayList.size());
//                id = in.nextInt();
//            }
//
//        }
//        catch (InputMismatchException e) {
//            e.printStackTrace();
//        }
//        return id;
//    }

    public void checkService() {
        System.out.printf("choose check id,should be [0,%d)----", serviceArrayList.size());
        int id;
        id = getValidInt();
        try {
            String sql = String.format("select * from myservice join typetable on myservice.typeid=typetable.id where myservice.id=%d", id);
            TestQuery.showOneServiceQuery(sql);
        }
        catch (InputMismatchException e) {
            e.printStackTrace();
        }

    }

    public void addComment() {
        if (serviceArrayList.size() == 0) {
            System.out.println("no service,can not comment");
            return;
        }

        System.out.println("choose comment star,should be [0 5]");
        try {
            System.out.println("choose comment id");
            int id = getValidInt();
            while (id < 0 || id >= serviceArrayList.size()) {
                System.out.printf("error id,should be [0,%d)\nchoose comment id", serviceArrayList.size());
                id = in.nextInt();
            }

            System.out.println("choose star,should be [0,5]");
            int commentStar = in.nextInt();
            while (commentStar > 5 || commentStar < 0) {
                System.out.println("error star range,should be [0,5]");
                commentStar = in.nextInt();
            }
            String sql = String.format("UPDATE `study`.`myservice` SET `comment` = '%d' WHERE (`id` = '%d');", commentStar, id);
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

    public void start() {
        showWelcome.showGuide(2);
        int choose;
        choose = in.nextInt();
        while (choose >= 0 && choose <= 4) {
            switch (choose) {
                case 0:
                    showInfo();
                    break;
                case 1:
                    checkService();
                    break;
                case 2:
                    buyService();
                    break;
                case 3:
                    addComment();
                    break;
                case 4:
                    System.out.print("choose show pic service id--");
                    int showId = getValidInt();
                    showImagePre(showId);
                    break;
            }
            showWelcome.showGuide(2);
            choose = in.nextInt();
        }

        System.exit(0);
    }
}
