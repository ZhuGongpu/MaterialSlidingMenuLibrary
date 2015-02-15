package bainiaohe.materialslidingmenulibrary.sections;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import bainiaohe.materialslidingmenulibrary.R;

/**
 * Created by zhugongpu on 15/2/15.
 */
public class MaterialBottomSection extends MaterialIconSection {
    public MaterialBottomSection(Context context, int iconResourceId, String title, String notification, int position,
                                 Fragment targetFragment,
                                 MaterialSectionOnClickListener onClickListener) {
        super(context, iconResourceId, title, notification, position, targetFragment, onClickListener);
        super.layoutId = R.layout.layout_material_section_with_icon_large_ripple;
    }

    @Override
    public View getContentView() {

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        // inflate the line
        View divider = new View(context);
        divider.setBackgroundColor(Color.parseColor("#e0e0e0"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);

        layout.addView(divider, params);
        layout.addView(super.getContentView());

        return layout;
    }


}
