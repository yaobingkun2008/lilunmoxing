package lilunmoxing3;


import java.net.*;
import java.util.Scanner;



import java.io.*;



class acceptinfor extends Thread{//接收学生信息
	
	
	private Socket server;
	public acceptinfor(Socket server)
	{
		this.server = server;
	}
	@Override
	public void run()//重写run方法，所有东西都在这里面运行
	{
		while(true)
		{
//			System.out.println("4444");
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(server.getInputStream()));
//				System.out.println("9999");
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			
			try {
//				System.out.println("999900");
				System.out.println(in.readLine());
//				System.out.println("99990");
				//System.out.println("5555");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}

class acceptfile extends Thread{//接收学生信息
	
	
	private Socket server;
	public acceptfile(Socket server)
	{
		this.server = server;
	}
	@Override
	public void run()//重写run方法，所有东西都在这里面运行
	{
//		System.out.println("6666");
		while(true)
		{
//		System.out.println("5555");
		 byte[] inputByte = null;
	        int length = 0;
	        DataInputStream din = null;
	        FileOutputStream fout = null;
	        try {
	            din = new DataInputStream(server.getInputStream());
	            String name = din.readUTF();
	            fout = new FileOutputStream(new File("G:\\"+name));//指定输出的目录
	            inputByte = new byte[1024];
	            System.out.println("正在接收文件，文件名字是：");
	            System.out.println(name);//name是文件名
	            while (true) {
	                if (din != null) {
	                    length = din.read(inputByte, 0, inputByte.length);
	                }
	                if (length < 0) {
	                    break;
	                    
	                }
	                System.out.println(length);
	                fout.write(inputByte, 0, length);
	                fout.flush();
	                if (length < 1024) {
	                    break;
	                }
	            }
	            System.out.println("完成接收!");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } 
	        
	        finally {
	            if (fout != null)
					try {
						fout.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	        
		}
	}
}




public class testteacher {
	public static void main(String[] args)throws Exception{
		Socket server = new Socket("localhost",4000);
		Socket server2 = new Socket("localhost",5000);
//		System.out.println("shit!");
		acceptfile x2 = new acceptfile(server2);
		x2.start();
//		System.out.println("shit2!");
		acceptinfor x1 = new acceptinfor(server);
		x1.start();
	}
}
