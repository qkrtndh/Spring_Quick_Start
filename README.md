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

<H2>1.4 클라이언트 요청 처리</H2>
<p>대부분 Controller는 사용자의 입력 정보를 추출하여 VO 객체에 젖아한다. 그리고 비즈니스 컴포넌트의 메소드를 호출할때 VO객체를 인자로 전달한다.
사용자 입력 정보는 HttpServletRequest 의 getParameter메소드를 사용하여 추출한다. 따라서 InsertBoardController를 위와 같이 작성하고 실행해도 글 등록 작업은 정상처리된다.</p>
<p>문제는 사용자가 입력하는 정보가 많거나 변경되는 상황으로,사용자입력 정보가 많으면 그만큼의 자바 코드가 필요하고 Controller클래스가 수정되어야 한다.</p>
<p>하지만 Command 객체를 이용하면 이런 문제를 모두 해결할 수 있다. Command객체는 Controller 메소드 매개변수로 받은 vo객체라고 보면 된다. InsertBoardController클래스의 insertBoard메소드르 Command객체를 이용하여 구현한다.</p>

<br />
<p>insertBoard메소드의 매개변수로 사용자가 입력한 값을 매핑할 BoardVO 클래스 선언하면, 스프링 컨테이너가 insertBoard메소드를 실행할 때 Command 객체를 생성하여 넘겨준다. 이때 사용자가 입력한 값들을 Command 객체에 세팅까지 해서 넘겨준다. 결과적으로 사용자 입력 정보 추출과 VO객체 생성, 값 설정을 모두 컨테이너가 자동으로 처리한다.</p>
<p>스프링 컨테이너가 사용자 입력값을 Command 객체에 자동으로 설정하는 과정을 이해하기 위해 서블릿 컨테이너의 동작 방식을 살펴본다.</p>

![20](https://user-images.githubusercontent.com/65153512/118351526-81f5f080-b597-11eb-9531-782841eacaf0.jpg)

<p>서블릿 객체는 서블릿 컨테이너가 생성한다. 그리고 service,doGet,doPost메소드도 서블릿 컨테이너가 호출한다, 이때 메소드가 정상 호출되려면 HttpServletRequest와 HttpServletResponse 객체가 필요한데, 이 객체들도 서블릿 컨테이너가 생성해서 넘겨준다.
결국 serviece 메소드는 매개변수로 받은 HttpServletRequest 객체를 통해 다양한 요청 처리를 구현할 수 있다.</p>
<p>아래 그림은 서버에 insertBoard.do 요청을 전달할때 스프링 컨테이너가 @Controller가 붙은 모든 컨트롤러 객체르 ㄹ생성하고 insertboardcontroller가 가진 insertVBoard메소드를 실행하고 BoardVO객체를 스프링 컨테이너가 생성하여 전달하는 과정이다.</p>

![21](https://user-images.githubusercontent.com/65153512/118351614-ef098600-b597-11eb-81e6-864b633703ea.jpg)

<p>Form 태그안의 파라미터 이름과 Command 객체의 setter 메소드 이름이 반드시 일치해야 한다. 즉 각 파라미터 이름에 해당하는 메소드들이 있어야 setter 인젝션에 의해 자동으로 사용자 입력값이 저장된다.</p>

![22](https://user-images.githubusercontent.com/65153512/118351612-edd85900-b597-11eb-9e20-6b2adcf4732c.jpg)

<H1>2. 어노테이션으로 게시판프로그램 구현하기</H1>
<H2>2.1 글 등록 기능 구현하기</H2>
<p>InsertBoardController 클래스에서 리턴타입과 매개변수를 수정한다.</p>
<p>글 등록 처리가 성공한 후에는 글 목록읓 출력해야 한다. 따라서 GetBoardListController를 실행시키기 위해 리턴값을 String으로 수정하고 getBoardList.do를 리턴한다.
그리고 사용자 입력값을 Command객체로 받기 위해 BoardVO클래스를 매개변수로 선언했고, DB연동 처리를 위해 BoardDAO도 매개변수로 선언했따. DAO 객체 역시 Command객체와 마찬가ㅣㅈ로 매개변수로 선언하면 스프링 컨테이너가 객체를 생성하여 전달해준다.</p>

<H2>2.2 글 목록 검색 구현하기</H2>
<p>GetBoardListController 역시 @Controller, @RequestMapping 을 이용하여 구현한다.
getBoardList 메소드는 세 개의 매개변수를 선언했는데, 첫 번째가 사용자 입력값을 받기 위한 BoardVO 클래스이고, 두 번째가 DB연동 처리를 위한 BoardDAO 클래스이다. 그리고 마지막으로 검색결과와 화면 정보를 저장하여 리턴하기 위한 ModelAndView를 매개변수로 선언하여 컨테이너가 생성하도록 처리했다.</p>

<H2>2.3 글 상세 보기 구현하기</H2>
<p>GetBoardListController를 참조하여 구현한다.</p>

<H2>2.4 글 수정 기능 구현하기</H2>
<p>InsertBoardController를 참고하여 구현한다</p>
<p>클라이언트로부터 /updateBoard.do 요청이 들어오면 updateBoard메소드가 실행되고, 수정에 필요한 데이터는 매개변수로 선언된 BoardVO 객체를 통해 받아낸다. 그리고 BoardDAO 객체의 updateBoard메소드를 호출하여 글 등록을 처리한 후에 getBoardList.do 를 리턴하여 글 목록 화면으로 이동한다.</p>

<H2>2.5 글 삭제 기능 구현</H2>
<p>글 등록이나 수정기능을 참조하여 구현한다.</p>

<H2>2.6 로그인 기능 구현</H2>
<p>@Controller와 @RequestMapping 어노테이션을 이용하여 구현한다.</p>
<p>클라이언트의 /login.do 요청에 대해서 실행되는 login메소드는 사용자가 입력한 아이디와 비밀번호를 받아내기 위해 UserVO클래스를 매개변수로 선언했다. 그리고 DB연동을 처리하기 위해 UserDAO클래스도 두번째 매개변수로 선언했다. 마지막으로 로그인 성공과 실패에 대해 화면 정보를 리턴한다.</p>

<H2>2.7 로그아웃 기능 구현</H2>
<p>로그아웃을 구현할때는 세션객체를 사용해야 한다.</p>
<p>/logout.do 요청으로 실행되는 logout메소드는 로그아웃 처리를 위해 세션 객체가 필요하다. 이때 HttpSession을 매개변수로 선언하면 스프링 컨테이너가 로그아웃을 요청한 브라우저와 매핑된 세션객체를 찾아서 넘겨준다.
따라서 매개변수로 받은 세션객체를 강제종료하면 된다.</p>

<H2>2.8 컨트롤러 통합하기</H2>
<p>어노테이션을 이용하여 Controller 클래스를 구현하면 대부분 4~5줄 내외로 간단하게 구현된다. 이렇게 간단하게 구현되는 Controller를 하나의 클래스로 묶어서 처리하면 관리가 더 편할것이다. 따라서 BoardController 클래스를 작성하여 게시판 작성Controller를 통합한다.</p>
<p>이와같이 BoardController클래스를 구현하면 스프링 컨테이너가 BoardController객체를생성한다. 그리고 클라이언트의 요청 패스에 따라 @RequestMapping 설정된 메소드를 실행한다. 나머지 Controller들을 삭제한다.</p>

<H3></H3>
<p></p>
<p></p>