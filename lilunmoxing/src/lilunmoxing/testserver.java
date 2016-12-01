package lilunmoxing;

import java.net.*;
import java.util.Scanner;
import java.io.*;

class jieshouxueshengxinxi extends Thread{//拥有4000、5000两个端口
	private Socket student;//
	private Socket teacher;//要求老师的
	public jieshouxueshengxinxi(Socket x,Socket c)
	{
		this.teacher = x;
		this.student = c;
	}
	@Override
	public void run()//重写run方法，所有东西都在这里面运行
	{
		try 
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(student.getInputStream()));//得到的输入服务器的值
			PrintWriter out = new PrintWriter(teacher.getOutputStream());//返回到老师端的东西
			String str = in.readLine();				//x++;
			out.println(student.getPort()+"说:"+str);//返回到老师端
			out.flush();
			//student.close();//结束语客户端连接先不要它
		}
		catch(IOException e)
		{
			
		}
		finally{//干什么用的？
			
		}
	}
}


class jieshouxueshengxinxibingfa extends Thread{//并发的接收学生信息
	
	
	private ServerSocket server;//
	private Socket teacher;//要求老师的
	public jieshouxueshengxinxibingfa(Socket teacher,ServerSocket server)
	{
		this.teacher = teacher;
		this.server = server;
	}
	@Override
	public void run()//重写run方法，所有东西都在这里面运行
	{
		while(true)//这种形式很重要！
		{
			jieshouxueshengxinxi mu;
			try {
				mu = new jieshouxueshengxinxi(teacher,server.accept());
				mu.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//一旦收到accept，就建立一个新线程，对于该客户端
			//启动这个线程//server.accept返回什么？//serversocket使用它的accept方法监听这个端口的入站链接。accept会一直阻塞，直到一个客户端尝试建立连接
		}
	}
}

class jieshouxueshengwenjian extends Thread{//拥有4000、5000两个端口
	private Socket student;//
	private Socket teacher;//老师端口，将文件传向老师端口
	public jieshouxueshengwenjian(Socket x,Socket c)
	{
		this.teacher = x;
		this.student = c;
	}
	@Override
	public void run()//重写run方法，所有东西都在这里面运行
	{
		 byte[] inputByte = null;
	        int length = 0;
	        int length2 = 0;
	        DataInputStream din = null;
	        DataOutputStream dout = null;
	        FileOutputStream fout = null;
	        byte[] sendByte = null;
	        FileInputStream fin = null;
	        String name = "";
	        try {//在服务器端存储一个副本，然后再发给同学
	        	
	        	//dout = new DataOutputStream(teacher.getOutputStream());//向老师端输出
	        	
	        	
	            din = new DataInputStream(student.getInputStream());//得到输出的端口
	            name = din.readUTF();
	            fout = new FileOutputStream(new File("H:\\"+name));//指定输出的目录
	            inputByte = new byte[1024];
	            System.out.println("服务器正在接收文件，文件名字是：");
	            System.out.println(name);
	            while (true) {//接收文件？
	                if (din != null) {
	                    length = din.read(inputByte, 0, inputByte.length);
	                }
	                if (length == -1) {
	                    break;
	                }
	               // System.out.println(length);
	                fout.write(inputByte, 0, length);
	                fout.flush();
//	                dout.write(inputByte, 0, length);
//	                dout.flush();
	            }
	            System.out.println("服务器完成接收!");
	            //接下来把文件发到客户端
	            try {
	                try {
	                   
	                 // System.out.println("连接主机端成功！");
	                  dout = new DataOutputStream(teacher.getOutputStream());
	                 // final Scanner input;
	      			//input = new Scanner(System.in);
	                  System.out.println("1");
	                  
	                  //String str = input.nextLine();
	                  String i = "H:\\" + name;
	                  File file = new File(i);
	                  
	                  fin = new FileInputStream(file);
	                  sendByte = new byte[1024];
	                  dout.writeUTF(file.getName());//把名字传进去
	                  System.out.println("2");
	             
	                  while((length2 = fin.read(sendByte, 0, sendByte.length))>0){
	                      dout.write(sendByte,0,length2);
	                      System.out.println("3");
	                      dout.flush();
	                  }
	                  System.out.println("4");
	                  } catch (Exception e) {

	                  } 
	                /*
	                finally{
	                      if (dout != null)
	                          dout.close();
	                      if (fin != null)
	                          fin.close();
	              }
	              */
	              } catch (Exception e) {
	                  e.printStackTrace();
	              }
	            
	            
	            
	            
	            
	            
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } 
	        /*
	        finally {
	            if (dout != null)
					try {
						dout.close();
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
	            if (din != null)
					try {
						din.close();
					} catch (IOException e) {
						
						e.printStackTrace();
					}
	            
	        }
	        */
	}
}


class jieshouxueshengwenjianbingfa extends Thread{//并发的接收学生信息
	
	
	private ServerSocket server;//
	private Socket teacher;//要求老师的
	public jieshouxueshengwenjianbingfa(Socket teacher,ServerSocket server)
	{
		this.teacher = teacher;
		this.server = server;
	}
	@Override
	public void run()//重写run方法，所有东西都在这里面运行
	{
		while(true)//这种形式很重要！
		{
			jieshouxueshengwenjian mu;
			try {
				mu = new jieshouxueshengwenjian(teacher,server.accept());
				mu.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//一旦收到accept，就建立一个新线程，对于该客户端
			//启动这个线程//server.accept返回什么？//serversocket使用它的accept方法监听这个端口的入站链接。accept会一直阻塞，直到一个客户端尝试建立连接
		}
	}
}








public class testserver{	
	
	public static void main(String[] args)throws IOException{//初始化多个端口，用来干不同事情？
		ServerSocket server = new ServerSocket(4000);//学生传送信息的端口和老师接收信息的端口
		ServerSocket server2 = new ServerSocket(5000);//学生传送文件的端口
//		ServerSocket server3 = new ServerSocket(6000);
//		ServerSocket server4 = new ServerSocket(7000);
		System.out.println("服务器端已经打开，正在运行。。。");
		Socket tea = null;
		Socket tea2 = null;
		System.out.println("等待老师接入。。。。。");
		while(tea==null)
		{
			tea = server.accept();
		}
		while(tea2==null)
		{
			tea2 = server2.accept();
		}
		System.out.println("老师已经接入。。。。。");
		System.out.println("正式开始运行。。。。。");
		jieshouxueshengxinxibingfa x1 = new jieshouxueshengxinxibingfa(tea,server);
		x1.start();
		jieshouxueshengwenjianbingfa x2 = new jieshouxueshengwenjianbingfa(tea2,server2);
		x2.start();
//		while(true)//这种形式很重要！
//		{
//			jieshouxueshengxinxi mu = new jieshouxueshengxinxi(tea,server.accept());//一旦收到accept，就建立一个新线程，对于该客户端
//			mu.start();//启动这个线程//server.accept返回什么？//serversocket使用它的accept方法监听这个端口的入站链接。accept会一直阻塞，直到一个客户端尝试建立连接
//		}
	}
}
