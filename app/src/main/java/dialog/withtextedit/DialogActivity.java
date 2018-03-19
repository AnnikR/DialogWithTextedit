package dialog.withtextedit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity implements NumpadDialogFragment.ChoiceListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    protected void startDialog_clicked(View v)
    {
        NumpadDialogFragment dialog = new NumpadDialogFragment();
        dialog.show(getFragmentManager(), "AnswerDia");
    }

    public void onDialogPositiveClick(android.app.DialogFragment dialog){
        Toast.makeText(getBaseContext(), "OK clicked", Toast.LENGTH_SHORT).show();
    }
    public void onDialogNegativeClick(android.app.DialogFragment dialog){
        Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
    }

    public void onDialogNumberClickCorrect(android.app.DialogFragment dialog)
    {
        Toast.makeText(getBaseContext(), "Oikein!", Toast.LENGTH_SHORT).show();
        //return true;
    }

    public void onDialogNumberClickWrong(android.app.DialogFragment dialog)
    {
        Toast.makeText(getBaseContext(), "Väärin!", Toast.LENGTH_SHORT).show();
        //return false;
    }
}
