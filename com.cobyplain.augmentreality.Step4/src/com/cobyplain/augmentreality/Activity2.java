
package com.cobyplain.augmentreality;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnLongPressListener;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.esri.core.symbol.SimpleLineSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.esri.core.tasks.ags.na.NAFeaturesAsFeature;
import com.esri.core.tasks.ags.na.Route;
import com.esri.core.tasks.ags.na.RoutingDirection;
import com.esri.core.tasks.ags.na.RoutingParameters;
import com.esri.core.tasks.ags.na.RoutingResult;
import com.esri.core.tasks.ags.na.RoutingTask;
import com.esri.core.tasks.ags.na.StopGraphic;

import com.cobyplain.augmentreality.DrawSurfaceView;


public class Activity2 extends Activity {
	String str = "";
	MapView map = null;
	//long is x is -77
	//lat is y 
	
	double myLong = Compass.globalLong;
	double myLat = Compass.globalLat;
	
	Point mLocation = new Point(myLat, myLong);
	
	public double destLat =   Compass.destLat;
	public double destLong =  Compass.globalLong;
	
	
	  Point endPt = new Point(destLat, destLong);
	  
	  final SpatialReference wm = SpatialReference.create(102100);
	  final SpatialReference egs = SpatialReference.create(4326);
	  Route curRoute = null;
	  String routeSummary = null;
	  // Global results variable for calculating route on separate thread
	  RoutingResult mResults = null;
	  // Variable to hold server exception to show to user
	  Exception mException = null;
	  ArrayList<String> curDirections = null;
	  SimpleLineSymbol segmentHider = new SimpleLineSymbol(Color.WHITE, 5);
	  //TextView directionsLabel;
	  ProgressDialog dialog;
	  GraphicsLayer routeLayer, hiddenSegmentsLayer;
	  final Handler mHandler = new Handler();
	  ArcGISTiledMapServiceLayer tileLayer;  
	  int selectedSegmentID = -1;
		  
	

	  //PRINT LONG AND LAT HERE TO MAKE SURE THEY ARE CORRECTLY SET
	  final Runnable mUpdateResults = new Runnable() {
		    public void run() {
		      updateUI();
		    }
		  };

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		
		//Log.d("TAG", "DID I MAKE IT?123");
		super.onCreate(savedInstanceState);
		//Log.d("TAG", "DID I MAKE IT?4444");
		setContentView(R.layout.activity2);
		
		//DrawSurfaceView.oneLoc = false;
		
		//Log.d("TAG", "DID I MAKE IT?4444");
		mLocation = new Point(myLat, myLong);
		//Log.d("TAG", "555555555555555555555 mLocation." +  mLocation.getX() +  mLocation.getY());
		//Log.d("TAG", "666666666666666666666 mLocation." +  mLocation.getX() +  mLocation.getY());   
	    map = (MapView)findViewById(R.id.map); 
		Point p = (Point) GeometryEngine.project(mLocation, egs, wm);
		//Point p = (Point) GeometryEngine.project(mLocation, egs, wm);
		map.zoomToResolution(p, 5.0);
		        
	    // Add tiled layer to MapView
		 //http://server.arcgisonline.com/ArcGIS/rest/services/
	        tileLayer = new ArcGISTiledMapServiceLayer("http://server.arcgisonline.com/arcgis/rest/services/World_Imagery/MapServer");
	       map.addLayer(tileLayer);
	        Log.d("TAG", "DID I MAKE IT CCCC");
	        // Add the route graphic layer (shows the full route)
	        routeLayer = new GraphicsLayer();
	        map.addLayer(routeLayer);

	        // Add the hidden segments layer (for highlighting route segments)
	        hiddenSegmentsLayer = new GraphicsLayer();
	        map.addLayer(hiddenSegmentsLayer);

