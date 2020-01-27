package com.dinesh.motionlayoutpoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dinesh.motionlayoutpoc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    MotionLayout mMotionBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mMotionBase = activityMainBinding.motionBase;


    }

    private void removeMotionSceneToView() {
        mMotionBase.removeView(mMotionBase.getChildAt(mMotionBase.getChildCount() - 1));

        Log.d("ABC", "Count " + mMotionBase.getChildCount());
    }

    @SuppressLint("InflateParams")
    private void addMotionSceneToView() {

        Log.d("ABC", "add view Count before insertion :: " + mMotionBase.getChildCount());


        if (mMotionBase.getChildCount() == 0) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView;
            if (inflater != null) {
                rowView = inflater.inflate(R.layout.insert_card, mMotionBase, false);


                mMotionBase.addView(rowView, 0);

                Log.d("ABC", "add view Count after insertion :: " + mMotionBase.getChildCount());

                mMotionBase.invalidate();


            }
        }
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_D:
                addMotionSceneToView();
                mMotionBase.rebuildScene();
                break;

            case KeyEvent.KEYCODE_A:
                removeMotionSceneToView();
                break;

            case KeyEvent.KEYCODE_S:
                mMotionBase.setTransition(R.id.start_anim, R.id.middle_anim);
                mMotionBase.setTransitionDuration(1000);
                mMotionBase.setTransitionListener(mStartSecondAnimListener);
                mMotionBase.transitionToEnd();
                break;
            case KeyEvent.KEYCODE_R:
                mMotionBase.setTransition(R.id.middle_anim, R.id.start_anim);
                mMotionBase.setTransitionDuration(1000);
                mMotionBase.setTransitionListener(mEndAnimListener);
                mMotionBase.transitionToEnd();
                break;

        }
        return super.onKeyUp(keyCode, event);
    }


    MotionLayout.TransitionListener mStartSecondAnimListener = new MotionLayout.TransitionListener() {
        @Override
        public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {
            Log.d("ABC", "Started" );

        }

        @Override
        public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {
            Log.d("ABC", "percentage" + (motionLayout.getProgress()*100));


        }

        @Override
        public void onTransitionCompleted(MotionLayout motionLayout, int i) {
            mMotionBase.setTransition(R.id.middle_anim, R.id.end_anim);
            mMotionBase.setTransitionDuration(1000);
            mMotionBase.setTransitionListener(null);
            mMotionBase.transitionToEnd();
            Log.d("ABC", "Completed");

        }

        @Override
        public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {
            Log.d("ABC", "Trigger" + (motionLayout.getDefinedTransitions()));

        }
    };

    MotionLayout.TransitionListener mEndAnimListener = new MotionLayout.TransitionListener() {
        @Override
        public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {

        }

        @Override
        public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {

        }

        @Override
        public void onTransitionCompleted(MotionLayout motionLayout, int i) {
            mMotionBase.setTransition(R.id.middle_anim, R.id.start_anim);
            mMotionBase.setTransitionDuration(1000);
            mMotionBase.setTransitionListener(null);
            mMotionBase.transitionToEnd();

        }

        @Override
        public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

        }
    };
}
