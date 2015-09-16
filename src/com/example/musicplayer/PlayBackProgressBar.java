package com.example.musicplayer;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class PlayBackProgressBar implements Runnable{
	Context context;
	MediaPlayer mp;
	SeekBar progress; 

	public PlayBackProgressBar(MusicPlayer mPlayer, SeekBar progress) {
		this.progress = progress;
		mp = mPlayer.mPlayer;
		progress.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
		            mp.seekTo(progress);
		        }
				
			}
		});
	}

	@Override
	public void run() {
		int currentPosition = 0;
		int total = mp.getDuration();
		progress.setMax(total);

		while (mp != null && currentPosition < total) {
			try {
				Thread.sleep(1000);
				currentPosition = mp.getCurrentPosition();
			} catch (InterruptedException e) {
				return;
			} catch (Exception e) {
				return;
			}
			progress.setProgress(currentPosition);
		}
		

	}
	
	
}
