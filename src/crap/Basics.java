package crap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Basics {

    private static abstract class Transaction{
        public Transaction(){
            logTransaction();
        }

        protected abstract void logTransaction();
    }

    private static class BuyTransaction extends Transaction{
        private int value = 5;
        protected void logTransaction(){
            System.out.println("im buying "+value);
        }
    }

    private static class SellTransaction extends Transaction{
        protected void logTransaction(){
            System.out.println("im selling");
        }
    }
    public static void main(String[] args) {
        BuyTransaction buyTransaction = new BuyTransaction();
        SellTransaction sellTransaction = new SellTransaction();
        System.out.println(buyTransaction+" "+sellTransaction);
    }
}
