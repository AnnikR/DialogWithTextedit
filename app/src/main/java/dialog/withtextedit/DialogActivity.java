package dialog.withtextedit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity implements NumpadDialogFragment.ChoiceListener{

    TextView textViewFalseTrue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        textViewFalseTrue = findViewById(R.id.textView2);
    }

    protected void startDialog_clicked(View v)
    {
        NumpadDialogFragment dialog = new NumpadDialogFragment();
        dialog.show(getFragmentManager(), "AnswerDia");
    }

    public void onDialogNumberClickCorrect(android.app.DialogFragment dialog)
    {
        Toast.makeText(getBaseContext(), "Oikein!", Toast.LENGTH_SHORT).show();
        textViewFalseTrue.setText("True");
    }

    public void onDialogNumberClickWrong(android.app.DialogFragment dialog)
    {
        Toast.makeText(getBaseContext(), "Väärin!", Toast.LENGTH_SHORT).show();
        textViewFalseTrue.setText("False");
    }
}
