package com.eryxlabs.fiderides.ui.assessment.dialogs;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Result;
import com.eryxlabs.fiderides.ui.assessment.AssessmentViewModel;

public class MarkAssessment extends DialogFragment {

    AppCompatEditText description;
    RadioGroup gradeRadioGroup;
    RadioButton gradeRadioButtton,below_expectation_btn,approaching_expectation_btn,meeting_expectation_btn,exceeding_expectation_btn;
    AppCompatButton closeBtn,submitBtn;
    AssessmentViewModel assessmentViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root_view =  inflater.inflate(R.layout.dialog_mark_assessment,container,false);
        return root_view;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assessmentViewModel= ViewModelProviders.of(this).get(AssessmentViewModel.class);

        description=view.findViewById(R.id.description);
        gradeRadioGroup = (RadioGroup) view.findViewById(R.id.grade_radio_group);
        below_expectation_btn=view.findViewById(R.id.below_expectation);
        approaching_expectation_btn=view.findViewById(R.id.approaching_expectation);
        meeting_expectation_btn=view.findViewById(R.id.meeting_expectation);
        exceeding_expectation_btn=view.findViewById(R.id.exceeding_expectation);

        closeBtn=view.findViewById(R.id.close_btn);
        submitBtn=view.findViewById(R.id.submit_btn);

        if (getArguments()==null  || getArguments().getSerializable("result")==null){

            getDialog().dismiss();
            return;
        }

        closeBtn.setOnClickListener(h->{

            getDialog().dismiss();
        });
        Result result= (Result) getArguments().getSerializable("result");

        if (result==null){

            getDialog().dismiss();
            return;
        }

        if (result.getDescription() != null) {
            description.setText(result.getDescription());


        }

        if (result.getGrade() !=null){
            switch (Integer.valueOf(result.getGrade())){

                case 1:{
                    exceeding_expectation_btn.setChecked(true);
                    break;

                }
                case 2:{

                    meeting_expectation_btn.setChecked(true);
                    break;
                }
                case 3:{

                    approaching_expectation_btn.setChecked(true);
                    break;

                }
                case 4:{

                    below_expectation_btn.setChecked(true);
                    break;
                }

            }



        }

        gradeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadio = gradeRadioGroup.getCheckedRadioButtonId();
                gradeRadioButtton = (RadioButton) view.findViewById(checkedRadio);

            }
        });

        submitBtn.setOnClickListener(f->{

            if (gradeRadioGroup.getCheckedRadioButtonId() == -1)
            {
                // no radio buttons are checked

                Toast.makeText(getActivity(),"Please check on of the grade option before proceeding",Toast.LENGTH_SHORT).show();

                return;
            }
            int grade=0;
            switch (gradeRadioButtton.getText().toString()){
                case "Below expectation":{
                    grade=4;

                    break;

                }
                case "Approaching expectation":{

                    grade=3;
                    break;
                }

                case "Meeting expectation":{

                    grade=2;

                    break;
                }

                case "Exceeding expectation":{


                    grade=1;
                    break;
                }



            }

            result.setGrade(String.valueOf(grade));
            result.setDescription(description.getText().toString());
            assessmentViewModel.updateGradesOnline(result);


        });

    }
}
