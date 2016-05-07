package com.example.helloandroid;

import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class card extends FrameLayout
{
	private int number = 0;
	private TextView lable;

	public card(Context context)
	{	
		super(context);
		lable = new TextView(getContext());
		lable.setTextSize(32);
		lable.setGravity(Gravity.CENTER);
		LayoutParams lp = new LayoutParams(-1, -1);// Ìî³äÂúÕû¸ö¸¸¼¶ÈÝÆ÷
		lp.setMargins(10, 10, 0, 0);
		addView(lable, lp);
		lable.setBackgroundColor(Color.GREEN);
		// TODO Auto-generated constructor stub
	}

	public int getNumber()
	{
		return number;
	}

	public void setNumber(int number)
	{
		this.number = number;
		if (this.number == 0)
		{
			lable.setText("");
			return;
		}
		lable.setText(number + "");
	}

	public boolean equals(card o)
	{
		return getNumber() == o.getNumber();
	}
}
