# a3-journalapp-Qarthak

Sarthak Chaudhary	

2019A7PS0125G

f20190125@goa.bits-pilani.ac.in	

Journal App

## What does the app do?

It stores information along with start and end times and the date. It can be edited, deleted and shared

## Known Bugs:

If one adds an entry and then decides to go back without saving it still saves. One can think of this as a autosave feature, although it was not intended by design

# Process:

1. I implemented the nav graphs and the actions using the UI instead of adding in code. They can referenced using the same method each time. I didn't pass anything using the nav graphs and instead used sharedViewModel to pass the one bit info that I need to pass( StartTime Pressed or EndTime Pressed )
2. The modification was pretty easy. I just copied insert and changed it to delete. I didn't get time to test the database using code but I did try it against all persistance problems and it still worked
3. Added a delete option in the menu xml file and I added a alert dialog which shows the warning "Are you Sure?"
4. I used Intent. I set the type of the intent as "text/*" which means it can handle texts and hyperlinks and JSON as opposed to text/plain which only handles texts
5. I used an empty fragment and used the UI to add the fragment to the nav graph and wasn't able to add the action through the UI so I added that in code. The fragment had a lot of boilerplate which was giving error so I deleted all that and only inserted what was neccesary
6. I tried the accessibility check but the library was not updated to work for M1. Similarly, the version of room library we were using was also not working with M!. I fixed it using a alpha release. There was a beta release too but it added a constrain android:exported=true which I wasn't able to satisfy for all the components

# Testing

Manually tested every inch of the app. It crashed numerous times and the fixes were sometimes absurd. I've added some in the comments

# Completion Time

20 hours

# Difficulty

9/10

Had to learn a lot of things but they were easily available

One place I straight up copy pasted code from:

https://stackoverflow.com/questions/27225815/android-how-to-show-datepicker-in-fragment
