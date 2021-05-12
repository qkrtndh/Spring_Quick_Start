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

<H3></H3>
<p></p>
<p></p>