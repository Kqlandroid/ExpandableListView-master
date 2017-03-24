package com.szy.update;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;

/**
 *@author coolszy
 *@date 2012-4-26
 *@blog http://blog.92coding.com
 */
public class MainActivity extends Activity
{
	private Button btn;
	private Button send;
	private Socket socket;
	private Button updateBtn ;
	private ExpandableListView mExpandableListView;
	private ExpandableListViewAdapter mAdapter;
	private ArrayList<List<User>> list;
	private List<User> ulist;
	private User u;
	private TextView tvsort;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		updateBtn = (Button) findViewById(R.id.btnUpdate);
		btn =(Button)findViewById(R.id.btn);
		send=(Button)findViewById(R.id.send);
		tvsort=(TextView)findViewById(R.id.btn_sort);
		mExpandableListView = (ExpandableListView)findViewById(R.id.chapter_expandableListView);
		initlist();
		Initdata();
		
		
	}

	private void initlist() {
		ulist=new ArrayList<User>();
		list=new ArrayList<List<User>>();
		
		u=new User("a", 0);
		ulist.add(u);
		u=new User("b", 1);
		ulist.add(u);
		u=new User("c", 2);
		ulist.add(u);
		u=new User("d", 3);
		ulist.add(u);
		u=new User("e", 4);
		ulist.add(u);
		u=new User("f", 5);
		ulist.add(u);
		u=new User("g", 6);
		ulist.add(u);
		u=new User("h", 7);
		ulist.add(u);
		u=new User("i", 8);
		ulist.add(u);
		u=new User("j", 9);
		ulist.add(u);
		u=new User("n", 10);
		ulist.add(u);
		u=new User("l", 11);
		ulist.add(u);
		u=new User("q", 12);
		ulist.add(u);
		u=new User("w", 13);
		ulist.add(u);
		u=new User("r", 14);
		ulist.add(u);
		u=new User("t", 15);
		ulist.add(u);
		for (int i = 0; i < ulist.size(); i++) {
			list.add(ulist);
		}
		
		mAdapter = new ExpandableListViewAdapter(MainActivity.this, list, ulist);
		mExpandableListView.setAdapter(mAdapter);
	
	}

	private void Initdata() {
		updateBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UpdateManager manager = new UpdateManager(MainActivity.this);
				// 检查软件更新
				manager.checkUpdate();
			}
		});
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(){
					public void run() {
						try {
							socket=new Socket("172.16.30.138",9999);
							System.out.println("建立连接"+socket);
						} catch (Exception e) {
							e.printStackTrace();
						}

					};
				}.start();
			}
		});
		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				wirte();
			}
		});
		tvsort.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Collections.reverse(ulist);
				mAdapter.notifyDataSetChanged();
				
			}
		});
	}

	private void wirte(){
		try {
			DataOutputStream dataInputStream=new DataOutputStream(socket.getOutputStream());
			dataInputStream.writeUTF("发送一条消息给服务器");
			System.out.println("hello");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}