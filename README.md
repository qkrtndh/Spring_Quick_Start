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

<H2>2.3 Mybatis JAVA API</H2>
<H3>2.3.1 SqlSessionFactoryBuilder 클래스</H3>
<p>MyBatis Mapper 설정이 끝났으면 이제 남은 작업은 MyBatis 프레임워크에서 제공하는 API를 이용해서 DAO 클래스를 구현하는 것이다. MyBatis로 DAO 클래스의 CRUD 메소드를 구현하려면 MyBatis에서 제공하는 SqlSession 객체를 사용해야 한다. 그런데 SqlSession 객체는 SqlSessionFactory로부터 얻어야 하므로 SqlSessionFactory 객체를 생성한다.</p>
<p>이 객체를 생성하려면 SqlSessionFactoryBuilder의 build 메소드를 이용하는데 build 메소드는 Mybatis 설정 파일을 로딩하여 SqlSessionFactory 객체를 생성한다. 그리고 sql-map-config 파일을 로딩하려면 입력 스트림인 Reader 객체가 필요하다. reader 객체는 resources 클래스의 getResourceAsReader 메소드를 사용하여 얻어낼 수 있다. 다음은 SqlSessionFactory 객체를 생성하는 코드이다.</p>

~~~
Reader reader = Resources.getResourceAsReader("sql-map-config.xml");
SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
~~~

<H3>2.3.2 SqlSessionFactory 클래스</H3>
<p>SqlSessionFactory는  이름에서 알수 있듯 SqlSession 객체에 대한 공장 역할을 수행한다. SqlSessionFactory 객체는 openSession이라는 메소드를 제공하며, 이 메소드를 통해서 SqlSession객체를 얻을 수 있다. 이렇게 얻어낸 SqlSession객체를 통해  다음과 같이 글 등록 기능을 처리할 수 있다.</p>

~~~
SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
SqlSession session = sessionFactory.openSession();
session.insert("BoardDAO.insertBoard",vo);
~~~

<H3>2.3.3 유틸리티 클래스 작성</H3>
<p>Mybatis를 사용하여 DB 연동을 간단하게 처리하려면 최종적으로 Mybatis가 제공하는 SqlSession 객체를 사용해야 한다. 따라서 모든 DAO 클래스에서 좀더 쉽게 SqlSession 객체를 획득할 수 있도록 공통으로 제공할 유틸리티 클래스(sqlsessionfactorybean 클래스)를 만든다.</p>
<p>지금은 유틸리티 클래스를 직접 만들었지만 후에 Mybatis와 스프링을 연동하여 프레임워크에서 제공하는 클래스를 사용한다.</p>

<H3>2.3.4 SqlSession 객체</H3>
<p>SqlSession 객체는 Mapper XML에 등록된 SQL을 실행하기 위한 다양한 API를 제공한다.</p>
<H4>2.3.4.1 selectOne() 메소드</H4>
<p>selectOne 메소드는 오직 하나의 데이터를 검색하는 SQL 구문을 실행할 때 사용한다. 따라서 getBoard 같은 단 건 조회용 메소드를 구현할 때 사용할 수 있다. 쿼리가 한 개의 레코드만 리턴되는지를 검사하므로 만약 쿼리의 수행 결과로 두 개 이상의 레코드가 리턴될 때는 예외가 발생한다.</p>

~~~
public Object selectOne(String statement)
public Object selectOne(String statement, Object parameter)
~~~

<p>selectOne 메소드의 statement 매개변수는 Mapper XML 파일에 등록된 SQL의 아이디이다.
이때 SQL의 아이디를 네임스페이스와 결합하여 지정해야 한다. 그리고 실행될 SQL 구문에서 사용할 파라미터 정보를 두번째 인자로 지정하면 된다.</p>

<H4>2.3.4.2 selectList 메소드</H4>
<p>selectList 메소드는 여러 개의 데이터가 검색되는 SQL 구문을 실행할 때 사용한다. 매개변수의 의미는 selectOne 메소드와 같다. </p>

~~~
public List selectList(String statement)
public List selectList(String statement, Object parameter)
~~~

