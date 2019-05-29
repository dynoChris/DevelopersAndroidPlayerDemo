package com.oliverstudio.developersandroidplayer.presentation.main_screen.home_fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.utils.androidx_moxy.MvpAppCompatFragment;

public class HomeFragment extends MvpAppCompatFragment implements HomeView {

    //tags
    public static final String FRAGMENT_TAG = "fragment_home";

    @InjectPresenter
    HomePresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
