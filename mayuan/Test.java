package mayuan1;

import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class Test {
	static int num;
	static ArrayList<String> question;
	static LinkedHashMap<Integer,String> error;
	static Scanner in;
	
	
	public static void main(String[] args) {
		
		try {
			in = new Scanner(System.in);
			String q=" 0 1 ";
			System.out.println("��֪�������򲻶������������У�飬ѡ��������𰸺�س���ֻУ��abcd��ABCD���ж���ʱ����0��1��س�,�涨1Ϊ�ԣ�0Ϊ��");
			selectChapter();
			readFile(num);
			startAnswer();
			endAnswer();
		} catch (Exception e) {
			in.close();
		}
	}

	private static void endAnswer() {
		System.out.println("");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("");
		System.out.println("��ɴ���,һ������"+error.size()+"�⣬�����Ǵ�������");
		for (Entry<Integer, String> en : error.entrySet()) {
			System.out.println(question.get(en.getKey()));
			System.out.println("��Ĵ��ǣ�"+en.getValue());
		}
		
	}

	private static void startAnswer() {
		System.out.println("������Ŀ��" + question.size() + "��");
		error=new LinkedHashMap<>();
		String ans="",ques;
		String[] str = {"",""};
		for (int i = 0; i < question.size(); i++) {
			ques=question.get(i);
			str=splitQeustionAns(ques);
			System.out.println(i + 1 + " . " + str[0]);
			if(!str[0].contains("A:"))
				System.out.print("�жϡ���");
			else {
				if(str[1].length()>1)
					System.out.print("��ѡ����");
				else
					System.out.print("��ѡ����");
			}
			
			System.out.print("������𰸺�س�: ");
			ans=in.nextLine();
			
			
			if(!judgeAns(ans,str[1])) {
				error.put(i, ans);
			}
		}
		
	}

	private static boolean judgeAns(String a, String b) {
		//b.replace("��", "0");
		//b.replace("��", "1");
		if(b.equals("��"))
			b="1";
		if(b.equals("��"))
			b="0";
		a=a.toUpperCase();
		boolean[] aa=new boolean[100];
		boolean[] bb=new boolean[100];
		char[] aCh=a.toCharArray();
		char[] bCh=b.toCharArray();
		for (int i = 0; i < bCh.length; i++) {
			char c = bCh[i];
			if((c>='A'&&c<='D')||c=='0'||c=='1')
				bb[c]=true;
		}
		for (int i = 0; i < aCh.length; i++) {
			char c = aCh[i];
			if((c>='A'&&c<='D')||c=='0'||c=='1')
				aa[c]=true;
		}
		return aa['A']==bb['A']&&aa['B']==bb['B']&&aa['C']==bb['C']&&aa['D']==bb['D']&&aa['1']==bb['1']&&aa['0']==bb['0'];
	}

	private static String[] splitQeustionAns(String ques) {
		String[] str = {"",""};
		char[] ch = ques.toCharArray();
		int i=0;
		for (  i = ch.length-1; i >=0; i--) {
			if(ch[i]==':')
				break;
		}
		str[0]=ques.substring(0,i-4);
		str[1]=ques.substring(i+1).trim();
		return str;
	}

	private static void readFile(int n) {
		File f = new File(n + ".txt");
		question = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line;
			StringBuilder temp = new StringBuilder();
			try {
				while ((line = br.readLine()) != null) {
					if (line.startsWith("��ȷ��")) {
						temp.append(line);
						temp.append('\n');
						question.add(temp.toString());
						temp.delete(0, temp.length());
					} else {
						temp.append(line);
						temp.append('\n');
					}
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static void selectChapter() {

		System.out.println("0.����");
		System.out.println("1.��һ��");
		System.out.println("2.�ڶ���");
		System.out.println("3.������");
		System.out.println("4.������");
		System.out.println("5.������");
		System.out.println("6.������");
		System.out.println("7.������");
		System.out.println("����������Ե��½ڣ��������ֺ�س����ɣ�");
		
		while (true) {

			try {
				num = in.nextInt();
				String t = in.nextLine();
				if (num >= 0 && num <= 7) {
					break;
				} else {
					System.out.println("�����������Ҫ��0~7֮��");
				}
			} catch (InputMismatchException e) {
				System.out.println("���������ֺ󲢻س�");
				String t = in.next();
			}
		}
		

	}
}
