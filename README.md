# **CGS Habit Certification Automation Project** 🚀
<p align="center">
  <img src="https://github.com/user-attachments/assets/90c336e6-ff4a-472b-ac60-5879eb95d7d2">
</p>

## 📌 Project Overview  
This project was developed to automate the certification process for the **CGS (Habit-Making Group)** on Naver Cafe.  
It streamlines the repetitive tasks of checking certification counts and uploading photos, improving user convenience.

## 🎯 Purpose  
This is more than just an automation tool—it was also a **learning project to explore Android development**.  
The following technologies were applied in real-world scenarios:  

✅ **Fragment-based UI structure**  
✅ **Data management using Room Database**  
✅ **Network communication with Retrofit2**  
✅ **Simple local data storage using SharedPreferences**  

## 📅 Development Timeline  
- **Initial Development:** June 6 – June 9, 2022  
- **Refactoring & Bug Fixes:** Updated to support latest Android Studio (Ladybug) and Gradle 8.9+

## ✨ Key Features  
✔ **Naver OAuth-based Login Integration**  
✔ **Automatic Habit Certification Uploads**  
✔ **Auto Check & Management of Certification Count**  
✔ **Local Data Storage using Room**  
✔ **Modern UI with ViewPager2 and RecyclerView**  

## 🚨 Known Issues & Areas for Improvement  
⚠️ No logic to verify whether the Naver account is a member of the target cafe  
⚠️ Possible crash if the upload button is pressed multiple times rapidly  
⚠️ Some unhandled exceptions due to minimal error-handling (built for light usage)

## 🛠 Tech Stack  
- **Language:** Kotlin  
- **Framework:** Android SDK  
- **Networking:** Retrofit2  
- **Database:** Room  
- **Image Handling:** Glide  
- **Build System:** Gradle 8.9+  

## ⚙️ Project Structure  
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

## 📸 **UI Screens & Roles**
| **Screen**               | **Description** |
|--------------------------|------------------|
| **Login Screen** (`activity_login.xml`) | Naver login and token clearing |
| **Main Screen** (`activity_main.xml`) | Tabs for Upload / History / Settings |
| **Splash Screen** (`activity_flash.xml`) | Auto-login and redirection |
| **Edit User Info** (`activity_edit_user_info.xml`) | Set habit goal |
| **History Screen** (`frament_history.xml`) | View uploaded certification history |
| **Write Screen** (`fragment_write.xml`) | Upload habit photo and text |
| **Settings Screen** (`fragment_settings.xml`) | Modify user info |
| **Change Count Dialog** (`dialog_change_cnt.xml`) | Manually edit certification count |

## 🔧 How to Run  
### 1️⃣ **Required Setup**
Add your Naver API credentials to the `local.properties` file (Do NOT upload this to Git!)
```properties
NAVER_CLIENT_ID=your_client_id
NAVER_CLIENT_SECRET=your_client_secret
NAVER_CLIENT_NAME=your_client_name
```
  
### 2️⃣ **Build Configuration**
- Use the **latest version of Android Studio (Ladybug)**  
- **Gradle 8.9+ is required**  
- Ensure your `minSdkVersion` and `targetSdkVersion` in `build.gradle` match modern standards  

### 3️⃣ **Run the App**
Open the project in Android Studio and click the `Run ▶` button!  

## ⚠️ Caution  
🔹 The initial repository included commits with sensitive data (IDs, passwords) due to misuse of Git  
🔹 To prevent data leakage, a **new public repository** was created  
🔹 Always use `.gitignore` to exclude API keys and sensitive credentials!  

## 📜 License  
MIT License  
Feel free to use and modify this project, but be sure to follow personal data protection guidelines and comply with Naver API usage policies.
