# 2020 책리뷰 API
<h2>※프로젝트 완료</h2>
<h3>공헌한 내용</h3>
<ul>
  <li>참여도: 개인프로젝트</li>
</ul>
<h3>사용 기술 및 환경</h3>
<h4>Spring boot, Gradle, JPA, Docker, Mysql, Jenkins,Java8, AWS</h4>

<h3>API</h3>
주소 http://13.209.64.185:5000</br>
<h4>GET</h4> 
/api/v2/posts <br/>
/api/v2/like <br/>

<h4>POST</h4>
/api/v2/posts <br/>
/api/v2/rating <br/>
/api/v2/users <br/>

<h4>PATCH</h4>
/api/v2/posts/{id} <br/>
/api/v2/rating/{id} <br/>
/api/v2/users/{id} <br/>

<h4>DELETE</h4>
/api/v2/like/{id} <br/>
<img src="https://user-images.githubusercontent.com/35258834/99074925-f907f780-25fb-11eb-893d-98139e9a568b.PNG" align="left">

<h3>Git-flow 방법 사용</h3>
참고 https://woowabros.github.io/experience/2017/10/30/baemin-mobile-git-branch-strategy.html <br/>
master : 제품으로 출시될 수 있는 브랜치<br/>
develop : 다음 출시 버전을 개발하는 브랜치<br/>
feature : 기능을 개발하는 브랜치 <br/>

<h3>CI</h3>
Jenkins : 로컬환경에서 사용(feat. Docker)<br/>
master 권한으로 업로드시 자동 build<br/>

<h3>CD</h3>
Docker 이미지를 제작하여 배포합니다.<br/>
CI 서버에서 빌드 완료시 Shell script가 작동하여 빌드된 이미지가 docker hub에 저장됩니다.<br/>
Push 완료시 EC2 서버에서 docker hub에 올라간 이미지를 받아 실행시킵니다.<br/>

<h4>docker hub</h4>
https://hub.docker.com/repository/docker/maie421/book_api<br/>

<h3>프론트 github</h3>
https://github.com/maie421/FLYBOOK <br/>
<h3>화면</h3>
<img src="https://user-images.githubusercontent.com/35258834/99064535-69f1e400-25e9-11eb-927a-396a337f6274.jpg" width="30%" align="left">
