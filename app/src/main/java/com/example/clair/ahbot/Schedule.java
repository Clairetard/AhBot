package com.example.clair.ahbot;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class Schedule extends AppCompatActivity {

private  SectionPageAdapter sectionPageAdapter;
private ViewPager viewPager;
List<ScheduleTask> scheduleTaskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        sectionPageAdapter=new SectionPageAdapter(getSupportFragmentManager());
        viewPager=findViewById(R.id.container);
        setupViewPager(viewPager);

TabLayout tabLayout=findViewById(R.id.tabs);
tabLayout.setupWithViewPager(viewPager);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Schedule.this, AddTask.class);
                int requestCode=1;
                startActivityForResult(intent,requestCode);
            }
        });

    }

    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter sectionPageAdapter=new SectionPageAdapter(getSupportFragmentManager());
        sectionPageAdapter.addFragment(new ScheduleToday(),"Today");
        sectionPageAdapter.addFragment(new ScheduleThisWeek(),"This Week");
        sectionPageAdapter.addFragment(new ScheduleAll(),"All");
        viewPager.setAdapter(sectionPageAdapter);
    }
    //region menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case  R.id.menu_home:
                Intent intent=new Intent(Schedule.this,MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_schedule:
                return true;
            case R.id.menu_settings:
                //TODO: create settings page
                return true;
            case R.id.menu_signOut:
                FirebaseAuth.getInstance().signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //endregion

public void getTaskList(List<ScheduleTask> scheduleTasks){
        scheduleTaskList=scheduleTasks;
}
}
