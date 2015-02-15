package bainiaohe.materialslidingmenulibrary.sections;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;
import bainiaohe.materialslidingmenulibrary.R;
import com.balysv.materialripple.MaterialRippleLayout;
import com.squareup.picasso.Picasso;

/**
 * Created by zhugongpu on 15/2/13.
 */
public class MaterialIconSection extends MaterialSection {

    protected int layoutId = R.layout.layout_material_section_with_icon_ripple;
    private int iconResourceId;//图标drawable id
    private String title = "";//section中显示的文字
    private String notification = "";//通知内容（可以为数字）

    public MaterialIconSection(Context context, int iconResourceId, String title, String notification, int position,
                               Fragment targetFragment, MaterialSectionOnClickListener onClickListener) {
        super(context);
        this.iconResourceId = iconResourceId;
        this.title = title;
        this.notification = notification;
        this.position = position;
        this.targetFragment = targetFragment;
        this.targetIntent = null;
        this.onClickListener = onClickListener;
    }

    public MaterialIconSection(Context context, int iconResourceId, String title, String notification, int position,
                               Intent targetIntent, MaterialSectionOnClickListener onClickListener) {
        super(context);
        this.iconResourceId = iconResourceId;
        this.title = title;
        this.notification = notification;
        this.position = position;
        this.targetFragment = null;
        this.targetIntent = targetIntent;
        this.onClickListener = onClickListener;
    }

    @Override
    public View getContentView() {
        if (contentView == null) {
            contentView = View.inflate(context, layoutId, null);

            Picasso.with(context).load(iconResourceId).into((android.widget.ImageView) contentView.findViewById(R.id.section_icon));
            ((TextView) contentView.findViewById(R.id.section_text)).setText(title);
            ((TextView) contentView.findViewById(R.id.section_notification)).setText(notification);

            rippleLayout = (MaterialRippleLayout) contentView.findViewById(R.id.section_ripple);
            rippleLayout.setRippleColor(colorPressed);
            rippleLayout.setRippleBackground(colorUnpressed);

            contentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onClick(MaterialIconSection.this);
                }
            });

        }
        return contentView;
    }

}
