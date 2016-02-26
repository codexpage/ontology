package tju.edu.main;
import tju.edu.model.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				write(1);
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				write(2);
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				write(3);
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				write(4);
			}
		}).start();
	}

	private static void write(int id) {
		int t=100;
		temperaturesensor temp=new temperaturesensor();
		temperaturesensorDAO tempDAO=new temperaturesensorDAO();
		temp.setSensorid(id);//No.1 sensor
		
		while(t-->=0){
			int i=(int)(1+Math.random()*(10-1+1))+20;
			temp.setTemperature(i);
			tempDAO.insert(temp);
//			System.out.println(i);
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
