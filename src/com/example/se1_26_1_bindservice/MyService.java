/**
 * 
 */
package com.example.se1_26_1_bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * @author Administrator
 * 当service解绑以后service会销毁掉，但是要注意所起的线程也需要停止，不然线程会单独一直运行
 */
public class MyService extends Service{

	int count  = 0;
	boolean flag = true;
	
	Binder myBinder = new MyBinder();;
	
	//连接activity和service的数据传递桥梁
	public class MyBinder extends Binder{
		public int getData(){
			return count;
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("服务已经绑定！！！");
		return myBinder;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("服务已经解绑定！！！");
		flag = false;
		return true;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("绑定成功！！！！");
		
		new Thread(){
			public void run() {
				while(flag){
					try {
						System.out.println("service打印，servcie正在活动中:" + count);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count++;
				}
			};
		}.start();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("服务已经销毁！！！");
	}
}
