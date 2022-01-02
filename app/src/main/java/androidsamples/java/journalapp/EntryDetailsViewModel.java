package androidsamples.java.journalapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.UUID;

public class EntryDetailsViewModel extends ViewModel {
  private static final String TAG = "EntryDetailsViewModel";
  private final JournalRepository mRepository;
  private   boolean isStartTime;
  private int hours;

  private int minutes;

  public int getHours() {
    return hours;
  }

  public void setHours(int hours) {
    this.hours = hours;
  }

  public int getMinutes() {
    return minutes;
  }

  public void setMinutes(int minutes) {
    this.minutes = minutes;
  }


  private final MutableLiveData<UUID> entryIdLiveData = new MutableLiveData<>();

  public EntryDetailsViewModel() {
    mRepository = JournalRepository.getInstance();
  }

  LiveData<JournalEntry> getEntryLiveData() {
    Log.d(TAG, "getEntryLiveData called");
    return Transformations.switchMap(entryIdLiveData, mRepository::getEntry);
  }

  void loadEntry(UUID entryId) {
    Log.d(TAG, "loading entry: " + entryId);
    entryIdLiveData.setValue(entryId);
  }

  void setIsStartTime(boolean num){
    isStartTime=num;
  }

  boolean getIsStartTime(){
    return isStartTime;
  }

  void saveEntry(JournalEntry entry) {
    Log.d(TAG, "Saving entry: " + entry.getUid());
    mRepository.update(entry);
  }

  void deleteEntry(JournalEntry entry) {
    Log.d(TAG, "DEleting entry: " + entry.getUid());
    mRepository.delete(entry);
  }
}
