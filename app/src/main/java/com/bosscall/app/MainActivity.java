package com.bosscall.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText number;
    private int dp(float v) { return (int)(v * getResources().getDisplayMetrics().density + 0.5f); }
    private TextView text(String value, int size) {
        TextView t = new TextView(this); t.setText(value); t.setTextSize(size); t.setTextColor(Color.BLACK); t.setGravity(Gravity.CENTER); return t;
    }
    private Button button(String value) {
        Button b = new Button(this); b.setText(value); b.setAllCaps(false); return b;
    }
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        LinearLayout root = new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setGravity(Gravity.CENTER_HORIZONTAL); root.setPadding(dp(22), dp(30), dp(22), dp(20)); root.setBackgroundColor(Color.rgb(247,248,252));
        TextView title = text("BOSS CALL", 30); title.setTextColor(Color.rgb(25,75,160)); title.setTypeface(null, 1); root.addView(title, new LinearLayout.LayoutParams(-1, dp(60)));
        TextView sub = text("Simple SIM dialer", 16); root.addView(sub, new LinearLayout.LayoutParams(-1, dp(40)));
        number = new EditText(this); number.setSingleLine(true); number.setTextSize(22); number.setHint("Mobile number"); number.setInputType(3); number.setPadding(dp(14), dp(12), dp(14), dp(12)); root.addView(number, new LinearLayout.LayoutParams(-1, dp(62)));
        Button call = button("Call"); root.addView(call, new LinearLayout.LayoutParams(-1, dp(58)));
        call.setOnClickListener(v -> dial(number.getText().toString(), false));
        Button hidden = button("Try caller ID hidden (#31#)"); root.addView(hidden, new LinearLayout.LayoutParams(-1, dp(58)));
        hidden.setOnClickListener(v -> dial(number.getText().toString(), true));
        TextView note = text("Caller-ID hiding depends on your SIM/network and may not work. #31# is not guaranteed. Calls use your normal mobile plan.", 14); note.setTextColor(Color.DKGRAY); note.setPadding(dp(8), dp(18), dp(8), dp(18)); root.addView(note, new LinearLayout.LayoutParams(-1, -2));
        TextView internet = text("Internet app-to-app calling is not included in this first version.", 14); internet.setTextColor(Color.DKGRAY); root.addView(internet, new LinearLayout.LayoutParams(-1, dp(70)));
        setContentView(root);
    }
    private void dial(String raw, boolean hide) {
        String n = raw.trim().replace(" ", ""); if (n.isEmpty()) { number.setError("Enter a phone number"); return; }
        if (hide && !n.startsWith("#31#")) n = "#31#" + n;
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(n)));
        startActivity(i);
    }
}
