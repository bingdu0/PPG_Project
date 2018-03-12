package com.ppg.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ppg.R;
import com.ppg.bean.RxBusMessage;
import com.ppg.rxjava.RxBus;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Create by Donny.
 * 2017/4/9
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IView {
    public TextView baseTitle;
    public ImageButton btnBaseBarBack;
    public ImageButton btnMore;
    public RelativeLayout baseBarLayou;
    public TextView baseTvRight;

    protected View view;
    protected P mPresenter;
    protected RxBus rxBus;
    protected Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), getLayoutId(), null);
        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        unbinder = ButterKnife.bind(this, view);
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

    private void initBar() {
        baseBarLayou = view.findViewById(R.id.base_bar_layout);
        if (baseBarLayou != null) {
            baseTitle = view.findViewById(R.id.base_title);
            btnMore = view.findViewById(R.id.btn_more);
            baseTvRight = view.findViewById(R.id.tv_submit);
            btnBaseBarBack = view.findViewById(R.id.btn_base_bar_back);
            btnBaseBarBack.setVisibility(View.GONE);
        }
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


    @Override
    public void onPause() {
        super.onPause();
        if (isRegisterEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
        if (rxBus != null) {
            rxBus.unSubscribe(this);
        }
        if (unbinder != null) {
            unbinder.unbind();
        }


    }


    protected boolean isRegisterEventBus() {
        return false;
    }

}
