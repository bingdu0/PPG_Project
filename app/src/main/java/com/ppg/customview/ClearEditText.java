package com.ppg.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.ppg.R;


/**
 * 
 * @ClassName: ClearEditText
 * @Description: 自定义EditText，在xml里设置了图片为类型1（清空内容），不设置图片为类型2（密码显示明文或暗文）
 * @author: yangwenquan
 * @date: 2015年2月5日 下午7:43:06
 * @version: V1.0
 */
@SuppressLint("AppCompatCustomView")
public class ClearEditText extends EditText implements OnFocusChangeListener, TextWatcher {
	/**
	 * 输入框后面图片的类型
	 */
	private int type = 1;
	/**
	 * 用来判断点击的切换
	 */
	private boolean isClick = true;
	/**
	 * 删除按钮的引用
	 */
	private Drawable mClearDrawable;
	/**
	 * 控件是否有焦点
	 */
	private boolean hasFoucs;

	public ClearEditText(Context context) {
		this(context, null);
	}

	public ClearEditText(Context context, AttributeSet attrs) {
		// 这里构造方法也很重要，不加这个很多属性不能再XML里面定义
		// this(context, attrs, android.R.attr.editTextStyle);
		super(context, attrs);
		init();
	}

	public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		// 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
		mClearDrawable = getCompoundDrawables()[2];
		if (mClearDrawable == null) {
			type = 2;
			// 默认密码暗文的图片
			mClearDrawable = getResources().getDrawable(R.drawable.pwd_invisible);
		}
		mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(),
				mClearDrawable.getIntrinsicHeight());
		// 默认设置隐藏图标
		setClearIconVisible(false);
		// 设置焦点改变的监听
		setOnFocusChangeListener(this);
		// 设置输入框里面内容发生改变的监听
		addTextChangedListener(this);
	}

	/**
	 * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件 当我们按下的位置 在 EditText的宽度 -
	 * 图标到控件右边的间距 - 图标的宽度 和 EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (getCompoundDrawables()[2] != null) {

				boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
						&& (event.getX() < ((getWidth() - getPaddingRight())));

				if (touchable) {
					if (type == 1) {
						this.setText("");
					} else {
						if (isClick) {
							// 设置EditText文本为可见的
							this.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
							// 设置暗文图片
							mClearDrawable = getResources().getDrawable(R.drawable.pwd_visible);
							mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(),
									mClearDrawable.getIntrinsicHeight());
							setCompoundDrawables(null, null, mClearDrawable, null);
							isClick = false;
						} else {
							// 设置EditText文本为隐藏的
							this.setTransformationMethod(PasswordTransformationMethod.getInstance());
							// 设置明文图片
							mClearDrawable = getResources().getDrawable(R.drawable.pwd_invisible);
							mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(),
									mClearDrawable.getIntrinsicHeight());
							setCompoundDrawables(null, null, mClearDrawable, null);
							isClick = true;
						}
						// 光标始终在内容后面
						Editable text = this.getText();
						Selection.setSelection(text, text.length());
					}
					//当点击图面的时候，清除焦点（不会产生点击两次出现输入法选择）
//					clearFocus();
				}else {
					//当点击内容的时候，获取焦点（正常显示）
//					requestFocus();
				}
				
			}
		}

		return super.onTouchEvent(event);
	}

	/**
	 * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
	 */
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		this.hasFoucs = hasFocus;
		if (hasFocus) {
			setClearIconVisible(getText().length() > 0);
		} else {
			setClearIconVisible(false);
		}
	}

	/**
	 * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
	 * 
	 * @param visible
	 */
	protected void setClearIconVisible(boolean visible) {
		Drawable right = visible ? mClearDrawable : null;
		setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right,
				getCompoundDrawables()[3]);
	}

	/**
	 * 当输入框里面内容发生变化的时候回调的方法
	 */
	@Override
	public void onTextChanged(CharSequence s, int start, int count, int after) {
		if (hasFoucs) {
			setClearIconVisible(s.length() > 0);
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

	@Override
	public void afterTextChanged(Editable s) {}

	/**
	 * 设置晃动动画
	 */
	public void setShakeAnimation() {
		// this.setAnimation(shakeAnimation(5));
		this.startAnimation(shakeAnimation(5));
	}

	/**
	 * 晃动动画
	 * 
	 * @param counts
	 *            1秒钟晃动多少下
	 * @return
	 */
	public static Animation shakeAnimation(int counts) {
		Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
		translateAnimation.setInterpolator(new CycleInterpolator(counts));
		translateAnimation.setDuration(1000);
		return translateAnimation;
	}
}
