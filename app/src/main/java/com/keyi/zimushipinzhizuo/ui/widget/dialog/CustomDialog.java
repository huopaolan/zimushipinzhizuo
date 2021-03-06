package com.keyi.zimushipinzhizuo.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.keyi.zimushipinzhizuo.R;


public class CustomDialog extends Dialog {

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private TextView messageView;
        private Button positiveView;
        private TextView negativeView;
        private TextView titleView;
        private DialogInterface.OnClickListener postiviteButtonClickLisener;
        private DialogInterface.OnClickListener negativaButtonClickListener;
        private int mLayoutId;

        public int getmLayoutId() {
            return mLayoutId;
        }

        public void setmLayoutId(int mLayoutId) {
            this.mLayoutId = mLayoutId;
        }

        public Builder(Context context) {
            this.context = context;
        }

        public TextView getMessageView() {
            return messageView;
        }

        public Button getPositiveView() {
            return positiveView;
        }

        public TextView getNegativeView() {
            return negativeView;
        }

        public TextView getTitleView() {
            return titleView;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View view) {
            this.contentView = view;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText
                , DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.postiviteButtonClickLisener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText
                , DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativaButtonClickListener = listener;
            return this;
        }

        public CustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final CustomDialog dialog = new CustomDialog(context, R.style.myDialog);
            dialog.setCancelable(false);
            View layout = inflater.inflate(mLayoutId != 0 ? mLayoutId : R.layout.dialog_normal_layout, null);
            dialog.addContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            messageView = (TextView)(layout.findViewById(R.id.message));
            positiveView = (Button)(layout.findViewById(R.id.positive_btn));
            negativeView = (TextView) (layout.findViewById(R.id.negative_btn));
            titleView = (TextView) layout.findViewById(R.id.title);
            ((TextView) layout.findViewById(R.id.title)).setText(title);
            if (positiveButtonText != null) {
                ((Button)(layout.findViewById(R.id.positive_btn)))
                        .setText(positiveButtonText);
                if (postiviteButtonClickLisener != null) {
                    ((Button)(layout.findViewById(R.id.positive_btn)))
                            .setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    postiviteButtonClickLisener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                layout.findViewById(R.id.positive_btn).setVisibility(View.GONE);
            }

            if (negativeButtonText != null) {
                ((TextView) (layout.findViewById(R.id.negative_btn)))
                        .setText(negativeButtonText);
                if (negativaButtonClickListener != null) {
                    ((TextView) (layout.findViewById(R.id.negative_btn)))
                            .setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    negativaButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                layout.findViewById(R.id.negative_btn)
                        .setVisibility(View.GONE);
            }

            if (message != null) {
                ((TextView) (layout.findViewById(R.id.message)))
                        .setText(message);
            } else  if (contentView != null) {
                ((LinearLayout) (layout.findViewById(R.id.content)))
                        .removeAllViews();
                ((LinearLayout) (layout.findViewById(R.id.content)))
                        .addView(contentView
                                , new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
            } else {
                ((TextView) (layout.findViewById(R.id.message)))
                        .setVisibility(View.GONE);
            }
            dialog.setContentView(layout);
            return dialog;
        }

    }

}
