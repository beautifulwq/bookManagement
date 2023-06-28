import java.util.InputMismatchException;
import java.util.Scanner;

public class testAll {
    public static void main(String[] args) {
        myManager manager=new myManager();

       manager.addService();
       manager.showInfo();
       manager.deleteService();
        manager.resetId();
       manager.showInfo();
//        manager.addImagePre(1);
//        manager.checkService();
//       manager.addService();
//        manager.changePrice();
//        manager.sellService();
//        manager.deleteService();
//        manager.showInfo();
    }


}
