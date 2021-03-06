package com.keyi.zimushipinzhizuo.ui.activity.mine;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huopaolan.lib_core.Base.BaseActivity;
import com.keyi.zimushipinzhizuo.R;
import com.keyi.zimushipinzhizuo.ui.activity.unsubscribe.UnsubscribeActivity;
import com.keyi.zimushipinzhizuo.utils.CommonDialog;

public class HelpAndFeedBackActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton help_back;
    private TextView toolbarTitle;
    private RelativeLayout interface_problem, function_problem, message_problem, rests_problem, product_problem, access_logout, qq;
    private Intent intent;

    @Override
    public int layoutID() {
        return R.layout.activity_help_and_feed_back;
    }

    @Override
    public void initView() {
        help_back = findViewById(R.id.back_btn);
        help_back.setOnClickListener(this::onClick);
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getResources().getString(R.string.activity_title_help_feedback));
        interface_problem = findViewById(R.id.interface_problem);
        interface_problem.setOnClickListener(this::onClick);
        function_problem = findViewById(R.id.function_problem);
        function_problem.setOnClickListener(this::onClick);
        message_problem = findViewById(R.id.message_problem);
        message_problem.setOnClickListener(this::onClick);
        rests_problem = findViewById(R.id.rests_problem);
        rests_problem.setOnClickListener(this::onClick);
        product_problem = findViewById(R.id.product_problem);
        product_problem.setOnClickListener(this::onClick);
        access_logout = findViewById(R.id.access_logout);
        access_logout.setOnClickListener(this::onClick);
        qq = findViewById(R.id.qq);
        qq.setOnClickListener(this::onClick);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setUpDagger() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.interface_problem:
            case R.id.function_problem:
            case R.id.message_problem:
            case R.id.rests_problem:
            case R.id.product_problem:
                if (v.getId() == R.id.interface_problem) {
                    intent = new Intent(this, HelpActivity.class);
                    intent.putExtra("problem", "界面问题：");
                } else if (v.getId() == R.id.function_problem) {
                    intent = new Intent(this, HelpActivity.class);
                    intent.putExtra("problem", "功能问题：");
                } else if (v.getId() == R.id.message_problem) {
                    intent = new Intent(this, HelpActivity.class);
                    intent.putExtra("problem", "内容问题：");
                } else if (v.getId() == R.id.rests_problem) {
                    intent = new Intent(this, HelpActivity.class);
                    intent.putExtra("problem", "其他问题：");
                } else if (v.getId() == R.id.product_problem) {
                    intent = new Intent(this, HelpActivity.class);
                    intent.putExtra("problem", "产品建议：");
                }
                startActivity(intent);
                break;
            case R.id.access_logout:
                initDialog();
                break;
            default:
                Toast.makeText(this, "敬请期待", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initDialog() {
        final CommonDialog dialog = new CommonDialog(this);
        dialog.setMessage("温馨提示").setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
            @Override
            public void onPositiveClick() {
                dialog.dismiss();
            }

            @Override
            public void onNegtiveClick() {
                dialog.dismiss();
                startActivity(new Intent(HelpAndFeedBackActivity.this, UnsubscribeActivity.class));
            }

            @Override
            public void onClose() {
                dialog.dismiss();
            }
        }).show();
    }
}
