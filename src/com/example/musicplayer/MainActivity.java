package com.example.musicplayer;

import java.io.File;

import android.app.ActionBar;
import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity implements MusicFragment.onListItemClickListener{
	
	ImageButton playButton;
	MusicPlayer mPlayer;
	MusicFragment mFragment;
	boolean isPlayPressed;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        
        ActionBar bar = getActionBar();
        playButton = (ImageButton)findViewById(R.id.play_button);
        playButton.setBackgroundResource(R.drawable.play_button);
        mPlayer = new MusicPlayer(this);
        isPlayPressed = false;
        onPlayButtonListener();
        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void onFragmentListener(){
		
	}
	
	public void onPlayButtonListener(){
		playButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "Button works", Toast.LENGTH_LONG).show();
				if(isPlayPressed == false){
					mPlayer.playMusic();
					playButton.setBackgroundResource(R.drawable.stop_button);
					isPlayPressed = true;
				}
				else if(isPlayPressed == true){
					mPlayer.pauseMusic();
					playButton.setBackgroundResource(R.drawable.play_button);
					isPlayPressed = false;
				}
				
				
			}
		});
		
	}

	@Override
	public void onListItemPicked(int position) { // this is used to communicate between MusicFragment and MainActivity
		Log.d("CAC", "Listener success, position: " + position);
		mPlayer.playMusic(position);
	}
	
//	private void getMusicList(){
//		File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
//		File musicDir = new File(root,"media/music");
//		for(File f : musicDir.listFiles()){
//			String name = f.getName();
//			Log.d("CAC", "Name of Song: " + name);
//		}
//	}
    
    
}
