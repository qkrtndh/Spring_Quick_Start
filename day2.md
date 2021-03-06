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

<H1>4. JoinPoint와 바인드 변수</H1>
<p>횡단 관심에 해당하는 어드바이스 메소드를 의미 있게 구현하려면 클라이언트가 호출한 비즈니스 메소드의 정보가 필요하다. 예를들면 after Throwing 기능의 어드바이스 메소드를 구현할때,예외가 발생한 비즈니스 메소드 이름이 무엇인지 그 메소드가 속한 클래스와 패키지 정보는
무엇인지 알아야 정확한 예외 처리 로직을 구현할 수 있다. 스프링에서는 이런 다양한 정보들을 이용할 수 있도록 JoinPoint 인터페이스를 제공한다.</p>

<H2>4.1 JoinPoint 메소드</H2>
<p>4-1</p>

메소드|설명
----|----
Signature getSignature()|클라이언트가 호출한 메소드의 시그니처(리턴타입, 이름, 매개변수) 정보가 저장된 Signature 객체 패턴
Object getTarget()|클라이언트가 호출한 비즈니스 메소드를 포함하는 비즈니스 객체 리턴
Object[] getArgs()|클라이언트가 메소드를 호출할 때 넘겨준 인자 목록을 Object 배열로 리턴

<p>우리가 이전에 around 어드바이스 메소드를 구현할 때 사용한 ProceedingJoinPoint 인터페이스는 JoinPoint를 상속했다. 따라서 JoinPoint가 가진 모든 메소드를 지원하며, proceed()메소드를 추가했다고 생각하면 된다.</p>
<p>주의할 점은 bfore,after,after-returning,after-throwing어드바이스에서는 JoinPoint를, Around만 ProceedingJoinPoint를 매개변수로 사용해야 한다.</p>
<p>getSignature()메소드가 리턴하는 Signature 객체를 이용하면, 호출되는 메소드에 대한 다양한 정보를 얻을 수 있다.</p>

메소드|설명
----|----
String getName()|클라이언트가 호출한 메소드 이름 리턴
String toLongStgring()|클라이언트가 호출한 메소드의 리턴타입, 이름, 매개변수를 패키지 경로까지 포함하여 리턴
String toShortString()|클라이언트가 호출한 메소드 시그니처를 축약한 문자열로 리

<p>JoinPoint는 어드바이스 종류에 따라 사용방법이 다소 다르다. 각 어드바이스에서 joinpoint를 사용하는 방법을 살펴보자</p>
<p>JoinPoint 객체를 사용하려면 단지 JoinPoint를 어드바이스 메소드 매개변수로 선언만 하면 된다. 그러면 클라이언트가 비즈니스 메소드를 호출할  떄, 스프링 컨테이너가 JoinPoint객체를 생성하고 메소드 호출과 관련된 모든 정보를 joinpoint 객체에 저장하여 어드바이스 메소드를 호출할때 인자로 넘겨준다.</p>

<H2>4.2 Before 어드바이스</H2>
<p>before 어드바이스는 비즈니스 메소드가 실행되기 전에 동작할 로직을 구현한다. 따라서 호출된 메소드 시그니처만 알 수 있으면 다양한 사전 처리 로직을 구현할 수 있다.</p>
<p>이때 Before 어드바이스 메소드의 매개변수로 JoinPoint를 선언한다.</p>
<p>JoinPoint 객체의 getSignature() 메소드를 이용하면 클라이언트가 호출한 메소드 이름을 출력할 수 있고 getArgs메소드를 통해 인자 목록을 Object배열로 얻어낼 수 있어, 매소드 호출에 어떤 값을 이용햇는지 알 수 있다.</p>
<p>이전의 before어드바이스 실습때 xml 설정을 사용한다. UserServiceClient를 실행한다.</p>
<p>getUser메소드가 호출되었고 이때 UserVO객체가 인자로 전달된 것을 확인할 수 있다. 또 UserVO객체에 설정된 아이디와 비밀번호 정보도 확인할 수 있다.</p>

<H2>4.3 After Returning 어드바이스</H2>
<p>After Returning은 비즈니스 메소드가 수행되고 나서, 결과 데이터를 리턴할  때 동작하는 어드바이스 이다. 따라서 어떤 메소드가 어떤 값을 리턴했는지 알아야 사후 처리 기능을 다양하게 구현할 수 있다. 기존에 작성한 AfterReutruningAdvice를 비즈니스 메소드가 리턴한 값을 이용하여 동작하도록 수정한다.</p>
<p>afterLog 메소드는 클라이언트가 호출한 비즈니스 메소드 정보를 알아내기 위해서 JoinPoint 객체를 첫번째 매개변수로 성너한다. 그리고 Object 타입의 변수도 두 번째 매개변수로 선언되어 있는데, 이를 바인드 변수 라고 한다. 바인드변수는 비즈니스 메소드가 리턴한 결과값을 바인딩할 목적으로 사용하며,
어떤 값이 리턴될 지 모르기 때문에 Object 타입으로 선언한다</p>
<p>After Returning 어드바이스 메소드에 JoinPoint만 선언되어 있다면 스프링 설정 파일은 수정하지 않아도 된다. 하지만 바인드 변수가 추가되었다면 반드시 바인드 변수에 대한 매핑 설정을 스프링 설정 파일에 추가해야 한다. 이때 aop:after-returning 엘리먼트의 returning 속성을 사용한다.</p>
<p>(코드참조) 이 설정은 비즈니스 메소드가 리턴한 결과값을 returnObj라는 바인드 변수에 바인드하라는 설정이다. returning 속성은 aop:after-returning 엘리먼트에서만 사용할 수 있는 속성이며, 속성값은 반드시 어드바이스 메소드 매개변수로 선언된 바인드 변수 이름과 같아야한다.</p>

