# Spring_Quick_Start
<p>SPRING QUICK START (저.채규태, 출.PUBY PAPER)</p>

<img src="http://image.yes24.com/momo/TopCate853/MidCate001/85204414.jpg">

<p>위의 책을 보며 공부한 것을 정리</p>

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

<H4></H4>
<p></p>
<p></p>
