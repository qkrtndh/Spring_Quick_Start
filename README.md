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

<H2>2.9 요청방식에 따른 처리</H2>
<H3>2.9.1 method 속성</H3>
<p>@RequestMapping을 이용하면 마치 Servlet처럼 클라이언트의 요청 방식(GET/POST)에 따라 수행될 메소드를 다르게 설정할 수 있다.</p>
<p>클라이언트가 GET방식으로 입력폼을 요청하면 입력 화면을 보여주고, 입력화면에서 submit버튼을 클릭하여 POST 방식으로 요청하면 클라이언트의 요청을 적절히 처리하고자할 때 이 방법을 사용한다. </p>
<p>LoginController 클래스에 다음과같이 loginView 메소드를 추가하고 설정을 변경한다.</p>
<br />
<p>loginView와 login 두개의 메소드가 선언되어 있고, 둘다 /login.do 요청에 실행되도록 설정했다. 같은 요청에 실행될 메소드가 두개 이므로, 에러가 발생할 수 있다.
하지만 각기 다르게 처리되도록 method속성을 추가한다.</p>
<p>클라이언트의 /login.do 요청이 GET방식의 요청이라면 스프링 컨테이너는 loginview 메소드를 실행하고 로그인 화면으로 이동한다. POST 방식으로 요청이 들어오면 login메소드를 실행하여 실직적인 로그인 인증 작업을 처리한다.</p>
<p>loginview메소드는 로그인화면으로 이동할때 실행되는 메소드로, 사용자가 입력할 값이 아무것도 없는 상태인데 매개변수로 UserVO 객체를 받아들이도록 설정했다.
논리적으로 맞지 않아보이지만 매개변수로 받은 Command객체에 적절한 데이터를 설정하면 ,리턴된 JSP파일에서 이 데이터를 이용할 수 있다.</p>

<H3>2.9.2 JSP에서 Command 객체 사용</H3>
<p>Command 객체에 저장된 데이터를 JSP에서 사용하려면 ${}구문을 사용한다.</p>
<p>테스트를 위한 index.jsp파일을 src/main/webapp 에 작성한다.</p>
<p>클라이언트가 직접 URL을 입력하거나 하이퍼링크를 클릭하여 요청하면 기본이 GET방식이다. 따라서 index.jsp화면에서 로그인 링크를 클릭하면 서버에 login.do 요청이 전달되고, 이때 GET방식의 요청이 전달되므로
logoinview 메소드가 실행된다. login,jsp화면이 브라우저에 출력될때 userVO 객체에 저장한 id,password가 자동으로 설정된다.</p>

<H3>2.9.3 @ModelAttribute 사용</H3>
<p>스프링 컨테이너가 생성하는 Command 객체의 이름은 클래스 이름의 첫 글자를 소문자로변경한 이름이 자동으로 설정된다. 따라서 login.jsp 화면에서 UserVO 객체의 변수에 접근할 때 ${userVO.변수명} 을 사용했다. 
그런데 Command객체의 이름을 변경하려면 아래와같이 @ModelAttribute를 사용해야 한다.</p>

