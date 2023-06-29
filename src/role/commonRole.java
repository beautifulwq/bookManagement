package role;

import sqlManage.TestQuery;

public class commonRole {
    public void showInfo() {
        String sql = "select * from myservice left join typetable on myservice.id=typetable.id order by myservice.id;";
        // serviceArrayList = sqlManage.TestQuery.showMyQuery(sql);
        TestQuery.showOneServiceQuery(sql);
    }

    public void checkService(){}
}
