package bainiaohe.materialslidingmenulibrary.sections;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import bainiaohe.materialslidingmenulibrary.R;
import bainiaohe.materialslidingmenulibrary.util.Utils;

/**
 * 包括text view和分割线
 * 不可点击
 * Created by zhugongpu on 15/2/13.
 */
public class MaterialSubHeader extends MaterialSection {
    private String title = "";
    private int titleColor;


    public MaterialSubHeader(Context context, String title, int position) {
        super(context);
        this.title = title;
        this.position = position;
        this.targetIntent = null;
        this.targetFragment = null;
    }

    /**
     * 返回text 和 分割线
     *
     * @return
     */
    @Override
    public View getContentView() {

        float density = context.getResources().getDisplayMetrics().density;
        // create layout
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        // inflate the line
        View divider = new View(context);
        divider.setBackgroundColor(Color.parseColor("#e0e0e0"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);
        params.setMargins(0, (int) (8 * density), 0, (int) (8 * density));
        layout.addView(divider, params);

        // inflate the textView
        TextView textView = new TextView(context);
        Utils.setAlpha(textView, 0.54f);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setGravity(Gravity.START);
        textView.setText(title);
        LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsText.setMargins((int) (16 * density), 0, (int) (16 * density), (int) (4 * density));

        layout.addView(textView, paramsText);

        // get attributes from current theme
        Resources.Theme theme = context.getTheme();
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(R.attr.sectionStyle, typedValue, true);
        TypedArray values = theme.obtainStyledAttributes(typedValue.resourceId, R.styleable.MaterialSection);
        try {
            titleColor = values.getColor(R.styleable.MaterialSubheader_subheaderTitleColor, 0x000);
        } finally {
            values.recycle();
        }
        // set attributes to the divider
        textView.setTextColor(Color.BLACK);

        return layout;
    }

    /**
     * 不可选中
     *
     * @param isSelected
     */
    @Override
    public void setSelected(boolean isSelected) {
        this.isSelected = false;
    }
}
