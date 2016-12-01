package lilunmoxing2;


import java.net.*;
import java.io.*;
import java.util.Scanner;

class pushinfor {//函数：对话发送端口
	
	//把视频信息传输作为一个线程，剩下的都是函数
	static Socket server;
	
	public void push()//服务器4000端口是信息发送端口
	{
		try 
		{
			server = new Socket("localhost",4000);
			//BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			PrintWriter out = new PrintWriter(server.getOutputStream());
			BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请输入要发送的文字，按回车确认？"); 
			String str = wt.readLine();
			out.println(str);
			out.flush();
			//System.out.println(in.readLine());
			server.close();//关闭连接
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class pushfile {//函数
	
	//把视频信息传输作为一个线程，剩下的都是函数
	static Socket server;
	
	public void push()//服务器5000端口是文件发送端口
	{
		int length = 0;
        byte[] sendByte = null;
//        Socket socket = null;
        DataOutputStream dout = null;
        FileInputStream fin = null;
        try {
          try {
            server = new Socket();
            server.connect(new InetSocketAddress("localhost", 5000),10 * 1000);
//           System.out.println("连接主机端成功！");
            dout = new DataOutputStream(server.getOutputStream());//向外传dout
            final Scanner input;
			input = new Scanner(System.in);
            System.out.println("请输入要发送的文件路径？");
            String str = input.nextLine();
            File file = new File(str);
            
            fin = new FileInputStream(file);
            sendByte = new byte[1024];
            dout.writeUTF(file.getName());//把名字传进去
	            while((length = fin.read(sendByte, 0, sendByte.length))>0)
	            {
	                dout.write(sendByte,0,length);//传送
	                dout.flush();
	            }
            } 
          	catch (Exception e) {

            } 
          finally{
                if (dout != null)
                    dout.close();
                if (fin != null)
                    fin.close();
                if (server != null)
                    server.close();
        }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
	
}

class pushshipin extends Thread{//发送视频端口
	static Socket server;
	
	 
	
	
	
}

public class teststudent{
	
	public static void main(String[] args)throws Exception{
		while(true)
		{
			System.out.println("请输入你要做的操作？");
			System.out.println("1.传消息；2.传文件");
			Scanner input = new Scanner(System.in);
			String choice = input.nextLine();
			switch(choice){
			case "1":
				pushinfor x = new pushinfor();
				x.push();
				break;
			case "2":
				pushfile y = new pushfile();
				y.push();
				break;
			default:
			
				break;
			}
		}
		
	}
	
	
	
}





