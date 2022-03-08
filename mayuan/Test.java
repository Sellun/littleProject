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
			System.out.println("须知：本程序不对输入做过多的校验，选择题输入答案后回车，只校验abcd，ABCD；判断题时输入0或1后回车,规定1为对，0为错");
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
		System.out.println("完成答题,一共错了"+error.size()+"题，下面是错题详情");
		for (Entry<Integer, String> en : error.entrySet()) {
			System.out.println(question.get(en.getKey()));
			System.out.println("你的答案是："+en.getValue());
		}
		
	}

	private static void startAnswer() {
		System.out.println("本次题目共" + question.size() + "个");
		error=new LinkedHashMap<>();
		String ans="",ques;
		String[] str = {"",""};
		for (int i = 0; i < question.size(); i++) {
			ques=question.get(i);
			str=splitQeustionAns(ques);
			System.out.println(i + 1 + " . " + str[0]);
			if(!str[0].contains("A:"))
				System.out.print("判断——");
			else {
				if(str[1].length()>1)
					System.out.print("多选——");
				else
					System.out.print("单选——");
			}
			
			System.out.print("请输入答案后回车: ");
			ans=in.nextLine();
			
			
			if(!judgeAns(ans,str[1])) {
				error.put(i, ans);
			}
		}
		
	}

	private static boolean judgeAns(String a, String b) {
		//b.replace("错", "0");
		//b.replace("对", "1");
		if(b.equals("对"))
			b="1";
		if(b.equals("错"))
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
					if (line.startsWith("正确答案")) {
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

		System.out.println("0.序章");
		System.out.println("1.第一章");
		System.out.println("2.第二章");
		System.out.println("3.第三章");
		System.out.println("4.第四章");
		System.out.println("5.第五章");
		System.out.println("6.第六章");
		System.out.println("7.第七章");
		System.out.println("请输入想测试的章节（输入数字后回车即可）");
		
		while (true) {

			try {
				num = in.nextInt();
				String t = in.nextLine();
				if (num >= 0 && num <= 7) {
					break;
				} else {
					System.out.println("输入的数字需要在0~7之间");
				}
			} catch (InputMismatchException e) {
				System.out.println("请输入数字后并回车");
				String t = in.next();
			}
		}
		

	}
}
