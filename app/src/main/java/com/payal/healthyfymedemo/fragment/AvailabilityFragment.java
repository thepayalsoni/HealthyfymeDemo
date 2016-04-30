package com.payal.healthyfymedemo.fragment;

import android.content.Context;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.decoration.ItemShadowDecorator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.SimpleListDividerDecorator;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;
import com.payal.healthyfymedemo.R;
import com.payal.healthyfymedemo.pojo.SessionDetails;
import com.payal.healthyfymedemo.pojo.Sessions;
import com.payal.healthyfymedemo.utility.Utils;
import com.payal.healthyfymedemo.views.ExpandableItemIndicator;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by payal on 25/4/16.
 */
public class AvailabilityFragment extends Fragment implements RecyclerViewExpandableItemManager.OnGroupCollapseListener,
        RecyclerViewExpandableItemManager.OnGroupExpandListener {
    private Sessions sessionData;
    private RecyclerView.Adapter mWrappedAdapter;
    private RecyclerViewExpandableItemManager mRecyclerViewExpandableItemManager;
    private static final String SAVED_STATE_EXPANDABLE_ITEM_MANAGER = "RecyclerViewExpandableItemManager";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;


    public static AvailabilityFragment newInstance(Sessions sessionData) {
        AvailabilityFragment fragment = new AvailabilityFragment();
        Bundle args = new Bundle();
        args.putSerializable(Utils.SESSION_DATA, sessionData);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_data, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sessionData = (Sessions) getArguments().getSerializable(Utils.SESSION_DATA);
        mLayoutManager = new LinearLayoutManager(getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(mLayoutManager);

        final Parcelable eimSavedState = (savedInstanceState != null) ? savedInstanceState.getParcelable(SAVED_STATE_EXPANDABLE_ITEM_MANAGER) : null;
        mRecyclerViewExpandableItemManager = new RecyclerViewExpandableItemManager(eimSavedState);
        mRecyclerViewExpandableItemManager.setOnGroupExpandListener(this);
        mRecyclerViewExpandableItemManager.setOnGroupCollapseListener(this);

        //adapter
        final MyAdapter myItemAdapter = new MyAdapter(getActivity().getApplicationContext(), sessionData);

        mWrappedAdapter = mRecyclerViewExpandableItemManager.createWrappedAdapter(myItemAdapter);
        recyclerView.setAdapter(mWrappedAdapter);
        recyclerView.setHasFixedSize(false);

        if (!supportsViewElevation()) {

            recyclerView.addItemDecoration(new ItemShadowDecorator((NinePatchDrawable) ContextCompat.getDrawable(getContext(), R.drawable.material_shadow_z1)));
        }
        recyclerView.addItemDecoration(new SimpleListDividerDecorator(ContextCompat.getDrawable(getContext(), R.drawable.list_divider_h), true));

        mRecyclerViewExpandableItemManager.attachRecyclerView(recyclerView);

    }

    private boolean supportsViewElevation() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save current state to support screen rotation, etc...
        if (mRecyclerViewExpandableItemManager != null) {
            outState.putParcelable(
                    SAVED_STATE_EXPANDABLE_ITEM_MANAGER,
                    mRecyclerViewExpandableItemManager.getSavedState());
        }
    }

    @Override
    public void onDestroyView() {
        if (mRecyclerViewExpandableItemManager != null) {
            mRecyclerViewExpandableItemManager.release();
            mRecyclerViewExpandableItemManager = null;
        }

        if (recyclerView != null) {
            recyclerView.setAdapter(null);
            recyclerView = null;
        }

        if (mWrappedAdapter != null) {
            WrapperAdapterUtils.releaseAll(mWrappedAdapter);
            mWrappedAdapter = null;
        }
        mLayoutManager = null;

        super.onDestroyView();
    }


    static abstract class MyBaseViewHolder extends AbstractExpandableItemViewHolder {
        TextView tvSessionType, tvSLotsAvailable;
        public ExpandableItemIndicator mIndicator;


        public MyBaseViewHolder(View itemView) {
            super(itemView);
            tvSessionType = (TextView) itemView.findViewById(R.id.tv_sessionType);
            tvSLotsAvailable = (TextView) itemView.findViewById(R.id.tv_slotsAvailable);
            mIndicator = (ExpandableItemIndicator) itemView.findViewById(R.id.expand_icon);
        }
    }

    static class MyGroupViewHolder extends MyBaseViewHolder {
        public MyGroupViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class MyChildViewHolder extends MyBaseViewHolder {
        TextView tvTimings;

        public MyChildViewHolder(View itemView) {
            super(itemView);
            tvTimings = (TextView) itemView.findViewById(R.id.tv_timings);
        }
    }

    static class MyAdapter extends AbstractExpandableItemAdapter<MyGroupViewHolder, MyChildViewHolder> {
        Sessions sessions;

        LinkedHashMap<String, ArrayList<SessionDetails>> data;
        Context mContext;

        public MyAdapter(Context mContext, Sessions sessions) {
            setHasStableIds(true); // this is required for expandable feature.
            this.sessions = sessions;
            this.mContext = mContext;
            data = new LinkedHashMap<>();
            data.put("Morning", sessions.getMorningSessionArray());
            data.put("Afternoon", sessions.getAfternoonSessionArray());
            data.put("Evening", sessions.getEveningSessionArray());

        }

        @Override
        public int getChildItemViewType(int a, int b) {
            return 0;
        }

        @Override
        public int getGroupItemViewType(int b) {
            return 0;
        }


        @Override
        public int getGroupCount() {
            return data.size();
        }

        @Override
        public int getChildCount(int groupPosition) {
            return data.get(getKeyAtPosition(data, groupPosition)).size();
        }

        private String getKeyAtPosition(LinkedHashMap<String, ArrayList<SessionDetails>> hMap, int position) {

            return (String) hMap.keySet().toArray()[position];
        }

        @Override
        public long getGroupId(int groupPosition) {
            // This method need to return unique value within all group items.
            return getKeyAtPosition(data, groupPosition).hashCode();
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            // This method need to return unique value within the group.
            return data.get(getKeyAtPosition(data, groupPosition)).get(childPosition).hashCode();
        }

        @Override
        public MyGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group_item_for_expandable_minimal, parent, false);
            return new MyGroupViewHolder(v);
        }

        @Override
        public MyChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_child_item_for_expandable_minimal, parent, false);
            return new MyChildViewHolder(v);
        }

        @Override
        public void onBindGroupViewHolder(MyGroupViewHolder holder, int groupPosition, int viewType) {
            holder.tvSessionType.setText(getKeyAtPosition(data, groupPosition));
            holder.tvSLotsAvailable.setText(mContext.getResources().getString(R.string.slots_avalable, data.get(getKeyAtPosition(data, groupPosition)).size() ));

            // set background resource (target view ID: container)
            final int expandState = holder.getExpandStateFlags();

            if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_UPDATED) != 0) {
                boolean isExpanded;
                boolean animateIndicator = ((expandState & Expandable.STATE_FLAG_HAS_EXPANDED_STATE_CHANGED) != 0);

                if ((expandState & Expandable.STATE_FLAG_IS_EXPANDED) != 0) {
                    isExpanded = true;
                } else {
                    isExpanded = false;
                }

                holder.mIndicator.setExpandedState(isExpanded, animateIndicator);
            }

        }

        @Override
        public void onBindChildViewHolder(MyChildViewHolder holder, int groupPosition, int childPosition, int viewType) {
            holder.tvTimings.setText(Utils.getTime(data.get(getKeyAtPosition(data, groupPosition)).get(childPosition).getStartTime()) + " - " + Utils.getTime(data.get(getKeyAtPosition(data, groupPosition)).get(childPosition).getEndTime()));
            if (data.get(getKeyAtPosition(data, groupPosition)).get(childPosition).getIsBooked() || data.get(getKeyAtPosition(data, groupPosition)).get(childPosition).getIsExpired())
                holder.tvTimings.setBackgroundColor(mContext.getResources().getColor(R.color.disable_color));
        }

        @Override
        public boolean onCheckCanExpandOrCollapseGroup(MyGroupViewHolder holder, int groupPosition, int x, int y, boolean expand) {

            return true;
        }


    }


    @Override
    public void onGroupCollapse(int groupPosition, boolean fromUser) {
    }

    @Override
    public void onGroupExpand(int groupPosition, boolean fromUser) {
        if (fromUser) {
            adjustScrollPositionOnGroupExpanded(groupPosition);
        }
    }

    private void adjustScrollPositionOnGroupExpanded(int groupPosition) {
        int childItemHeight = getActivity().getResources().getDimensionPixelSize(R.dimen.list_item_height);
        int topMargin = (int) (getActivity().getResources().getDisplayMetrics().density * 16);

        mRecyclerViewExpandableItemManager.scrollToGroup(groupPosition, childItemHeight, topMargin, topMargin);
    }
    private interface Expandable extends ExpandableItemConstants {
    }


}