<H4>2.3.4.3 insert,update,delete 메소드</H4>
<p>각 메소드는 INSERT,UPDATE<DELETE SQL 구문을 실행할 때 사용한다. 각 메소드는 실행된 SQL 구문으로 인해 몇 건의 데이터가 처리되었는지를 리턴한다. </p>

~~~
public int insert(String statement, Object parameter)
public int update(String statement, Object parameter) throws SQLException
public int delete(String statement, Object parameter) throws SQLException
~~~

<H3>3. 스프링과 MyBatis 연동</H3>
<p>Mybatis 와 스프링을 연동하는 실습은 기존의 BoardWeb 프로젝트에서 진행한다.</p>

<H2>3.1 라이브러리 받기</H2>
<p>스프링에서 Mybatis 연동에 대한 API를 제공하지 않으며 오히려 Mybatis 에서 스프링 연동에대한 API를 제공한다. 따라서 스프링과 Mybatis 를 연동하려면 Mybatis에서 제공하는 클래스들을 이용해야한다.</p>

~~~
org.mybatis.spring.SqlSessionFactoryBean
org.mybatis.spring.SqlSessionTemplate
~~~

<p>스프링과 MyBatis 연동에 필요한 라이브러리들을 받으려면 pom.xml 파일에 depedency를 추가한다.</p>
<p>maven depedencies에 라이브러리들이 추가되었는지 확인한다. mybatis-3.3.1.jar은 순수 Mybatis 라이브러리고, mybatis-spring-1.2.4.jar 파일은 Mybatis와 스프링을 연동하기 위해 사용하는 라이브러리다.</p>

<H2>3.2 Mybatis 설정 파일 복사 및 수정</H2>
<p>스프링과 Mybatis를 연동하려면 Mybatis 메인 환경설정 파일인 sql-map-config.xml 파일과 SQL 명령어들이 저장되어있는 Mapper 파일이 필요하다. 따라서 MybatisProject에서 작성했던 XML 설정 파일들을 복사하여 BoardWeb 프로젝트의 src/main/resources 폴더에 추가한다.</p>
<p>이후 sql-map-config.xml 파일의 데이터 소스 관련 설정을 삭제한다.</p>
<p>데이터 소스는 스프링 프레임워크에서 이미 등록하여 사용하고 있다. 그리고 이 데이터 소스는 DB연동 뿐만 아니라 트랜젝션 처리 등 여러  곳에서 사용할 수 있으므로 Mybatis설정이 아닌 스프링 설정 파일에서 제공하는 것이 맞다. 그리고 SQL 명령어가 저장된 Mapper XML 파일은 수정없이 사용한다.</p>

<H2>3.3 스프링 연동 설정</H2>
<p>스프링과 Mybatis를 연동하려면 우선 스프링 설정 파일에 SqlSessionFactoryBean 클래스를 Bean 등록해야 한다. 그래야 SqlSessionFactoryBean 객체로부터 DB 연동 구현에 사용할 SqlSession 객체를 얻을 수 있다.</p>
<p>MybatisProject 에서는 SqlSession 객체를 얻기 위해 SqlSessionFactoryBean 클래스를 유틸리티 클래스로 직접 구현했다. 하지만 이 클래스를 Mybatis 에서 제공하므로 굳이 작성할 필요 없이 bean 등록 하면 된다.</p>
<p>SqlSessionFactoryBean 객체가 SqlSession 객체를 생산하려면 반드시 DataSource와 SQL Mapper 정보가 필요하다. 따라서 앞에 등록된 DataSource를 Setter 인젝션으로 참조하고, SQL Mapper가 드록된 sql-mapper-config.xml 파일도 Setter 인젝션으로 설정해야 한다.
그래야 bean등록된 SqlSessionFactoryBean이 SqlSessionFactory 객체를 생성할 수 있다.</p>

