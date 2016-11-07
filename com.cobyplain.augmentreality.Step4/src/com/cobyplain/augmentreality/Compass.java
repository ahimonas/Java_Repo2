package com.cobyplain.augmentreality;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*public class Compass extends Activity implements  OnTouchListener{
 //main activity.java 
 ->sensor event (mlistener)
 ->Location Manager (locMgr)  <-helper class-> LocationUtils.fine(slower more accurate within 50 m).course faster less acurate
 on pause - release camer/gps 
 //on resume acqure camer/gps 

 CamerSurface view - dont need, eye canndy

 dpiUtilis_hleper class 
 */

public class Compass extends Activity {
	String globalDist = null;
	private static final String TAG = "Compass";
	private static boolean DEBUG = false;
	private SensorManager mSensorManager;
	private Sensor mSensor;
	public DrawSurfaceView mDrawView;
	LocationManager locMgr;

	// REALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
	// 38.887292, -77.032413

	//
	// 38.887292, -77.032413

	/*
	 * FEMMMAAAAAAAAAAAAAAAAAA 38.887292 -77.032413
	 * 
	 * 38.887292, -77.032413
	 */

	// old 38.887292 , -77.032413
	/** NEW (38.887292, -77.032413) **/

	static double globalLong = 38.887292;
	static double globalLat = -77.032413;
	public static double angleY1 = 0;

	// 38.887292, -77.032413

	/*
	 * static double globalLong = 38.887292; static double globalLat =
	 * -77.032413;
	 */

	// 38.887292, -77.032413 ENSCO!

	boolean visible = true;

	static double destLong = 0.0;
	static double destLat = 0.0;
	boolean buttonPressed = false;
	boolean arcGis = false;
	double dist2 = 0.0;
	public boolean onStart = true;
	private final SensorEventListener mListener = new SensorEventListener() {

		// sensore channged for compasses
		public void onSensorChanged(SensorEvent event) {
			angleY1 = event.values[1];
			if (DEBUG)
				Log.d(TAG, "sensorChanged (" + event.values[0] + ", "
						+ event.values[1] + ", " + event.values[2] + ")");
			if (mDrawView != null) {
				mDrawView.setOffset(event.values[0]);
				// invalidate redraw canvase on every compass change
				String myDist = Double.toString(dist2);
				globalDist = myDist;
				Log.d("DISTANCE FROM CENTER", myDist);
				dist2 = mDrawView.distInMetres(mDrawView.me, mDrawView.center);

				mDrawView.setMyLocation(38.887292, -77.032413);
				// mDrawView.setMyLocation(location.getLatitude(),
				// location.getLongitude());
				// Log.e("FUUUUUUUUUCKKKKKKKKKK", location.getLatitude() +
				// ":::::::::::::::::::" + location.getLongitude() );
				globalLong = 38.804551;
				globalLat = -77.032413;
				// if(dist2<300 && buttonPressed == false){

				// myView.setVisibility(View.GONE);
				// }
				if (arcGis == true) {
					arcGis = false;
					View myVieww = findViewById(R.id.linearLayout2);
					myVieww.setVisibility(View.GONE);
				}

				if (onStart == true) {
					onStart = false;
					DrawSurfaceView.oneLoc = false;
					View v2 = findViewById(R.id.linearLayout2);
					v2.setVisibility(View.GONE);

				}
				mDrawView.invalidate();
			}
		}

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

		// TextView tv = new TextView(this);
		// tv.setText("hello");
		// setContentView(R.);
		if (onStart == true)

			setContentView(R.layout.activity_main);
		// LinearLayout hiddenLayout =
		// (LinearLayout)findViewById(R.id.hiddenLayout);
		// Inflate the Hidden Layout Information View
		// FrameLayout myLayout = (FrameLayout)findViewById(R.id.activity_main);
		// View hiddenInfo = getLayoutInflater().inflate(R.layout.hidden,
		// myLayout, false);
		// myLayout.addView(hiddenInfo);
		mDrawView = (DrawSurfaceView) findViewById(R.id.drawSurfaceView);

		// mLocationManager.requestLocationUpdates(
		// LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES,
		// MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
		// Log.d("Network", "Network");
		// if (mLocationManager != null) {
		// location =
		// mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

		locMgr = (LocationManager) this.getSystemService(LOCATION_SERVICE); // <2>

		// locMgr.getBestProvider(createCoarseCriteria())

		// locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,time,distance,listener);
		LocationProvider high = locMgr.getProvider(locMgr.getBestProvider(
				LocationUtils.createCoarseCriteria(), true));

		globalLong = 38.887292;
		globalLat = -77.032413;
		mDrawView.setMyLocation(38.887292, -77.032413);
		// using high accuracy provider... to listen for updates
		locMgr.requestLocationUpdates(high.getName(), 0, 0f,
				new LocationListener() {
					public void onLocationChanged(Location location) {
						// do something here to save this new location
						// Log.d(TAG, "Location Changed");
						// mDrawView.invalidate();
						// mDrawView.setMyLocation(39.027158, -77.122599);
						// mDrawView.setMyLocation(location.getLatitude(),
						// location.getLongitude());
						// Log.e("FUUUUUUUUUCKKKKKKKKKK", location.getLatitude()
						// + ":::::::::::::::::::" + location.getLongitude() );
						// globalLong= 39.027158;
						// globalLat = -77.122599;
						// globalLong= location.getLatitude();
						// globalLat = location.getLongitude();
						// mDrawView.me.longitude = -77.122599;
						// mDrawView.me.latitude= 39.027158;
						// if(mDrawView.me.longitude != -33.870932)
						// {
						// // View myVieww2 = findViewById(R.id.linearLayout3);
						// myVieww2.setVisibility(View.GONE);
						//
						// visible = false;
						// }

						// Point u =mDrawView.props.get(0);//Northpole

						// double dist = mDrawView.distInMetres(mDrawView.me,
						// u);
						/*
						 * String myDist = Double.toString(dist2); globalDist =
						 * myDist; View myView =
						 * findViewById(R.id.linearLayout2);
						 * Log.d("DISTANCE FROM CENTER", myDist); dist2 =
						 * mDrawView.distInMetres(mDrawView.me,
						 * mDrawView.center);
						 * 
						 * //if(dist2<300 && buttonPressed == false){
						 * 
						 * // myView.setVisibility(View.GONE); //} if(arcGis ==
						 * true) { arcGis =false; View myVieww =
						 * findViewById(R.id.linearLayout2);
						 * myVieww.setVisibility(View.GONE); }
						 * 
						 * if(onStart == true){ onStart = false;
						 * DrawSurfaceView.oneLoc = false; View v2 =
						 * findViewById(R.id.linearLayout2);
						 * v2.setVisibility(View.GONE);
						 * 
						 * 
						 * } mDrawView.invalidate();
						 */
					}

					public void onStatusChanged(String s, int i, Bundle bundle) {

					}

					public void onProviderEnabled(String s) {
						// try switching to a different provider
					}

					public void onProviderDisabled(String s) {
						// try switching to a different provider
					}
				});

		/* ARCGIS BUTTON */
		Button btnSimple = (Button) findViewById(R.id.button1);
		// btnSimple.setPadding(100, 100,100, 100);
		btnSimple.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				View myView = findViewById(R.id.linearLayout2);

				buttonPressed = false;
				// mSensorManager.unregisterListener(mListener);
				Log.d("TAG", "DID I MAKE IT?AAAA");
				Intent intent = new Intent(v.getContext(), Activity2.class);
				startActivityForResult(intent, 0);
				Log.d("TAG", "DID I MAKE IT?BBBB");
				// if(dist2 > 300)
				// myView.setVisibility(View.VISIBLE);

			}
		});

		Button buttonb = (Button) findViewById(R.id.buttonB);

		// LinearLayout ll = (LinearLayout) findViewById(R.id.llid);
		// ll.addView(image);
		// btnSimple.setPadding(100, 100,100, 100);
		buttonb.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// set bool
				// DrawSurfaceView.oneLoc = true;

				// buttonPressed = true;
				View myView = findViewById(R.id.linearLayout2);
				if (visible == false) {

					myView.setVisibility(View.VISIBLE);
					visible = true;
				} else {
					myView.setVisibility(View.GONE);
					visible = false;
				}

			}
		});

		/* DIRECTIONS TO AND FROM */
		// 6200 yorkshire! (39.027158d, -77.1225989000000)
		/*
		 * Button york = (Button) findViewById(R.id.button11);
		 * //btnSimple.setPadding(100, 100,100, 100);
		 * york.setOnClickListener(new OnClickListener() { public void
		 * onClick(View v){ DrawSurfaceView.oneLoc = true; //globalLong =
		 * mLocation.getX(); //globalLat = mLocation.gety(); View myView =
		 * findViewById(R.id.linearLayout2); myView.setVisibility(View.GONE);
		 * mDrawView.props.get(0).setAttr(38.987188, -76.947540,
		 * "Stamp Student Union"); destLong = 38.987188; destLat = -76.947540;
		 * mDrawView.mSpots[0] = BitmapFactory.decodeResource(
		 * mDrawView.context1.getResources(), R.drawable.stamp); } });
		 * 
		 * //stamp student! 38.988049, -76.944403 Button stamp = (Button)
		 * findViewById(R.id.button21); //btnSimple.setPadding(100, 100,100,
		 * 100); stamp.setOnClickListener(new OnClickListener() { public void
		 * onClick(View v){ DrawSurfaceView.oneLoc = true; View myView =
		 * findViewById(R.id.linearLayout2); myView.setVisibility(View.GONE);
		 * //props.add(new Point(39.026104,-77.129791, "Walter Johnson"));
		 * mDrawView.props.get(0).setAttr(39.026104,-77.129791,
		 * "Walter Johnson"); destLong = 39.026104; destLat = -77.129791;
		 * DrawSurfaceView.oneLoc = true;
		 */
		// 38.990788, -76.936294, "A.V. Williams Building (AVW)"));

		// 40.770446, -111.891923
		Button avw = (Button) findViewById(R.id.button11);

		avw.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				// props.add(new Point(39.026104,-77.129791, "Walter Johnson"));
				mDrawView.props.get(0).setAttr(38.869093, -77.06671,
						"Air Force Memorial");
				destLong = 38.869093;
				destLat = -77.06671;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.pento);
				// globalLong = mDrawView.props.get(0).x;
				// globalLat = mDrawView.props.get(0).y;
			}
		});

		// 38.988127, -76.944745,
		// "Adele H. Stamp Student Union Building (SSU)"));
		Button stamp = (Button) findViewById(R.id.button21);

		stamp.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);

				mDrawView.props.get(0).setAttr(38.988127, -76.944745, "STAMP");
				destLong = 38.988127;
				destLat = -76.944745;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(),
						R.drawable.stampstamp);
			}
		});

		// 38.991650, -76.939001,
		// "Animal Science/Agricultural Engineering Building (ANS)"));
		Button ans = (Button) findViewById(R.id.button31);

		ans.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);

				mDrawView.props.get(0).setAttr(38.991650, -76.939001, "ANS");
				destLong = 38.991650;
				destLat = -76.939001;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.ansans);
			}
		});

		// need pic
		// new Point(38.984446, -76.947893 "Architecture Building (ARC)");
		/*

*/

		// 38.985135, -76.947773, "Art-Sociology Building (ASY)"));

		Button asy = (Button) findViewById(R.id.button51);

		asy.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);

				mDrawView.props.get(0).setAttr(38.985135, -76.947773, "ASY");
				destLong = 38.985135;
				destLat = -76.947773;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.asyasy);
			}
		});

		// 38.992817, -76.942622, "Bel Air Hall (BEL)"));
		Button bel = (Button) findViewById(R.id.button61);

		bel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);

				mDrawView.props.get(0).setAttr(38.992817, -76.942622, "BEL");
				destLong = 38.992817;
				destLat = -76.942622;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.belbel);
			}
		});

		// 38.986791, -76.947372, "Benjamin Building (EDU)"));
		Button edu = (Button) findViewById(R.id.button72);

		edu.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);

				mDrawView.props.get(0).setAttr(38.986791, -76.947372, "EDU");
				destLong = 38.986791;
				destLat = -76.947372;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.eduedu);
			}
		});

		// 38.988822, -76.942602, "Biology-Psychology Building (BPS)"));
		Button bps = (Button) findViewById(R.id.button82);

		bps.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);

				mDrawView.props.get(0).setAttr(38.988822, -76.942602, "BPS");
				destLong = 38.988822;
				destLat = -76.942602;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.bpsbps);
			}
		});

		// 38.989032, -76.943137, "Biosciences Research Building (BRB)"));
		Button brb = (Button) findViewById(R.id.button92);

		brb.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);

				mDrawView.props.get(0).setAttr(38.989032, -76.943137, "BRB");
				destLong = 38.989032;
				destLat = -76.943137;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.brbbrb);
			}
		});

		// need pic
		// new Point(38.990517, -76.948282, Byrd Stadium Complex (BRD)");

		// props.add(new Point(38.992196, -76.943044,
		// "Cambridge Community Center (CCC)"));
		Button ccc = (Button) findViewById(R.id.button93);

		ccc.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);

				mDrawView.props.get(0).setAttr(38.992196, -76.943044, "CCC");
				destLong = 38.992196;
				destLat = -76.943044;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.cccccc);
			}
		});

		// props.add(new Point(38.993570, -76.945228,
		// "Campus Recreation Center (Epply)"));
		Button epply = (Button) findViewById(R.id.button94);

		epply.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.993570, -76.945228, "EPPLY");
				destLong = 38.993570;
				destLat = -76.945228;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(),
						R.drawable.epplyepply);
			}
		});

		// props.add(new Point(38.990414, -76.939168,
		// "Chemical and Nuclear Engineering Building (CHE)");

		Button che = (Button) findViewById(R.id.button95);

		che.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.990414, -76.939168, "CHE");
				destLong = 38.990414;
				destLat = -76.939168;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.cheche);
			}
		});

		// props.add(new Point(38.989930, -76.940117,
		// "Chemistry Building (CHM)"));
		Button chm = (Button) findViewById(R.id.button96);

		chm.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.989930, -76.940117, "CHM");
				destLong = 38.989930;
				destLat = -76.940117;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.chmchm);
			}
		});

		// props.add(new Point(38.990904, -76.950527,
		// "Clarice Smith Performing Arts Center (PAC)"));

		Button pac = (Button) findViewById(R.id.button97);
		pac.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.990904, -76.950527, "PAC");
				destLong = 38.990904;
				destLat = -76.950527;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.pacpac);
			}
		});

		// props.add(new Point(38.988006, -76.946761,
		// "Cole Student Activities Building (COL)"));
		Button col = (Button) findViewById(R.id.button98);
		col.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.988006, -76.946761, "COL");
				destLong = 38.988006;
				destLat = -76.946761;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.colcol);
			}
		});
		// 99
		// props.add(new Point(38.990000, -76.936194,
		// "Computer Science Instructional Center(CSI)"));
		Button csi = (Button) findViewById(R.id.button99);
		csi.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.990000, -76.936194, "CSI");
				destLong = 38.990000;
				destLat = -76.936194;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.csicsi);
			}
		});
		// 100
		// props.add(new Point(38.990984, -76.942578,
		// "Computer and Space Sciences Building (CSS)"));
		Button css = (Button) findViewById(R.id.button100);
		css.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.990984, -76.942578, "CSS");
				destLong = 38.990984;
				destLat = -76.942578;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.csscss);
			}
		});
		// props.add(new Point(38.989697, -76.93776,
		// "Education Annex, West (EDA)"));
		// 102
		// props.add(new Point(38.991836, -76.946687, "Ellicot Hall (ELL)"));
		Button ell = (Button) findViewById(R.id.button102);
		ell.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.991836, -76.946687, "ELL");
				destLong = 38.991836;
				destLat = -76.946687;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.ellell);
			}
		});
		// 103
		// props.add(new Point(38.990727, -76.937097,
		// "Engineering Annex (EAB)"));
		Button eab = (Button) findViewById(R.id.button103);
		eab.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.990727, -76.937097, "EAB");
				destLong = 38.990727;
				destLat = -76.937097;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.eabeab);
			}
		});
		// 104
		// props.add(new Point(38.989263, -76.937929,
		// "Engineering Laboratory Building	(EGL)"));
		Button egl = (Button) findViewById(R.id.button104);
		egl.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.989263, -76.937929, "EGL");
				destLong = 38.989263;
				destLat = -76.937929;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.eglegl);
			}
		});
		// 105
		// props.add(new Point(38.985132, -76.943122,
		// "Francis Scott Key Hall (KEY)"));
		Button key = (Button) findViewById(R.id.button105);
		key.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.985132, -76.943122, "KEY");
				destLong = 38.985132;
				destLat = -76.943122;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.keykey);
			}
		});
		// 106
		// props.add(new Point(38.988159, -76.940923,
		// "Geology Building (GEO)"));
		Button geo = (Button) findViewById(R.id.button106);
		geo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.988159, -76.940923, "GEO");
				destLong = 38.988159;
				destLat = -76.940923;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.geogeo);
			}
		});
		// hjp har hzf hbk itv jmp keb jmz
		// jrn jul kni lef mmh egr mth mck
		// mcb mor ncc phy pls pkt qan arm
		// rit sph shm shr skn sdh sqh sym
		// tlf tws tap tur tyd wtu wds

		// 39 106- 145

		// 38.986857, -76.943271, hjp 107
		Button hjp = (Button) findViewById(R.id.button107);
		hjp.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.986857, -76.943271, "HJP");
				destLong = 38.986857;
				destLat = -76.943271;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.hjphjp);
			}
		});
		// 38.986042, -76.93896, har 108
		Button har = (Button) findViewById(R.id.button108);
		har.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.986042, -76.93896, "HAR");
				destLong = 38.986042;
				destLat = -76.93896;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.harhar);
			}
		});
		// 38.986869, -76.941916, hzf 109

		Button hzf = (Button) findViewById(R.id.button109);
		hzf.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.986042, -76.93896, "HZF");
				destLong = 38.986042;
				destLat = -76.93896;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.hzfhzf);
			}
		});
		// 38.988131, -76.941574, hbk 110
		Button hbk = (Button) findViewById(R.id.button110);
		hbk.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.988131, -76.941574, "HBK");
				destLong = 38.988131;
				destLat = -76.941574;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.hbkhbk);
			}
		});
		// 38.989566, -76.938311 itv 111
		Button itv = (Button) findViewById(R.id.button111);
		itv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.989566, -76.938311, "ITV");
				destLong = 38.989566;
				destLat = -76.938311;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.itvitv);
			}
		});
		// 38.990504, -76.940287 jmp 112
		Button jmp = (Button) findViewById(R.id.button112);
		jmp.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.990504, -76.940287, "JMP");
				destLong = 38.990504;
				destLat = -76.940287;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.jmpjmp);
			}
		});
		// 38.991057, -76.938113, keb 146
		Button keb = (Button) findViewById(R.id.button146);
		keb.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.991057, -76.938113, "KEB");
				destLong = 38.991057;
				destLat = -76.938113;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.kebkeb);
			}
		});
		// 38.991057, -76.938113, jmz 113 YOU FUCKED UP! SAME COORDINATES AS KEB
		Button jmz = (Button) findViewById(R.id.button113);
		jmz.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.991057, -76.938113, "JMZ");
				destLong = 38.991057;
				destLat = -76.938113;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.jmzjmz);
			}
		});
		// 38.985236, -76.944541, jrn 114
		Button jrn = (Button) findViewById(R.id.button114);
		jrn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.985236, -76.944541, "JRN");
				destLong = 38.985236;
				destLat = -76.944541;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.jrnjrn);
			}
		});
		// 38.990850, -76.943623, jul 115
		Button jul = (Button) findViewById(R.id.button115);
		jul.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.990850, -76.943623, "JUL");
				destLong = 38.990850;
				destLat = -76.943623;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.juljul);
			}
		});
		// 38.986939, -76.948485, kni 116
		Button kni = (Button) findViewById(R.id.button116);
		kni.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.986939, -76.948485, "KNI");
				destLong = 38.986939;
				destLat = -76.948485;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.knikni);
			}
		});
		// 38.983666, -76.943691, lef 117
		Button lef = (Button) findViewById(R.id.button117);
		lef.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.983666, -76.943691, "LEF");
				destLong = 38.983666;
				destLat = -76.943691;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.leflef);
			}
		});
		// 38.984942, -76.94081, mmh 145
		Button mmh = (Button) findViewById(R.id.button145);
		mmh.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.984942, -76.94081, "MMH");
				destLong = 38.984942;
				destLat = -76.94081;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.mmhmmh);
			}
		});
		// 38.988892, -76.938163, egr 118
		Button egr = (Button) findViewById(R.id.button118);
		egr.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.988892, -76.938163, "EGR");
				destLong = 38.988892;
				destLat = -76.938163;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.egregr);
			}
		});
		// 38.988615, -76.939248, mth 119
		Button mth = (Button) findViewById(R.id.button119);
		mth.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.988615, -76.939248, "MTH");
				destLong = 38.988615;
				destLat = -76.939248;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.mthmth);
			}
		});
		// 38.985986, -76.945107, mck 120
		Button mck = (Button) findViewById(R.id.button120);
		mck.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.985986, -76.945107, "MCK");
				destLong = 38.985986;
				destLat = -76.945107;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.mmck);
			}
		});
		// 38.988102, -76.943457, mcb 121
		Button mcb = (Button) findViewById(R.id.button121);
		mcb.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.988102, -76.943457, "MCB");
				destLong = 38.988102;
				destLat = -76.943457;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.mcbmcb);
			}
		});
		// 38.984323, -76.944186, mor 122
		Button mor = (Button) findViewById(R.id.button122);
		mor.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.984323, -76.944186, "MOR");
				destLong = 38.984323;
				destLat = -76.944186;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.mormor);
			}
		});
		// 38.988128, -76.943826, ncc 123
		Button ncc = (Button) findViewById(R.id.button123);
		ncc.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.988128, -76.943826, "NCC");
				destLong = 38.988128;
				destLat = -76.943826;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.nccncc);
			}
		});
		// 38.988534, -76.94014, phy 124
		Button phy = (Button) findViewById(R.id.button124);
		phy.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.988534, -76.94014, "PHY");
				destLong = 38.988534;
				destLat = -76.94014;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.phyphy);
			}
		});
		// 38.988829, -76.94117, pls 125
		Button pls = (Button) findViewById(R.id.button125);
		pls.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.988829, -76.94117, "PLS");
				destLong = 38.988829;
				destLat = -76.94117;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.plspls);
			}
		});
		// 38.984355, -76.946062, pkt 126
		Button pkt = (Button) findViewById(R.id.button126);
		pkt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.984355, -76.946062, "PKT");
				destLong = 38.984355;
				destLat = -76.946062;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.pktpkt);
			}
		});
		// 38.985291, -76.946316, qan 127
		Button qan = (Button) findViewById(R.id.button127);
		qan.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.985291, -76.946316, "QAN");
				destLong = 38.985291;
				destLat = -76.946316;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.qanqan);
			}
		});
		// 38.989697, -76.93776, arm 128
		Button arm = (Button) findViewById(R.id.button128);
		arm.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.989697, -76.93776, "ARM");
				destLong = 38.989697;
				destLat = -76.93776;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.armarm);
			}
		});
		// 38.985020, -76.93644, rit 129
		Button rit = (Button) findViewById(R.id.button129);
		rit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.985020, -76.93644, "RIT");
				destLong = 38.985020;
				destLat = -76.93644;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.ritrit);
			}
		});
		// 38.993454, -76.943156, sph 130
		Button sph = (Button) findViewById(R.id.button130);
		sph.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.993454, -76.943156, "SPH");
				destLong = 38.993454;
				destLat = -76.943156;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.sphsph);
			}
		});
		// 38.983942, -76.942713, shm 131
		Button shm = (Button) findViewById(R.id.button131);
		shm.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.983942, -76.942713, "SHM");
				destLong = 38.983942;
				destLat = -76.942713;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.shmshm);
			}
		});
		// 38.989697, -76.93776, shr 133
		Button shr = (Button) findViewById(R.id.button133);
		shr.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.989697, -76.93776, "SHR");
				destLong = 38.989697;
				destLat = -76.93776;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.shrshr);
			}
		});
		// 38.984801, -76.941862, skn 134
		Button skn = (Button) findViewById(R.id.button134);
		skn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.984801, -76.941862, "SKN");
				destLong = 38.984801;
				destLat = -76.941862;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.sknskn);
			}
		});
		// 38.983024, -76.943691, sdh 135
		Button sdh = (Button) findViewById(R.id.button135);
		sdh.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.983024, -76.943691, "SDH");
				destLong = 38.983024;
				destLat = -76.943691;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.sdhsdh);
			}
		});
		// 38.982088, -76.943779, sqh 136
		Button sqh = (Button) findViewById(R.id.button136);
		sqh.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.982088, -76.943779, "SQH");
				destLong = 38.982088;
				destLat = -76.943779;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.sqhsqh);
			}
		});

		// 38.987095, -76.94065, sym 137
		Button sym = (Button) findViewById(R.id.button137);
		sym.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.987095, -76.94065, "SYM");
				destLong = 38.987095;
				destLat = -76.94065;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.symsym);
			}
		});

		// 38.984796, -76.943115, tlf 138
		Button tlf = (Button) findViewById(R.id.button138);
		tlf.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.984796, -76.943115, "TLF");
				destLong = 38.984796;
				destLat = -76.943115;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.tlftlf);
			}
		});
		// 38.985944, -76.948237, tws 139
		Button tws = (Button) findViewById(R.id.button139);
		tws.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.985944, -76.948237, "TWS");
				destLong = 38.985944;
				destLat = -76.948237;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.twstws);
			}
		});
		// tap FUCK
		// 38.986111, -76.937344, tur 140
		Button tur = (Button) findViewById(R.id.button140);
		tur.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.986111, -76.937344, "TUR");
				destLong = 38.98611;
				destLat = -76.937344;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.turtur);
			}
		});
		// 38.984791, -76.943973, tyd 141
		Button tyd = (Button) findViewById(R.id.button141);
		tyd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.984791, -76.943973, "TYD");
				destLong = 38.98611;
				destLat = -76.937344;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.tydtyd);
			}
		});
		// 38.983057, -76.947058, vmh 142
		Button vmh = (Button) findViewById(R.id.button142);
		vmh.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.983057, -76.947058, "VMH");
				destLong = 38.983057;
				destLat = -76.947058;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.vmhvmh);
			}
		});
		// byrd FUCK2
		// 38.989887, -76.936904, wtu 143
		Button wtu = (Button) findViewById(R.id.button143);
		wtu.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.989887, -76.936904, "WTU");
				destLong = 38.989887;
				destLat = -76.936904;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.wtuwtu);
			}
		});
		// 38.985162, -76.94196, wds 144
		Button wds = (Button) findViewById(R.id.button144);
		wds.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				mDrawView.props.get(0).setAttr(38.985162, -76.94196, "WDS");
				destLong = 38.985162;
				destLat = -76.94196;
				DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.wdswds);
			}
		});

		// 38.985162, -76.94196, wds 144
		Button wj = (Button) findViewById(R.id.button500);
		wj.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DrawSurfaceView.oneLoc = true;
				View myView = findViewById(R.id.linearLayout2);
				myView.setVisibility(View.GONE);
				// 38.889484, -77.035279
				mDrawView.props.get(0).setAttr(38.889484, -77.035279,
						"Washington Monument");
				destLong = 38.889484;
				destLat = -77.035279;
				// DrawSurfaceView.oneLoc = true;
				mDrawView.mSpots[0] = BitmapFactory.decodeResource(
						mDrawView.context1.getResources(), R.drawable.washmoo);
			}
		});

	}

	@Override
	protected void onPause() {
		mSensorManager.unregisterListener(mListener, mSensor);
		Log.d(TAG, "onPause");
		// mSensorManager.unregisterListener(mListener);
		// Log.d(TAG, "666666666666666666666666666666666666 + " +
		// DrawSurfaceView.oneLoc);
		arcGis = true;
		super.onPause();
	}

	@Override
	protected void onResume() {
		if (DEBUG)
			Log.d(TAG, "onResume");
		super.onResume();
		mSensorManager.registerListener(mListener, mSensor,
				SensorManager.SENSOR_DELAY_GAME);
	}

	protected void onStop() {
		if (DEBUG)
			Log.d(TAG, "onStop");
		mSensorManager.unregisterListener(mListener, mSensor);
		super.onStop();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Handle the back button
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// Ask the user if they want to quit
			new AlertDialog.Builder(this)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle(R.string.quit)
					.setMessage(R.string.really_quit)
					.setPositiveButton(R.string.yes,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {

									// Stop the activity
									Compass.this.finish();
								}

							}).setNegativeButton(R.string.no, null).show();

			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}

	}
}
