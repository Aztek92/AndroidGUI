package aztek.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText surname;
    EditText gradesAmount;
    Button gradesButton;
    boolean gradesAmountTF = false;
    boolean nameTF = false;
    boolean surnameTF = false;
    boolean result = false;
    String notification;
    public final static String GRADES_AMOUNT="grades_amount";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.nameBox);
        surname = (EditText) findViewById(R.id.surnameBox);
        gradesAmount = (EditText) findViewById(R.id.gradesamountBox);
        gradesButton = (Button) findViewById(R.id.gradesButton);

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override public void onFocusChange(View v, boolean hasFocus)        {
                changeFocusName(v, hasFocus);
            }});
        surname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override public void onFocusChange(View v, boolean hasFocus)        {
                changeFocusSurname(v, hasFocus);
            }});
        gradesAmount.addTextChangedListener(watcher);

        gradesButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        gradesButtonPress();
                    }
                }
        );
    }

    protected void onStart() {
        super.onStart();
        if(!result) Toast.makeText(MainActivity.this, getString(R.string.startNotification), Toast.LENGTH_LONG).show();


    }

    protected void onResume() { super.onResume(); }

    protected void onStop() { super.onStop(); }

    protected void onDestroy() { super.onDestroy(); }

    public void gradesButtonPress() {
        Intent switch2grades = new Intent(MainActivity.this,GradesActivity.class);
        switch2grades.putExtra(GRADES_AMOUNT, Integer.parseInt(gradesAmount.getText().toString()));
        startActivityForResult(switch2grades,1);
    }

    private void checkName() {
        if(name.length()==0) {
            nameTF = false;
            notification = getString(R.string.nameClear);
        }
        else nameTF = true;
    }

    private void checkSurname() {
        if(surname.length()==0) {
            surnameTF = false;
            notification = getString(R.string.surnameClear);
        }
        else surnameTF = true;
    }

    private void checkGradesAmount() {
        gradesAmountTF = false;
       try {
            if (gradesAmount.getText().toString().equals("")) {
                notification = getString(R.string.gradesAmountZero);
            } else if (Integer.parseInt(gradesAmount.getText().toString()) < 4) {
                notification = getString(R.string.gradesAmount2Few);
            } else if (Integer.parseInt(gradesAmount.getText().toString()) > 7) {
                notification = getString(R.string.gradesAmount2Many);
            } else gradesAmountTF = true;
        } catch (NumberFormatException e) {
        }
    }

    private void gradesButtonShow() {
        if (gradesAmountTF && nameTF && surnameTF && !result) gradesButton.setVisibility(View.VISIBLE);
        else gradesButton.setVisibility(View.GONE);
    }

    private void changeFocusName(View v, boolean hasFocus) {
        if(hasFocus) return;
        checkName();
        if(!nameTF && !result) Toast.makeText(MainActivity.this, notification, Toast.LENGTH_LONG).show();
        gradesButtonShow();
    }
    private void changeFocusSurname(View v, boolean hasFocus) {
        if(hasFocus) return;
        checkSurname();
        if(!surnameTF && !result) Toast.makeText(MainActivity.this, notification, Toast.LENGTH_LONG).show();
        gradesButtonShow();
    }
    private void changeFocus(View v, boolean hasFocus) {
        if(hasFocus) return;
        checkName();
        checkSurname();
        checkGradesAmount();
        if(!gradesAmountTF && !result) Toast.makeText(MainActivity.this, notification, Toast.LENGTH_LONG).show();
        gradesButtonShow();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            gradesButton.setVisibility(View.GONE);
            result = true;

            name.setFocusable(false);
            surname.setFocusable(false);
            gradesAmount.setFocusable(false);

            TextView avrgLabel = (TextView) findViewById(R.id.avrgLabel);
            TextView avrgScore = (TextView) findViewById(R.id.avrgScore);
            Button superButton = (Button)findViewById(R.id.superButton);
            Button badButton = (Button)findViewById(R.id.badButton);

            avrgLabel.setVisibility(View.VISIBLE);
            avrgScore.setVisibility(View.VISIBLE);

            Bundle info = data.getExtras();
            String avrString =  info.getString(GradesActivity.AVERAGE);
            Float avrFloat = Float.parseFloat(avrString);
            avrgScore.setText(Float.toString(avrFloat));
            if(avrFloat >=3.0) superButton.setVisibility(View.VISIBLE);
            else badButton.setVisibility(View.VISIBLE);

            superButton.setOnClickListener(
                    new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            Toast.makeText(MainActivity.this, R.string.success, Toast.LENGTH_LONG).show();
                            finish();

                        }
                    }
            );

            badButton.setOnClickListener(
                    new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            Toast.makeText(MainActivity.this, R.string.failure, Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
            );
        }
    }

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            checkName();
            checkSurname();
            checkGradesAmount();
            if(!gradesAmountTF && !result) Toast.makeText(MainActivity.this, notification, Toast.LENGTH_LONG).show();
            gradesButtonShow();
        }
    };
}


