package eu.rampsoftware.er.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import eu.rampsoftware.er.R;

public class ValueSwitcher extends LinearLayout {
    private TextView mTextValue;
    private ImageView mImagePrevious;
    private ImageView mImageNext;
    private OnPositionChangedListener mNextClickListener;
    private OnPositionChangedListener mPreviousClickListener;


    public ValueSwitcher(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttributes(context, attrs);
    }

    public ValueSwitcher(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttributes(context, attrs);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ValueSwitcher,
                0, 0);

        try {
            final String textValue = a.getString(R.styleable.ValueSwitcher_textValue);
            final int textColor = a.getColor(R.styleable.ValueSwitcher_textColor, Color.BLACK);
            final int textSizePx = a.getDimensionPixelSize(R.styleable.ValueSwitcher_textSize, 26);

            this.mTextValue.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePx);
            this.mTextValue.setText(textValue);
            this.mTextValue.setTextColor(textColor);

        } finally {
            a.recycle();
        }

    }
    public void setOnNextClickListener(final OnPositionChangedListener listener) {
        mNextClickListener = listener;
    }

    public void setOnPreviousClickListener(final OnPositionChangedListener listener) {
        mPreviousClickListener = listener;
    }


    private void initView(final Context context) {
        inflate(context, R.layout.view_value_switcher, this);
        mTextValue = (TextView) findViewById(R.id.vs_text_value);
        mImagePrevious = (ImageView) findViewById(R.id.vs_image_previous);
        mImageNext = (ImageView) findViewById(R.id.vs_image_next);

        mImagePrevious.setOnClickListener(view -> onPreviousClicked());
        mImageNext.setOnClickListener(view -> onNextClicked());

    }

    private void onNextClicked() {
        if(null != mNextClickListener){
            mNextClickListener.onClick();
        }
    }

    private void onPreviousClicked() {
        if(null != mPreviousClickListener){
            mPreviousClickListener.onClick();
        }
    }

    public void setTextValue(final String text) {
        mTextValue.setText(text);
    }

    public interface OnPositionChangedListener {
        void onClick();
    }
}