<H2>4.4 After Throwing 어드바이스</H2>
<p>After Throwing은 비즈니스 메소드가 수행되다다 예외가 발생할 때 동작하는 어드바이스 이다. 따라서 어떤 메소드에서 예외가 발생했는지를 알아야 한다. 그래야 발생한 예외의 종류에 따라 정확한 예외 처리를 구현할 수 있을 것이다.</p>
<p>기존의 Afger ThrowingAdvice 를 수정하여 예외가 발생한 메소드 이름과 발생한 예외 객체의 메세지를 출력하도록 수정한다.</p>
<p>exceptLog 메소드는 예외가 발생한 메소드 정보를 알아내기 위해서 JoinPoint 객체를 매개변수로 받는다. 그리고 after returning처럼 비즈니스 메소드에서 발생한 예외 객체를 exceptObj라는 바인드 변수를 통해 받는다. 바인드 변수는 모든 예외 객체를 바인드 할 수 있도록 최상위 타입인 Exception으로 선언한다.</p>
<p>After Throwing 어드바이스 메소드에 JoinPoint만 선언되어 있다면 스프링 설정파일은 수정할 필요 없지만, 바인드 변수가 추가되었다면 바인드 변수에 대한 매핑설정을 스프링 설정 파일에 추가해야 한다.
 aop:after-throwing 엘리먼트의 throwing 속성을 사용한다.</p>
<p>이 설정은 비즈니스 메소드에서 발생한 예외 객체를 exceptObj라는 바인드 변수에 바인드 하라는 설정이다. throwing속성은 aop:afger-throwing 엘리먼트에서만 사용할 수 있는 속성이며, 속성값은 어드바이스 메소드 매개변수로 선언된 바인드 변수 이름과 같아야 한다.</p>
<p>발생한 예외 객체의 종류에 따라 다양하게 예외처리를 할 수도 있다.</p>

<H2>4.5 Around 어드바이스</H2>
<p>Around 어드바이스는 다른 어드바이스와는 다르게 반드시 ProceedingJoinPoint 객체를 배개변수로 받아야 한다. ProceedingJoinPoint 객체는 비즈니스 메소드를 호출하는 proceed() 메소드를 가지고 있으며 JoinPoint를 상속한다.</p>

<H1>5. 어노테이션 기반 AOP</H1>
<p>스프링 IoC를 학습하면서 XML기반 설정과 어노테이션 기반 설정을 모두 사용했었다. 그리고 xml과 어노테이션 설정을 적절히 혼합하여 사영하면 xml 설정을 최소화하면서 객체들을 효율적으로 관리할 수 있었다. 스프링 AOP도 IoC와 마찬가지로 어노테이션 설정을 지원한다.</p>

<H2>5.1 어노테이션 기반 AOP 설정</H2>
<H3>5.1.1 어노테이션 사용을 위한 스프링 설정</H3>
<p>AOP관련 어노테이션 종류와 기능에 대해서 살펴본다. AOP를 어노테이션으로 설정하려면 가장 먼저 스프링 설정 파일에 aop:aspectj-autoporxy 엘리먼트를 선언해야 한다.</p>

~~~
<aop:aspectj-autoproxy></aop:aspectj-autoproxy>0
혹은
<aop:aspectj-autoproxy />
~~~

<p> aop:aspectj-autoproxy 엘리먼트만 선언하면 스프링 컨테이너는 AOP관련 어노테이션들을 인식하고 용도에 맞게 처리해준다.</p>
<p>AOP 관련 어노테이션들은 어드바이스 클래스에 설정한다. 그리고 어드바이스 클래스에 선언된 어노테이션들을 스프링 컨테이너가 처리하게 하려면, 반드시 어드바잇 객체가 생성되어 있어야 한다.
따라서 어드바이스 클래스는 반드시 스프링 설정 파일에 bean등록하거나 @service 어노테이션을 사용하여 컴포넌트가 검색될 수 있도록 해야 한다.</p>
<p>앞으로의 실습에서는 bean 등록 대신 @Service 어노테이션을 사용한다.</p>

<H3>5.1.2 포인트컷 설정</H3>
<p>XML 설정에서 포인트컷을 선언할 때는 aop:pointcut 엘리먼트를 사용했다. 그리고 선언된 여러 포인트컷을 식별하기 위한 유일한 아이디를 지정했으며, 이후에 애스팩트 설정에서 포인트컷을 참조할 수 있었다.</p>
<p>어노테이션 설정으로 포인트컷을 선언할 때는 @Pointcut 을 사용하며, 하나의 어드바이스 클래스 안에 여러개의 포인트컷을 선언할 수 있다. 따라서 여러개의 포인트컷을 구분하기 위한 식별자가 필요한데, 이때 참조 메소드를 사용한다.</p>
<p>참조 메소드는 메소드 몸체가 비어 있는, 즉 구현 로직이 없는 메소드이다. 따라서 어떤 기능 처리를 목적으로 하지 않고, 단순히 포인트 컷을 식별하는 이름으로만 사용된다.</p>
<p>(코드) appPointcut과 getPointcut 메소드 위에 각각 @Pointcut 을 이용하여 두개의 포인트 컷을 선언했다. 그러면 이후에 이 포인트컷을 참조할 때 @Pointcut 이 붙은 참조 메소드 이름을 이용하여 특정 포인트컷을 지정할 수 있다.</p>

