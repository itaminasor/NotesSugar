package pe.com.itaminasor.notessugar.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pe.com.itaminasor.notessugar.R;
import pe.com.itaminasor.notessugar.repository.UserRepository;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText fullnameInput;
    private EditText emailInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameInput = (EditText)findViewById(R.id.username_input);
        fullnameInput = (EditText)findViewById(R.id.fullname_input);
        emailInput =    (EditText)findViewById(R.id.email_input);
        passwordInput = (EditText)findViewById(R.id.password_input);

    }

    public void callRegister(View view){

        String username = usernameInput.getText().toString();
        String fullname = fullnameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(username.isEmpty() || fullname.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "You must complete these fields", Toast.LENGTH_SHORT).show();
            return;
        }

        UserRepository.create( username,  fullname,  email,  password);

        Intent intent = new Intent();
        intent.putExtra("username", username);

        setResult(RESULT_OK, intent);
        finish();

    }

}
