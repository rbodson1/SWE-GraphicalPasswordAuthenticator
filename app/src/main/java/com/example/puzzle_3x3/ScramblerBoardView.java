/*
   PLEASE DO NOT MESS WITH THIS FILE (CODE)
*/
package com.example.puzzle_3x3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class ScramblerBoardView extends View {
    public static final int NUM_SHUFFLE_STEPS = -1;
    private Activity activity;
    private ScramblerBoard scramblerBoard;
    private ArrayList<ScramblerBoard> animation;
    private Random random = new Random();


    Comparator<ScramblerBoard> comparator = new ScramblerBoardComparator();
    PriorityQueue<ScramblerBoard> queue = new PriorityQueue<>(9999, comparator);

    public ScramblerBoardView(Context context) {
        super(context);
        activity = (Activity) context;
        animation = null;


    }
    // this gets the width of the image and initialize it to the boardView
    public void initialize(Bitmap imageBitmap) {
        int width = getWidth();
        scramblerBoard = new ScramblerBoard(imageBitmap, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       if (scramblerBoard != null) {
            if (animation != null && animation.size() > 0) {
                scramblerBoard = animation.remove(0);
                scramblerBoard.draw(canvas);

            } else {
                scramblerBoard.draw(canvas);
            }
        }
    }

    // this is the function that displays and resets the image
    public void displayReset() {
            ArrayList<ScramblerBoard> boards;
            for (int i = 0; i <= NUM_SHUFFLE_STEPS; i++) {
                boards = scramblerBoard.neighbours();
                scramblerBoard = boards.get(random.nextInt(boards.size()));
            }
            invalidate();
            queue.clear();
        }

    // this enables the user to touch a tile to move it
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (animation == null && scramblerBoard != null) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (scramblerBoard.click(event.getX(), event.getY())) {
                        invalidate();
                        return true;
                    }
            }
        }
        return super.onTouchEvent(event);
    }

}
class ScramblerBoardComparator implements Comparator<ScramblerBoard> {
    @Override
    public int compare(ScramblerBoard first, ScramblerBoard second) {
        if (first.priority() == second.priority()) {
            return 0;
        }
        else if (first.priority() < second.priority()) {
            return -1;
        }
        else {
            return 1;
        }
    }
}
