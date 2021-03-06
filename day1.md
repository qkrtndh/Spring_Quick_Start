# Spring_Quick_Start
<p>SPRING QUICK START (저.채규태, 출.PUBY PAPER)</p>

<img src="http://image.yes24.com/momo/TopCate853/MidCate001/85204414.jpg">

<p>위의 책을 보며 공부한 것을 정리(1/5)</p>
<P>※패키지명이나, 클래스 위치등에 오류가 있음※</p>
<p>경로수정 하였으나 test소스 수행시 오류 발생</p>

<H1>1. 프레임워크 개요</H1>
<H2>1.1 프레임 워크의 개념</H2>
<p>프레임 워크의 사전적 의미는 뼈대 혹은 틀이다.</p>
<p>이 의미를 소프트웨어의 관점에서 접근하면 아키텍처에 해당하는 골격 코드이다.</p>
<p>시스템 아키텍처(system Architecture)는 시스템의 구조, 행위, 더 많은 뷰를 정의하는 개념적 모형이다.</p>
<br />
<p>애플리케이션 개발시 전체 구조를 결정하는 것이 아키텍처이다. 이 아키텍처에 해당하는 골격코드를 프레임워크가 제공한다.</p>

<br />
<p>시스템을 개발하는 과정에서 애부분 개발자들은 산출물에 입각해서 개발하므로 아키텍처의 일관성이 잘 유지된다. 하지만 유지보수 과정에서 인력과 시간 부족으로 인해 산출물은 무시되기 쉽고, 개발자들의 경험에 의존하여 유지보수가 진해오디는 경우가 많다.</p>
<p>프레임워크는 이런문제를 근본적으로 해결해준다. 개발에서 기본이 되는 뼈대나 틀을 제공하여 개발자는 그 뼈대에 살을 붙이는 작업을 하게 된다.</p>

<H2>1.2 프레임 워크의 장점</H2>
<H3>1.2.1 빠른 구현 시간</H3>
<p>프레임워크를 사용하면 아키텍처에 해당하는 골격 코드를 프레임워크에서 제공한다. 따라서 개발자는 비즈니스 로직만 구현하면 되므로 제한된 시간에 많은 기능을 구현할 수 있다.</p>

<H3>1.2.2 쉬운 관리</H3>
<p>같은 프레임워크가 적용된 애플리케이션들은 아키텍처가 같으므로 관리하기가 쉽다. 결과적으로 유지보수에 들어가는 인력과 시간이 감소한다.</p>

<H3>1.2.3 개발자들의 역량 획일화</H3>
<p>슥련된 개발자와 초급개발자의 소스의 품질은 차이가 날 수 밖에 없지만 프레임워크를 사용하면 두 개발자가 생성한 코드가 어느정도 비슷해진다.</p>

<H3>1.2.4 검증된 아키텍처의 재사용과 일관성 유지</H3>
<p>프레임워크를 이용하여 애플리케이션을 갭라하면 프레임워크에서 제공하는 아키텍처를 이용하므로 아키텍처에 대한 별다른 고민이나 검증없이 소프트웨어를 개발할 수 있다. 또한, 시간이 지나도 유지보수 과정에서 아키텍처가 왜곡되거나 변형되지 않는다.</p>

<H2>1.3 스프링 프레임 워크의 특징</H2>
<p>IoC와 AOP를 지원하는 경량의 컨테이너 프레임워크로 표현할 수 있다.</p>

<H3>1.3.1 경량</H3>
<p>스프링은 여러개의 모듈로 구성되며, 각 모듈은 하나 이상의 JAR파일로 구성된다. 이 몇 개의 JAR파일로 개발과 실행이 모두 가능하다.</p>
<p>스프링을 이용해 만든 애플리케이션의 배포는 매우 빠르고 쉽다.</p>
<p>스프링 프레임워크는 POJP(Plain Old Java Object) 형태의 객체를 관리한다. POJO는 클래스를 구현하는데 특별한 규칙이 없는 단순하고 가벼운 객체이므로 기존의 EJB객체를 관리하는 것 ㅗ다 훨씬 가볍고 빠르다.</p>

<H4>모듈</H4>
<p>모듈은 외부에서 재사용 할 수 있는 패키지들을 묶은것으로, 이클립스의 각 프로젝트들의 목표는 하나의 모듈을 개발하는 것으로 볼 수 있다.</p>
<p>모듈화를 하는 이유는 사용하지 말아야 할 패키지를 숨길 수 있게 하기 위함이 있고(보안), 패키지를 기능별로 모듈화 함으로서 필요한 모듈만 가져와 프로그램을 실행할 수 있다. (최적의 런타임 이미지)</p>

<H4>POJO</H4>
<p>옛날 자바 객체를 의미하며 아무런 규칙이 없다는 것의 반대로 Servlet 클래스가 있다.</p>
<p>Servlet 클래스는 마음대로 만들 수 없고 반드시 요구하는 규칙에 맞게 실행해야 한다. 작성규칙으로는</p>
<ol>  
 <li>javax.servlet, javax.servlet.http 패키지를 import 해야한다.</li>  
 <li>public 클래스로 선언되어야 한다.</li>  
 <li>Servlet, GenericServlet, HttpServlet 중 하나를 상속해야 한다.</li>  
 <li>기본 생성자가 있어야 한다</li>
 <li>생명주기에 해당하는 메소드를 재정의(Overriding)한다.</li>  
</ol>

<H4>생명주기</H4>
<img src="https://kairo96.gitbooks.io/android/content/pic2/2-4-1-1.jpg" width="500">
<p>액티비티 생명주기는 onCreate() -> onStart() -> onResume() -> onPause() -> onStop() -> onDestory()순으로 실행되며, 경우에 따라서 onRestart() 메소드가 호출되기도 한다.</p>

<H3>1.3.2 제어의 역행 Inversion of Control</H3>
<p>우리가 비즈니스 컴포넌트를 개발할 때 항상 신경 쓰는 것이 바로 낮은 결합도와 늪은 응집도 이다. 스프링은 제어의 역행을 통해 애플리케이션을 구성하는 객체 간의 느슨한 결합, 즉 낮은 결합도를 유지한다.</p>
<p>IoC가 적용되지 않은 상황에서는 애플리케이션 수행에 필요한 객체의 생성이나 객체간 의존관계를 개발자가 직접 코드로 처리해야 했다. 이런 상황에서는 의존관계에 있는 객체를 변경할 때 반드시 코드의 수정이 필요해진다.</p>

<p>IoC가 적용되면 객체 생성을 자바 코드로 직접 하지 않고 컨테이너가 대신 처리하며, 객체간 의존관계 또한 컨테이너가 처리하므로 결과적으로 소스에 의존관계가 명시되지 않아 결합도가 떨어져 유지보수가 편리해진다.</p>

<H4>컴포넌트</H4>
<p>컴포넌트는 독립적인 소프트웨어 모듈이다.</p>
<a href="https://thefif19wlsvy.tistory.com/24" target='_blank'>자세한 내용</a>

