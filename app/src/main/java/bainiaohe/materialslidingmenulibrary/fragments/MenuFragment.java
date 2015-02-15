package bainiaohe.materialslidingmenulibrary.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import bainiaohe.materialslidingmenulibrary.R;
import bainiaohe.materialslidingmenulibrary.sections.MaterialAccountSection;
import bainiaohe.materialslidingmenulibrary.sections.MaterialBodySection;
import bainiaohe.materialslidingmenulibrary.sections.MaterialBottomSection;
import bainiaohe.materialslidingmenulibrary.sections.listeners.MaterialAccountSectionOnClickListener;
import bainiaohe.materialslidingmenulibrary.sections.listeners.MaterialBodySectionOnClickListener;
import bainiaohe.materialslidingmenulibrary.sections.listeners.MaterialBottomSectionOnClickListener;
import bainiaohe.materialslidingmenulibrary.util.Utils;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.util.ArrayList;

/**
 * 注:生成section时，需要从上到下按顺序生成，且生成后必须添加到sections中
 * Created by zhugongpu on 15/2/13.
 */
public class MenuFragment extends Fragment implements MaterialBodySectionOnClickListener, MaterialAccountSectionOnClickListener, MaterialBottomSectionOnClickListener {

    public static final String ARGUMENT_NAME_RIPPLE_SUPPORT = "RippleSupport";
    private static final String TAG = "Menu Fragment";
    /**
     * 是否支持ripple effect
     * 从参数中获取
     */
    private boolean rippleSupport = true;
    /**
     * Views
     */
    private RelativeLayout account_section_container = null;
    private LinearLayout body_sections_container = null;
    private LinearLayout bottom_sections_container = null;
    /**
     * 用于存放menu中body中的所有section（account和bottom不需要存储）
     */
    private ArrayList<MaterialBodySection>
            sections = new ArrayList<>();
    //当前正在显示的fragment
    private Fragment currentFragment = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, null);
        init(view);

        return view;
    }

    private void init(View view) {
        if (getArguments() != null)
            rippleSupport = getArguments().getBoolean(ARGUMENT_NAME_RIPPLE_SUPPORT, true);

        this.account_section_container = (RelativeLayout) view.findViewById(R.id.account_section_container);
        this.body_sections_container = (LinearLayout) view.findViewById(R.id.body_sections_container);
        this.bottom_sections_container = (LinearLayout) view.findViewById(R.id.bottom_sections_container);

        //TODO add sections

        //set up account section
        setAccountSection("name", "email", R.mipmap.ic_launcher, null);

        //TODO add body sections
        addBodySection("title", "notification", null);
        addSubHeader("subheader");
        addBodySection("title", "notification", R.mipmap.ic_launcher, null);

        addDivider();//结束body section

        //set up bottom section
        setBottomSection("title", "notification", R.mipmap.ic_launcher, null);
    }

    //add sections
    public void setAccountSection(String name, String email, String avatarUrl, Intent targetIntent) {
        setAccountSection(new MaterialAccountSection(getActivity(), name, email, avatarUrl, targetIntent, this));
    }

    public void setAccountSection(String name, String email, int avatarId, Intent targetIntent) {
        setAccountSection(new MaterialAccountSection(getActivity(), name, email, avatarId, targetIntent, this));
    }

    /**
     * 设置account section，不需要指定position
     */
    public void setAccountSection(MaterialAccountSection account) {
        account_section_container.removeAllViews();
        account_section_container.addView(account.getContentView());
    }

    /**
     * add body section
     *
     * @param title
     * @param notification
     * @param targetFragment
     */
    public void addBodySection(String title, String notification, Fragment targetFragment) {
        addBodySection(new MaterialBodySection(getActivity(), title, notification, sections.size(), targetFragment, this));
    }

    public void addBodySection(String title, String notification, int iconId, Fragment targetFragment) {
        addBodySection(new MaterialBodySection(getActivity(), iconId, title, notification, sections.size(), targetFragment, this));
    }

    public void addSubHeader(String title) {

        float density = getActivity().getResources().getDisplayMetrics().density;
        // create layout
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        // inflate the line
        View divider = new View(getActivity());
        divider.setBackgroundColor(Color.parseColor("#e0e0e0"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);
        params.setMargins(0, (int) (8 * density), 0, (int) (8 * density));
        layout.addView(divider, params);

        // inflate the textView
        TextView textView = new TextView(getActivity());
        Utils.setAlpha(textView, 0.54f);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setGravity(Gravity.START);
        textView.setText(title);
        textView.setTextColor(Color.BLACK);

        LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsText.setMargins((int) (16 * density), 0, (int) (16 * density), (int) (4 * density));
        layout.addView(textView, paramsText);

        body_sections_container.addView(layout);
    }

    /**
     * 向body section 中添加分割线
     */
    public void addDivider() {
        View view = new View(getActivity());
        view.setBackgroundColor(Color.parseColor("#e0e0e0"));
        // height 1 px
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);
        body_sections_container.addView(view, params);
    }

    /**
     * 向body添加一个section(Icon Section或No Icon Section或Sub Header)，并指定position
     */
    public void addBodySection(MaterialBodySection section) {
        sections.add(section);
        body_sections_container.addView(section.getContentView());
    }

    public void setBottomSection(String title, String notification, int iconId, Intent targetIntent) {
        setBottomSection(new MaterialBottomSection(getActivity(), iconId, title, notification, targetIntent, this));
    }

    /**
     * 设置bottom section(Icon Section或No Icon Section)，不需要指定position
     */
    public void setBottomSection(MaterialBottomSection section) {
        bottom_sections_container.removeAllViews();
        bottom_sections_container.addView(section.getContentView());
    }


    @Override
    public void onClick(MaterialBodySection selectedSection) {

        Log.e(TAG, "onClick : " + selectedSection.getPosition() + " " + selectedSection.isSelected());

        if (!selectedSection.isSelected() && selectedSection.getTargetFragment() != null)//已处于选中状态时，不再响应点击事件
        {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            Fragment target = selectedSection.getTargetFragment();
            if (!target.isAdded()) {
                transaction.add(target, null);
            }
            if (currentFragment != null)
                transaction.hide(currentFragment);
            transaction.show(target).commit();//隐藏当前fragment，显示target

            currentFragment = target;//设置当前fragment为target
            ((SlidingFragmentActivity) (getActivity())).showContent();//show content

        }

        //取消其他section的选中状态
        for (MaterialBodySection section : sections) {
            if (section.isSelected() && section.getPosition() != selectedSection.getPosition())
                section.setSelected(false);
        }

        // 设置当前section为选中状态
        selectedSection.setSelected(true);
    }

    @Override
    public void onClick(MaterialAccountSection selectedSection) {
        if (selectedSection.getTargetIntent() != null) {
            startActivity(selectedSection.getTargetIntent());
        }
    }

    @Override
    public void onClick(MaterialBottomSection selectedSection) {
        if (selectedSection.getTargetIntent() != null) {
            startActivity(selectedSection.getTargetIntent());
        }
    }
}