<H2>3.4 DAO 클래스 구현 - 방법1</H2>
<p>Mybatis를 이용하여 DAO 클래스를 구현하는 방법은 두 가지이다. 첫째는 SqlSessionDaoSupport 클래스를 상속하여 구현하는 것이다.</p>
<p>SqlSessionDaoSupport 클래스를 상속한 후에 가장 먼저 한 작업은 setSqlSessionFactory 메소드를 재정의 한 것이다. 재정의한 setSqlSessionFactory  메소드 위에 @AutoWired를 붙였는데 이렇게 하면 스프링 컨테이너가 setSqlSessionFactory 메소드를 자동으로 호출한다. 이때, 스프링 설정 파일에
bean등록된 SqlSessionFactoryBean 객체를 인자로 받아 부모인 SqlSessionDaoSupport에 setSqlSessionFactory 메소드로 설정해준다. </p>
<p>이렇게 해야 SqlSessionDaoSupport 클래스로부터 상속된 getSqlSession 메소드를 호출하여 SqlSession 객체를 리턴받을 수 있다. 이제 SqlSession 객체의 CRUD 관련 메소드를 이용하여 DB 연동을 처리하면 된다.</p>

<H2>3.5 DAO클래스 구현 - 방법2</H2>
<p>Mybatis를 이용하여 DAO 클래스를 구현하는 두 번째 방법은 SqlSessionTemplate 클래스를 bean등록하여 사용하는 것이다. 스프링 설정 파일에서 SqlSessionTemplate 클래스를 SqlSessionFactoryBean 아래에 bean 등록한다.</p>
<p>여기서 중요한 것은 SqlSessionTemplate 클래스에는 Setter 메소드가 없어서 Setter 인젝션할 수 없다는 것이다. 따라서 생성자 메소드를 이용한 Constructor 주입으로 처리할 수 밖에 없다.
그리고 나서 DAO 클래스를 구현할 때, SqlSessionTemplate 객체를 @AutoWried를 이용하여 의존성 주입 처리하면 SqlSessionTemplate 객체로 DB연동 로직을 처리할 수 있다.</p>
<p></p>

<H2>3.6 MyBatis 연동 테스트</H2>
<p>BoardDAOMybatis 객체를 의존성 주입할 수 있도록 BoardServiceImpl 클래스를 수정하고 테스트 클라이언트 프로그램을 실행한다.</p>

<H2>3.7 Dynamic SQL으로 검색 처리</H2>
<p>Mybatis SQL의  재사용성과 유연성을 향상하고자 Dynamic SQL을 지원한다. Dynamic SQL을 사용하면 조건에 따라 다양한 쿼리를 데이터베이스에 전송할 수 있다.</p>

<H3>3.7.1 Dynamic SQL 적용 전</H3>
<p>Dynamic SQL의 필요성을 확인해본다. 만약 현재 상태에서 검색기능을 추가한다고 하면 두가지 검색 관련 쿼리가 필요할 것이다.</p>

~~~
board-mapping.xml

<select id="getBoardList_T" resultType="board" resultMap="boardResult">
	<![CDATA[
		SELECT * FROM BOARD
		WHERE TITLE LIKE '%'||#{searchKeyword}||'%'
		ORDER BY SEQ DESC
	]]>
</select>

<select id="getBoardList_C" resultType="board" resultMap="boardResult">
	<![CDATA[
		SELECT * FROM BOARD
		WHERE CONTENT LIKE '%'||#{searchKeyword}||'%'
		ORDER BY SEQ DESC
	]]>
</select>
~~~

<p>제목 검색과 내용 검색을 처리하기 위한 두 개의 쿼리를 등록했으면, 이제 DAO 클래스 getBoardList 메소드에 검색 조건에 따른 분기 처리 로직을 추가한다.
이런 방식으로 검색기능을 구현하면 이후에 추가되는 검색조건에 대해 비슷한 SQL 구문들을 반복해서 작성해야 하고, 이는 유지보수의 어려움을 의미한다.
그리고 DAO 클래스의 메소드 역시 검색 관련 SQL 구문의 개수만큼 분기 처리 로직을 추가해야 하므로 SQL이 추가될때마다 DAO 클래스도 수정해야 한다.</p>

