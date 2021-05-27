# Spring_Quick_Start
<p>SPRING QUICK START (저.채규태, 출.PUBY PAPER)</p>

<img src="http://image.yes24.com/momo/TopCate853/MidCate001/85204414.jpg">

<p>위의 책을 보며 공부한 것을 정리(5/5)</p>

<H1>1. Mybatis 프레임 워크 시작하기</H1>
<H2>1.1 Mybatis 프레임 워크의 특징</H2>
<p>Mybatis 프레임 워크의 가장 중요한 특징은 한두줄의 자바 코드로 DB연동을 처리하며, SQL명령어를 자바 코드에서 분리하여 XML 파일에 따로 관리한다.
이 두가지가 기존의 우리가 사용하던 JDBC 기반의 DB연동을 어떻게 개선하는지 살펴본다.</p>
<p>BoardDAO 클래스의 글 목록 검색 기능은 JDBC기반으로 구현이 되어있다. JDBCUtil 클래스를 이용하여 DB 커넥션 획득과 해제 작업을 처리해서, 그나마 코드를 간결하게 만들었다.
만약 JDBCUtil 클래스가 없다면 더 많은 자바 코드가 필요했을 것이다.</p>

<p>유지보수에 있어서 DB연동에 관한 복잡한 자바 코드는 이제 중요하지 않다. 개발자는 SQL만 관리하면 되며, Mybatis는 개발자가 이 SQL 관리에만 집중할 수 있도록 도와준다.</p>
<p>Mybatis는 xml에 저장된 sql명령어를 대신 실행하고 결과를 VO같은 자바 객체에 자동으로 매핑까지 해준다. 그래서 Mybatis 프레임워크를 데이터 맵퍼라고 부른다. 결국
Mybatis 프레임워크를 이용하여 DB연동을 처리하면 대부분 한두줄의 자바 코드만으로 원하는 DB연동 로직을 처리할 수 있게 된다.</p>

<p>Mybatis는 Java코드에서 SQL문을 분리하는데 만약 SQL명령어가 DAO 같은 자바 클래스에 저장되면 SQL 명령어만 수정하는 상황에서도 자바 클래스를 다시 컴파일 해야한다. 그리고 SQL 명령어들을 한곳에 모아 관리하기도 쉽지 않다.
결국 SQL명령어에 대한 통합관리를 위해서도 자바 소스에서 SQL을 분리하는 것은 매우 중요하다. </p>

<H2>1.2 Java ORM Plugin 설치</H2>
<p>스프링 관련 프로젝트를 개발할 때 STS플러그인 프로그램을 이용하면 스프링 환경설정파일을 비롯하여 다양한 기능을 사용할 수 있었다. Mybatis역시 Java ORM이라는 플러그인 프로그램이 있어 이 플러그인을 사용하면 Mybatis와 관련된 복잡한 XML 설정 파일들을 자동으로 만들고 관리할 수 있다.</p>
<p>p.470 부터 설치과정이 불가능하여 수동 설치하였음.</p>

<H2>1.3 프로젝트 생성</H2>
<p>Mynatis 프레임워크의 구조와 개념을 이해하기 위해 Mybatis만으로 간단한 CRUD기능을 테스트한다. 스프링과 연동하지 않고 단독으로 사용하면 불편한 점이 많지만 기능과 구조를 이해하기 위해 간단한 프로젝트를 Mybatis로 수행해본다.</p>
<p>책을 따라 프로젝트를 생성하면 결과가 다르다.
이럴때는 메이븐 프로젝트를 심플 프로젝트로 생성하면 심플프로젝트를 메이븐으로 생성한것과 같은 결과로 생성된다.</p>
<p>JRE Library가 1.5 버전으로 된것을 확인할 수 있는데 이를 1.8 버전으로 수정한다.</p>
<p>마지막으로 DB 연동을 위한 H2 Driver와 Mybatis, iBatis 라이브러리들을 내려받고자 pom.xml 파일에 depedency를 추가한다.</p>

<H2>1.4 VO(value Object)클래스 작성</H2>
<p>XML 파일에 저장된 SQL 명령어에 사용자가 입력한 값들을 전달하고 실행 결과를 매핑할 VO 클래스를 작성한다.
기존의 게시판 프로그램에서 사용한 BoardVO 클래스를 복사한다.</p>

