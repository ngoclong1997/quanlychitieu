package vn.edu.fithou.quanlychitieu.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.fithou.quanlychitieu.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    TextView tvSignUp;
    Button btnLogin;
    EditText etSecretKey;
    String secretKey;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        mapView();

        progressDialog = new ProgressDialog(this, R.style.MyAlertDialogStyle);
        progressDialog.setCancelable(true);


        btnLogin.setOnClickListener(this);

    }

    private void mapView() {
        btnLogin = findViewById(R.id.btnLogin);
        etSecretKey = findViewById(R.id.etSecretKey);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                login();
                break;
        }
    }

    private void login() {
        secretKey = etSecretKey.getText().toString();

        authenticate(secretKey);
    }

    private void authenticate(String secretKey) {
        progressDialog.setTitle("Signing in");
        progressDialog.setMessage("You are signing in");
        progressDialog.show();

        AsyncTask<String, String, String> demoLogin = new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "done";
            }

            @Override
            protected void onPostExecute(String s) {
                if (s.equals("done")) {
                    progressDialog.dismiss();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        };

        demoLogin.execute();

    }
}