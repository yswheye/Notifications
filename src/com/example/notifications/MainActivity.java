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
 * ֪ͨ��Ӧ��
 */
public class MainActivity extends BaseActivity implements OnClickListener {
    private Button btn_show;
    private Button btn_bigstyle_show;
    private Button btn_show_progress;
    private Button btn_show_cz;
    private Button btn_clear;
    private Button btn_clear_all;
    private Button btn_show_custom;
    /** �����ת��ָ���Ľ��� */
    private Button btn_show_intent_act;
    /** �����ָ���Ľ�apk */
    private Button btn_show_intent_apk;
    /** Notification������ */
    NotificationCompat.Builder mBuilder;
    /** Notification��ID */
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

    /** ��ʼ��֪ͨ�� */
    private void initNotify() {
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("���Ա���").setContentText("��������").setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL)).setNumber(5)// ��ʾ����
                .setTicker("����֪ͨ����")// ֪ͨ�״γ�����֪ͨ��������������Ч����
                .setWhen(System.currentTimeMillis())// ֪ͨ������ʱ�䣬����֪ͨ��Ϣ����ʾ
                .setPriority(Notification.PRIORITY_DEFAULT)// ���ø�֪ͨ���ȼ�
                // .setAutoCancel(true)//���������־���û��������Ϳ�����֪ͨ���Զ�ȡ�� api16
                .setOngoing(false)// ture��������Ϊһ�����ڽ��е�֪ͨ������ͨ����������ʾһ����̨����,�û���������(�粥������)����ĳ�ַ�ʽ���ڵȴ�,���ռ���豸(��һ���ļ�����,ͬ������,������������)
                .setDefaults(Notification.DEFAULT_VIBRATE)// ��֪ͨ������������ƺ���Ч������򵥡���һ�µķ�ʽ��ʹ�õ�ǰ���û�Ĭ�����ã�ʹ��defaults���ԣ�������ϣ�
                // Notification.DEFAULT_ALL Notification.DEFAULT_SOUND ������� // requires VIBRATE
                // permission
                .setSmallIcon(R.drawable.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
    }

    /**
     * 1.��ʾ֪ͨ��
     */
    public void showNotify() {
        mBuilder.setContentTitle("���Ա���").setContentText("��������")
        // .setNumber(number)//��ʾ����
                .setTicker("����֪ͨ����");// ֪ͨ�״γ�����֪ͨ��������������Ч����
        mNotificationManager.notify(notifyId, mBuilder.build());
        // mNotification.notify(getResources().getString(R.string.app_name), notiId,
        // mBuilder.build());
    }

    /**
     * 2.��ʾ����ͼ���֪ͨ�� �������������;���ж��ַ�� 
     *  1.NotificationCompat.BigPictureStyle ��ͼƬ��������������һ��256dp�߶ȵ�λͼ
     *  2.NotificationCompat.BigTextStyle �����ַ����ʾһ��������ֿ� 
     *  3.NotificationCompat.InboxStyle
     *  �ռ�������ʾ�������ָ��ַ�񶼾������³�����ͼ�����е�����ѡ� 
     *      1.����⣺��չ����ͼʱ�����ͨ��ͼ�ı�� 
     *      2.�ܽ����֣�����������������֮������һ������
     */
    public void showBigStyleNotify(int type) {
        /**
         * InboxStyle
         */
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] events = new String[] {"��һ������", "�ڶ�������", "����������", "����������", "����������"};
        // Sets a title for the Inbox style big view
        inboxStyle.setBigContentTitle("����ͼ����");
        // Moves events into the big view
        for (int i = 0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }
        // ��ϸ������׶����һ���ı�
        inboxStyle.setSummaryText("��ϸ������׶����һ���ı�");
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
                .setContentTitle("���Ա���")
                .setContentText("��������")
                //.setContentInfo("num")//ͬsetNumber
                //.setNumber(22)