<H3>3.7.2 Dynamic SQL 적용 후</H3>
<p>이런 SQL의 중복 문제를 해결하기 위해 Mybatis에서는 Dynamic SQL을 지원한다. Dynamic SQL을 이용하여 이런 문제가 해결하는 방법을 알아본다.</p>
<p>수정된 SQL 구문을 보면 if 라는 동적 요소를 사용하여 조건에 따른 분기 처리를 하고 있다. 만약 searchContion 변숫값이 TITLE를 가지고 있으면 제목검색에 해당하는 조건이 추가되고, CONTENT 라는 값을 가지고 있으면
내용 검색에 해당하는 조건이 추가되어 실행된다. 이렇게 동적 엘리먼트를 이용하여 SQL을 처리할 수 있으므로 검색과 관련된 쿼리는 하나만 있으면 된다.</p>
<p>이 SQL을 이용하면 DB연동 로직을 처리하는 BoardDAOMybatis클래스의 메소드 역시 원래의 코드를 유지할 수 있다. 이 코드는 새로운 검색 조건이 추가되더라도 수정할 필요가 없어 유지보수가 편해진다.</p>

<H1>4. JPA 개념</H1>
<p>우리가 사용하는 대부분 프로그램은 사용자가 입력한 데이터나 비즈니스 로직 수행 결과로 얻은 데이터를 재사용할 수 있도록 데이터베이스에 저장한다.
하지만 자바의 객체와 데이터베이스의 테이블이 정확하게 일치하지 않는다. 따라서 둘 사이를 매핑하기 위해 많은 SQL 구문과 자바 코드가 필요할 수 밖에 없다.</p>
<p>ORM(Object-Relation-Mapping) 은 이렇게 정확하게 일치하지 않는 자바 객체와 테이블 사이를 매핑해준다. 다시 말하면 ORM은 자바 객체에 저장된 데이터를 테이블의 Row 정보로 저장하고, 반대로 테이블에 저장된 Row 정보를 자바객체로 매핑해준다. 이 과정에서 사용되는 SQL구문과 자바코드는 ORM 프레임워크가 자동으로 만들어준다.</p>
<p>지금까지 스프링 JDBC나 Mybatis를 이용하여 자바 객체와 테이블을 매핑해왔다. 하지만 어떤 DB연동 기술이나 프레임워크를 사용하더라도 SQL 명령어를 자바 클래스나 외부의 XML 파일에 작성해야 했다.
그리고 이렇게 작성된 SQL은 유지보수 과정에서 지속적으로 수정되며 새로운 SQL이 추가되기도 한다. ORM프레임워크의 가장 큰 특징이자 장점은 DB연동에 필요한 SQL을 자동으로 생성한다는 것이다.
또한 이렇게 생성되는 SQL은 DBMS가 변경될 때 자동으로 변경된다. 다만 ORM 환경설정 파일 어딘가에 DBMS가 변경되었다는 것만 수정해주면 된다.</p>

