# Spring_Quick_Start
<p>SPRING QUICK START (저.채규태, 출.PUBY PAPER)</p>

<img src="http://image.yes24.com/momo/TopCate853/MidCate001/85204414.jpg">

<p>위의 책을 보며 공부한 것을 정리(3/5)</p>

<H1>1. Model 1 아키텍처로 게시판 개발</H1>
<H2>1.1 Model 1 아키텍처 구조</H2>
<p>90년대 말부터 2000년대 초까지 자바 기반의 웹 애플리케이션 개발에 사용됐던 아키텍쳐는 Model1이다. 
Model1 아키텍처는 JSP와 JavaBeans만 사용항 웹을 개발하는 것으로 다음 그림과 같은 구조이다.</p>

![11](https://user-images.githubusercontent.com/65153512/117946520-58945500-b34a-11eb-9a5f-f4f3046329f6.jpg)

<p>model1 아키텍처에서 우선 살펴볼 것이 model 기능의 JavaBeans다 자바에서 bean이라는 용어는 객체를 의미하므로 JavaBean하면 자바 객체를 의미한다. 결국, JavaBeans는 데이터베이스 연동에 사용되는 자바 객체들이다.
원래 model의 정확한 의미는 데이터베이스 연동 로직을 제공하면서 DB에서 검색한 데이터가 저장되는 자바 객체다. 스프링 IoC와 AOP 실습에서 VO와 DAO클래스를 사용했는데 ,VO,DAO 클래스가 Model 기능의 자바 객체다.</p>

<p>Model1아키텍처에서는 JSP파일이 가장 중요한 역할을 수행하는데, 이는 JSP가 Controller와 View기능을 모두 처리하기 때문이다. 먼저 Controller기능은  JSP 파일에 작성도니 자바 코드를 의미한다. 하지만 JSP에 작성된 모든 자바코드를 Controller라고 하지는 않는다.
일반적으로 Controller는 사용자의 요청 처리와 관련된 자바 코드를 의미하며 대부분 다음과 같은 코드들로 구성된다.</p>

기능|사용 예
----|----
사용자입력정보 추출|String id = request.getParameter("userId")
DB연동관리|UserVO vo = new UserVO();
DB연동관리|vo.setID(id);
DB연동관리|UserDAO userDAO = new UserDAO();
DB연동관리|UserVO user = UserDAO.getUser(vo);
화면 내비게이션|if(user != null){//로그인성공
화면 내비게이션|	response.sendRedirect("getBoardList.jsp");
화면 내비게이션|} else { //로그인실패
화면 내비게이션|	response.sendRedirect("login.jsp");}

<p>JSP에서는 model을 사용하여 검색한 데이터를 사용자가 원하는 화면으로 제공하기 위해 다양한 마크업을 사용한다. 대표적인 언어가 바로 HTML과 CSS이며, 이 두가지 마크업이 View 기능을 담당한다.</p>
<p>Model1 구조는 JSP파일에서 Controller 기능과 View 기능을 모두 처리한다는 특징이 있다. 결과적으로 JSP파일에 자바 코드와 마크업 관련 코드들이 뒤섞여 있어서 역할 구분이 명확하지 않고, JSP파일에 대한 디버깅과 유지보수에 많은 어려움이 생긴다.
따라서 Model1 구조는 적은 개발인력으로 간단한 프로젝트를 수행할때라면 사용할 수 있지만, 엔터프라이즈급의 복잡한 시스템에는 부적합하다.</p>

<p>그래서 등장한 것이 Model2 즉, MVC(Model View Controller) 아키텍처 이다.Model2를 일반적으로 MVC라고 부르는데, 이는 Model,View,Controller 요소로 기능을 분리하여 개발하기 때문이다.
결국 MVC는 Model1 구조의 단점을 보완하기 위해 만들어진 구조라고 생각할 수 있다.</p>

<p>Model2 구현에 앞서 Model1 아키텍쳐로 게시판 프로그램을 개발해본다.</p>

<H2>1.2 로그인 기능 구현</H2>
<H3>1.2.1 로그인 화면</H3>
<p>게시판 사용자는 로그인을 성공해야 게시판 목록 화면을 볼 수 있다. 따라서 가장 먼저 로그인 기능을 개발한다. 우선 사용자에게 로그인 화면을 제공하기 위해 login.jsp 파일을 만든다.</p>
<p>앞으로 작성하는 모든 jsp파일은 src/main/webapp 폴더에 등록한다. 이후 login.jsp 파일에 HTML 태그를 이용하여 로그인 화면을 구성한다(코드)</p>
<p>사용자가 id와 password 파라미터에 해당하는 값을 입력하고, submit 타입의 로그인 버튼을 클릭하면 id와 password 파라미터 정보를 가지고 'login_proc.jsp' 파일을 호출할 것이다.</p>
<p>작성된 login.jsp 파일을 실행하면 서버가 구동되고 브라우저에 화면이 출력된다.</p>

<H3>1.2.2 로그인 인증 처리</H3>
<p>어제 사용자가 입력한 아이디 비밀번호를 추출하여 로그인을 처리하는 login_proc.jsp 파일을 작성한다.</p>
<p>사용자가 입력한 아이디와 비밀번호를 request 객체로부터 추출한다. 그리고 Model에 해당하는 UserVO와 UserDAO 객체를 이용하여 사용자 정보를 검색한다. 그리고 검색 결과로 UserVO객체가 리턴되면 로그인 성공이고, null이 리턴되면 로그인 실패로처리한다.</p>
<p>USERS 테이블에 등록된 계정으로 로그인에 성공하면 글 목록 화면(getBoardList.jsp)으로 이동하고, 실패하면 다시 로그인하도록 로그인화면(login.jsp)으로 이동한다. 화면 내비게이션 방법으로는 포워드방식과 리다이렉트 방식 두가지가 있다. 단순화를 위해 우선 리다이렉트 방식만 사용한다.</p>

![12](https://user-images.githubusercontent.com/65153512/117961217-d4959980-b358-11eb-9388-6bfbe6ac7c59.jpg)

<H2>1.3 글 목록 검색 기능 구현</H2>
<p>로그인에 성공한 다음 글 목록 화면으로 이동한다. BOARD 테이블에서 게시글을 검색하여 글 목록 화면을 구성하는 .getBoardList.jsp 파일을 작성한다.</p>
<p>getBoardList.jsp 파일에는 사용자가 입력한 검색 관련 정보를 추출해야 하는데, 검색 기능은 나중에 추가한다. 곧바로 BoardVO와 BoardDAO 객체를 이용하여 BOARD테이블에 저장된 게시글 목록을 검색한다. 그리고 검색 결과로 얻은 List<BoardVO> 객체를 이용하여 게시글 목록 화면을 구성한다.</p>
<p>앞의 소스에서 게시글 제목에 하이퍼링크를 설정했는데, 사용자가 게시글 제목을 선택했을 때 해당 글의 상세 정보를 조회하여 출력하기 위해서 getBoard.jsp 파일로 링크를 연결했다. 이때, 사용자가 클릭한 게시글 번호를 넘겨주고자 getBoard.jsp 파일 뒤에 ?를 추가하고 쿼리 문자열 정보를 넘겼다.</p>

~~~
<td align="left"><a href="getBoard.jsp?seq=<%= board.getSeq() %>">
~~~
<p>이제 로그인에 성공하면 글 목록화면으로, 실패시 로그인 화면으로 리다이렉트 된다.</p>

<H2>1.4 글 상세 기능 구현</H2>
<p>글 목록 화면에서 사용자가 클릭한 게시글을 조회하고, 조회된 게시글의 상세 화면을 제공하는 getBoard.jsp 파일을 작성한다.</p>
<p>getBoard.jsp 파일은 가장 먼저 글 목록 화면에서 사용자가 클릭한 게시글 번호를 추출한다. 그리고 BoardDAO 객체의 getBoard() 메소드를 이용하여 이 게시글 번호에 해당하는 BoardVO 객체를 검색한다. 마지막으로 검색된 BoardVO 객체의 값들을 화면에 출력한다.</p>
<p>상세 화면은 수정을 위한 화면이기도 하다. 따라서 제목과 내용을 수정하고 글 수정 버튼을 클릭하면 글 수정 처리가 되어야 한다. </p>

<H2>1.5 글 등록 기능 구현</H2>
<H3>1.5.1 글 등록 화면</H3>
<p>글 등록 기능은 먼저 insertBoard.jsp 파일을 생성하여 글 등록 화면을 구성한다.</p>
<p>글등록 화면은 로그인화면과 유사하며 사용자가 title,writer, content파라미터 정보를 입력하고 등록버튼을 클릭하면 insertBoard_proc.jsp 파일을 호출한다.</p>

<H3>1.5.2 글 등록 처리</H3>
<p>사용자가 입력한 데디터를 데이터베이스의 BOARD 테이블에 저장하는 insertBoard_proc.jsp 파일을 다음과 같이 작성한다.</p>
<p>글 등록 처리 로직에서 가장 먼저 작성한것은 인코딩 관련 코드이다. 사용자 입력 데이터에 한글이 포함되어있다면, 입력 정보를 getParameter 메소드로 추출했을 때 한글이 깨지므로 setCharacterEncoding 메소드로 한글 인코딩을 처리해야 한다</p>

<p>인코딩 처리된 입력값들을 추출하고 나면 BoardVO 객체를 생성하여 추출된 값들을 적절히 세팅한다. 그리고 BoardDAO의 insertBoard() 메소드를 호출할 때 인자로 전달하면 DB연동처리가 마무리된다.</p>

<H2>1.6 글 수정 기능 구현</H2>
<p>글 수정을 위한 별도의 화면은 제공하지 않으며, 앞에서 작성한 글 상세 화면(getBoard.jsp)이 글 수정에 사용된다. 글 수정을 위해서는 제목, 내용 뿐만 아니라 게시글 번호도 알아야한다. 따라서 상세 화면을 출력할 때 form태그 밑에 hidden타입의 input 태그를 추가하여 수정할 게시글 번호도 전달되도록 수정한다.</p>
<p>사용자가 수정버튼을 누르면 title,content파라미터와 함께 hidden 으로 설정한 게시글 번호를 가지고 updateboard_proc.jsp 파일을 호출한다.</p>
<p>글 등록과 마찬가지로 인코딩처리를 한다. </p>

<H2>1.7 글 삭제 기능 구현</H2>
<p>사용자가 상세 화면에서 글 삭제 링크를 클릭하면 deleteBoard_proc.jsp 파일에서 해당 게시글을 삭제처리 한다.삭제할 게시글 번호를 deleteBoard_proc.jsp파일에 전송해야 하므로 뒤에 seq파라미터 정보를 추가한다.</p>
<p>deleteBoard_proc.jsp 파일에서는 삭제요청된 게시글 번호를 추출하여 BoardVO객체에 저장한다. 이때 getParameter메소드는 문자열로 리턴하므로 integer클래스를 이용하여 int로 변환한다.</p>

<H2>1.8 로그아웃 기능 구현</H2>
<p>로그아웃은 세션과 관련된 작업을 처리해야 하는데 이번 실습에서는 로그아웃 요청의 세션을 종료하고 메인화면으로 돌아가는 것만을 구현한다.</p>
<p>현재 작업중인 프로젝트에서 메인 화면은 별도로 생성하지 않았으므로 세션을 강제로 종료하고 로그인 화면으로 돌아가는 것으로 마무리 하였다.</p>

<H1>3. Model2 아키텍처로 게시판 개발</H1>
<H3>3.1 Model2 아키텍처 구조</H3>
<p>Model 1아키텍처가 엔터프라이즈 시스템에 적합하지 않은 이유는 자바 로직과 화면 디자인이 통합되어 유지보수가 어렵기 때문이다. 자바 갭라자 입장에서 JSP 파일에 자바 로직과 화면 디자인이 통합되어 있으면, 우선 수정할 자바 로지긍ㄹ 찾기부터가 쉽지 않다.
그리고 디자이너가 디자인을 변경할 때도 복잡한 자바 코드들 땜누에 어려움을 느낄 수 있다.</p>
<p>이런 Model1 아키텍처의 문제를 해결하기 위해 고안된 웹 개발 모델이 Model2 아키텍처, MVC 아키텍처이다. 가장 중요한 특징은 Controller의 등장이며, Controller는 서블릿 클래스를 중심으로 구현한다.</p>

![13](https://user-images.githubusercontent.com/65153512/118083807-8e911200-b3fa-11eb-9476-aa2d951cb31b.jpg)

<p>Model2 아키텍처에서는 기존에 JSP가 담당했던 Controller 로직이 별도의 Controller 기능의 서블릿으로 옮겨졌다. 따라서 기존의 Model1 아키텍처로 개발한 프로그램에서 JSP파일에 있는 자바 코드만 Controller로 이동하면 Model 2 아키텍처가 된다.
결과적으로 Controller 로직이 사라진 JSP에는 View와 관련된 디자인만 남게 되어 디자이너는 JSP파일을, 자바 개발자는 Controller와 Model만 관리하면 된다.</p>

<p>MVC 아키텍처에서 각 요소의 기능과 개발 주체를 정리한 표</p>

기능|구성요소|개발주체
----|----|----
Model|VO,DAO 클래스|자바 개발자
View|JSP 페이지|웹 디자이너
Controller| Servlet 클래스|자바 개발자 또는 MVC 프레임워크

<H4>기타</H4>
<p>서블릿(servlet)은 서버에서 웹페이지 등을 동적으로 생성하거나 데이터 처리를 수행하기 위해 자바로 작성된 프로그램이다.</p>
<p>JSP가 HTML문서안에 자바 코드를 품고 있다면, 서블릿은 자바코드안에 HTML을 포함한다.</p>

<br />

<p>Controller는 직접 구현해도 되지만, MVC 프레임워크가 제공하는 Controller를 사용할 수 있으며, 더 효율적이고 안정정깅다. 문제는 프레임워크에서 제공하는 
controller 구조가 복잡하고 어렵다. 이번 실습은 controller 적용보다는 기능 이해에 중점을 둔다.</p>

<H2>3.2 Controller 구현하기</H2>
<H3>3.2.1 서블릿 생성 및 등록</H3>
<p>Controller 기능을 수행하는 서블릿 클래스를 하나 추가하여 기존의 Model1 기반으로 개발된 게시판 프로그램을 MVC 아키텍처로 변경하자. Controller에 해당하는 서블릿 클래스를 구현할 때 이클립스의 기능을 이용하면 좀 더 쉽게 작성할 수 있다.</p>
<p>DispatcherServlet 클래스가 만들어지는 순간 WEB-INF/web.xml 파일에 서블릿 관련 설정이 자동으로 추가된다. 이 설정에서 description과 display-name은 의미 없는 설정이므로 삭제한다.</p>
<p>(책 참조p.266) 현 설정은 클라이언트의 모든 *.do요청을 DispatcherServlet 클래스의 객체가 처리한다는 설정이다. 확장자 do는 언제든지 다른 이름으로 변경할 수 있다.</p>

<H3>3.2.2 Controller 서블릿 구현</H3>
<p>서블릿 클래스가 만들어질 때 자동으로 추가되는 주석들은 모두 제거하고DispatcherServlet 클래스가 Controller 기능을 수행하도록 구현한다.</p>
<p>DispatcherServlet 에는 GET방식을 처리하는 doGet메소드와 POST방식을 처리하는 doPost 메소드가 재정의 되어있다. 하지만 어떤 방식으로 요청하든 process 메소드를 통해 클라이언트의 요청을 처리한다.</p>
<p>POST 방식의 요청에 대해서 doPost 메소드가 호출되는데 이때 한글이 깨지지 않도록 인코딩처리를 추가한다. 이제 글 등록, 수정 작업에서 한글 데이터가 깨지는 일은 발생하지 않는다. 인코딩작업을 DispatcherServlet 에서 처리하므로 인코딩을 변경시 해당 파일만 수정하면 된다.</p>
<p>DispatcherServlet 에서 가장 중요한 process메소드에서는 가장 먼저 클라이언트의 요청 uri로부터 path정보를 추출하고 있는데, 추출된 path는 uri문자열에서 마지막 /xxx.do 문자열이다. 그리고 추출된 path문자열에 따라 복잡한 분기 처리 로직이 실행된다.</p>
<p>기존의 JSP파일에서 각 분기처리 로직을 추출하여 DispatcherServlet에 추가하고 기능을 하나씩 구현하면 된다.</p>

<H2>3.3 로그인 기능 구현하기</H2>
<p>로그인 기능을 MVC로 변환하려면 login.jsp 파일의 form엘리먼트의 action속성값을 login.do로 수정한다. *.do 형태의 요청에 대해서만 DispatcherServlet이 동작하기 때문이다.</p>
<p>그리고 login_proc.jsp 파일에 있는 모든 자바 로직을 복사하여 DispatcherServlet에 추가한다.</p>

<H2>3.4 글 목록 검색 기능 구현하기</H2>
<p>기존에 글 목록 화면을 처리했던 getBoardList.jsp 파일에서 Controller 로직에 해당하는 자바 코드를 DispatcherServlet으로 복사한다.</p>
<p>(코드) 이 소스는 리다이렉트 되는 getBoardList.jsp 화면에서 검색결과를 출력하기 위해 세션 객체를 사용했다. 사실 검색 결과를 JSP에 공유하기 위해 세션에 저장하는 것은 문제가 있다.
세션은 브라우저당 서버 메모리에 하나씩 유지되는 객체이므로 사용자가 많을수록 세션이 많아지고, 세션에 정보가 많을 수록 서버에 부담이 된다.</p>
<p>따라서 검색 결과는 세션이 아닌 HttpSerlvetRequest 객체에 저장하여 공유해야 한다. 클라이언트가 서버에 요청을 전송할 때 마다 새롭게 생성되며, 응답메세지가 브라우저에 전송되면 삭제되는 1회성 객체 이므로 공유할 데이터를 HttpServletRequest에 저장하면 
서버는 부담이 되지 않는다. 한동안은 간결함을 유지하기 위해 세션을 사용하며 후에 Spring MVC 로 전환하면서 HttpSession을 HttpServletRequest로 수정한다.</p>
<p>이제 getBoardList.jsp파일은 글 목록을 검색하는 코드 대신 세션에 세선에 저장된 글 목록을 꺼내서 출력하도록 수정한다.</p>
<p>getBoardList.jsp파일은 직접 db연동을 처리하지 않고 자바코드는 Controller인 DispatcherServlet 클래스로 이동했다. 단지 세션에 저장된 글 목록을 불러와 출력하는 기능만을한다.</p>

<H2>3.5 글 상세 보기 기능 구현하기</H2>
<p>getBoardList.jsp의 하이퍼링크를 수정한다. 기존에는 getBoard.jsp로 바로 깅크를 걸었다면 이제는 게시글의 상세 정보를 검색할 수 있도록 getBoard.do 로 링크를 수정해야 한다.
getBoard.jsp에 있던 코드를 DispatcherServlet 클래스에 /getBoard.do 분기처리에 복사한다.</p>
<p>글 상세 조회는 글 목록 검색 기능과비슷하다. 검색결과를 공유하기 위해 세션에저장하고, getBoard.jsp파일을 리다이렉트한다.</p>

<H2>3.6 글 등록 기능 구현하기</H2>
<p>insertBoard.jsp 파일을 수정한다. form태그의 액션값을 insertBoard.do로 수정한다.그 후 insertBoard_proc.jsp 파일의 자바코드를 DispatcherServlet으로 복사한다.</p>
<p>이때 주의할점은 리다이렉션을 getBoardList.do 로해야 등록 된 내용이 세션에 저장되어 정상적으로 출력된다. 이는 삭제, 수정에도 마찬가지이다.</p>

<H2>3.7 글 수정 기능 구현하기</H2>
<p>getBoard.jsp에서 form 태그의 action을 updateBoard.do 로 수정한다.</p>
<p>이전과 마찬가지로 코드를 복사한뒤 리다이렉션코드를 수정한다.</p>

<H2>3.8 글 삭제 기능 구현하기</H2>
<p>getBoard.jsp 에서 글 삭제 관련 링크를 deleteBoard.do로 수정한다.</p>
<p>이전과 마찬가지로 코드를 복사한뒤 리다이렉션코드를 수정한다.</p>

<H2>3.9 로그아웃 기능 구현하기</H2>
<p>모든 페이지의 logout_proc.jsp링크를 logout.do로 수정한다. 그 후 기존의 코드를 복사하여 옮긴다.</p>
<p>이제 모든 기능이 MVC구조로 수정되었다. 먼저 Model 기능의 VO,DAO클래스는 재사용되었고, DispatcherServlet이라는 Controller기능의 서블릿 클래스가 추가되었다.
가장 큰변화는 view기능의 jsp파일로 controller기능의 자바로직을 DispatcherServlet으로 이동했다. 따라서 proc파일들은 삭제해도 된다.</p>
<p>Controller로직은 사용지 입력 정보 추출, Model을 이용한  DB연동 처리, 화면 내비게이션에 해당하는 자바코드를 의미한다.</p>

<H2>4. MVC프레임워크 개발</H2>
<H2>4.1 MVC프레임워크 구조</H2>
<p>지금까지 개발한 게시판 프로그램은 MVC 아키텍처를 적용하긴 했지만, DispathcerServlet 클래스 하나로 Controller 기능을 구현했다. 하지만 이렇게 하나의 서블릿으로 
Controller를 구현하면 클라이언트의 모든 요청을 하나의 서블릿이 처리하게 된다. 따라서 수많은 분기 처리 로직을 가질 수 밖에 없고, 이는 오히려 개발과 유지보수를 어렵게 만든다.</p>
<p>controller를 서블릿 클래스 하나로 구현하는 것은 여러 측면에서 문제가 있으며, 다양한 디자인 패턴을 결합하여 개발과 유지보수의 편의성이 보장되도록 잘 만들어야 한다.
하지만 프레임워크에서 제공하는 controller를 사용하면 우리가 직접 구현하지 않아도 된다. struts나 Spring(MVC) 같은 MVC 프레임워크를 사용하는 이유는 바로 이런 프레임 워크들이 효율적인 Controller를 제공하기 때문이다.</p>
<p>본격적으로 Spring MVC를 적용하기 전  SpringMVC와 동일한 구조의 프레임워크를 직접 구현하여 적용한다. 이 과정을 통해 SpringMVC의 구성요소와 동작원리를 더욱 쉽게 이해할 수 있다.</p>

![14](https://user-images.githubusercontent.com/65153512/118096418-4bd83580-b40c-11eb-8cb8-016bfb5920a3.jpg)

<p>이 그림은 우리가 개발할 MVC프레임워크의 구조를 표현한 것이다.</p>

클래스|기능
----|----
DispatcherServlet|유일한 서블릿 클래스로서 모든 클라이언트의 요청을 가장 먼저 처리하는 Front Controller
HandlerMapping|클라이언트의 요청을 처리할 Controller 매핑
Controller|실질적인 클라이언트의 요청 처리
ViewResolver|Controller가 리턴한 View 이름으로 실행될 JSP경로 완성

<p>로그인 기능을 구현하면서 각 구성 요소들을 만들어본다.</p>

<H2>4.2 MVC 프레임워크 구현</H2>
<H3>4.2.1 Contorller 인터페이스 작성</H3>
<p>Controller를 구성하는 요소 중 DispatcherSerlvet은 클라이언트의 요청을 가장 먼저 받아들이는 Front Controller다. 하지만 클라이언트의 요청을 처리하기 위해 DispatcherServlet이 하는 일은 거이 없으며, 실질적 요청 처리는 각 Controller에서 담당한다.</p>
<p>구체적인 Controller 클래스들을 구현하기에 앞서 모든 Controller를 같은 타입으로 관리하기 위한 인터페이스를 만들어야 한다. 클라이언트의 요청을 받은 DispatcherServlet은  HandlerMapping을 통해 Controller객체를 검색하고, 검색된 Controller를 실행한다.
이때 어떤 Cotroller객체가 검색되더라도 같은 코드를 실행하려면 모든 Controller의 최상위인터페이스가 필요하다.</p>

<H3>4.2.2 LoginController 구현</H3>
<p>컨트롤러 인터페이스를 구현한 LoginController 클래스를 만든다.</p>
<p>로그인 처리 소스는 DispatcherServlet과 같다 하지만 Controller인터페이스의 handleRequest를 재정의 했으므로 처리 기능 마지막은 리다이렉트가아닌 리턴한다.</p>
<p>로그인에 실패했을 때 login.jsp가 아닌이유는 나중에 추가할 viewResolver 클래스에서 다루는데, 우선 handleRequest 메소드가 확장자 없는 문자열을 리턴하면 자동으로 .jsp가 붙는다는 것만 이해한다.</p>

<H3>4.2.3 HandlerMapping 클래스 작성</H3>
<p>HandlerMapping은 모든 Controller 객체들을 저장하고 있다가, 클라이언트의 요청이 들어오면 요청을 처리할 특정  Controller를 검색하는 기능을 제공한다. HandlerMapping 객체는 DispatcherServlet이 사용하는 객체이다. 따라서DispatcherServlet이 생성되고 init()메소드가 호출될 때 단 한번 생성된다.</p>
<p>Map 타입의 컬렉션을 멤버변수로 가지고 있으면서 게시판 프로그램에 필요한 모든 Controller 객체들을 등록하고 관리한다. getController메소드는 매개변수로 받은 path 에 해당하는 Controller객체를 HashMap컬렉션으로부터 검색하여 리턴한다. 지금은 LoginController객체 하나만 등록되어 있지만, 앞으로 계속 Controller 객체들이 추가될 것이다.</p>

<H3>4.2.4 ViewResolver 클래스 작성</H3>
<p>ViewResolver 클래스는 Controller가 리턴한 View 이름에 접두사(perpix)와 접미사(suffix)를 결합하여 최종적으로 실행될 view경로와 파일명을 완성한다
ViewResolver도 HandlerMapping과 마찬가지로 DispatcherServlet의 init()메소드가 호출될 때 생성된다.</p>
<p>ViewResolver 는 setPrefix와 setSuffix 메소드로 접두사와 접미사를 초기화한다. 그리고 getView 메소드가 호출될 때 viewName 앞에 prefix를 결합하고 viewName뒤에 suffix를 결합하여 리턴한다.</p>

<H3>4.2.5 DispatcherServlet 수정</H3>
<p>DispatcherServlet은 Front Controller 기능의 클래스로서 Controller 구성 요소 중 가장 중요한 역할을 수행한다. 수정하기 전 복사본을 만들어 후에 재사용하도록 한다.</p>
<p>수정된 DispatcherServlet 클래스에는 init 메소드가 정의되어 있다. 서블릿의 init 메소드는 서블릿 객체가 생성된 후에 멤버변수를 초기화하기 위해 자동으로 수행된다.
따라서 init 메소드에서 DispatcherServlet이 사용할 HandlerMapping와 ViewResolver객체를 초기화하고, 이 둘을 이용하여 사용자 요청을 처리 한다.</p>
<p>가장 중요한 process 메소드를 보면 클라이언트의 요청 path정보를 추출하는 추출하는 코드를 제외한 대부분이 수정되었다. 클라이언트의 요청 path에 해당하는 Controller를 검색하기 위해 HandlerMapping 객체의 getController 메소드를 호출한다.
그리고 나서 검색된 controller의 handleRequest 메소드를 호출하여 요청에 해당하는 로직을 처리하고 나면 이동할 화면정보가 리턴된다.
마지막으로 Controller가 리턴한 View이름을 이용하여 실행될 view를 찾아 해당 화면으로 이동한다.</p>

![15](https://user-images.githubusercontent.com/65153512/118104747-7b8c3b00-b416-11eb-9553-f67fd5bf84f2.jpg)

<H2>4.3 MVC 프레임워크 적용</H2>
<H3>4.3.1 글 목록 검색 구현</H3>
<p>Controller 인터페이스를 구현한 GetBoardListController 클래스를 작성한다.</p>
<p>GETBoardListController 객체를 HandlerMapping에 등록한다.</p>

<H3>4.3.2 글 상세 보기 구현</H3>
<p>Controller 인터페이스를 구현한 GetBoardController 클래스를 작성한다.</p>
<p>GetBoardController객체도 HandlerMApping클래스에 추가한다.</p>

<H3>4.3.3 글 등록 구현</H3>
<p>InsertBoardController클래스를 작성한다.</p>
<p>getBoardList.do 문자열을 리턴할때 글 등록에 성공하면 등록된 글이 포함된 글 목록을 다시 검색해야 한다. 따라서 getBoardList.do 문자열을 리턴하여 리다이렉트 처리한다.</p>
<p>InsertBoardController객체도 HandlerMApping클래스에 추가한다.</p>

<H3>4.3.4 글 수정 구현</H3>
<p>UpdateBoardController클래스를 작성한다.</p>
<p>수정 성공 후 글 목록 갱신을 위해 getBoardList.do 를 리턴한다.</p>
<p>UpdateBoardController객체도 HandlerMApping클래스에 추가한다.</p>

<H3>4.3.5,6 글삭제, 로그아웃 구현</H3>
<p>앞의 과정과 동일하다</p>
<p>Controller를 구성하는 클래스를 모두 개발하고나면 너무나 복잡한 구조와 수많은 클래스때문에 더 혼란스러울 수 있는데에도 불구하고 Controller를 사용하는 이유는 Controller에서 가장 중요한 DispatchServlet 클래스는 유지보수 과정에서 기존의 기능을 수정하거나 새로운 기능을 추가하더라도 절대 수정되지 않는다.</p>
<p>예를들어 게시판에 회원가입 기능을 추가한다고 할 때 insertUserConroller 클래스를 작성하고 HandlerMapping에 등록하면 된다. 이 과정에서 dispatcherServlet은 수정할 필요가 없다. 이렇게 기능 추가나 수정에 대해 DispatcherServlet을 수정하지 않도록 해야 프레임 워크에서 DispatcherServlet을 제공할 수 있다.</p>
<p>지금은 직접 클래스를 만들었지만 최종적으로는 스프링 프레임워크에서 제공하는 클래스를 사용해야 한다. 그런데 만약 새로운 기능이 추가될 때 마다 클래스를 수정해야 한다면 스프링 프레임워크에서 클래스를 제공할 수 없다. 결국 스프링 프레임워크에서 제공하는 DispatcherServlet을 사용하려면 새기능이 추가되더라도 DispatcherServlet 클래스의 소스는 변경할 필요가 없도록 개발해야 한다.</p>

<H2>4.4 EL/JSTL 이용한 JSP 화면 처리</H2>
<p>우리가 Model 1아키텍처를 Model2 즉 MVC로 변환했던 이유는 JSP 파일에서 Controller 로직에 해당하는 자바 코드를 제거하기 위함이다. MVC 아키텍처가 적용된
BoardWeb 프로젝트에서 상세 화면과 목록 화면에 해당하는 getBoard.jsp와 getBoardList.jsp파일에 여전히 자바 코드가 남아있다.</p>
<p>사실 Controller 로직은 사용지 입력 정보 추출,DB연동, 화면 내비게이션 같은 자바 코드를 의미하기에 JSP에 남아있는 코드는 Cotroller로직은 아니다. 이 코드조차 제거하고 싶다면 JSP에서 제공하는 EL과 JSTL를 이용하면 된다.</p>

![16](https://user-images.githubusercontent.com/65153512/118114341-6917fe80-b422-11eb-935d-28536bfe8c0d.jpg)

<H3>4.4.1 상세 화면 수정</H3>
<p>가장먼저 게시글의 상세 정보를 출력하는 getBoard.jsp파일에 EL을 적용하여 자바 코드를 제거한다.</p>
<p>가장 눈에 띄는 것은 BoardVO 와 BoardDAO 클래스에 대한 import 선언이 사라진 것이다. 그리고 세션에 저장된 BoardVO객체를 거내는 자바코드도 삭제되었다.
EL 표션식을 이용해 세션에 board라는 이름으로 저장된 BoardVO 객체의 값들을 ${} 구문을 이용하여 출력하고 있다.</p>

<H3>4.4.2 글 목록 화면 수정</H3>
<p>getBoardList.jsp 파일은 EL과 함께 for 루프를 처리하기 위한 JSTL도 같이 사용한다.</p>
<p>getBoard.jsp 파일과 동일하게 import 선언은 모두 삭제되었다. 그리고 JSTL 사용을 위한 taglib 지시문이 추가되었으며
글 목록을 반복해서 출력하기 위해 사용되었던 for 루프는 JSTL에서 제공하는 c:forEach태그로 대체되었다.</p>

<H2>5. Spring MVC 구조</H2>
<H3>5.1 Spring MVC 수행 흐름</H3>

![17](https://user-images.githubusercontent.com/65153512/118118231-9ca95780-b427-11eb-8dfc-8cf0ccc3ab93.jpg)

<p>위 그림은 클라이언트의 요청을 싲가으로 Spring MVC를 구성하는 각요소가 어떻게 동작하는지를 표현하였다.</p>
<p>대부분의 기능이 같으나, Controller의 리턴타입이 String이 아니라 ModelAndView로 바뀐것인데 이는 차차 알보도록 한다.</p>

<H2>5.2 DispatcherServlet 등록 및 스프링 컨테이너 등록</H2>
<H3>5.2.1DispatcherServlet 등록</H3>
<p>Spring MVC 적용에서 가장 먼저 해야할 일은 WEB-INF/web.xml 파일에 등록된 DispatcherServlet클래스를 스프링 프레임워크에서 제공하는 것으로 변경하는 것이다.</p>
<p>크게 달라진 것은 없지만 login.jsp를 실행하고 do요청을 위해 로그인을 하면</p>

~~~
정보: Initializing Spring FrameworkServlet 'action'
INFO : org.springframework.web.servlet.DispatcherServlet - FrameworkServlet 'action': initialization started
~~~
<p>login.do 요청을 서버에 전달하면 서블릿 컨테이너는 web.xml파일에 action이라는 이름으로 등록된 DispatcherSelvet클래스의 객체를 생성한다.</p>

<H3>5.2.2 스프링 컨테이너 구동</H3>
<p>클라이언트의 요청으로 DispatcherServlet 객체가 생성되면 해당 클래스의 init메소드가 수행되어 XmlWebApplicationContext라는 스프링 컨테이너가 구동된다. ApplicationContext를 구현한 클래스 중 하나로 우리가 직접생성 한 것이 아닌 DispatcherServlet이 생성한 것이다.</p>
<p>우리가 직접 DispatcherServlet클래스를 구현했을 때 init메소드에서 HandlerMapping, Controller, ViewResolver 객체들을 생성했다. 다만 스프링에서 제공하는 DispatcherServlet은 스프링 컨테이너를 통해 이 객체들을 생성하는 것이 다를 뿐이다.</p>

![18](https://user-images.githubusercontent.com/65153512/118120396-c7e17600-b42a-11eb-8a81-635141f5953e.jpg)

<p>결국 DispatcherServlet은 클라이언트 요청 처리에 필요한 객체들을 생성하기 위해 스프링 컨테이너를 구동한다. 이 과정은 위 그림과 같다.</p>

<br />
<p>서블릿 컨테이너가 dispacherservlet객체를 생성하고나면 재정의된 init 메소드가 자동으로 실행되고, 스프링설정 파일이 로딩되어 XmlWebApplicationContext를 생성한다. 즉 스프링 컨테이너가 구동된다.
결국 스프링 설정 파일(action-servlet.xml)에 DispatcherServlet이 사용할 세 클래스를 bean등록 하면 스프링 컨테이너가 해당 객체들을 생성해준다.</p>

<H3></H3>
<p></p>
<p></p>