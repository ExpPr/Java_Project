package project4;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextArea;


class givesuggestion {//각 제시어 랜덤 생성기
	
	public static int rnIn(int a) {//1부터 a까지 중 랜덤기 발생
		return (int) (Math.random()*(a))+1;
	}
	public static int rnIn(int a,int b) {//a부터 a+b까지 중 랜덤발생기
		return (int) (Math.random()*b)+a+1;
	}
	public static int rnIn0(int a) {//0부터 a+1까지 생성기
		return (int) (Math.random()*(a+1));
	}
	
	public static int[] arrays1rnIn(int length,int a) {
		int[] temp=new int[length];
		for (int i=0;i<length;i++) {
			temp[i]=rnIn(a);
		}
		return temp;
	}

	
	public static int[] q1prompt() {
		int[] temp=new int[3];
		temp[0]=suggestion.rnIn(100000);
		temp[1]=suggestion.rnIn(1000000);
		temp[2]=suggestion.rnIn(10000000);
		return temp;
	}
	
	public static int[] q2a(int max,int clength) {
		int length=rnIn(5,clength);
		
		int[] array=new int[length];
		
		for (int i=0;i<length;i++) {
			array[i]=rnIn(max);
		}
		return array;
	}
	
	public static int[][] q2b(int length) {
		int clength=rnIn(50);
		int[][] command=new int[clength][3];
		int a,b,c;
		for (int i=0;i<clength;i++) {
			a=rnIn(length);
			b=rnIn(length);
			c=rnIn(Math.abs(a-b)+1);
			command[i][0]=Math.min(a, b);
			command[i][1]=Math.max(a, b);
			command[i][2]=c;

		}
		return command;
	}
	
	
	public static int[][] board(int length,int entryMax) {//레벨 3 보드 크기 및 원소 최대 크기설정
		
		int[] maxcolumn=new int[length];
		
		int brd[][]=new int[length][length];
		
		for (int i=0;i<length;i++) {
			maxcolumn[i]=rnIn0(length);
		}
		
		for (int i=0;i<length;i++) {//i=행
			for (int k=0;k<length;k++) {//k=열
				if (i<maxcolumn[k]) {
					brd[i][k]=0;
				}
				else
				{
					brd[i][k]=rnIn(entryMax);
				}
			}
		}
		return brd;
		
	}
	
	public static int[] moves (int length) {
		int[] temp=new int[length];
		for (int i=0;i<length;i++) {
			temp[i]=rnIn(length);
		}
		return temp;
	}//레벨 3 move 랜덤 생성기
	
	
	public static String randomDate () {//1900년 ~ 4000년 사이 랜덤 생성기
		Calendar randomdate = Calendar.getInstance();
		randomdate.set(Calendar.YEAR, rnIn(1900,2100));
		randomdate.set(Calendar.MONTH, rnIn(12));
		
		if (randomdate.get(Calendar.MONTH)==2) {
			if (randomdate.get(Calendar.YEAR)%400==0 || (randomdate.get(Calendar.YEAR)%4==0) && randomdate.get(Calendar.YEAR)%100!=0) {
				randomdate.set(Calendar.DATE, rnIn(29));
			}
			else {randomdate.set(Calendar.DATE, rnIn(28));}
		}
		else {
			
			switch (randomdate.get(Calendar.MONTH)) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12: randomdate.set(Calendar.DATE, rnIn(31));
			break;
			
			case 4:
			case 6:
			case 9:
			case 11: randomdate.set(Calendar.DATE, rnIn(30));
			}
			}
		
		DateFormat RnDate = new SimpleDateFormat("yyyyMMdd");
		Date rddte=randomdate.getTime();
		
		System.out.println(RnDate.format(rddte));
		return RnDate.format(rddte);
		
		
		}
	
	public static int[] rtruck_weights(int length,int maxWeight) {
		int[] temp=new int[length];
		
		for (int i=0;i<length;i++) {
			temp[i]=rnIn(maxWeight);
		}
		return temp;
	}
	
	
	}

public class suggestion extends givesuggestion {
	
	private static final String newline="\n";//jtextarea 띄어쓰기용

	public static void sptextadd(JTextArea txfd,String ...v) {
		for (String e:v) {
			txfd.append(e+newline);//문자열 자동 띄어쓰기 적용 
		}
	}
	
	public static int[][] q2_1() {
		int[][] arrays=new int[3][];
		arrays[0]=q2a(10,10);
		arrays[1]=q2a(30,20);
		arrays[2]=q2a(50,50);
		
		return arrays;
	}
	
	public static int[][][] q2_2(int length) {
		int[][][] commands=new int[3][][];
		commands[0]=q2b(rnIn(3,length));
		commands[1]=q2b(rnIn(3,length));
		commands[2]=q2b(rnIn(3,length));
		
		return commands;
	}
	
	public static int[][][] q3_1() {
		int[][][] brd=new int[3][][];
		brd[0]=board(7,7);
		brd[1]=board(10,10);
		brd[2]=board(20,20);
		return brd;
	}
	public static int[][] q3_2(int length1,int length2, int length3) {
		int [][] moves=new int[3][];
		
		moves[0]=arrays1rnIn(rnIn(8,7),length1);
		moves[1]=arrays1rnIn(rnIn(10,10),length2);
		moves[2]=arrays1rnIn(rnIn(20,10),length3);
		
		
		return moves;
		
	}
	public static String[] q4() {
		String[] temp=new String[3];
		temp[0]=randomDate();
		temp[1]=randomDate();
		temp[2]=randomDate();
		return temp;
		
	}
	public static int[][] q5_1(int[] set,int[] weight) {
		int[][] bridge=new int[3][];
		bridge[0]=rtruck_weights(set[0],weight[0]);
		bridge[1]=rtruck_weights(set[1],weight[1]);
		bridge[2]=rtruck_weights(set[2],weight[2]);
		return bridge;
	}
	public static int[] q5_2() {//다리 길이 랜덤. 
		int[] temp=new int[3];
		temp[0]=rnIn(10);
		temp[1]=rnIn(30);
		temp[2]=rnIn(100);
		
		return temp;
	}
	public static int[] q5_3() {//최대 무개 랜덤
		int[] temp=new int[3];
		
		temp[0]=rnIn(10);
		temp[1]=rnIn(1000);
		temp[2]=rnIn(10000);
		
		return temp;
		}
	
	
	
}
