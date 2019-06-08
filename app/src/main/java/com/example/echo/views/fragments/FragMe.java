package com.example.echo.views.fragments;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.echo.R;
import com.example.echo.views.activities.UserInfoActivity;

public class FragMe extends Fragment{
    private static final String TAG = "FragMe";
    private FragBoard.OnFragmentInteractionListener mListener;
    private NestedScrollView nestedScrollView;
    private View infoBar = null;

    public FragMe() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_me, container, false);
        infoBar = view.findViewById(R.id.getInfo);
        infoBar.setOnClickListener(v -> {
            Intent toInfo = new Intent(getContext(), UserInfoActivity.class);
            startActivity(toInfo);
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }


    /**
     * 外部方法:
     * 滚动到顶部
     */
    public void scrollViewTo(int x, int y) {
        nestedScrollView.smoothScrollTo(x, y);
    }
}


