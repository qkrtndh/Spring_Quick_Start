# Spring_Quick_Start
<p>SPRING QUICK START (저.채규태, 출.PUBY PAPER)</p>

<img src="http://image.yes24.com/momo/TopCate853/MidCate001/85204414.jpg">

<p>위의 책을 보며 공부한 것을 정리(4/5)</p>

<H1>1. 어노테이션 기반 MVC 개발</H1>
<p>스프링은 어노테이션 기반 설정을 제공함으로써 과도한 xml설정으로 인한 문제를 해결한다. Spring MVC도 설정 파일에 handlermapping,controller,viewresolver 같은 여러 클래스를 등록해야 하므로
어노테이션 설정을 최대한 활용하여 xml 설정을 최소화할 필요가 있다.</p>

<H2>1.1 어노테이션 관련 설정</H2>
<p>스프링 MVC에서 어노테이션을 사용하려면, 먼저 beans 루트 엘리먼트에 context 네임 스페이스를 추가하고, handermapping,controller,viewsesolver 클래스에 대한 bean등록을 제거하고, context:component-scan 엘리먼트로 대체한다.</p>
<p>이때 모든 Controller 클래스가 스캔 범윙 ㅔ포함되도록 하고자 Contorller클래스들이 있는 가장 상위 패키지인 com.springbook.view를 등록한다.
또한, 어노테이션 활용에 집중하기 위해 ViewResovler설정을 삭제한다. 따라서 getBoard.jsp와 getBoardList.jsp도 webapp폴더로 옮긴다.</p>

<H2>1.2 @Controller 사용하기</H2>
<p>기존에는 스프링 컨테이너가 Controller 클래스를 생성하게 하려면 Controller 클래스들을 스프링 설정 파일에 bean등록 해야 했다, 하지만 어노테이션을 사용하면 컨트롤러 클래스 모두를 일일이 등록할 필요 없이 @Controller를 붙이면 된다. 스프링 설정 파일에 context:component-scanf으로 스프링 컨테이너가 컨트롤러 객체들을 자동 생성하기 때문이다.</p>
<p>@Component를 상속한 @Controller는 @Controller가 붙은 클래스의 객체를 메모리에 생성하는 기능을 제공한다. 단순히 객체를 생성하는 것 외에도, DispatcherServlet이 인식하는 Controller객체로 만들어준다</p>
<p>만일 @Controller를 사용하지 않는다면, 컨트롤러 클래스는 반드시 스프링에서 제공하는 Cotnroller 인터페이스를 구현해야 하고, handleRequest 메소드를 재정의하여 DispatcherServlet이 모든 Controller의 handleRequest 메소드를 호출할 수 있도록 해야 한다.</p>
<p>이제 InsertBoardController를  스프링 프레임워크가 지향하는 POJO스타일로 구현해보자.</p>
<p>insertboardcontroller 클래스 객체는 스프링 컨테이너가 자동으로 생성하고, Controller 객체로 인식한다. 중요한것은 insertboardcontroller가 pojo클래스로 변경되었으므로 메소드 이름을  insertboard, 리턴타입을 void, 매개변수를 HttpServletRequest로 변경한다.</p>

<H2>1.3 @RequestMapping 사용하기</H2>
<p>앞에서 처럼 @Controller를 클래스 위에 추가함으로써  InsertBoardController 객체를 생성하고 Controller로 인시갛게 할 수는 있지만. 클라이언트의 /insertBoard.do 요청에 대해서 insertBoard메소드가 실행되도록할 수는 없다.
기존에는 handlerMapping을 이용하여 클라이언트의요청을 매핑했다.</p>
<p>스프링에서는 @RequestMapping을 이용하여 HanderMapping 설정을 대체한다.
이 설정은 클라이언트로부터 /insertBoard.do 라는 요청이 있을 때, insertBoard 메소드를 매핑하겠다는 설정이다. 여기서 @RequestMapping의 value 속성은 생략할 수 있으며, 특별한 경우가 아니면 대부분 생략한다.</p>

<H3></H3>
<p></p>
<p></p>

<H3></H3>
<p></p>
<p></p>