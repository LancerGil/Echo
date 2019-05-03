package com.example.echo.views.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.echo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragHome extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private SharedViewModel model;

    public FragHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragHome newInstance(String param1, String param2) {
        FragHome fragment = new FragHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
//        itemSelector.setOnClickListener(item -> {
//            model.setFragLiveData(item);
//        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_home, container, false);
        SearchView searchView = view.findViewById(R.id.search_bar);
        //设置数据
        ArrayList<StuContent.StuItemContent> data = getData();

        //设置adapter及其数据
        StuAdapter stuAdapter = new StuAdapter(getContext(),data);
        RecyclerView rv_stu = view.findViewById(R.id.rv_stu_list);
        rv_stu.setAdapter(stuAdapter);
        rv_stu.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rv_stu.addItemDecoration(decoration);
        rv_stu.setHasFixedSize(true);
        rv_stu.setFocusable(false);

        return view;
    }

    private ArrayList<StuContent.StuItemContent> getData() {
        ArrayList<StuContent.StuItemContent> listitem = new ArrayList<>();
        listitem.add(new StuContent.StuItemContent("马云飞","2013020101",R.drawable.boy));
        listitem.add(new StuContent.StuItemContent("张小丽","2013020102",R.drawable.girl));
        listitem.add(new StuContent.StuItemContent("李军","2013020103",R.drawable.boy));
        listitem.add(new StuContent.StuItemContent("刘艳","2013020104",R.drawable.girl));
        listitem.add(new StuContent.StuItemContent("马云飞","2013020101",R.drawable.boy));
        listitem.add(new StuContent.StuItemContent("张小丽","2013020102",R.drawable.girl));
        listitem.add(new StuContent.StuItemContent("李军","2013020103",R.drawable.boy));
        listitem.add(new StuContent.StuItemContent("刘艳","2013020104",R.drawable.girl));
        listitem.add(new StuContent.StuItemContent("马云飞","2013020101",R.drawable.boy));
        listitem.add(new StuContent.StuItemContent("张小丽","2013020102",R.drawable.girl));
        listitem.add(new StuContent.StuItemContent("李军","2013020103",R.drawable.boy));
        listitem.add(new StuContent.StuItemContent("刘艳","2013020104",R.drawable.girl));
        return listitem;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
