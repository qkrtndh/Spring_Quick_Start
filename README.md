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

<H2></H2>
<p></p>
<p></p>
<p></p>

<H2></H2>
<p></p>
<p></p>
<p></p>