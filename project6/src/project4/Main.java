package project4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import project4.suggestion;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	private JFrame frame;
	private JPanel currentPanel;
	// ȭ�� ũ��
	int WIDTH = 1600, HEIGHT = 900;

	// ������ �޾ƿ��� ��, totalQNum : �� ���� ���� �� �����ΰ�? qNum : ���� ����� ���
	// �����ΰ�?
	private int totalQNum = 4, qNum = 0;
	String[] radioName = new String[5];
	int myScore = 0;
	String myname;
	int mygrade;
	ArrayList<Manager> list = new ArrayList<Manager>();

	class Name {
		String name;

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	class Score { // 점수
		int score;

		public void setScore(int score) {
			this.score = score;
		}

		public int getScore() {
			return score;
		}
	}

	class Manager { // 이름과 점수를 종합
		Name name = new Name();
		Score quiz = new Score();

		int sum;
		float avr;

		public Manager(String name, int quiz) {
			// TODO Auto-generated constructor stub
			this.name.setName(name);
			this.quiz.setScore(quiz);

		}

		public void setName(String name) {
			this.name.setName(name);
		}

		public String getName() {
			return this.name.getName();
		}

		public void setquiz(int score) {
			this.quiz.setScore(score);
		}

		public int getquiz() {
			return this.quiz.getScore();
		}
	}

	// JLabel ����
	JLabel score_label, score, Rank_label, rank, getname, user_name, name_label, user_rank;
	JTextField name = new JTextField();

	int[] q1sug = suggestion.q1prompt();// 레벨1 제시어 문제

	int[][] q2sug1 = suggestion.q2_1();// 레벨 2 array 제시어 3개
	int[][][] q2sug2 = suggestion.q2_2(30);// 레벨 2 command 제시어 3개

	int[][][] q3sug1 = suggestion.q3_1();// 레벨 3 board 제시어 3개
	int[][] q3sug2 = suggestion.q3_2(q3sug1[0].length, q3sug1[1].length, q3sug1[2].length);// 레벨 3 크레인 제시어 3개

	String[] q4sug = suggestion.q4();

	int[] q5sug1 = suggestion.q5_3();// 최대무게
	int[] q5sug2 = suggestion.q5_2();// 다리길이
	int[][] q5sug3 = suggestion.q5_1(q5sug2, q5sug1);// 다리 배치

	// main �Լ�
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ������
	public Main() {
		initialize();
	}

	public void initialize() {
		/* frame ����� */
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* startPanel ���� */
		ImagePanel startPanel = new ImagePanel(new ImageIcon(getClass().getResource("/images/image3.jpg")).getImage());
		String path = "/var/data/stuff/xyz.dat";
		String base = "/var/data";
		String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();
		System.out.println(relative);

		frame.setSize(startPanel.getDim());
		frame.setPreferredSize(startPanel.getDim());
		frame.getContentPane().add(startPanel);
		frame.setResizable(false);
		currentPanel = startPanel;

		/* submit panel */
		/* quizpanel another */

		ImagePanel quizpanel[] = new ImagePanel[5];

		JButton submitBtn[] = new JButton[5];
		for (int i = 0; i < 5; i++) {
			submitBtn[i] = new JButton("Submit");
			submitBtn[i].setBorder(null);
			submitBtn[i].setBounds(1350, 700, 200, 80);
			submitBtn[i].setIcon(new ImageIcon(getClass().getResource("/images/button4.jpg")));
			submitBtn[i].setPressedIcon(new ImageIcon(getClass().getResource("/images/button4_clicked.jpg")));
			submitBtn[i].setHorizontalTextPosition(JButton.CENTER);
			submitBtn[i].setVerticalTextPosition(JButton.CENTER);
			submitBtn[i].setFont(new Font("Eras Bold ITC", Font.PLAIN, 50));
			submitBtn[i].setForeground(Color.WHITE);

		}
		JLabel qTitle = new JLabel("Quiz");
		qTitle.setFont(new Font("Eras Bold ITC", Font.PLAIN, 50));
		qTitle.setBounds(0, 20, WIDTH, 49);
		qTitle.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < 5; i++) {
			quizpanel[i] = new ImagePanel(new ImageIcon(getClass().getResource("/images/image5.jpg")).getImage());
			quizpanel[i].setVisible(false);
			quizpanel[i].add(qTitle);
			frame.add(quizpanel[i]);
		}

		/* Summary Panel ���� */
		ImagePanel summaryPanel = new ImagePanel(
				new ImageIcon(getClass().getResource("/images/image2.jpg")).getImage());
		summaryPanel.setVisible(false); // ��� ���д�
		frame.getContentPane().add(summaryPanel);

		/* Rank Panel ���� */
		ImagePanel RankPanel = new ImagePanel(new ImageIcon(getClass().getResource("/images/image2.jpg")).getImage());
		RankPanel.setVisible(false); // ��� ���д�
		frame.getContentPane().add(RankPanel);

		/* startPanel */
		// JLabel
		JLabel titleGame = new JLabel("Game");
		titleGame.setFont(new Font("Eras Bold ITC", Font.PLAIN, 80));
		titleGame.setBounds(0, 0, WIDTH, 500);
		titleGame.setHorizontalAlignment(JLabel.CENTER);
		titleGame.setForeground(Color.WHITE);
		startPanel.add(titleGame);

		// Question Label 크기 및 설정

		JLabel Question[] = new JLabel[5];
		for (int i = 0; i < 5; i++) {
			Question[i] = new JLabel();
			Question[i].setForeground(Color.BLACK);
			Question[i].setFont(new Font("돋움", Font.BOLD, 20));
			Question[i].setHorizontalAlignment(SwingConstants.CENTER);
			Question[i].setBounds(0, 0, 700, 900);
		}

		Question[0].setText(
				"<html>1부터 문제에 제시된 수까지의 자연수 중에서 소수의 개수를 구하세요<br>조건 : 제시되는 수는 2부터 1,000,000,000까지의 수 중 일부입니다.<br></html>");
		Question[1].setText(
				"<html><p>출처 : International collegiate programming contest Northwestern Russia Regional Contest<br />배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하려 합니다.<br />예를 들어 array가 [1, 5, 2, 6, 3, 7, 4], i = 2, j = 5, k = 3이라면 array의 2번째부터 5번째까지 자르면 [5, 2, 6, 3]입니다.<br />1에서 나온 배열을 정렬하면 [2, 3, 5, 6]입니다. 2에서 나온 배열의 3번째 숫자는 5입니다.<br />배열 array, [i, j, k]를 원소로 가진 2차원 배열 commands가 매개변수로 주어질 때, commands의 모든 원소에 대해</p>\r\n"
						+ "<p>앞서 설명한 연산을 적용했을 때 나온 결과를 <br />배열 꼴로 정답을 적어주세요.</p>\r\n"
						+ "<p><br />제한사항<br />array의 길이는 1 이상 100 이하입니다. /array의 각 원소는 1 이상 100 이하입니다.<br />commands의 길이는 1 이상 50 이하입니다. /commands의 각 원소는 길이가 3입니다.</p>\r\n"
						+ "<p>&nbsp;</p>\r\n" + "<p>예시</p>\r\n" + "<table style=\"height: 37px;\" width=\"530\">\r\n"
						+ "<tbody>\r\n" + "<tr style=\"height: 13px;\">\r\n"
						+ "<td style=\"width: 167px; height: 13px; text-align: center;\">Array</td>\r\n"
						+ "<td style=\"width: 169px; height: 13px; text-align: center;\">command</td>\r\n"
						+ "<td style=\"width: 172px; height: 13px; text-align: center;\">Answer</td>\r\n" + "</tr>\r\n"
						+ "<tr style=\"height: 13px;\">\r\n"
						+ "<td style=\"width: 167px; height: 13px; text-align: center;\">[1, 4, 2, 6, 3, 7, 4]</td>\r\n"
						+ "<td style=\"width: 169px; height: 13px; text-align: center;\">[[2, 5, 3], [4, 4, 1], [1, 7, 3]]</td>\r\n"
						+ "<td style=\"width: 172px; height: 13px; text-align: center;\">[5, 6, 3]</td>\r\n"
						+ "</tr>\r\n" + "</tbody>\r\n" + "</table></html>");
		Question[2].setText("<html><p>&nbsp;</p>\r\n"
				+ "<p><img src=\"https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/69f1cd36-09f4-4435-8363-b71a650f7448/crane_game_101.png\" alt=\"\" width=\"400\" height=\"400\" /></p>\r\n"
				+ "<p>게임 화면은&nbsp;<strong>\"1 x 1\"</strong>&nbsp;크기의 칸들로 이루어진&nbsp;<strong>\"N x N\"</strong>&nbsp;크기의 정사각 격자이며 위쪽에는 크레인이 있고 오른쪽에는 바구니가 있습니다.</p>\r\n"
				+ "<p>(위 그림은 \"5 x 5\" 크기의 예시입니다).</p>\r\n"
				+ "<p>각 격자 칸에는 다양한 인형이 들어 있으며 인형이 없는 칸은 빈칸입니다. 모든 인형은 \"1 x 1\" 크기의 격자 한 칸을 차지하며&nbsp;<strong>격자의 가장 아래 칸부터 차곡차곡 쌓여 있습니다.</strong>&nbsp;</p>\r\n"
				+ "<p>게임 사용자는 크레인을 좌우로 움직여서 멈춘 위치에서 가장 위에 있는 인형을 집어 올릴 수 있습니다. 집어 올린 인형은 바구니에 쌓이게 되는 데,</p>\r\n"
				+ "<p>이때 바구니의 가장 아래 칸부터 인형이 순서대로 쌓이게 됩니다.</p>\r\n" + "<p>&nbsp;</p>\r\n"
				+ "<p><img src=\"https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/8569d736-091e-4771-b2d3-7a6e95a20c22/crane_game_103.gif\" alt=\"\" width=\"400\" height=\"400\" /></p>\r\n"
				+ "<p>만약 같은 모양의 인형 두 개가 바구니에 연속해서 쌓이게 되면 두 인형은 터뜨려지면서 바구니에서 사라지게 됩니다.</p>\r\n"
				+ "<p>크레인 작동 시 인형이 집어지지 않는 경우는 없으나 만약 인형이 없는 곳에서 크레인을 작동시키는 경우에는 아무런 일도 일어나지 않습니다.</p>\r\n"
				+ "<p>또한 바구니는 모든 인형이 들어갈 수 있을 만큼 충분히 크다고 가정합니다. (그림에서는 화면표시 제약으로 5칸만으로 표현하였음)</p>\r\n"
				+ "<p>&nbsp;</p>\r\n"
				+ "<p>게임 화면의 격자의 상태가 담긴 2차원 배열 board와 인형을 집기 위해 크레인을 작동시킨 위치가 담긴 배열 moves가 매개변수로 주어질 때,</p>\r\n"
				+ "<p>크레인을 모두 작동시킨 후 터트려져 사라진 인형의 개수를 return 할 것</p>\r\n" + "<p>&nbsp;</p>\r\n"
				+ "<p>제한사항 : board 크기는 최소 5x5 , 최대 30x30, 각 칸에는 0이상 100이하의 정수가 담겨있음.</p>\r\n"
				+ "<p>0은 빈칸을 의미하며, 1부터 100은 각기 다른 인형을 의미함. 숫자가 같으면 같은 모형</p>\r\n" + "<p>&nbsp;</p>\r\n"
				+ "<p>위 사진을 board로 두면 [[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]] 와 같음.</p></html>");
		Question[3].setText("<html><p>1900년 1월 1일은 월요일 입니다. 문자열 형태로 날짜를 제시받으면 해당 요일을 return 하세요.</p>\r\n"
				+ "<p>요일은 &ldquo;MON&rdquo;, &ldquo;TUE&rdquo;, &ldquo;WED&rdquo;, &ldquo;THU&rdquo;, &ldquo;FRI&rdquo;, &ldquo;SAT&rdquo;, &ldquo;SUN&rdquo; 중 하나로 입력해야 합니다.</p>\r\n"
				+ "<p>1900년 1월 1일은 19000101로 제시됩니다.</p>\r\n"
				+ "<p>조건 : 제시날은 1900년 1월 1일 ~ 4000년 12월 31일 이며 존재하지 않는 날짜는 제공되지 않습니다.</p>\r\n"
				+ "<p>예시 : &ldquo;19000101&rdquo; -&gt; &ldquo;MON&rdquo;</p></html>");

		Question[4].setText("<html><p>트럭 여러 대가 강을 가로지르는 일 차선 다리를 정해진 순으로 건너려 합니다.</p>\r\n"
				+ "<p>모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다.</p>\r\n"
				+ "<p>트럭은 1초에 1만큼 움직이며, 다리 길이는 bridge_length이고 다리는 무게 weight까지 견딥니다.<br />※ 트럭이 다리에 완전히 오르지 않은 경우, 이 트럭의 무게는 고려하지 않습니다.</p>\r\n"
				+ "<p>예를 들어, 길이가 2이고 10kg 무게를 견디는 다리가 있습니다.</p>\r\n"
				+ "<p>무게가 [7, 4, 5, 6]kg인 트럭이 순서대로 최단 시간 안에 다리를 건너려면 다음과 같이 건너야 합니다.</p>\r\n"
				+ "<table class=\"table\">\r\n" + "<thead>\r\n" + "<tr>\r\n" + "<th>경과 시간</th>\r\n"
				+ "<th>다리를 지난 트럭</th>\r\n" + "<th>다리를 건너는 트럭</th>\r\n" + "<th>대기 트럭</th>\r\n" + "</tr>\r\n"
				+ "</thead>\r\n" + "<tbody>\r\n" + "<tr>\r\n" + "<td>0</td>\r\n" + "<td>[]</td>\r\n" + "<td>[]</td>\r\n"
				+ "<td>[7,4,5,6]</td>\r\n" + "</tr>\r\n" + "<tr>\r\n" + "<td>1~2</td>\r\n" + "<td>[]</td>\r\n"
				+ "<td>[7]</td>\r\n" + "<td>[4,5,6]</td>\r\n" + "</tr>\r\n" + "<tr>\r\n" + "<td>3</td>\r\n"
				+ "<td>[7]</td>\r\n" + "<td>[4]</td>\r\n" + "<td>[5,6]</td>\r\n" + "</tr>\r\n" + "<tr>\r\n"
				+ "<td>4</td>\r\n" + "<td>[7]</td>\r\n" + "<td>[4,5]</td>\r\n" + "<td>[6]</td>\r\n" + "</tr>\r\n"
				+ "<tr>\r\n" + "<td>5</td>\r\n" + "<td>[7,4]</td>\r\n" + "<td>[5]</td>\r\n" + "<td>[6]</td>\r\n"
				+ "</tr>\r\n" + "<tr>\r\n" + "<td>6~7</td>\r\n" + "<td>[7,4,5]</td>\r\n" + "<td>[6]</td>\r\n"
				+ "<td>[]</td>\r\n" + "</tr>\r\n" + "<tr>\r\n" + "<td>8</td>\r\n" + "<td>[7,4,5,6]</td>\r\n"
				+ "<td>[]</td>\r\n" + "<td>[]</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n"
				+ "<p>따라서, 모든 트럭이 다리를 지나려면 최소 8초가 걸립니다.</p>\r\n"
				+ "<p>solution 함수의 매개변수로 다리 길이 bridge_length, 다리가 견딜 수 있는 무게 weight,</p>\r\n"
				+ "<p>트럭별 무게 truck_weights가 주어집니다.</p>\r\n"
				+ "<p>이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.</p>\r\n" + "<h5>제한 조건</h5>\r\n"
				+ "<ul>\r\n" + "<li>bridge_length는 1 이상 10,000 이하입니다.</li>\r\n"
				+ "<li>weight는 1 이상 10,000 이하입니다.</li>\r\n" + "<li>truck_weights의 길이는 1 이상 10,000 이하입니다.</li>\r\n"
				+ "<li>모든 트럭의 무게는 1 이상 weight 이하입니다.</li>\r\n" + "</ul></html>");
		// 문제 스크롤 추가
		JScrollPane quest[] = new JScrollPane[5];

		for (int i = 0; i < 5; i++) {
			quest[i] = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			quest[i].getViewport().add(Question[i]);
			quest[i].setBounds(0, 0, 700, 865);
			quest[i].setFont(new Font("돋움", Font.BOLD, 15));
			quizpanel[i].add(quest[i]);
			quizpanel[i].add(submitBtn[i]);
		}

		// quizpanel 입력칸 각각 추가

		JTextField[][] in1 = new JTextField[5][3];
		for (int k = 0; k < 5; k++) {
			for (int i = 0; i < 3; i++) {
				in1[k][i] = new JTextField(10);
				in1[k][i].setBounds(900, 500 + 50 * i, 500, 40);
				in1[k][i].setFont(new Font("돋움", Font.BOLD, 15));
				quizpanel[k].add(in1[k][i]);

				in1[k][i].setText("문제 " + (i + 1) + "번의 답을 입력하세요");

			}
		}
		// 제시어 주는 공간
		JTextArea[] give = new JTextArea[5];
		JScrollPane[] prompt = new JScrollPane[5];
		for (int k = 0; k < 5; k++) {
			give[k] = new JTextArea();
			give[k].setBounds(900, 0, 680, 400);
			give[k].setEditable(false);
			give[k].setDragEnabled(true);

			prompt[k] = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			prompt[k].getViewport().add(give[k]);
			prompt[k].setBounds(900, 0, 680, 400);

			quizpanel[k].add(prompt[k]);

		}

		// 제시어 세부 셋팅

		suggestion.sptextadd(give[0], "제시어 1 : " + q1sug[0], "제시어 2 :" + q1sug[1], "제시어 3 : " + q1sug[2]);

		for (int i = 0; i < 3; i++) {
			suggestion.sptextadd(give[1], "제시어 " + (i + 1), "Arrays " + (i + 1) + ": " + Arrays.toString(q2sug1[i]),
					"Commands " + (i + 1) + " : " + Arrays.deepToString(q2sug2[i]), "-------------------------");
		}

		for (int i = 0; i < 3; i++) {
			suggestion.sptextadd(give[2], "제시어 " + (i + 1), "Board " + (i + 1) + ": " + Arrays.deepToString(q3sug1[i]),
					"Moves " + (i + 1) + " : " + Arrays.toString(q3sug2[i]), "----------------------------");
		}

		for (int i = 0; i < 3; i++) {
			suggestion.sptextadd(give[3], "제시어 " + (i + 1), "날짜  " + (i + 1) + ": " + q4sug[i],
					"----------------------------");
		}

		for (int i = 0; i < 3; i++) {
			suggestion.sptextadd(give[4], "제시어 " + (i + 1), "bridge_length " + (i + 1) + ": " + q5sug2[i],
					"Max weight " + (i + 1) + " : " + q5sug1[i],
					"truck_weighs" + (i + 1) + " : " + Arrays.toString(q5sug3[i]), "-------------------------");
		}

		// JButton
		JButton startBtn = new JButton("Start");
		startBtn.setBorder(null);
		startBtn.setBounds(WIDTH / 2 - 100, 350, 200, 80);
		startBtn.setHorizontalAlignment(JButton.CENTER);
		startBtn.setIcon(new ImageIcon(getClass().getResource("/images/button2.jpg")));
		startBtn.setPressedIcon(new ImageIcon(getClass().getResource("/images/button2_clicked.jpg")));
		startBtn.setHorizontalTextPosition(JButton.CENTER);
		startBtn.setVerticalTextPosition(JButton.CENTER);
		startBtn.setFont(new Font("Eras Bold ITC", Font.PLAIN, 50));
		startBtn.setForeground(Color.WHITE);

		JButton exitBtn = new JButton("Exit");
		exitBtn.setBorder(null);
		exitBtn.setBounds(WIDTH / 2 - 100, 480, 200, 80);
		exitBtn.setIcon(new ImageIcon(getClass().getResource("/images/button3.jpg")));
		exitBtn.setPressedIcon(new ImageIcon(getClass().getResource("/images/button3_clicked.jpg")));
		exitBtn.setHorizontalTextPosition(JButton.CENTER);
		exitBtn.setVerticalTextPosition(JButton.CENTER);
		exitBtn.setFont(new Font("Eras Bold ITC", Font.PLAIN, 50));
		exitBtn.setForeground(Color.WHITE);

		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���� ���������Ƿ�, ���� ȭ������ �Ѿ�� ���� currentPanel�� �����ְ� quizPanel��
				// �����Ų��
				startPanel.setVisible(false);
				quizpanel[0].setVisible(true);
				currentPanel = quizpanel[0];
			}
		});

		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "프로그램을 종료합니다.");
				System.exit(0);
			}
		});

		startPanel.add(startBtn);
		startPanel.add(exitBtn);

		/* quizPanel */

		// JButton - submit �κ�

		/* SummaryPanel */
		// JButton
		JButton restartBtn = new JButton("Restart");
		restartBtn.setBorder(null);
		restartBtn.setBounds(1200, 200, 200, 80);
		restartBtn.setIcon(new ImageIcon(getClass().getResource("/images/button5.jpg")));
		restartBtn.setPressedIcon(new ImageIcon(getClass().getResource("/images/button5_clicked.jpg")));
		restartBtn.setHorizontalTextPosition(JButton.CENTER);
		restartBtn.setVerticalTextPosition(JButton.CENTER);
		restartBtn.setFont(new Font("Eras Bold ITC", Font.PLAIN, 50));
		restartBtn.setForeground(Color.WHITE);

		restartBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���� ���������Ƿ�, ���� ȭ������ �Ѿ�� ���� currentPanel�� �����ְ� quizPanel��
				// �����Ų��
				currentPanel.setVisible(false);
				currentPanel = startPanel;
				currentPanel.setVisible(true);
				myScore = 0;

				for (int i = 0; i < 5; i++) {
					for (int k = 0; k < 3; k++) {
						in1[i][k].setText("");
					}
				}
			}
		});
		RankPanel.add(restartBtn);

		// JButton

		// submitBtn. 람다식제한

		submitBtn[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				currentPanel.setVisible(false);
				currentPanel = quizpanel[1];
				currentPanel.setVisible(true);

				for (int i = 0; i < 3; i++) {
					try {
						if (in1[0][i].getText().equals(Integer.toString(solution.q1(q1sug[i])))) {
							myScore += 10;
						}
					} catch (Exception err) {

					}
				}
			}

		});
		submitBtn[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				currentPanel.setVisible(false);
				currentPanel = quizpanel[2];
				currentPanel.setVisible(true);

				for (int i = 0; i < 3; i++) {
					try {
						if (in1[1][i].getText().equals(Arrays.toString(solution.q2(q2sug1[i], q2sug2[i])))) {
							myScore += 10;
						}
					} catch (Exception err) {

					}
				}
			}

		});
		submitBtn[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				currentPanel.setVisible(false);
				currentPanel = quizpanel[3];
				currentPanel.setVisible(true);

				for (int i = 0; i < 3; i++) {
					try {
						if (Integer.parseInt(in1[2][i].getText()) == solution.q3(q3sug1[i], q3sug2[i])) {
							myScore += 15;
						}
					} catch (Exception err) {

					}
				}
			}

		});
		submitBtn[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				currentPanel.setVisible(false);
				currentPanel = quizpanel[4];
				currentPanel.setVisible(true);

				for (int i = 0; i < 3; i++) {
					try {
						if (in1[3][i].getText().equals(solution.q4(q4sug[i]))) {
							myScore += 5;
						}
					} catch (Exception err) {

					}
				}
			}

		});
		submitBtn[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				currentPanel.setVisible(false);
				currentPanel = summaryPanel;
				currentPanel.setVisible(true);

				for (int i = 0; i < 3; i++) {
					try {
						if (Integer.parseInt(in1[4][i].getText()) == solution.q5(q5sug2[i], q5sug1[i], q5sug3[i])) {
							myScore += 10;
						}
					} catch (Exception err) {

					}
				}

				score.setText(Integer.toString(myScore));
			}

		});

		// text
		score_label = new JLabel("Score");
		score_label.setFont(new Font("Eras Bold ITC", Font.PLAIN, 33));
		score_label.setBounds(500, 200, 139, 49);
		score_label.setForeground(Color.WHITE);
		summaryPanel.add(score_label);

		score = new JLabel("");
		score.setFont(new Font("Eras Bold ITC", Font.PLAIN, 33));
		score.setBounds(500, 250, 139, 49);
		score.setBackground(Color.WHITE);
		score.setOpaque(true);
		score.setText(Integer.toString(myScore));
		summaryPanel.add(score);

		getname = new JLabel("이름을 입력하세요");
		getname.setFont(new Font("돋움", Font.BOLD, 33));
		getname.setBounds(500, 320, 400, 49);
		getname.setForeground(Color.WHITE);
		summaryPanel.add(getname);

		JTextField name = new JTextField(10);
		name.setBounds(500, 370, 139, 49);
		name.setBackground(Color.WHITE);
		name.setFont(new Font("돋움", Font.BOLD, 15));
		summaryPanel.add(name);

		/*
		 * rank_label = new JLabel("Rank"); rank_label.setFont(new Font("Eras Bold ITC",
		 * Font.PLAIN, 33)); rank_label.setBounds(500, 300, 139, 49);
		 * rank_label.setForeground(Color.WHITE); summaryPanel.add(rank_label);
		 */

		// score print
		name_label = new JLabel("name");
		name_label.setFont(new Font("Eras Bold ITC", Font.PLAIN, 33));
		name_label.setBounds(500, 200, 139, 49);
		name_label.setForeground(Color.WHITE);
		RankPanel.add(name_label);

		user_name = new JLabel("");
		user_name.setFont(new Font("Eras Bold ITC", Font.PLAIN, 33));
		user_name.setBounds(500, 250, 139, 49);
		user_name.setBackground(Color.WHITE);
		user_name.setOpaque(true);
		user_name.setText(name.getText());
		System.out.println(name.getText());
		RankPanel.add(user_name);

		Rank_label = new JLabel("Rank");
		Rank_label.setFont(new Font("Eras Bold ITC", Font.PLAIN, 33));
		Rank_label.setBounds(800, 200, 139, 49);
		Rank_label.setForeground(Color.WHITE);
		RankPanel.add(Rank_label);

		user_rank = new JLabel("");
		user_rank.setFont(new Font("Eras Bold ITC", Font.PLAIN, 33));
		user_rank.setBounds(800, 250, 139, 49);
		user_rank.setBackground(Color.WHITE);
		user_rank.setOpaque(true);
		user_rank.setText(Integer.toString(myScore));
		RankPanel.add(user_rank);
		
		
		JButton nextBtn = new JButton("Next");
		nextBtn.setBorder(null);
		nextBtn.setBounds(WIDTH / 2 - 100, 480, 200, 80);
		nextBtn.setIcon(new ImageIcon(getClass().getResource("/images/button5.jpg")));
		nextBtn.setPressedIcon(new ImageIcon(getClass().getResource("/images/button5_clicked.jpg")));
		nextBtn.setHorizontalTextPosition(JButton.CENTER);
		nextBtn.setVerticalTextPosition(JButton.CENTER);
		nextBtn.setFont(new Font("Eras Bold ITC", Font.PLAIN, 50));
		nextBtn.setForeground(Color.WHITE);

		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���� ���������Ƿ�, ���� ȭ������ �Ѿ�� ���� currentPanel�� �����ְ� quizPanel��
				// �����Ų��
				currentPanel.setVisible(false);
				currentPanel = RankPanel;
				currentPanel.setVisible(true);
				String myname = name.getText();
				System.out.println(name.getText());
				user_name.setText(myname);

				mygrade = myScore;
				list.add(new Manager(myname, myScore));
			}
		});
		summaryPanel.add(nextBtn);


		/* ���� ���� */
		frame.pack();
	}
}
