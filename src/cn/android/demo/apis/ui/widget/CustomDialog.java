package cn.android.demo.apis.ui.widget;

import cn.android.demo.apis.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomDialog extends Dialog {

	public CustomDialog(Context context) {
		super(context);
	}

	public CustomDialog(Context context, int theme) {
		super(context, theme);
	}

	public static class Builder {
		private Context context;// 上下文对象
		private String title;// 对话框标题
		private String message;// 对话框内容

		private String textOK;// 按钮 确定
		private String textCancel;// 按钮 取消

		private View contentView;// 对话框中间加载的其他布局界面

		/* 按键事件 */
		private DialogInterface.OnClickListener okClickListener;
		private DialogInterface.OnClickListener cancelClickListener;

		public Builder(Context context) {
			this.context = context;
		}

		/* 设置标题 */
		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		/* 设置对话框信息 */
		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		/* 设置对话框界面 */
		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		/**
		 * Set the positive button resource and it's listener
		 * 
		 * @param confirm_btnText
		 * @return
		 */
		public Builder setPositiveButton(int confirm_btnText,
				DialogInterface.OnClickListener listener) {
			this.textOK = (String) context.getText(confirm_btnText);
			this.okClickListener = listener;
			return this;
		}

		/**
		 * Set the positive button and it's listener
		 * 
		 * @param confirm_btnText
		 * @return
		 */
		public Builder setPositiveButton(String confirm_btnText,
				DialogInterface.OnClickListener listener) {
			this.textOK = confirm_btnText;
			this.okClickListener = listener;
			return this;
		}

		/**
		 * Set the negative button resource and it's listener
		 * 
		 * @param confirm_btnText
		 * @return
		 */
		public Builder setNegativeButton(int cancel_btnText,
				DialogInterface.OnClickListener listener) {
			this.textCancel = (String) context.getText(cancel_btnText);
			this.cancelClickListener = listener;
			return this;
		}

		/**
		 * Set the negative button and it's listener
		 * 
		 * @param confirm_btnText
		 * @return
		 */
		public Builder setNegativeButton(String cancel_btnText,
				DialogInterface.OnClickListener listener) {
			this.textCancel = cancel_btnText;
			this.cancelClickListener = listener;
			return this;
		}

		public CustomDialog create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// instantiate the dialog with the custom Theme
			final CustomDialog dialog = new CustomDialog(context,
					R.style.ios_dialog_styles);
			View layout = inflater.inflate(R.layout.ui_widget_custom_dialog,
					null);
			dialog.addContentView(layout, new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			// set the dialog title
			((TextView) layout.findViewById(R.id.ui_widget_custom_dialog_title))
					.setText(title);
			((TextView) layout.findViewById(R.id.ui_widget_custom_dialog_title))
					.getPaint().setFakeBoldText(true);
			;
			// set the confirm button
			if (textOK != null) {
				((Button) layout
						.findViewById(R.id.ui_widget_custom_dialog_confirm_btn))
						.setText(textOK);
				if (okClickListener != null) {
					((Button) layout
							.findViewById(R.id.ui_widget_custom_dialog_confirm_btn))
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									okClickListener.onClick(dialog,
											DialogInterface.BUTTON_POSITIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.ui_widget_custom_dialog_confirm_btn)
						.setVisibility(View.GONE);
			}
			// set the cancel button
			if (textCancel != null) {
				((Button) layout
						.findViewById(R.id.ui_widget_custom_dialog_cancel_btn))
						.setText(textCancel);
				if (cancelClickListener != null) {
					((Button) layout
							.findViewById(R.id.ui_widget_custom_dialog_cancel_btn))
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									cancelClickListener.onClick(dialog,
											DialogInterface.BUTTON_NEGATIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.ui_widget_custom_dialog_cancel_btn)
						.setVisibility(View.GONE);
			}
			// set the content message
			if (message != null) {
				((TextView) layout
						.findViewById(R.id.ui_widget_custom_dialog_message))
						.setText(message);
			} else if (contentView != null) {
				// if no message set
				// add the contentView to the dialog body

				((LinearLayout) layout
						.findViewById(R.id.ui_widget_custom_dialog_message))
						.removeAllViews();
				((LinearLayout) layout
						.findViewById(R.id.ui_widget_custom_dialog_message))
						.addView(contentView,
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);

			}
			dialog.setContentView(layout);
			return dialog;
		}
	}

}
