package com.example.notifications;


import java.io.File;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.BigPictureStyle;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.notifications.base.BaseActivity;

/*
 * 通知栏应用
 */
public class MainActivity extends BaseActivity implements OnClickListener {
    private Button btn_show;
    private Button btn_bigstyle_show;
    private Button btn_show_progress;
    private Button btn_show_cz;
    private Button btn_clear;
    private Button btn_clear_all;
    private Button btn_show_custom;
    /** 点击跳转到指定的界面 */
    private Button btn_show_intent_act;
    /** 点击打开指定的界apk */
    private Button btn_show_intent_apk;
    /** Notification构造器 */
    NotificationCompat.Builder mBuilder;
    /** Notification的ID */
    int notifyId = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        initNotify();
    }

    private void initView() {
        btn_show = (Button) findViewById(R.id.btn_show);
        btn_bigstyle_show = (Button) findViewById(R.id.btn_bigstyle_show);
        btn_show_progress = (Button) findViewById(R.id.btn_show_progress);
        btn_show_cz = (Button) findViewById(R.id.btn_show_cz);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_clear_all = (Button) findViewById(R.id.btn_clear_all);
        btn_show_custom = (Button) findViewById(R.id.btn_show_custom);
        btn_show_intent_act = (Button) findViewById(R.id.btn_show_intent_act);
        btn_show_intent_apk = (Button) findViewById(R.id.btn_show_intent_apk);
        btn_show.setOnClickListener(this);
        btn_bigstyle_show.setOnClickListener(this);
        btn_show_progress.setOnClickListener(this);
        btn_show_cz.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_clear_all.setOnClickListener(this);
        btn_show_custom.setOnClickListener(this);
        btn_show_intent_act.setOnClickListener(this);
        btn_show_intent_apk.setOnClickListener(this);
        // big style
        findViewById(R.id.btn_bigpicture_style_show).setOnClickListener(this);
        findViewById(R.id.btn_bigtext_style_show).setOnClickListener(this);
        findViewById(R.id.btn_biginbox_style_show).setOnClickListener(this);
    }

    /** 初始化通知栏 */
    private void initNotify() {
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("测试标题").setContentText("测试内容").setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL)).setNumber(5)// 显示数量
                .setTicker("测试通知来啦")// 通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
                .setPriority(Notification.PRIORITY_DEFAULT)// 设置该通知优先级
                // .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消 api16
                .setOngoing(false)// ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)// 向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
                // Notification.DEFAULT_ALL Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE
                // permission
                .setSmallIcon(R.drawable.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
    }

    /**
     * 1.显示通知栏
     */
    public void showNotify() {
        mBuilder.setContentTitle("测试标题").setContentText("测试内容")
        // .setNumber(number)//显示数量
                .setTicker("测试通知来啦");// 通知首次出现在通知栏，带上升动画效果的
        mNotificationManager.notify(notifyId, mBuilder.build());
        // mNotification.notify(getResources().getString(R.string.app_name), notiId,
        // mBuilder.build());
    }

    /**
     * 2.显示大视图风格通知栏 详情区域根据用途可有多种风格： 
     *  1.NotificationCompat.BigPictureStyle 大图片风格：详情区域包含一个256dp高度的位图
     *  2.NotificationCompat.BigTextStyle 大文字风格：显示一个大的文字块 
     *  3.NotificationCompat.InboxStyle
     *  收件箱风格：显示多行文字各种风格都具有以下常规视图不具有的内容选项： 
     *      1.大标题：在展开视图时替代普通视图的标记 
     *      2.总结文字：允许你在详情区域之下增加一行内容
     */
    public void showBigStyleNotify(int type) {
        /**
         * InboxStyle
         */
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] events = new String[] {"第一行文字", "第二行文字", "第三行文字", "第四行文字", "第五行文字"};
        // Sets a title for the Inbox style big view
        inboxStyle.setBigContentTitle("大视图内容");
        // Moves events into the big view
        for (int i = 0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }
        // 在细节区域底端添加一行文本
        inboxStyle.setSummaryText("在细节区域底端添加一行文本");
        /**
         * BigPictureStyle
         */
        BigPictureStyle bigPictureStyle = new BigPictureStyle();
        bigPictureStyle
        .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.big_pic));
        /**
         * BigTextStyle
         */
        BigTextStyle bigTextStyle = new BigTextStyle();
        bigTextStyle.bigText("Helper class for generating large-format notifications that include a lot of text. This class is a \"rebuilder\": It attaches to a Builder object and modifies its behavior, like so.");
        
        
        NotificationCompat.Builder builder = new Builder(this);
        builder
                .setContentTitle("测试标题")
                .setContentText("测试内容")
                //.setContentInfo("num")//同setNumber
                //.setNumber(22)
