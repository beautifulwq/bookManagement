package role;

import sqlManage.TestQuery;
import tools.showImage;
import java.util.ArrayList;
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
}
