package tools;

import java.util.Scanner;

import role.myManager;
import role.myConsumer;

public class showWelcome {
    static public void begin() {
        System.out.println("choose role:\n" +
                "1: admin\n" +
                "2: visitor");

        int choose;
        //todo: 输入choose
        Scanner in = new Scanner(System.in);
        choose = in.nextInt();
        if (choose == 1) {
            myManager manager = new myManager();
            manager.start();
        }
        else {
            myConsumer consumer = new myConsumer();
            consumer.start();
        }

    }

    static public void showGuide(int role) {
        if (role == 1) {
            System.out.println("\n0\tshow info\t\t\t" +
                    "1\tcheck service\t\t" +
                    "2\tadd service\n" +
                    "3\tsell service\t\t" +
                    "4\tdelete service\t\t" +
                    "5\tchange price\n" +
                    "6\tadd image\t\t\t" +
                    "7\tshow image");
            System.out.print("choose func--");

        }
        else if (role == 2) {
            System.out.println("\n0\tshow info\t\t" +
                    "1\tcheck service\t\t" +
                    "2\tbuy service\n" +
                    "3\tadd comment\t\t" +
                    "4\tshow image");
            System.out.print("choose func--");
        }
    }

    public static void main(String[] args) {
        // programme origin start
        showWelcome.begin();
        
    }

}
