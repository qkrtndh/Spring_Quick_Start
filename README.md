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

<H3></H3>
<p></p>
<p></p>