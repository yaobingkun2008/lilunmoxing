package lilunmoxing;

import java.net.*;
import java.util.Scanner;
import java.io.*;

class jieshouxueshengxinxi extends Thread{//ӵ��4000��5000�����˿�
	private Socket student;//
	private Socket teacher;//Ҫ����ʦ��
	public jieshouxueshengxinxi(Socket x,Socket c)
	{
		this.teacher = x;
		this.student = c;
	}
	@Override
	public void run()//��дrun���������ж�����������������
	{
		try 
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(student.getInputStream()));//�õ��������������ֵ
			PrintWriter out = new PrintWriter(teacher.getOutputStream());//���ص���ʦ�˵Ķ���
			String str = in.readLine();				//x++;
			out.println(student.getPort()+"˵:"+str);//���ص���ʦ��
			out.flush();
			//student.close();//������ͻ��������Ȳ�Ҫ��
		}
		catch(IOException e)
		{
			
		}
		finally{//��ʲô�õģ�
			
		}
	}
}


class jieshouxueshengxinxibingfa extends Thread{//�����Ľ���ѧ����Ϣ
	
	
	private ServerSocket server;//
	private Socket teacher;//Ҫ����ʦ��
	public jieshouxueshengxinxibingfa(Socket teacher,ServerSocket server)
	{
		this.teacher = teacher;
		this.server = server;
	}
	@Override
	public void run()//��дrun���������ж�����������������
	{
		while(true)//������ʽ����Ҫ��
		{
			jieshouxueshengxinxi mu;
			try {
				mu = new jieshouxueshengxinxi(teacher,server.accept());
				mu.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//һ���յ�accept���ͽ���һ�����̣߳����ڸÿͻ���
			//��������߳�//server.accept����ʲô��//serversocketʹ������accept������������˿ڵ���վ���ӡ�accept��һֱ������ֱ��һ���ͻ��˳��Խ�������
		}
	}
}

class jieshouxueshengwenjian extends Thread{//ӵ��4000��5000�����˿�
	private Socket student;//
	private Socket teacher;//��ʦ�˿ڣ����ļ�������ʦ�˿�
	public jieshouxueshengwenjian(Socket x,Socket c)
	{
		this.teacher = x;
		this.student = c;
	}
	@Override
	public void run()//��дrun���������ж�����������������
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
	        try {//�ڷ������˴洢һ��������Ȼ���ٷ���ͬѧ
	        	
	        	//dout = new DataOutputStream(teacher.getOutputStream());//����ʦ�����
	        	
	        	
	            din = new DataInputStream(student.getInputStream());//�õ�����Ķ˿�
	            name = din.readUTF();
	            fout = new FileOutputStream(new File("H:\\"+name));//ָ�������Ŀ¼
	            inputByte = new byte[1024];
	            System.out.println("���������ڽ����ļ����ļ������ǣ�");
	            System.out.println(name);
	            while (true) {//�����ļ���
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
	            System.out.println("��������ɽ���!");
	            //���������ļ������ͻ���
	            try {
	                try {
	                   
	                 // System.out.println("���������˳ɹ���");
	                  dout = new DataOutputStream(teacher.getOutputStream());
	                 // final Scanner input;
	      			//input = new Scanner(System.in);
	                  System.out.println("1");
	                  
	                  //String str = input.nextLine();
	                  String i = "H:\\" + name;
	                  File file = new File(i);
	                  
	                  fin = new FileInputStream(file);
	                  sendByte = new byte[1024];
	                  dout.writeUTF(file.getName());//�����ִ���ȥ
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


class jieshouxueshengwenjianbingfa extends Thread{//�����Ľ���ѧ����Ϣ
	
	
	private ServerSocket server;//
	private Socket teacher;//Ҫ����ʦ��
	public jieshouxueshengwenjianbingfa(Socket teacher,ServerSocket server)
	{
		this.teacher = teacher;
		this.server = server;
	}
	@Override
	public void run()//��дrun���������ж�����������������
	{
		while(true)//������ʽ����Ҫ��
		{
			jieshouxueshengwenjian mu;
			try {
				mu = new jieshouxueshengwenjian(teacher,server.accept());
				mu.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//һ���յ�accept���ͽ���һ�����̣߳����ڸÿͻ���
			//��������߳�//server.accept����ʲô��//serversocketʹ������accept������������˿ڵ���վ���ӡ�accept��һֱ������ֱ��һ���ͻ��˳��Խ�������
		}
	}
}








public class testserver{	
	
	public static void main(String[] args)throws IOException{//��ʼ������˿ڣ������ɲ�ͬ���飿
		ServerSocket server = new ServerSocket(4000);//ѧ��������Ϣ�Ķ˿ں���ʦ������Ϣ�Ķ˿�
		ServerSocket server2 = new ServerSocket(5000);//ѧ�������ļ��Ķ˿�
//		ServerSocket server3 = new ServerSocket(6000);
//		ServerSocket server4 = new ServerSocket(7000);
		System.out.println("���������Ѿ��򿪣��������С�����");
		Socket tea = null;
		Socket tea2 = null;
		System.out.println("�ȴ���ʦ���롣��������");
		while(tea==null)
		{
			tea = server.accept();
		}
		while(tea2==null)
		{
			tea2 = server2.accept();
		}
		System.out.println("��ʦ�Ѿ����롣��������");
		System.out.println("��ʽ��ʼ���С���������");
		jieshouxueshengxinxibingfa x1 = new jieshouxueshengxinxibingfa(tea,server);
		x1.start();
		jieshouxueshengwenjianbingfa x2 = new jieshouxueshengwenjianbingfa(tea2,server2);
		x2.start();
//		while(true)//������ʽ����Ҫ��
//		{
//			jieshouxueshengxinxi mu = new jieshouxueshengxinxi(tea,server.accept());//һ���յ�accept���ͽ���һ�����̣߳����ڸÿͻ���
//			mu.start();//��������߳�//server.accept����ʲô��//serversocketʹ������accept������������˿ڵ���վ���ӡ�accept��һֱ������ֱ��һ���ͻ��˳��Խ�������
//		}
	}
}
