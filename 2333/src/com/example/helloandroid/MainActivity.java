package com.example.helloandroid;

import android.support.v7.app.ActionBarActivity;
import android.R.integer;
import android.os.Bundle;
import android.provider.ContactsContract.FullNameStyle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity
{
	private TextView tvScore;
	private static MainActivity mainActivity = null;
	private int score = 0;

	public MainActivity()
	{
		mainActivity = this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		View GameView = (View) findViewById(R.id.GameView);
		tvScore = (TextView) findViewById(R.id.tvSorce);
		showScore();
	}
	

	public static MainActivity getMainActivity()
	{
		return mainActivity;
	}

	public void clearScore()
	{
		// TODO Auto-generated method stub
		score = 0;
		showScore();
	}

	public void showScore()
	{
		// TODO Auto-generated method stub
		tvScore.setText("" + score);
	}

	public void addScore(int s)
	{
		// TODO Auto-generated method stub
		score += s;
		showScore();
	}
}