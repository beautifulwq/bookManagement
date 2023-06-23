import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class myConsumer {
    public static void main(String[] args) {
        myConsumer c1 = new myConsumer();
        ArrayList<myBook> arrayList = new ArrayList<>();
        myBook b1 = new myBook(1);
        arrayList.add(b1);
        c1.buyBook(arrayList);
    }

    void buyBook(ArrayList<myBook> arrayList) {

        if (arrayList.size() == 0) {
            System.out.println("no book,can not buy");
            return;
        }

        System.out.println("choose buy id");
        Scanner in = new Scanner(System.in);

        try {
            int buyId = in.nextInt();
            buyId = in.nextInt();
            while (buyId < 0 || buyId >= arrayList.size()) {
                System.out.printf("error index id, should be [0,%d)\n", arrayList.size());
                System.out.println("scan in new buy id");
                buyId = in.nextInt();
            }
            myBook book = arrayList.get(buyId);
            int haveCnt = book.getHaveCnt();
            if (haveCnt <= 0) {
                System.out.println("book cnt=0,can not buy");
                return;
            }
            else book.setHaveCnt(haveCnt - 1);
            int buyCnt = book.getBuyCnt();
            book.setBuyCnt(buyCnt + 1);
        }
        catch (InputMismatchException e) {
            System.out.println("error input kind, should be int");
            System.out.printf("exit buy");
            e.printStackTrace();
        }
        finally {
            System.out.printf("exit programme");
        }
    }
}