<H4>결합도와 응집도</H4>
<p>응집도는 모듈에 포함된 요소들이 하나의 책임/목적을 위해 연결되어 연관된 정도이다. 높을 수록 좋다.</p>
<p>응집도가 높으면 변경대상과 범위가 명확해지기 때문에 코드수정이 용이하다</p>
<br />
<p>결합도는 다른 모듈과의 의존성 정도이다. 모듈수정을 위해 다른 모듈의 변경을 요구하는 정도라고 생각할 수 있으며, 낮을수록 좋다.</p>
<p>기능하나를 수정하기위해 모듈 하나를 수정할 때 해당 모듈과 연관되어 있는 다른 모듈들과 기능을 수정하려 한다면 유지보수가 힘들어진다.</p>

<H3>1.3.3 관점지향 프로그래밍 AOP(Aspect Oriented Programming)</H3>
<p>비즈니스 메소드를 개발할 때 핵심 비즈니스 로직과 각 비즈니스 메소드마다 반복해서 등장하는 공통 로직을 분리함으로써 응집도가 높게 개발할 수 있도록 지원한다.</p>
<p>공통으로 사용되는 기능들을 외부의 독립된 클래스로 분리하고 해당 기능을 프로그램 코드에 직접 명시하지 않고 선언적으로 처리하여 적용하는 것이 관점지향 프로그래밍의 기본 개념이다. 이렇게 되면 공통 기능을 분리하여 관리할 수 있으므로 응집도가 높은 비즈니스 컴포넌트를 만들 수 있을 뿐만 아니라 유지보수를 혁신적으로 향상시킬 수 있다.</p>

<H3>1.3.4 컨테이너</H3>
<p>컨테이너는 특정 객체의 생성과 관리를 담당하며 객체 운용에 필요한 다양한 기능을 제공한다. 컨테이너는 일반적으로서버 안에 포함되어 배포 및 구동된다. 대표적인 컨테이너로 Servlet 객체를 생성하고 관리하는 Servlet 컨테이너등이 있다 Servlet 컨테이너는 우리가 사용하는 톰캣 서버에도 포함됭 있다. 애플리케이션 운용에 필요한 객체를 생성하고 객체간의 의존관계를 관리한다는 점에서 스프링도 일종의 컨테이너라고 할 수 있다.</p>
<p></p>


<H2>1.4 IoC 컨테이너</H2>
<p>컨테이너는자신이 관리할 클래스들이 등록된 xml 설정파일을 로딩하여 구동한다. 그리고 클라이언트의 요청이 들어오는 순간 xml설정 파일을 참조하여 객체를 생성하고 객체의 생명주기를 관리한다.</p>
<p>제어의 역행 IoC는 결합도와 관련된 개념으로 이해할 수 있다.기존에는 개발자가 직접 의존관계를 처리해야 했다. 하지만 제어의 역행은 일련의 작업들을 코드로 처리하지 않고 컨테이너로 처리하는 것을 의미하며, 소스에서 객체 생성과 의존관계에 대하 코드가 사라져 낮은 결합도의 컴포넌트를 구현할 수 있다.</p>

<H3>1.4.1 결합도가 높은 프로그램</H3>
<p>결합도란 하나의 클래스가 다른 클래스와 얼마나 많이 연결되어 있는지를 나타내는 표현이며, 결합도가 높은 프로그램은 유지보수가 어렵다.</p>
<p>이 결합도와 유지보수 관계를 이해하기 위해 간단한 실습을 진행한다. BoardWeb/src/main/java 소스폴더에 SamsungTV 클래스로 진행한다.</p>
<p>samsungtv와 lgtv는 메소드 시그니처가 다르므로 tvuser코드 대부분을 수정해야 tv를 교체할 수 있다. 두 tv클래스가 같은 메소드를 가지도록 강제할 어떤 수단도 없다. 만약 tvuser와 같은 클라이언트 프로그램이 하나가 아니라면 유지보수는 더욱 힘들 것이며 tv교체를 결정하기 쉽지 않다.</p>

<H3>1.4.2 다형성 이용하기</H3>
<p>결합도를 낮출 수 있는 방법은 다양하지만 가장 쉽게 생각할 수 있는 것이 다형성이다. 앞의 코드를 다형성을 이용하여 수정한다.</p>
<p>다형성을 이용하려면 상속과 메소드 재정의, 형변환이 필요하며 자바같은 객체지향 언어는 문법으로 지원한다.</p>
<p>TV 클래스들의 최상위 부모로 TV 인터페이스를 추가하고 모든 TV가 공통적으로 가져야할 메소드를 추상 메소드로 선언한다.</p>

<br />
<p>인터페이스를 이용하여 모든 TV클래스가 같은 메소드들을 가질 수 밖에 없도록 강제 할 수 있게 되었다.</p>
<p>TVUser 클래스는 TV인터페이스 타입의 변수로 SamsungTV 객체를 참조하고 있다. 이렇게 묵시적 형변환을 이용하여 객체를 참조하면 SamsungTV를  LgTV로 변경할때 참조하는 객체만 변경하면 되므로 객체를 쉽게 변경할 수 있다.</p>
<p>이렇게 다형성을 이용하면 TVUser와 같은 클라이언트 프로그램이 여러개 있더라도 최소한의 수정으로 TV를 교체할 수 있다. 유지보수가 편해진다고 할 수 있다.</p>


<H3>1.4.3 디자인 패턴 이용하기</H3>
<p>결합도를 낮추기 위한 방법중 하나로 디자인패턴을 이용하는 방법이 있다. 인터페이스를 사용하더라도 TV를 변경하고자 할때 TV클래스 객체를 생성하는 소스(형변환 부분)를 수정해야만 한다.</p>
<p>TV를 교체할 때 클라이언트 소스를 수정하지 않고 TV를 교체할 수 있다면 유지보수는 더욱 편리해 질 것이다. 이를위해 Factory 패턴을 적용해야한다.</p>
<p>Factory 패턴은 클라이언트에서 사용할 객체 생성을 캡슐화 하여 TVUser와 TV사이를 느슨한 결합 상태로 만들어준다.</p>
<p>실행되는 TV를 바꾸고싶을 때는 명령행 매개변수만 수정하여 실행한다. 클라이언트 소스를 수정하지 않고도 객체를 변경할 수 있다.</p>
<p>이런 결과를 얻을 수 있는 것은 TV객체를 생성하여 리턴하는 BeanFactory 때문이다. 클라이언트에 해당하는 TVUser는 자신이 필요한 객체를 직접 생성하지 않는다. 그랬다면 TV가 변경될 때마다 소스를 수정해야 한다. TVUser는 단지 객체가 필요하다고 BeanFactory에 알렸고, BeanFactory가 클라이언트가 사용할 TV객체를 적절하게 생성하여 넘겨준 것이다.</p>



<H1>2 스프링 컨테이너 및 설정 파일</H1>
<p>대부분 IoC컨테이너는 각 컨테이너에서 관리할 객체들을 위한 별도의 설정 파일이 있다. Servlet컨테이너는 web.xml파일등. 해당 컨테이너가 생성하고 관리할 클래스들을 등록한다.</p>
<p>스프링 프레임워크도 다른 컨테이너와 마찬가지로 자신이 관리할 클래스들이 등록된 xml파일이 필요하다. 스프링 컨테이너가 사용할 xml파일은 앞에서 설치한 STS를 이용하면 간단하게 만들 수 있다.</p>

