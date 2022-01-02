package androidsamples.java.journalapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

  Calendar mCalender;
  public static final String KEY_DATE = "KEY_DATE";
  EntryDetailsViewModel mEntryDetailsViewModel;
  @NonNull
  public static TimePickerFragment newInstance(Date time) {
    // TODO implement the method

    return null;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    mEntryDetailsViewModel = new ViewModelProvider(getActivity()).get(EntryDetailsViewModel.class);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    // TODO implement the method
        final Calendar c = Calendar.getInstance();
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);
    return new TimePickerDialog(getActivity(), this, hour, minute,false);
  }

  public void onTimeSet(TimePicker view, int hour, int minute) {
//    if(mEntryDetailsViewModel.getIsStartTime()){
      mEntryDetailsViewModel.setHours(hour);
      mEntryDetailsViewModel.setMinutes(minute);
      Button mBtnTime;
      if(mEntryDetailsViewModel.getIsStartTime()) {
        mBtnTime = getActivity().findViewById(R.id.btn_start_time);
      }
      else{
        mBtnTime = getActivity().findViewById(R.id.btn_end_time);
      }
      mBtnTime.setText(hour+" : "+minute);

  }
}
