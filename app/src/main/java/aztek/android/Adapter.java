package aztek.android;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aztek on 03.04.2017.
 */

public class Adapter extends ArrayAdapter<GradesModel> {
    private List<GradesModel> gradesModelList;
    private Activity context;


    public Adapter(Activity context, List<GradesModel> gradesModelList) {
        super(context, R.layout.scheme,gradesModelList);
        this.context = context;
        this.gradesModelList = gradesModelList;
    }

    @Override
    public View getView(int rowNumber, final View recycle, ViewGroup parent) {
        View view = null;
        LayoutInflater pump = context.getLayoutInflater();
        view = pump.inflate(R.layout.scheme, null);

        if(recycle == null) {
            final RadioGroup gradeGroup = (RadioGroup) view.findViewById(R.id.gradeGroup);
            gradeGroup.check(R.id.grade2);
            gradeGroup.setTag(gradesModelList.get(rowNumber));
            gradeGroup.setOnCheckedChangeListener(
                    new RadioGroup.OnCheckedChangeListener()
                    {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId)
                        {
                            RadioButton selectedRadioButton = (RadioButton) group.findViewById(checkedId);
                            GradesModel element = (GradesModel) group.getTag();
                            element.setGrade(Integer.parseInt(selectedRadioButton.getText().toString()));
                        }
                    }
            );
        }
        else {
            view = recycle;
            RadioGroup gradeGroup = (RadioGroup) view.findViewById(R.id.gradeGroup);
        }

        TextView label = (TextView) view.findViewById(R.id.gradeLabel);
        label.setText(gradesModelList.get(rowNumber).getName());
        RadioGroup gradeGroup = (RadioGroup) view.findViewById(R.id.gradeGroup);




    return view;
    }
}