<H3>5.1.3 어드바이스 설정</H3>
<p>어드바이스 클래스에는 횡단 관심에 해당하는 어드바이스 메소드가 구현되어 있다. 이 어드바이스 메소드가 언제 동작할지 결정하여 관련된 어노테이션을 메소드 위에 설정하면된다. 어드바이스의 동작 시점은 xml과 마찬가지로 5가지가 제공된다.</p>
<p>이 때 반드시 어드바이스 메소드가 결합될 포인트컷을 참조해야 한다. 포인트컷을 참조하는 방법은 어드바이스 어노테이션 뒤에괄호를 추가하고 참조메소드를 지정하면 된다.</p>
<p>(코드) 이 설정은 allPointcut 참조 메소드로 지정한 비즈니스 메소드가 호출될 때 (*Impl클래스의 메소드)  어드바이스 메소드인 pringLog 메소드가 before형태로 동작하도록 설정한 것이다.</p>

어노테이션|설명
----|----
@Before|비즈니스 메소드 실행 전에 동작
@AfterReturning|비즈니스 메소드가 성공적으로 리턴되면 동작
@AfterThrowing|비즈니스 메소드 실행 중 예외가 발생하면 동작
@After|비즈니스 메소드가 실행된 후, 무조건 실행
@Around|호출 자체를 가로채 비즈니스 메소드 실행 전후에 처리할 로직을 삽입할 수 있음.

<H3>5.1.4 애스팩트 설정</H3>
<p>AOP 설정에서 가장 중요한 애스팩트는 @Aspect를 이용하여 설정한다. 애스팩트는 용어 정리 시간에 봤듯이 포인트컷과 어드바이스의 결합이다. 따라서 @Aspect가 설정된 애스팩트 객체에는 반드시 포인트컷과 어드바이스를 결합하는 설정이 있어야 한다.</p>
<p>LogAdivce 클래스 위에 @Aspect가 설정되었으므로 스프링 컨테이너는 LogAdvice객체를 애스팩트 객체로 인식한다. 그리고 LogAdvice에는 포인트컷 메소드(allPointcut)와 어드바이스 메소드(printLog)가 선언되어 있는데, 이 두 메소드에 설정된 어노테이션에 의해 위빙이 처리된다.</p>
<p>(코드) allPoint 메소드로 지정된 포인트컷 메소드가 호출될 때 printLog라는 어드바이스 메소드가 실행되도록 설정한 것이다. 그리고 이 printLog 메소드 위에 @Before가 설정되었으므로 사전처리 형태로 동작한다.</p>

<H2>5.2 어드바이스 동작 시점</H2>
<p>지금부터는 이전에 XML기반으로 설정했던 각 어드바이스들을 어노테이션으로 변경한다.</p>

<H3>5.2.1 Before 어드바이스</H3>
<p>Before 어드바이스는 비즈니스 메소드가 실해오디기 전에 공통으로 처리할 작업을 위해 사용한다. 앞에서 작성한 BeforeAdvice 클래스에 관련된 어노테이션을 추가한다.</p>
<p>우선 클래스 선어부에 @Service와 @Aspect를 추가하여 BeforeAdvice 클래스가 컴포넌트 스캔되어 애스팩트 객체로 인식되도록 했다. 그리고 allPointcut() 참조 메소드를 추가하여 포인트컷을 선언했다. 마지막으로 beforeLog()메소드 위에 @Before를 추가하여 allPointcut()으로 지정한 메소드가 호출될 때, beforeLog() 메소드가 Before 형태로 동작하도록 설정했다.</p>

<H3>5.2.2 After Returning 어드바이스</H3>
<p>After Returning 어드바이스는 비즈니스 메소드가 리턴한 결과 데이터를 다른 용도로 처리할 때 사용한다. 앞에서 작성한 AfterReturningAdvice 클래스에 어노테이션을 추가한다.</p>
<p>afterLog 메소드가 After Returning 형태로 동작하도록 메소드 위에 @AfterReturning 어노테이션을 추가한다. AfterReturning은  @Before와 다르게, pointcut속성을 이용하여 포인트컷을 참조한다. 이는 after returning 어드바이스가 비즈니스 메소드 수행 결과를 받아내기 위해서 바인드 변수를 지정해야 하기 때문이다.</p>
<p>xml 설정에서는 returning 속성을 사용하여 바인드 변수를 명확하게 지정할 수 있었다. 어노테이션 설정에서도 reutrning 속성을 이용하여 바인드 변수를 지정할 수 있다.</p>

<H3>5.2.3 After Throwing 어드바이스</H3>
<p>After Throwing 어드바이스는 비즈니스 메소드 실행 도중에 예외가 발생했을 때 공통적인 예외 처리 로직을 제공할 목적으로 사용하는 어드바이스 이다. 앞에서 작성한 AfterThrowingAdvice클래스에 관련된 어노테이션을 추가한다.</p>
<p>exceptionLog메소드가 after Throwing 형태로 동작하도록 메소드 위에 @AfterThrowing 어노테이션을 추가했다. @AfterThrwing은 앞에서 설정한 @AfterReturning과 마찬가지로 pointcut 속성을 이용하여 포인트컷을 참조한다. 비즈니스 메소드에서 발생된 예외 객체를 받아낼 바인드 변수를 지정해야 하기 때문이다.</p>

