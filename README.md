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

~~~
	<context:component-scan base-package="com.springbook.biz"></context:component-scan>
	<bean id="log" class="com.springbook.biz.board.Log4jAdvice"></bean>
	<aop:config>
		<aop:pointcut id="allPointcut" expression="execution(* com.springbook.biz..*Impl.*(..))"/>
		<aop:pointcut id="getPointcut" expression="execution(* com.springbook.biz..*Impl.*(..))"/>
		<aop:aspect ref="log">
			<aop:before pointcut-ref="allPointcut" method="printLogging"/>	
		</aop:aspect>
	</aop:config>
~~~

<H3>1.2.4 테스트 및 결과 확인</H3>
<p>이제 BoardServiceClient 프로그램을 실행하여 insertBoard와 getBoardList 메소드가 호출될 때 LogAdvice 클래스의 printLog 메소드가 실행되는지 확인해본다.</p>
<p>LogAdvice 를 Log4jAdivce 로 교체하고 싶으면, BoardServiceImpl를 수정할 필요 없이 스프링설정 파일의 AOP 설정만 수정하면 된다.</p>
<p>스프링의 AOP는 클라이언트가 핵심 관심에 해당하는 비즈니스 메소드를 호출할 때, 횡단 관심에 해당하는 메소드를 적절하게 실행해준다. 이때 핵심 관심 메소드와 횡단 관심 메소드 사이에서 소스상의 결합은 발생하지 않으며, 이것이 우리가 AOP를 사용하는 주된 목적이다.</p>

<H1>2. AOP 용어 및 기본 설정</H1>
<H2>2.1 AOP 용어 정리</H2>

<H3>2.1.1 조인포인트(Joinpoint)</H3>
<p>조인 포인트는 클라이언트가 호출하는 모든 비즈니스 메소드로서, BoardServiceImpl이나 UserServiceImpl 클래스의 모든 메소드를 조인 포인트라고 생각하면 된다.</p>
<p>조인포인트를 포인트컷 대상 또는 포인트컷 후보 라고도 하는데, 이는 조인포인트 중에서 포인트컷이 선택되기 때문이다.</p>

<H3>2.1.2 포인트컷(Pointcut)</H3>
<p>클라이언트가 호출하는 모든 비즈네스 메소드가 조인 포인트라면, 포인트컷은 필터링된 조인포인트를 의미한다.</p>
<p>예를들어 트랜잭션을 처리하는 공통 기능을 만들었다고 가정하자. 이 횡단 관심 기능은 등록, 수정, 삭제 기능의 비즈니스 메소드에 대해서는 당연히 동작해야 하지만, 검색 기능의 메소드에 대해서는 트랜잭션과 무관하므로 동작하지 않는다. 이렇게 수 많은 비즈니스 메소드 중에서
우리가 원하는 특정 메소드에서만 횡단  관심에 해당하는 공통기능을 수행시키기 위해서 포인트컷이 필요하다. 포인트컷을 이용하면 메소드가 포함된 클래스와 패키지는 물론이고, 메소드 시그니처까지 정확하게 지정할 수 있다.</p>
<p>포인트컷을 테스트하기 위해 스프링 설정 파일에 기존에 사용하던 포인트컷을 복사하여 하나 더 추가해본다.</p>

~~~
<aop:pointcut id="getPointcut" expression="execution(* com.springbook.biz..*Impl.get*(..))"/>
~~~
<p>포인트컷은 aop:pointcut 엘리먼트로 선언하며, id 속성으로 포인트컷을 식별하기 위한 유일한 문자열을 선언한다. 이 아이디가 나중에 포인트컷을 참조할 때 사용된다.</p>
<p>중요한것은 expression 속성인데, 이 값을 어떻게 설정하는지에 따라 필터링 되는 메소드가 달라진다.</p>
<br />
<p>* com.springbook.biz..*Impl.get*(..)</p>
<p>가장 앞의 *은 리턴타입, com.springbook.biz는 패키지 경로, *Impl은 클래스명, *(..) 혹은 get*(..)은 메소드명 및 매개변수이다.</p>
<br />
<p>처음 등록한 allPointcut은 리턴타입과 매개변수를 무시하고, com.springbook.biz 패키지로 시작하는 클래스중 Impl로 끝나는 클래스의 모든 메소드를 포인트컷으로 설정했다.</p>
<p>두번째 등록한 getPointcut은 allPointcut과 같지만 get으로 시작하는 메소드만 포인트컷으로 설정했다.</p>
<p>마지막으로 aop:before 엘리먼트에서 appPointcut 부분을 getPointcut으로 수정하여 실행해본다. insert에는 반응하지 않고 get메소드에만 반응하는 것을 볼 수있다.</p>

