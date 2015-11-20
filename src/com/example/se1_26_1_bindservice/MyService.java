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
 * ��service����Ժ�service�����ٵ�������Ҫע��������߳�Ҳ��Ҫֹͣ����Ȼ�̻߳ᵥ��һֱ����
 */
public class MyService extends Service{

	int count  = 0;
	boolean flag = true;
	
	Binder myBinder = new MyBinder();;
	
	//����activity��service�����ݴ�������
	public class MyBinder extends Binder{
		public int getData(){
			return count;
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("�����Ѿ��󶨣�����");
		return myBinder;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("�����Ѿ���󶨣�����");
		flag = false;
		return true;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("�󶨳ɹ���������");
		
		new Thread(){
			public void run() {
				while(flag){
					try {
						System.out.println("service��ӡ��servcie���ڻ��:" + count);
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
		System.out.println("�����Ѿ����٣�����");
	}
}
