package com.ppg.utils;

import android.support.v4.app.Fragment;

import com.ppg.mvp.view.fragment.AddFragment;
import com.ppg.mvp.view.fragment.HomeFragment;
import com.ppg.mvp.view.fragment.UserFragment;

import java.util.ArrayList;

/**
 * @author kanle MainActivity全局控制跳转
 * 
 *         GotoType 判断是跳转activity还是fragment ActionKey 跳转 activity Key
 *         FragmentKey 跳转 FragmentKey Key 实例： 跳转activity： Intent i = new
 *         Intent(RegisterActivity.this, MainActivity.class);
 *         i.putExtra(KeyUtil.GotoType, KeyUtil.GOTO_ACTION);
 *         i.putExtra(KeyUtil.FragmentKey,LoginActivity.class);
 *         //直接写要跳转的activity startActivity(i); 跳转fragment： Intent i = new
 *         Intent(RegisterActivity.this, MainActivity.class);
 *         i.putExtra(KeyUtil.GotoType, KeyUtil.GOTO_FRAGMENT);
 *         i.putExtra(KeyUtil.FragmentKey,"1"); //要跳转的fragment位置
 *         startActivity(i);
 * 
 */
public class FragmentKeyUtil {

	public static final String GotoType = "GotoWhere";
	public static final String ActionKey = "ActionKey";
	public static final String FragmentKey = "FragmentKey";
	public static final int GOTO_ACTION = 1;
	public static final int GOTO_FRAGMENT = 2;
	private static ArrayList<Fragment> listfragment = new ArrayList<Fragment>();
	// 初始化存储fragment

	public static int FRAG_HOME = 0;
	public static int FRAG_ADD = 1;
	public static int FRAG_USER = 2;

	public static void initFragment() {
		Fragment homeFragment = HomeFragment.getInstance();
		Fragment addFragment = AddFragment.getInstance();
		Fragment userFragment = UserFragment.getInstance();
		
		if (listfragment.isEmpty()) {
			listfragment.add(homeFragment);
			listfragment.add(addFragment);
			listfragment.add(userFragment);
		}
	}

	public void addFragment(Fragment fragment) {
		listfragment.add(fragment);
	}

	public static Fragment getFragment(int key) {
		return listfragment.get(key);
	}

	public static int getFragmentSize() {
		return listfragment.size();
	}

	public static ArrayList<Fragment> getFragments() {
		return listfragment;
	}

}
