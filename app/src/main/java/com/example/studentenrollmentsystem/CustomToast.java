package com.example.studentenrollmentsystem;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class CustomToast extends AppCompatActivity {

    LinearLayout ll_text;
    ImageView iv_icon;
    TextView tv_status, tv_message;

    public void myToast(Context context, int iconRID, String message, String status)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);

        ll_text = view.findViewById(R.id.llTextToast);
        iv_icon = view.findViewById(R.id.ivIconToast);
        tv_message = view.findViewById(R.id.tvMessage);
        tv_status = view.findViewById(R.id.tvStatus);

//        ll_text.setBackground(color);
        iv_icon.setImageResource(iconRID);
        tv_status.setText(status);
        tv_message.setText(message);

        Toast toast = new Toast(context);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }


}