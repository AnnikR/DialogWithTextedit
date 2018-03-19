package dialog.withtextedit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Annika on 16.3.2018.
 * Numpad for Snippy's Journey
 */

public class NumpadDialogFragment extends DialogFragment {

    ChoiceListener AnswerListener;

    public interface ChoiceListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);

//        void onDialogNumberClickCorrect(DialogFragment dialog);
//        void onDialogNumberClickWrong(DialogFragment dialog);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ChoiceListener) {
            AnswerListener = (ChoiceListener) context;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //määritellään kysymyksiä varten kaksi arpoutuvaa numeroa
        Random r = new Random();
        int number1 = r.nextInt(6-1)+ 1;
        int number2 = r.nextInt(5-1)+ 1;

        String questionOnScreen = "Laske: " + String.valueOf(number1) + " + " + String.valueOf(number2);
        final Integer answer = number1 + number2;

//        final String numberToEdittext;

        final LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        //uusi AlertDialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        final EditText typedAnswerText = new EditText(getActivity());
        typedAnswerText.setHint("Vastaus");
        typedAnswerText.setInputType(InputType.TYPE_NULL);
        layout.addView(typedAnswerText);

        //määritellään GridViewiin numerot 1-9 kolmen riveihin
        final GridView numberGridView = new GridView(getActivity());
        List<Integer> numberList = new ArrayList<>();
        for (int i = 1; i < 10; i++){
            numberList.add(i);
        }
        numberGridView.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,numberList));
        numberGridView.setNumColumns(3);
        numberGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                typedAnswerText.setText((Integer) numberGridView.getItemAtPosition(position));
                String selectedItem = parent.getItemAtPosition(position).toString();
                typedAnswerText.setText("Vastaus : " + selectedItem);
            }
        });
        layout.addView(numberGridView);

//        typedAnswerText.setInputType(InputType.TYPE_CLASS_NUMBER);

        dialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //OK button clicked
//                        String answerString = answerText.getText().toString();
                        AnswerListener.onDialogPositiveClick(NumpadDialogFragment.this);
                    }
                });
        dialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Cancel button clicked
                        AnswerListener.onDialogNegativeClick(NumpadDialogFragment.this);
                    }
                });

        //määritä AlertDialogin otsikoksi kysymys, ja otsikon alle näkymäksi numeroruudukko.
        dialogBuilder.setTitle(questionOnScreen);
//        dialogBuilder.setTitle("%s + s% = ", number1, number2);
//        dialogBuilder.setView(typedAnswerText);
        dialogBuilder.setView(layout);
        return dialogBuilder.create();
    }
}