![1](https://user-images.githubusercontent.com/65153512/119996840-146fa880-c00a-11eb-8210-fce4430c2029.jpg)

<H2>4.1 JPA의 특징</H2>
<p>JPA(Java Persistence API)는 모든 ORM 구현체 들의 공통 인터페이스를 제공한다. JPA를 JDBC API와 비교하여 이해하면 편하다.</p>

![2](https://user-images.githubusercontent.com/65153512/119996847-15a0d580-c00a-11eb-8adc-233425cc3c9a.jpg)

<p>JDBC는 특정 DBMS에 종속되지 않는 DB연동구현을 지원한다. DB연동 로직을 구현할 때, JDBC API의 인터페이스들을 이용하면 실질적인 DB연동 처리는 해당 DBMS의 드라이버 클래스들이 담당하는 구조이다.
따라서 DBMS가 변경되는 상황에서도 드라이버만 변경하면 JDBC API를 이용하는 애플리케이션은 수정하지 않는다.</p>
<p>JPA도 JDBC와 마찬가지 이다. 애플리케이션을 구현할 때 JPA API를 이용하면 개발 당시에는 Hibernate를 ORM 프레임워크로 사용하다가 실제 서비스가 시작될때는 TopLink로 변경할 수 있다.</p>

![3](https://user-images.githubusercontent.com/65153512/119996849-16396c00-c00a-11eb-8115-4285dc452d3e.jpg)

<H2>4.2 JPA 프로젝트 생성</H2>
<p>JPA 프로젝트는 이클립스의 JPA Project 마법사를 이용해도 되지만, JPA Project는 추가로 설정해야 하는 것이 있으므로 Maven 기반으로 프로젝트를 생성한다.
책을 참조하여 설치하되 properties 설정부분은 현재 버전에서 진행이 안되므로 install new software에서 이클립스 버전에 맞는 persistence 를 설치 후 진행한다(?).</p>

<H2>4.3 JPA 라이브러리 내려받기</H2>
<p>프로젝트에 JPA관련 라이브러리를 추가하기 위해 pom.xml파일을 수정한다.</p>

<H2>4.4 JPA 시작하기</H2>
<H3>4.4.1 엔티티 클래스 매핑</H3>
<p>JPA를 이용하는 간단한 프로젝트를 통해서 JPA를 구성하는 요소들이 어떤 것들이 있는지 확인한다. 가장 먼저 데이터베이스의 테이블과 매핑될 영속 클래스를 작성하여 매핑 설정을 추가한다.
엔티티 클래스를 작성하는데 특별한 조건이나 규칙이 존재하지 않으므로 VO 클래스를 만들 때 처럼 작성하면 된다. 하지만 가능하다면 이클립스의 JPA Entity 기능을 이용하여 생성하는것이 좋다.</p>
<p>이기능을 이용하면 persistence에 자동으로 엔티티 클래스가 등록된 것을 확인할 수 있다. 이제 엔티티 클래스에 JPA 매핑 관련 어노테이션을 설정한다. 엔티티 클래스의 모든 멤버 변수를 private로 선언한다. 특히 일반적인 프로그램에서는 객체를 식별하기 위해서 유일 식별자를 사용하지는 않지만, 영속 객체가 테이블과 매핑될 때 객체 식별 방법이 필요하므로 유일 식별자를 소유하는 클래스로 작성한다.</p>
<p>Board 라는 엔티티 클래스에 설정된 각 어노테이션의 의미는 다음과 같다.</p>

어노테이션|의미
----|----
@Entity|@Entity가 설정된 클래스를 엔티티 클래스라고 하며, @Entity가 붙은 클래스는 테이블과 매핑된다
@Table|엔티티와 관련된 테이블을 매핑한다. name 속성을 사용하여 BOARD 테이블과 매핑했는데 생략하면 클래스이름이 테이블 이름과 매핑된다.
@Id|엔티티 클래스의 필수 어노테이션으로, 특정 변수를 테이블의 기본키와 매핑한다. 예제에서는 seq변수를 테이블의 SEQ와 매핑했다. @Id가 없는 엔티티 클래스는 JPA가 처리하지 못한다.
@GeneratedValue|@Id가 선언된 필드에 기본 키를 자동으로 생성하여 할당할 때 사용한다. 다양한 옵션이 있지만 이것만 사용하면 데이터베이스에 따라 자동으로 결정되며 H2는 시퀀스를 이용하여 처리한다.
@Temporal|날짜 타입의 변수에 선언하며 날짜 타입을 매핑할 때 사용한다.TemporalType의 DATE,TIME,TIMESTAMP 중 하나를 선택한다.

<p>매핑 정보가 없는 나머지 필드들은 자동으로 BOARD 테이블의 동일한 칼럼과 매핑된다.</p>

<H3>4.4.2 persistence.xml 파일 작성</H3>
<p>JPA는 persistence.xml 파일을 사용하여 설정 정보를 관리한다. 이 설정 파일이 META-INF 폴더 아래에 있으면 별도의 설정없이 자동으로 JPA가 인식한다.</p>
<p>persistence.xml 파일은 JPA에서 메인 환경 설정 파일이다.  persistence를 루트 엘리먼트로 사용하며 영속성 유닛 (persistence-unit) 을 연동할 데이터베이스 하나당 하나의 영속성 유닛을 사용한다.</p>
<p></p>

<H3>4.4.3 클라이언트 프로그램 작성</H3>
<p>JPA 설정에 기초한 클라이언트 프로그램을 구현한다.</p>
<p>작성된 클라이언트 프로그램의 구조를 보면 가장 먼저 영속성 유닛을 이용하여 EntityManagerFactory 객체를 생성하고 있다. JPA를 이용하여 CRUD기능을 구현하려면 EntityManager 객체를 사용해야 한다. 그런데 이 객체는 EntityManagerFactory 객체로부터 얻을 수 있다.</p>
<p></p>

<H3></H3>
<p></p>
<p></p>
<p></p>