//                .setStyle(bigTextStyle)// ���÷��
                .setTicker("����֪ͨ����")
                .setWhen(System.currentTimeMillis())
                .addAction(0, "��", PendingIntent.getActivity(this, 0x111, new Intent(this, CustomActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
                .addAction(0, "����", PendingIntent.getActivity(this, 0x222, new Intent(this, CustomActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
                .addAction(0, "ɾ��", PendingIntent.getActivity(this, 0x333, new Intent(this, CustomActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
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
        .setContentTitle("���Ա���")
        .setContentText("��������")
        //.setContentInfo("num")//ͬsetNumber
        //.setNumber(22)
        .setTicker("����֪ͨ����")
        .setWhen(System.currentTimeMillis())
        .addAction(0, "��", PendingIntent.getActivity(this, 0x111, new Intent(this, CustomActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
        .addAction(0, "����", PendingIntent.getActivity(this, 0x222, new Intent(this, CustomActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
        .addAction(0, "ɾ��", PendingIntent.getActivity(this, 0x333, new Intent(this, CustomActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
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
     * 3.��ʾ��פ֪ͨ��
     */
    public void showCzNotify() {
        // Notification mNotification = new Notification();//Ϊ�˼������⣬���ø÷��������Զ�����BUILD��ʽ����
        // Notification mNotification = new Notification.Builder(this).getNotification();//���ַ�ʽ�Ѿ���ʱ
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        // //PendingIntent ��ת����
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, getIntent(), 0);
        mBuilder.setSmallIcon(R.drawable.ic_launcher).setTicker("��פ֪ͨ����").setContentTitle("��פ����").setContentText("ʹ��cancel()�����ſ��԰���ȥ��Ŷ").setContentIntent(pendingIntent);
        Notification mNotification = mBuilder.build();
        // ����֪ͨ ��Ϣ ͼ��
        mNotification.icon = R.drawable.ic_launcher;
        // ��֪ͨ���ϵ����֪ͨ���Զ������֪ͨ
        mNotification.flags = Notification.FLAG_ONGOING_EVENT;// FLAG_ONGOING_EVENT
                                                              // �ڶ�����פ�����Ե���������������ȥ��
                                                              // FLAG_AUTO_CANCEL ������������ȥ��
        // ������ʾ֪ͨʱ��Ĭ�ϵķ������𶯡�LightЧ��
        mNotification.defaults = Notification.DEFAULT_VIBRATE;
        // ���÷�����Ϣ������
        mNotification.tickerText = "֪ͨ����";
        // ���÷���֪ͨ��ʱ��
        mNotification.when = System.currentTimeMillis();
        // mNotification.flags = Notification.FLAG_AUTO_CANCEL; //��֪ͨ���ϵ����֪ͨ���Զ������֪ͨ
        // mNotification.setLatestEventInfo(this, "��פ����", "ʹ��cancel()�����ſ��԰���ȥ��Ŷ", null); //������ϸ����Ϣ
        // ,������������Ѿ�������
        mNotificationManager.notify(notifyId, mNotification);
    }

    /**
     * 4.��ʾ֪ͨ�������ת��ָ��Activity
     */
    public void showIntentActivityNotify() {
        // Notification.FLAG_ONGOING_EVENT --���ó�פ Flag;Notification.FLAG_AUTO_CANCEL
        // ֪ͨ���ϵ����֪ͨ���Զ������֪ͨ
        // notification.flags = Notification.FLAG_AUTO_CANCEL; //��֪ͨ���ϵ����֪ͨ���Զ������֪ͨ
        mBuilder.setAutoCancel(true)// �������֪ͨ����ʧ
                .setContentTitle("���Ա���").setContentText("�����ת").setTicker("����");
        // �������ͼACTION����ת��Intent
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify(notifyId, mBuilder.build());
    }

    /**
     * 5.��ʾ֪ͨ�������Apk
     */
    public void showIntentApkNotify() {
        // Notification.FLAG_ONGOING_EVENT --���ó�פ Flag;Notification.FLAG_AUTO_CANCEL
        // ֪ͨ���ϵ����֪ͨ���Զ������֪ͨ
        // notification.flags = Notification.FLAG_AUTO_CANCEL; //��֪ͨ���ϵ����֪ͨ���Զ������֪ͨ
        mBuilder.setAutoCancel(true)// �������֪ͨ����ʧ
                .setContentTitle("�������").setContentText("�����װ").setTicker("������ɣ�");
        // ����������Ҫ�����Ǵ�һ����װ��
        Intent apkIntent = new Intent();
        apkIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        apkIntent.setAction(android.content.Intent.ACTION_VIEW);
        // ע�⣺��������APK�Ƿ���assets�ļ����£���ȡ·������ֱ�Ӷ�ȡ�ģ�Ҫͨ��COYP��ȥ�ڶ�����ֱ�Ӷ�ȡ�Լ����ص�PATH�����ֻ����һ����תAPK��ʵ�ʴ򲻿���
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
