package vn.edu.fithou.quanlychitieu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import vn.edu.fithou.quanlychitieu.R;
import vn.edu.fithou.quanlychitieu.adapter.TransactionListViewAdapter;
import vn.edu.fithou.quanlychitieu.model.Transaction;
import vn.edu.fithou.quanlychitieu.model.TransactionDate;
import vn.edu.fithou.quanlychitieu.model.TransactionGroup;
import vn.edu.fithou.quanlychitieu.model.TransactionStatistic;
import vn.edu.fithou.quanlychitieu.model.WalletType;

public class TransactionFragment extends Fragment {

    private RecyclerView rvTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transaction, container, false);

        rvTransaction = rootView.findViewById(R.id.rvTransaction);

        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());


    ArrayList<Object> data = new ArrayList<>();

        data.add(new TransactionStatistic(100000, 1800000));

        data.add(new TransactionDate(Calendar.getInstance().getTime(), 1800000));
        data.add(new Transaction(50000, new TransactionGroup(1, "Hóa đơn điện", R.drawable.ic_wallet_24dp, TransactionGroup.OUTGOING), "Dont know", Calendar.getInstance().getTime(), WalletType.NORMAL_WALLET));
        data.add(new Transaction(50000, new TransactionGroup(1, "Luơng", R.drawable.ic_wallet_24dp, TransactionGroup.INCOMING), "Dont know", Calendar.getInstance().getTime(), WalletType.NORMAL_WALLET));
        data.add(new Transaction(50000, new TransactionGroup(1, "Hóa đơn nước", R.drawable.ic_wallet_24dp, TransactionGroup.OUTGOING), "Dont know", Calendar.getInstance().getTime(), WalletType.BANK_CARD));
        data.add(new Transaction(50000, new TransactionGroup(1, "Thưởng", R.drawable.ic_wallet_24dp, TransactionGroup.INCOMING), "Dont know", Calendar.getInstance().getTime(), WalletType.NORMAL_WALLET));

        data.add(new TransactionDate(Calendar.getInstance().getTime(), 1800000));
        data.add(new Transaction(50000, new TransactionGroup(1, "Hóa đơn điện", R.drawable.ic_wallet_24dp, TransactionGroup.OUTGOING), "Dont know", Calendar.getInstance().getTime(), WalletType.NORMAL_WALLET));
        data.add(new Transaction(50000, new TransactionGroup(1, "Hóa đơn điện", R.drawable.ic_wallet_24dp, TransactionGroup.OUTGOING), "Dont know", Calendar.getInstance().getTime(), WalletType.NORMAL_WALLET));

        TransactionListViewAdapter adapter = new TransactionListViewAdapter(getActivity(), data);
        rvTransaction.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTransaction.setAdapter(adapter);
        return rootView;
    }
}
