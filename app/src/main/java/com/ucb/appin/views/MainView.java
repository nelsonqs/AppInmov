package com.ucb.appin.views;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ucb.appin.R;

public class MainView extends AppCompatActivity {

    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = new PublicationsView();
            switch (item.getItemId()) {
                case R.id.nav_publications:
                    fragment = new PublicationsView();
                    break;
                case R.id.nav_maps:
                    fragment = new MapView();
                    break;
                case R.id.nav_my_publications:
                    fragment = new MyPublicationsView();
                    break;
            }

            loadFragment(fragment);

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////////////////////////////////////////////////////////////////////////////////////////////
        //final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //init test only
        PublicationsView publicationsView = new PublicationsView();
        this.loadFragment(publicationsView);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.setBackground(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        //navigation.setItemBackgroundResource(R.drawable.transparent);

        //test only
        BottomNavigationMenuView menuView = (BottomNavigationMenuView)navigation.getChildAt(0);

        for (int i = 0; i < menuView.getChildCount(); i++) {
            BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
            //noinspection RestrictedApi
            item.setShiftingMode(false);
            // set once again checked value, so view will be updated
            //noinspection RestrictedApi
            item.setChecked(item.getItemData().isChecked());
            System.out.println("++++>>>" + item.getItemData().isChecked());

            if(i == 1){
                View badge1 = LayoutInflater.from(this).inflate(R.layout.badge_main, item, false);
                TextView tv1 = badge1.findViewById(R.id.notifications_badge);
                tv1.setText("44");

                item.addView(badge1);
            }
            else{
                View badge = LayoutInflater.from(this).inflate(R.layout.badge_main, item, false);
                TextView tv = badge.findViewById(R.id.notifications_badge);
                tv.setText("22");

                item.addView(badge);
            }

        }
    }

    public void loadFragment(Fragment fragment){

        try {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_fragment, fragment);
            fragmentTransaction.commit();
        }catch (Exception ex){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //ESTE CODIGO ES PARA ACTIVAR LA BUSQUEDA DE LA BARRA DE TAREA
        /*SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // this is your adapter that will be filtered
                buscarEnTab(newText);
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                //Here u can get the value "query" which is entered in the search box.
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        this.menu = menu;
        mostrarOcultarMenu(1);//para visualizar al inicio*/

        return true;
    }

    public void click(View view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView)navigation.getChildAt(0);

        for (int i = 0; i < menuView.getChildCount(); i++) {
            BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
            //noinspection RestrictedApi
            item.setShiftingMode(false);
            // set once again checked value, so view will be updated
            //noinspection RestrictedApi
            item.setChecked(item.getItemData().isChecked());
            System.out.println("++++>>>" + item.getItemData().isChecked());

            item.removeViewAt(2);

        }
    }
}
