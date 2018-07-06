/*
 *    Copyright 2018 XuJiaji
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.xujiaji.learnmvvm.module.main;

import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.xujiaji.learnmvvm.R;
import com.xujiaji.learnmvvm.databinding.ActivityMainBinding;
import com.xujiaji.learnmvvm.module.projectdetail.ProjectFragment;
import com.xujiaji.learnmvvm.module.projectlist.ProjectListFragment;
import com.xujiaji.learnmvvm.service.model.Project;
import com.xujiaji.mvvmquick.base.MQActivity;
import com.xujiaji.mvvmquick.util.ActivityUtils;
import com.xujiaji.mvvmquick.util.FragmentUtils;
import com.xujiaji.mvvmquick.util.ToastUtil;
import com.xujiaji.mvvmquick.util.Utils;

import javax.inject.Inject;

import dagger.Lazy;

import static com.xujiaji.learnmvvm.module.projectdetail.ProjectFragment.KEY_PROJECT_ID;


public class MainActivity extends MQActivity<ActivityMainBinding, AndroidViewModel>
{

    @Inject
    Lazy<ProjectListFragment> mProjectListFragment;
    @Inject
    Lazy<ProjectFragment> mProjectFragment;

    private ActionBar actionBar;
    private Menu menu_navigation;
    private View navigation_header;
    private boolean is_account_mode = false;

    public static void launch(Context context)
    {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    public void onBinding(ActivityMainBinding binding)
    {
        super.onBinding(binding);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initToolbar();
        initNavigationMenu();
        if (savedInstanceState == null)
        {
            ActivityUtils.addFragmentInActivity(
                    getSupportFragmentManager(),
                    mProjectListFragment.get(),
                    R.id.fragment_container,
                    ProjectListFragment.class.getSimpleName());

        }
    }

    private void initToolbar() {
//        toolbar.setBackgroundColor(getResources().getColor(R.color.pink_600));
        setSupportActionBar(binding.toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Github Projects");
    }

    private void initNavigationMenu() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                updateCounter(binding.navView);
                super.onDrawerOpened(drawerView);
            }
        };
        binding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                onItemNavigationClicked(item);
                return true;
            }
        });

        // open drawer at start
        binding.drawerLayout.openDrawer(GravityCompat.START);
        updateCounter(binding.navView);
        menu_navigation = binding.navView.getMenu();

        // navigation header
        navigation_header = binding.navView.getHeaderView(0);
        (navigation_header.findViewById(R.id.bt_account)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean is_hide = Utils.toggleArrow(view);
                is_account_mode = is_hide;
                menu_navigation.clear();
                if (is_hide) {
                    menu_navigation.add(1, 1000, 100, "evans.collins@mail.com").setIcon(R.drawable.ic_account_circle);
                    menu_navigation.add(1, 2000, 100, "adams.green@mail.com").setIcon(R.drawable.ic_account_circle);
                    menu_navigation.add(1, 1, 100, "Add account").setIcon(R.drawable.ic_add);
                    menu_navigation.add(1, 2, 100, "Manage accounts").setIcon(R.drawable.ic_settings);
                } else {
                    binding.navView.inflateMenu(R.menu.menu_navigation_drawer);
                    updateCounter(binding.navView);
                }
            }
        });
    }

    private void onItemNavigationClicked(MenuItem item) {
        if (!is_account_mode) {
            Toast.makeText(getApplicationContext(), item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
            actionBar.setTitle(item.getTitle());
            binding.drawerLayout.closeDrawers();
        } else {
            TextView name = (TextView) navigation_header.findViewById(R.id.name);
            TextView email = (TextView) navigation_header.findViewById(R.id.email);
            CircularImageView avatar = (CircularImageView) navigation_header.findViewById(R.id.avatar);
            switch (item.getItemId()) {
                case 1000:
                    name.setText("Evans Collins");
                    email.setText(item.getTitle().toString());
                    avatar.setImageResource(R.drawable.photo_male_5);
                    break;
                case 2000:
                    name.setText("Adams Green");
                    email.setText(item.getTitle().toString());
                    avatar.setImageResource(R.drawable.photo_male_2);
                    break;
                default:
                    Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    private void updateCounter(NavigationView nav) {
        if (is_account_mode) return;
        Menu m = nav.getMenu();
        ((TextView) m.findItem(R.id.nav_all_inbox).getActionView().findViewById(R.id.text)).setText("75");
        ((TextView) m.findItem(R.id.nav_inbox).getActionView().findViewById(R.id.text)).setText("68");

        TextView badgePrioInbx = (TextView) m.findItem(R.id.nav_priority_inbox).getActionView().findViewById(R.id.text);
        badgePrioInbx.setText("3 new");
        badgePrioInbx.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        TextView badgeSocial = (TextView) m.findItem(R.id.nav_social).getActionView().findViewById(R.id.text);
        badgeSocial.setText("51 new");
        badgeSocial.setBackgroundColor(getResources().getColor(R.color.green_500));

        ((TextView) m.findItem(R.id.nav_spam).getActionView().findViewById(R.id.text)).setText("13");
    }

    public void show(Project project)
    {
        ActivityUtils.replaceFragmentInActivity(
                getSupportFragmentManager(),
                FragmentUtils.setArgs(mProjectFragment.get(), new String[]{KEY_PROJECT_ID}, project.name),
                R.id.fragment_container,
                null,
                "project");
    }

    @Override
    public void onBackPressed()
    {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0 || ActivityUtils.exitBy2Click())
        {
            super.onBackPressed();
        } else
        {
            ToastUtil.getInstance().showShortY("再点一次退出");
        }
    }
}
