package com.example.news;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toobar);
        drawerLayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.nav_design);
        View view=navigationView.getHeaderView(0);
        CircleImageView circleImageView= view.findViewById(R.id.header_image);
        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);
        list=new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        toolbar.setTitle("简易新闻");
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();

        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon);
        }
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                switch(item.getItemId()){
                    case R.id.nav_call:
                        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_friends:
                        Toast.makeText(MainActivity.this,"点击了朋友",Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.nav_location:
                        Toast.makeText(MainActivity.this,"点击了文件",Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.nav_favorite:
                        Toast.makeText(MainActivity.this,"点击了收藏",Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.nav_settings:
                        Toast.makeText(MainActivity.this,"点击了设置",Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.nav_exit:


                        break;
                        default:
                            break;

                }
                return true;
            }
        });
        list.add("头条");
        list.add("社会");
        list.add("国内");
        list.add("国际");
        list.add("娱乐");
        list.add("体育");
        list.add("军事");
        list.add("科技");
        list.add("财经");
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position);
            }

            @Override
            public Fragment getItem(int position) {
                NewsFragment newsFragment=new NewsFragment();
                Bundle bundle=new Bundle();
                if(list.get(position).equals("头条")){
                    bundle.putString("name","top");
                }else if(list.get(position).equals("社会")){
                    bundle.putString("name","top");
                }else if(list.get(position).equals("国内")){
                    bundle.putString("name","top");
                }else if(list.get(position).equals("国际")){
                    bundle.putString("name","top");
                }else if(list.get(position).equals("娱乐")){
                    bundle.putString("name","top");
                }else if(list.get(position).equals("体育")){
                    bundle.putString("name","top");
                }else if(list.get(position).equals("军事")){
                    bundle.putString("name","top");
                }else if(list.get(position).equals("科技")){
                    bundle.putString("name","top");
                }else if(list.get(position).equals("财经")){
                    bundle.putString("name","top");
                }
                newsFragment.setArguments(bundle);
                return newsFragment;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                NewsFragment newsFragment= (NewsFragment) super.instantiateItem(container,position);
                return newsFragment;
            }

            @Override
            public int getItemPosition(Object object) {
                return FragmentStatePagerAdapter.POSITION_NONE;
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.userFeedback:
                EditText editText=new EditText(MainActivity.this);

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("用户反馈");
                builder.setView(editText);
                builder.setCancelable(false);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
                break;
            case R.id.userExit:
                Toast.makeText(this,"ni click 退出",Toast.LENGTH_SHORT).show();
                break;

                default:
                    break;
        }
        return true;
    }
}
