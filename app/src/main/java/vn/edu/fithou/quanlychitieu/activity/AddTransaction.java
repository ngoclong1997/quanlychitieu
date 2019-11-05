package vn.edu.fithou.quanlychitieu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import vn.edu.fithou.quanlychitieu.R;
import vn.edu.fithou.quanlychitieu.util.DateUtil;

public class AddTransaction extends AppCompatActivity implements View.OnClickListener {

    TextView tvDatePickerValue;
    EditText etAmountOfMoney;
    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        mapView();

        init();
    }

    private void init() {
        tvDatePickerValue.setOnClickListener(this);
        etAmountOfMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etAmountOfMoney.removeTextChangedListener(this);
                try {
                    String givenstring = editable.toString();
                    Long longval;
                    if (givenstring.contains(",")) {
                        givenstring = givenstring.replaceAll(",", "");
                    }
                    longval = Long.parseLong(givenstring);
                    DecimalFormat formatter = new DecimalFormat("#,###,###");
                    String formattedString = formatter.format(longval);
                    etAmountOfMoney.setText(formattedString);
                    etAmountOfMoney.setSelection(etAmountOfMoney.getText().length());
                    // to place the cursor at the end of text
                } catch (Exception nfe) {
                    nfe.printStackTrace();
                }

                etAmountOfMoney.addTextChangedListener(this);
            }
        });
    }

    private void mapView() {
        tvDatePickerValue = findViewById(R.id.tvDatePickerValue);
        etAmountOfMoney = findViewById(R.id.etAmountOfMoney);
        tvDatePickerValue.setText(DateUtil.formatDate(Calendar.getInstance().getTime()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvDatePickerValue:
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddTransaction.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                tvDatePickerValue.setText(DateUtil.formatDate((new GregorianCalendar(year, monthOfYear, dayOfMonth)).getTime()));
                            }
                        }, year, month, day);
                picker.show();
                break;
        }
    }
}
