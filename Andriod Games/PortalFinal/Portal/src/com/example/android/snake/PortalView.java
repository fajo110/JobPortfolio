package com.example.android.snake;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


/**
 * PortalView: implementation of a simple game of Portal
 */
public class PortalView extends TileView {

	/**
	 * Current mode of application: READY to run, RUNNING, or you have already lost. static final
	 * ints are used instead of an enum for performance reasons.
	 */
	private int mMode = READY;
	public static final int PAUSE = 0;
	public static final int READY = 1;
	public static final int RUNNING = 2;
	public static final int LOSE = 3;

	/**
	 * Current direction the character/object is headed.
	 */
	private int mDirection = EAST;
	private int mNextDirection = EAST;
	private static final int EAST = 1;
	private static final int WEST = 2;
	private static final int NORTH = 3;
	private static final int SOUTH = 4;

	/**
	 * Labels for the drawables that will be loaded into the TileView class
	 */
	private static final int RED_STAR = 1;
	private static final int YELLOW_STAR = 2;
	private static final int GREEN_STAR = 3;
	private static final int SCORPION1 = 4;
	private static final int SCORPION2 = 5;
	private static final int RED_PORTAL = 6;
	private static final int BLUE_PORTAL = 7;
	private static final int WALL = 8;
	private static final int SPIKE = 9;
	private static final int MOVINGSPIKE = 10;
	private static final int GHOST = 11;
	private static final int DOT = 12;

	/**
	 * mScore: Used to track the number of dots captured
	 * mMoveDelay: Number of milliseconds between character movements.
	 */
	private long mScore = 0;
	private long mMoveDelay = 60;
	/**
	 * mLastMove: Tracks the absolute time when character last moved, and is used to determine if a
	 * move should be made based on mMoveDelay.
	 */
	private long mLastMove;

	/**
	 * mStatusText: Text shows to the user in some run states
	 */
	private TextView mStatusText;

	/**
	 * mArrowsView: View which shows 2 arrows to signify 2 directions in which the character can move
	 */
	private View mArrowsView;

	/**
	 * mBackgroundView: Background View which shows 4 different colored triangles pressing which
	 * controls the character
	 */
	private View mBackgroundView;
	
	/**
	 * Other variables to control the game
	 */
	private Coordinate mScorpion;
	private ArrayList<Coordinate> mSpikeList = new ArrayList<Coordinate>();
	private ArrayList<Coordinate> mHorizontalSpikeList = new ArrayList<Coordinate>();
	private ArrayList<Coordinate> mVerticalSpikeList = new ArrayList<Coordinate>();
	private int[] mHorizontalSpikesDirections=new int[10];
	private int[] mVerticalSpikesDirections=new int[10];
	private ArrayList<Coordinate> mGhostList = new ArrayList<Coordinate>();
	private ArrayList<Coordinate> mDotList = new ArrayList<Coordinate>();
	private ArrayList<Coordinate> mWall = new ArrayList<Coordinate>();
	private Coordinate redPortal;
	private Coordinate bluePortal;
	private int redDirection = 0;
	private int blueDirection = 0;
	private int mPortal = -1;
	private int numberOfHorizontalSpikes=0;
	private int numberOfVerticalSpikes=0;
	private int numberOfGhost=0;
	private boolean redRelease = false;
	private boolean blueRelease = false;
	private static final Random RNG = new Random();

	/**
	 * Create a simple handler that we can use to cause animation to happen. We set ourselves as a
	 * target and we can use the sleep() function to cause an update/invalidate to occur at a later
	 * date.
	 */

	private RefreshHandler mRedrawHandler = new RefreshHandler();