<H2>2.1 스프링 IoC 시작하기</H2>
<H3>2.1.1 스프링 설정 파일 생성</H3>
<p>BoardWeb 프로적트에 src/main/resources소스 폴더를 선택하여 new->others 에서 spring bean configuration file을 선택한다.</p>
<p>file name에  applicationContext를 입력하면 스프링 설정파일이 생성된다. 기본적으로 beans루트 엘리먼트와 네임스페이스 관련 설정들이 추가되어 제공된다.</p>
<p>이때 bean 엘리먼트를 사용하는데 클래스 하나당 하나의 bean 설정이 필요하다. bean 엘리먼트에서 가장 중요한것은 class 속성값이다 패키지경로가 포함된 클래스 경로를 지정해야한다.</p>

<H3>2.1.2 스프링 컨테이너 구동 및 테스트</H3>
<p>코드 참조</p>
<ol>  
 <li>TVUser 클라이언트가 스프링 설정 파일을 로딩하여 컨테이너 구동</li>  
 <li>스프링 설정 파일에 bean 등록된 SamsungTV 객체 생성</li>  
 <li>getBean 메소드로 이름이 tv인 객체를 요청 (look up)</li>  
 <li>SamsungTV 객체 반환</li>
</ol>
<p>중요한 점은 실행되는 TV를 LgTV로 변경할 때 applicationContext.xml 파일만 수정하면 된다는 점이다. 즉 TVUser 클라이언트 소스를 수정하지 ㅇ낳고도 동작하는 TV를 변경할 수 있으며, 기존에 BeanFactory 클래스를 사용했던 것보다 유지보수가 좀 더 편해졌다.</p>


<H3>2.1.3 스프링 컨테이너의 종류</H3>
<p>스프링에서는 BeanFactory와 이를 상속한 applicationContext 두 가지 유형의 컨테이너를 제공한다.</p>
<p>BeanFactory는 스프링 설정 파일에 등록된 bean객체를 생성하고 관리하는 가장기본적인 컨테이너 기능만 제공한다. 컨테이너가 구동될 때 bean객체를 생성하는 것이 아니라 클라이언트의 요청(look up)에 의해서만 bean객체가 생성되는 지연 로딩 방식을 사용한다. 따라서 일반적인 스프링 프로젝트에서 BeanFactory를 사용할 일은 없다.</p>
<br />
<p>ApplicationContext는 BeanFactory가 제공하는 bean 객체 관리 기능 외에도 트랜잭션 관리나 메세지 기반의 다국어 처리등 다양한 기능을 지원한다. 컨테이너가 구동되는 시점에 bean 등록된 클래스들을 객체 생성하는 즉시 로딩방식으로 동작한다. 웹 애플리케이션 개발도 지원하므로 대부분 스ㅡ링 프로젝트는 applicationContext 유형의 컨테이너를 이용한다.</p>
<p>ApplicationContext의 구현 클래스는 매우 다양하지만 그중 가장 많이 사용되는 두가지만 알아보도록 한다. </p>

<br>

<p>GenericXmlApplicationContext - 파일 시스템이나 클래스 경로에 있는 xml 설정 파일을 로딩하여 구동하는 컨테이너이다.</p>
<p>XmlWebApplicationContext - 웹 기반의 스프링 애플리케이션을 개발할 때 사용하는 컨테이너 이다.</p>

<H2>2.2 스프링 XML 설정</H2>
<H3>2.2.1 beans 루트 엘리먼트</H3>
<p>스프링 컨테이너는 bean 저장소에 해당하는 xml 설정 파일을 참조하여 bean의 생명주기를 관리하고, 여러 가지 서비스를 제공한다. 스프링 프로젝트 전체에서 가장 중요한 역할을 담당하며, 이 설정 파일을 정확하게 작성하고 관리하는 것이 무엇보다 중요하다.</p>
<p>스프링 설정 파일 이름은 무엇으로 해도 상관 없지만 반드시 beans를 루트 엘리먼트로 사용해야 하며, beans 엘리먼트 시작태그에 네임스페이스를 비롯한 xml 스키마 관련 정보가 설정된다.</p>
<p>STS를 이용하여 만든 스프링 설정 파일에는 beans 네임 스페이스가 기본 네임스페이스로 선언되어 있으며, spring-beans.xsd 스키마 문서가 schemaLoacation으로 등록되어 있다 따라서 bean, description,alias,import 등 네 개의 엘리먼트를 자식 엘리먼트로 사용할 수 있다. 이 중 bean과 import 정도가 실제 프로젝트에 사용된다.</p>


<H3>2.2.2 import 엘리먼트</H3>
<p>스프링 설정 파일 하나에 우리가 만든 모든 클래스를 bean으로 등록하고 관리할 수도 있다. 하지만 스프링 기반의 애플리케이션은 단순한 bean등록 외에도 트랜잭션 관리, 예외처리, 다국어처리 등 복잡하고 다양한 설정이 필요하다</p>
<p>이런 모든 설정을 하나의 파일로 처리할 수도 있지만 그렇게하면 스프링 설정 파일이 너무 길어지고 관리도 어렵다. 결국 기능별로 여러 xml파일로 나누어 설정하는 것이 더 효율적인데, 이렇게 분리하여 작성한 설정 파일들을 하나로 통합할 때 import엘리먼트를 사용한다.</p>
<p>import 태그를 이용해서 여러 설정파일을 한 파일에 작성하는것과 같은 효과를 낸다</p>

<H3>2.2.3 bean 엘리먼트</H3>
<p>스프링 설정 파일에 클래스를 등록하려면 bean엘리먼트를 사용한다. 이때 id와 class속성을 사용하는데 id는 생략 가능하지만 class는필수이다.class 속성에 클래스를 등록할 때는 정확한 패키지 경로와 클래스 이름을 지정해야한다. 따라서 될 수 있으면 STS의 자동완성 기능을 이용하여 클래스를 등록하는것이 권장된다.</p>
<p>하지만 클라이언트가 객체를 요청하려면 이름이 반드시 지정되어야 하며 bean객체의 이름을 지정할때 사용되는 속성이 id이다. id속성은 컨테이너로부터 bean객체를 요청할 때 사용하므로 반드시 스프링 컨테이너가생성한개체들 사이에서 유일해야 한다.</p>

<br />

<p>id와같은 기능을 하는 속성으로 name이 있다. name은 id와 다르게 자바 식별자 작성 규칙을 따르지 않는 문자열도 허용한다. 따라서 특수기호가 포함된 아이디를 bean 아이디로 지정할 때는 id대신 name속성을 쓴다.</p>
<p>name 또한 전체 스프링 파일 내에서 유일해야 한다. id나 name중 어떤 것을 사용해도 상관 없으나 규칙이 존재하는 id가 선호된다.</p>

