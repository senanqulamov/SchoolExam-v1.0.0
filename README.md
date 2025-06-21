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

### Reminder:
* this is a `beta` version, so expect some bugs and missing features.

---

> `copyright ©2025 [Senan Qulamov]`

---

## 📅 Version History

- **v1.1.0**: Enhanced reporting features and user interface improvements
- **v1.2.0**: Integration with external learning management systems (LMS)
- **v1.3.0**: Mobile app support for exam management on the go
- **v2.0.0**: Major overhaul with AI-driven analytics and personalized learning paths
- **v2.1.0**: Advanced security features and data encryption

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

## 🛠 Technology Stack

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

