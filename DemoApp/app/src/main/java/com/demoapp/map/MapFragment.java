package com.demoapp.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.demoapp.R;
import com.demoapp.google_map_models.MapModel;
import com.demoapp.services.CustomStringRequest;
import com.demoapp.utilities.Constants;
import com.demoapp.utilities.Utilities;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by android5 on 26/7/16.
 */
public class MapFragment extends Fragment implements GoogleMap.OnCameraChangeListener {

    private MapView mMapView;
    private GoogleMap map;
    private LatLng mOrigin;
    private LatLng mDestination;

    private String fromLatitude;
    private String fromLongitude;
    private String toLatitude;
    private String toLongitude;

    private String url = null;
    private boolean noFocus;
    private CustomStringRequest mapRequest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return init(inflater, container);
    }

    /**
     * @description This method is used to find views from the layout
     */
    private View init(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mMapView = (MapView) view.findViewById(R.id.map_view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments().getString(Constants.START_LATITUDE) != null) {
            fromLatitude = getArguments().getString(Constants.START_LATITUDE);
        }
        if (getArguments().getString(Constants.START_LONGITUDE) != null) {
            fromLongitude = getArguments().getString(Constants.START_LONGITUDE);
        }
        if (getArguments().getString(Constants.END_LATITUDE) != null) {
            toLatitude = getArguments().getString(Constants.END_LATITUDE);
        }
        if (getArguments().getString(Constants.END_LONGITUDE) != null) {
            toLongitude = getArguments().getString(Constants.END_LONGITUDE);
        }

        initializeMap(savedInstanceState);
        setLocations();
    }


    /**
     * @description This method is used to set markers on the map
     */
    private void setLocations() {
        if (map != null) {
            if (fromLatitude != null && fromLongitude != null) {
                Log.e("from", ":lati " + fromLatitude);
                Log.e("from", ":longi " + fromLongitude);
                map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(fromLatitude), Double.parseDouble(fromLongitude)))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_pin)));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(Double.parseDouble(fromLatitude)
                        , Double.parseDouble(fromLongitude))).zoom(12f).build();
                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                mOrigin = new LatLng(Double.parseDouble(fromLatitude), Double.parseDouble(fromLongitude));
            }
            if (toLatitude != null && toLongitude != null) {
                map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(toLatitude), Double.parseDouble(toLongitude)))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_pin)));
                Log.e("to", ":lati " + toLatitude);
                Log.e("to", ":longi " + toLatitude);
                mDestination = new LatLng(Double.parseDouble(toLatitude), Double.parseDouble(toLongitude));
            }
        }

        if (Utilities.getInstance().isOnline(getActivity())) {
            Utilities.getInstance().setMAP_DIRECTION("");
            if (mOrigin != null && mDestination != null) {
                url = getDirectionsUrl(mOrigin, mDestination);
                callService();

            }
        } else {
            if (TextUtils.equals(Utilities.getInstance().getValueFromSharedPreference(Constants.ORDER_NO, "", getActivity())
                    , Utilities.getInstance().getORDER_NUMBER())) {
                if (Utilities.getInstance().isSHOW_MAP_OFFLINE()) {
                    if (!TextUtils.isEmpty(Utilities.getInstance().getValueFromSharedPreference(Constants.MAP_PATH, "", getActivity()))) {
                        drawPath(Utilities.getInstance().getValueFromSharedPreference(Constants.MAP_PATH, "", getActivity()));
                    }
                }
            }
        }
        map.setOnCameraChangeListener(this);
    }

    /**
     * @description This method is used to initialize the map in map view
     */
    private void initializeMap(Bundle savedInstanceState) {
        map = Utilities.getInstance().intialiseMap(mMapView, savedInstanceState, getActivity());
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mMapView.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(false);
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(false);
        mMapView.onDestroy();
        if(mapRequest!=null){
            mapRequest.cancel();
        }
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    /**
     * @description This method is used to make the url for getting path
     */
    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Travelling Mode

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&mode=driving";

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
        Log.e("urll", url);
        return url;
    }

    /**
     * @description This method is used to get path from one location to another from google api
     */
    private void callService() {
//        Utilities.getInstance().showProgressBar(getActivity());
        mapRequest = new CustomStringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Utilities.getInstance().dismissDialog();
                Log.e("response", ":" + response);
                if (response != null) {
                    Log.e("here", ":1");
                    Utilities.getInstance().setMAP_DIRECTION(response.toString());
                    drawPath(response);
                } else {
                    Log.e("here", ":2");
                    Utilities.getInstance().setMAP_DIRECTION("");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("here", ":3");
                Utilities.getInstance().dismissDialog();
                Utilities.getInstance().setMAP_DIRECTION("");
                Log.e("error", ":" + error.getMessage());
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                if (response.headers == null) {
                    // cant just set a new empty map because the member is final.
                    response = new NetworkResponse(
                            response.statusCode,
                            response.data,
                            Collections.<String, String>emptyMap(), // this is the important line, set an empty but non-null map.
                            response.notModified,
                            response.networkTimeMs);


                }

                return super.parseNetworkResponse(response);
            }
        };

        Volley.newRequestQueue(getActivity()).add(mapRequest);

    }

    /**
     * @description This method is used to draw the path on the map
     */
    private void drawPath(String Path) {
        Type type = new TypeToken<MapModel>() {
        }.getType();
        MapModel mapModel = Utilities.getInstance().getGson().fromJson(Path.toString(), type);
        ArrayList<LatLng> points = new ArrayList<>();
        PolylineOptions lineOptions = new PolylineOptions();
        points.clear();
        for (int i = 0; i < mapModel.getRoutes().get(0).getLegs().get(0).getSteps().size(); i++) {
            String polyline = mapModel.getRoutes().get(0).getLegs().get(0).getSteps().get(i).getPolyline().getPoints();
            List<LatLng> list = decodePoly(polyline);
            for (int l = 0; l < list.size(); l++) {
                double lat = list.get(l).latitude;
                double lng = list.get(l).longitude;
                LatLng position = new LatLng(lat, lng);
                points.add(position);
            }
        }
        // Adding all the points in the route to LineOptions
        lineOptions.addAll(points);
        lineOptions.width(5);
        if (lineOptions != null) {
            lineOptions.color(ContextCompat.getColor(getActivity(), R.color.blue_text));
        }
        map.addPolyline(lineOptions);
    }

    /**
     * @description This method is used to decode the path of map
     */
    private List<LatLng> decodePoly(String encoded) {
        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }
        return poly;
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        if (!noFocus) {
            if (mOrigin != null && mDestination != null) {
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(mOrigin);
                builder.include(mDestination);
                LatLngBounds bounds = builder.build();

                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 100);
                map.animateCamera(cu);
                noFocus = true;
            }
        }
    }
}