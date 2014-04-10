rclog4j
=======
=== 소개 ===
 * RCLog4j(Runtime Configuration Log4j)는 JRE1.6이상, Apache log4j 1.2.x, Eclipse3.5이상에서 동작한다.
 * RCLog4j Agent와 RCLOG Client로 분리 배포된다.
 * RCLog4j Agent는 Java Application Software로 Apache log4j 1.2.x를 사용하여 구현되어진 Application 호출로 동작하고, Broadcast를 지원하여 접속된 사용자에게 동기화를 지원한다.
 * RCLog4j Client는 Eclipse plugin으로 배포되어 Agent와 JMX통신을 하며 Runtime환경에서 Log Category를 등록하고 Log Level을 조절할 수 있다.
=== 배경 ===
 * 인프라시스템 관리하던중 많은 운영개발자들이 운영시스템에 DEBUG성격의 Log를 INFO로 사용하는걸 발견하고
   개선하기위해 노력했지만 쉽게 개선되지 않았고, 단일시스템 LOG파일을 Size증가로 인해
   Log관리작업소요가 증가하고 Log분석시간 증가로 악순환이 발생함으로 LOG Level을 ERROR로 올렸지만
   개발자들은 DEBUG성격의 Log를 ERROR Level로 코드를 바꾸는 악순환발생으로
   개발자들이 DEBUG성격의 Log가 왜 꼭 필요한가를 확인해보니 대부분 특정시점 
   또는 운영에만 문제가 발생할 경우 이를 디버깅하기위해 사용함을 알 수 있었다.
=== 목적 ===
 * 개발자들이 운영에서 원하는 시점에 확인하고자하는 Log의 일부분을 Debug Level까지 확인가능 하도록 함으로써
   Code상에 Log Level을 본래의 성격에 맞게 사용하고, Log관리 및 분석이 용이한 시스템될 수 있도록 지원을 목적으로 한다.
 
=== 라이선스 ===
 * 이 소프트웨어는 집,회사등에서 무료로 자유롭게 사용할 수 있는 자유소프트웨어이다. 
 * 이 소프트웨어는 오픈소스(GPL3.0) 라이선스를 준수한다.
=== 설치방법 ===
 > RCLog4j Agent
   * Web Application(첨부 web.xml 참고)
     * rclog-agent_*.zip 파일의 압축을 해제한다.
     * 압축해제된 jar파일을 WEB-INF/lib 폴더로 복사한다.
     * log4j.xml을 WEB-INF/classes 폴더로 복사한다.
     * JMX 지원을 위한 WAS 기동시 Java환경변수를 추가한다.
       * -Dcom.sun.management.jmxremote.port=9999
       * -Dcom.sun.management.jmxremote.authenticate=false
       * -Dcom.sun.management.jmxremote.ssl=false
     * web.xml에 listener를 등록한다.
       ```
<listener>
   <listener-class>com.forif.rclog.agent.RCLogAgentListener</listener-class>
</listener> ```			
     * context-param을 추가할 경우 테스트용 log를 1초간격으로 발생시킨다.
       ```
<context-param>
   <param-name>rclog4j.test</param-name>
   <param-value>true</param-value>
</context-param> ```
     * test log category(debug,info,error)
       * com.forif.rclog.agent
       * com.forif.rclog
       * com.forif

   * Standard Application
     * rclog-agent_*.zip 파일의 압축을 해제한다.
     * 압축해제된 jar파일을 classpath에 추가한다.
     * log4j.xml을 classpath에 추가한다.
     * JMX 지원을 위한 Java환경변수를 추가한다.
       * -Dcom.sun.management.jmxremote.port=9999
       * -Dcom.sun.management.jmxremote.authenticate=false
       * -Dcom.sun.management.jmxremote.ssl=false
     * 테스트는 com.forif.rclog.test.RCLogTest 호출로 확인할 수 있다.(첨부 start.bat 참고)
     * main method에 코드삽입
       ```
    com.forif.rclog.agent.RCLogManager.start();  ```

 > RCLog4j Client
   * rclog-client_*.zip 파일을 압축을 해제 한다.
   * Eclipse : Help -> Install new software 메뉴에서 압축해제한 폴더를 사이트로 등록후 설치한다.
   * Group Items by category 항목해제
   * 설치완료 후 toolbar가 추가된 것을 확인되면 정상설치

=== 사용법 ===
* 설치된 Agent IP,Port를 입력하고 Connect버튼을 클릭한다.
![alt tag](http://dev.naver.com/wiki/rclog/pds/FrontPage/rclog1.JPG)
* Agent에 연결되면 RCLog4j로 등록된 Log category가 조회되고 설정된 정보에 대한 Log가 콘솔창에 기록된다.(최초 접속자라면 접속메세지만 볼수있고 등록된 정보가 존재하지 않는다.)
attachment:rclog2.JPG
* log level을 debug로 조정한다.
attachment:rclog3.JPG
* Save버튼을 클릭한다.
attachment:rclog4.JPG
* Agent로부터 변경메세지를 수신하고 Log가 변경된것을 확인할 수 있다.
attachment:rclog5.JPG
* 새로운 category를 추가한다.
attachment:rclog6.JPG
* Save버튼 클릭적용후 log가 변경되는것을 확인할 수 있다.
attachment:rclog7.JPG
* Delete버튼 클릭으로 category를 삭제한다.
attachment:rclog8.JPG
* Save버튼 클릭적용후 log가 변경되는것을 확인할 수 있다.
attachment:rclog9.JPG
* Release버튼을 클릭하면 Agent와 연결종료된다.
attachment:rclog10.JPG



=== 관련정보 링크 ===
 * [http://blog.naver.com/asdkf20 공식블로그] 
 * [http://dev.naver.com/projects/noti-j noti-J project]
 * [http://dev.naver.com/projects/s-cross Cross-Site Security] 
