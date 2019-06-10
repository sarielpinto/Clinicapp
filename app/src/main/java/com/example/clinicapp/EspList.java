package com.example.clinicapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import com.example.clinicapp.Mostrar_solo_los_nombre.Lista;
public class EspList extends AppCompatActivity implements ContactsAdapter.ContactsAdapterListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Contact> contactList;
    private ContactsAdapter mAdapter;
    private SearchView searchView;
    JsonArrayRequest request;
    TextView nombre;
    // url to fetch contacts json
    private static String URL="";
   private  String v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esp_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       v=getIntent().getStringExtra("valor");
        switch (v){
            case "Otorrinolaringología":
                URL="https://gist.githubusercontent.com/LuisYama/f6ae6e885eaeabd5cb472818c773d28a/raw/5c10690bb2ee1da6cc367f513ef6f6a3d334a0b0/otorrino.json";
                break;
            case "Pediatría":
                URL="https://gist.githubusercontent.com/LuisYama/3d7a965cb2d520c028175f710e00445b/raw/40b29c7d817198c7c4a28fca8b6fb909baf6aa1a/pediatria.json";
                break;
            case "Oftalmología":
                URL="https://gist.githubusercontent.com/sarielpinto/1dad838c2b2cb2438b49938acb65ff1a/raw/0bd177d80bfee1455826d0650ddd4be717095fa3/Oftalmolog%25C3%25ADa";
                break;
            case "Radiología":
                URL="https://gist.githubusercontent.com/LuisYama/1626e49263ac1aec56148735b430cb4f/raw/6b2f6675c8bdfad45b3df79c8a37c649b86d4206/radiologia.json";
                break;
            case "Cardiología":
                URL="https://gist.githubusercontent.com/LuisYama/88869828d047c3ac92a3195abd1ebaee/raw/4aa894d4e24cb0cc5426f4486d9bc01beca48301/cardiologia.json";
                break;
            case "Psiquiatría":
                URL="https://gist.githubusercontent.com/sarielpinto/e5ab9dd289ed18307288bf48ed2ad6e5/raw/524fe4dec132d013d0fc924b5fecc90dc60f439f/Psiquiatr%25C3%25ADa";
                break;
            case "Médico General":
                URL="https://gist.githubusercontent.com/sarielpinto/fde469ece9de8cea491373753019ff47/raw/9252b681c281c1484adc2fd04640c79dc803afd5/M%25C3%25A9dico%2520General";
                break;

        }

        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);
        toolbar.setBackgroundColor(Color.parseColor("#7FD3FA"));
        toolbar.setTitleTextColor(Color.BLACK);

        recyclerView = findViewById(R.id.recycler_view);
        contactList = new ArrayList<>();
        mAdapter = new ContactsAdapter(this, contactList, this);

        // white background notification bar
        whiteNotificationBar(recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);

        fetchContacts();
    }


    private void fetchContacts() {
        request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(getApplicationContext(), "Couldn't fetch the contacts! Please try again.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        List<Contact> items = new Gson().fromJson(response.toString(), new TypeToken<List<Contact>>() {
                        }.getType());

                        // adding contacts to contacts list
                        contactList.clear();
                        contactList.addAll(items);

                        // refreshing recycler view
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        MyApplication.getInstance().addToRequestQueue(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onContactSelected(Contact contact) {
        Intent intent= new Intent(EspList.this,Lista.class);
        intent.putExtra("especialista",v);
        intent.putExtra("nombre",contact.getName());
        startActivity(intent);
    }
}