	@SuppressLint("HandlerLeak")
	class RefreshHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			PortalView.this.update();
			PortalView.this.invalidate();
		}

		public void sleep(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	};

	/**
	 * Constructs a PortalView based on inflation from XML
	 * 
	 * @param context
	 * @param attrs
	 */
	public PortalView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initSnakeView(context);
	}

	public PortalView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initSnakeView(context);
	}

	private void initSnakeView(Context context) {

		setFocusable(true);

		Resources r = this.getContext().getResources();

		resetTiles(13);
		loadTile(RED_STAR, r.getDrawable(R.drawable.redstar));
		loadTile(YELLOW_STAR, r.getDrawable(R.drawable.yellowstar));
		loadTile(GREEN_STAR, r.getDrawable(R.drawable.greenstar));
		loadTile(SCORPION1, r.getDrawable(R.drawable.scorpion1));
		loadTile(SCORPION2, r.getDrawable(R.drawable.scorpion2));
		loadTile(RED_PORTAL, r.getDrawable(R.drawable.redportal));
		loadTile(BLUE_PORTAL, r.getDrawable(R.drawable.blueportal));
		loadTile(WALL, r.getDrawable(R.drawable.wall));
		loadTile(SPIKE, r.getDrawable(R.drawable.spike));
		loadTile(MOVINGSPIKE, r.getDrawable(R.drawable.movingspike));
		loadTile(GHOST, r.getDrawable(R.drawable.ghost));
		loadTile(DOT, r.getDrawable(R.drawable.dot));

	}

	private void initNewGame() {
		mSpikeList.clear();
		mHorizontalSpikeList.clear();
		mVerticalSpikeList.clear();
		mGhostList.clear();
		mDotList.clear();
		mWall.clear();
		mPortal=-1;
		redDirection=0;
		blueDirection=0;
		numberOfHorizontalSpikes=0;
		numberOfVerticalSpikes=0;
		numberOfGhost=0;
		setWall();

		// Load with character on bottom

		mScorpion=new Coordinate(1, 39);
		mScorpion=new Coordinate(1, 40);
		redPortal=new Coordinate(2, 41);
		bluePortal=new Coordinate(2, 41);

		mNextDirection = EAST;

		//Add the random walls, spikes, ghosts, and dot
		
		for (int i=0; i<10;i++)
		{
			addRandom(WALL);
		}
		for (int i=0; i<10;i++)
		{
			addRandom(SPIKE);
		}
		for (int i=0; i<10;i++)
		{
			addRandom(MOVINGSPIKE);
		}
		addRandom(GHOST);
		addRandom(GHOST);
		addRandom(DOT);
		mMoveDelay = 60;
		mScore = 0;
	}

	/**
	 * Given a ArrayList of coordinates, we need to flatten them into an array of ints before we can
	 * stuff them into a map for flattening and storage.
	 * 
	 * @param cvec : a ArrayList of Coordinate objects
	 * @return : a simple array containing the x/y values of the coordinates as
	 *         [x1,y1,x2,y2,x3,y3...]
	 */
	private int[] coordArrayListToArray(ArrayList<Coordinate> cvec) {
		int[] rawArray = new int[cvec.size() * 2];

		int i = 0;
		for (Coordinate c : cvec) {
			rawArray[i++] = c.x;
			rawArray[i++] = c.y;
		}

		return rawArray;
	}

	/**
	 * Save game state so that the user does not lose anything if the game process is killed while
	 * we are in the background.
	 * 
	 * @return a Bundle with this view's state
	 */
	public Bundle saveState() {
		Bundle map = new Bundle();

		map.putIntArray("mHorizontalSpikeList", coordArrayListToArray(mHorizontalSpikeList));
		map.putIntArray("mVerticalSpikeList", coordArrayListToArray(mVerticalSpikeList));
		map.putIntArray("mGhostList", coordArrayListToArray(mGhostList));
		map.putIntArray("mDotList", coordArrayListToArray(mDotList));
		map.putIntArray("mSpikeList", coordArrayListToArray(mSpikeList));
		map.putInt("mDirection", Integer.valueOf(mDirection));
		map.putInt("mNextDirection", Integer.valueOf(mNextDirection));
		map.putLong("mMoveDelay", Long.valueOf(mMoveDelay));
		map.putLong("mScore", Long.valueOf(mScore));
		map.putIntArray("mWall", coordArrayListToArray(mWall));

		return map;
	}

	/**
	 * Given a flattened array of ordinate pairs, we reconstitute them into a ArrayList of
	 * Coordinate objects
	 * 
	 * @param rawArray : [x1,y1,x2,y2,...]
	 * @return a ArrayList of Coordinates
	 */
	private ArrayList<Coordinate> coordArrayToArrayList(int[] rawArray) {
		ArrayList<Coordinate> coordArrayList = new ArrayList<Coordinate>();

		int coordCount = rawArray.length;
		for (int index = 0; index < coordCount; index += 2) {
			Coordinate c = new Coordinate(rawArray[index], rawArray[index + 1]);
			coordArrayList.add(c);
		}
		return coordArrayList;
	}

	/**
	 * Restore game state if our process is being relaunched
	 * 
	 * @param icicle a Bundle containing the game state
	 */
	public void restoreState(Bundle icicle) {
		setMode(PAUSE);

		mHorizontalSpikeList = coordArrayToArrayList(icicle.getIntArray("mHorizontalSpikeList"));
		mVerticalSpikeList = coordArrayToArrayList(icicle.getIntArray("mVerticalSpikeList"));
		mGhostList = coordArrayToArrayList(icicle.getIntArray("mGhostList"));
		mDotList = coordArrayToArrayList(icicle.getIntArray("mDotList"));
		mSpikeList = coordArrayToArrayList(icicle.getIntArray("mSpikeList"));
		mDirection = icicle.getInt("mDirection");
		mNextDirection = icicle.getInt("mNextDirection");
		mMoveDelay = icicle.getLong("mMoveDelay");
		mScore = icicle.getLong("mScore");
		mWall = coordArrayToArrayList(icicle.getIntArray("mWall"));

	}

	/**
	 * Handles character movement/action
	 */
	public void moveScorpion(int direction) {

		if (direction == Portal.ROTATE) {
			if (redRelease==false) {
				redDirection=(redDirection+1)%8;
			}
			if (blueRelease==false) {
				blueDirection=(blueDirection+1)%8;
			}
			return;
		}

		if (direction == Portal.SHOOT) {

			if (mPortal==-1||mPortal==0)
			{
				boolean onWall=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (bluePortal.x==mWall.get(wallindex).x&&bluePortal.y==mWall.get(wallindex).y)
					{
						onWall=true;
					}
				}
				if (onWall==false){
					mPortal=(mPortal+1)%4;
				}
			}
			else if (mPortal==2)
			{
				boolean onWall=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (redPortal.x==mWall.get(wallindex).x&&redPortal.y==mWall.get(wallindex).y)
					{
						onWall=true;
					}
				}
				if (onWall==false){
					mPortal=(mPortal+1)%4;
				}
			}
			else
			{
				mPortal=(mPortal+1)%4;
			}
		}

		if (direction == Portal.MOVE_LEFT) {
			mNextDirection = WEST;
			return;
		}

		if (direction == Portal.MOVE_RIGHT) {
			if (mMode == READY | mMode == LOSE) {
				/*
				 * At the beginning of the game, or the end of a previous one,
				 * we should start a new game if UP key is clicked.
				 */
				initNewGame();
				setMode(RUNNING);
				update();
				return;
			}

			if (mMode == PAUSE) {
				/*
				 * If the game is merely paused, we should just continue where we left off.
				 */
				setMode(RUNNING);
				update();
				return;
			}
			mNextDirection = EAST;
			return;
		}
	}

	/**
	 * Sets the Dependent views that will be used to give information (such as "Game Over" to the
	 * user and also to handle touch events for making movements
	 * 
	 * @param newView
	 */
	public void setDependentViews(TextView msgView, View arrowView, View backgroundView) {
		mStatusText = msgView;
		mArrowsView = arrowView;
		mBackgroundView = backgroundView;
	}

	/**
	 * Updates the current mode of the application (RUNNING or PAUSED or the like) as well as sets
	 * the visibility of textview for notification
	 * 
	 * @param newMode
	 */
	public void setMode(int newMode) {
		int oldMode = mMode;
		mMode = newMode;

		if (newMode == RUNNING && oldMode != RUNNING) {
			// hide the game instructions
			mStatusText.setVisibility(View.INVISIBLE);
			update();
			// make the background and arrows visible as soon the snake starts moving
			mArrowsView.setVisibility(View.VISIBLE);
			mBackgroundView.setVisibility(View.VISIBLE);
			return;
		}

		Resources res = getContext().getResources();
		CharSequence str = "";
		if (newMode == PAUSE) {
			mArrowsView.setVisibility(View.GONE);
			mBackgroundView.setVisibility(View.GONE);
			str = res.getText(R.string.mode_pause);
		}
		if (newMode == READY) {
			mArrowsView.setVisibility(View.GONE);
			mBackgroundView.setVisibility(View.GONE);

			str = res.getText(R.string.mode_ready);
		}
		if (newMode == LOSE) {
			mArrowsView.setVisibility(View.GONE);
			mBackgroundView.setVisibility(View.GONE);
			str = res.getString(R.string.mode_lose, mScore);
		}

		mStatusText.setText(str);
		mStatusText.setVisibility(View.VISIBLE);
	}

	/**
	 * @return the Game state as Running, Ready, Paused, Lose
	 */
	public int getGameState() {
		return mMode;
	}

	/**
	 * Selects a random location within the maze that is not currently covered by the character
	 */
	private void addRandom(int x) {
		Coordinate newCoord = null;
		boolean found = false;
		while (!found) {
			// Choose a new location
			int newX = 1 + RNG.nextInt(mXTileCount - 2);
			int newY = 1 + RNG.nextInt(mYTileCount - 2);
			newCoord = new Coordinate(newX, newY);

			// Make sure it's not already under
			boolean collision = false;
			if ((mScorpion.x==newCoord.x&&mScorpion.y==newCoord.y)||(mScorpion.x==newCoord.x&&mScorpion.y+1==newCoord.y)) {
				collision = true;
			}
			// if we're here and there's been no collision, then we have
			// a good location for an apple. Otherwise, we'll circle back
			// and try again
			found = !collision;
		}
		if (x==8)
		{
			mWall.add(newCoord);
		}
		else if (x==9)
		{
			mSpikeList.add(newCoord);
		}
		else if (x==10)
		{
			double h = RNG.nextInt(4);
			if (h<2){
				mHorizontalSpikeList.add(newCoord);
				if (h==0){
					mHorizontalSpikesDirections[numberOfHorizontalSpikes]=EAST;
					numberOfHorizontalSpikes++;
				}
				else{
					mHorizontalSpikesDirections[numberOfHorizontalSpikes]=WEST;
					numberOfHorizontalSpikes++;
				}
			}
			else
			{
				mVerticalSpikeList.add(newCoord);
				if (h==2){
					mVerticalSpikesDirections[numberOfVerticalSpikes]=NORTH;
					numberOfVerticalSpikes++;
				}
				else{
					mVerticalSpikesDirections[numberOfVerticalSpikes]=SOUTH;
					numberOfVerticalSpikes++;
				}
			}
		}
		else if (x==11)
		{
			mGhostList.add(newCoord);
			numberOfGhost++;
		}
		else if (x==12)
		{
			mDotList.add(newCoord);
		}
	}



	/**
	 * Handles the basic update loop, checking to see if we are in the running state, determining if
	 * a move should be made, updating the character's and objects' locations.
	 */
	public void update() {
		if (mMode == RUNNING) {
			long now = System.currentTimeMillis();

			if (now - mLastMove > mMoveDelay) {
				clearTiles();
				updateWalls();
				updateScorpion();
				updateStatus();
				updateSpikes();
				updateGhost();
				updateDots();
				updateStatus();
				gravity();
				mLastMove = now;
			}
			mRedrawHandler.sleep(mMoveDelay);
		}

	}

	/**
	 * Pulls the character down for gravitational effect
	 */
	private void gravity() {
		boolean onFloor=false;
		int walllength = mWall.size();
		for (int wallindex = 0; wallindex < walllength; wallindex++) {
			if (mScorpion.x==mWall.get(wallindex).x&&mScorpion.y+2==mWall.get(wallindex).y)
			{
				onFloor=true;
			}
		}
		if (onFloor==false){
			mScorpion=new Coordinate(mScorpion.x, mScorpion.y+1);
		}
	}

	/**
	 * Teleports character from red portal to blue portal
	 */
	private void teleport(){
		if (redRelease==true&&blueRelease==true)
		{
			if ((mScorpion.x==redPortal.x&&mScorpion.y==redPortal.y)||(mScorpion.x==redPortal.x&&mScorpion.y+1==redPortal.y))
			{
				mScorpion.x=bluePortal.x;
				mScorpion.y=bluePortal.y-1;
				setTile(SCORPION1, mScorpion.x, mScorpion.y);
				setTile(SCORPION2, mScorpion.x, mScorpion.y+1);
			}
			
			for (int i=0; i<numberOfGhost;i++)
			{
				if (mGhostList.get(i).x==redPortal.x&&mGhostList.get(i).y==redPortal.y)
				{
					mGhostList.get(i).x=bluePortal.x;
					mGhostList.get(i).y=bluePortal.y;
					setTile(GHOST, mGhostList.get(i).x, mGhostList.get(i).y);
				}
			}
		}
	}

	/**
	 * Set initial walls
	 */
	private void setWall()
	{
		Coordinate w = new Coordinate(1, 1);
		for (int x = 0; x < mXTileCount; x++) {
			if (x==0|x==mXTileCount-1)
			{
				for (int y = 0; y < mYTileCount; y++)
				{
					w=new Coordinate(x, y);
					mWall.add(0, w);
				}
			}
			else
			{
				w=new Coordinate(x, 0);
				mWall.add(0, w);
				w=new Coordinate(x, mYTileCount-1);
				mWall.add(0, w);
			}	
		}
		for (int x=6; x < 16 ; x++){
			w=new Coordinate(x, 10);
			mWall.add(0, w);
		}
		for (int x=12; x < 22 ; x++){
			w=new Coordinate(x, 20);
			mWall.add(0, w);
		}
		for (int x=9; x < 19 ; x++){
			w=new Coordinate(x, 30);
			mWall.add(0, w);
		}
	}

	private void updateWalls() {
		for (Coordinate c : mWall) {
			setTile(WALL, c.x, c.y);
		}
	}

	private void updateGhost() {
		Coordinate newCoord = null;
		for (int i=0; i<numberOfGhost;i++)
		{
			newCoord=new Coordinate(mGhostList.get(i).x, mGhostList.get(i).y);
			if (mScorpion.x<mGhostList.get(i).x)
			{
				newCoord=new Coordinate(newCoord.x - 1, newCoord.y);
			}
			else if (mScorpion.x>mGhostList.get(i).x)
			{
				newCoord=new Coordinate(newCoord.x + 1, newCoord.y);
			}
			else if (mScorpion.y<mGhostList.get(i).y)
			{
				newCoord=new Coordinate(newCoord.x, newCoord.y - 1);
			}
			else if (mScorpion.y>mGhostList.get(i).y)
			{
				newCoord=new Coordinate(newCoord.x, newCoord.y + 1);
			}

			mGhostList.set(i,newCoord);
			setTile(GHOST, mGhostList.get(i).x, mGhostList.get(i).y);
		}
	}
	
	private void updateSpikes() {
		for (Coordinate c : mSpikeList) {
			setTile(SPIKE, c.x, c.y);
		}

		for (int i=0; i<numberOfHorizontalSpikes;i++)
		{
			Coordinate newCoord = null;
			switch(mHorizontalSpikesDirections[i]){
			case EAST: {
				boolean onWall=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (mHorizontalSpikeList.get(i).x+1==mWall.get(wallindex).x&&mHorizontalSpikeList.get(i).y==mWall.get(wallindex).y)
					{
						onWall=true;
					}
				}
				if (onWall==false){
					newCoord=new Coordinate(mHorizontalSpikeList.get(i).x + 1, mHorizontalSpikeList.get(i).y);
					mHorizontalSpikeList.set(i,newCoord);
				}
				else{
					newCoord=new Coordinate(mHorizontalSpikeList.get(i).x - 1, mHorizontalSpikeList.get(i).y);
					mHorizontalSpikesDirections[i]=WEST;
				}
				setTile(MOVINGSPIKE, mHorizontalSpikeList.get(i).x, mHorizontalSpikeList.get(i).y);
				break;
			}
			case WEST: {
				boolean onWall=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (mHorizontalSpikeList.get(i).x-1==mWall.get(wallindex).x&&mHorizontalSpikeList.get(i).y==mWall.get(wallindex).y)
					{
						onWall=true;
					}
				}
				if (onWall==false){
					newCoord=new Coordinate(mHorizontalSpikeList.get(i).x - 1, mHorizontalSpikeList.get(i).y);
					mHorizontalSpikeList.set(i,newCoord);
				}
				else{
					newCoord=new Coordinate(mHorizontalSpikeList.get(i).x + 1, mHorizontalSpikeList.get(i).y);
					mHorizontalSpikesDirections[i]=EAST;
				}
				setTile(MOVINGSPIKE, mHorizontalSpikeList.get(i).x, mHorizontalSpikeList.get(i).y);
				break;
			}
			}
		}

		for (int i=0; i<numberOfVerticalSpikes;i++)
		{
			Coordinate newCoord = null;
			switch(mVerticalSpikesDirections[i]){
			case NORTH:
			{
				boolean onWall=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (mVerticalSpikeList.get(i).x==mWall.get(wallindex).x&&mVerticalSpikeList.get(i).y-1==mWall.get(wallindex).y)
					{
						onWall=true;
					}
				}
				if (onWall==false){
					newCoord=new Coordinate(mVerticalSpikeList.get(i).x, mVerticalSpikeList.get(i).y-1);
					mVerticalSpikeList.set(i,newCoord);
				}
				else{
					newCoord=new Coordinate(mVerticalSpikeList.get(i).x, mVerticalSpikeList.get(i).y+1);
					mVerticalSpikesDirections[i]=SOUTH;
				}
				setTile(MOVINGSPIKE, mVerticalSpikeList.get(i).x, mVerticalSpikeList.get(i).y);
				break;
			}
			case SOUTH:
			{
				boolean onWall=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (mVerticalSpikeList.get(i).x==mWall.get(wallindex).x&&mVerticalSpikeList.get(i).y+1==mWall.get(wallindex).y)
					{
						onWall=true;
					}
				}
				if (onWall==false){
					newCoord=new Coordinate(mVerticalSpikeList.get(i).x, mVerticalSpikeList.get(i).y+1);
					mVerticalSpikeList.set(i,newCoord);
				}
				else{
					newCoord=new Coordinate(mVerticalSpikeList.get(i).x, mVerticalSpikeList.get(i).y-1);
					mVerticalSpikesDirections[i]=NORTH;
				}
				setTile(MOVINGSPIKE, mVerticalSpikeList.get(i).x, mVerticalSpikeList.get(i).y);
				break;
			}
			}
		}
	}

	private void updateDots() {
		for (Coordinate c : mDotList) {
			setTile(DOT, c.x, c.y);
		}
	}
	
	/**
	 * Figure out which way the character and portals are going and see if they will run into anything
	 */
	private void updateScorpion() {

		mDirection = mNextDirection;

		switch (mDirection) {
		case EAST: {
			boolean onWall=false;
			int walllength = mWall.size();
			for (int wallindex = 0; wallindex < walllength; wallindex++) {
				if (mScorpion.x+1==mWall.get(wallindex).x&&mScorpion.y==mWall.get(wallindex).y||mScorpion.x+1==mWall.get(wallindex).x&&mScorpion.y+1==mWall.get(wallindex).y)
				{
					onWall=true;
				}
			}
			if (onWall==false){
				mScorpion = new Coordinate(mScorpion.x + 1, mScorpion.y);
			}
			break;
		}
		case WEST: {
			boolean onWall=false;
			int walllength = mWall.size();
			for (int wallindex = 0; wallindex < walllength; wallindex++) {
				if (mScorpion.x-1==mWall.get(wallindex).x&&mScorpion.y==mWall.get(wallindex).y||mScorpion.x-1==mWall.get(wallindex).x&&mScorpion.y+1==mWall.get(wallindex).y)
				{
					onWall=true;
				}
			}
			if (onWall==false){
				mScorpion = new Coordinate(mScorpion.x - 1, mScorpion.y);
			}
			break;
		}
		}

		switch (mPortal) {
		case -1: {
			redRelease=false;
			blueRelease=false;
			break;
		}
		case 0: {
			redRelease=true;
			blueRelease=false;
			break;
		}
		case 1: {
			redRelease=true;
			blueRelease=true;
			break;
		}
		case 2: {
			redRelease=false;
			blueRelease=true;
			break;
		}
		case 3:	{
			redRelease=true;
			blueRelease=true;
			break;
		}
		}


		if (redRelease==false)
		{
			switch (redDirection){
			case 0:	{
				redPortal = new Coordinate(mScorpion.x - 1, mScorpion.y);
				break;
			}
			case 1:	{
				redPortal = new Coordinate(mScorpion.x - 1, mScorpion.y-1);
				break;
			}
			case 2:	{
				redPortal = new Coordinate(mScorpion.x, mScorpion.y-1);
				break;
			}
			case 3:	{
				redPortal = new Coordinate(mScorpion.x + 1, mScorpion.y-1);
				break;
			}
			case 4:	{
				redPortal = new Coordinate(mScorpion.x + 1, mScorpion.y);
				break;
			}
			case 5:	{
				redPortal = new Coordinate(mScorpion.x + 1, mScorpion.y+1);
				break;
			}
			case 6:	{
				redPortal = new Coordinate(mScorpion.x, mScorpion.y+1);
				break;
			}
			case 7:	{
				redPortal = new Coordinate(mScorpion.x - 1, mScorpion.y+1);
				break;
			}
			}
		}
		else
		{
			switch (redDirection){
			case 0:	{
				boolean stick=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (redPortal.x-1==mWall.get(wallindex).x&&redPortal.y==mWall.get(wallindex).y)
					{
						stick=true;
					}
				}
				if (stick==false){
					redPortal = new Coordinate(redPortal.x-1, redPortal.y);
				}
				break;
			}
			case 1:	{
				boolean stick=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (redPortal.x-1==mWall.get(wallindex).x&&redPortal.y-1==mWall.get(wallindex).y)
					{
						stick=true;
					}
				}
				if (stick==false){
					redPortal = new Coordinate(redPortal.x-1, redPortal.y-1);
				}
				break;
			}
			case 2:	{
				boolean stick=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (redPortal.x==mWall.get(wallindex).x&&redPortal.y-1==mWall.get(wallindex).y)
					{
						stick=true;
					}
				}
				if (stick==false){
					redPortal = new Coordinate(redPortal.x, redPortal.y-1);
				}
				break;
			}
			case 3:	{
				boolean stick=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (redPortal.x+1==mWall.get(wallindex).x&&redPortal.y-1==mWall.get(wallindex).y)
					{
						stick=true;
					}
				}
				if (stick==false){
					redPortal = new Coordinate(redPortal.x+1, redPortal.y-1);
				}
				break;
			}
			case 4:	{
				boolean stick=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (redPortal.x+1==mWall.get(wallindex).x&&redPortal.y==mWall.get(wallindex).y)
					{
						stick=true;
					}
				}
				if (stick==false){
					redPortal = new Coordinate(redPortal.x+1, redPortal.y);
				}
				break;
			}
			case 5:	{
				boolean stick=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (redPortal.x+1==mWall.get(wallindex).x&&redPortal.y+1==mWall.get(wallindex).y)
					{
						stick=true;
					}
				}
				if (stick==false){
					redPortal = new Coordinate(redPortal.x+1, redPortal.y+1);
				}
				break;
			}
			case 6:	{
				boolean stick=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (redPortal.x==mWall.get(wallindex).x&&redPortal.y+1==mWall.get(wallindex).y)
					{
						stick=true;
					}
				}
				if (stick==false){
					redPortal = new Coordinate(redPortal.x, redPortal.y+1);
				}
				break;
			}
			case 7:	{
				boolean stick=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (redPortal.x-1==mWall.get(wallindex).x&&redPortal.y+1==mWall.get(wallindex).y)
					{
						stick=true;
					}
				}
				if (stick==false){
					redPortal = new Coordinate(redPortal.x-1, redPortal.y+1);
				}
				break;
			}
			}
		}

		if (blueRelease==false)
		{
			switch (blueDirection){
			case 0:	{
				bluePortal = new Coordinate(mScorpion.x - 1, mScorpion.y);
				break;
			}
			case 1:	{
				bluePortal = new Coordinate(mScorpion.x - 1, mScorpion.y-1);
				break;
			}
			case 2:	{
				bluePortal = new Coordinate(mScorpion.x, mScorpion.y-1);
				break;
			}
			case 3:	{
				bluePortal = new Coordinate(mScorpion.x + 1, mScorpion.y-1);
				break;
			}
			case 4:	{
				bluePortal = new Coordinate(mScorpion.x + 1, mScorpion.y);
				break;
			}
			case 5:	{
				bluePortal = new Coordinate(mScorpion.x + 1, mScorpion.y+1);
				break;
			}
			case 6:	{
				bluePortal = new Coordinate(mScorpion.x, mScorpion.y+1);
				break;
			}
			case 7:	{
				bluePortal = new Coordinate(mScorpion.x - 1, mScorpion.y+1);
				break;
			}
			}
		}
		else
		{
			switch (blueDirection){
			case 0:	{
				boolean stick=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (bluePortal.x-1==mWall.get(wallindex).x&&bluePortal.y==mWall.get(wallindex).y)
					{
						stick=true;
					}
				}
				if (stick==false){
					bluePortal = new Coordinate(bluePortal.x-1, bluePortal.y);
				}
				break;
			}
			case 1:	{
				boolean stick=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (bluePortal.x-1==mWall.get(wallindex).x&&bluePortal.y-1==mWall.get(wallindex).y)
					{
						stick=true;
					}
				}
				if (stick==false){
					bluePortal = new Coordinate(bluePortal.x-1, bluePortal.y-1);
				}
				break;
			}
			case 2:	{
				boolean stick=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (bluePortal.x==mWall.get(wallindex).x&&bluePortal.y-1==mWall.get(wallindex).y)
					{
						stick=true;
					}
				}
				if (stick==false){
					bluePortal = new Coordinate(bluePortal.x, bluePortal.y-1);
				}
				break;
			}
			case 3:	{
				boolean stick=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (bluePortal.x+1==mWall.get(wallindex).x&&bluePortal.y-1==mWall.get(wallindex).y)
					{
						stick=true;
					}
				}
				if (stick==false){
					bluePortal = new Coordinate(bluePortal.x+1, bluePortal.y-1);
				}
				break;
			}
			case 4:	{
				boolean stick=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (bluePortal.x+1==mWall.get(wallindex).x&&bluePortal.y==mWall.get(wallindex).y)
					{
						stick=true;
					}
				}
				if (stick==false){
					bluePortal = new Coordinate(bluePortal.x+1, bluePortal.y);
				}
				break;
			}
			case 5:	{
				boolean stick=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (bluePortal.x+1==mWall.get(wallindex).x&&bluePortal.y+1==mWall.get(wallindex).y)
					{
						stick=true;
					}
				}
				if (stick==false){
					bluePortal = new Coordinate(bluePortal.x+1, bluePortal.y+1);
				}
				break;
			}
			case 6:	{
				boolean stick=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (bluePortal.x==mWall.get(wallindex).x&&bluePortal.y+1==mWall.get(wallindex).y)
					{
						stick=true;
					}
				}
				if (stick==false){
					bluePortal = new Coordinate(bluePortal.x, bluePortal.y+1);
				}
				break;
			}
			case 7:	{
				boolean stick=false;
				int walllength = mWall.size();
				for (int wallindex = 0; wallindex < walllength; wallindex++) {
					if (bluePortal.x-1==mWall.get(wallindex).x&&bluePortal.y+1==mWall.get(wallindex).y)
					{
						stick=true;
					}
				}
				if (stick==false){
					bluePortal = new Coordinate(bluePortal.x-1, bluePortal.y+1);
				}
				break;
			}
			}
		}

		setTile(SCORPION1, mScorpion.x, mScorpion.y);

		setTile(SCORPION2, mScorpion.x, mScorpion.y+1);

		teleport();

		setTile(BLUE_PORTAL, bluePortal.x, bluePortal.y);

		setTile(RED_PORTAL, redPortal.x, redPortal.y);
	}

	/**
	 * Check if character dies or gains a point
	 */
	private void updateStatus() {
		for (Coordinate c : mSpikeList)
		{
			if ((mScorpion.x==c.x&&mScorpion.y==c.y)||(mScorpion.x==c.x&&mScorpion.y+1==c.y))
			{
				setMode(LOSE);
			}
		}

		for (Coordinate c : mHorizontalSpikeList)
		{
			if ((mScorpion.x==c.x&&mScorpion.y==c.y)||(mScorpion.x==c.x&&mScorpion.y+1==c.y))
			{
				setMode(LOSE);
			}
		}

		for (Coordinate c : mVerticalSpikeList)
		{
			if ((mScorpion.x==c.x&&mScorpion.y==c.y)||(mScorpion.x==c.x&&mScorpion.y+1==c.y))
			{
				setMode(LOSE);
			}
		}
		
		for (Coordinate c : mGhostList)
		{
			if ((mScorpion.x==c.x&&mScorpion.y==c.y)||(mScorpion.x==c.x&&mScorpion.y+1==c.y))
			{
				setMode(LOSE);
			}
		}
		
		for (Coordinate c : mDotList)
		{
			if ((mScorpion.x==c.x&&mScorpion.y==c.y)||(mScorpion.x==c.x&&mScorpion.y+1==c.y))
			{
				mDotList.remove(mDotList.size() - 1);
				addRandom(DOT);
				mScore++;
			}
		}
	}

	/**
	 * Simple class containing two integer values and a comparison function.
	 */
	private class Coordinate {
		public int x;
		public int y;

		public Coordinate(int newX, int newY) {
			x = newX;
			y = newY;
		}

		@Override
		public String toString() {
			return "Coordinate: [" + x + "," + y + "]";
		}
	}
}
