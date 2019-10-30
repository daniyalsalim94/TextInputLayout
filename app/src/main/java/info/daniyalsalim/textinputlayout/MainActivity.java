package info.daniyalsalim.textinputlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import info.daniyalsalim.textinputlayout2.component;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private component ltvName;
    private component ltvGender;
    private component ltvDOb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ltvName = (component)findViewById( R.id.ltvName );
        ltvGender = (component)findViewById( R.id.ltvGender );
        ltvDOb = (component)findViewById( R.id.ltvDOb );



        ltvDOb.btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == ltvDOb.btn)
        {
            Calendar now = Calendar.getInstance();
            DatePickerDialog dpd = DatePickerDialog.newInstance(
                    MainActivity.this,
                    now.get(Calendar.YEAR), // Initial year selection
                    now.get(Calendar.MONTH), // Initial month selection
                    now.get(Calendar.DAY_OF_MONTH) // Inital day selection
            );

            dpd.show(getSupportFragmentManager(), "Datepickerdialog");
        }
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = +dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        ltvDOb.et.setText(date);
    }
}
