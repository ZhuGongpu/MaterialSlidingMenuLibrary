package bainiaohe.materialslidingmenulibrary.sections;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import bainiaohe.materialslidingmenulibrary.R;
import com.balysv.materialripple.MaterialRippleLayout;

/**
 * Created by zhugongpu on 15/2/13.
 */
public abstract class MaterialSection {

    private static final String TAG = "MaterialSection";
    /**
     * 所有需要响应点击事件的section都需要通过ripple layout设置背景
     */
    protected MaterialRippleLayout rippleLayout = null;
    // COLORS
    protected int colorPressed;
    protected int colorUnpressed;
    protected int colorSelected;
    /**
     * section 在 menu 中唯一的位置
     * 构造子类时需要设置
     */
    protected int position;
    protected boolean isSelected = false;
    protected View contentView = null;
    //target (至多只能存在一种) , 构造子类时需要设置
    protected Fragment targetFragment = null;
    protected Intent targetIntent = null;
    //用于处理点击事件
    protected MaterialSectionOnClickListener onClickListener;
    protected Context context = null;

    protected MaterialSection(Context context) {
        this.context = context;
        // resolve attributes from current theme
        Resources.Theme theme = context.getTheme();
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(R.attr.sectionStyle, typedValue, true);
        TypedArray values = theme.obtainStyledAttributes(typedValue.resourceId, R.styleable.MaterialSection);
        try {
            colorPressed = values.getColor(R.styleable.MaterialSection_sectionBackgroundColorPressed, 0x16000000);
            colorUnpressed = values.getColor(R.styleable.MaterialSection_sectionBackgroundColor, 0x00FFFFFF);
            colorSelected = values.getColor(R.styleable.MaterialSection_sectionBackgroundColorSelected, 0x0A000000);
        } finally {
            values.recycle();
        }
    }

    public Fragment getTargetFragment() {
        return targetFragment;
    }

    public Intent getTargetIntent() {
        return targetIntent;
    }

    /**
     * 设置content view中显示的内容、ripple layout颜色、text view颜色
     *
     * @return
     */
    public abstract View getContentView();

    public boolean isSelected() {
        return isSelected;
    }

    /**
     * 需要被复写
     * 用于处理取消选中事件
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;

        if (rippleLayout != null) {
            if (isSelected)
                rippleLayout.setRippleBackground(colorSelected);
            else
                rippleLayout.setRippleBackground(colorUnpressed);
        }

        Log.e(TAG, "set selected : " + isSelected + "  " + getPosition() + "   " + (rippleLayout == null));

    }

    public int getPosition() {
        return position;
    }

}
