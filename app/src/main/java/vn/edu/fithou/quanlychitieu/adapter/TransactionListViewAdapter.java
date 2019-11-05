package vn.edu.fithou.quanlychitieu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.fithou.quanlychitieu.R;
import vn.edu.fithou.quanlychitieu.model.Transaction;
import vn.edu.fithou.quanlychitieu.model.TransactionDate;
import vn.edu.fithou.quanlychitieu.model.TransactionGroup;
import vn.edu.fithou.quanlychitieu.model.TransactionStatistic;
import vn.edu.fithou.quanlychitieu.model.WalletType;
import vn.edu.fithou.quanlychitieu.util.ConversionUtil;
import vn.edu.fithou.quanlychitieu.util.DateUtil;
import vn.edu.fithou.quanlychitieu.util.GroupIconUtil;

public class TransactionListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int TYPE_STATISTIC = 1;
    private static int TYPE_HEADER = 2;
    private static int TYPE_ITEM = 3;
    private Context context;
    private ArrayList<Object> data;

    public TransactionListViewAdapter(Context context, ArrayList<Object> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == TYPE_STATISTIC) {
            view = LayoutInflater.from(context).inflate(R.layout.row_statistic, viewGroup, false);
            return new TransactionStatisticViewHolder(view);
        } else if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(context).inflate(R.layout.row_trans_header, viewGroup, false);
            return new TransactionHeaderViewHolder(view);
        } else if (viewType == TYPE_ITEM) {
            view = LayoutInflater.from(context).inflate(R.layout.row_trans_item, viewGroup, false);
            return new TransactionItemViewHolder(view);
        }
        view = LayoutInflater.from(context).inflate(R.layout.row_statistic, viewGroup, false);
        return new TransactionStatisticViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = data.get(position);
        if (obj instanceof Transaction) {
            return TYPE_ITEM;
        } else if (obj instanceof TransactionDate) {
            return TYPE_HEADER;
        } else if (obj instanceof TransactionStatistic) {
            return TYPE_STATISTIC;
        }
        return TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_ITEM) {
            ((TransactionItemViewHolder) viewHolder).setTransaction((Transaction) data.get(position));
        } else if (getItemViewType(position) == TYPE_HEADER) {
            ((TransactionHeaderViewHolder) viewHolder).setTransactionDate((TransactionDate) data.get(position));
        } else if (getItemViewType(position) == TYPE_STATISTIC) {
            ((TransactionStatisticViewHolder) viewHolder).setTransactionStatistic((TransactionStatistic) data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class TransactionStatisticViewHolder extends RecyclerView.ViewHolder {

        private TextView tvBeginMoney;
        private TextView tvEndMoney;
        private TextView tvRemainMoney;

        TransactionStatisticViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBeginMoney = itemView.findViewById(R.id.tv_begin_money_value);
            tvEndMoney = itemView.findViewById(R.id.tv_end_money_value);
            tvRemainMoney = itemView.findViewById(R.id.tv_remain_money);
        }

        void setTransactionStatistic(TransactionStatistic transactionStatistic) {
            tvBeginMoney.setText(ConversionUtil.doubleToString(transactionStatistic.getBeginMoneyAmount()));
            tvEndMoney.setText(ConversionUtil.doubleToString(transactionStatistic.getEndMoneyAmount()));
            tvRemainMoney.setText(ConversionUtil.doubleToString(transactionStatistic.getEndMoneyAmount() - transactionStatistic.getBeginMoneyAmount()));
        }
    }

    class TransactionHeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDayOfMonth;
        private TextView tvDayOfWeek;
        private TextView tvMonth;
        private TextView tvAmount;

        TransactionHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDayOfMonth = itemView.findViewById(R.id.row_trans_header_day_of_month);
            tvDayOfWeek = itemView.findViewById(R.id.row_trans_header_day_of_week);
            tvMonth = itemView.findViewById(R.id.row_trans_header_month);
            tvAmount = itemView.findViewById(R.id.row_trans_header_amount);
        }

        void setTransactionDate(TransactionDate transactionDate) {
            tvDayOfWeek.setText(DateUtil.getDayOfWeek(transactionDate.getDate()));
            tvDayOfMonth.setText(DateUtil.getDayOfMonth(transactionDate.getDate()));
            tvAmount.setText(ConversionUtil.doubleToString(transactionDate.getMoneyAmount()));
            tvMonth.setText(DateUtil.getMonthAndYear(transactionDate.getDate()));
        }
    }

    class TransactionItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivTransactionGroupIcon;
        private TextView tvGroupName;
        private TextView tvNote;
        private TextView tvType;
        private TextView tvAmount;

        TransactionItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ivTransactionGroupIcon = itemView.findViewById(R.id.transaction_group_icon);
            tvGroupName = itemView.findViewById(R.id.row_trans_item_group_name);
            tvNote = itemView.findViewById(R.id.row_trans_item_note);
            tvType = itemView.findViewById(R.id.row_trans_item_type);
            tvAmount = itemView.findViewById(R.id.row_trans_item_amount);
        }

        void setTransaction(Transaction transaction) {
            ivTransactionGroupIcon.setImageResource(GroupIconUtil.getGroupIcon(transaction.getGroup()));
            tvGroupName.setText(transaction.getGroup().getName());
            tvNote.setText(transaction.getNote());
            tvType.setText(transaction.getWalletType() == WalletType.BANK_CARD ? "Thẻ ngân hàng" : "Ví thường");
            tvAmount.setText(ConversionUtil.doubleToString(transaction.getMoneyAmount()));
            if (transaction.getGroup().getType() == TransactionGroup.INCOMING) {
                tvAmount.setTextColor(Color.GREEN);
            } else {
                tvAmount.setTextColor(Color.RED);
            }
        }
    }

}
