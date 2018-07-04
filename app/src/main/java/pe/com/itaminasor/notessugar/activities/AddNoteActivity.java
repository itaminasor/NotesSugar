package pe.com.itaminasor.notessugar.activities;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pe.com.itaminasor.notessugar.R;
import pe.com.itaminasor.notessugar.repository.NoteRepository;
import pe.com.itaminasor.notessugar.repository.UserRepository;
import pe.com.itaminasor.notessugar.util.Constants;

public class AddNoteActivity extends AppCompatActivity {


    private static final String TAG = AddNoteActivity.class.getSimpleName();

    // SharedPreferences
    private SharedPreferences sharedPreferences;

    private EditText titleInput;
    private EditText contentInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleInput = (EditText)findViewById(R.id.title_input);
        contentInput = (EditText)findViewById(R.id.content_input);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    }

    public void callRegister(View view){

        String title = titleInput.getText().toString();
        String content = contentInput.getText().toString();
        Long id = sharedPreferences.getLong(Constants.IDUSER,0);

        if(title.isEmpty() || content.isEmpty() ){
            Toast.makeText(this, "You must complete these fields", Toast.LENGTH_SHORT).show();
            return;
        }

        NoteRepository.create(id,  title,  content);

        finish();

    }
}
