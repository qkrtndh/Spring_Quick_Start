# Spring_Quick_Start
<p>SPRING QUICK START (저.채규태, 출.PUBY PAPER)</p>

<img src="http://image.yes24.com/momo/TopCate853/MidCate001/85204414.jpg">

<p>위의 책을 보며 공부한 것을 정리(2/5)</p>

<H1>1. 스프링 AOP</H1>
<p>비즈니스 컴포넌트 개발에서 가장 중요한 두 가지 원칙은 낮은 결합도와 높은 응집도를 유지하는 것이다. 스프링의 의존성 주입을 이용하면 비즈니스 컴포넌트를 구성하는 객체들의 결합도를 떨어뜨릴 수 있어서 의존관계를 쉽게 변경할 수 있다.</p>
<p>스프링의 IoC가 결합도와 관련된 기능이라면 AOP는 응집도와 관련된 기능이다.</p>

<H2>1.1 AOP이해하기</H2>
<p>엔터프라이즈 애플리케이션의 메소드들은 대부분 다음과 같이 복잡한 코드들로 구성되어 있다. 이 중에서 핵심 비즈니스 로직은 몇 줄 안되고, 주로 로깅이나 예외, 트랜잭션 처리 같은 부가적인 코드가 대부분이다. 이런 부가적인 코드들로 인해서 비즈니스 메소드의 복잡도는 증가하고 결국 개발자를 지치게 한다.</p>
<p>이런 코드들은 비즈니스 로직만큼이나 중요한 기능들이기 때문에 삭제해서는 안된다. 중요한 점은 우리가 구현하는 비즈니스 메소드마다 이런 부가적인 코드들을 매번 반복해야 한다는 것이다.</p>
<p>따라서 새로운 메소드를 구현하는 가장 일반적인 방법은 기존에 잘 만들어진 메소드를 복사해서 구현하는 것이다. 이렇게 되면 결국 비즈니스 메소드에 부가적인 코드들이 반복해서 등장한다. 따라서 코드 분석과 유지보수를 어렵게 만든다. AOP는 이런 부가적인 공통 코드들을 효율적으로 관리하는데 주목한다.</p>
<br />
<p>AOP를 이해하는데에 가장 중요한 핵심개념이 바로 관심 분리이다. AOP에서는 메소드마다 공통으로 등장하는 로깅이나 예외, 트랜잭션 처리 같은 코드들을 횡단 관심 이라고 한다.</p>
<p>이에 반해 사용자의 요청에 따라 실제로 수행되는 핵심 비즈니스 로직을 핵심 관심 이라고 한다.</p>

![4](https://user-images.githubusercontent.com/65153512/117620514-7f685500-b1ab-11eb-9712-cb2a478ffb1f.jpg)

<p>만약 이 두 관심을 완벽하게 분리할 수 있다면, 우리가 구현하는 메소드에는 실제 비즈니스 로직만으로 구성할 수 있으므로 더욱 간결하고 응집도 높은 코드를 유지할 수 있다. 문제는 기존의 OOP(Object-Oriented Programming)언어에서는 횡단 관심에 해당하는 공통 코드를 완벽하게 독립적인 모듈로 분리해내기가 어렵다는 것이다.</p>
<p>기존의 OOP언어에서 완벽한 관심분리가 왜 어려운지 실습으로 확인해본다. 이후 AOP가 관심분리문제를 해결하는 과정을 살펴보며 AOP의 개념을 쉽게 이해해본다.</p>
<p>BoardService 컴포넌트의 모든 비즈니스 메소드가 실행되기 직전에 공통으로 처리할 로직을 LogAdvice 클래스에 printLog 메소드로 구현한다. 이렇게 구현된 printlog메소드를 boardservice 컴포넌트에서 사용할 수 있도록 boardServiceimpl클래스를 수정한다.</p>
<p>BoardServiceImpl 객체가 생성될 때, 생성자에서 LogAdvice 객체도 같이 생성한다. 그리고 각 비즈니스 메소드에서 비즈니스 로직을 수행하기 전에 LogAdvice의 printlog 메소드를 호출한다. 이후 공통기능을 수정할 때는 LogAdvice의 printlog 메소드만 수정하면 되므로 관리가 편해졌다고 할 수 있다.</p>

<br />

<p>그러나 이렇게 작성된 프로그램은 BoardServiceImpl 클래스와  LogAdvice 객체가 소스코드에서 강하게 결합되어 있어서 LogAdvice 클래스를 다른 클래스로 변경해야 하거나 공통기능에 해당하는 printLog 메소드의 시그니처가 변경되는 상화에서는 유연하게 대처할 수 없다.</p>
<p>기존에 사용하던 LogAdvice 클래스의 성능이 떨어져서 이를 대체할 Log4jAdvice 클래스를 만들고 메소드 이름도 printLogging 으로 변경한다. BoardServiceImpl클래스의 모든 메소드는 Log4jAdvice를 이용하도록 해야한다.</p>

<br />

<p>결국 Advice 클래스가 LogAdvice 에서 Log4jAdvice로 바뀌는 순간 BoardServiceImpl클래스의 생성자를 수정해야 한다. 그리고 메소드가 변경되었으므로 모두 수정해야 한다.</p>
<p>정리하면, OOP처럼 모듈화가 뛰어난 언어를 통해 개발하더라도 공통모듈에 해당하는 Advice 클래스 객체를 생성하고 공통 메소드를 호출하는 코드가 비즈니스 메소드에 있다면, 핵심 관심과 횡단 관심을 완벽하게 분리할 수는 없다. 하지만 스프링의 AOP 는 OOP의 이런 한계를 극복하게 해준다.</p>

<H2>1.2 AOP 시작하기</H2>
<p>이번에는 스프링의 AOP를 이용해서 핵심 관심과 횡단 관심을 분리한다.</p>
<p>이 실습을 통해 BoardServiceImpl 소스와 LogAdvice, Log4jAdvice 클래스의 메소드를 무관하게 실행할 수 있다.</p>

<H3>1.2.1 비즈니스 클래스 수정</H3>
<p>BoardServiceImpl 클래스를 원래상태로 되돌린다. 이로인해 LogAdvice, Log4jAdvice 클래스와 상관이 없는 클래스가 되었다.</p>

<H3>1.2.2 AOP 라이브러리 추가</H3>
<p>본격적으로 AOP를 적용하기 위해서 우선 BoardWeb 프로젝트에 있는 pom.xml 파일을 수정하여 AOP관련 라이브러리를 추가한다.</p>

~~~
<!-- AspectJ -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${org.aspectj-version}</version>
		</dependency>	
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjweaver</artifactId>
			<version>1.8.8</version>
		</dependency>
~~~

<p>aspectjweaver를 내려받을 수 있도록 설정을 추가하고, Maven Dependencies에 라이브러리가 추가되었는지 확인한다.</p>

<H3>1.2.3 네임스페이스 추가 및 AOP 설정</H3>
<p>AOP 설정을 추가하려면 AOP에서 제공하는 엘리먼트들을 사용해야 한다. 따라서 스프링 설정 파일에서 namespaces탭에서 aop 네임스페이스를 추가한다.</p>
<p>LogAdvice클래스를 bean등록한 후 AOP 관련 설정을 추가한다. 자세한 설명은 뒤에서 계속한다.</p>

<H3></H3>
<p></p>
<p></p>

<H3></H3>
<p></p>
<p></p>