package dialog.withtextedit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        void onDialogNumberClickCorrect(DialogFragment dialog);
        void onDialogNumberClickWrong(DialogFragment dialog);
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

        //uusi AlertDialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        //määritellään kysymyksiä varten kaksi arpoutuvaa numeroa
        Random r = new Random();
        int number1 = r.nextInt(6-1)+ 1;
        int number2 = r.nextInt(5-1)+ 1;

        //määritellään kysymys (questionOnScreen), oikea vastaus (correctAnswer) ja käyttäjän syöttämä vastaus (userAswer)
        String questionOnScreen = "Laske: " + String.valueOf(number1) + " + " + String.valueOf(number2);
        final Integer correctAnswer = number1 + number2;
        final int[] userAnswer = new int[1];

        //TextEdit vastauksen näyttämistä varten
        final EditText typedAnswerText = new EditText(getActivity());
        typedAnswerText.setText("Vastaus: ");
        typedAnswerText.setInputType(InputType.TYPE_NULL);

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
                String selectedItem = parent.getItemAtPosition(position).toString();
                userAnswer[0] = Integer.parseInt(selectedItem);
                typedAnswerText.setText("Vastaus: " + userAnswer[0]);
//                userAnswer.add(Integer.parseInt(selectedItem));

            }
        });

        dialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //OK button clicked

                        if(userAnswer[0] == correctAnswer)
//                        if(userAnswer.get(0) == correctAnswer)
                        {
                            //vastaus oikein
                            AnswerListener.onDialogNumberClickCorrect(NumpadDialogFragment.this);

                        }
                        else
                        {
                            //vastaus väärin
                            AnswerListener.onDialogNumberClickWrong(NumpadDialogFragment.this);
                        }
                    }
                });
        dialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Cancel button clicked

                        //"väärä" vastaus eli ei vastausta ollenkaan
                        AnswerListener.onDialogNumberClickWrong(NumpadDialogFragment.this);
                    }
                });
//        dialogBuilder.setNeutralButton("<-",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                        //Erase button clicked
//
//            }
//        });
        //lisätään EditText ja GridView LinearLayoutiin.
        final LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(typedAnswerText);
        layout.addView(numberGridView);

        //määritä AlertDialogin otsikoksi kysymys, ja otsikon alle näkymäksi numeroruudukko.
        dialogBuilder.setTitle(questionOnScreen);
        dialogBuilder.setView(layout);
        return dialogBuilder.create();
    }
}
