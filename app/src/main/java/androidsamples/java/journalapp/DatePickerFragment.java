package androidsamples.java.journalapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

  Calendar mCalendar;

  @NonNull
  public static DatePickerFragment newInstance(Date date) {
    return new DatePickerFragment();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    mCalendar = Calendar.getInstance();
    mCalendar.setLenient(false);
    int currentYear = mCalendar.get(Calendar.YEAR);
    int currentMonth = mCalendar.get(Calendar.MONTH);
    int currentDay = mCalendar.get(Calendar.DAY_OF_MONTH);
    return new DatePickerDialog(requireContext(), this, currentYear, currentMonth, currentDay);
  }

  public void onDateSet(DatePicker view, int year, int month, int day) {
    mCalendar.set(year,month+1,day);  //Month goes from 0 to 1 indexed
    Button mBtnEntryDate = getActivity().findViewById(R.id.btn_entry_date);
    String fullFormat=mCalendar.getTime().toString();
    String dayDate=fullFormat.substring(0,3)+","+fullFormat.substring(3,10)+" ,"+fullFormat.substring(30,34);
    mBtnEntryDate.setText(dayDate);
  }
}