<H3>2.2.4 bean엘리먼트 속성</H3>
<H4>2.2.4.1 init-method 속성</H4>
<p>Servlet 컨테이너는 web.xml 파일에 등록된 Servlet 클래스의 객체를 생성할 때 디폴트 생성자만 인식한다. 따라서 생성자로 Servlet 객체의 멤버 변수를 초기화 할 수 없다. 그래서 서블릿은 init()메소드를 재정의 하여 멤버변수를 초기화한다.</p>
<p>스프링 컨테이너 역시 스프링 설정 파일에 등록된 클래스를 객체 생성할 때 디폴트 생성자를 호출한다. 따라서 객체를 생성한 후에 멤버변수 초기화 작업이 필요하다면, Servlet의 init()같은 메소드가 필요하다. 이를 위해 스프링에서는 bean엘리먼트에 init-method 속성을 지원한다</p>
<p>스프링 컨테이너는 bean 등록된 클래스 객체를 생성한 후에 init-method 속성으로 지정된 initMethod() 메소드를 호출한다. 이 메소드에서 멤버변수에 대한 초기화 작업을 처리한다.</p>

<H4>2.2.4.2 destroy-method 속성</H4>
<p>bean 엘리먼트에서 destroy-method 속성을 이용하여 스프링 컨테이너가 객체를 삭제하기 직전에 호출될 임의의 메소드를 지정할 수 있다.</p>
<p>init-method는 객체 생성 직후, destory-method는 컨테이너가 종료되면서 자신이 관리하는 객체들을 삭제하는데 그때 수행된다.</p>

<H4>2.2.4.3 lazy-init 속성</H4>
<p>ApplicationContext 를 이용하여 컨테이너를 구동하면 컨테이너가 구동되는 시점에 스프링 설정 파일에 등록된 bean 들을 생성하는 즉시 로딩 방식으로 동작한다. 그런데 어떤 bean은 자주 사용되지 않으면서 메모리를 많이 차지하여 시스템에 부담을 주는 경우도 있다.</p>
<p>따라서 스프링에서는 컨테이너가 구동되는 시점이 아닌 해당 bean이 사용되는 시점에 객체를 생성하도록init-lazy 속성을 제공한다. 특정 bean을 등록할 때 lazy-init="true"로 설정하면 스프링 컨테이너는 해당 bean을 미리 생성하지 않고 클라이언트가 요청하는 시점에 생성한다. 결국 메모리 관리를 효율적으로 할 수 있게 된다.</p>

<H4>2.2.4.4  scope 속성</H4>
<p>프로그램을 개발하다 보면 개발자도 모르는 사이에 수많은 객체가 생성된다. 글너데 이 중에는 하나만 생성돼도 상관없는 객체들이 있다. 예를들어 우리가 사용중인 samsungTV 클래스에는 price같은 변수가 있어서 생성되는 객체들이 다른 가격을 가지는 것도 아니므로 하나의 객체만 생성돼도 된다.</p>
<p>하나의 객체만 생성하여 유지하려면 어떻게 해야 할까? 가장 쉬운 방법은 주소를 복사하여 재사용 하는 것이다.</p>
<br />
<p>TV tv1 = new SamsungTV();</p>
<p>TV tv2 = tv1;</p>
<p>TV tv3 = tv2;</p>
<br />
<p>결과적으로 tv1,tv2,tv3는 같은 주소를 가지므로 하나의 객체를 공유하게 된다. 하지만 이렇게 개발하는 것은 불가능하며, 자연스럽게 하나의 객체를 생성하도록 제어해야 한다. 이때 사용되는 것이 GoF 디자인 패턴중 하나인 싱클톤 패턴(하나의 인스턴스만사용)이다. 그러나 싱글톤 패턴을 구현하려면 일일이 클래스에 패턴 관련 코드를 작성해야 하므로 매우 귀찮은 일이다.</p>
<p>스프링에서는 컨테이너가 객체를 생성하는 즉시 자동으로 싱글톤 객체로 생성하는 기능을 제공한다. 스프링 컨테이너는 컨테이너가 생성한 bean을 어느 범위에서 사용할 수 있는지를 지정할 수 있는데, 이 때 scope 속성을 사용한다</p>
<p>scope 속성은 기본이 싱글톤이다. 이는 해당 bean이 스프링 컨테이너에 의해 단 하나만 생성되어 운용되도록 한다.</p>

<br />
<p>애플리케이션마다 다르겠지만 스프링 컨테이너가 관리하는 bean들은 대부분 싱글톤으로 운영되어야 하며, scope속성을 싱글톤으로 설정하거나 생략하는 경우가 많다</p>
<p>scope 속성을 prototype으로 지정하면 해당 bean이 요청될 때 마다 객체를 새로 만들어 반환한다.</p>

<H1>3. 의존성 주입</H1>
<H2>3.1 의존성 관리</H2>
<H3>3.1.1 스프링의 의존성 관리 방법</H3>
<p>스프링 프레임 워크의 가장 중요한 특징은 객체의 생성과 의존관계를 컨테이너가 자동으로 관리한다는 점이다. 이것이 스프링IoC의 핵심 원리이기도 하다.</p>
<p>스프링은 IoC를 다음 두 가지 형태로 지원한다.</p>
<br />
<p>Dependency Lookup</p>
<p>Dependency Injection</p>
<br />

<p>컨테이너가 애플리케이션 운용에 필요한 객체를 생성하고 클라이언트는 컨테이너가 생성한 객체를 검색하여 사용하는 방식을 Dependency Lookup 이라고한다./p>
<p>Dependency Lookup은 우리가 지금까지 컨테이너를 사용했던 방법이다. 하지만 Dependency Lookup은 실제 애플리케이션 개발 과정에서는 사용하지 않으며, 대부분 Dependency Injection을 사용하여 개발한다.</p>
<p>Dependency Injection은 객체 사이의 의존관계를 스프링 설정 파일에 등록된 정보를 바탕으로 컨테이너가 자동으로 처리해준다. 따라서 의존성 설정을 바꾸고 싶을 때 프로그램 코드를 수정하지 않고 스프링 설정 파일 수정만으로 변경사항을 적용할 수 있어 유지보수가 향상된다.</p>
<p>Dependency Injection은 컨테이너가 직접 객체들 사이에 의존관계를 처리하는 것을 의미하며, 이를 다시 setter 메소드를 기반으로 하는 setter injection과 생성자를 기반으로 하는 constructor injection으로 나뉜다.</p>


<H3>3.1.2 의존성 관계</H3>
<p>의존성 관계란 객체와 객체의 결합 관계이다. 즉, 하나의 객체에서 다른 객체의 변수나 메소드를 이용해야 한다면 이용하려는 객체에 대한 객체 생성과 생성된 객체의 레퍼런스 정보가 필요하다.</p>
<br />
<p>실습코드에서 쓸데없이 sonyspeaker 객체가 두개 생성되고 스피커 교체시 볼륨메소드 두개를 모두 수정해야 한다. 의존관계에 있는 speaker객체애 대한 객체 생성 코드를 samsungtv소스에  명시했기 때문이다. 스프링은 이 문제를 의존성 주입으로 해결한다.</p>


