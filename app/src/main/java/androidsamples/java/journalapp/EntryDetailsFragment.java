package androidsamples.java.journalapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.UUID;

public class EntryDetailsFragment extends Fragment {
  private static final String TAG = "EntryDetailsFragment";
  private EditText mEditTitle;
  private Button mBtnDate, mBtnStartTime, mBtnEndTime;
  private EntryDetailsViewModel mEntryDetailsViewModel;
  private JournalEntry mEntry;

  //Why are we using onCreate as well as onCreateView
  // when we can just be done with onCreateView and onViewCreated

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    setHasOptionsMenu(true);
    UUID entryId = UUID.fromString(EntryDetailsFragmentArgs.fromBundle(getArguments()).getEntryId());
    mEntryDetailsViewModel = new ViewModelProvider(getActivity()).get(EntryDetailsViewModel.class);
    mEntryDetailsViewModel.loadEntry(entryId);
    View view = inflater.inflate(R.layout.fragment_entry_details, container, false);
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mEditTitle = view.findViewById(R.id.edit_title);
    mBtnDate = view.findViewById(R.id.btn_entry_date);
    mBtnStartTime = view.findViewById(R.id.btn_start_time);
    mBtnEndTime = view.findViewById(R.id.btn_end_time);
    view.findViewById(R.id.btn_save).setOnClickListener(this::saveEntry);
    view.findViewById(R.id.btn_start_time).setOnClickListener(this::setStartTime);
    view.findViewById(R.id.btn_end_time).setOnClickListener(this::setEndTime);
    view.findViewById(R.id.btn_entry_date).setOnClickListener(this::setEntryDate);

    mEntryDetailsViewModel.getEntryLiveData().observe(getActivity(),
            entry -> {
              this.mEntry = entry;
              if (entry != null) {
                updateUI();
              }
            });
  }

  private void setEntryDate(View view) {
    Navigation.findNavController(view).navigate(R.id.datePickerAction);
  }

  private void setEndTime(View view) {
    mEntryDetailsViewModel.setIsStartTime(false);
    Navigation.findNavController(view).navigate(R.id.timePickerAction);
  }

  private void setStartTime(View view) {
    mEntryDetailsViewModel.setIsStartTime(true);
    Navigation.findNavController(view).navigate(R.id.timePickerAction);
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.fragment_entry_detail, menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.menu_delete_entry) {
      new AlertDialog.Builder(getContext()).setTitle("Delete?").setMessage("Are you sure?").setPositiveButton("YES",(delete, back) ->
        {
          mEntryDetailsViewModel.deleteEntry(mEntry);
          getActivity().onBackPressed();
        }
      ).setNegativeButton("NO", null).show();
    }
    else if (item.getItemId() == R.id.menu_share_entry) {
      Intent shareIntent = new Intent();
      shareIntent.setAction(Intent.ACTION_SEND);
      String message = "Look what I have been up to: " + mEntry.title() + " on " + mEntry.getDate() + ", " + mEntry.startTime() + " to " + mEntry.endTime();
      shareIntent.putExtra(Intent.EXTRA_TEXT, message);
      shareIntent.setType("text/*");
      startActivity(shareIntent);
    }
    return super.onOptionsItemSelected(item);
  }

  private void updateUI() {
    if(!mEntry.getDate().equals(""))
      mBtnDate.setText(mEntry.getDate());
      mEditTitle.setText(mEntry.title());
    if(!mEntry.startTime().equals(""))
      mBtnStartTime.setText(mEntry.startTime());
    if(!mEntry.endTime().equals(""))
      mBtnEndTime.setText(mEntry.endTime());
  }

  private void saveEntry(View v) {
    mEntry.setTitle(mEditTitle.getText().toString());
    mEntry.setDate(mBtnDate.getText().toString());
    mEntry.setStartTime(mBtnStartTime.getText().toString());
    mEntry.setEndTime(mBtnEndTime.getText().toString());

    mEntryDetailsViewModel.saveEntry(mEntry);
    getActivity().onBackPressed();
  }
}