<H2>1.5 SQL Mapper XML 파일 작성</H2>
<p>SQL Mapper XML 파일은 Mybatris 에서 가장 중요한 파일로 이 XML 파일에 DB 연동에 필요한 SQL 명령어들이 저장되기 때문이다. 이 XML 파일은 Java ORM 플러그인을 이용하여 간단하게 생성할 수 있다.</p>
<p>src/main/resources 폴더에 mappings 패키지를 생성하여 이동시키고, 내용을 수정한다.</p>
<p>SQL Mapper 파일은 mapper를 루트 엘리먼트로 사용한다. 그리고 insert,update,delete,select 엘리먼트를 이용하여 필요한 sql 구문을 등록한다. 기존의 BoardDAO 클래스에서 사용했던 SQL 구문을 그대로 등록하여 재사용한다.</p>

<H2>1.6 Mybatis 환경 설정 파일</H2>
<p>My batis환경 설정 파일도 앞에서 설치한 Java ORM 플러그인을 사용하면 자동으로 생성할 수 있다. 일반적으로 이름은 sql-map-config.xml 을 사용한다.</p>
<p>생성하면 scr 폴더에 sql-map-config.xml 파일과 db.properties 파일이 생성되며 이를 src/main/resource 폴더로 이동시킨다. 이동시킨 후 데이터베이스 커녁션 관련 프로퍼티 정보가 등록된 db.properties파일을 수정한다.
이 파일에는 MySQL데이터베이스 연동을 위한 프로퍼티 정보가 설정되어있으므로 H2데이터 베이스 관련 정보로 수정한다.</p>
<p>Mybatis 메인 환경설정 파일인 sql-map-config.xml 역시 기본적인 내용이 설정되어 있다. 이는 정확한 설정을 제공하는것이 아닌 전체적인 설정의 기본 틀만 제공한다. 따라서 관련정보를 정확하게 수정한다.</p>
<p>properties엘리먼트는 xml 설정에서 사용할 프로퍼티를 선언하거나 외부 프로퍼티 파일을 참조할 때 사용한다. 이렇게 선언도니 프로퍼티는 ${프로퍼티 이름}으로 참조하여 사용할 수 있다.
typeAliases 엘리먼트는 typeAlias를 여러개 가질 수 있으며, typeAlias 엘리먼트를 이용하여 특정 클래스의 별칭을 선언할 수 있다. 이 Alias는 SQL 명령어들이 저장되는 sql mapper 에서사용할 수 있으며, 이를 통해 sql mapping 파일의 크기를 줄여주기도 하고 설정을 간단히 처리할 수 있다.
현재설정은 BoardVO 클래스에 대한 Alias만 board로 설정했다.</p>
<p>Mybatis 는 특정 DBMS로부터 커넥션을 획득하고 DB 연동을 처리하기 위해서 반드시 DataSource 정보가 필요하다. environments 엘리먼트에는 다양한 설정을 추가할 수 있지만, 현재는 가장 중요한 DataSource 정보가 필요하다. environments 엘리먼트에는 다양한 설정을 추가할 수 있지만, 현재는 가장 중요한 DataSource 설정만 작성하였고, H2 데이터베이스 연동을 위한 설정으로 수정했다. </p>
<p>mappers 엘리먼트는 여러 mapper를 가질 수 있으며, 이 mapper를 이용하여 sql명령어들이 저장된 sql 파일들을 등록할 수 있다.</p>

<H2>1.7 SqlSession 객체 생성하기</H2>
<p>지금까지는 Mybatis 관련 설정 파일을 작성하기만 했다. 지금부터 이 Mybatis 관련 설정을 기반으로 DAO 클래스를 구현한다.
Mybatis를 이용하여 DAO를 구현하려면 sqlSession 객체가 필요하다. 그런데 이 객체를 얻으려면 sqlSessionFactory 객체가 필요하다. 따라서 DAO 클래스를 구현하기 앞서 SqlSessionFactory 객체를 생성하는 유틸리티 클래스를 작성해야 한다.</p>
<p>이 코드에서 가장 핵심적인 두 줄이 있다. Mybatis 메인 설정 파일인 sql-map-config.xml 파일로부터 설정 정보를 읽어 들이기 위한 입력 스트림을 생성해야 한다. 그리고 나서 입력 스트림을 통해 sql-map-config.xml 파일으 읽어 sqlSessionFactory객체를 생성한다.</p>

