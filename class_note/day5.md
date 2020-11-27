# day5

- 검색 기능
  - 서치키워드와 컨디션
  - 자기자신을 포스트로 요청한다
    - 1번 두포스트 오버라이드해서 두겟을 실행시키도록 메소드를 만듬
    - 혹은 2번 서비스 메소드 오버라이드(둘다 상관없이 사용하고싶을때)
  - 서비스메소드 작성
    - 
    - vo에 변수추가
  - GETBOARDlIST 메소드 수정
    - '%'||?||'%' %는 0개이상의 문자 무시 언더바는 1나의 문자무시
    - if문으로 prepare넣어주기
    - ?로 타티틀을 물음표 해서는 안되는 이유유
      - ''다운 표가 붙어서 입력이 되어버림
      - ||로 연결해준건 괜찬타
        - 이런거 때문에 마이바틱스 같은거 생긴다
  - 실습- 에러
    - 로그인 성공시 값을 안줘서 널로 에러난다
    - 그래서 널체크를 해야한다
    - 그리고 리퀘스트 인코딩해줘야 된다
    
    
  - 인코딩 귀차느끼 하기
    - 지금있는 인코딩 다지우기
    - common에 newifileter CharacterEncodingFIlter
      - *.do 로해주기
    - Initialzation parameters => web.xml에 기록된다
      - 서블릿도만들수있다
      - name, encoding 변수 만들기
    - encoding 변수만들기
      - init에서 초기화 시키는데 fConfig.getInitParameter("encoding");
    - web.xml에서 확인하기
      - init-param 선언되있는걸 볼수있음
        - 얘들은 현재 filter에서만 사용가능하다
    - 네임도 만들어서 추출하기
    - Config는 서블릿엔진이 객체생성해서 init param 넣어서 넣어준다
    - 필터는 프리 로딩이라 엔진 켜지면서 바로 객체가 등록된다
      - 필터는 기능하나당 필터하나를 생성한다
      - 브라우저가 요청하면 필터가 가로채서 타임체크가 하고 chin.dofilter  만나면 인코딩필터 그리고 필터없으면 서블릿 그다음에 인코딩 필터 타임 필터 그리고 브라우저다. 이게 필터 체인이다
  - 확인 - 인코딩 되는지 확인하기
    
    - 안깨진다. 필터는 이런용도로 사용되서 필터는 꽤나 중요하다9

- 기본생성자 login하고 리스트에 만들기
  - 레이지 로딩이 되는 서블릿을 프리로딩으로바꿀수있다
  - <load-on-startup>1</load-on-startup>
    - 프리로딩이 되야디ㅗ는데 왜아되지 나는
  - 2도 만든다. 1, 2는 순서이다
    - 순서는 설정할 수 있게되어있는것이다

- 리스너
  - 커먼 - new Listner
  - SevletEngineListner 생성 next
  - http session events가 중요한 인터페이스
  - Lifecyle 체크
    - Context가 나오면 엔진이라고 생각해도된다
    - 엔진은 나중에 컨테이너라는 개념으로 바뀐다
  - finish하면 xml에도 추가된다
  - serveltcontextListner하니까 서블릿엔진이 생성되는시점에프리로딩된다
    - 리스너가 가장 먼저 로딩이된다
    - 서블릿 돌아가기전에 무조건 실행되는 로직이 있다면 이메서드에 구현하면 된다
  - 리스너가 중요한 이유는 스프링 mvc에 쓰여서 그렇다




- 현재 문제점, 로그인안한상태에서 기능을 다 쓸수 있다
  - 겟보드리스트 서블릿입장에서는 지금나를 호출한 브라우저가 로그인된상태인지 판단할수잇어야한다
- 겟보드리스트로이동
  - 세션체크 항목 작성
    - getsession
  - request = 브라우저라고 봐도된다
  - 세션을 생성하는것을 이해해야한다
    - 서버를 껏다키고 처음 브라우저 열어서 요청하면 세션을 생성해서 요청한다
      - 다음 요청때는 해당 세션을 유지해서 재사용한다
    - 브라우저 껏다가 새로운 브라우저 켜면 새로운 세션을 연다
      - 객체를지웟다가 새로운 세션객체만든다
    - 브라우저하나당 하나씩만 유지가 된다
    - getSession은 없으면 객체만들고 있으면 재사용하는 메서드이다
  - 만든거 주석
    - getAttribute해서 uesrID가지고있는지 확인해서 없으면 로그인.html로 보맨
  - 로그인 서블릿
    - 로그인 성공시 sessionAtrribute에 userId추가해줌
    - 세션은 맵처럼 key-value로 값을 저장한다
    - 로그인 실패시 글목록못보고 성공시 볼수 있다
      - 성공시에만 세션값
  - 모든서블릿에 세션에 들어가야되니 필터활용해라
