package lilunmoxing2;


import java.net.*;
import java.io.*;
import java.util.Scanner;

class pushinfor {//�������Ի����Ͷ˿�
	
	//����Ƶ��Ϣ������Ϊһ���̣߳�ʣ�µĶ��Ǻ���
	static Socket server;
	
	public void push()//������4000�˿�����Ϣ���Ͷ˿�
	{
		try 
		{
			server = new Socket("localhost",4000);
			//BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			PrintWriter out = new PrintWriter(server.getOutputStream());
			BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("������Ҫ���͵����֣����س�ȷ�ϣ�"); 
			String str = wt.readLine();
			out.println(str);
			out.flush();
			//System.out.println(in.readLine());
			server.close();//�ر�����
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

class pushfile {//����
	
	//����Ƶ��Ϣ������Ϊһ���̣߳�ʣ�µĶ��Ǻ���
	static Socket server;
	
	public void push()//������5000�˿����ļ����Ͷ˿�
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
//           System.out.println("���������˳ɹ���");
            dout = new DataOutputStream(server.getOutputStream());//���⴫dout
            final Scanner input;
			input = new Scanner(System.in);
            System.out.println("������Ҫ���͵��ļ�·����");
            String str = input.nextLine();
            File file = new File(str);
            
            fin = new FileInputStream(file);
            sendByte = new byte[1024];
            dout.writeUTF(file.getName());//�����ִ���ȥ
	            while((length = fin.read(sendByte, 0, sendByte.length))>0)
	            {
	                dout.write(sendByte,0,length);//����
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

class pushshipin extends Thread{//������Ƶ�˿�
	static Socket server;
	
	 
	
	
	
}

public class teststudent{
	
	public static void main(String[] args)throws Exception{
		while(true)
		{
			System.out.println("��������Ҫ���Ĳ�����");
			System.out.println("1.����Ϣ��2.���ļ�");
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





