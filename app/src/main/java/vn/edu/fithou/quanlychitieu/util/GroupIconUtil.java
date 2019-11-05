package vn.edu.fithou.quanlychitieu.util;

import vn.edu.fithou.quanlychitieu.R;
import vn.edu.fithou.quanlychitieu.model.TransactionGroup;

public class GroupIconUtil {
    public static int getGroupIcon(TransactionGroup group) {
        switch (group.getName()) {
            default:
                return R.drawable.ic_wallet_24dp;
        }
    }
}
