package xyz.itwill.jdbc;

public class StaticBlockApp {
	public static void main(String[] args) {
		/*
		//ClassLoader 프로그램에 의해 클래스(Class 파일)을 읽어 메모리에 저장 - 자동(1번)
		//new 연산자가 메모리에 저장된 클래스(Clazz)의 생성자를 호출하여 인스턴스 생성
		// => 생성된 인스턴스
		StaticBlock sb = new StaticBlock();
		
		//참조변수에 저장된 인스턴스의 메소드 호출 - 기능 구현
		sb.display();
		*/
		
		/*
		//Class : 클래스 정보를 저장하기 위한 클래스
		//Class.forName(String className) : 문자열로 표현된 패키지를 포함한 클래스를 전달받아
		//ClassLoader 프로그램을 이용하여 클래스를 읽어 메모리에 저장하는 메소드
		// => 메모리에 저장된 클래스 정보(Class 인스턴스 - Clazz) 반환
		// => ClassNotFoundException 발생 - 예외처리
		//Class.newInstance() : 메모리에 저장된 클래스(Clazz)의 기본 생성자를 이용하여 인스턴스를 생성하는 메소드
		// => InstantiationException, IllegalAccessException 발생 = 예외처리
		// => Object 타입의 인스턴스를 반환 : 명시적 객체 형변환
		//리플렉션(Reflection) : 클래스를 직접 메모리에 저장하여 인스턴스를 생성하는 방법
		try {
			StaticBlock sb = (StaticBlock)Class.forName("xyz.itwill.jdbc.StaticBlock").newInstance();
			sb.display();
		} catch (ClassNotFoundException e) {
			System.out.println("[에러]클래스를 찾을 수 없습니다.");
		} catch (Exception e) {
			System.out.println("[에러]실행 프로그램에서 문제가 발생 하였습니다.");
		}
		*/
		
		try {
			//정적영역에서 인스턴스가 생성되어 메소드 호출
			Class.forName("xyz.itwill.jdbc.StaticBlock");
		} catch (ClassNotFoundException e) {
			System.out.println("[에러]클래스를 찾을 수 없습니다.");
		}
	}
}
