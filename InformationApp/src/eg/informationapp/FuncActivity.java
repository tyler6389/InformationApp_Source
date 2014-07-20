package eg.informationapp;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 
/**
 * 액션바에 탭을 설정하는 방법을 알 수 있습니다.
 * 
 * @author Mike
 */
public class FuncActivity extends ActionBarActivity {
 
    EditText edit01;
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        // 메인 레이아웃을 설정하는 setContentView() 메소드를 사용하지 않습니다.
         
 
        // 액션바를 직접 참조하여 모드를 변경합니다.
        ActionBar abar = getActionBar();
        abar.setNavigationMode( ActionBar.NAVIGATION_MODE_TABS );
 
        Tab tab01 = abar.newTab();
        tab01.setText("Traffic");
        tab01.setTabListener(new ProductTabListener(this, Fragment01.class.getName()));
        abar.addTab(tab01);
 
        Tab tab02 = abar.newTab();
        tab02.setText("Weather");
        tab02.setTabListener(new ProductTabListener(this, Fragment02.class.getName()));
        abar.addTab(tab02);
 
         
    }
 
    /**
     * 탭을 선택했을 때 처리할 리스너 정의
     */
    private class ProductTabListener implements ActionBar.TabListener {
        private Fragment mFragment;
        private final Activity mActivity;
        private final String mFragName;
 
        public ProductTabListener(Activity activity, String fragName) {
            mActivity = activity;
            mFragName = fragName;
        }
 
        @Override
        public void onTabReselected(Tab tab, FragmentTransaction arg1) {
             
        }
 
        /**
         * 탭이 선택되었을 때
         */
        @Override
        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            mFragment = Fragment.instantiate(mActivity, mFragName);
            ft.add(android.R.id.content, mFragment);
        }
 
        /**
         * 탭 선택이 해제되었을 때
         */
        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            ft.remove(mFragment);
            mFragment = null;
        }
    }
}

 