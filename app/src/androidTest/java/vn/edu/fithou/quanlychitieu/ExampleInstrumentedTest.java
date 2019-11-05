package vn.edu.fithou.quanlychitieu;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import vn.edu.fithou.quanlychitieu.model.Notification;
import vn.edu.fithou.quanlychitieu.model.Transaction;
import vn.edu.fithou.quanlychitieu.model.TransactionGroup;
import vn.edu.fithou.quanlychitieu.model.WalletType;
import vn.edu.fithou.quanlychitieu.util.ConversionUtil;
import vn.edu.fithou.quanlychitieu.util.SQLiteUtil;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    private Context appContext = InstrumentationRegistry.getTargetContext();

    @Test
    public void testInitSQLite() {
        SQLiteUtil sqLiteUtil = new SQLiteUtil(appContext);
//
//        List<TransactionGroup> lst = sqLiteUtil.getAllGroup();
//
//        long currentAmount = sqLiteUtil.getCurrentMoney();
//
//        Notification notification = new Notification(1, "HAHAHA", Calendar.getInstance().getTime(), Notification.STATUS_UNSEEN);
//        sqLiteUtil.insertNotification(notification);
//
//        List<Notification> notifications = sqLiteUtil.getAllNotification();
//
//        currentAmount = sqLiteUtil.getCurrentMoney();
//
        Transaction transaction = new Transaction(1000000, sqLiteUtil.getGroupById(1), "Thu tiền lương", new GregorianCalendar(2019, Calendar.OCTOBER, 5).getTime(), WalletType.BANK_CARD);
        Transaction transactionx = new Transaction(1000000, sqLiteUtil.getGroupById(1), "Thu tiền lương", new GregorianCalendar(2019, Calendar.OCTOBER, 6).getTime(), WalletType.BANK_CARD);
        Transaction transactiony = new Transaction(-600000, sqLiteUtil.getGroupById(3), "Thu tiền lương", new GregorianCalendar(2019, Calendar.OCTOBER, 6).getTime(), WalletType.BANK_CARD);
        Transaction transactionz = new Transaction(-1500000, sqLiteUtil.getGroupById(4), "Thu tiền lương", new GregorianCalendar(2019, Calendar.OCTOBER, 6).getTime(), WalletType.BANK_CARD);
        Transaction transactionzz = new Transaction(-1250000, sqLiteUtil.getGroupById(4), "Thu tiền lương", new GregorianCalendar(2019, Calendar.OCTOBER, 30).getTime(), WalletType.BANK_CARD);
        Transaction transactionzx = new Transaction(1000000, sqLiteUtil.getGroupById(4), "Thu tiền lương", new GregorianCalendar(2019, Calendar.DECEMBER, 1).getTime(), WalletType.BANK_CARD);
//
        Transaction transaction12 = new Transaction(-50000, sqLiteUtil.getGroupById(1), "Mua kẹo", Calendar.getInstance().getTime(), WalletType.NORMAL_WALLET);
        Transaction transaction123 = new Transaction(1000000, sqLiteUtil.getGroupById(22), "Thu tiền lương", Calendar.getInstance().getTime(), WalletType.BANK_CARD);
//
        sqLiteUtil.insertTransaction(transaction);
        sqLiteUtil.insertTransaction(transaction12);
        sqLiteUtil.insertTransaction(transaction123);
        sqLiteUtil.insertTransaction(transactionx);
        sqLiteUtil.insertTransaction(transactiony);
        sqLiteUtil.insertTransaction(transactionz);
        sqLiteUtil.insertTransaction(transactionzz);
        sqLiteUtil.insertTransaction(transactionzx);

//
        List<Transaction> transactions = sqLiteUtil.getAllTransaction();
//
//        Transaction transaction1 = sqLiteUtil.getTransactionById(1);
//
//        List<Transaction> transactionInRange = sqLiteUtil.getTransactionInRange(new GregorianCalendar(2019, Calendar.NOVEMBER, 1).getTime(),  new GregorianCalendar(2019, Calendar.NOVEMBER, 28).getTime());
//
//        List<Transaction> transactionInRange1 = sqLiteUtil.getTransactionInRange(new GregorianCalendar(2019, Calendar.DECEMBER, 1).getTime(),  new GregorianCalendar(2019, Calendar.DECEMBER, 30).getTime());
//
//
        System.out.println(sqLiteUtil.getCurrentMoney(WalletType.BOTH));
        System.out.println(sqLiteUtil.getCurrentMoney(WalletType.BANK_CARD));
        System.out.println(sqLiteUtil.getCurrentMoney(WalletType.NORMAL_WALLET));

    }
}
