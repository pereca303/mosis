package lab.mosis.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lab.mosis.MenuTask;
import lab.mosis.R;
import lab.mosis.data.DataStorage;
import lab.mosis.data.MyPlace;

public class MapActivity extends AppCompatActivity {

    private static final int PERMISSION_ACCESS_FINE_LOCATION = 1;

    private MapView map_view;
    private IMapController map_controller;
    private MyLocationNewOverlay location_overlay;
    private Button map_button;

    private Map<Integer, MenuTask> menu_tasks;

    private Map<MapContext, InitializeTask> initialize_task_map;

    private double selected_longitude;
    private double selected_latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        this.populateInitTaskMap();

        Intent intent = this.getIntent();
        MapContext map_context = MapContext.valueOf(intent.getStringExtra("map_context"));

        Toast.makeText(getApplicationContext(),
                       String.valueOf(intent.getStringExtra("map_context")),
                       Toast.LENGTH_SHORT)
                .show();

        this.initViews();
        this.initMenuTasks();

        Context context = getApplicationContext();
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context));

        this.initMap();

        this.selected_latitude = -1;
        this.selected_longitude = -1;

        InitializeTask init_task = this.initialize_task_map.get(map_context);
        init_task.execute(intent);

    }

    private void initViews() {

        this.setSupportActionBar((Toolbar) this.findViewById(R.id.map_toolbar));
        ((Toolbar) this.findViewById(R.id.map_toolbar)).inflateMenu(R.menu.map_toolbar_items);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.map_button = (Button) this.findViewById(R.id.map_button);
        this.map_view = (MapView) this.findViewById(R.id.map_view);


    }

    private void populateInitTaskMap() {

        this.initialize_task_map = new HashMap<MapContext, InitializeTask>();

        this.initialize_task_map.put(MapContext.EditMyPlace, new InitializeTask() {
            @Override
            public void execute(final Intent intent) {

                // edit button icon ...
                map_button.setBackgroundResource(R.drawable.ic_action_view_as_list);

                double passed_long = intent.getDoubleExtra("longitude", -1);
                double passed_lat = intent.getDoubleExtra("latitude", -1);

                Toast.makeText(getApplicationContext(),
                               "logn: " + passed_long + " lat: " + passed_lat,
                               Toast.LENGTH_SHORT).show();

                map_controller.setZoom(15.0);
                GeoPoint point = new GeoPoint(passed_lat, passed_long);
                map_controller.setCenter(point);

                MapEventsReceiver mapEventRecv = new MapEventsReceiver() {
                    @Override
                    public boolean singleTapConfirmedHelper(GeoPoint p) {

                        selected_latitude = p.getLatitude();
                        selected_longitude = p.getLatitude();

                        map_controller.setCenter(p);

                        return false;
                    }

                    @Override
                    public boolean longPressHelper(GeoPoint p) {
                        return false;
                    }
                };

                MapEventsOverlay event_overlay = new MapEventsOverlay(mapEventRecv);
                map_view.getOverlays().add(event_overlay);

                map_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent info_intent = new Intent();
                        if (selected_latitude != -1 && selected_longitude != -1) {

                            info_intent.putExtra("longitude", selected_longitude);
                            info_intent.putExtra("latitude", selected_latitude);

                            setResult(Activity.RESULT_OK, info_intent);
                            finish();

                        } else {
                            Toast.makeText(MapActivity.this, "Select location first ... ", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

            }
        });

        this.initialize_task_map.put(MapContext.AddMyPlace, new InitializeTask() {
            @Override
            public void execute(Intent intent) {

                map_button.setBackgroundResource(R.drawable.ic_action_new);

                MapEventsReceiver mapEventRecv = new MapEventsReceiver() {
                    @Override
                    public boolean singleTapConfirmedHelper(GeoPoint p) {

                        Toast.makeText(getApplicationContext(), "Map click ", Toast.LENGTH_SHORT).show();

                        selected_latitude = p.getLatitude();
                        selected_longitude = p.getLatitude();

                        map_controller.setCenter(p);

                        return false;
                    }

                    @Override
                    public boolean longPressHelper(GeoPoint p) {
                        return false;
                    }
                };

                MapEventsOverlay event_overlay = new MapEventsOverlay(mapEventRecv);
                map_view.getOverlays().add(event_overlay);

                map_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent info_intent = new Intent();
                        if (selected_latitude != -1 && selected_longitude != -1) {

                            info_intent.putExtra("longitude", selected_longitude);
                            info_intent.putExtra("latitude", selected_latitude);

                            setResult(Activity.RESULT_OK, info_intent);
                            finish();

                        } else {
                            Toast.makeText(MapActivity.this, "Select location first ... ", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        this.initialize_task_map.put(MapContext.ShowMyPlace, new InitializeTask() {
            @Override
            public void execute(Intent intent) {

                double longitude = intent.getDoubleExtra("longitude", -1);
                double latitude = intent.getDoubleExtra("latitude", -1);

                map_controller.setZoom(15.0);
                GeoPoint point = new GeoPoint(latitude, longitude);
                map_controller.setCenter(point);

            }
        });

        this.initialize_task_map.put(MapContext.ShowAllPlaces, new InitializeTask() {
            @Override
            public void execute(final Intent intent) {

                List<OverlayItem> items = new ArrayList<OverlayItem>();

                for (MyPlace place : DataStorage.getInstance().getData()) {

                    OverlayItem item = new OverlayItem(place.getName(),
                                                       place.getDescription(),
                                                       new GeoPoint(place.getLatitude(), place.getLongitude()));


                    item.setMarker(getResources().getDrawable(R.drawable.myplace32));
                    ((ArrayList) items).add(item);

                }

                ItemizedIconOverlay<OverlayItem> itemized_overlay = new ItemizedIconOverlay<OverlayItem>(items,
                                                                                                         new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                                                                                                             @Override
                                                                                                             public boolean onItemSingleTapUp(
                                                                                                                     int index,
                                                                                                                     OverlayItem item) {

                                                                                                                 Intent intent = new Intent(
                                                                                                                         MapActivity.this,
                                                                                                                         PlaceInfoActivity.class);

                                                                                                                 intent.putExtra(
                                                                                                                         "place_index",
                                                                                                                         index);

                                                                                                                 startActivity(
                                                                                                                         intent);

                                                                                                                 return true;
                                                                                                             }

                                                                                                             @Override
                                                                                                             public boolean onItemLongPress(
                                                                                                                     int index,
                                                                                                                     OverlayItem item) {
                                                                                                                 Intent intent = new Intent(
                                                                                                                         MapActivity.this,
                                                                                                                         EditActivity.class);

                                                                                                                 intent.putExtra(
                                                                                                                         "place_index",
                                                                                                                         index);

                                                                                                                 startActivity(
                                                                                                                         intent);


                                                                                                                 return true;
                                                                                                             }
                                                                                                         },
                                                                                                         getApplicationContext());

                map_view.getOverlays().add(itemized_overlay);

            }
        });

        this.initialize_task_map.put(MapContext.ShowCurrentLocation, new InitializeTask() {
            @Override
            public void execute(Intent intent) {

                Toast.makeText(getApplicationContext(), "Set overlay ", Toast.LENGTH_SHORT).show();

                location_overlay = new MyLocationNewOverlay(map_view);
                location_overlay.enableFollowLocation();
                location_overlay.enableMyLocation();

                Drawable icon_draw = ResourcesCompat.getDrawable(getResources(), R.drawable.myplace32, null);

                if (icon_draw != null) {

                    location_overlay.setPersonIcon(((BitmapDrawable) icon_draw).getBitmap());

                }

                map_view.getOverlays().add(location_overlay);

                if (map_controller != null) {
                    map_controller.setZoom(15.0);
                }

            }
        });

    }

    private void initMenuTasks() {

        this.menu_tasks = new HashMap<Integer, MenuTask>();

        this.menu_tasks.put(R.id.map_first_item, new MenuTask() {
            @Override
            public boolean execute(MenuItem selected_item) {


                // true means that event is handled
                return true;

            }
        });

    }

    private void initMap() {

        this.map_view.setMultiTouchControls(true);
        this.map_controller = this.map_view.getController();

        // ask for permission
        if (ActivityCompat.checkSelfPermission(this,
                                               Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat
                .checkSelfPermission(this,
                                     Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                                              new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                              MapActivity.PERMISSION_ACCESS_FINE_LOCATION);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case MapActivity.PERMISSION_ACCESS_FINE_LOCATION: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getApplicationContext(), "POSITIVE RESPONSE", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getApplicationContext(), "NO PERMISSION IN RESPONSE", Toast.LENGTH_SHORT).show();

                }

                return;
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        this.map_view.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.map_view.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        this.getMenuInflater().inflate(R.menu.map_toolbar_items, menu);

        return true;

    }
}