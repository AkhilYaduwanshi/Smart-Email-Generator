# 📧 Smart Email Generator

🚀 **[Live Demo](https://smart-email-generator-sigma.vercel.app/)**

An AI-powered email generator built with **Spring Boot, React, and Gemini API**.

Generate clear, well-structured emails in different tones from a simple description.

## ✨ Features

- 🤖 AI-powered email generation using Gemini API
- 🎯 Professional, Friendly, and Casual tones
- 📝 Separate Subject and Email Body generation
- 📋 Copy generated email
- 🔄 Regenerate email
- ✅ Input validation
- ⚠️ Global exception handling
- 🌐 React frontend with Spring Boot REST API
- 📱 Responsive UI
- 🔐 API key secured using environment variables

## 🛠️ Tech Stack

### Backend

- Java
- Spring Boot
- Spring Web
- WebClient
- Maven
- Lombok
- Jackson

### Frontend

- React
- Vite
- Axios
- JavaScript
- CSS

### AI

- Google Gemini API

## 🏗️ Architecture

```text
React + Vite
     |
     | POST /generate-email
     v
EmailController
     |
     v
EmailService
     |
     v
GeminiClient
     |
     v
Gemini API
     |
     v
Subject + Email Body
```

## 📂 Project Structure

```text
Smart-Email-Generator/
│
├── frontend/
│   ├── src/
│   │   ├── App.jsx
│   │   ├── App.css
│   │   └── main.jsx
│   ├── package.json
│   └── vite.config.js
│
├── src/
│   └── main/
│       ├── java/com/chatbot/gemini/
│       │   ├── client/
│       │   ├── config/
│       │   ├── controller/
│       │   ├── exception/
│       │   ├── model/
│       │   └── service/
│       │
│       └── resources/
│           └── application.properties
│
├── pom.xml
├── .gitignore
└── README.md
```

## ⚙️ Setup

### 1. Clone the Repository

```bash
git clone https://github.com/AkhilYaduwanshi/Smart-Email-Generator.git
cd Smart-Email-Generator
```

### 2. Configure Gemini API Key

The application requires a Gemini API key.

Set the following environment variable:

```text
GEMINI_API_KEY=your_api_key_here
```

The application reads the key using:

```properties
gemini.api.key=${GEMINI_API_KEY}
```

> Never commit your actual API key to GitHub.

### 3. Run the Backend

From the project root:

```powershell
.\mvnw.cmd spring-boot:run
```

The backend will start on:

```text
http://localhost:8081
```

### 4. Run the Frontend

Open a second terminal:

```bash
cd frontend
npm install
npm run dev
```

The frontend will start on:

```text
http://localhost:5173
```

## 🔌 API

### Generate Email

**POST**

```text
/generate-email
```

### Request

```json
{
  "message": "I want to ask my manager for leave tomorrow.",
  "tone": "Professional"
}
```

### Response

```json
{
  "subject": "Leave Request for Tomorrow",
  "body": "Dear Manager,\n\nI am writing to request..."
}
```

## 🎨 Supported Tones

| Tone | Description |
|---|---|
| Professional | Formal workplace communication |
| Friendly | Warm and approachable communication |
| Casual | Relaxed everyday communication |

## 🔐 Security

The Gemini API key is loaded through the `GEMINI_API_KEY` environment variable.

Never commit real API keys, passwords, or other secrets to GitHub.

## 🚀 Future Improvements

- Email history
- User authentication
- Multiple email templates
- More tone and language options
- Multi-language email generation
- Cloud deployment
- Advanced prompt customization

## 👨‍💻 Author

**Akhil Yaduwanshi**

---

⭐ Built with Spring Boot, React, and Gemini AI.
