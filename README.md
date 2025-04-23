# wt-ticket-tool-server

# Wissen Ticketing Tool – Server

This is the *backend* for the Wissen Ticketing Tool, built using *Spring Boot*. It powers the ticketing flow including user actions, comments, notifications, email alerts, attachments, and more.

---

## 🔧 Tech Stack

- Java 17+
- Spring Boot
- PostgreSQL
- Gradle
- Swagger UI
- Docker (optional for deployment)

---

## 🚀 Getting Started

### Prerequisites

- Java 17 or later
- Gradle
- PostgreSQL
- IDE (IntelliJ, VS Code, etc.)

### Setup Instructions

1. *Clone the Repository*
   ```bash
   git clone https://github.com/your-org/wissen-ticketing-tool-server.git
   cd wissen-ticketing-tool-server
  ### Features
  🎫 Ticket Management
Users can create, update, and view their tickets.

👤 Assignee & Transfer
Admin/Assignee can transfer tickets between departments.

💬 Comment System
Add, edit (within 15 mins), and retrieve comments on tickets.

📩 Email Notifications
Real-time email alerts on ticket creation, updates, and transfers.

🔔 In-app Notifications
Users receive in-app alerts for any ticket status or action.

📎 Attachment Support
Upload and view files related to tickets.

📅 Scheduled Tasks

Auto-assign open tickets after 15 mins.

Delete read notifications after 24 hours.

🔐 Authentication

Google Sign-In

Microsoft SSO (Under Development)

### Docker 

docker build -t wissen-ticketing-server .
docker run -p 8080:8080 wissen-ticketing-server