<H2>3.2 생성자 인젝션 이용하기</H2>
<p>스프링 컨테이너는 xml 설정 파일에 등록된 클래스를 찾아서 객체 생성할때 기본적으로 매개변수가 없는 기본 생성자를 호출한다. 하지만 컨테이너가 기본 생성자 말고 매개변수를 가진 다른 생성자를 호출하도록 설정할 수 있는데, 이 기능을 이용하여 생성자 인젝선을 처리한다. 생성자 인젝션을 사용하면 생성자의 매개변수로 의존관계에 있는 객체의 주소 정보를 전달할 수 있다.</p>
<p>이때 xml 설정은 </p>
<br />

~~~
<bean id="tv" class="com.springbook.biz.SamsungTV" init-method="initMethod" destroy-method="destroyMethod" lazy-init="true" scope="singleton">

		<constructor-arg ref="sony"></constructor-arg>
	</bean>
<bean id="sony" class="com.springbook.biz.SonySpeaker"></bean>
~~~

<p>생성자 인젝션을 위해서 samsungtv bean 등록설정에서 시작 태그와 종료태그 사이에 constructor-arg 엘리먼트를 추가하면 된다. 그리고 생성자 인자로 전달할 객체의 아이디를 constructor-arg 엘리먼트에 ref 속성으로 참조한다</p>


<H3>3.2.1 다중 변수 매핑</H3>
<p>생성자 인젝션에서 초기화해야 할 멤버 변수가 여러 개이면, 여러 개의 값을 한꺼번에 전달해야 한다. 이 때는 다음처럼 생성자를 적절하게 추가하면 된다.(코드참조)</p>
<p>스프링 설정 파일에 constructor-arg 엘리먼트를 매개변수의 개수만큼 추가해야 한다. constructor-arg 엘리먼트에는 ref와 value 속성을 사용하여 생성자 매개변수로 전달할 값을 지정할 수 있다. 이때 인자로 전달될 데이터가 bean으로 등록된 다른 객체인 경우 ref속성으로 해당 객체의 id나 name을 참조한다.</p>
<p>고정된 문자열이나 정수같은 기본형 데이터인 경우 value속성을 이용한다.</p>
<p>하지만 생성자가 여러개 오버로딩 되어있다면 어떤 생성자를 호출해야 할지 분명하지 않을 수 있다. 이를 위해 index속성을 지원하며, index속성을 이용하면 어떤 값이 몇 번째 매개변수로 매핑되는지 지정할 수 있다.</p>


<H3>3.2.2 의존관계 변경</H3>
<p>지금까지는  SamsungTV 객체가 SonySpeaker를 이용하여 동작했지만 유지보수 과정에서 다른 스피커로 교체하는 상황이 발생할 수 있다.
의존성 주입은 이런 상황을 매우 효과적으로 처리해준다.</p>
<p>speaker인터페이스를 추가하고 sonyspeaker가 상속하게 한 후, 마찬가지로 상속하는 applespeaker를 추가한다. 스프링 설정파일만 적절히 관리하면 TV와 Speaker모두 변경할 수 있다. 이 과정에서 어떤 자바 코드도 변경되지 않음에 주목한다.</p>

<H2>3.3 Setter 인젝션 이용하기</H2>
<p>생성자 인젝션은 생성자를 이용하여 의존성을 처리한다. 하지만 setter 인젝션은 이름에서 알 수 있듯이 setter 메소드를 호출하여 의존성을 주입을 처리하는 방식이다.</p>
<p>두 가지 방법 모두 멤버변수를 원하는 값으로 설정하는 것을 목적으로 하고ㅡ 겨로가가 같으므로 어떤 방법을 쓰든 상관 없다. 다만 코딩 컨벤션에 따라 한 가지로 통일해서 사용하는데 대부분은 setter 인젝션을 사용하며, setter 메소드가 제공되지 않는 클래스에 대해서만 생성자 인젝션을 사용한다.</p>

<H3>3.3.1 setter 인젝션 기본</H3>
<p>setter메소드는 스프링 컨테이너가 자동으로 호출하며, 호출하는 시점은 bean 객체 생성 직후이다. 따라서 setter인젝션이 동작하려면 setter메소드 뿐만아니라 기본 생성자도 반드시 필요하다.</p>
<p>setter 인젝션을 이용하려면 스프링 설정 파일에 constructor-arg 엘리먼트 대신 property엘리먼트를 사용한다. name속성값이 호출하고자 하는 메소드 이름이다.</p>
<p>name 값이 speaker 라면 호출값은 setSpeaker로 앞에 set을 붙이고 첫 글자를 대문자로한 것이 호출된다.</p>
<p>생성자 인젝션과 마찬가지로 다른 bean객체를 인자로 넘길때 ref속성을 이용하고 기본형 데이터는 value를 이용한다.</p>

<H3>3.3.2  p 네임스페이스 사용하기</H3>
<p>Setter인젝션 설정시 p네임스페이스를 이용하면 더 효율적으로 의존성을 주입할 수 있다. p네임스페이스는 네임스페이스에 대한 별도의 schemaLocation이 없다. 따라서 네임스페이스만 적절히 선언하고 사용할 수 있다.</p>
<p>xmlns:p="http://www.springframework.org/schema/p"</p>
<p>p네임스페이스를 선언했으면 다음과 같이 참조형 변수에 참조할 객체를 할당할 수 있다.</p>
<br />
<p>p:변수명-ref="참조할 객체의 이름이나 아이디"</p>
<p>기본형이나 문자형 변수에 직접 값을 설정할 때</p>
<p>p:변수명="설정할 값"</p>

<H2>3.4 컬렉션 객체 설정</H2>
<p>프로그램을 개발하다 보면 배열이나 List 같은 컬렉션 객체를 이용하여 데이터 집합을 사용해야 하는 경우가 있다.</p>
<p>이때 컬렉션 객체를 의존성 주입하면 되는데 스프링에서는 이를위해 컬렉션 매핑과 관련된 엘리먼트를 지원한다.</p>

컬렉션 유형| 엘리먼트
---|---
java.util.List, 배열|<list>
java.util.Set|<set>
java.util.Map|<map>
java.util.Properties|<props>


<H3>3.4.1 List 타입 매핑</H3>
<p>배열 객체나 java.util.List 객체는 <list>태그를 사용해서 설정한다.</p>
<p>List 컬렉션을 멤버변수로 가지는 CollectionBean 클래스를 작성하고, 작성된 CollctionBean 클래스를 스프링 설정 파일에 bean 등록한다</p>

~~~
<bean id="collectionBean" class="com.springbook.biz.CollectionBean">
		<property name="addressList">
			<list>
				<value>서울시 강남구 역삼동</value>
				<value>서울시 성동구 행담동</value>
			</list>
		</property>
	</bean>
~~~

<p>위 설정은 두 개의 문자여 ㄹ주소가 저장된 List객체를 CollectionBean 객체의 setAddressList()호출시 인자로 전달하여  addressList멤버변수를 초기화 하는 설정이다.</p>
<p>클라이언트 프로그램을 작성하여 List컬렉션이 정상적으로 의존성 주입 되었는지 확인한다.</p>

