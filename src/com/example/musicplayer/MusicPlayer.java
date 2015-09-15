package com.example.musicplayer;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;

public class MusicPlayer {
	private MediaPlayer mPlayer;
	Context context;
	boolean isPaused;
	boolean songSelected;
	File root;
	File musicDir;

	public MusicPlayer(Context context) {
		mPlayer = new MediaPlayer();
		this.context = context;
		root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
		musicDir = new File(root,"media/music");
		isPaused = false;
		songSelected = false;
	}

	public void playMusic() {
		// String fileName = "psychosocial.mp3";
		// String completePath = Environment.getExternalStorageDirectory() + "/"
		// + fileName;
		//
		// File file = new File(completePath);

		if (isPaused == false && songSelected == false) { //play the first song off of the sd card if none are specifically selected and play button is pressed
			File[] musicFiles = musicDir.listFiles();
			Uri musicUri = Uri.fromFile(musicFiles[0]);
			mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			try {
				mPlayer.setDataSource(context, musicUri);
				mPlayer.prepare();
				mPlayer.start();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(isPaused == true && songSelected == true){
			mPlayer.start();
		}

	}
	
	public void playMusic(int songPosition){
		File[] musicFiles = musicDir.listFiles();
		Uri musicUri = Uri.fromFile(musicFiles[songPosition]);
		mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try {
			mPlayer.setDataSource(context, musicUri);
			mPlayer.prepare();
			mPlayer.start();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pauseMusic() {
			mPlayer.pause();
			isPaused = true;

	}
	public void resetPlayer(){
		mPlayer.reset();
		isPaused = false;
	}

}
