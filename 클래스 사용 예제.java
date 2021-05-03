import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;//인터페이스 - 상속필요
import java.util.Scanner;

class Students {
    int num;
    String name;
    int Java_score;

    Students(int num, String name, int Java_score) {
        this.num=num;
        this.name=name;
        this.Java_score=Java_score;
    }

    Students() {
        Scanner sc=new Scanner(System.in);

        System.out.print("학번을 입력하세요 : ");
        num=sc.nextInt();
        System.out.print("이름을 입력하세요 : ");
        name=sc.next();
        System.out.print("자바 점수를 입력하세요 : ");
        Java_score=sc.nextInt();

        System.out.println(this.name+"의 정보가 등록되었습니다.");

    }

}

class nameComparator implements Comparator<Students> {//List 객체 정렬방법. Comparator 인터페이스 상속방법 기준 : 이름 후 점수

    @Override//오버라이드
    public int compare(Students a, Students b) {
        if(a.name.compareTo(b.name)<0) {//이름이 더 빠른 경우
            return -1;
        }
        else if (a.name.compareTo(b.name)>0) {//이름이 뒤인경우
            return 1;
        }
        else//같은경우 높은 점수가 우선순위
            if(a.Java_score>b.Java_score) {
                return -1;
            }
            else
                return 1;
        }
    
}

public class HW_1 {

    static int opt=0;//메뉴 선택 옵션
    public static void hud0() {//인터페이스 화면 제시 및 메뉴 선택 강요.
        Scanner sc=new Scanner(System.in);
        System.out.println("==============================================================");
        System.out.println("==      원하는 기능을 숫자로 입력해서 Enter키를 누르세요               ==");
        System.out.println("==       1. 학생 정보 입력.                                     ==");
        System.out.println("==       2. 전체 학생 정보 출력                                  ==");
        System.out.println("==       3. 특정 학생 검색 (이름검색).                            ==");
        System.out.println("==       4. 종료                                             ==");
        System.out.println("==============================================================");

        opt=sc.nextInt();
    }

        public static Students hud1() {//1번 선택시 작동
            Students temp=new Students();
            return temp;
    }

    public static void hud2(ArrayList<Students> std) {//2번 선택시 작동. 

        Collections.sort(std,new nameComparator());//가나다순 배열, Collections.sort 방식 이용
        System.out.println("-----------------------------------------------------------------");
        for (Students s : std) {
            System.out.println("학생 이름 : "+s.name+"// 학번 : "+s.num+"// 자바 점수 : "+s.Java_score);
            System.out.println("ㅡㅡㅡㅡㅡ");
        }
        System.out.println("-----------------------------------------------------------------");
    }

    
    public static void hud3(ArrayList<Students> std) {//3번 누를시 작동
    	Collections.sort(std,new nameComparator());//가나다순 배열
        Scanner input=new Scanner(System.in);
        String temp;
        System.out.print("원하는 학생의 이름을 입력하세요. 없는 이름이면 아무런 정보가 뜨지 않습니다. : ");
        temp=input.next();
        for (Students s : std) {
            if (temp.equals(s.name)) {
                System.out.println("==============================================================");
                System.out.println("학생 이름 : "+s.name+"// 학번 : "+s.num+"// 자바 점수 : "+s.Java_score);
                System.out.println("==============================================================");
                break;
            }
        }
        
    }


    public static void main(String[] args) {
        ArrayList<Students> student = new ArrayList<>();
        student.add(new Students(2020001001,"고길동",68));
        student.add(new Students(2009002002,"홍길동",84));
        student.add(new Students(2018006002,"수호랑",87));
        student.add(new Students(2017011012,"삼수생",98));
        student.add(new Students(2021011023,"자바인",100));
        while (HW_1.opt!=4) {//구동 명령어
            if (HW_1.opt==0) {
                hud0();
            }
            else if (HW_1.opt==1) {
                student.add(hud1());
                HW_1.opt=0;
            }
            else if (HW_1.opt==2) {
                hud2(student);
                HW_1.opt=0;
            }
            else if (HW_1.opt==3) {
                hud3(student);
                HW_1.opt=0;
            }
            else if (HW_1.opt!=4) {
            	System.out.println("잘못된 번호 선택입니다. 다시 선택하세요");
            	HW_1.opt=0;
            }
            
        }
        System.out.println("프로그램이 안전하게 종료되었습니다.");
        
    }
}