![23](https://user-images.githubusercontent.com/65153512/118357264-2dad3980-b5b4-11eb-9532-1e2e0c9ca038.jpg)

<H2>2.10 Servlet API 사용</H2>
<p>Controlle 메소드에서 사용자가 입력한 정보를 추출하기 위해 HttpServletRequest 대신 Command객체를 사용했다. 하지만 HttpServletRequest객체가 사용자 입력값을 추출할 때만 사용되는 것은 아니다.
HttpServletRequest가 객체가 제공하는 다양한 메소드를 이용하여 Controller를 구현해야 할 때는 HttpServletRequest객체를 매개변수로 받아야 한다.
스프링 MVC에서는 Controller 메소드 매개변수로 다양한 Servlet API를 사용할 수 있도록 지원한다.</p>
<p>HttpSession 객체를 매개변수로 받아서 로그인 성공 시에 사용자 이름을 세션에 저장하고, 글 목록 화면에서 출력해보기 위해 LoginController를 수정한다. 이후 getBoardList.jsp에서 출력해본다.</p>

<H2>2.11 Controller의 리턴타입</H2>
<p>Controller에 메소드를 정의할 떄, 리턴타입은 개발자 마음대로 결정할 수 있다. String은 완벽한 View이름을 문자열로 리턴한다는 뜻이고, ModelAndView로 설정하면 검색된 Model데이터와 View이름을 모두 저장하여 리턴하겠다는 의미이다.</p>

<p>다음은 ModelAndView와 String을 리턴타입으로 사용한 경우를 비교한 것이다.</p>

![24](https://user-images.githubusercontent.com/65153512/118357575-c8f2de80-b5b5-11eb-8361-a7b5d5aa3c90.jpg)

<p>실행 결과는 같지만 대부분 프로젝트는 일관성있는 코드를 중요하게 생각하므로 리턴타입을 하나로 통일하여 사용하며, String을 사용하는 것이 더 간결하기 때문에 대부분 String으로 통일한다.</p>
<p>BoardController의 getBoard와 getBoardList 메소드를 수정한다.</p>
<p>String으로 리턴타입을 변경했으므로 ModelAndView객체를 사용할 필요가 없다. 그리고 View정보를 ModelAndView에 저장하지 않고 리턴하고 있는데, 이렇게 View이름이 문자열로 리턴되면 스프링 컨테이너는 리턴된 JSP 파일을 찾아 실행한다.</p>
<p>매개변수가 Model로 변경되었는데, MAV에 저장했던 결과는 이제 Model에 저장한다. MAV는 Model과 View정보를 모두 저장하여 리턴할때 사용하지만, JSP화면에서 검색결과를 출력하려면 Model을 사용해야한다. 저장된 정보는 MAV와 동일하게 JSP화면에서 사용할 수 있다.</p>

<H2>2.12 기타 어노테이션</H2>
<H3>2.12.1 @RequestParam 사용하기</H3>
<p>Command 객체를 이용하면 클라이언트에서 넘겨준 요청 파라미터 정보를 받아낼 수있지만 이를 위해선 반드시 요청 파라미터와 매핑될 변수와 setter 메소드가 command 클래스에 선언 되어 있어야 한다.
그런데 command 객체에는 없는 파라미터를 controller클래스에서 사용하려면 어떻게 해야 할까?</p>
<p>SPring MVC에서는 HTTP요청 파라미터 정보를 추출하기 위한 @ReauestParam을 제공한다. 이를 이용하면 Command 클래스에는 없는 파라미터 정보를 추출할 수 있다. 간단한 예제를 통해 @RequestPram의 기능을 확인해보자.</p>
<p>사용자가 글 목록 화면에서 검색 조건과 검색 키워드를 입력하고 검색 버튼을 클릭하면 "/getBoardList.do"와 매핑된 getBoardList()메소드가 실행된다. 하지만 BoardVO라는 Command객체에는
searchCondition, searchKeyword라는 변수와 Setter 메소드가 없다. 따라서 BoardVO를 Command 객체로 사용할 수는 없다. 이때 @RequestPram을 사용하면 검색과 관련된 파라미터 정보를 추출할 수 있다. @RequestPram은 HttpServletRequest에서 제공하는 getPrameter 메소드와같은 기능의 어노테이션이라 할 수 있다.
사용자가 입력한 검색조건과 키워드 정보를 추출하는 BoardController의 getBoardList 메소드를 작성한다.</p>

~~~
@RequestParam에서 속성의 의미

@RequestParam(value="searchCondition",defaultValue="TITLE",required=false) String condition

value = 화면으로부터 전달될 파라미터 이름
defaultValue = 화면으로부터 전달될 파라미터 정보가 없을 때 설정할 기본값
required = 파라미터의 생략 여부
~~~

<p>이 설정은 searchCondition이 필수 파라미터는 아니지만, 정보가 전달된다면 해당 값을 추출하여 condition변수에 할당하고, 파라미터 정보가 전달되지 않았다면 기본값으로 문자열 TITLE을 할당하라는 의미이다.
@RequestParam을 사용하고 싶지 않다면 BoardVO클래스에 searchCondition,searchKeyword 변수를 추가하고 getter/setter 메소드만 생성하면 처리할 수 있다.</p>

<H3>2.12.2 @ModelAttribute 사용하기</H3>
<p>앞에서 @ModelAttribute를 controller 메소드의 매개변수로 선언된 Command 객체의 이름을 변경할 때 사용했다.(실제사용은 하지 않음)
이처럼 Command 객체의 이름을 변경할 목적으로 @ModelAtrribute를 사용할 수 있지만, View(jsp)에서 사용할 데이터를 설정하는 용도로 사용할 수 있다.</p>
<p>@ModelAtrribute가 설정된 메소드는 @RequestMapping 어노테이션이 적용된 메소드보다 먼저호출된다. 그리고 @ModelAttribute 메소드 실행 결과로 리턴된 객체는 자동으로 Model에 저장된다. 따라서 @ModelAttribute 메소드의 실행 결과로 리턴된 객체를 View 페이지에서 사용할 수 있다.</p>
<p>searchConditionMap()메소드 위에 @ModelAttribute가 선언되었으므로 getBoardList메소드가 살행되기 전에 먼저실행된다. searchConditinoMap()메소드는 다양한 검색 조건이 저장된 conditionMap을 리턴하는데 이 리턴 결과를 다음에 실행된 getBoardList메소드가 리턴한 jsp에서 사용할 수 있다.</p>

![25](https://user-images.githubusercontent.com/65153512/118974261-92e69d80-b9ad-11eb-812b-5653a35fd5ea.jpg)

<p>이제 getBoardList() 메소드가 리턴한 getBoardList.jsp 파일에서는 Model에 저장된 검색 목록과 글 목록을 모두 사용할 수 있다.
Model에 저장된 검색 조건 목록을 출력하도록 getBoardList.jsp 파일을 수정한다.</p>

<H3>2.12.3 @SessionAttributes 사용하기</H3>
<H4>(1) null 업데이트</H4>
<p>@SessionAttrubutes는 수정 작업을 처리할 때, 유용하게 사용할 수 있는 어노테이션이다. 예를들어 상세화면에서 게시글을 수정할때 수정 버튼을 클리갛면 사용자가 입력한 수정제목과 수정 내용 정보를 가지고 "/updateBaord.do" 요청을 전송할 것이고, BoardController의 updateBoard메소드에서는 사용자가 입력한
정보를 이용하여 글 수정 작업을 처리한다. 문제는 사용자가 입력한 정보가 제목과 내용 뿐이고, 작성자 정보는 전달되지 않았기 때문에 Command객체인 BaordVO에 writer 정보가 저장되지 않는다.</p>
<p>현재는 BoardDAO에 updateBoard 메소드가 제목과 내용 정보만 수정하도록 되어있지만, 작성자 컬럼까지 수정하도록 돼어있다면 작성자 파라미터 정보가 전달되지 않으므로 wirter컬럼은 null로 수정된다.
이런 문제를 해결하기 위해 스프링에서는 @SessionAttribute 어노테이션을 제공한다. 우선 @SessionAttribute를 사용하기 전 상황을 살펴본다. BoardController 클래스에 updateBoard 메소드에서 수정할 작성자 이름을 확인하는 코드를 추가한다. 이후 로그인하여 글 수정 요청을 한다. 콘솔에는 writer 정보가 없으므로 null이 출력된다.</p>


<H4>(2) null 업데이트 방지</H4>
<p>@SessionAttrubutes를 이용하여 writer 칼럼 값이 null로 업데이트 되지 않도록 처리한다. BoardController클래스를 수정한다.
글목록 화면에서 특정 게시글 제목을 클릭하면 BoardController의 getBoard 메소드가 실행된다. 제목과 내용에 수정할 값을 입력하고 수정버튼을 클릭하면 updateBoard 메소드가 실행된다.
콘솔에 사용자가 입력한 title, Content 정보뿐만 아니라, wirter, regDate, cnt 파라미터 정보도 모두 설정된것을 확인할 수 있다.</p>
<p>사용자가 상세 화면을 요청하면 getBoard메소드는 검색결과인 BoardVO객체를 board라는 이름으로 Model에 저장한다. 이때 BoardController 클래스에 선언된 @SessionAttributes("board")설정이 매우 중요하다.
이는 Model에 board라는 이름으로 저장되는 데이터가 있다면 그 데이터를 세션에도 자동으로 저장하라는 설정이다.</p>
<p>따라서 getBoard메소드가 실행되어 상세 화면이 출력되면 일차적으로 Model에 board라는 일므으로 BaordVO객체가 저장되고, 세션에도 board라는 이름으로 BoardVO객체가 저장된다. 이 BoardVO객체에는 상세화면에 출력된 모든 정보가 저장되어 있다.
사용자가 수정버튼을 클릭하면 updateBoard 메소드가 실행된다. 중요한것은 매개변수로 선언된BoardVO 앞에 @ModelAttribute가 추가된단 것이다.</p>
<p>BoardController의 updateBoard 메소드가 호출될 때 스프링 컨테이너는 우선 @ModelAttribute("board")설정을 해석하여 세션에 board라는 이름으로 저장된
데이터가 있는지 확인하고 있다면 해당객체를 세션에서 꺼내어 매개변수로 선언된 vo 변수에 할당한다. 그리고 사용자가 입력한 파라미터 값을 vo객체에 할당한다.
사용자가 입력한 수정정보 값만 새롭게 할당되고 나머지는 상세보기 했을 때 세션에 저장된 데이터가 유지된다.</p>

<H1>3. 프레젠테이션 레이어와 비즈니스 레이어 통합</H1>
<p>지금까지 Spring MVC를 기반으로 개발한 게시판 프로그램의 구조는 다음과 같다.</p>

![26](https://user-images.githubusercontent.com/65153512/119129669-2d101980-ba72-11eb-8a13-a06f91ef038f.jpg)

<p>브라우저에서 버튼이나 하이퍼링크를 클릭하여 서버에 요청을 전송하면, 모든 요청을 서블릿 컨테이너가 생성한 DispatcherSerlvet이 받는다. 그리고 DispatcherServlet은 스프링 컨테이너가 생성한 Controller에게 요청을 전달하고, Controller는 매개변수를 통해 전달된 DAO객체를 이용하여 사용자가 요청한 로직을 처리한다.</p>
<p>지금까지 개발한 게시판 프로그램의 기능은 이런 순서로 동작한다. 여기에서 중요한 것은 Controller의 모든 메소드가 사용자의 요청을 처리할때 DAO객체를 직접 이용하고 있다는 것이다. 이렇게 개발한다고 해도 실행되지 않거나  심각한 문제가 발생하지 않는다.
하지만 Controller는 DAO 객체를 직접 이용해서는 안되며, 반드시 비즈니스 컴포넌트를 사용해야 한다.</p>

<H2>3.1 비즈니스 컴포넌트 사용</H2>
<p>1챕터에서 Spring IoC를 학습하면서 만든 비즈니스 컴포넌트는 VO클래스, DAO 클래스, Service 인터페이스, Service 구현 클래스 등 네 개의 파일로 구성되어 있다.
우리는 지금까지 게시판 프로그램을 작성하면서 BoardVO와 BoardDAO 클래스만 사용하고 BoardService 인터페이스나 BoardServiceImpl클래스를 사용하지 않았다. </p>
<p>사실 Controller는 비즨스 컴포넌트를 이용하여 사용자의 요청을 처리해야 하며, 이때 컴포넌트가 제공하는 Serivce 인터페이스를 이용해야 한다. 지금까지 처럼 Controller가 직접 DAO객체의 메소드를 호출하도록 하면 안되며, 이는 여러가지 문제를 일으킨다.
DAO를 직접 사용하는 것이 어떤 문제를 일으키는지, 해결하기 위한 추가적인 작업을 알아보도록 한다.</p>

<H3>3.1.1 DAO클래스 교체하기</H3>
<p>Controller 메소드에서 DAO의 메소드를 호출하면 첫 번째 이유는 유지보수 과정에서 DAO클래스를 다른 클래스로 쉽게 교체하기 위해서 이다. 지금은 BoardController의 모든 메소드가 BoardDAO 객체를 매개변수로 받아 DB연동을 처리하고 있다. 그런데 DAO클래스를 기존에 만든 BoardDAOSpring이나 추가될 다른 DAO클래스로 변경하고자 하면
BoardController의 모든 메소드를 수정해야 한다.</p>
<p>BoardDAO 클래슬르 사용하는 Controller가 BoardController 클래스 하나면 상관 없지만 여러개라면 해당 클래스들을 모두 열어 일일이 매개변수를 수정해야 한다. 이렇게 비즈니스 컴포넌트가 변경되거나, 새로운 요소가 추가될 때마다 이를 사용하는 Controller의 소스를 매번 수정한다면 유지보수는 어려울 수 밖에 없다.</p>
<p>비즈니스 컴포넌트가 수정되더라도 이를 사용하는 Controller 는 수정하지 않아도 되도록 하려면 어떻게 해야할까?. 비즈니스 컴포넌트 입장에서 자신을 사용해주는 클라이언트는 Controller이다. 클라이언트가 인터페이스를 통해서 비즈니스 컴포넌트를 이용하면 컴포넌트의 구현 클래스를 수정하거나 다른클래스로 대체해도 이를 사용하는 클라이언트는 수정하지 않아도 된다. 이것이 다형성의 장점이자 객체지향 언어의 중요한 특징이다.</p>
<p>BoardController의 모든 메소드를 BoardSerivce 컴포넌트의 인터페이스를 사용하도록 수정한다.</p>
<p>우선 boardService 라는 멤버변수를 선언했고, 변수 위에 @Autowired를 설정했으므로 BoardSerice 타입의 BoardServiceImpl 객체가 의존성 주입된다. 그리고 모든 메소드에서 매개변수로 선언된 BoardDAO를 제거했고, 대신 boardService 변수를 이용해서 비즈니스 컴포넌트를 사용하도록 수정했다.</p>
<p>이제 BoardServiceImpl 클래스의 멤버변수로 선언된 BoardDAO를 다른 DAO클래스로 얼마든지 변경할 수 있다. BoardServiceImpl 클래스를 열어서 기존의 BoardDAOSpring 타입의 멤버변수를 다음과 같이 수정해보자.</p>
<p>중요한 것은 이렇게 BoardServiceImpl클래스에서 사용하는 DAO 클래스가변경되어도 클라이언트에 해당하는 BoardController는 수정할 필요가 없다는 것이다. 이제 새로운 DAO클래스가 추가되더라도 Controller를 수정하지 않고 쉽게 적용할 수 있게 되었다.</p>

<H3>3.1.2 AOP 설정 적용하기</H3>
<p>Controller 메소드에서 DAO의 메소드를 호출하면 안되는 두번째 이유는 AOP 적용 때문이다. 두번째 챕터에서 Spring AOP를 학습했고, 모든 비즈니스 컴포넌트의 메소드에서 공통으로 사용할 기능들을 횡단관심, 즉 어드바이스로 구현했다. 그리고 이 어드바이스가 적절한 시점에서 동작할 수 있도록 AOP 관련 설정을 XML이나 어노테이션으로 처리했다. 하지만 지금까지 게시판 프로그램을 개발하고 동작하면서 어드바이스가 동작하지 않았다.</p>
<p>횡단 관심에 해당하는 어드바이스가 동작하려면 반드시 Service 구현 클래스의 비즈니스 메소드가 실행되어야 한다. 스프링 설정파일에서 포인트컷 설정 부분을 다시한번 확인하면.</p>

~~~
XML 설정
<aop:pointcut id="allPointcut" expression="execution(* com.springbook.biz.. *Impl.*(..))"/>

어노테이션 설정
@Aspect
public class PointcutCommon{
	@Pointcut("execution(* com.springbook.biz.. *Impl.*(..))")
	public void allPointcut(){}
}

~~~

<p>중요한 것은 포인트컷을 설정할 때 DAO 클래스의 메소드를 지정한 것이 아니라 Service 구현 클래스의 메소드에 설정했다는 것이다. 따라서 우리가 구현한 모든 어드바이스는 반드시 비즈니스 클래스의 메소드가 호출될 때 동작한다. 
결국 Controller가 매개변수로 DAO 객체를 받아서 DAO의 메소드를 직접 호출하는 현재 상황에서는 AOP로 설정한 어떤 어드바이스도 동작할 수 없다.</p>
<p>그러면 어떻게 해야 AOP설정이 처리되어 어드바이스들이 동작할 수 있을까? 대부분 비즈니스 컴포넌트는 인터페이스를 가지고 있으며 이 인터페이스만 컴포넌트를 사용하는 클라이언트에 노출한다.
따라서 비즈니스 컴포넌트를 사요하는 클라이언트는 인터페이스의 추상 메소드를 호출하여 인터페이스를 구현한 Service 구현 객체의 메소드를 실행할 수 있다.
사실 Spring MVC를 적용하기 이전까지는 모든실습에서 비즈니스 컴포넌트의 기능을 태스트할 때, 다음처럼 처리했기 때문에 어드바이스들이 동작할 수 있었다.</p>

~~~

BoardServiceClient에

//1. spring 컨테이너 구동
AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
//2. Spring컨테이너로부터 BopardServiceImpl객체를 Look up한다.
BoardService boardService = (BoardService) container.getBean("boardService");

~~~

<p>따라서 Controller 클래스는 비즈니스 컴포넌트의 인터페이스 타입의 멤버변수를 가지고 있어야 하며 이 변수에 비즈니스 객체를 의존성 주입해야 한다.
그러면 Controller 메소드에서는 인터페이스를 통해서 비즈니스 객체의 메소드를 호출할 수 있고 결국 AOP로 설정한 어드바이스들이 동작한다.</p>
<p></p>

<H3>3.1.3 비즈니스 컴포넌트 의존성 주입하기.</H3>
<p>지금 상태에서 실행하면 에러가 발생하고 실행되지 않는다. BoardController가 @Autowired로 의존성 주입을 처리하려 하는데, BoardService 타입의 객체가 메모리에 없어서 의존성 주입을 할 수 없다는 에러이다.
@Autowired어노테이션을 사용하려면 의존성 주입 대상이 되는 객체가 반드시 메모리에 올라가야 한다.</p>

![27](https://user-images.githubusercontent.com/65153512/119135936-00600000-ba7a-11eb-8d70-37bb8f39e0dd.jpg)

<p>BoardController보다 의존성 주입될 BoardServiceImpl 객체가 먼저 생성되어 있어야 한다. 하지만 presentation-layer.xml 파일에는 다음과 같이 Controller 객체들만 컴포넌트 스캔하도록 설정되어있기 때문에 BoardServiceImpl객체가 생성되지 않는다.</p>
<p>결국 Controller 보다 의존성 주입 대상이 되는 비즈니스 컴포넌트를 먼저 생성하려면 비즈니스 컴포넌트를 먼저 생성하는 또 다른 스프링 컨테이너가 필요하다. 그리고 이 컨테이너를 Controller를 메모리에 생성하는 컨테이너보다 먼저 구동하면 된다.</p>

<H2>3.2 비즈니스 컴포넌트 로딩</H2>
<H3>3.2.1 2-Layerd 아키텍처</H3>
<p>일반적으로 프레임워크 기반의 웹 프로젝트를 보면 두개의 레이어로 나누어 시스템을 개발하는데, 이를 2-Layered 아키텍처 스타일이라고 한다.</p>

![28](https://user-images.githubusercontent.com/65153512/119217520-b3316c00-bb15-11eb-81a6-5402fd84107e.jpg)

<p>이미 이런 구조로 게시판 프로그램으 개발했다. src/main/resources 소스 폴더에는 비즈니스 레이어에 해당하는 설정파일인 applicationContext.xml이 있으며, /WEB-INF/config 폴더에는 프레젠테이션 레이어에 해당하는 설정 파일인 presentation-layer.xml이 있다.</p>
<p>DispatcherServlet이 생성되어 presentation-layer.xml 파일을 읽고 스프링 컨테이너를 구동하면 Controller 객체들이 메모리에 생성된다 하지만 Controller 객체들이 생성되기전 누군가 먼저 src/main/resources 소스폴더에 applicationContext.xml 파일을 읽어 비즈니스 컴포넌트들을 메모리에 생성해야 한다. 이때 사용하는 클래스가 스프링에서 제공하는 ContextLoaderListener다.</p>

<H3>3.2.2 ContextLoaderListener 등록</H3>
<p>Listener는 Servlet이나 Filter 클래스와 마찬가지로 web.xml파일에 등록한다. listener 태그하위에 listener-class 태그를 이용하여 스프링에서 제공하는 ContextLoaderListener 클래스를 등록하면 된다.
중요한 것은 ContextLoaderListener 클래스는 서블릿 컨테이너가 web.xml 파일을 읽어서 구동될 대, 자동으로 메모리에 생성된다. 즉, ContextLoaderListener는 클라이언트의 요청이 없어도 컨테이너가 구동될 때 Pre-Loading 되는 객체이다. web.xml 파일에 ContextLoaderListener 클래스를 등록한다.</p>
<p>서버를 재구동하여 서블릿 컨테이너가 ContextLoaderListener 객체를 생성하는지 확인해보면 된다. 서버를 재구동하면 FileNotFoundException이 발생하는데, 이는 ContextLoaderListener가  기본적으로 WEB-INF/applicationContext.xml 파일을 읽어 스프링 컨테이너를 구동하기 때문이다.
따라서 src/main/resources 소스 폴더에 있는 applicationContext.xml 파일을 WEB-INF 폴더에 복사하면 ContextLoaderListener는 해당 XML파일을 읽어 스프링 컨테이너를 정상적으로 구동할 수 있다.
그런데 이렇게 하면 비즈니스 레이어에 해당하는 스프링 설정 파일이 두곳에 있어서 나중에 관리상 문제가 발생할 수 있다.</p>

~~~
src/main/resources/applicationContext.xml
/WEB-INF/applicationContext.xml
~~~

<p>두곳에 있는 XML 설정 파일중 어떤 파일을 사용해야 할까? 일단은 유지보수 과정에서 비즈니스 컴포넌트를 수정하고 테스트를 진행하기 위해서라도 src/main/resources 소스폴더에 xml파일을 위치시키는 것이 맞을 것이다. 그래야만 src/test/java 소스 폴더에 테스트 클라이언트 프로그램도 실행할 수 있기 때문이다.</p>
<p>그렇다면 src/main/resources 소스 폴더에 있는 스프링 설정 파일을 어떻게 ContextLoaderListener가 읽어들일 수 있을까?. 다음과 같이 web.xml 파일에 context-param 설정을 추가해야 한다.</p>
<p>ContextLoaderListener 객체는 context-param으로 등록된 contextConfigLocation파라미터 정보를 읽어 스프링 컨테이너를 구동하도록 프로그램 되어 있다. 서버를 재구동해보면 ContextLoaderListener 객체가 Pre-Loading 되어 스프링 컨테이너를 먼저 구동하고 이때, 비즈니스 컴포넌트 객체들이 생성되는 것을 확인할 수 있다.</p>
<p>아직 DispatcherServlet은 생성되지 않은 상태로, 아이디와 비밀번호를 입력하고 로그인 버튼을 클릭하면 최초의 .do 요청에 대해서 DispatcherServlet 객체가 생성된다. 실행 확인 후 WEB-INF 폴더의 applicationContext.xml 파일은 삭제한다.</p>

<H3>3.2.3 스프링 컨테이너의 관계</H3>
<p>총 세개의 컨테이너가 구동되는 것을 확인할 수 있다.</p>

![29](https://user-images.githubusercontent.com/65153512/119218655-72892100-bb1c-11eb-9c11-5a1dee536334.jpg)

<p>톰캣 서버를 처음 구동하면 ①web.xml 파일을 로딩하여 서블릿 컨테이너가 구동된다. 그리고 ②서블릿 컨테이너는 web.xml파일에 등록된 ContextLoaderListener 객체를 생성(Pre-Loading)한다. 이때 ContextLoaderListener 객체는 src/main/resources 소스 폴더에 있는 applicationContext.xml 파일을 로딩하여 스프링 컨테이너를 구동하는데 이를 <u>Root 컨테이너</u> 라고 한다</p>
<p>이때 Service 구현 클래스나 DAO 객체들이 메모리에 생성된다. 그리고 사용자가 로그인 버튼을 클릭하여 .do 요청을 서버에 전달하면 서블릿 컨테이너는 DispatcherServlet 객체를 생성하고 ③ DispatcherServlet 객체는 WEB-INF/config 폴더에 있는 presentation-layer.xml 파일을 로딩하여 두 번째 스프링 컨테이너를 구동한다. 이 두번째 스프링 컨테이너가 Controller 객체를 메모리에 생성한다.</p>
<p>분명히 스프링 컨테이너는 두 개가 구동된다. 즉, ContextLoaderListener와 DispatcherServlet이 각각 XmlWebApplicationContext를 생성하는데, 이때 두 스프링의 컨테이너의 역할과 기능이 다르다.
우선 ContextLoaderListener가 생성하는 스프링 컨테이너를 Root 컨테이너라고 하며 부모 컨테이너로 생각할 수 있다.</p>
<p>그리고 DispatcherServlet이 생성한 컨테이너는 Root 컨테이너가 생성한 객체를 이용하는 자식 컨테이너가 된다. 따라서 부모 컨테이너가 생성한 비즈니스 객체를 자식 컨테이너가 Controller에서 참조하여 사용할 수 있다.</p>

![30](https://user-images.githubusercontent.com/65153512/119219287-ab76c500-bb1f-11eb-9ba8-955a08afeb1e.jpg)


<H1>4. 검색기능 추가 구현</H1>
<p>지금까지 Spring MVC로 게시판 프로그램을 XML 방식과 어노테이션 방식으로 각각 구현해봤다. 이번에는 지금까지 학습한 내용을 토대로 게시글 검색 기능을 구현해본다.</p>

<H2>4.1 검색 정보 추출</H2>
<H3>4.1.1 검색화면 구성</H3>
<p>검색기능을 구현하기 위한 화면은 이미 getBoardList.jsp 파일에 구현되어 있다. 검색 조건을 선택하고 검색 키워드를 입력한 후에 검색 버튼을 클릭하면 검색결과가 출력되는 형태이다.</p>
<p>검색 화면에서 검색 조건과 검색 키워드에 해당하는 파라미터 이름은 searchCondition과 searchKeyword이다. searchCondition 파라미터 값은 사용자가 검색 조건을 제목으로 선택했으면 TITLE이, 내용으로 선택했으면 CONTENT가 설정된다.
그리고 검색 키워드는 텍스트 필드이므로 사용자가 입력한 값이 직접 파라미터값으로 설정된다. 사용자가 이 두 정보를 적절하게 설정하고 검색 버튼을 클릭하면, 사용자 입력값을 가지고 /getBoardList.do 요청을 서버에 전달한다</p>

<H3>4.1.2 Command 객체 수정</H3>
<p>getBoardList.do 경로 요청이 서버에 전달되면 스프링 컨테이너는 BoardController에 getBoardList 메소드를 실행한다. 이때, 사용자가 입력한 파라미터 값들을 BoardVO 라는 Command 객체에 자동으로 채운다
따라서command 객체로 사용할 BoardVO 클래스에 파라미터에 해당하는 멤버변수와 Getter/Setter 메소드를 추가한다.(이전과정에 이미 추가되어 있다.)</p>
<p></p>

<H2>4.2 Controller 구현</H2>
<p>Command 객체로 사용할 BoardVO 클래스를 수정했으면 이제 비즈니스 컴포넌트를 호출하는 BoardController 클래스를 수정해야 한다. 
클라이언트가 입력한 검색 조건과 검색 키워드 정보는 BoardVO 객체에 자동으로 설정되어 전달된다. 문제는 전달되지 않았을 때이다. </p>
<p>로그인 후 getBoardList.do 요청이 전달되거나 글목록 링크를 클리갛여 getBoardList.do 요청을 서버에 전달하면 검색 조건과 키워드 정보는 전달되지않아 BoardVO 객체의 searchCondition과 searchKeyword 변수에는 null이 설정된다.
따라서 이때는 기본값을 적절하게 설정하여 비즈니스 컴포넌트에 전달해야 하므로 null에대한 체크로직을 추가한다.</p>

<H2>4.3 DAO 클래스 수정</H2>
<p>BoardController 클래스를 수정했으면 이제 실질적으로 데이터베이스 연동 처리를 담당하는 DAO 클래스를 수정하면 된다. 현재 두개의 DAO 클래스가 있으므로 둘 다 수정한다.</p>
<p></p>

<H3>4.3.1 BoardDAO 클래스 수정</H3>
<p>JDBC 기반의 BoardDAO클래스를 수정한다.</p>
<p>BoardDAO 클래스에서 가장 먼저 수정해야할 코드는 SQL 명령어이다. 기존의 글 목록을 조회하는 쿼리는 Board테이블의 모든 게시글을 조회하는 단일 쿼리였다. 따라서 검색 조건 파라미터 값이 TITLE이냐 CONTENT이냐에 따라 다른 쿼리가 동작할 수 있도록 두 개로 나눠야 한다.
제목이나 내용에 검색 키워드가 포함된게시글 목록만 조회할 수 있도록 LIKE 연산자를 이용하여 조건절을 추가해야 한다</p>
<p>두번째는 getBoardList 메소드에 대한 수정이다. 매개변수로 받은 BoardVO 객체에 searchCondition 변수값이 TITLE이냐 CONTENT이냐에 따라 적절한 쿼리문이 실행되도록 분기 처리 로직을 추가해야 한다. 그리고 어떤 쿼리가 실행되든 검색 키워드에 해당하는 ?가 추가되기 때문에 serachKeyword 변숫값을 반드시 해당 물음표에 설정해야 한다.</p>

<H3>4.3.2 BoardDAOSpring 클래스 수정</H3>
<p>Spring JDBC를 이용하여 DB연동을 처리한 BoardDAOSpring 클래스를 수정한다.</p>
<p>기본적인 구성은 BoardDAO 클래스와 같다. 다만 키워드 설정을 위해 Object 배열을 사용하는것이 차이점이 있다.</p>

<H1>5. 파일 업로드</H1>
<H2>5.1 파일 업로드 처리</H2>
<H3>5.1.1 파일 업로드 입력 화면</H3>
<p>게시판 프로그램에 파일 업로드 기능을 추가히기 위해서 글 등록 화면을 수정한다</p>
<p>글 등록 화면에서 파일을 업로드할 수 있게 만들기 위해서 form 태그에 enctype 속성을 추가하고, 속성값을 멀티파트 형식인 multipart/form-data로 지정해야 한다
그리고 file 타입의 input 태그를 추가하여 업로드할 파일을 선택할 수 있도록 화면을 수정한다.</p>
<p>이렇게 insertBoard.jsp 파일을 수정하고 나서 브라우저에서 글 등록 화면을 요청하면 다음 처럼 파일을 업로드할 수 있는 화면이 제공된다. 글 등록 화면에서 찾아보기 버튼을 클릭하면 서버에 업로드할 파일을 선택할 수 있다.</p>

<H3>5.1.2 Command 객체 수정</H3>
<p>파일 업로드를 추가하기 전에는 사용자가 입력한 데이터는 제목, 작성자, 내용뿐이었다. 하지만 이제 업로드할 파일 정보가 추가되었으므로 Command 객체로 사용하는 BoardVO에 파일 업로드와 관련된 변수를 추가해야 한다.</p>
<p>BoardVO 클래스 파일을 열어 org.springframework.web.multipart.MultipartFile 타입의 uploadFile 변수를 추가하고 Getter/Setter 메소드를 적절한 위치에 생성해야 한다.</p>

<H3>5.1.3 FileUpload 라이브러리 추가</H3>
<p>Apache에서 제공하는 Common FileUpload 라이브러리를 사용하여 파일 업로드를 처리하려면 먼저 FileUpload 라이브러리를 내려받기 위한 pom.xml 파일을 수정한다.</p>
<p>이후 메이븐이 두개의 라이브러리를 추가했는지 반드시 확인한다.</p>

<H3>5.1.4 MultipartResolver 설정</H3>
<p>스프링 설정 파일에 CommonsMultipartResover를 bean 등록한다.</p>
<p>이 설정에서 매우 중요한 것은 CommonsMultipartResolver 클래스의 아이디와 이름 값이다.
지금까지 특정 클래스를 스프링 설정 파일에 bean 등록 할때 참조할 bean의 이름을 자유롭게 등록했다. 다만 등록된 이름이 유일하다면 상관없었다
CommonsMultipartResolver클래스는 그 이름이 정해져있다. DispatcherServlet이 특정 이름으로 등록된 CommonsMultipartResolver 객체만 인식하도록 프로그램 되어있다. 
</p>
<p>따라서 반드시 CommonsMultipartResolver 의 아이디 값은 multipartResolver를 사용해야 한다. 이후에 스프링 설정 파일에 등록되는 클래스 중 이름이 Resolver로 끝나는 클래스들은 대부분 아이디가 정해져있다.</p>

<br />
<p>CommonsMultipartResolver 등록에서 maxUploadSize에 대한 settter 인젝션은 업로드할 수 있는 파일의 크기를 제한하기 위한 설정이다.
maxUploadSize를 지정하지 않으면 기본으로 -1이 설정되는 데 무제한이라는 뜻이다</p>
<p>스프링을 이용한 파일 업로드 처리에서 CommonsMultipartResolver의 역할은 굉장히 중요하다. 클라이언트가 글 등록 화면에서 제목, 작성자, 내용, 파일 업로드 정보를 입력하고 글 등록 요청을 서버에 전송하면, CommonsMultipartResolver가 어떤 역할을 하는지 아래 그림에서 확인할 수 있다.</p>

![31](https://user-images.githubusercontent.com/65153512/119224771-04a02200-bb3b-11eb-9cb7-aa6a91d47a5a.jpg)

<p>여기서 중요한 것이 setUploadFile 메소드다. 다른 Setter 메소드들은 문자열이나 기본형 데이터를 매개변수로 받아 값을 할당하는데 setUploadFile 메소드가 호출되려면 MultipartFile타입의 객체가 먼저 생성되어 있어야 한다. 스프링 컨테이너가 이 객체를 생성해준다.</p>
<p>스프링 컨테이너가 insertBoard.do 요청에 대해서 insertBoard 메소드도 호출해주며, BoardVO 객체도 생성하여 전달한다. 이때 MultipartFile 객체를 생성하고 할당하는 일 역시 스프링 컨테이너의 역할이다. 그런데 multipartResolver라는 이름으로 등록된 CommonsMultipartResolver 객체가 없으면 스프링 컨테이너는 MultipartFile 객체를 생성할 수 없다.</p>

<p>그러면 MultipartFile 객체는 어떤 객체이며 어떻게 사용하는 것일까? MultipartFile 객체에는 클라이언트가 업로드한 파일에 대한 모든 정보가 저장된다고 보면 된다. 따라서 해당 객체만 가지고 있다면 원하는 위치에 해당 파일을 업로드할 수 있다.</p>

메소드|설명
----|----
String getOriginalFilename()|업로드한 파일명을 문자열로 리턴
void transferTo(File destFile)|업로드한 파일을 destFile에 저장
boolean isEmpty()|업로드한 파일 존재 여부 리턴(없으면 true 리턴)

<H3>5.1.5 파일 업로드 처리</H3>
<p>마지막으로 BoardController 클래스의 insertBoard메소드에 파일 업로드와 관련된 코드를 추가한다.
MultipartFile 객체가 제공하는 세개의 메소드만 이용하면 간단히 처리할 수 있다.</p>

<H2>5.2 예외 처리</H2>
<p>클라이언트의 요청에 따라 Controller의 메소드가 실행되다 보면 예기치 못한 예외가 발생할 수 있다. 예외가 발생했을 때 적절한 메시지가 담긴 화면을 보여줘야지 500에러 화면을 전송하는 것은 문제가 있다.</p>
<p>예를 들어 클라이언트가 로그인 요청을 전송할 때 아이디를 입력하지 않으면, IllegalArgumentException이 발생한다고 가정하고 LoginController를 수정한다. 로그인 화면에서 아이디 입력 없이 요청을 서버에 전송하면 예외화면이 클라이언트에 제공된다.</p>
<p>하지만 일반 사용자에게 이런 메시지의 의미는 이해되기 어려우며 예외를 내버려 두어서는 안된다. 스프링에서는 이런 예외를 처리하기 위해 XML 설정과 어노테이션 설정 두 가지 방법을 제공한다.</p>

<H3>5.2.1 어노테이션 기반의 예외 처리</H3>
<p>스프링에서는 @ControllerAdvice와 @ExceptionHandler 어노테이션을 이용하여 컨트롤의 메소드 수행 중 발생하는 예외를 총괄적으로 처리할 수 있다.
먼저 presentation-layer.xml 파일에 예외 처리 관련 어노테이션 사용을 위한 설정을 추가한다.</p>
<p>스프링 설정 파일에 mvc네임 스페이스를 추가하고 mvc:annotation-driven 엘리먼트를 설정해야한다. 그래야 다음에 작성할 예외 처리 클래스에서 예외 처리 메소드 위에 @ExceptionHandler 어노테이션을 사용할 수 있다.
이제 발생하는 예외의 종류에 따라 적절한 예외 화면이 서비스되도록 예외 처리 클래스를 작성한다.</p>
<p>클래스 위에 선언된 @ControllerAdivce("com.springbook.view") 어노테이션에 의해 CommonExceptionHandler 객체는 자동으로 생성된다. 그리고 "com.springbook.view" 패키지로 시작하는 컨트롤러에서 예외가 발생하는 순간 @ExceptionHandler 어노테이션으로 지정한 메소드가 실행된다. 이때, 어떤 예외가 발생했느냐에 따라 다른 메소드가 수행된다.</p>
<p>만약 ArithmeticException이 발생하면 handleArithmeticException() 메소드가 실행되고, NullPointerException이 발생하면 handleNullPointerExcetpion 메소드가, 그리고 전혀 다른 예외가 발생하면 handleException 메소드가 기본으로 실행된다.
마지막으로 사용자에게 전송할 예외 관련 화면을 만든다. 모든 예외에대해 무조건 실행되는 기본 예외화면을 만든다. 이후 파일을 복사하여 2개의 에러 파일을 추가한다.</p>
<p>로그인 화면에서 아이디 없이 로그인 요청을 서버에 전달하면, IllegalArgumentException에 대응하는 기본 에러 화면이 브라우저에 출력된다.</p>

<H3>5.2.2 XML 기반의 예외 처리</H3>
<p>스프링은 예외 처리를 어노테이션이 아닌 XML 설정 방식으로도 지원하는데 이 방법이 조금 더 쉬운 방법이라고 볼 수 있다.
앞에서 처리한 어노테이션 기반의 설정과 개념은 같지만 CommonExceptionHandler 처럼 예외 처리 클래스를 별도로 구현하지 않아도 되며, 단지 XML 설정만 처리하면 된다.</p>
<p>presentation-layer.xml 파일에 SimpleMappingExceptionResolver 클래스를 bean 등록 하기만 하면 된다. 그리고 이전에 어노테이션 기반의 에외 처리에 추가했던 설정은 모두 제거한다.</p>
<p>위 설정은 ArithmeticException이 발생하면 arithmeticError.jsp 화면이 사용자 브라우저에 전송되고, NullPointerException이 발생하면 nullPointerError.jsp 화면이, 등록되지 않은 IllegalArgumentException이 발생하면 defaultErrorView로 설정한 error.jsp 화면이 저송된다. </p>

<H1>6. 다국어 처리</H1>
<p>다국어 지원은 국제화라고도 하며 하나의 JSP 페이지를 다양한 언어로 서비스 하는 것을 의미한다. 프레임워크에서 다국어를 지원하기 전에는 언어별로 JSP 파일을 만들어야 했기 때문에 매우 불편했다.
프레임워크의 다국어 기능을 이용하면 JSP 화면에 출력되는 메시지 외에도 예외나 로그 메시지 까지 다국어로 처리할 수 있어서 매우 편하다.</p>

<H2>6.1 메시지 파일 작성하기</H2>
<p>사용자가 원하는 언어로 메시지를 출력하려면 각 언어에 따른 메시지 파일을 작성해야 한다. 기본적으로 메시지 파일의 확장자는 .properties이며 파일명은 언어에 해당하는 Locale 정보를 결합하여 작성한다</p>
<p>영어와 한글 두개의 언어를 지원하기 위해 두 개의 메시지 파일을 생성한다.</p>

<H3>6.1.1 영어 메시지 파일 작성</H3>
<p>Properties 파일을 만들 때는 일반 File로 만든다.message 패키지에 만들어진 messageSource_en.properties 파일에 영어 메시지를 프로퍼티로 등록한다.</p>

<H3>6.1.2 한글 메시지 파일 작성</H3>
<p>메시지 파일을 만들 때 프로퍼티 값을 영어로 등록하는 것은 상관 없지만, 한국어를 포함한 아시아권 언어를 사용할 때는 반드시 유니코드로 변환하여 등록해야 한다.
messageSource_ko.txt파일을 만들어 한글 메시지를 등록한다.</p>
<p>메모장에 복사한뒤 messageSource_ko.properties에 붙여넣기하면 간단하게 만들 수 있다.</p>

<H3></H3>
<p></p>
<p></p>

<H3></H3>
<p></p>
<p></p>