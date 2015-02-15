package bainiaohe.materialslidingmenulibrary.sections;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import bainiaohe.materialslidingmenulibrary.R;
import com.balysv.materialripple.MaterialRippleLayout;

/**
 * Created by zhugongpu on 15/2/13.
 */
public class MaterialNoIconSection extends MaterialSection {
    private static final String TAG = "MaterialNoIconSection";
    private String title = "";//section中显示的文字
    private String notification = "";//通知内容（可以为数字）

    public MaterialNoIconSection(Context context, String title, String notification, int position, Fragment targetFragment,
                                 MaterialSectionOnClickListener onClickListener) {
        super(context);
        this.title = title;
        this.notification = notification;
        this.position = position;
        this.targetFragment = targetFragment;
        this.targetIntent = null;
        this.onClickListener = onClickListener;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    @Override
    public View getContentView() {
        if (contentView == null) {
            this.contentView = View.inflate(context, R.layout.layout_material_section_no_icon_ripple, null);
            ((TextView) contentView.findViewById(R.id.section_text)).setText(title);
            ((TextView) contentView.findViewById(R.id.section_notification)).setText(notification);

            rippleLayout = (MaterialRippleLayout) contentView.findViewById(R.id.section_ripple);
            rippleLayout.setRippleColor(colorPressed);
            rippleLayout.setRippleBackground(colorUnpressed);

            contentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onClick(MaterialNoIconSection.this);
                }
            });

            Log.e(TAG, "MaterialNoIconSection ripple layout:" + (rippleLayout == null));
        }
        return contentView;
    }

}
