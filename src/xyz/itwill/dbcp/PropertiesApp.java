package xyz.itwill.dbcp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//Properties 파일 : 프로그램 실행에 필요한 값을 제공하기 위한 텍스트 파일
// => 프로그램을 변경하지 않고 Properties 파일의 내용을 변경하여 프로그램 실행 결과 변경 가능
// => 프로그램의 유지보수 효율성 증가
// => Properties 파일에서는 값을 문자열(String)로만 제공
// => Properties 파일에서는 영문자,숫자,일부 특수문자를 제외한 문자는 유니코드로 표현

//user.properties 파일에 의해 제공되는 값을 출력하는 프로그램
public class PropertiesApp {
	public static void main(String[] args) throws IOException {
		//Properties 파일을 읽기 위한 파일 입력 스트림 생성
		
		//FileInputStream 클래스를 이용하여 입력 스트림 생성 - 파일 경로 관련 문제 
		//FileInputStream in = new FileInputStream("src/xyz/itwill/dbcp/user.properties");
		
		//메모리에 저장된 클래스를 Class 인스턴스(clazz)로 제공받는 방법 
		//1.Class.forName(String class) : 문자열로 표현된 클래스를 메모리에 저장하고 Class 인스턴스 반환
		//2.Object.getClass() : 현재 사용중인 클래스를 Class 인스턴스로 반환
		//3.클래스명.class : 메모리에 저장된 클래스를 직접 Class 인스턴스로 표현하여 사용
		
		//ClassLoader 프로그램을 이용한 입력 스트림 생성 - 파일 경로 관련 문제 미발생
		//클래스명.class : 메모리에 저장된 클래스(Class 인스턴스 - Clazz)를 직접 표현하는 방법
		//Class.getClassLoader() : 클래스를 읽어 메모리에 저장하는 기능을 제공하는
		//ClassLoader 인스턴스를 반환하는 메소드
		//ClassLoader.getResourceAsStream(String resource) : 리소스 파일의 경로를 전달받아
		//파일을 읽기 위한 입력 스트림을 생성하여 반환하는 메소드
		InputStream in = PropertiesApp.class.getClassLoader().getResourceAsStream("xyz/itwill/dbcp/user.properties");
		
		//Properties 클래스로 인스턴스 생성
		// => Properties 클래스는 Map 인터페이스를 상속받은 콜렉션 클래스 
		// => Properties 인스턴스에는 Key와 Value를 하나의 엔트리(Entry)로 처리하여 저장 
		Properties properties = new Properties();
		
		//Properties.load(InputStream in) : Properties 파일에 저장된 모든 Key와 Value를 얻어와
		//Properties 인스턴스의 엔트리(Key:String, Value:String)로 추가하여 저장하는 메소드
		properties.load(in);
		
		//Properties 인스턴스에 저장된 값(Value)을 식별자(Key)를 이용하여 반환받아 저장
		//Properties.get(String Key) : Properties 인스턴스에 저장된 엔트리에서 식별자(Key)를
		//이용하여 값(Value)을 반환하는 메소드
		// => 반환값이 Object 타입으로 반환되므로 명시적 객체 형변환
		String id = (String)properties.get("id");
		String password = (String)properties.get("password");
		String name = (String)properties.get("name");
		
		System.out.println("아이디 = " + id);
		System.out.println("비밀번호 = " + password);
		System.out.println("이름 = " + name);
	}
}
