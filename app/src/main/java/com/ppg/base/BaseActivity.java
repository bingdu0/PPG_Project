package com.ppg.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ppg.R;
import com.ppg.bean.RxBusMessage;
import com.ppg.rxjava.RxBus;

import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * Created by GaoSheng on 2016/9/13.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements
        IView {
    protected View view;

    protected P mPresenter;

    protected RxBus rxBus;

    public TextView baseTitle;
    public TextView baseTvRight;
    public ImageButton btnBaseBarBack;
    public ImageButton btnMore;
    public ImageButton btnAdd;
    public RelativeLayout baseBarLayou;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
        ButterKnife.bind(this);
        mPresenter = loadPresenter();
        initCommonData();
        initBar();
        initRxbus();
        initData();
        initListener();

    }

    private void initCommonData() {
        if (mPresenter != null)
            mPresenter.attachView(this);

        rxBus = RxBus.getInstance();

        ButterKnife.bind(this);

    }

    protected abstract int getLayoutId();

    protected abstract P loadPresenter();

    protected abstract void initData();

    protected abstract void initListener();


    /**
     * 获取RxBus信息
     *
     * @param rxBusMessage
     */
    public void getRxBusMassage(RxBusMessage rxBusMessage) {

    }

    /**
     * @return 显示的内容
     */
    public View getView() {
        view = View.inflate(this, getLayoutId(), null);
        return view;
    }


    /**
     * 初始化RxBus
     */
    private void initRxbus() {
        registerRxBus(RxBusMessage.class, new Consumer<RxBusMessage>() {
            @Override
            public void accept(RxBusMessage rxBusMessage) throws Exception {
                getRxBusMassage(rxBusMessage);
            }
        });
    }

    /**
     * 注册RxBus
     *
     * @param eventType
     * @param action
     * @param <T>
     */
    public <T> void registerRxBus(Class<T> eventType, Consumer<T> action) {
        Disposable disposable = rxBus.doSubscribe(eventType, action, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                Log.e("RxBus", throwable.toString());
            }
        });
        rxBus.addSubscription(this, disposable);
    }

    private void initBar(){
        baseBarLayou=view.findViewById(R.id.base_bar_layout);
        if (baseBarLayou!=null) {
            baseTitle=findViewById(R.id.base_title);
            btnMore=findViewById(R.id.btn_more);
            btnAdd=findViewById(R.id.btn_add);
            baseTvRight=findViewById(R.id.tv_submit);
            btnBaseBarBack=findViewById(R.id.btn_base_bar_back);
            btnBaseBarBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();

        if (rxBus != null) {
            rxBus.unSubscribe(this);
        }
    }
}