<H3>5.2.4 After 어드바이스</H3>
<p>After 어드바이스는 예외 발생 여부 상관 없이 무조건 수행되는 어드바이스로서 @After 어노테이션을 사용하여 설정한다.</p>
<p>finallyLog메소드가 AfterAdvice 형태로 동작하도록 @After 어노테이션을 선언했다. 바인드 변수가 없으므로 @Before처럼 포인트컷 메소드만참조한다.</p>

<H3>5.2.5 Around 어드바이스 설정</H3>
<p>Around 어드바이스는 하나의 어드바이스로 사전, 사후 처리를 모두 해결하고자 할 때 사용하며, @Around어노테이션을 사용하여 설정한다.</p>
<p>aroundLog 메소드를 Around어드바이스로 동작시키기 위해서 메소드 위에 @Around어노테이션을 추가했다. 그리고 aroundLog 메소드도 바인드 변수가 없으므로 포인트컷 메소드만 참조함녀 된다.</p>
<p>주의할 점으로 오직 around메소드에서만 JoinPoint가 아닌 ProceedingJoinPoint를 사용한다.</p>

<H3>5.2.6 외부 Pointcut 참조하기</H3>
<p>XML 설정으로 포인트컷을 관리했을 때는 스프링 설정 파일에 포인트컷을 여러 개 등록했다. 그리고 애스팩트를 설정할 때 pointcut-ref속성으로 특정 포인트컷을 참조할 수 있었기 때문에 포인트컷을 재사용할 수 있었다.</p>
<p>하지만 어노테이션 설정으로 변경하고 부터는 어드바이스 클래스마다 포인트컷 설정이 포함되면서 비슷하거나 같은 포인트컷이 반복 선언되는 문제가 발생한다. 스프링은 이런 문제를 해결하고자 포인트컷을 외부에 독립된 클래스에 따로 설정하도록 한다.</p>
<p>시스템에서 사용할 모든 포인트컷을 PointcutCommon 클래스에 등록한다. 이렇게 정의된 포인트컷을 참조하려면 클래스 이름과 참조 메소드 이름을 조합하여 지정해야 한다. BeforeAdvice클래스를 수정해본다.</p>
<p>포인트컷에 대한 소스는 삭제되고 @Before 어노테이션에서 PointcutCommon클래스의 allPointcut 메소드를 참조하고 있다.</p>
<p>바인드 변수가 있을 때도 포인트컷 클래스의 메소드를 참조하는 것은 같다. AfterReturningAdvice를 수정한다.</p>

<H1>6. 스프링JDBC</H1>
<H2>6.1 스프링JDBC의 개념</H2>
<p>JDBC는 가장 오랬동안 자바 개발자들이 사용한 DB연동 기술이다. JDBC를 이용하여 DB연동 프로그램을 개발하면 데이터베이스에 비종속적인 DB 연동 로직을 구현할 수 있다.
그런데 JDBC프로그램은 이용하려면 개발자가 작성해야 할 코드가 너무 많다.</p>
<p>기존 UserDAO 클래스의 구성을 보면, 사용자가 입력한 아이디와 비밀번호와 매칭되는 회원 정보 하나를 조회하기 위해 많은 자바 코드를 사용한다. JDBCUtil 클래스를 사용하더라도 많은 양의 코드가 필요하다. 더 심각한 문제는 이런 JDBC관련 코드들은 다른 메소드들을 구현할 때 마다 반복해서 작성해야 한다는 점이다.</p>
<p>JDBCUtil 클래스를 사용하여 구현한한 BoardDAO 클래스에서 insertBoard, updateBoard 메소드는 실행되는 SQL구문과 ? 에 설정하는 값만 다를 뿐, JDBC에 해당하는 자바 코드는 거의 같다.</p>
<p>이런 환경에서 새로운 기능의 메소드를 개발하려면, 결국 기존 메소드를 복사하여 SQL만 수정하는 방법 뿐이다. 그런데 만약 DB연동에 필요한 자바 코드를 누가 대신 처리해 주고 개발자는 실행되는 SQL구문만 관리한다면 개발과 유지보수는 훨씬 편리해진다.
스프링은 JDBC기반의 DB연동 프로그램을 쉽게 개발할 수 있도록 JdbcTemplate클래스를 지원한다.</p>

<H2>6.2 JdbcTemplate 클래스</H2>
<p>JdbcTemplate은 GoF 디자인 패턴중 템플릿 메소드 패턴이 적용된 클래스이다. 템플릿 메소드 패턴은 복잡하고 반복되는 알고리즘을 캡슐화해서 재샤용하는 패턴으로 정의할 수 있다. 템플릿 메소드 패턴을 이용하면 반복해서 사용되는 알고리즘을 템플릿 메소드로 캡슐화 할 수 있어서 JDBC처럼 코딩순서가 정형화된 기술에서 유용하게 사용할 수 있다.</p>
<p>따라서 반복되는 DB연동 로직은 JdbcTemplate 클래스의 템플릿 메소드가 제공하고, 개발자는 달라지는 SQL 구문과 설정값만 신경 쓰면 된다.</p>

