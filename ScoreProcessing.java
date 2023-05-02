import java.util.Scanner;

// 점수에 관한 인터페이스 생성
interface Grade {
	void scoreProcess();
	int getAverageBelowCount();
}

// 추상 클래스를 이용해 학생 수를 상수로 지정
abstract class Student {
	final int numOfStudent = 8;
}

// Student 추상 클래스를 상속받고, Grade 인터페이스를 구현하는 Score 클래스 생성
class Score extends Student implements Grade {
	// 점수를 입력받을 수 있는 배열 생성
	int[] scoreArray = new int [numOfStudent];
	// 인덱스 순으로 평점을 누적시킬 수 있는 배열 생성
	int[] gpaCount = new int [5];
	// 평점 배열 생성
	char[] gpa = {'A', 'B', 'C', 'D', 'F'};
	// 총점
	int totalScore = 0;
	// 평균 미만을 누적시킬 수 있는 변수 선언
	int averageBelowCount = 0;
	// 평균 점수 
	double scoreAverage = 0.0;
	
	@Override
	// 점수를 입력받고, 총점과 평균을 계산하는 메소드
	public void scoreProcess() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("아래에 자바 성적을 입력하세요. (입력은 엔터로 구분합니다.)");
		
		for (int i=0; i<numOfStudent; i++) {
			scoreArray[i] = scanner.nextInt();
			
			if (scoreArray[i] > 100 || scoreArray[i] < 0) {
				System.out.println("점수의 범위는 0점부터 100점입니다. 다시 입력해주세요.");
				i--;
				continue;
			} else if (scoreArray[i] >= 90) {
				gpaCount[0]++;
			} else if (scoreArray[i] >= 80) {
				gpaCount[1]++;
			} else if (scoreArray[i] >= 70) {
				gpaCount[2]++;
			} else if (scoreArray[i] >= 60) {
				gpaCount[3]++;
			} else {
				gpaCount[4]++;
			}
			// 총점 계산
			totalScore += scoreArray[i];
		}
		// 평균 계산
		scoreAverage = totalScore / numOfStudent;
		scanner.close();
	}

	@Override
	// 평균 미만인 점수를 누적시키는 메소드
	public int getAverageBelowCount() {
		for (int i=0; i<numOfStudent; i++) {
			if (scoreArray[i] < scoreAverage) {
				averageBelowCount++;
			}
		}
		return averageBelowCount;
	}
	
}

// 성적분포를 출력하는 클래스
class displayResult extends Score {
	public void printResult() {
		System.out.println("평균 미만 학생은 " + getAverageBelowCount() + "명이고 성적 분포는 아래와 같습니다.");
		
		for (int i=0; i<gpaCount.length; i++) {
			System.out.print(gpa[i] + "(" + gpaCount[i] + "명) | ");
			for (int j=0; j<gpaCount[i]; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		System.out.println("-----------------");
		System.out.println("총합" + numOfStudent + "명(평균" + scoreAverage + "점)");
	}
}

public class ScoreProcessing extends displayResult {
	public static void main(String[] args) {
		// ScoreProcessing 클래스에 대한 생성자를 만들어 초기화
		ScoreProcessing score = new ScoreProcessing();
		score.scoreProcess();
		score.printResult();
	}

}