~~~
Reader reader = Resouces.getResourceAsReader("sql-map-config.xml");
sessionFactory = new SqlSessionFactoryBuilder().build(reader);
~~~

<p>getSqlSessinInstance 메소드는 SqlSessionFactory 객체로부터 SqlSession 객체를 얻어내어 리턴하는 메소드다. 이 메소드를 이용하여 SqlSession객체가 필요한 DAO 클래스를 구현하면 된다.</p>

<H2>1.8 DAO 클래스 작성</H2>
<p>Mybatis를 이용하여 데이터 베이스 연동을 처리하는 BoardDAO 클래스를 작성한다.</p>
<p>BoardDAO 클래스는 생성자에서 SqSessionFactoryBean 을 이용하여 SqlSession 객체를 얻어내고 있다. 그리고 이 SqlSession 객체의 메소드를 이용하여 CRUD 기능의 메소드를 모두 구현하고 있다.</p>
<p>구현된 각 메소드를 보면 두 개의 정보가 인자로 전달되고 있는데, 첫 번째 인자는 실행될 SQL의 id 정보이다. 이때 SQL Mapper에 선언된 네임스페이스와 아이디를 조합하여 아이디를 지정해야 한다.
그리고 두 번째 인자는 parameterType 속성으로 지정된 파라미터 객체이다. 등록, 수정, 삭제는 각각 insert(), update(), delete() 메소드로 처리하며, 단 건 조회, 목록 조회는 selectOne(), selectList()메소드로 처리한다.</p>

<H2>1.9 테스트 클라이언트 작성 및 실행</H2>
<p>다음은 BoardDAO 클래스의 메소드를 테스트하는 클라이언트 프로그램이다. src/test/java 소스폴더에 클라이언트 프로그램을 작성하고 실행해본다.</p>

<H1>2. Mapper XML 파일 설정</H1>
<H2>2.1 SQL Mapper XML 기본설정</H2>
<H3>2.1.1 Mybatis 구조</H3>
<p>My batis의 구조는 다음 그림과 같다.</p>