![9](https://user-images.githubusercontent.com/65153512/117794746-7d27f880-b288-11eb-8d8b-5b8fd23776b6.jpg)

<p>위 그림은 DB연동 프로그램을 개발할 때 JdbcTemplate 클래스의 위치와 역할을 그림으로 표현한 것이다.</p>
<p>JdbcTemplate은 JDBC의 반복적인 코드를 제거하기 위해 제공되는 클래스다. 따라서 DAO클래스에서는 JdbcTemplate 클래스가 제공하는 템플릿 메소드를 호출하여 DB연동을 간단하게 처리할 수 있다.</p>
<p>그러면 JdbcTemplate 클래스는 내부적으로 JDBC API를 이용하여 실제 DB연동 작업을 처리한다. 하지만 JdbcTemplate 클래스가 어떻게 JDBC API를 이용하는지 DAO 클래스 개발자는 전혀 신경 쓸 필요 없다.</p>


<H2>6.3 스프링 JDBC 설정</H2>
<H3>6.3.1 라이브러리 추가</H3>
<p>스프링 JDBC를 이용하려면 BoardWeb 프로젝트에 있는 pom.xml 파일에 DBCP관련 depencency 설정을 추가해야 한다.</p>

~~~
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		<!-- DBCP -->
		<dependency>
			<groupId>commons-dbcp</groupId>
			<artifactId>commons-dbcp</artifactId>
			<version>1.4</version>
		</dependency>
~~~

<p>설정 추가 후 라이브러리에 DBCP라이브러리가 등록되었는지 확인한다.</p>

<H3>6.3.2 DataSource 설정</H3>
<p>JdbcTemplate 클래스가 JDBC API를 이용하여 DB연동을 처리하려면 반드시 데이터베이스로부터 커넥션을 얻어야 한다. 따라서 JdbcTemplate 객체가 사용할 DataSource를 bean 등록하여 스프링 컨테이너가 생성하도록 해야 한다.</p>
<p>사실 DataSource 설정은 스프링 JDBC만을 위한 설정은 아니다. 이후에 테스트할 트랜잭션 처리나, Mybatis 연동, JPA 연동에서도 DataSource가 사용되므로 매우 중요한 설정이라고 할 수 있다.</p>
<p>DataSource 인터페이스를 구현한 클래스는 다양하지만, 일반적으로 가장 많이 사용하는 Apache의 BasicDataSource를 등록한다. BasicDataSource 객체는 연결에 필요한 프로퍼티 들을 setter 인젝션으로 설정해주면 된다. 그리고 BasicDataSource 객첵 삭제되기 전에 연결을 해제하고자 close() 메소드를 destroy-method 속성으로 지정한다.</p>

<p>XML 설정</p>

~~~
	<!-- DataSource 설정 -->
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
		<property name="driverClassName" value="org.h2.Driver"></property>
		<property name="url" value="jdbc:h2:~/test"></property>
		<property name="username" value="sa"></property>
		<property name="password" value=""></property>
	</bean>
~~~


<H3>6.3.3 프로퍼티 파일을 활용한 DataSource 설정</H3>
<p>PropertyPlaceholderConfigurer를 이용하면 외부의 프로퍼티 파일을 참조하여 DataSource를 설정할 수 있다.</p>
<p>실습을 위해 src/main/resources 소스 폴더에 config폴더를 생성하고 config폴더에 database.properties파일을 작성한다.</p>

~~~
jdbc.driver=org.h2.driver
jdbc.url=jdbc:h2:~/test
jdbc.username=sa
jdbc.password=
~~~

<p>이제 Properties 파일에 설정된 프로퍼티들을 이용하여 DataSource를 설정하려면 다음과 같이 context:property-placeholder 엘리먼트를 사용한다.</p>
<p>프로퍼티 파일을 사용하려면 context:property-placeholder 엘리먼트로 프로퍼티 파일의 위치를 등록해야 한다. 그리고 ${}구문을 이용하여 프로퍼티 이름을 지정하면 프로퍼티 값으로 치환하여 실행된다.</p>

<H2>6.4</H2>
<p>스프링 JDBC를 위한 기본 설정이 마무리됐으면 이제 JdbcTemplate 객체를 이용하여 DB연동을 간단하게 처리할 수 있다.</p>

<H3>6.4.1 update() 메소드</H3>
<p>INSERT, UPDATE, DELETE 구문을 처리하려면 JdbcTemplate 클래스의 update 메소드를 사용한다. update 메소드의 사용법은 ?에 값을 설정하는 방식에 따라 크게 두 가지 형태가 있다.</p>
<p>첫번째는 SQL구문에 설정된 ? 수만큼 값들을 차례대로 나열하는 것이다.</p>

~~~
메소드 - int update(String sql, Object . . . args)
	
	//글 수정
	public void updateBoard(BoardVO vo){
사용예		String BOARD_UPDATE="update board set title = ?, content=?, where seq=?";
		int cnt = jdbcTemplate.update(BOARD_UPDATE,vo.getTitle(),vo.getContent(),vo.getSeq());
		System.out.println(cnt + "건 데이터 수정");
	}
~~~

<p>두번째는 Object 배열 객체에 SQL 구성문에 설정된 ? 수만큼 값들을 세팅하여 배열 객체를 두번째 인자로 전달하는 방식이다.</p>

~~~
메소드 - int update(String sql, Object[] args)
	
	//글 수정
	public void updateBoard(BoardVO vo){
		String BOARD_UPDATE="update board set title = ?, content=?, where seq=?";
사용예		Object[] args = {vo.getTitle(),vo.getContent(),vo.getSeq()};
		int cnt = jdbcTemplate.update(BOARD_UPDATE,args);
		System.out.println(cnt + "건 데이터 수정");
	}
~~~

<H3>6.4.2 queryForInt() 메소드</H3>
<p>SELECT 구문으로 검색된 적숫값을 리턴 받으려면 queryForInt() 메소드를 사용한다. 매개변수의 의미는 update메소드와 같다</p>

~~~
메소드 - int queryForInt(String sql)
	int queryForInt(String sql, Object... args)
	int queryForInt(String sql, Object[] args)
	
	//전체 개시글 수 조회
	public int getBoardTotalCount(BoardVO vo){
사용예		String BOARD_TOT_COUNT="select count(*) from board";
		int cnt = jdbcTemplate.queryForInt(BOARD_TOT_COUNT);
		System.out.println("전체 게시글 수 : "+cnt + "건");
	}
~~~

<H3>6.4.3 queryForObject() 메소드</H3>
<p>queryForObject 메소드는 select 구문의실행 결과를 특정 자바 객체(value Object)로 매핑하여 리턴받을 때 사용한다. queryForObject 메소드는 검색 결과가 없거나 검색 결과가 두 개 이상이면 예외를 발생시킨다.</p>
<p>중요한 것은 검색 결과를 자바 객체로 매핑할 RowMapper 객체를 반드시 지정해야 한다.</p>

~~~
메소드 - Object queryForObject(String sql)
	Object queryForObject(String sql, RowMapper<T> rowMapper)
	Object queryForObject(String sql, Object[] args, RowMapper<T> rowMapper)
	
	//글 상세 조회
	public BoardVO getBoard(BoardVO vo){
사용예		String BOARD_GET="select * from board where seq =?";
		int cnt = jdbcTemplate.queryForInt(BOARD_TOT_COUNT);
		System.out.println("전체 게시글 수 : "+cnt + "건");
	}
~~~

<p>검색 결과를 특정 VO객체에 매핑하여 리턴하려면 RowMapper 인터페이스를 구현한 RowMapper 클래스가 필요하다. 결국 RowMapper 클래스는 테이블당 하나씩은 필요하다.</p>
<p>RowMapper 인터페이스에는 mapRow 메소드가 있어서 검색 결과로 얻어낸 Row 정보를 어떤 VO에 어떻게 매핑할 것인지를 구현해주면 된다.</p>
<p>RowMapper 객체를 queryForObject 메소드의 매개변수로 넘겨주면, 스프링 컨테이너는 SQL 구문을 수행한 후 자동으로 RowMapper 객체의 mapRow 메소드를 호출한다</p>

<H3>6.4.4 query 메소드</H3>
<p>queryForObject가 select 문으로 객체 하나를 검색할 때 사용하는 메소드라면, query메소드는 select 문의 실행 결과가 목록일 때 사용한다.
기본사용법은 queryForObject 메소드와 같다. 따라서 query 메소드에서도 검색 결과를 VO객체에 매핑하려면 RowMapper 객체를 사용한다.</p>

~~~
메소드 - List query(String sql)
	List query(String sql, RowMapper<T> rowMapper)
	List query(String sql, Object[] args, RowMapper<T> rowMapper)
	
	//글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo){
사용예		String BOARD_LIST="select * from board order by seq desc";
		return jdbcTemplate.query(BOARD_LIST,new BoardRowMapper());
	}
