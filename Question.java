import java.util.Arrays;
import java.util.ArrayList;


public class Question {//에라토스테네스 의 체
	public static int q1(int n) {
		int[] arr=new int[n+1];
		int count=0;
		
		for (int i=2;i<=n;i++) {
			arr[i]=i;
		}
		
		for (int i=2;i<=(int) Math.sqrt(n);i++) {
			if (arr[i]==0) {
				continue;
			}
			
			for (int j=i+i;j<=n;j+=i) {
				arr[j]=0;
			}
		}
		
		for (int i=0;i<arr.length;i++) {
			if (arr[i]!=0) {
				count++;
			}
		}
		
		return count;
	}
	
	public static int[] q2(int[] array, int[][] inputs) {
		int[] answer = new int[inputs.length];
        int i=0;
        for (int[] e : inputs) {
            int p=e[1]-e[0]+1; //길이
            int[] temp=new int[p];
            for (int k=0;k<p;k++) {
                
                temp[k]=array[e[0]+k-1];           
            }
            Arrays.sort(temp);
            answer[i]=temp[e[2]-1];
            i++;
        }
        
        return answer;
	}
	
	public static int q3(int[][] board, int[] moves) {
        int count=0;//터져버린 횟수
        ArrayList<Integer> temp=new ArrayList<>();//크레인으로 뽑히고 난 뒤 보관하는 곳.
        
        int y=0;//크레인 y 위치.
        for (int k:moves ) {
            y=0;
            while (y<board[0].length) {
                if (board[y][k-1]==0) {//크레인이 내려가는 모습을 구현
                    y++;
                }
                else {
                    temp.add(board[y][k-1]);//크레인이 인형을 집고 올릴때
                    board[y][k-1]=0;
                    break;//맨위꺼 잡고 다음 x축 크레인 이동
                }
            }
            
            if (temp.size()>1 && temp.get(temp.size()-1).equals(temp.get(temp.size()-2))) {
                temp.remove(temp.size()-1);
                temp.remove(temp.size()-1);
                count+=2;
            }
            
            
        }
        return count;
	}
	
	static void q4_1(int years,int[] days) {//지역클래스 선언 q4위한 임시 클래스
		class leapYear {
			public void setleap(int year,int[] p) {
				if ((year%4==0 && year%100!=0) || year%400==0 ) {
					p[1]=29;
				}
				else
					p[1]=28;
			}
		}
		
		leapYear temp=new leapYear();
		temp.setleap(years,days);
	}
	
	public static String q4(String p) {
		int year=Integer.parseInt(p.substring(0,4));
		int month=Integer.parseInt(p.substring(4,6));
		int days=Integer.parseInt(p.substring(6,8));
		
		String[] daily={"MON","TUE","WED","THU","FRI","SAT","SUN"};
		int[] date= {31,28,31,30,31,30,31,31,30,31,30,31};
		
		int[] setstartday= {1900,1};//1900년 1월 
		int dis=0;//차이
		for (int y=setstartday[0];y<year;y++) {
			q4_1(setstartday[0],date);
			
			for (int m=setstartday[1];m<13;m++) {
				dis+=date[m-1];
			}
			setstartday[0]+=1;
		}
		setstartday[0]=year;
		q4_1(year,date);
		for (int m=setstartday[1];m<month;m++) {
			dis+=date[m-1];
		}
		dis+=(days-1);
		
		return daily[dis%7];
		
	}
	
	
	
	public static void main(String[] args) {	
		int[][] p1= {{0,0,0,0,0,0,0},{0,2,7,7,0,0,0},{0,3,4,5,0,2,7},{0,5,2,5,0,7,1},{1,2,3,7,1,6,6},{2,4,3,3,3,4,5},{2,3,3,4,7,6,2}};

		int[] p2= {2,6,6,1,1,3,4,7,7,5};
		System.out.println(Question.q3(p1,p2));
		int s=(int) (Math.random()*10000000);
		System.out.println(Question.q1(s));
		
		System.out.println(q4("31230812"));

	}

}
