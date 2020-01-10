package com.beraj.instagram.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.beraj.instagram.PostApi;
import com.beraj.instagram.StoryApi;
import com.beraj.instagram.Url;
import com.beraj.instagram.fragments.PostFragment;
import com.beraj.instagram.fragments.SearchFragment;
import com.beraj.instagram.models.PostModel;
import com.beraj.instagram.R;
import com.beraj.instagram.adapters.StoryAdapter;
import com.beraj.instagram.models.StoryModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView btmNav;
//    Fragment selectedFragment = null;

    public static List<PostModel> listPosts = new ArrayList<>();
    public static List<StoryModel> listStories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btmNav = findViewById(R.id.nav_view);

        getSupportActionBar().hide();

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_add, R.id.navigation_liked, R.id.navigation_profile
        ).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(btmNav, navController);

        loadStories();
//        loadPosts();
               listPosts.add(new PostModel("1","Samridhi Rai", "Nattu with new album", R.drawable.post5, R.drawable.post6));
        listPosts.add(new PostModel("2","Matrika elm rai", "Bipul dai", R.drawable.post7, R.drawable.post4));
        listPosts.add(new PostModel("3","Trisala gurung", "Dropped my new song please check", R.drawable.post9, R.drawable.post10));
        listPosts.add(new PostModel("4","Shrinkhala Khatiwoda", "Candid", R.drawable.profilepic2, R.drawable.profilepic2));
        listPosts.add(new PostModel("5","Naruto Uzumaki", "about to deal with Akatsuki", R.drawable.post12, R.drawable.post11));
        listPosts.add(new PostModel("4","Naruto Uzumaki", "My son and me", R.drawable.post14, R.drawable.post11));
        listPosts.add(new PostModel("6","Iron Man", "My lego figure looks sick", R.drawable.post16, R.drawable.post15));
        listPosts.add(new PostModel("7","Superman", "from 90s to 2020", R.drawable.post18, R.drawable.post17));
        listPosts.add(new PostModel("8","Rajesh Hamal", "Tasbir afai bolcha", R.drawable.post20, R.drawable.post19));
        listPosts.add(new PostModel("9","Dare Devil", "Mask off freaking Mask off", R.drawable.post22, R.drawable.post21));
        listPosts.add(new PostModel("10","Deadpool", "Ohh my Gooosshhh", R.drawable.post24, R.drawable.post23));


        listStories.add(new StoryModel("1","Batman ", R.drawable.post1, 0));
        listStories.add(new StoryModel("Sajjan Raj Vaidhya", R.drawable.post3));
        listStories.add(new StoryModel("1","Bipul chhetri", R.drawable.post2, 0));
        listStories.add(new StoryModel("Rohit John Chhetri", R.drawable.post7));
//        listStories.add(new StoryModel("2","Add", R.drawable.add, 0));
//        listStories.add(new StoryModel("3","beraj Ranjit", R.drawable.home, 0));
//        setUpStories();
    }

//    For changing active icon on bottom navigation bar
//    private BottomNavigationView.OnNavigationItemReselectedListener
//                selectedItem = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//            Menu menu = btmNav.getMenu();
//            menu.findItem(R.id.navigation_home).setIcon(R.drawable.on_home);
//            menu.findItem(R.id.navigation_search).setIcon(R.drawable.on_search);
//            menu.findItem(R.id.navigation_add).setIcon(R.drawable.on_add);
//            menu.findItem(R.id.navigation_liked).setIcon(R.drawable.on_like);
//            menu.findItem(R.id.navigation_profile).setIcon(R.drawable.on_profile);
//
//            switch (menuItem.getItemId()) {
//                case R.id.navigation_home:
//                    selectedFragment = new PostFragment();
//                    menuItem.setIcon(R.drawable.on_home);
//                    break;
//                case R.id.navigation_search:
//                    selectedFragment = new SearchFragment();
//                    menuItem.setIcon(R.drawable.on_search);
//                    break;
//                case  R.id.navigation_add:
//                    menuItem.setIcon(R.drawable.on_add);
//                    break;
//                case R.id.navigation_liked:
//                    menuItem.setIcon(R.drawable.on_like);
//                    break;
//                case R.id.navigation_profile:
//                    menuItem.setIcon(R.drawable.on_profile);
//                    break;
//
//            }
//            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
//                    selectedFragment).commit();
//
//            if (selectedFragment != null){
//                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit();
//            }
//
//            return true;
//        }
//    };



    public void loadStories(){

        StoryApi storyApi = Url.getInstance().create(StoryApi.class);
        Call<List<StoryModel>> storyCall = storyApi.getStories();

        storyCall.enqueue(new Callback<List<StoryModel>>() {
            @Override
            public void onResponse(Call<List<StoryModel>> call, Response<List<StoryModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Code: " +response.body(), Toast.LENGTH_SHORT).show();
                    return;
                }

//                String imgPath = Url.imagePath +  response.body();
//                Picasso.get().load(imgPath).into(imgProgileImg);

                List<StoryModel> list = response.body();
                for(StoryModel story: list){
                    listStories.add(new StoryModel(story.getName(), story.getDailyPhoto()));
                    List stories = null;
                    stories.add(story.getName());
                }
            }

            @Override
            public void onFailure(Call<List<StoryModel>> call, Throwable t) {

            }
        });


    }

    public void loadPosts(){

        PostApi postApi = Url.getInstance().create(PostApi.class);
        Call<List<PostModel>> postCalls = postApi.getPosts();

        postCalls.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Code: " + response.body(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<PostModel> list = response.body();
                assert list != null;
                listPosts.addAll(list);
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {

            }
        });

    }



}