<H3>3.4.2 Set 타입 매핑</H3>
<p>중복 값을 허용하지 않는 집합 객체를 사용할 때는 java.util.Set 이라는 컬렉션을 사용하며 <set> 태그를 사용한다.</p>
<p></p>

<H3>3.4.3 Map 타입 매핑</H3>
<p>특정 key로 데이터를 등록하고 사용할 때는 java.util.Map 컬렉션을 사용하며, <map>태그를 사용한다.</p>
<p>스프링 설정파일에서 key 와 value를 설정한다.</p>
<p>자바파일의 경우</p>

~~~
Map<string,string>
~~~

~~~
<bean id="collectionBean" class="com.springbook.biz.CollectionBean">
	<property name="addressList">
		<map>
			<entry>
				<key><value>고길동</value></key>
				<value>서울시 성동구 행담동</value>
			</entry>
			<entry>
				<key><value>마이콜</value></key>
				<value>서울시 강서구 화곡동</value>
			</entry>
		</map>
	</property>
</bean>
~~~

<H3>3.4.4 Properities 타입 매핑</H3>
<p>key=value 형태의 데이터를 등록하고 사용할 때는 java.util.Properities 컬렉션을 사용하며 <props> 태그를 사용한다.</p>
<p>자바파일의 경우 꺽쇠없는 형태이다. (참조)</p>

~~~
<bean id="collectionBean" class="com.springbook.biz.CollectionBean">
	<property name="addressList">
		<props>
			<prop key ="고길동">서울시 강남구 역삼동</value>
			<prop key ="마이콜">서울시 강서구 화곡동</value>
		</props>
	</property>
</bean>
~~~


<H1>4. 어노테이션 기반 설정</H1>
<H2>4.1 어노테이션 설정 기초</H2>
<p>스프링 프레임워크 역시 대부분의 프레임워크 처럼 XML 설정이 매우 중요하다. 그만큼 xml파일의 과도한 설정에 대한 부담도 크며, 이로인해 프레임워크 사용을 꺼리기도 한다. 따라서 대부분 프레임워크는 어노테이션을 이용한 설정을 지원한다.</p>

<H3>4.1.1 Context 네임스페이스 추가</H3>
<p>어노테이션 설정을 추가하려면 다음과 같이 스프링 설정 파일의 루트 엘리먼트인 beans에 Context관련 네임스페이스와 스키마 문서의 위치를 등록해야 한다. 이는 p네임스페이스를 추가했을 때처럼 Namespace탭을 선택하고 context 항목을 체크하면 간단히 추가할 수 있다.</p>
<p>버전차이로 안되서 직접추가함.</p>


<H3>4.1.2 컴포넌트 스캔 설정</H3>
<p>스프링 설정 파일에 애플리케이션에서 사용할 객체들을 bean등록하지 않고 자동으로 생성하려면</p>

~~~
<context:component-scan/>
~~~

<p>이라는 엘리먼트를 정의해야 한다. 이 설정을 추가하면 스프링 컨테이너는 클래스 패스에 있는 클래스들을 스캔하여 @Component가 설정된 클래스들을 자동으로 객체 생성한다.</p>
<p>context 설정을 제외한 나머지 bean 설정은 삭제하거나 주석처리한다.</p>

<H3>4.1.3 @Component</H3>
<p>컴포넌트 스캔 설정을 했으면 스프링 설정 파일에 클래스들을 일일이 bean등록 할 필요가 없다. @Component만 클래스 선언부 위에 설정하면 끝난다.</p>
<p>컴포넌트나 빈등록이나 기본 생성자가 있어야만 컨테이너가 객체를 생성할 수 있다.</p>
<p>클라이언트 프로그램에서 객체를 요청할 수는 없다. 클라이언트가 스프링 컨테이너가 생성한 객체를 요청하려면, 요청할 때 사용할 아이디나 이름이 반드시 설정되어 있어야 한다. @Component("아이디나이름")</P>
<br />
<p>스프링 컨테이너가 클래스 객체를 생성할 때, 이름이나 아이디 속성을 지정하지 않았다면, 컨테이너가 자동으로 이름을 설정해준다. 이때 이름 규칙은 클래스 이름의 첫 글자를 소문자로 변경하기만 하면 된다. 따라서 LgTV의 경우 lgTV가 된다.</p>

<H2>4.2 의존성 주입 설정</H2>
<H3>4.2.1 의존성 주입 어노테이션</H3>
<p>스프링에서 의존성을 주입하는 어노테이션으로는 @Autowired, @Inject, @Qualifier,@Resource 가 있다.</p>

어노테이션| 설명
---|---
@Autowired|주로 변수 위에 설정하여 해당 타입의 객체를 찾아서 자동으로 할당
@Qualifier|특정 객체의 이름을 이용하여 의존성 주입시 사용
@Inject|@Autowired와 동일
@Resource|@Autowired와 @Qualifier의 기능을 결합한 어노테이션

<p>@Autowired와 @Qualifier 는 스프링에서 제공하지만 나머지는 스프링에서 제공하지 않는다.</p>

<H3>4.2.2 @Autowired</H3>
<p>@Autowired는 생성자나 메소드, 멤버변수 위에 모두 사용할 수 있다. 어디에 사용하든 결과가 같지만 대부분은 변수위에 선언하여 사용한다.</p>
<p>스프링 컨테이너는 멤버변수위에 붙은 @Autowired를 확인하는 순간 해당 변수의 타입을 체크하고  그 타입의 객체가 메모리에 존재하는지 확인 후 그 객체를 변수에 주입한다.</p>
<p>그런데 만약 @Autowired가 붙은 객체가 메모리에 없다면 컨테이너가 NoSuchBeanDefinitionException을 발생시킨다.</p>
<p>Autowired로 lgtv클래스에 스피커를추가할 때 소니나 애플스피커 객체가 생성되어있어야 하므로 Component 어노테이션을 넣는다.</p>


<H3>4.2.3 @Qualifier</H3>
<p>문제는 의존성 주입 대상이 되는 speaker 타입 객체가 두개 이상일 때 발생한다. sony, apple 두 객체가 모두 생성되어 있는 사오항이라면 컨테이너는 스스로 어떤 객체를 할당할지 스스로 판단할 수 없어서 에러가 발생한다</p>
<p>이 문제를 해결하기 위해 스프링은 @Qualifier 어노테이션을 제공한다. autowired와 qualifier를 적용하여 해결한다.</p>
<p>@Qualifier 어노테이션을 이용하면 의존성 주입될 객체의 아이디나 이름을 지정할 수 있다.</p>


<H3>4.2.4 @Resource</H3>
<p>autowired가 변수 타입을 기준으로 객체를 검색하여 의존성 주입을 처리하지만 resource는 객체의 이름을 이용하여 의존성 주입을 처리한다.</p>
<p>name 속성을 사용할 수 있어서 스프링 컨테이너가 해당 이름으로 생성된 객체를 검색하여 의존성 주입을 처리한다.</p>
<p>@Inject 또한 같은 기능으로 둘다 이름을 기반으로 의존성 주입을 처리한다.</p>

