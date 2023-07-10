package role;

import sqlmanage.TestManage;
import sqlmanage.TestQuery;
import tools.showImage;
import tools.showWelcome;

import java.sql.SQLException;
import java.util.InputMismatchException;

public class myManager extends commonRole {
    public static void main(String[] args) {
        myManager m1 = new myManager();

    }

//    public String getValidString() {
//
//        try {
//            String anw = null;
//            if (in.hasNextLine()) {
//
//                anw = in.nextLine();
//            }
//            else System.out.println("No input string in getValidString");
//            return anw;
//        }
//        catch (InputMismatchException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

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

    public myManager() {
        String sql = "select * from myservice order by id;";
        serviceArrayList = TestQuery.showMyQuery(sql);

    }

    public void addImagePre(int id) {
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

    public void sellService() {
        if (serviceArrayList.size() == 0) {
            System.out.println("no service,can not sell");
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
            String sql1 = String.format("update myservice set havecnt=havecnt-1 where id=%d", delId);
            String sql2 = String.format("update myservice set buycnt=buycnt+1 where id=%d", delId);
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
    }

    public void deleteService() {
        if (serviceArrayList.size() == 0) {
            System.out.println("no book,can not delete");
            return;
        }

        System.out.println("choose delete id");
//        try {
        int delId;
        delId = getValidInt();
        for (myService s : serviceArrayList) {
            if (s.getId() == delId) {
                System.out.println("delete success");
                break;
            }
        }
        serviceArrayList.removeIf(o1 -> o1.getId() == delId);
        String sql1 = String.format("delete from myService where id=%d", delId);
        try {
            TestManage.updateRecord(sql1);
            resetId();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
//        }
//        catch (InputMismatchException e) {
//            System.out.println("error input kind, should be int");
//            System.out.println("exit delete");
//            e.printStackTrace();
//        }
    }

    public void changePrice() {
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
            price = in.nextInt();
            book.setPrice(price);
            System.out.println("change price success");
            String sql1 = String.format("update myservice set price=%d where id=%d", price, changeId);
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
    }

    public void addService() {
        try {
            myService service = new myService();
            System.out.println("adding service\n");
            System.out.print("in name--");
            String rubbish = getValidString();
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
            int id = serviceArrayList.size();
            service.setId(serviceArrayList.size());
            serviceArrayList.add(service);

            String sql = String.format("INSERT INTO `myservice` (`id`, `name`, `usehour`, `typeid`, `price`, `havecnt`) VALUES ('%d', '%s', '%d', '%d', '%d', '%d');\n", id, name, useHour, type, price, haveCnt);

            TestManage.updateRecord(sql);

            //reset
            resetId();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void resetId() {
        int cnt = 0;
        for (myService service : serviceArrayList) {
            int oriId = service.getId();
            String name = service.getName();
            service.setId(cnt);
            String sql = String.format("UPDATE `myservice` SET `id` = '%d' WHERE (`id` = '%d' and `name` = '%s');\n", cnt, oriId, name);
            try {
                TestManage.updateRecord(sql);
                cnt++;
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        String sql = "select * from myService";
        serviceArrayList = TestQuery.showMyQuery(sql);
    }

    public void start() {
        showWelcome.showGuide(1);
        int choose;
        choose = in.nextInt();
        while (choose >= 0 && choose <= 7) {
            switch (choose) {
                case 0:
                    showInfo();
                    break;
                case 1:
                    checkService();
                    break;
                case 2:
                    addService();
                    break;
                case 3:
                    sellService();
                    break;
                case 4:
                    deleteService();
                    break;
                case 5:
                    changePrice();
                    break;
                case 6:
                    System.out.print("choose add service id--");
                    int addId = getValidInt();
                    addImagePre(addId);
                    break;
                case 7:
                    System.out.print("choose show pic service id--");
                    int showId = getValidInt();
                    showImagePre(showId);
                    break;
            }
            showWelcome.showGuide(1);
            choose = in.nextInt();
        }

        System.exit(0);
    }

}

