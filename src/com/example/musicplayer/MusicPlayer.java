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
	boolean songSelected;
	File root;
	File musicDir;

	public MusicPlayer(Context context) {
		mPlayer = new MediaPlayer();
		this.context = context;
		root = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath());
		musicDir = new File(root, "media/music");
		songSelected = false;
	}

	public void playMusic(int songPosition) {
		

		if (songSelected == false) {
			startPlayer(songPosition);
			songSelected = true;
		}
		else if(songSelected == true){
			resetPlayer();
			startPlayer(songPosition);
		}

	}
	public void playFirstSong(){
		startPlayer(0);
		songSelected = true;
	}

	public void pausePlayer() {
		mPlayer.pause();
	}
	
	public void resumePlayer() {
		mPlayer.start();
	}

	public void resetPlayer() {
		mPlayer.reset();
	}
	private void startPlayer(int songPosition) {
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

}

		//String fileName = "psychosocial.mp3";
		// String completePath = Environment.getExternalStorageDirectory() + "/"
		// + fileName;
		//
		// File file = new File(completePath);


//public void playMusic(int songPosition) {
//if (songSelected == false) { 
//	startPlayer(songPosition);
//	songSelected = true;
//}
//else {
//	resetPlayer();
//	startPlayer(songPosition);
//	
//}
//}