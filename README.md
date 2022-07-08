# 화면 별 기능
## LoginActivity
- 닉네임과 이메일을 통해 로그인
- 닉네임과 이메일에 규칙 적용
- 화면을 전환해도 EditText의 텍스트가 보존되도록 ViewModel 사용
## MainActivity
- BottomNavigation, Navigation Rail 그리고 Navigation Drawer를 이용하여 프래그먼트 전환
- ViewModel에 LoginActivity로부터 넘어 온 유저 정보 저장
- ViewModel에 Drawer와 Naviagtion의 현재 상태를 저장하여 화면이 전환되어도 화면 유지
## MailFragment
- Drawer의 메뉴가 바뀔때마다 MailFragment의 RecyclerView 갱신
- 기본 메일이 아닌 다른 메뉴에서 뒤로가기를 누를시 onBackPressedDispatcher를 통해 기본 메일로 돌아가게 설정
## SettingFragment
- ActivityViewModel을 공유받아 닉네임과 이메일 표기
- 메인 화면은 MailFragment이므로 뒤로가기를 누를시 MailFragment로 돌아가도록 스택 설정
-------------
# 공통 기능
- 다크모드 적용
- 태블릿 사이즈 대응
