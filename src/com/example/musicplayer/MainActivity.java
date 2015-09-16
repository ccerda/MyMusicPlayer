package com.example.musicplayer;

import java.io.File;

import android.app.ActionBar;
import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity implements
		MusicFragment.onListItemClickListener {

	ImageButton playButton;
	ImageButton nextButton;
	ImageButton prevButton;
	TextView currentSongText;
	MusicPlayer mPlayer;
	MusicFragment mFragment;
	boolean isPlayPressed;
	boolean firstButtonPress;
	int currPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);

		ActionBar bar = getActionBar();
		playButton = (ImageButton) findViewById(R.id.play_button);
		prevButton = (ImageButton) findViewById(R.id.back_button);
		nextButton = (ImageButton) findViewById(R.id.next_button);
		currentSongText = (TextView) findViewById(R.id.current_song_view);
 		mPlayer = new MusicPlayer(this);
		isPlayPressed = false;
		firstButtonPress = false;
		onPlayButtonListener();
		onPrevButtonListener();
		onNextButtonListener();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onFragmentListener() {

	}

	public void onPlayButtonListener() {
		playButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(MainActivity.this, "Button works",
				//		Toast.LENGTH_LONG).show();
				if (isPlayPressed == false && firstButtonPress == false) {
					mPlayer.playFirstSong();
					firstButtonPress = true;
					currPosition = 0;
					isPlayPressed = true;
					currentSongText.setText("Current Song: " + getMusicFileName(0));
				} else if (isPlayPressed == false) {
					mPlayer.resumePlayer();
					isPlayPressed = true;
				} else if (isPlayPressed == true) {
					mPlayer.pausePlayer();
					isPlayPressed = false;
				}

			}
		});

	}

	private void onNextButtonListener() {
		nextButton.setOnClickListener(new OnClickListener() {
			int musicTotal = getTotalMusicCount();
			
			@Override
			public void onClick(View v) {
				if(firstButtonPress == false){
					// this means that there is no song even playing
				}
				else if(currPosition < musicTotal - 1){
					currPosition++;
					mPlayer.playMusic(currPosition);
					currentSongText.setText("Current Song: " + getMusicFileName(currPosition));
					if(isPlayPressed == false) {
						isPlayPressed = true;
					}
				}
				else {
					mPlayer.resetPlayer();
					firstButtonPress = false;
					isPlayPressed = false;
					currentSongText.setText("");
				}
				
			}

			
		});
	}

	private void onPrevButtonListener() {
		prevButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(firstButtonPress == false){
					
				}
				else if(currPosition!= 0){
					currPosition--;
					mPlayer.playMusic(currPosition);
					currentSongText.setText("Current Song: " + getMusicFileName(currPosition));
					if(isPlayPressed == false) {
						isPlayPressed = true;
					}
						
				}
				else{
					mPlayer.playMusic(0);
				}
				
			}
		});
	}

	@Override
	public void onListItemPicked(int position) { // this is used to communicate
													// between MusicFragment and
													// MainActivity
		// Log.d("CAC", "Listener success, position: " + position);
		mPlayer.playMusic(position);
		currPosition = position;
		isPlayPressed = true;
		currentSongText.setText("Current Song: " + getMusicFileName(currPosition));
		if (firstButtonPress == false) { // this will make sure to get rid of
											// first press play action if first
											// song is selected thru list
			firstButtonPress = true;
		}
	}
	
	private int getTotalMusicCount() {
		File root = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath());
		File musicDir = new File(root, "media/music");
		String[] musicFiles = musicDir.list();
		
		return musicFiles.length;
	}
	
	private String getMusicFileName(int position){
		File root = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath());
		File musicDir = new File(root, "media/music");
		String[] musicFiles = musicDir.list();
		return musicFiles[position];
	}

	// private void getMusicList(){
	// File root = new
	// File(Environment.getExternalStorageDirectory().getAbsolutePath());
	// File musicDir = new File(root,"media/music");
	// for(File f : musicDir.listFiles()){
	// String name = f.getName();
	// Log.d("CAC", "Name of Song: " + name);
	// }
	// }

}
