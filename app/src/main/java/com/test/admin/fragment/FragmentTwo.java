package com.test.admin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.test.admin.R;
import com.test.admin.activity.PersonDetail;
import com.test.admin.adapter.PermissionAdapter;
import com.test.admin.bean.AsPermissionApplying;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by hc6 on 2016/11/16.
 */

public class FragmentTwo extends Fragment {

    private ListView lv_permission;
    private PermissionAdapter mAdapter;
    private List<AsPermissionApplying> asPermissionApplyingList = new ArrayList<AsPermissionApplying>();

    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_two, container, false);

        lv_permission = (ListView) view.findViewById(R.id.lv_permission);

        BmobQuery<AsPermissionApplying> bmobQuery = new BmobQuery<AsPermissionApplying>();
        bmobQuery.addQueryKeys("perIdentity,perSupplement");
        bmobQuery.findObjects(new FindListener<AsPermissionApplying>() {
            @Override
            public void done(List<AsPermissionApplying> list, BmobException e) {

                if (e == null) {
                    asPermissionApplyingList.addAll(list);

                    mAdapter = new PermissionAdapter(getActivity(),asPermissionApplyingList);

                    lv_permission.setAdapter(mAdapter);
                }
            }
        });


        lv_permission.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), PersonDetail.class));
            }
        });

        return view;
    }

}