<H3>4.2.5 어노테이션과 XML 설정 병행하여 사용하기</H3>
<p>스프링으로 의존성 주입을 처리할 때, xml 설정과 어노테이션 설정은 장단점이 서로 상충한다. 앞에서 살펴본대로 xml방식은 자바 소스를 수정하지 않고 xml 파일의 설정만 변경하면 실행되는 speaker를 교채할 수 있어서 유지보수가 편하다. 하지만 xml설정에 대한 부담 역시 존재한다</p>
<p>그리고 자바 소스에 의존관계와 관련된 어떤 메타데이터도 없으므로 xml 설정을 해석해야만 무슨 객체가 의존성 주입되는지를 확인할 수 있다.</p>
<p>어노테이션 기반 설정은 xml 설정의 부담도 없고 의존관계정보가 자바소스에 있어 사용하기는 편하다. 하지만 의존성 주입할 객체의 이름이 자바소스에 명시되어야 하므로 자바 소스를 수정하지 않고 speaker를 교체할 수 없다는 문제가 생긴다.</p>
<p>서로의 장점을 조합하는 것으로 해결할 수 있다.</p>

<br />
<p>tv에서 autowired만 사용하고 스피커의 컴포넌트를 모두 제거한다. 그 후 xml파일에 하나의 스피커만 등록하면 xml파일만 그때 그때 수정하면 된다.</p>
<p>자바소스에서 의존관계를 확인할 수 있으면서, xml파일만 수정하면 된다.</p>
<p>클라이언트가 요청한 lgtv는 컴포넌트 어노테이션으로 처치하고, 의존성중비 역시 autowired로 처리한다. 다만, 변경될 스피커만 스프링 설정 파일에 bean등록함으로써 xml설정만으로 스피커를 교체할 수 있다.</p>


<br />
<p>우리가 직접 개발한 클래스는 어노테이션을 사용할 수도 있고, xml설정을 할 수도 있다. 하지만 라이브러리 형태로 제공되는 클래스는 반드시 xml 설정을 통해서만 사용할 수 있다.</p>
<p>따라서 아파치에서 제공하는 BasicDataSource클래스를 사용하여 DB연동을 처리한다면 commons-dbcp-1.4.jar 파일에 있는 BasicDataSource 클래스에 관련된 어노테이션을 추가할 수 없다. 스프링 컨테이너가 생성하도록 하려면 bean등록 해야한다.</p>

<H3>4.3 추가 어노테이션</H3>