~~~
<p>query 메소드가 실행되면 여러건의 ROW정보가 검색되며 검색된 데이터 ROW 수만큼 RowMapper 객체의 mapRow 메소드가 실행된다. 그리고 이렇게 ROW 정보가 매핑된 VO 객체 여러개가 List 컬렉션에 저장되어 리턴된다.</p>

<H2>DAO 클래스 구현</H2>
<p>스프링 JDBC를 이용하기 위한 모든 설정이 마무리됐으면, 이제 JdbcTemplate 객체를 이용하여 DAO클래스만 구현하면 된다. 그런데 DAO 클래스에서 JdbcTemplate 객체를 얻는 방법은 두 가지이다.</p>
<H3>6.5.1 첫번째 방법 : JdbcDaoSupport 클래스 상속</H3>
<p>DAO 클래스를 구현할 때, JdbcDaoSupport클래스를 부모 클래스로 지정하면(extends JdbcDaoSupport) getJdbcTemplate 메소드를 상속 받을 수 있다. 
그리고 getJdbcTemplate 메소드를 호출하면 JdbcTemplate 객체가 리턴되어 모든 메소드를 JdbcTemplate 객체로 구현할 수 있다.</p>
<p>그런데 문제는 getJdbcTemplate 메소드가 JdbcTemplate 객체를 리턴하려면 DataSource 객체를 가지고 있어야 한다. 따라서 반드시 부모 클래스인 JdbcDaoSupport 클래스의 setDataSource 메소드를 호출하여 데이터 소스 객체 의존성을 주입해야 한다.</p>
<p>@Autowired 어노테이션은 주로 변수 위에 선언하는데 메소드 위에 선언해도 동작ㅎ나다 . 해당 메소드를 스프링 컨테이너가 자동으로 호출해주며, 이때 메소드 배개변수 타입을 확인하고 해당 타입의 객체가 메모리에 존재하면 그 객체를 인자로 넘겨준다.</p>


