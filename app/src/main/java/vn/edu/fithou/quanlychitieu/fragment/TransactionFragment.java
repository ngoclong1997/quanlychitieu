package vn.edu.fithou.quanlychitieu.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import vn.edu.fithou.quanlychitieu.R;
import vn.edu.fithou.quanlychitieu.adapter.TransactionListViewAdapter;
import vn.edu.fithou.quanlychitieu.model.Transaction;
import vn.edu.fithou.quanlychitieu.model.TransactionDate;
import vn.edu.fithou.quanlychitieu.model.TransactionGroup;
import vn.edu.fithou.quanlychitieu.model.TransactionStatistic;
import vn.edu.fithou.quanlychitieu.model.WalletType;
import vn.edu.fithou.quanlychitieu.util.ConversionUtil;
import vn.edu.fithou.quanlychitieu.util.DateUtil;
import vn.edu.fithou.quanlychitieu.util.SQLiteUtil;

public class TransactionFragment extends Fragment implements View.OnClickListener {

    private Date currentDate;

    private TextView tvTotalMoney, tvPreviousDate, tvNextPage, tvCurrentPage, tvNoTransaction;

    SQLiteUtil sqLiteUtil;

    ProgressDialog progressDialog;

    ArrayList<Object> data;

    RecyclerView rvTransaction;

    TransactionListViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transaction, container, false);

         rvTransaction = rootView.findViewById(R.id.rvTransaction);


        rootView.findViewById(R.id.transaction_next_page).setOnClickListener(this);
        rootView.findViewById(R.id.transaction_prev_page).setOnClickListener(this);

        tvTotalMoney = rootView.findViewById(R.id.tvTotalMoney);
        tvPreviousDate = rootView.findViewById(R.id.tvPreviousPage);
        tvNextPage = rootView.findViewById(R.id.tvNextPage);
        tvCurrentPage = rootView.findViewById(R.id.tvCurrentPage);
        tvNoTransaction = rootView.findViewById(R.id.tvNoTransaction);

        progressDialog = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
        progressDialog.setCancelable(true);

        sqLiteUtil = new SQLiteUtil(getActivity());

        currentDate = Calendar.getInstance().getTime();

        data = new ArrayList<>();

        adapter = new TransactionListViewAdapter(getActivity(), data);
        rvTransaction.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTransaction.setAdapter(adapter);

        fetchAndFillData();
        updatePagesTitle();
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.transaction_next_page:
                btnNextPageClick();
                break;
            case R.id.transaction_prev_page:
                btnPrevPageClick();
                break;
        }
    }

    private void btnNextPageClick() {

        int currentMonth = DateUtil.getMonth(currentDate);
        int currentYear = DateUtil.getYear(currentDate);

        int nowInMonth = DateUtil.getMonth(Calendar.getInstance().getTime());
        int nowInYear = DateUtil.getYear(Calendar.getInstance().getTime());

        if (nowInYear > currentYear || (nowInYear == currentYear && nowInMonth > currentMonth)) {
            currentDate = DateUtil.getNextMonth(currentDate);
            updatePagesTitle();
            fetchAndFillData();
        }
    }

    private void btnPrevPageClick() {
        currentDate = DateUtil.getPrevMonth(currentDate);
        updatePagesTitle();
        fetchAndFillData();
    }

    private void updatePagesTitle() {
        Date nextMonth = DateUtil.getNextMonth(currentDate);
        Date prevMonth = DateUtil.getPrevMonth(currentDate);

        String nextMonthText = DateUtil.formatDateBaseOnMonth(nextMonth);
        String prevMonthText = DateUtil.formatDateBaseOnMonth(prevMonth);
        String currentMonthText = DateUtil.formatDateBaseOnMonth(currentDate);

        tvPreviousDate.setText(prevMonthText);
        tvNextPage.setText(nextMonthText);
        tvCurrentPage.setText(currentMonthText);
    }

    private void fetchAndFillData() {
        progressDialog.setTitle("Đang tải");
        progressDialog.setMessage("Dữ liệu đang được lấy...");
        progressDialog.show();

        Date firstDay = DateUtil.getFirstDayOfThisMonth(currentDate);
        Date lastDay = DateUtil.getLastDayOfThisMonth(currentDate);
        List<Transaction> transactions = sqLiteUtil.getTransactionInRange(firstDay, lastDay);

        long firstDayMoney = sqLiteUtil.getMoneyFromADateTillNow(firstDay, WalletType.BOTH);
        long lastDayMoney = sqLiteUtil.getMoneyFromADateTillNow(DateUtil.getSmallerDate(lastDay, Calendar.getInstance().getTime()), WalletType.BOTH);

        long currentMoney = sqLiteUtil.getCurrentMoney(WalletType.BOTH);

        data.clear();

        data.add(new TransactionStatistic(firstDayMoney, lastDayMoney));

        if (transactions.size() != 0) {
            rvTransaction.setVisibility(View.VISIBLE);
            tvNoTransaction.setVisibility(View.GONE);
            int currentDay = -1;
            for (Transaction transaction : transactions) {
                if (DateUtil.getDayOfYear(transaction.getDate()) != currentDay) {
                    currentDay = DateUtil.getDayOfYear(transaction.getDate());
                    data.add(new TransactionDate(transaction.getDate(), sqLiteUtil.getMoneyInADay(transaction.getDate(), WalletType.BOTH)));
                }
                data.add(transaction);
            }
            tvTotalMoney.setText(ConversionUtil.doubleToString(currentMoney));
            adapter.notifyDataSetChanged();
        } else {
            rvTransaction.setVisibility(View.GONE);
            tvNoTransaction.setVisibility(View.VISIBLE);
        }

        progressDialog.dismiss();
    }

}
