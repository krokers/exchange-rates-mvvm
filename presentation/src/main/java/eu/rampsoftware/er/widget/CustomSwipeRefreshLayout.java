package eu.rampsoftware.er.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

/**
 * This implementation resolves problem with setting refresh when layout was not measured yet. In
 * such case, progress indicator was not presented. This implementation waits for the measurement,
 * and when ready, shows the progress if requested.
 */
public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {
    private boolean mMeasured = false;
    private boolean mPreMeasureRefreshing = false;


    public CustomSwipeRefreshLayout(final Context context) {
        super(context);
    }

    public CustomSwipeRefreshLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!mMeasured) {
            mMeasured = true;
            setRefreshing(mPreMeasureRefreshing);
        }
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        if (mMeasured) {
            super.setRefreshing(refreshing);
        } else {
            mPreMeasureRefreshing = refreshing;
        }
    }


}