![33](https://user-images.githubusercontent.com/65153512/119659625-4dbce280-be69-11eb-974a-eece946d944b.jpg)

<p>SqlMapConfig.xml 파일은 Mybatis 메인 환경설정 파일이다. Mybatis는 이 파일을 읽어들여 어떤 DBMS와 커넥션을 맺을지, SQL Mapper XML 파일들이 등록되어 있는지 알 수 있다.</p>
<p>Mybatis는 SqlMap.xml 파일에 등록된 각 SQL 명령어들을 Map 구조로 저장하여 관리한다. 각 SQL 명령어는 고유한 아이디 값을 가지고 있으므로 특정 아이디로 등록된 SQL을 실행할 수 있다. 그리고 SQL이 실행될 때 필요한 값들은 input 형태의 데이터로 할당하고, 실행된 SQL이 SELECT 구문일 때는 output 형태의 데이터로 리턴한다.</p>

<H3>2.1.2 Mapper XML 파일 구조</H3>
<p>Mybatis 프레임 워크에서 가장 중요한 파일은 SQL 명령어들이 저장되는 SQL Mapper XML(이후부터는 Mapper로 통칭) 파일이다. Mapper는 mapper를 루트 엘리먼트로 가지는 XML파일이며 다음과 같은 구조를 가진다.</p>

![34](https://user-images.githubusercontent.com/65153512/119661568-6201df00-be6b-11eb-83d9-b197604403a6.jpg)

<p>Mapper 파일의 구조를 보면 먼저 DTD선언이 등장하고 그 밑에 mapper 루트 엘리먼트가 선언된다. mapper 엘리먼트는 namespace 속성을 가지는데, 이 네임 스페이스를 사용하여 더 쉽게 유일한 SQL 아이디를 만들 수 있다. 네임스페이스가 지정된  mapper의 sql을 dao클래스에서 참조할 때는 다음과 같이 네임스페이스와 sql의 아이디를 결합하여 참조해야 한다.</p>

![35](https://user-images.githubusercontent.com/65153512/119662912-dab56b00-be6c-11eb-8f5f-e437f98c1314.jpg)

<p>mapper 파일에 sql 명령어들을 등록할 때는 sql 구문의 종류에 따라 적절한 엘리먼트를 사용한다. INSERT 구문은 insert엘리먼트를, select 구문은 select 엘리먼트를 사용하는 식이다. 이때, 각 엘리먼트에서 사용할 수 있는 속성들이 다르므로 그 의미와 용도를 이해해야 한다.</p>

<H3>2.1.3 select 엘리먼트</H3>
<p>select 엘리먼트는 데이터를 조회하는 SELECT 구문을 작성할 때 사용한다. select 엘리먼트에는 다음과 같이 parameterType과 resultType 속성을 사용할 수 있다.</p>

![36](https://user-images.githubusercontent.com/65153512/119676828-21a95d80-be79-11eb-8111-d5deb5b34990.jpg)

<H4>2.1.3.1 id 속성</H4>
<p>select 엘리먼트에 선언된 id 속성은 필수 속성으로, 반드시 전체 Mapper 파일들 내에서 유일한 아이디를 등록해야 한다. 그래야 나중에 DAO 클래스에서 특정 아이디로 등록된 SQL을 실행할 수 있다. 이 id속성과 관련하여 살펴볼 것이 루트 엘리먼트인 mapper이다.
mapper 엘리먼트에 설정된 네임스페이스는 mapper 엘리먼트 안에서 선언된 여러 아이디를 하나의 네임스페이스로 묶을 수 있다. 예를들어 getTotalCount라는 아이디는 네임스페이스가 다르므로 다른 아이디로 처리될 수 있다.</p>

![37](https://user-images.githubusercontent.com/65153512/119682578-f5441000-be7d-11eb-8443-9bbde1f3b2dd.jpg)

<H4>2.1.3.2 parameterType 속성</H4>
<p>Mapper 파일에 등록된 SQL을 실행하려면 SQL 실행에 필요한 데이터를 외부로부터 받아야 한다. 이때 사용하는 속성이 parameterType이다.parameterType 속성값으로는 일반적으로 기본형이나 VO 형태의 클래스를 지정한다.</p>
<p>이때 Mybatis 메인 설정 파일 sql-map-confix.xml에 등록된 typeAlias의 Alias를 사용하면 설정을 더 간결하게 처리할 수 있다. parameterType으로 지정된 클래스에는
사용자가 입력한 값들을 저장할 여러 변수가 있다. 변수들을 이용하여 SQL 구문에 사용자 입력값들을 설정하는데, 이때 #{변수명} 표현을 사용한다. 중요한건 parameterType 속성은 생략할 수 있으며 대부분 생략한다.</p>

<H3>2.1.3.3 resultType 속성</H3>
<p>검색 관련 SQL 구문이 실행되면 ResultSet이 리턴되며, ResultSet에 저장된 검색 결과를 어떤 자바 객체에 매핑할지 지정해야 하는데, 이때 사용하는 것이 resultType 속성이다.</p>
<p>resultType 속성값으로도 Alias를 사용할 수 있는데, 만약 resultType 속성값으로 board를 설정했다면 SELECT 실행 결과를 BoardVO 객체에 매핑하여 리턴하라는 의미이다.
resultType 속성은 당연히 쿼리 명령어가 등록되는 select 엘리먼트에서만 사용할 수 있으며 parameterType 속성과 달리 select 엘리먼트에서 절대 생략할 수 없는 속성이다. 다만 resultType 속성대신 resultMap속성을 사용할 수는 있다.</p>
<p></p>

<H3>2.1.4 insert 엘리먼트</H3>
<p>insert 엘리먼트는 데이터베이스에 데이터를 삽입하는 insert 구문을 작성하는 요소이다.</p>

![image](https://user-images.githubusercontent.com/65153512/119779662-ea33c300-bf03-11eb-9b27-710b6607bd71.png)

<p>insert 구문은 자식 요소로 selectKey 엘리먼트를 가질 수 있다. 대부분 관계형 데이터베이스에서는 기본 키 필드의 자동 생성을 지원하는데, Mybatis 에서는 insert요소의 자식요소인 selectKey 요소를 이용하여 생성된 키를 쉽게 가져올 수 있는 방법을 제공한다</p>

~~~
<selectKey keyProperty="seq" resultType="int">
	select board_seq.nextval as seq from dual
</selectKey>
~~~

<p>이 설정은 Board_SEQ 라는 시퀀스로부터 유일한 킷값을 얻어내어 글 등록에서 일련번호(seq) 값으로 사용하라는 설정이다.</p>

<H3>2.1.5 update 엘리먼트</H3>
<p>update 엘리먼트는 데이터를 수정할 때 사용되는 update 구문을 작성하는 요소이다.</p>

![image](https://user-images.githubusercontent.com/65153512/119780246-8c53ab00-bf04-11eb-8ebd-24c67cac4ff5.png)

<H3>2.1.6 delete 엘리먼트</H3>
<p>delete 엘리먼트는 데이터를 삭제할 때 사용되는 delete 구문을 작성하는 요소이다.</p>

![image](https://user-images.githubusercontent.com/65153512/119780451-cc1a9280-bf04-11eb-8a60-2dfba6dce899.png)

<H2>2.2 SQL Mapper XML 추가설정</H2>
<H3>2.2.1 resultMap 속성 사용</H3>
<p>검색결과를 특정 자바 객체에 매핑하여 리턴하기 위해 parameterType 속성을 사용한다. 그러나 검색 결과를 parameterType 속성으로 매핑할 수 없는 몇몇 사례가 있다.
예를들어, 검색 쿼리가 단순 테이블 조회가 아닌 JOIN 구문을 포함할 때는 검색 결과를 정확하게 하나의 자바 객체로 매핑할 수 없다. 또는 검색된 테이블의 칼럼 이름과 매핑에 사용될 자바 객체의 변수이름이 다를 때 검색 결과가 정확하게 자바 객체로 매핑되지 않는다. 이럴 때 resultMap속성을 사용하여 처리한다.</p>
<p> resultMap 속성을 사용하려면 먼저 resultMap 엘리먼트를 사용하여 매핑 규칙을 지정해야 한다. (코드)이 설정에서는 boardResult라는 아이디로 resultMap을 설정했다. reseultMap 설정ㅇ느 PK(primary key)에 해당하는 seq만 id엘리먼트를 사용했고 나머지를 result 엘리먼트를 이용하여 검색결과로 얻어낸 칼럼의 값과 BoardVO객체의 변수를 매핑하고 있다. 이렇게 설정된 resultMap을 getBoardList로 드록된 쿼리에서 resultMap 속성으로 참조하고 있다.</p>

<H3>2.2.2 CDATA Section 사용</H3>
<p>만약 SQL 구문 내에 꺽쇄 기호를 사용한다면 에러가 발생한다. 이는 XML 파서가 XML 파일을 처리할때 꺽쇄를 연산자가 아닌 또 다른 태그의 시작으로 처리하기 때문이다. 결국 Mapper 파일에 등록된 SQL 구문에는 꺽쇄 기호를 사용하면 에러가 발생한다.
하지만 CDATA Section으로 SQL구문을 감싸주면 에러는 사라진다.</p>

~~~
<select id="getBoard" resultType="board" parameterType="board">
	<![CDATA[select * from board where seq <=#{seq} ]]>
</select>
~~~

<p>CDATA Section은 Mybatis와는 상관없는 XML고유의 문법으로서, CDATA 영역에 작성된 데이터는 단순한 문자 데이터이므로 XML파서가 해석하지 않도록 한다.
결국 CDATA Section 안에 작성된 데이터는 XML 파서가 처리하지 않고 데이터베이스에 그대로 전달하므로 문제가 발생하지 않는다.</p>
<p></p>

<H3>SQL 대문자로 설정하기</H3>
<p>Mapper 파일에 등록되는 SQL 구문은 일반적으로 대문자로 작성한다. 사실 SQL 구문은 대소문자를 구분하지 않는다. 하지만 파라미터들을 바인딩할 때 대부분 칼럼명과 변수명이 같으므로 SQL 구문이 조금이라도 복잡해지면 이 둘을 구분하기 쉽지 않다. 따라서 SQL은 모두 대문자로 표현하여 식별성을 높인다.</p>
<p>지금까지의 내용을 board-mapping.xml에 적용한다.</p>

<H2></H2>
<p></p>
<p></p>
<p></p>