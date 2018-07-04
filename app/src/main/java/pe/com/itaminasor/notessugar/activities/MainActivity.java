package pe.com.itaminasor.notessugar.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import pe.com.itaminasor.notessugar.R;
import pe.com.itaminasor.notessugar.model.User;
import pe.com.itaminasor.notessugar.repository.UserRepository;
import pe.com.itaminasor.notessugar.util.Constants;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int REGISTER_FORM_REQUEST = 100;

    // SharedPreferences
    private SharedPreferences sharedPreferences;

    private EditText usernameInput;
    private EditText passwordInput;
    private ProgressBar progressBar;
    private View loginPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        usernameInput = (EditText)findViewById(R.id.username_input);
        passwordInput = (EditText)findViewById(R.id.password_input);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        loginPanel = findViewById(R.id.login_panel);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // username remember
        String username = sharedPreferences.getString(Constants.USERNAME, null);
        if(username != null){
            usernameInput.setText(username);
            passwordInput.requestFocus();
        }

        // islogged remember
        if(sharedPreferences.getBoolean(Constants.ISLOGGED, false)){
            // Go to Dashboard

            goNotes();
        }
    }

    public void callLogin(View view){
        loginPanel.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "You must complete these fields", Toast.LENGTH_SHORT).show();
            loginPanel.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;
        }

        // Login logic
        User user = UserRepository.login(username, password);

        if(user == null){
            Toast.makeText(this, "Username or password invalid", Toast.LENGTH_SHORT).show();
            loginPanel.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;
        }

        Toast.makeText(this, "Welcome " + user.getFullname(), Toast.LENGTH_SHORT).show();

        // Save to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean success = editor
                .putString(Constants.USERNAME, user.getUsername())
                .putLong(Constants.IDUSER,user.getId())
                .putBoolean(Constants.ISLOGGED, true)
                .commit();

        // Go to Dashboard
        goNotes();
    }

    private void goNotes(){
        startActivity(new Intent(this, NoteActivity.class));
        finish();
    }

    public void callRegister(View view){
        //startActivity(new Intent(this, RegisterActivity.class));

        startActivityForResult(new Intent(this, RegisterActivity.class), REGISTER_FORM_REQUEST);
    }

    // return from RegisterActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String username = data.getExtras().getString("username");
        usernameInput.setText(username);


    }



}
