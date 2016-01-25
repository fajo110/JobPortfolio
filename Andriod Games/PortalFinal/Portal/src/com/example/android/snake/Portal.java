package com.example.android.snake;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

/**
 * By: James Ji, Farjad Abbas, Hassaan Khawar
 * Portal: Use the portal gun to find yourself through the maze and collect golden dots
 * You can move left or right but cannot stop unless you hit a wall
 * You can rotate the direction to shoot the portal
 * You can shoot the 2 portals, red and blue, at walls
 * Touching the released red portal will teleport you to the released blue portal
 * If you hit a spike or a ghost, you die
 * Instructions but not rules are placed in the program for players' creative measures
 */

public class Portal extends Activity {

	/**
	 * Constants for desired action
	 */
	public static int MOVE_LEFT = 0;
	public static int ROTATE = 1;
	public static int SHOOT = 2;
	public static int MOVE_RIGHT = 3;

	private static String ICICLE_KEY = "portal-view";

	private PortalView mPortalView;

	/**
	 * Called when Activity is first created. Turns off the title bar, sets up the content views,
	 * and fires up the PortalView.
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.portal_layout);

		mPortalView = (PortalView) findViewById(R.id.snake);
		mPortalView.setDependentViews((TextView) findViewById(R.id.text),
				findViewById(R.id.arrowContainer), findViewById(R.id.background));

		if (savedInstanceState == null) {
			// We were just launched -- set up a new game
			mPortalView.setMode(PortalView.READY);
		} else {
			// We are being restored
			Bundle map = savedInstanceState.getBundle(ICICLE_KEY);
			if (map != null) {
				mPortalView.restoreState(map);
			} else {
				mPortalView.setMode(PortalView.PAUSE);
			}
		}
		mPortalView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (mPortalView.getGameState() == PortalView.RUNNING) {
					// Normalize x,y between 0 and 1
					float x = event.getX() / v.getWidth();
					float y = event.getY() / v.getHeight();

					// Action will be [0,1,2,3] depending on quadrant
					int action = 0;
					action = (x > y) ? 1 : 0;
					action |= (x > 1 - y) ? 2 : 0;

					// Action is same as the quadrant which was clicked
					mPortalView.moveScorpion(action);

				} else {
					// If the game is not running then on touching any part of the screen
					// we start the game by sending MOVE_RIGHT signal to PortalView
					mPortalView.moveScorpion(MOVE_RIGHT);
				}
				return false;
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		// Pause the game along with the activity
		mPortalView.setMode(PortalView.PAUSE);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Store the game state
		outState.putBundle(ICICLE_KEY, mPortalView.saveState());
	}
}
