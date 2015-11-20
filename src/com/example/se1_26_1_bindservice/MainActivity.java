package com.example.se1_26_1_bindservice;

import com.example.se1_26_1_bindservice.MyService.MyBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * ��service
 * @author Administrator
 *
 */
public class MainActivity extends Activity {

	private Button bindButton;
	private Button getDataButton;
	private Button unbindButton;
	
	MyBinder myBinder;
	//�����ڲ������ڴ�service�л�ȡbinder����
	
	ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			System.out.println("--onServiceDisconnected--");
		}
		
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder binder) {
			System.out.println("--onServiceConnected--");
			 myBinder = (MyBinder) binder;
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bindButton = (Button) findViewById(R.id.bindButton);
		getDataButton = (Button) findViewById(R.id.getDataButton);
		unbindButton = (Button) findViewById(R.id.unbindButton);
		
		unbindButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				unbindService(conn);
			}
		});
		
		final Intent intent = new Intent(MainActivity.this, MyService.class);
		bindButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				bindService(intent, conn, BIND_AUTO_CREATE);
			}
		});
		
		getDataButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//myBinder.getData()���ݻ�ȡ����ŵ�����
				Toast.makeText(MainActivity.this, "��ǰ��������" + myBinder.getData() , Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
