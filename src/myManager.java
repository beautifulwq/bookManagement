import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class myManager {
    public static void main(String[] args) {
        myManager m1 = new myManager();
      m1.addService();
        // m1.addService();
        // m1.addImage(0);

    }

    private ArrayList<myService> serviceArrayList;

    Scanner in=new Scanner(System.in);

    String getValidString() {
        String anw = null;
        try  {
            anw = in.nextLine();
        }
        catch (InputMismatchException e) {
            e.printStackTrace();
        }
        return anw;
    }

    int getValidInt() {
        int id = -1;
        try {
           // Scanner in = new Scanner(System.in);
            id = in.nextInt();
            while (id < 0 || id >= serviceArrayList.size()) {
                System.out.printf("error id,should be [0,%d)\nchoose new id\n", serviceArrayList.size());
                id = in.nextInt();
            }
            in.close();
        }
        catch (InputMismatchException e) {
            e.printStackTrace();
        }
        return id;
    }

    public myManager() {
        String sql = "select * from myService";
        serviceArrayList = TestQuery.showMyQuery(sql);
    }

    void showInfo() {
        String sql = "select * from myService";
        serviceArrayList = TestQuery.showMyQuery(sql);
    }

    void addImagePre(int id) {
        String path;
        path = showImage.chooseImage();
//        String path="P:/playground/javaplay/bookmanage/bookmanage/picture/bing.png";
        serviceArrayList.get(id).setImagePath(path);

        String sql1 = String.format("UPDATE `myservice` SET `imagepath` = '%s' WHERE (`id` = '%d');", path, id);
        try {
            TestManage.updateRecord(sql1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void showImagePre(int id) {
        try {
            showImage.show(serviceArrayList.get(id).getImagePath());
        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }

    void checkService() {
        System.out.printf("choose check id,should be [0,%d)\n", serviceArrayList.size());
        try {
            int id;
            id = getValidInt();
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

        try {
            int delId;
            delId = getValidInt();
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
        try {
            int delId;
            delId = getValidInt();
            myService book = serviceArrayList.get(delId);
            serviceArrayList.remove(delId);
            System.out.println("delete success");
            String sql1 = String.format("delete from myService where id=%d", delId);
            try {
                TestManage.updateRecord(sql1);
                resetId();
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
    }

    void changePrice() {
        if (serviceArrayList.size() == 0) {
            System.out.println("no book,can not change");
            return;
        }

        System.out.println("choose change id");


        try {
            int changeId;
            changeId = getValidInt();
            myService book = serviceArrayList.get(changeId);
            System.out.println("choose new price");
            int price;
            price = getValidInt();
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

    void addService() {
        try {
            myService service = new myService();
            System.out.println("adding service\n");
            System.out.print("in name--");
            String name = getValidString();
            service.setName(name);

            System.out.printf("\nin useHour--");
            int useHour;
            useHour = in.nextInt();
            service.setUseHour(useHour);

            System.out.printf("\nin type--");
            int type = in.nextInt();
            service.setType(type);

            System.out.printf("\nin price--");
            int price = in.nextInt();
            service.setPrice(price);

            System.out.printf("\nin haveCnt--");
            int haveCnt = in.nextInt();
            service.setHaveCnt(haveCnt);

            //add id
            int id=-1;
            for(myService i:serviceArrayList){
                id=Integer.max(id,i.getId());
            }
            id=id+1;
            service.setId(serviceArrayList.size());
            serviceArrayList.add(service);


            String sql = String.format("INSERT INTO `myservice` (`id`, `name`, `usehour`, `typeid`, `price`, `havecnt`) VALUES ('%d', '%s', '%d', '%d', '%d', '%d');\n", id, name, useHour, type, price, haveCnt);

            TestManage.updateRecord(sql);

            resetId();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void resetId(){
        int cnt=0;
        for(myService service:serviceArrayList){
            int oriId=service.getId();
            service.setId(cnt);
            String sql=String.format("UPDATE `myservice` SET `id` = '%d' WHERE (`id` = '%d');\n",cnt,oriId);
            try {
                TestManage.updateRecord(sql);
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
            cnt++;
        }
        String sql = "select * from myService";
        serviceArrayList = TestQuery.showMyQuery(sql);
    }
}

