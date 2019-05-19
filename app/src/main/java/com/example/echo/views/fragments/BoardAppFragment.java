package com.example.echo.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.echo.R;
import com.example.echo.adapters.AppRVAdapter;
import com.example.echo.entities.AppContentInList;
import com.example.echo.entities.AppContentInList.DummyAppInListItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class BoardAppFragment extends Fragment {

    private static final String TAG = "BoardAppFragment";

    private static final String ARG_ITEM_INDEX = "item_index";
    private int mItemIndex = 1;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BoardAppFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static BoardAppFragment newInstance(int itemIndex) {
        BoardAppFragment fragment = new BoardAppFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ITEM_INDEX, itemIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mItemIndex = getArguments().getInt(ARG_ITEM_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_boardapp_list, container, false);

        // Set the adapter
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new AppRVAdapter(AppContentInList.ITEMS, mItemIndex, mListener));
        if (mItemIndex >= 0) {
            //设置recyclerView不滚动，从而恢复scrollview惯性滚动
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            recyclerView.setLayoutManager(layoutManager);
        }

        return view;
    }

    public void scrollResyclerViewTo(int position) {
        recyclerView.smoothScrollToPosition(position);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyAppInListItem item);
    }
}
