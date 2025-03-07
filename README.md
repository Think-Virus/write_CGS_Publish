# **CGS 습관 인증 자동화 프로젝트** 🚀
<p align="center">
  <img src="https://github.com/user-attachments/assets/90c336e6-ff4a-472b-ac60-5879eb95d7d2">
</p>

## 📌 프로젝트 소개  
이 프로젝트는 **CGS(습관만들기단체)** 네이버 카페 인증을 자동화하기 위해 개발되었습니다.  
매번 인증 횟수를 확인하고, 사진을 업로드하는 번거로운 과정을 자동화하여 사용자의 편의성을 높였습니다.

## 🎯 개발 목적  
이 프로젝트는 단순한 자동화 도구를 넘어서, **Android 개발 기술을 익히기 위한 학습 프로젝트**로 진행되었습니다.  
특히 다음 기술들을 실전에서 활용하고자 했습니다.  

✅ **Fragment 기반의 UI 구성**  
✅ **Room Database를 활용한 데이터 저장 및 관리**  
✅ **Retrofit2를 이용한 네트워크 통신 및 API 연동**  
✅ **SharedPreferences를 활용한 간단한 로컬 데이터 저장**  

## 📅 개발 기간  
- **초기 개발:** 2022년 6월 6일 ~ 6월 9일  
- **리팩토링 및 오류 수정:** 최신 Android Studio(Ladybug) 및 Gradle 8.9+ 대응  

## ✨ 주요 기능  
✔ **네이버 로그인 연동** (OAuth 기반)  
✔ **습관 인증 자동 업로드**  
✔ **자동 인증 횟수 체크 및 관리**  
✔ **로컬 데이터 저장 (Room Database 사용)**  
✔ **UI 구성 (ViewPager2, RecyclerView 적용)**  

## 🚨 현재 개선이 필요한 사항  
⚠️ **해당 카페에 회원가입된 네이버 계정인지 확인하는 로직 없음**  
⚠️ **업로드 버튼 연타 시 크래시 발생 가능**  
⚠️ **일부 크래시 현상 존재 (가볍게 사용하기 위해 만든 것이기에 일부 예외처리 미흡)**  

## 🛠 기술 스택  
- **언어:** Kotlin  
- **프레임워크:** Android SDK  
- **네트워킹:** Retrofit2  
- **데이터 저장:** Room Database  
- **이미지 처리:** Glide  
- **빌드 시스템:** Gradle 8.9+  

## ⚙️ 프로젝트 구조  
```
/app/src/main/java/com/example/writepost_cgs
│── activities
│   ├── LoginActivity.kt
│   ├── MainActivity.kt
│   └── FlashActivity.kt
│
│── fragments
│   ├── EditUserInfoFragment.kt
│   ├── HistotyFragment.kt
│   ├── SettingsFragment.kt
│   └── WriteFragment.kt
│
│── database
│   ├── DB.kt
│   └── Converter.kt
│
│── network
│   └── RetrofitService.kt
│
│── utils
│   ├── MyApplication.kt
│   └── SharedPreferenceManager.kt
│
└── ui
    ├── activity_main.xml
    ├── activity_login.xml
    ├── activity_flash.xml
    ├── activity_edit_user_info.xml
    ├── fragment_edit_user_info.xml
    ├── fragment_settings.xml
    ├── fragment_write.xml
    ├── frament_history.xml
    ├── dialog_change_cnt.xml
    └── item_post_history.xml
```

## 📸 **화면 구성 & 역할**
| **화면**             | **설명** |
|----------------------|---------|
| **로그인 화면** (`activity_login.xml`) | 네이버 로그인 및 토큰 삭제 |
| **메인 화면** (`activity_main.xml`) | 3개의 탭 (업로드 / 기록 / 설정) |
| **플래시 화면** (`activity_flash.xml`) | 자동 로그인 확인 후 이동 |
| **사용자 정보 수정** (`activity_edit_user_info.xml`) | 습관 인증 목표 설정 |
| **인증 기록 화면** (`frament_history.xml`) | 업로드한 인증 기록 확인 |
| **글쓰기 화면** (`fragment_write.xml`) | 습관 인증 사진 + 내용 업로드 |
| **설정 화면** (`fragment_settings.xml`) | 사용자 정보 변경 |
| **인증 횟수 변경 다이얼로그** (`dialog_change_cnt.xml`) | 인증 횟수 수동 수정 |

## 🔧 실행 방법  
### 1️⃣ **필수 설정**
- `local.properties` 파일에 네이버 API 키 추가 (Git에 업로드 금지!)
```properties
NAVER_CLIENT_ID=your_client_id
NAVER_CLIENT_SECRET=your_client_secret
NAVER_CLIENT_NAME=your_client_name
```
  
### 2️⃣ **빌드 환경 설정**
- **Android Studio 최신 버전 (Ladybug) 사용**
- **Gradle 8.9 이상 필수**
- `build.gradle`에서 `minSdkVersion` 및 `targetSdkVersion`을 최신 버전에 맞게 조정  

### 3️⃣ **실행**
- `Android Studio`에서 프로젝트를 열고 `Run ▶` 버튼 클릭!  

## ⚠️ 주의 사항  
🔹 초기 개발 당시 Git을 잘못 사용하여 개인 정보(아이디, 비밀번호)가 포함된 상태로 커밋된 이력이 있음  
🔹 이를 방지하기 위해 **새로운 Public Repository**를 생성하여 업로드  
🔹 API 키 및 민감한 정보는 `.gitignore` 설정 필수!  

## 📜 라이선스  
MIT License  
자유롭게 사용하되, 개인 정보 보호 및 네이버 API 이용 약관을 준수하세요.  
