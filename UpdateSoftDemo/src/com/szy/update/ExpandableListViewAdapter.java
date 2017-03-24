package com.szy.update;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
	private ArrayList<List<User>> list;
	private List<User> user;
	private Context context;

	public ExpandableListViewAdapter(Context mcontext,ArrayList<List<User>> list, List<User> user) {
		super();
		this.context=mcontext.getApplicationContext();
		this.list = list;
		this.user = user;
	}

	@Override
	public int getGroupCount() {
		return list.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return list.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return user.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return list.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		GroupHolder gHolder = null;
		if (convertView==null) {
			gHolder = new GroupHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.item, null);
			gHolder.mgrouptv = (TextView)convertView.findViewById(R.id.tv_item);
			convertView.setTag(gHolder);
		}else{
			gHolder=(GroupHolder)convertView.getTag();
		}
		gHolder.mgrouptv.setText(user.get(groupPosition).getAge()+"");
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ItemHolder itemHolder = null;
		if (convertView==null) {
			itemHolder = new ItemHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.child_item, null);
			itemHolder.mitemtv = (TextView)convertView.findViewById(R.id.tv_childitem);
			convertView.setTag(itemHolder);
		}else{
			itemHolder=(ItemHolder)convertView.getTag();
		}
		itemHolder.mitemtv.setText(list.get(groupPosition).get(childPosition).getName());
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	class GroupHolder{
		public TextView mgrouptv;
	}
	class ItemHolder{
		public TextView mitemtv;
	}
}