	        // Make the segmentHider symbol "invisible"
	        segmentHider.setAlpha(1);
/*
	        
	        RoutingTask rt = new RoutingTask( 
	                  "http://sampleserver3.arcgisonline.com/ArcGIS/rest/services/Network/USA/NAServer/Route",
	                  null);
	        
	     create a graphics layer with some sample stops
	         GraphicsLayer stopsGraphicsLayer = new GraphicsLayer();
	        map.addLayer(stopsGraphicsLayer);
	        PictureMarkerSymbol destinationSymbol = new PictureMarkerSymbol(getResources().getDrawable(R.drawable.flag_finish));
	        Point startPt = new Point( -77.12259890000001, 39.027158);  
	        Graphic startGraphic = new Graphic(startPt, destinationSymbol);
	        Point endPt = new Point(-77.12259890000001, 39.0252960);
	        Graphic endGraphic = new Graphic(endPt, destinationSymbol);
	        stopsGraphicsLayer.addGraphic(startGraphic);
	        stopsGraphicsLayer.addGraphic(endGraphic);

	        // create a set of stops for the route parameters
	        NAFeaturesAsFeature stops = new NAFeaturesAsFeature();
	        stops.setSpatialReference(map.getSpatialReference());
	        stops.addFeature(startGraphic);
	        stops.addFeature(endGraphic);

	        /* create parameters
	        RouteParameters routeParameters = task.retrieveDefaultRouteTaskParameters();
	        routeParameters.setStops(stops);
	        routeParameters.setOutSpatialReference(map.getSpatialReference());
	        routeParameters.setReturnDirections(true);
	        routeParameters.setFindBestSequence(true);
	        routeParameters.setPreserveFirstStop(true);
			*/
	
	        Log.d("TAG", "DID I MAKE IT DDDDD");
	      //  map.setOnLongPressListener(new OnLongPressListener() {
		   
	    //  private static final long serialVersionUID = 1L;

	     // public void onLongPress(final float x, final float y) {
	    	  Log.d("TAG", "DID I MAKELONGPRESS");
	        //Clear the graphics and empty the directions list
	        routeLayer.removeAll();
	        hiddenSegmentsLayer.removeAll();
	        curDirections = new ArrayList<String>();
	        mResults = null;

	        // retrieve the user clicked location
	       // final Point loc = map.toMapPoint(x, y);