- 테스터바꾸기
  - 로그인서블릿에 유저이름도 세션에 등록하라
- 실습
  - 어드민이면 글삭제 아니면 안보이게
  - 이건 성공했다.
    - userRole을 Object로 받기때문에 String으로 바꾸려면 형변환해야된다
  - Sevlet은 java코드라는것을 잊지않으면 도움이될것같다
- user 여러개 받을거면 그냥 user객체받아버리기
  - 사용하는 곳에서 getatrtribute 에서 user받아버리기

- Session이 제공하는 메서드
  - getAttribute, setAttribute
    - sesssion 같은 key에 넣어주면 덮어써버린다
    - 그래서 unique한 key를 써야한다
  - map이랑 차이는 value가 어떤 타입이 저장될지 몰라서 object가 출력된다
    - 그래서 형변화해야한다
    - set(Attribute(String name, Object vlaue)
    - 저장갯수는 무한하지만 덮어쓸수잇으니 유니크해야됨
  - Invalidate session 종료
  - removeAttribute 
    - get한다고해서 entry가 사라지는것이 아니다
    - 특정이름에 정보 삭제
  - setMaxInactiveInterval  
    - 세션유효시간 지정
    - session.setMaxInactiveInterval(10); 
      - 세컨드단위 -> 10초 유지하겟다
      - 로그인 유지하다 로그아웃되는것
- 로그아웃
  - 서블릿만들고 연결해주기



## jsp

- 서블릿 장점 은 빠르고 효율적

- 서블리 단점

  - web.xml에 등록을 해야한다
  - 자바코드 중심이라 할게많다
    - out.println 을 열라많이 써야한다
      - 디자인을 다해야한다.. 이게 헬이다

- 이걸 개선시키고 나온게 JSP다

  - WEbContent에 만들어야한다
  - jps는 디자인 중심 이고 내가 쓰고싶은만큼만 자바코드 입력한다
    - 코드가 줄어든다
  - web.xml 등록안해도된다
  - 리로딩을 할 필요가 없다
  - 아주 중요한 비밀이있다
    - C:\Users\foevn\Documents\dev\JBS-Spring\class_source\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\JSPProject\org\apache\jsp
      - 이게머지? 자바 파일
      - JSP는 서블릿으로 변환되서 실행된다 (서버가 변환해준다)
        - 톰캣으로 만든것과 다른서버가 만든 서블릿은 다르다 . 하지만 결과는 똑같다\
      - .java 복사해서 src에 넣기
      - 지우고 jsp다시 실행하니 다시파일생김
        - 근데 전 시간하고 똑같이 생성시간이됨
        - jsp 수정후 다시 요청하니 그때 새로운 시간으로 만들어짐
  - jps로만든 서블릿 탐사
    - servlet에 내가 구현한게 들어가있다 -> get post상관없이 실행된다
  - jsp는 내부적으로 서블릿으로 변환되서 실행된다
    - 왜서블릿을 배웟어야됫냐
      - jsp 사용할때 디버깅할대 필용할수있다

  

- 이클립스 프로젝트 - 클린 하면 바로 컴파일이 된다

- jsp로 게시판 진행
  - login_proc.jsp생성
    - 1,2,3 서블릿하고 똑같다
    - request 어떻게 쓸까?
      - 내가 작성한 모든 소스가 servcie안으로 들어가기 때문에 매개변수도 활용가능한것이다

- Jsp 문법
  - Scriptlet     \<%    %>
    - 정상적 자바코드, 모든 자바코드
  - Expression <%=  %>
    - 변수
    - 메소드 호출 (void가 아닌 메소드)
    - ;은 쓰지않는다 -> 정상적인 자바코드이기 때문에
    - 여러줄 코딩 불가능
  - 주석 <%--  --%>
    - 서버에게 서블릿으로 변환시키지 말라고 표시해주는 것
      - 잘안쓰여서 의미만 알면된다
    - <!-- --> 에 html 주석은 서블릿 변환시킬시 포함이 된다
  - 자바코드를 감싸지 않으면 실행이 안되고 텍스트로 나온다

- 임포트에잇는코드
  - language 자바언어를삽입할수있다
    - 다른언어도작성하게할려고했으나 실패 지웓됨
  - contentType- 인코딩
  - pageEncoding 은 안해도된다
- 중간에 뭘많이했는데 못적엇음

- 필터 복사
  
  - web.xml에등록
- 숙제
  - 상세수정 삭제는 마무리하기
  - 