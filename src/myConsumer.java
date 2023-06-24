import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class myConsumer {

    public static void main(String[] args) {
        myConsumer c1 = new myConsumer();
        c1.buyBook();
    }

    private ArrayList<myBook> bookArrayList;

    public myConsumer() {
        String sql = "select * from mybook";
        bookArrayList = TestQuery.showMyQuery(sql);
    }

    void buyBook() {
        if (bookArrayList.size() == 0) {
            System.out.println("no book,can not buy");
            return;
        }

        System.out.println("choose buy id");
        Scanner in = new Scanner(System.in);

        try {
            int buyId = in.nextInt();
            while (buyId < 0 || buyId >= bookArrayList.size()) {
                System.out.printf("error index id, should be [0,%d)\n", bookArrayList.size());
                System.out.println("scan in new buy id");
                buyId = in.nextInt();
            }
            myBook book = bookArrayList.get(buyId);
            int haveCnt = book.getHaveCnt();
            if (haveCnt <= 0) {
                System.out.println("book cnt=0,can not buy");
                return;
            }
            else book.setHaveCnt(haveCnt - 1);
            int buyCnt = book.getBuyCnt();
            book.setBuyCnt(buyCnt + 1);
            System.out.println("buy success");
            String sql1 = String.format("update mybook set havecnt=havecnt-1 where id=%d", buyId);
            String sql2 = String.format("update mybook set buycnt=buycnt+1 where id=%d", buyId);
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
}