	        // Show that the route is calculating
	        dialog = ProgressDialog.show(Activity2.this, "", "Calculating route...", true);
	        // Spawn the request off in a new thread to keep UI responsive
	        Thread t = new Thread() {
	          @Override
	          public void run() {
	            try {
	           	 //Log.e("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL","AHH" );	
	              // Start building up routing parameters
	              RoutingParameters rp = new RoutingParameters();
	              NAFeaturesAsFeature rfaf = new NAFeaturesAsFeature();
	              // Convert point to EGS (decimal degrees)
	             // Point p = (Point) GeometryEngine.project(loc, wm, egs);
	            
	              // Create the stop points (start at our location, go to pressed location)
	              mLocation.setX(myLat);
	              mLocation.setY(myLong);
	              
	              endPt.setX(destLat);
	              endPt.setY(destLong);
	              
	              
	              StopGraphic point1 = new StopGraphic(mLocation);
	              StopGraphic point2 = new StopGraphic(endPt);
	              
	              
	              rfaf.setFeatures(new Graphic[] {point1, point2});
	              rfaf.setCompressedRequest(true);
	              rp.setStops(rfaf);
	              //Log.d("TAG", "GOT HERE1");
	              // Set the routing service output SR to our map service's SR
	              rp.setOutSpatialReference(wm);

	              // Create a new routing task pointing to an NAService (null credentials -> free service)
	              RoutingTask rt = new RoutingTask( 
	                  "http://sampleserver3.arcgisonline.com/ArcGIS/rest/services/Network/USA/NAServer/Route",
	                  null);
	              Log.d("TAG", "After Rest");
	              // Solve the route and use the results to update UI when received
	              mResults = rt.solve(rp);
	              mHandler.post(mUpdateResults);
	            } 
	            
	            catch (Exception e) {
	              mException = e;
	              mHandler.post(mUpdateResults);
	            }
	          }
	        };
	        // Start the operation
	        t.start();
	    // }
	    //});
	  }


	  /**
	   * Updates the UI after a successful rest response has been received.
	   */
	  void updateUI() {
		  Log.d("TAG", "Updating UI");
	    dialog.dismiss();
	    if(mResults == null) {
	      Toast.makeText(Activity2.this, mException.toString(), Toast.LENGTH_LONG).show();
	      return;
	    }
	    
	    myLong = Compass.globalLong;
	    myLat = Compass.globalLat;
	    
	    destLat =   Compass.destLat;
	    destLong =  Compass.globalLong;
	    
	    mLocation = new Point(myLat, myLong);
	    /*
	       mLocation.setX(myLat);
           mLocation.setY(myLong);
           
           endPt.setX(destLat);
           endPt.setY(destLong);
	    */
	    curRoute = mResults.getRoutes().get(0);
	    // Symbols for the route and the destination (blue line, checker flag)
	    SimpleLineSymbol routeSymbol = new SimpleLineSymbol(Color.BLUE, 3);
	    PictureMarkerSymbol destinationSymbol = new PictureMarkerSymbol(getResources().getDrawable(R.drawable.flag_finish));
	    PictureMarkerSymbol startSymbol = new PictureMarkerSymbol(getResources().getDrawable(R.drawable.markertko));
	    
	    // Add all the route segments with their relevant information to the
	    // hiddenSegmentsLayer, and add the direction information to the list
	    // of directions
	    
	    str = str +  "ETA: " + curRoute.getTotalTime() + " minutes \n" 
	    + "Total Distance: " + curRoute.getTotalLength() + " miles \n" + "LISTED DIRECTIONS!" + "\n";
	    
	    for(RoutingDirection rd : curRoute.getRoutingDirections()) {
	      HashMap<String, Object> attribs = new HashMap<String, Object>();
	      attribs.put("text", rd.getText());
	      attribs.put("time", Double.valueOf(rd.getTime()));
	      attribs.put("length", Double.valueOf(rd.getLength()));
	      curDirections.add(String.format("%s%nTime: %.1f minutes, Length: %.1f miles",
	          rd.getText(), rd.getTime(), rd.getLength()));
	      
	      
	      // TIME SPENT ON STREET IDK REFFERENCE! "street duration " +   rd.getTime() +
	      str = str + rd.getText() +  " " + rd.getLength() + " miles" + "\n";
	      
	      Log.d("TAG", "UPDATE NEWEST STRING!!!!!!!!!!" + "TEXT: " + rd.getText() + "time" +   rd.getTime() + "length" +  rd.getLength());
	      hiddenSegmentsLayer.addGraphic(new Graphic(rd.getGeometry(), segmentHider, attribs, null));
	    }
	    // Reset the selected segment
	    selectedSegmentID = -1;
	    Log.d("TAG", "Updatied UI");
	    // Add the full route graphic and destination graphic to the routeLayer 
	    Graphic routeGraphic = new Graphic(curRoute.getRoute().getGeometry(), routeSymbol);
	    
	   // PictureMarkerSymbol startSymbol = new PictureMarkerSymbol(getResources().getDrawable(R.drawable.flag_finish2));
	    
	    Graphic strGraphic = new Graphic(((Polyline)routeGraphic.getGeometry()).getPoint(((Polyline)routeGraphic.getGeometry()).getPointCount()-1), destinationSymbol);
	    Graphic endGraphic = new Graphic(((Polyline)routeGraphic.getGeometry()).getPoint(0),startSymbol);

	    routeLayer.addGraphics(new Graphic[] {strGraphic, routeGraphic, endGraphic});
	    
	    
	   // StopGraphic point22 = new StopGraphic(endPt);
	    //Graphic startGraphic = new Graphic(point22, startSymbol);
	   // routeLayer.addGraphic(startGraphic);
	    Log.d("TAG", "Updatied Graphics");
	    // Get the full route summary and set it as our current label
	    
	    routeSummary = String.format("%s%nTotal time: %.1f minutes, length: %.1f miles", 
	        curRoute.getRouteName(), curRoute.getTotalTime(), curRoute.getTotalLength());
	    
	    //directionsLabel.setText(routeSummary);
	    Log.d("TAG", "Updated Syting format ");
	    Log.d("TAG", "Updated Route  +++++++" +  routeSummary  );
	    // Zoom to the extent of the entire route with a padding
	    map.setExtent(curRoute.getEnvelope(), 250);
	    
	   
	    
	    
	    Button btnSimple1 = (Button) findViewById(R.id.button55);
		//btnSimple.setPadding(100, 100,100, 100);
		btnSimple1.setOnClickListener(new OnClickListener() {
		TextView tv = (TextView) findViewById(R.id.helloT12);
	
			public void onClick(View v){
			//mSensorManager.unregisterListener(mListener);	
				Log.d("TAG", "500000000000000000000000");
				tv.setVisibility((tv.getVisibility() == View.VISIBLE) 
                           ? View.GONE : View.VISIBLE);
				tv.setText(str);
			//Intent intent = new Intent(v.getContext(), Activity3.class);
			//startActivityForResult(intent, 0); 
			//Log.d("TAG", "DID I MAKE IT?BBBB");
		}
		});
	  }

	protected void onPause() {
		
		super.onPause();
		map.pause();
	}

	protected void onResume() {
		super.onResume();
		map.unpause();
	}

}