//                .setStyle(bigTextStyle)// 设置风格
                .setTicker("测试通知来啦")
                .setWhen(System.currentTimeMillis())
                .addAction(0, "打开", PendingIntent.getActivity(this, 0x111, new Intent(this, CustomActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
                .addAction(0, "分享", PendingIntent.getActivity(this, 0x222, new Intent(this, CustomActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
                .addAction(0, "删除", PendingIntent.getActivity(this, 0x333, new Intent(this, CustomActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
                .setSmallIcon(R.drawable.ic_launcher);
        if (type == 1) {
            builder.setStyle(bigPictureStyle);
        } else if (type == 2) {
            builder.setStyle(bigTextStyle);
        } else if (type == 3) {
            builder.setStyle(inboxStyle);
        }
        mNotificationManager.notify(notifyId, builder.build());
    }
    
    private void showScreenShot() {
        Bitmap preview = BitmapFactory.decodeResource(getResources(), R.drawable.big_pic);
        int mNotificationIconSize =
                getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_height);
        Bitmap croppedIcon = Bitmap.createScaledBitmap(preview, mNotificationIconSize, mNotificationIconSize, true);
        NotificationCompat.Builder builder = new Builder(this);
        builder
        .setContentTitle("测试标题")
        .setContentText("测试内容")
        //.setContentInfo("num")//同setNumber
        //.setNumber(22)
        .setTicker("测试通知来啦")
        .setWhen(System.currentTimeMillis())
        .addAction(0, "打开", PendingIntent.getActivity(this, 0x111, new Intent(this, CustomActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
        .addAction(0, "分享", PendingIntent.getActivity(this, 0x222, new Intent(this, CustomActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
        .addAction(0, "删除", PendingIntent.getActivity(this, 0x333, new Intent(this, CustomActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
        .setSmallIcon(R.drawable.ic_launcher)
        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.btn_next));
        BigPictureStyle bigPictureStyle = new BigPictureStyle();
        bigPictureStyle.bigPicture(preview);
        builder.setStyle(bigPictureStyle);
        
        mNotificationManager.notify(notifyId, builder.build());
//        builder.setLargeIcon(croppedIcon);
//        bigPictureStyle.bigLargeIcon(null);
    }

    /**
     * 3.显示常驻通知栏
     */
    public void showCzNotify() {
        // Notification mNotification = new Notification();//为了兼容问题，不用该方法，所以都采用BUILD方式建立
        // Notification mNotification = new Notification.Builder(this).getNotification();//这种方式已经过时
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        // //PendingIntent 跳转动作
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, getIntent(), 0);
        mBuilder.setSmallIcon(R.drawable.ic_launcher).setTicker("常驻通知来了").setContentTitle("常驻测试").setContentText("使用cancel()方法才可以把我去掉哦").setContentIntent(pendingIntent);
        Notification mNotification = mBuilder.build();
        // 设置通知 消息 图标
        mNotification.icon = R.drawable.ic_launcher;
        // 在通知栏上点击此通知后自动清除此通知
        mNotification.flags = Notification.FLAG_ONGOING_EVENT;// FLAG_ONGOING_EVENT
                                                              // 在顶部常驻，可以调用下面的清除方法去除
                                                              // FLAG_AUTO_CANCEL 点击和清理可以去调
        // 设置显示通知时的默认的发声、震动、Light效果
        mNotification.defaults = Notification.DEFAULT_VIBRATE;
        // 设置发出消息的内容
        mNotification.tickerText = "通知来了";
        // 设置发出通知的时间
        mNotification.when = System.currentTimeMillis();
        // mNotification.flags = Notification.FLAG_AUTO_CANCEL; //在通知栏上点击此通知后自动清除此通知
        // mNotification.setLatestEventInfo(this, "常驻测试", "使用cancel()方法才可以把我去掉哦", null); //设置详细的信息
        // ,这个方法现在已经不用了
        mNotificationManager.notify(notifyId, mNotification);
    }

    /**
     * 4.显示通知栏点击跳转到指定Activity
     */
    public void showIntentActivityNotify() {
        // Notification.FLAG_ONGOING_EVENT --设置常驻 Flag;Notification.FLAG_AUTO_CANCEL
        // 通知栏上点击此通知后自动清除此通知
        // notification.flags = Notification.FLAG_AUTO_CANCEL; //在通知栏上点击此通知后自动清除此通知
        mBuilder.setAutoCancel(true)// 点击后让通知将消失
                .setContentTitle("测试标题").setContentText("点击跳转").setTicker("点我");
        // 点击的意图ACTION是跳转到Intent
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify(notifyId, mBuilder.build());
    }

    /**
     * 5.显示通知栏点击打开Apk
     */
    public void showIntentApkNotify() {
        // Notification.FLAG_ONGOING_EVENT --设置常驻 Flag;Notification.FLAG_AUTO_CANCEL
        // 通知栏上点击此通知后自动清除此通知
        // notification.flags = Notification.FLAG_AUTO_CANCEL; //在通知栏上点击此通知后自动清除此通知
        mBuilder.setAutoCancel(true)// 点击后让通知将消失
                .setContentTitle("下载完成").setContentText("点击安装").setTicker("下载完成！");
        // 我们这里需要做的是打开一个安装包
        Intent apkIntent = new Intent();
        apkIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        apkIntent.setAction(android.content.Intent.ACTION_VIEW);
        // 注意：这里的这个APK是放在assets文件夹下，获取路径不能直接读取的，要通过COYP出去在读或者直接读取自己本地的PATH，这边只是做一个跳转APK，实际打不开的
        String apk_path = "file:///android_asset/cs.apk";
        // Uri uri = Uri.parse(apk_path);
        Uri uri = Uri.fromFile(new File(apk_path));
        apkIntent.setDataAndType(uri, "application/vnd.android.package-archive");
        // context.startActivity(intent);
        PendingIntent contextIntent = PendingIntent.getActivity(this, 0, apkIntent, 0);
        mBuilder.setContentIntent(contextIntent);
        mNotificationManager.notify(notifyId, mBuilder.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                showNotify();
                break;
            case R.id.btn_bigstyle_show:
//                showBigStyleNotify();
                showScreenShot();
                break;
            case R.id.btn_show_cz:
                showCzNotify();
                break;
            case R.id.btn_clear:
                clearNotify(notifyId);
                break;
            case R.id.btn_clear_all:
                clearAllNotify();
                break;
            case R.id.btn_show_intent_act:
                showIntentActivityNotify();
                break;
            case R.id.btn_show_intent_apk:
                showIntentApkNotify();
                break;
            case R.id.btn_show_progress:
                startActivity(new Intent(getApplicationContext(), ProgressAcitivty.class));
                break;
            case R.id.btn_show_custom:
                startActivity(new Intent(getApplicationContext(), CustomActivity.class));
                break;
            case R.id.btn_biginbox_style_show:
                showBigStyleNotify(3);
                break;
            case R.id.btn_bigpicture_style_show:
                showBigStyleNotify(1);
                break;
            case R.id.btn_bigtext_style_show:
                showBigStyleNotify(2);
                break;
            default:
                break;
        }
    }

}