<H3>2.1.3 어드바이스(Advice)</H3>
<p>어드바이스는 횡단 관심에 해당하는 공통 기능의 코드를 의미하며, 독립된 클래스의 메소드로 작성된다. 어드바이스로 구현된 메소드가 언제 동작할지 스프링 설정 파일을 통해서 지정할 수 있다.</p>
<p>예를들어 트랜잭션 관리 기능의 어드바이스 메소드가 있을 때, 이 어드바이스가 비즈니스 로직이 수행되기 전에 동작하는 것은아무런 의미가 없다. 당연히 비즈니스 로직 수행 후에 트랜잭션을 커밋 또는 롤백 처리하면 된다.</p>
<br />
<p>스프링에는 어드바이스의 동작 시점을 before, after, after-returning, after-throwing,around 등 다섯 가지로 지정할 수 있는데 구체적인 동작 시점들은 이후에 자세히 살펴보도록 한다.</p>
<p>이전에 작성한 LogAdviced의 printLog메소드가 비즈니스 메소드 수행 후에 동작하도록 설정을 바꾸어 본다.</p>


<H3>2.1.4 위빙(Weaving)</H3>
<p>위빙은 포인트컷으로 지정한 핵심 관심 메소드가 호출될 때 호출될 때, 어드바이스에 해당하는 횡단 관심메소드가 삽입되는 과정을 의미한다. 이 위빙을 통해 비즈니스 메소드를 수정하지 않고도 횡단 관심에 해당하는 기능을 추가하거나 변경할 수 있다.</p>
<p>위빙을 처리하는 방식은 크게 컴파일타임위빙, 로딩타임위빙, 런타임위빙이 있지만, 스프링에서는 런타임 위빙방식만 지원한다.</p>

<H3>2.1.5 애스팩트(Aspect) 또는 어드바이저(Advisor)</H3>
<p>Aspect Oriented Programming 이라는 이름에서 알 수 있듯이 AOP의 핵심은 바로 애스팩트이다. 애스팩트는 포인트컷과 어드바이스의 결합으로서, 어떤 포인트컷 메소드에 대해서 어떤 어드바이스 메소드를 실행할지 결정한다. 이 애스팩트 설정에 따라 AOP의 동작 방식이 결정되므로 AOP용어 중 가장 중요한 개념이다.</p>
<p>우리가 지금까지 사용한 애스팩트 설정의 의미를 해석해본다.</p>

