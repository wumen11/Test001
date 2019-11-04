package com.example.ubuntu.test001;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private boolean aBoolean=false;


    private LinearLayout linearLayout;
    private int w,h;
    private int x,y,r=20,speed=-10;
    private int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        WindowManager manager=getWindowManager();
        Display display = manager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        w=displayMetrics.widthPixels;
        h=displayMetrics.heightPixels;
        x=w/2;
        y=h/2;
        linearLayout=findViewById(R.id.linear);
        textView=findViewById(R.id.tv);
        button=findViewById(R.id.btn_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aBoolean==false){
                    aBoolean=true;
                    button.setText("关闭震动");
                }else {
                    aBoolean=false;
                    button.setText("开启震动");
                }

            }
        });
        final BallView ballView=new BallView(this,r);
        linearLayout.addView(ballView);
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    if (ballView != null) {
                        textView.setText(index+"");
                        ballView.invalidate();
                    }
                }
            }
        };

        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

//                if(x==0||x==w){
//                    speed*=-1;
//                }
//                x=x+speed;

                if (y<=r||y>=h-r){
                    speed*=-1;
                    index++;
                    if(aBoolean!=false){
                        vibrator();
                    }
                }
                y=y+speed;

                handler.sendEmptyMessage(1);
            }
        },0,10);


    }

    public void vibrator(){
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);
    }




    class BallView extends View {
        Paint p=new Paint();
        private int r;

        public BallView(Context context,int r) {
            super(context);
            this.r=r;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.BLUE);
            p.setAntiAlias(true);
            canvas.drawCircle(x,y,r,p);


        }
    }

}
