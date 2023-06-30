package tools;

import role.myManager;
import role.myConsumer;

public class testAll {
    public static void main(String[] args) {
        myManager manager = new myManager();
        manager.start();

        myConsumer consumer=new myConsumer();
        consumer.start();
    }
}