<H3>6.5.2 두번째 방법 JdbcTemplate 클래스 bean 등록, 의존성 주입.</H3>
<p>DAO 클래스에서 JdbcTemplate 객체를 얻는 두 번째 방법은 JdbcTemplate 클래스를 bean 등록하고, 의존성 주입으로 처리하는 것이다. 일반적으로 이 방법을 사용한다. 
먼저 스프링 설정 파일에 JdbcTemplate클래스를 bean 등록한다.</p>
<p>이때 반드시 JdbcTemplate객체에 DataSource 객체를 의존성 주입해야 한다. 그리고나서 DAO 클래스에는 @Autowired 어노테이션을 이용하여 JdbcTemplate 타입의 객체를 의존성 주입 처리하면 된다.</p>
<p>JdbcDaoSupport 클래스를 상속하여 구현하는 것 보다 더 깔끔해진 것을 볼 수 있다. JdbcTemplate 객체를 이용하여 BoardDAOSpring 클래스를 구현했으면 BoardServiceImpl클래스가 BoardDAOSpring 객체를 이용하여 DB 연동을 처리하도록 수정한다.</p>

<H1>7. 트랜잭션 처리</H1>
<p>스프링과 비교되는 EJB는 모든 비즈니스 메소드에 대한 트랜잭션 관리를 EJB 컨테이너가 자동으로 처리해준다. 스프링도 트랜잭션 처리를 컨테이너가 자동으로 처리하도록 설정할 수 있는데, 이를 선언적 트랜잭션 처리라고 한다.</p>
<p>스프링의 트랜잭션 설정에서는 앞에서 학습한 AOP가 사용된다. 그런데 XML기반의 AOP설정만 사용할 수 있고, 어노테이션은 사용할 수 없다.  그리고 애스팩트를 설정하는 것도 aop:aspect 엘리먼트를 사용하지 못하고 aop:advise 엘리먼트를 사용해야 한다.
이는 트랜잭션 관리에 사용되는 어드바이스가 독특하기 때문이다.</p>

<H2>7.1 트랜잭션 네임 스페이스 등록</H2>
<p>스프링에서는  트랜잭션 관련 설정을 AOP로 처리한다. 추가로 트랜잭션을 제어하는 어드바이스를 설정하기 위해 스프링 설정 파일에 트랜잭션 관련 네임스페이스도 추가한다.</p>

<H2>7.2 트랜젝션 관리자 등록</H2>
<p>트랜잭션 관련 설정에서 가장 먼저 등록하는 것은 트랜잭션 관리자 클래스이다. 스프링은 다양한 트랜잭션 관리자를 지원하는데, 어떤 기술을 이용하여 데이터베이스 연동을 처리했느냐에 따라 트랜잭션 관리자가 달라진다. 그리고 모든 트랜잭션 관리자는 
PlatformTransactionManager 인터페이스를 구현한 클래스들이다.</p>
<p>따라서 스프링이 제공하는 모든 트랜잭션 관리자는 트랜잭션 관리에 필요한 commit(), rollback() 메소드를 가지고 있다.</p>

<br />
<p>현재 구현한 두 개의 DAO 클래스가 모두 JDBC를 기반으로 동작하므로 DataSourceTransactionManager 클래스를 이용하여 트랜잭션을 처리한다.
이후에 Mybatis와 JPA를 이용하여 DAO 클래스를 추가로 구현하면 그때 트랜잭션 관리자를 바꿔준다. JPA의 경우 JPATransactionManager를 등록한다.</p>
<br />
<p>스프링 설정 파일에 DataSourceTransactionManager 클래스를 bean 등록한다.</p>
<p>여기서 중요한 것은 DataSourceTransactionManager를 bean등록한다고 해서 자동적으로 트랜잭션이 관리되는 것은 아니다. DataSourceTransactionManager도 다른 트랜잭션 관리자와 마찬가지로 PlatformTransactionManager의 commit(), rollback() 메소드를 재정의하여 구현하고 있을 뿐이지, PlatformTransactionManager 객체 스스로 자신이 가진 메소드를 실행할 수는 없다.</p>
<p>트랜잭션 관리자가 가지고 있는 메소드를 호출하면서 실질적인 트랜잭션 관리기능을 제공하는 것은 무엇인지에 대해 어드바이스의 개념을 다시 떠올려보자. 어드바이스는 비즈니스 메소드 실행 전,후에 동작하여 비즈니스 메소드와 무관하게 공통기능을 제공한다. 이 공통기능 중 가장 대표적인 것이 트랜잭션 처리이다.</p>
<p>비즈니스 메소드가 수행되다 예외가 발생하면 해당 메소드에 대한 트랜잭션을 롤백하고 문제없이 정상 종료되면 트랜잭션을 커밋한다. 트랜잭션을 커밋, 롤백하기 위한 객체는 DataSourceTransactionManager로 등록했다. 이제 이 트랜잭션 관리자를 이용하여 트랜잭션을 제어하는 어드바이스만 등록하면 된다.</p>

