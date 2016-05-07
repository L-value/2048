package com.example.helloandroid;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

public class GameView extends GridLayout
{

	private card[][] cardMap = new card[4][4];
	private List<Point> emptypoints = new ArrayList<>();

	public GameView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initGameView();
	}

	public GameView(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
		initGameView();
	}

	public GameView(Context context, AttributeSet atters)
	{
		super(context, atters);
		// TODO Auto-generated constructor stub
		initGameView();

	}

	private void initGameView()
	{
		setColumnCount(4);
		this.setOnTouchListener(new View.OnTouchListener()
		{
			private float start_x, start_y, end_x, end_y;

			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				switch (event.getAction())
				{
				case MotionEvent.ACTION_DOWN:
					start_x = event.getX();
					start_y = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					end_x = event.getX() - start_x;
					end_y = event.getY() - start_y;
					if (Math.abs(end_x) > Math.abs(end_y))
					{
						if (end_x > 5)
						{
							swipeRight();
							// invalidate();
						}

						else if (end_x < -5)
						{
							swipeLeft();
							// invalidate();
						}
					} else
					{
						if (end_y > 5)
						{
							swipeDown();
							// invalidate();
						} else if (end_y < -5)
						{
							swipeUp();
							// invalidate();
						}
					}
					break;
				}
				// TODO Auto-generated method stub
				return true;
			}
		});
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)// 该方法只会在程序创建的时候
	{
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
	    int card_Width = (Math.min(w, h) - 10) / 4;
		addcards(card_Width, card_Width);
		setBackgroundColor(Color.RED);
		startGame();
	}

	private void startGame()
	{
		MainActivity.getMainActivity().clearScore();
		// TODO Auto-generated method stub
		for (int x = 0; x < 4; x++)
		{
			for (int y = 0; y < 4; y++)
			{
				cardMap[x][y].setNumber(0);
			}
		}
		addrandom();
		addrandom();

	}

	private void addcards(int width, int height)
	{
		card card;
		// TODO Auto-generated method stub
		for (int y = 0; y < 4; y++)
		{
			for (int x = 0; x < 4; x++)
			{
				card = new card(getContext());
				card.setNumber(0);
				addView(card, width, height);
				cardMap[x][y] = card;
			}
		}
	}

	private void addrandom()
	{
		emptypoints.clear();
		// TODO Auto-generated method stub
		for (int x = 0; x < 4; x++)
		{
			for (int y = 0; y < 4; y++)
			{
				if (cardMap[x][y].getNumber() <= 0)
				{
					emptypoints.add(new Point(x, y));
				}
			}
		}
		Point point = emptypoints.remove((int) (Math.random() * emptypoints
				.size()));
		cardMap[point.x][point.y].setNumber(Math.random() > 0.2 ? 2 : 4);
	}

	private void swipeLeft()
	{
		// TODO Auto-generated method stub
		boolean merge = false;
		for (int y = 0; y < 4; y++)
		{
			for (int x = 0; x < 4; x++)
			{
				for (int x1 = x + 1; x1 < 4; x1++)
				{
					if (cardMap[x1][y].getNumber() > 0)
					{
						if (cardMap[x][y].getNumber() <= 0)
						{
							cardMap[x][y].setNumber(cardMap[x1][y].getNumber());
							cardMap[x1][y].setNumber(0);
							x--;
							merge = true;
						} else if (cardMap[x][y].equals(cardMap[x1][y]))
						{
							cardMap[x][y]
									.setNumber(cardMap[x][y].getNumber() * 2);
							cardMap[x1][y].setNumber(0);
							MainActivity.getMainActivity().addScore(
									cardMap[x][y].getNumber());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if (merge == true)
		{
			addrandom();
			check_is_over();
		}
	}

	private void swipeRight()
	{
		// TODO Auto-generated method stub
		boolean merge = false;
		for (int y = 0; y < 4; y++)
		{
			for (int x = 3; x >= 0; x--)
			{
				for (int x1 = x - 1; x1 >= 0; x1--)
				{
					if (cardMap[x1][y].getNumber() > 0)
					{
						if (cardMap[x][y].getNumber() <= 0)
						{
							cardMap[x][y].setNumber(cardMap[x1][y].getNumber());
							cardMap[x1][y].setNumber(0);
							x++;
							merge = true;

						} else if (cardMap[x][y].equals(cardMap[x1][y]))
						{
							cardMap[x][y]
									.setNumber(cardMap[x][y].getNumber() * 2);
							cardMap[x1][y].setNumber(0);
							MainActivity.getMainActivity().addScore(
									cardMap[x][y].getNumber());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if (merge == true)
		{
			addrandom();
			check_is_over();
		}
	}

	private void swipeUp()
	{
		// TODO Auto-generated method stub
		boolean merge = false;
		for (int x = 0; x < 4; x++)
		{
			for (int y = 0; y < 4; y++)
			{
				for (int y1 = y + 1; y1 < 4; y1++)
				{
					if (cardMap[x][y1].getNumber() > 0)
					{
						if (cardMap[x][y].getNumber() <= 0)
						{
							cardMap[x][y].setNumber(cardMap[x][y1].getNumber());
							cardMap[x][y1].setNumber(0);
							y--;
							merge = true;
						} else if (cardMap[x][y].equals(cardMap[x][y1]))
						{
							cardMap[x][y]
									.setNumber(cardMap[x][y].getNumber() * 2);
							cardMap[x][y1].setNumber(0);
							MainActivity.getMainActivity().addScore(
									cardMap[x][y].getNumber());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if (merge == true)
		{
			addrandom();
			check_is_over();
		}
	}

	private void swipeDown()
	{
		// TODO Auto-generated method stub
		boolean merge = false;
		for (int x = 0; x < 4; x++)
		{
			for (int y = 3; y >= 0; y--)
			{
				for (int y1 = y - 1; y1 >= 0; y1--)
				{
					if (cardMap[x][y1].getNumber() > 0)
					{
						if (cardMap[x][y].getNumber() <= 0)
						{
							cardMap[x][y].setNumber(cardMap[x][y1].getNumber());
							cardMap[x][y1].setNumber(0);
							y++;
							merge = true;
						} else if (cardMap[x][y].equals(cardMap[x][y1]))
						{
							cardMap[x][y]
									.setNumber(cardMap[x][y].getNumber() * 2);
							cardMap[x][y1].setNumber(0);
							MainActivity.getMainActivity().addScore(
									cardMap[x][y].getNumber());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if (merge == true)
		{
			addrandom();
			check_is_over();
		}
	}

	private void check_is_over()
	{
		// TODO Auto-generated method stub
		boolean complete = true;
		All: for (int y = 0; y < 4; y++)
		{
			for (int x = 0; x < 4; x++)
			{
				if (cardMap[x][y].getNumber() == 0
						|| (x > 1 && cardMap[x][y].equals(cardMap[x - 1][y]))
						|| (x < 3 && cardMap[x][y].equals(cardMap[x + 1][y]))
						|| (y > 0 && cardMap[x][y].equals(cardMap[x][y - 1]))
						|| (y < 3) && cardMap[x][y].equals(cardMap[x][y + 1]))
				{
					complete = false;
					break All;
				}
			}
		}
		if (complete)
		{
			new AlertDialog.Builder(getContext())
					.setTitle("你好")
					.setMessage("Game Over")
					.setPositiveButton("重来",
							new DialogInterface.OnClickListener()
							{

								@Override
								public void onClick(DialogInterface dialog,
										int which)
								{
									// TODO Auto-generated method stub
									startGame();
								}
							}).show();
		}
	}
}
