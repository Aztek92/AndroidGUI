package aztek.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class GradesActivity extends AppCompatActivity {

    ListView gradesList;
    Integer gradesAmount;
    ArrayList<GradesModel> data = new ArrayList<GradesModel>();
    public final static String AVERAGE="AVERAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        LinearLayout scheme = (LinearLayout) findViewById(R.id.scheme);

        if (savedInstanceState == null) {
            Bundle info = getIntent().getExtras();
            gradesAmount =  info.getInt(MainActivity.GRADES_AMOUNT);
        } else gradesAmount = 5;

        for(int i = 0; i < gradesAmount; i++) {
            data.add(new GradesModel("Ocena " + (i+1) + ": ",2));
        }

        Integer dataSize = data.size();
        Adapter adapter = new Adapter(this,data);
        ListView gradesList = (ListView) findViewById(R.id.gradesList);
        gradesList.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button readyButton = (Button) findViewById(R.id.readyButton);
        readyButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        float avrg = 0.0f;
                        for(int i=0;i<data.size();i++) {
                            if(data.get(i).getGrade() >= 2) avrg += (float)data.get(i).getGrade();
                            else avrg += 2;
                        }
                        avrg /= (float)data.size();
                        Intent switch2main = new Intent(GradesActivity.this, MainActivity.class);
                        switch2main.putExtra(AVERAGE,Float.toString(avrg));
                        setResult(RESULT_OK,switch2main);
                        finish();
                    }
                }
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onBackPressed() {
        Intent switch2main = new Intent(GradesActivity.this, MainActivity.class);
        startActivity(switch2main);
    }

    public void createRadioButton(LinearLayout scheme, int n) {
        RadioButton[] radioB = new RadioButton[5];
        RadioGroup radioG = new RadioGroup(this);
        radioG.setOrientation(RadioGroup.HORIZONTAL);
        radioG.setId(n);
        TextView tw = new TextView(this);
        Integer nn = n+1;
        tw.setText("Ocena " + nn);
        tw.setId(n);
        scheme.addView(tw);
        Integer ii = 0;
        for(int i = 0; i<5; i++) {
            ii = i+1;
            radioB[i] = new RadioButton(this);
            radioB[i].setId(i+100+(n*10));
            radioB[i].setText(ii.toString());
            radioG.addView(radioB[i]);
        }
        scheme.addView(radioG);
    }
}
