package com.bosscall.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText number;
    private int dp(float v) { return (int)(v * getResources().getDisplayMetrics().density + 0.5f); }
    private void dial(boolean hideId) {
        String n = number.getText().toString().trim().replace(" ", "").replace("-", "");
        if (n.isEmpty()) { number.setError("Pehle mobile number likho"); return; }
        if (n.length() < 5) { number.setError("Number check karo"); return; }
        String uriNumber = (hideId ? "#31#" : "") + n;
        try { startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", uriNumber, null))); }
        catch (Exception e) { Toast.makeText(this, "Phone dialer nahi khul paya", Toast.LENGTH_LONG).show(); }
    }
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        LinearLayout root = new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setGravity(Gravity.CENTER_HORIZONTAL); root.setPadding(dp(24), dp(36), dp(24), dp(24)); root.setBackgroundColor(Color.rgb(12,18,34));
        TextView title = new TextView(this); title.setText("BOSS CALL"); title.setTextColor(Color.WHITE); title.setTextSize(30); title.setGravity(Gravity.CENTER); title.setTypeface(null, 1); root.addView(title, new LinearLayout.LayoutParams(-1, dp(70)));
        TextView sub = new TextView(this); sub.setText("Simple calling • No account needed"); sub.setTextColor(Color.LTGRAY); sub.setTextSize(14); sub.setGravity(Gravity.CENTER); root.addView(sub, new LinearLayout.LayoutParams(-1, dp(42)));
        number = new EditText(this); number.setSingleLine(true); number.setHint("Mobile number"); number.setInputType(3); number.setTextColor(Color.WHITE); number.setHintTextColor(Color.GRAY); number.setTextSize(20); number.setPadding(dp(16),0,dp(16),0); number.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.rgb(90,150,255))); LinearLayout.LayoutParams np = new LinearLayout.LayoutParams(-1, dp(60)); np.setMargins(0,dp(28),0,dp(18)); root.addView(number,np);
        Button normal = button("CALL", Color.rgb(28,150,95)); root.addView(normal, params()); normal.setOnClickListener(v -> dial(false));
        Button privateCall = button("TRY HIDE CALLER ID", Color.rgb(45,105,190)); LinearLayout.LayoutParams bp=params(); bp.topMargin=dp(12); root.addView(privateCall,bp); privateCall.setOnClickListener(v -> dial(true));
        TextView note = new TextView(this); note.setText("Note: #31# caller-ID hiding may not work on every SIM/network. Jio/your operator controls CLIR. This app cannot guarantee a hidden number. Calls use your normal SIM plan."); note.setTextColor(Color.LTGRAY); note.setTextSize(13); note.setPadding(0,dp(28),0,0); root.addView(note,new LinearLayout.LayoutParams(-1,-2));
        TextView internet = new TextView(this); internet.setText("Internet app-to-app calling is not included in this first version."); internet.setTextColor(Color.rgb(255,210,120)); internet.setTextSize(13); internet.setPadding(0,dp(18),0,0); root.addView(internet,new LinearLayout.LayoutParams(-1,-2));
        setContentView(root);
    }
    private Button button(String text,int color) { Button b=new Button(this); b.setText(text); b.setTextSize(15); b.setTextColor(Color.WHITE); b.setBackgroundTintList(android.content.res.ColorStateList.valueOf(color)); return b; }
    private LinearLayout.LayoutParams params() { return new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,dp(56)); }
}
