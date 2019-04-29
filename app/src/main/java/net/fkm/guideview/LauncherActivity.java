package net.fkm.guideview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

/*
 *  描述： 启动页
 */
public class LauncherActivity extends AppCompatActivity {

    /**
     * 1.延时2000ms
     * 2.判断程序是否第一次运行
     * 3.Activity全屏主题
     */

    //闪屏业延时
    private static final int HANDLER_SPLASH = 1001;
    //判断程序是否是第一次运行
    private static final String SHARE_IS_FIRST = "isFirst";


    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_SPLASH:
                    //判断程序是否是第一次运行
                    if (isFirst()) {
                        startActivity(new Intent(LauncherActivity.this, GuideActivity.class));
                    } else {
                        startActivity(new Intent(LauncherActivity.this, HomeActivity.class));
                    }
                    finish();
                    break;
                default:
                    break;
            }
            return false;
        }

    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        initView();

    }

    //初始化View
    private void initView() {
        //延时2000ms
        handler.sendEmptyMessageDelayed(HANDLER_SPLASH, 2000);
    }

    //判断程序是否第一次运行
    private boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this, SHARE_IS_FIRST, true);
        if (isFirst) {
            ShareUtils.putBoolean(this, SHARE_IS_FIRST, false);
            //是第一次运行
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
