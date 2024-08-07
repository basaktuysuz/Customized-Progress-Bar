package com.example.wallet;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.Path;

class ProgressDrawable extends Drawable {
    private static final int NUM_SEGMENTS = 4;
    private final int[] mSegmentColors;
    private final int mBackground;
    private final Paint mPaint = new Paint();
    private final Paint mPointerPaint = new Paint(); // Paint for the pointer
    private final Paint mGrayPaint = new Paint(); // Paint for the gray color
    private final RectF mSegment = new RectF();
    private final float mPointerSize = 50f; // Size of the pointer
    private final float mPointerOffset = 10f; // Offset above the progress bar
    private final int mGrayColor = Color.LTGRAY; // Gray color for remaining segments

    public ProgressDrawable(int[] segmentColors, int bgColor) {
        if (segmentColors.length != NUM_SEGMENTS) {
            throw new IllegalArgumentException("Number of segment colors must be equal to " + NUM_SEGMENTS);
        }
        mSegmentColors = segmentColors;
        mBackground = bgColor;
        mPointerPaint.setColor(Color.BLACK); // Color of the pointer
        mPointerPaint.setStyle(Paint.Style.FILL);
        mGrayPaint.setColor(mGrayColor); // Gray paint for remaining segments
        mGrayPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected boolean onLevelChange(int level) {
        invalidateSelf();
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        float level = getLevel() / 10000f;
        Rect b = getBounds();
        float gapWidth = b.height() / 2f;
        float segmentWidth = (b.width() - (NUM_SEGMENTS - 1) * gapWidth) / NUM_SEGMENTS;
        mSegment.set(0, 0, segmentWidth, b.height());

        // Draw the segments
        for (int i = 0; i < NUM_SEGMENTS; i++) {
            float loLevel = i / (float) NUM_SEGMENTS;
            float hiLevel = (i + 1) / (float) NUM_SEGMENTS;

            if (loLevel <= level) {
                // Draw completed segments with their original color
                if (level <= hiLevel) {
                    float middle = mSegment.left + segmentWidth * (level - loLevel) / (hiLevel - loLevel);
                    mPaint.setColor(mSegmentColors[i]);
                    canvas.drawRect(mSegment.left, mSegment.top, middle, mSegment.bottom, mPaint);
                    // Draw background color for the remaining part of the segment
                    mPaint.setColor(mBackground);
                    canvas.drawRect(middle, mSegment.top, mSegment.right, mSegment.bottom, mPaint);
                } else {
                    mPaint.setColor(mSegmentColors[i]);
                    canvas.drawRect(mSegment, mPaint);
                }
            } else {
                // Draw the remaining segments in gray
                mPaint.setColor(mGrayColor);
                canvas.drawRect(mSegment, mPaint);
            }
            mSegment.offset(mSegment.width() + gapWidth, 0);
        }

        // Draw the downward triangular pointer above the progress bar
        float pointerX = (b.width() * level); // Center the pointer on the progress level
        float pointerY = b.top - mPointerSize - mPointerOffset; // Position above the bar

        // Ensure the pointer is within the bounds of the canvas
        if (pointerY < 0) {
            pointerY = 0;
        }

        drawDownwardTrianglePointer(canvas, pointerX, pointerY);
    }

    private void drawDownwardTrianglePointer(Canvas canvas, float x, float y) {
        float halfSize = mPointerSize / 2;
        Path path = new Path();
        path.moveTo(x, y); // Top of the triangle
        path.lineTo(x - halfSize, y + mPointerSize); // Bottom left
        path.lineTo(x + halfSize, y + mPointerSize); // Bottom right
        path.close(); // Close the triangle path
        canvas.drawPath(path, mPointerPaint); // Draw the pointer with the pointer paint
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
        mPointerPaint.setAlpha(alpha);
        mGrayPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
        mPointerPaint.setColorFilter(cf);
        mGrayPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