![KakaoTalk_20210509_143111807](https://user-images.githubusercontent.com/65153512/117561638-7015d880-b0d3-11eb-8b62-d8fc32765972.jpg)

<p>다음 그림은 시스템의 전체 구조를 두개의 레이어로 표현한 것이다. 프레젠테이션 레이어는 사용자와 커뮤니케이션을 담당하고, 비즈니스 레이어는 사용자의 요청에 대한 비즈니스 로직 처리를 담당한다.</p>
<p>이 구조의 핵심요소는 Controller, ServiceImpl, DAO 클래스이다. 컨트롤러 클래스는 사용자의 요청을 제어하고 ServiceImpl 클래스는 실질적인 비즈니스 로직을 처리한다. DAO(Data Access Object) 클래스는 데이터베이스 연동을 담당한다</p>
<p>우리는 컴포넌트 어노테이션을 이용하여 스프링 컨테이너가 해당 클래스 객체를 생성하도록 설정할 수 있었다, 그런데 모든 클래스에 컴포넌트 어노테이션을 할당하면 어떤 클래스가 어떤 역할을 수행하는지 알기 어렵다. 스프링 프레임워크에서는 이런 클래스들을 분류하기 위해 컴포넌트 어노테이션을 상속하여 다음과 같은 세개의 어노테이션을 추가로 제공한다.</p>

어노테이션| 위치| 의미
---|---|---
@Service|XXXServiceImpl|비즈니스 로직을 처리하는 Service 클래스
@Repository|XXXDAO|데이터베이스 연동을 처리하는 DAO 클래스
@Controller|XXXController|사용자 요청을 제어하는 Controller 클래스

<p>어노테이션을 이처럼 나눈 이유는 단순히 해당 클래스를 분류하기 위함뿐만이 아니고, 컨트롤러는 객체를 MVC아키텍처에서 컨트롤러 객체로 인식하고, Repository는 DB 연동 과정에서 발생하는 예외를 변환해주는 특별한 기능이 추가된다.</p>

<H1>5. 비즈니스 컴포넌트 실습1</H1>
<H2>5.1 BoardService 컴포넌트 구조</H2>
<p>일반적으로 비즈니스 컴포넌트는 네 개의 자바 파일로 구성된다. 그리고 각 자바 파일을 작성하는 순서와 이름도 규칙도 어느정도는 정해져있는 것이 일반적이다.</p>

![KakaoTalk_20210509_150924127](https://user-images.githubusercontent.com/65153512/117562269-9f7b1400-b0d8-11eb-9e96-af084caaa48f.jpg)

<p>다음은 BOARD 테이블과 관련된 BoardService 컴포넌트에 대한 클래스 다이어그램이며, BoardVO, BoardDAO, BoardService, BoardServiceImpl 클래스로 구현되어 있다.</p>

<H2>5.2 VO(Value Object) 클래스 작성</H2>
<p>vo클래스는 레이어와 레이어 사이에서 관련된 데이터를 한꺼번에 주고받을 목적으로 사용하는 클래스이다.</p>
<p>DTO(Data Transfer Object)라 하기도 하는데, 데이터 전달을 목적으로 사용하는 객체이므로 결국 같은 의미의 용어라고 생각하면 된다.</p>
<p>VO 클래스를 작성하려면 가장 먼저 데이터베이스에 생성되어 있는 테이블의 구조를 확인해야 한다.</p>

<p>BOARD 테이블 이름 뒤에 VO나 DTO를 붙여서 클래스 이름으로 사용한다. 그리고 BOARD테이블에 포함된 칼럼과 같은 이름의 멤버변수를 private 접근제한다로 선언한다. 그리고 private변수에 접근하는 getter/setter 메소드를 선언하면 VO클래스는 마무리된다.</p>
<p>옵션으로 toString() 메소드도 생성해 놓으면 나중에 VO객체의 값을 출력할 때 요긴하게 사용할 수 있다.</p>

<H2>5.3 DAO 클래스 작성</H2>
<p>DAO 클래스는 데이터 베이스 연동을 담당하는 클래스이다. 따라서 DAO 클래스에는 CRUD기능의 메소드가 구현되어야 하는데, 이때 우리가 사용할 H2데이터 베이스에서 제공하는 JDBC 드라이버가 필요하다.</p>
<p></p>

<H3>5.3.1 드라이버 내려받기</H3>
<p>BoardWeb 프로젝트에 있는 pom.xml 파일을 열고 데이터베이스 dependency를 추가한다. 추가하면 Maven Dependencies 에 추가된것을 확인할 수 있다.(코드참조)</p>

<H3>5.3.2 JDBC Utility 클래스</H3>
<p>Mybatis같은 프레임워크를 사용하기 전 까지는 데이터베이스 연동 처리를 JDBC로 할 것이다. 따라서 모든 DAO클래스에서 공통으로 사용할 JDBCUtil클래스를 작성하여 Connection획득과 해제 작업을 공통으로 처리한다.</p>
<p>JDBCUtil클래스 코드참조</p>

<H3>5.3.3 DAO 클래스 작성</H3>
<p>앞에서 설명한 BoardVO 객체를 매개변수와 리턴타입으로 사용하면서 BOARD테이블과 CRUD기능을 처리할 BoardDAO 클래스를 다음과 같이 작성한다(코드참조)</p>
<p>DAO 클래스 이름은 BOARD 테이블 뒤에 DAO만 추가하여 사용한다. 이 클래스 객체를 스프링 컨테이너가 생성할 수 있도록 클래스 선언부에 @Repository설정을 했다. 컴포넌트로 해도 문제가 생기지는 않지만 여러이유에서 repository가 적절하다.</p>
<p>CURD 기능의 메소드 이름 역시 일관성을 위해 대부분 다음과 같은 규칙을 사용한다.</p>

기능| 메소드 이름
---|---
등록|insert테이블명
수정|update테이블명
삭제|delete테이블명
상세조회|get테이블명(혹은 select테이블명)
목록검색|get테이블명List(혹은 select테이블명List)

<H2>5.4 Service 인터페이스 작성</H2>
<p>DAO 클래스를 작성했으면 DAO클래스에서 단축키를 이용하여 BoardService인터페이스를 작성한다. 이때 인터페이스가 만들어지는 동시에 BoardDAO클래스에는 implements코드가 자동으로 설정되는데 이 implements를 삭제해야 한다.</p>
<p>클래스 다이어그램을 확인해보면 알지만 BoardService 인터페이스는 BoardServiceImpl클래스가 구현해야 하고, BoardDAO클래스는 독립된 클래스이다.</p>

<H2>5.5 Service구현 클래스 작성</H2>
<p>이제 마지막으로 BoardService 인터페이슬르 구현한 BoardServiceImpl 클래슬르 구현하면 비즈니스 컴포넌트가 마무리 된다.</p>
<p>BoardServiceImpl클래스의 비즈니스 메소드를 구현할 때, 멤버변수로 선언된 BoardDAO를 이용하면 된다.</p>
<p>BoardService 인터페이스를 구현한 BoardServiceImpl 클래스는 BoardService 인터페이스의 모든 추상 메소드를 재정의 하여 구현해야 한다. 그리고 클래스 선언부에 객체생성을 위한 @Service가 선언되어 있고, 클라이언트 프로그램에서 boardService라는 이름으로 객체 요청을 할 수 있도록 아이디도 설정했다.</p>
<p>BoardServiceImpl은 데이터베이스 연동이 포함된 비즈니스 로직 처리를 위해 BoardDAO타입의 객체를 멤버변수로 가지고 있다. 그리고 이 변수에 BoardDAO타입의 객체를 의존성 주입하기 위해 @Autowired를 설정했다.</p>


<H2>5.6 BoardService 컴포넌트 테스트</H2>
<H3>5.6.1 스프링 설정 파일 수정</H3>
<p>작성된 BoardService 컴포넌트를 스프링 기반으로 테스트하려면 우선 스프링 설정 파일에 컴포넌트 스캔 설정을 수정한다.</p>
<p>컴포넌트 스캔 범위를 해당 패키지로 지정했으므로 BoarServiceImpl클래스와 BoardDAO클래스가 스캔 범위에 포함되어 객체가 생성된다. 의존성도 주입된다.</p>
<p></p>

<H3>5.6.2 클라이언트 작성 및 실행</H3>
<p>스프링 컨테이너를 구동하고 BoardService 컴포넌트를 사용하는 클라이언트 프로그램을 다음과 같이 작ㅎ성하여 글 등록 기능과 글 목록 검색 기능을 테스트한다.</p>
<p>작성된 클라이언트 프로그램을 실행하기전 반드시 H2 서버가 구동중인지 확인해야 한다.</p>

<H1>6. 비즈니스 컴포넌트 실습 2</H1>
<p>BoardService 컴포넌트를 만들었으면 이제 회원정보를 UserService 컴포넌트를 추가로 개발한다. 이번에는 어노테이션 없이 Setter 인젝션으로 의존성을 주입하고 나서 어노테이션으로 변경해보도록 한다.</p>
<H2>6.1 UserService 컴포넌트 구조</H2>
<p>UserService 컴포넌트에 대한 클래스 다이어그램이다. BoardService 보다 심플하게 구성되어 있다.</p>

![3](https://user-images.githubusercontent.com/65153512/117607891-e67c0e80-b197-11eb-958c-aa1223f0f69f.jpg)

<H2>6.2 Value Object 클래스 작성</H2>
<p>VO 클래스를 작성하기 위해서 USERS 테이블의 구조를 확인한다.</p>
<p>users테이블의 칼럼 이름과 매핑되는 멤버변수를 가진 UserVO클래스를 작성한다.</p>

<H2>6.3 DAO 클래스 작성</H2>
<p>JDBCUtil 클래스를 이용하여 UserDAO 클래스의 메소드를 구현한다. 다만 UserDAO클래스에는  회원 정보 하나를 검색하는 getUser메소드만 구현한다.</p>
<p>UserDAO 클래스는 정상적인 bean 등록으로 객체를 생성할 예정이므로 어노테이션은 설정하지 않는다.</p>


<H2>6.4 Service 인터페이스 작성</H2>
<p>UserDAO클래스에서 이클립스 단축키 alt + shift + t 로 인터페이슬르 작성한다. UserDAO클래스에 추가되는 상속은 삭제한다.</p>


<H2>6.5 Service 구현 클래스 작성</H2>
<p>UserService 인터페이스를 구현하는 UserServiceImpl 클래스를 만든다.</p>
<p>UserServiceImpl 클래스의 비즈니스 메소드를 구현할 때, 멤버변수로 선언된 UserDAO 객체를 이용하여 DB 연동을 처리하면 된다. UserServiceImpl 클래스에는 Setter 인젝션 처리를 위한 Setter 메소두가 추가되었다.</p>


<H2>6.6 UserService 컴포넌트 테스트</H2>
<p>작성된 UserService 컴포넌트를 테스트 하기 위해 스프링 설정 파일에 UserServiceImpl와 UserDAO 클래스를 bean 등록한다. 그리고 UserServiceImpl 클래스에서 UserDAO 객체를 의존성 주입하기 위한 property 설정을 추가한다.</p>
<p>이후 UserServiceClient 프로그램을 작성한다.</p>

<H2>6.7 어노테이션 적용</H2>
<p>Setter 인젝션 설정으로 테스트 한 UserService 컴포넌트를 어노테이션 설저으로 변경해본다.</p>
<p>설정파일의 setter인젝션 관련 설정을 제거한다. 이후 UserDAO와 UserServiceImpl클래스에 어노테이션을 추가한다.</p>