package me.himanshusoni.edittextspinner;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by himanshusoni on 30/10/15.
 */
public class EditTextSpinner<T extends EditTextSpinner.Listable> extends AppCompatEditText {

    List<T> mItems;
    String[] mListableItems;
    CharSequence mHint;

    OnItemSelectedListener<T> onItemSelectedListener;

    public EditTextSpinner(Context context) {
        super(context);

        mHint = getHint();
    }

    public EditTextSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);

        mHint = getHint();
    }

    public EditTextSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mHint = getHint();
    }

//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public ClickToSelectEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//
//        mHint = getHint();
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setFocusable(false);
        setClickable(true);
    }

    public void setItems(List<T> items) {
        this.mItems = items;
        this.mListableItems = new String[items.size()];

        int i = 0;

        for (T item : mItems) {
            mListableItems[i++] = item.getLabel();
        }

        configureOnClickListener();
    }

    private void configureOnClickListener() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(mHint);
                builder.setItems(mListableItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {
                        setText(mListableItems[selectedIndex]);

                        if (onItemSelectedListener != null) {
                            onItemSelectedListener.onItemSelectedListener(mItems.get(selectedIndex), selectedIndex);
                        }
                    }
                });
                builder.setPositiveButton(android.R.string.cancel, null);
                builder.create().show();
            }
        });
    }

    public void setOnItemSelectedListener(OnItemSelectedListener<T> onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public interface OnItemSelectedListener<T> {
        void onItemSelectedListener(T item, int selectedIndex);
    }

    public interface Listable {
        String getLabel();
    }
}
