package role;

import sqlmanage.TestQuery;
import tools.showImage;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class commonRole {

    protected ArrayList<myService> serviceArrayList;

    public Scanner in = new Scanner(System.in);

    public void showInfo() {
        String sql = "select * from myservice left join typetable on myservice.typeid=typetable.id order by myservice.id;";
        // serviceArrayList = sqlManage.TestQuery.showMyQuery(sql);
        TestQuery.showOneServiceQuery(sql);
    }

    public void checkService(){}

    public void start(){};

    public void showImagePre(int id) {
        try {
            showImage.show(serviceArrayList.get(id).getImagePath());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getValidString() {

        try {
            String anw = null;
            if (in.hasNextLine()) {

                anw = in.nextLine();
            }
            else System.out.println("No input string in getValidString");
            return anw;
        }
        catch (InputMismatchException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getValidInt() {
        int id = -1;
        try {

//            String tem = in.next();
//            id = Integer.parseInt(tem);
            id = in.nextInt();
            while (id < 0 || id >= serviceArrayList.size()) {
                System.out.printf("error id,should be [0,%d)\nchoose new id\n", serviceArrayList.size());
                id = in.nextInt();
            }

        }
        catch (InputMismatchException e) {
            e.printStackTrace();
        }
        return id;
    }
}