![5](https://user-images.githubusercontent.com/65153512/117635569-e4777700-b1ba-11eb-9c34-ac51422f7091.jpg)

<p>getPointcut으로 설정한 포인트컷 메소드가 호출될때①, log라는 어드바이스 객체②의 printLog메소드가 실행되고③, 이때 printLog메소드 동작 시점이 aop:before라는 내용④의 설정이다.</p>
<p>애스팩트를 설정할 때는 aop:aspect 엘리먼트를 사용하는데 가끔 aop:advisor 엘리먼트를 사용하는 경우가 있다. 대표적인 상황이 트랜잭셔느설정인데, 트랜잭션 설정에서는 advisor를 사용한다.</p>


<H3>2.1.6 AOP 용어 종합</H3>

![6](https://user-images.githubusercontent.com/65153512/117637210-8fd4fb80-b1bc-11eb-9d21-0cae2010854b.jpg)

<p>사용자는 시스템을 사용하면서 비즈니스 컴포넌트의 여러 조인포인트를 호출하게 된다. 이때 특정 포인트컷으로 지정한 메소드가 호출되는 순간, 어드바이스 객체의 어드바이스 메소드가 수행된다. 이 어드바이스 메소드의 동작 시점을 5가지로 지정할 수 있으며, 포인트컷으로 지정한 메소드가 호출될 때, 어드바이스 메소드르 삽입하도록 하는 설정을 애스팩트 라고 한다.
이 애스팩트 설정에 따라 위빙이 처리된다.</p>

<H2>2.2 AOP 엘리먼트</H2>
<p>AOP와 관련된 다양한 설정을 살펴본다. 스프링은 AOP설정을 xml방식과 어노테이션 방식으로 지원한다. 우선 xml설정을 본 뒤 어노테이션 설정을 살펴본다.</p>

<H3>2.2.1 aop:config 엘리먼트</H3>
<p>AOP 설정에서 aop:config는 루트 엘리먼트이다. 스프링 설정 파일 내에 aop:config엘리먼트는 여러번 사용할 수 있으며, 하위에는 포인트컷, 애스팩트 엘리먼트가 위치할 수 있다.</p>

<H3>2.2.2 aop:pointcut 엘리먼트</H3>
<p>aop:pointcut 엘리먼트는 포인트컷을 지정하기 위해 사용하며, config의 자식이나 aspect의 자식으로만 사용할 수 있다. 그러나 aspect의 자식으로 설정된 포인트컷은 해당 aspect에서만 사용할 수 있다.</p>
<p>aop:pointcut은 여러개 정의할 수 있으며 유일한 아이디를 할당하여 애스팩트를 설정할 때 포인트컷을 참조하는 용도로 사용한다.</p>

<H3>2.2.3 aop:aspect 엘리먼트</H3>
<p>핵심 관심에 해당하는 포인트컷 메소드와 횡단관심에 해당하는 어드바이스 메소드를 결합하기 위해 사용된다.</p>
<p>어떻게 설정하느냐에 따라 위빙 결과가 달라지므로 aop에서 가장 중요한 설정이다.</p>

<H3>2.2.4 aop:advisor 엘리먼트</H3>
<p>포인트컷과 어드바이스를 결합한다는 점에서 애스팩트와 같은 기능이나, 트랜잭션 설정 같은 몇몇 특수한 경우는 애스팩트가 아닌 어드바이저를 사용해야 한다.</p>
<p>애스팩트를 사용하려면 어드바이스의 아이디와 메소드 이름을 알아야 한다. 그러나 만약 어드바이스 객체의 아이디를 모르거나 메소드 이름을 확인할 수 없을 때는 애스팩트를 설정할 수 없다.</p>
<p>다음은 트랜잭션 처리 관련된 설정이 포함된 스프링 설정 파일이다 여기에서 핵심은 트랜잭션 관리 어드바이스 설정인데, 이 어드바이스의 아이디는 txAdvice로 설정했따. 하지만 문제는 이 어드바이스의 메소드 이름을 확인할 수 없다는 것이다.</p>

~~~
<tx:advice id = "txAdvice" transaction-manager="txManager">
	<tx:attrubutes>
		<tx:method name="get*" read-only="true"/>
		<tx:method name="*"/>
	</tx:attrubutes>
</tx:advice>

<aop:config>
	<aop:pointcut id="allPointcut" expression="execution(* com.springbook.biz..*Impl.*(..))"/>
	<aop:advisor pointcut-fef="allPointcut" advice-ref="txAdivce"/>
</aop:config>
~~~

<p>스프링 컨테이너는 설정 파일에 등록된 tx:advice 엘리먼트를 해석하여 트랜잭션 관리 기능의 어드바이스 객체를 메모리에생성한다. 그리고 어떻게 tx:advice를 설정했는지에 따라
생성되는 어드바이스의 기능과 동작 방식은 달라진다. 문제는 이렇게 생성된 어드바이스의 아이디는 확인되지만 메소드 이름은 확인할 방법이 없다. 이럴땐 애스팩트를 사용하지 못하므로 어드바이저를 사용한다.</p>

<H2>2.3 포인트컷 표현식</H2>
<p>포인트컷을 이용하면 어드바이스 메소드가 적용될 비즈니스 메소드를 정확하게 필터링할 수 있는데, 이때 다양한 포인트컷 표현식을 사용할 수 있다. 포인트컷 표현식은
메소드처럼 생긴 execution 명시자를 이용하며, execution명시자 안에 포인트컷 표현식을 기술한다.</p>

![7](https://user-images.githubusercontent.com/65153512/117641901-ac276700-b1c1-11eb-9ec0-fea417da0ec9.jpg)

![8](https://user-images.githubusercontent.com/65153512/117641912-ae89c100-b1c1-11eb-8890-0885044a3b54.jpg)

<H1>3. 어드바이스 동작 시점</H1>
<p>어드바이스는 각 조인 포인트에 삽입되어 동작할 횡단 관심에 해당하는 공통 기능이며, 동작 시점은 각 AOP 기술마다 다르다. 스프링은 다섯가지의 동작 시점을 제공한다.</p>

동작시점|설명
----|----
Before|비즈니스 메소드 실행전 동작
After|After Returning: 비즈니스 메소드가 성공적으로 리턴되면 동작
After|After Throwing: 비즈니스 메소드가 실행 중 예외 발생시 동작
After|After: 비즈니스 메소드가 수행된 후 무조건 실행
Around|메소드 호출 자체를 가로채 비즈니스 메소드 실행 전후에 처리할 로직을 삽입할 수 있음

<p>어드바이스 메소드의 동작 시점은 aop:aspect 엘리먼트 하위에 각각 aop:before, aop:after-returning,aop:after-throwing,aop:after,after-around 엘리먼트를 이용하여 저장한다.</p>
<p>이후 실습에서는 어드바이스 기능에는 의미를 두지 않고 어드바이스 동작 시점과 aop 설정을 중심적으로 다룬다.</p>


<H2>3.1 Before 어드바이스</H2>
<p>before 어드바이스는 포인트컷으로 지정된 메스도 호출시, 메소드가실행되기 전에 처리될 내용들을 기술하기 위해 사용된다.</p>
<p>Before 어드바이스를 테스트하기 위해 비즈니스 메소드가 실행되기 전에 수행될 기능을 어드바이스 클래스로 구현한다.</p>
<p>BeforeAdvice 클래스에 선언한 beforeLog() 메소드가 Before 형태로 동작하도록 스프링 설정 파일을 수정한다.</p>
<p>어드바이스메소드를 BeforeLog로 수행하려면 aop:before 엘리먼트를 사용한다. </p>

<H2>3.2 After Returning 어드바이스</H2>
<p>after returning 어드바이스는 포인트컷으로 지정된 메소드가 정상적으로 실행되고 나서, 메소드 수행 결과로 생성된 데이터를 리턴하는 시점에 동작한다.</p>
<p>따라서 비즈니스 메소드 수행 결과로 얻은 결과 데이터를 이용하여 사후 처리 로직을 추가할 때 사용한다.</p>
<p>After Returning 어드바이스를 테스트하기 위해 비즈니스 메소드가 실행되고 나서 수행될 기능을 어드바이스 클래스로 구현한다.</p>
<p>이 설정은 XXXImpl의 모든 메소드가 실행된 후에 afterReturningAdvice 로 지정한 어드바이스의 afterLog()메소드가 실행되도록 설정한 것이다.</p>

<H2>3.3 After Throwing 어드바이스</H2>
<p>After Throwing 어드바이스는 포인트컷으로 지정한 메소드가 실행되다가 예외가 발생하는 시점에 동작한다. 예외처리 어드바이스를 설정할 때 사용한다.</p>
<p>After Throwing 어드바이스를 테스트하기 위해 예외처리 기능의 어드바이스 클래스를 구현하고 스프링 설정을 변경한다.</p>
<p>After Throwing  어드바이스는 aop:after-throwing 엘리먼트를 이용하여 설정한다. 비즈니스메소드에서 예외가 발생할 때 동작하므로 예외가 발생하지 않은 현재 상황에서는 동작여부를 확인할 수 없다. 따라서 BoardServiceImpl클래스에 예외가 발생하는 코드를 추가한다.</p>

<H2>3.4 after 어드바이스</H2>
<p>try-catch-finally 구문에서 finally 블록처럼 예외 발생 여부에 상관없이 무조건 수행되는 어드바이스를 통해 after 어드바이스를 사용한다.</p>
<p>after어드바이스를 테스트하기 위해 예외처리 기능의 어드바이스 클래스를 구현하고 스프링 설정파일에 after 애스팩트를 추가한다.</p>
<p>after 어드바이스는 aop:after 엘리먼트를 이용하여 설정한다. 예외가 발생한 상황에서도 after로 설정한 메소드가 먼저 실행되고, exceptionlog메소드가 실행된다.</p>

<H2>3.5 Around 어드바이스</H2>
<p>하나의 어드바이스가 비즈니스 메소드 실행 전과 후에 모두 동작하여 로직을 처리하는 경우 Around 어드바이스를 사용한다.</p>
<p>Around 어드바이스는 클라이언트의 메소드 호출을 가로챈다. 그래서 클라이언트가 호출한 비즈니스 메소드가 실행되기 전에 사전 처리 로직을 수행할 수 있으며, 비즈니스 메소드가 모두 실행되고 나서 사후 처리 로직을 수행할 수 있다.</p>
<p>Around어드바이스 클래스를 작성한다.</p>
<p>pjp.proceed() 메소드 호출 앞에 작성된 코드는 before어드바이스와 동일하게, 이후에 작성된 코드는 after 어드바이스와 동일하게 동작한다</p>
<p>클라이언트의 요청을 가로챈 어드바이스는 클라이언트가 호출한 비즈니스 메소드를 호출하기 위해 ProceedingJoinPoint 객체를 매개변수로 받아야한다.
스프링 설정파일에 애스팩트 설정을 추가한다.</p>

<H3></H3>
<p></p>
<p></p>