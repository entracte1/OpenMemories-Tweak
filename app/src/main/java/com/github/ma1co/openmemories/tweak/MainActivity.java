package com.github.ma1co.openmemories.tweak;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends TabActivity {
    public static final File LOG_FILE = new File("/sdcard/TWEAKLOG.TXT");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addTab("video", "Video", android.R.drawable.ic_menu_camera, VideoActivity.class);
        addTab("lang", "Languages", android.R.drawable.ic_menu_mapmode, LanguageActivity.class);
        addTab("protection", "Protection", android.R.drawable.ic_lock_lock, ProtectionActivity.class);
    }

    protected void addTab(String tag, String label, int iconId, Class activity) {
        TabHost.TabSpec tab = getTabHost().newTabSpec(tag);
        tab.setIndicator(label, getResources().getDrawable(iconId));
        tab.setContent(new Intent(this, activity));
        getTabHost().addTab(tab);
    }

    @Override
    protected void onResume() {
        Logger.info("MainActivity", "application start");
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.info("MainActivity", "application end");
        try {
            FileWriter writer = new FileWriter(LOG_FILE, true);
            writer.write(Logger.getLogs() + "\n");
            writer.close();
            Logger.reset();
        } catch (IOException e) {}
    }
}
