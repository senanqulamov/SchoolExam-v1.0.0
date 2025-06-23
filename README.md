# 🎓 SchoolExam - Exam Management System `v1.0.0 (Beta)`

<p align="center">
  <img src="https://assets.ppy.sh/topic-covers/6320/c7490f9d8cba26dcd7fb34f08a211156e311d57ffd470edff68a92832d3a1fd8.gif" 
       alt="Animated Banner" 
       width="35%"
    />
</p>

> A lightweight and modular `exam management system` designed for schools and educational institutions.
This version includes features for creating exams, managing students and results, and generating performance reports.

![License](https://img.shields.io/badge/license-MIT-blue.svg)

---

### ⚠️ Beta Notice:
* This is a console-only beta version using `Scanner`, `System.out` and Java 17 features.
  `GUI`, backend integrations, and web interfaces are not included **(yet)**.

---

> `copyright ©2025 [Senan Qulamov]`

---

## 📅 Version History

> **`(Beta)`** `v.1.0 Alpha` - `2025-06-22`
- **`v1.0.0` Console Prototype Init**
  - Basic subject and score input via `Scanner`
  - Simple grade calculation logic with `System.out.println()`
  - Manual test cases, no persistence or storage
- **`v2.0.0` Modular Structure**:
    - Split logic into packages like `Helpers`, `Models`, and `Methods`
    - Introduced color-coded output using `ANSI` escape sequences
    - Added helper utilities for `printing`, `debugging` and `formatting`
- **`v3.0.0` Enhanced Output**:
    - Added `loading` animations and `debug toggle` support
    - Improved printing methods: `formatted`, `same-line`, `colored`
    - `Timestamps` added to logs for better traceability
- **`v4.0.0` Simulated User Login Flow**:
    - Username prompt with animated login simulation
    - Clear console output using `ANSI codes` (limited to supported terminals)
    - Improved user feedback with personalized `welcome messages`
- **`v5.0.0` First Stable Console Version**:
    - Fully modularized `console app` with reusable helper classes
    - Polished input/output experience with `colors`, `sections`, and `loading states`
    - Ready for `CLI` demos, code reviews, and future `GUI layering`


> `(Planned Features)` `v.1.0 Alpha` - `2025-07-?`
- **`v1.0.0`**: GUI version using JavaFX or Swing
- **`v1.1.0`**: Enhanced reporting features and user interface improvements
- **`v1.2.0`**: Integration with external learning management systems (LMS)
- **`v2.0.0`**: Mobile app support for exam management on the go
- **`v3.0.0`**: Major overhaul with AI-driven analytics and personalized learning paths
- **`v4.0.0`**: Advanced security features and data encryption

---

## ✨ Key Features

### Exam Management
- 📝 Create and schedule exams with customizable subjects
- ⏱️ Manage time slots and exam durations
- 🏷️ Categorize exams by term, grade, or subject

### Student Administration
- 👥 Bulk import student data
- 🔗 Assign students to specific exams
- 📊 Track individual exam registrations

### Results Processing
- ✅ Automated grading calculations
- 📈 Performance analytics dashboard
- 🏆 Identify top performers and areas for improvement

### Reporting
- 📄 Generate printable scorecards and transcripts
- 📊 Create institutional performance reports
- 📤 Export data in multiple formats (PDF, CSV, Excel)

### Access Control
- 🔐 Role-based permissions (Admin, Teacher, Viewer)
- 👩‍💻 Intuitive dashboard for each user type
- 📱 Responsive design for all devices

---

## 🛠 Technology Stack `future (Alpha v.2.0.0)`

| Component       | Technology                          |
|-----------------|-------------------------------------|
| **Backend**     | Java 17 (Spring Boot 3.1)           |
| **Frontend**    | React 18 + Next.js 14               |
| **Database**    | MySQL 8 (with Flyway migrations)    |
| **Auth**        | JWT with OAuth 2.0 support          |
| **CI/CD**       | GitHub Actions                      |
| **Testing**     | JUnit 5, Mockito, React Testing Lib |

---

## 🚀 Quick Start

### Prerequisites
- Java 17 JDK
- Node.js 18+
- MySQL 8.0+

### Installation
```bash
# Clone repository
git clone https://github.com/senanqulamov/SchoolExam-v1.0.0.git
cd schoolexam

# Backend setup
cd backend
./mvnw clean install
java -jar target/schoolexam-1.0.0.jar

# Frontend setup (in another terminal)
cd ../frontend
npm install
npm run dev
```