<H2>7.3 트랜잭션 어드바이스 설정</H2>
<p>트랜잭션 관리 기능의 어드바이스는 tx:advice 엘리먼트를 사용하여 설정한다. 앞에서 트랜잭션 설정을 위한 tx네임스페이스를 추가했으므로 스프링 설정 파일에 tx:advice 엘리먼트를 이용하여 트랜잭션 관리 어드바이스를 설정한다.</p>
<p>지금까지는 AOP관련 설정에 사용한 모든 어드바이스 클래스를 우리가 직접 구현했지만 트랜잭션 관리 기능의 어드바이스는 직접 구현하지 않으며, 스프링 컨테이너가 tx:advice 설정을 참조하여 자동으로 생성한다.</p>
<p>이는 트랜잭션 관리 어드바이스에서 객체에 클래스 이르밍나 메소드를 확인할 수 없다는 의미이기도 하다. 우리는 생성되는 어드바이스의 아이디를 id속성으로, 어드바이스객체가 사용할 트랜잭션 관리자를 transaction-manager속성으로 지정할 뿐이다.</p>
<p>앞에서 한 설정은 txAdivce라는 어드바이스가 앞에서 설정한 txManager를 이용하여 트랜잭션을 관리한다는 설정이다. tx:attrubutes의 자식 엘리먼트로 tx:method 엘리먼트를 이용하여 트랜잭션을 적용할 메소드를 지정할 수 있다. 앞에서의 설정은 get으로 시작하는 모든 메소드는 read-only="true" 즉 읽기전용으로 처리되어 트랜잭션 관리대상에서 제외하고 나머지 메소드들은 트랜잭션 관리에 포함한 것이다.</p>

<br />
<p>tx:method 엘리먼트의 속성 설명</p>

속성|의미
----|----
name|트랜잭션이 적용될 메소드 이름 지정
read-only|읽기 전용 여부 지정(기본값 false)
no-rollback-for|트랜잭션을 롤백하지 않을 예외 지정
rollback-for|트랜잭션을 롤백할 예외 지정


<H2>7.4 AOP설정을 통한 트랜잭션 적용</H2>
<p>트랜잭션 관리 어드바이스까지 설정했으면 비즈니스 메소드 실행 후에 트랜잭션 관리 어드바이스가 동작하도록 AOP설정만 추가하면된다.
이때 aop:aspect가 아닌 aop:adivsor엘리먼트를 사용한다는 점이 기존 AOP설정과 다르다. 우리는 앞에서 포인트컷과 어드바이스를 결합할 때 aop:aspect 엘리먼트를 사용했다.</p>
<p>aop:aspect 엘리먼트 사용시에는 어드바이스 객체의 아이디와 메소드 이름을 알아야 한다. 하디만 트랜잭션 관리 어드바이스는 우리가 직접 클래스를 구현하지 않고 tx:advice 설정 때문에 스프링 컨테이너가 자동으로 생헝하므로 어드바이스 메소드 이름을 알 수 없다. </p>
<p>aop:advisor와 aop:aspect 의 기능은 같으므로 포인트컷과 어드바이스의 결합의 기능을 가지며 txPointcut으로 지정한 메소드가 호출될 때, txAdvice로 등록한 어드바이스가 동작하여 트랜잭션을 관리하도록 설정하면 된다.</p>

<br />
<p>지금까지의 트래잭션 설정이 어떤 순서로 동작하는지 그림을 통해 확인해본다.</p>

![10](https://user-images.githubusercontent.com/65153512/117938568-24b53180-b342-11eb-9889-3cea43127582.jpg)

<p>1. 클라이언트가 BoardServiceImpl 객체의 insertBoard() 메소드를 호출하면, 2. insertBoard 메소드의 비즈니스 로직이 수행된다. 만약 insertBoard메소드 수행중에 문제가 발생하면 3. txAdvice로 등록한 어드바이스가 동작하여, 참조하는 4. txManager의  rollback메소드를 호출한다. 만약 문제없이 정상으로 수행되었다면 commit 메소드를 호출한다</p>

<H2>7.5 트랜잭션 설정 테스트</H2>
<p>지금까지 작성한 트랜잭션 관련 설정이 실제 트랜젝션 관리를 지원하는지 간단히 테스트해보도록 한다. BoardServiceClient에서 명시적으로 100번 글을 등록하도록 한다.</p>
<p>BoardServiceImpl클래스의 insertBoard 메소드에서 BoardDAOSpring의 insertBoard메소드를 연속으로 두 번 호출한다. 첫번째 입력은 성공하지만, Board 테이블에 SEQ컬럼이 기본키로 지정되어 있어 두번째 입력에서 예외가 발생한다. 하지만 트랜잭션은 메소드 단위로 관리되므로 발생한 예외로 인해 BoardServiceImpl클래스의 insertBoard메소드의 작업 결과는 모두 롤백 처리 된다.</p>
<p>BoardServiceImpl에서 BoardDAOSpring 객체의 insertBoard 메소드를 호출하므로 BoardDAOSpring의 insertBoard 메소드와 SQL구문을 다음처럼 수정한다.(단 다음 실습부터는 원래의 것을 사용한다.)</p>
<p>seq칼럼을 자동 증가하도록 작성한 sql을 수정하여 사용자가 입력한 100번글이 insert되도록 수정했다. 이제 BoardServiceClient 프로그램을 실행하면 에러메세지를 확인할 수 있다. 이후 insertboard메소드 부분만 주석처리하여 목록을 확인해보면 추가되지 않았음을 볼 수 있다.</p>

<H3></H3>
<p></p>
<p></p>