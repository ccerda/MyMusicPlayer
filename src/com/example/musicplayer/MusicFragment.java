package com.example.musicplayer;

import java.io.File;
import java.util.Arrays;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MusicFragment extends ListFragment {

	public String[] musicList = getMusicList();
	public int musicSize = musicList.length;
	
	
	public String songName; // will use this when clicked to save name of song
							// requested to be played
	Activity activity;

	// Context context = getActivity();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		for(int i = 0; i < musicSize; i++){
			musicList[i] = musicList[i].split("\\.")[0];
		}
		Arrays.sort(musicList, String.CASE_INSENSITIVE_ORDER);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, musicList);

		setListAdapter(adapter);
	}

	@Override
	public void onAttach(Activity activity) { // this is used to get the MainActivity, must use or app crash when attempting to communicate
		super.onAttach(activity);
		this.activity = activity;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		// Log.d("CAC", l.getItemAtPosition(position).toString());
		try {
			((onListItemClickListener) activity).onListItemPicked(position);
		} catch (ClassCastException cce) {

		}
	}

	public interface onListItemClickListener {
		public void onListItemPicked(int position);
	}

	private String[] getMusicList() {
		File root = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath());
		File musicDir = new File(root, "media/music");
		String[] musicFiles = musicDir.list();
		return musicFiles;
	}
	
}
