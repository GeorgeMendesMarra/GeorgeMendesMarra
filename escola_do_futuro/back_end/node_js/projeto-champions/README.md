```markdown
# ⚽ Champions League API with Node.js and Express

## 📝 Description

The **Champions League API** is a back-end application built with **Node.js** and **Express** that provides endpoints to manage information about football players and clubs. The API implements a **complete CRUD** for players and allows listing registered clubs. Data is stored in an **in-memory database** (players) and a **JSON file** (clubs).

---

## 📦 Installation

1. Clone the repository:
```bash
git clone https://github.com/georgemendesmarra/projeto-champions.git
cd projeto-champions
```

2. Install dependencies:
```bash
npm install
```

---

## 🚀 Running the Server

Start the server in development mode:
```bash
npm run start:dev
```

The API will be available at:
```
http://localhost:3333/api
```

---

## 🔀 API Routes

### 🏠 Welcome Route

| Method | Endpoint    | Description                    |
|--------|-------------|--------------------------------|
| GET    | `/api`      | Returns a welcome message      |

**Response example:**
```json
{
  "message": "Bem vindo(a) à API da Champions League."
}
```

---

### 👤 Players Routes

| Method   | Endpoint               | Description                          |
|----------|------------------------|--------------------------------------|
| GET      | `/api/players`         | Lists all players                    |
| GET      | `/api/players/:id`     | Retrieves a player by ID             |
| POST     | `/api/players`         | Creates a new player                 |
| PATCH    | `/api/players/:id`     | Partially updates a player           |
| DELETE   | `/api/players/:id`     | Deletes a player                     |

#### Response example (GET `/api/players`):
```json
[
  {
    "id": 1,
    "name": "Lionel Messi",
    "club": "Inter Miami",
    "nationality": "Argentina",
    "position": "Forward",
    "statistics": {
      "Overall": 93,
      "Pace": 80,
      "Shooting": 92,
      "Passing": 91,
      "Dribbling": 95,
      "Defending": 34,
      "Physical": 65
    }
  },
  {
    "id": 2,
    "name": "Cristiano Ronaldo",
    "club": "Al Nassr",
    "nationality": "Portugal",
    "position": "Forward",
    "statistics": {
      "Overall": 88,
      "Pace": 81,
      "Shooting": 92,
      "Passing": 78,
      "Dribbling": 84,
      "Defending": 34,
      "Physical": 75
    }
  }
]
```

#### Request body (POST `/api/players`):
```json
{
  "id": 0,
  "name": "string",
  "club": "string",
  "nationality": "string",
  "position": "string",
  "statistics": {
    "Overall": 0,
    "Pace": 0,
    "Shooting": 0,
    "Passing": 0,
    "Dribbling": 0,
    "Defending": 0,
    "Physical": 0
  }
}
```

#### Allowed fields for update (PATCH):
```json
{
  "Overall": 0,
  "Pace": 0,
  "Shooting": 0,
  "Passing": 0,
  "Dribbling": 0,
  "Defending": 0,
  "Physical": 0
}
```

---

### 🏟️ Clubs Routes

| Method | Endpoint       | Description            |
|--------|----------------|------------------------|
| GET    | `/api/clubs`   | Lists all clubs        |

**Response example:**
```json
[
  { "id": 1, "name": "Real Madrid" },
  { "id": 2, "name": "Barcelona" }
]
```

---

## 🗃️ Data Models

### 🧑‍🤝‍🧑 Player (`PlayerModel`)
```typescript
interface PlayerModel {
  id: number;
  name: string;
  club: string;
  nationality: string;
  position: string;
  statistics: StatisticsModel;
}
```

### 📊 Statistics (`StatisticsModel`)
```typescript
interface StatisticsModel {
  Overall: number;
  Pace: number;
  Shooting: number;
  Passing: number;
  Dribbling: number;
  Defending: number;
  Physical: number;
}
```

### 🏅 Club (`ClubModel`)
```typescript
interface ClubModel {
  id: number;
  name: string;
}
```

### 🔄 Standard HTTP Response (`HttpResponse`)
```typescript
interface HttpResponse {
  statusCode: number;
  body: any;
}
```

---

## 🏗️ Application Architecture

The application follows the **separation of concerns** principle, organized into the following layers:

### ⚙️ Services (`/services`)
Contain the **business logic** of the application.
- `players-service.ts`: Business rules for players (validations, calculations, etc.)
- `clubs-service.ts`: Business rules for clubs

### 📂 Repositories (`/repositories`)
Responsible for **data access and persistence**.
- `players-repository.ts`: In-memory database for players
- `clubs-repository.ts`: Reads club data from a JSON file

### 🕹️ Controllers (`/controllers`)
Handle HTTP requests and orchestrate the services.
- `players-controller.ts`: Player-related endpoints
- `club-controller.ts`: Club-related endpoints
- `welcome-controller.ts`: Welcome endpoint

### 🧰 Utilities (`/utils`)
Reusable helper functions.
- `http-helper.ts`: Standardizes HTTP responses (status and body)

---

## 📁 Suggested Folder Structure

```
src/
├── controllers/
│   ├── players-controller.ts
│   ├── club-controller.ts
│   └── welcome-controller.ts
├── services/
│   ├── players-service.ts
│   └── clubs-service.ts
├── repositories/
│   ├── players-repository.ts
│   └── clubs-repository.ts
├── models/
│   ├── PlayerModel.ts
│   ├── StatisticsModel.ts
│   └── ClubModel.ts
├── utils/
│   └── http-helper.ts
└── server.ts
```

---

## 🛠️ Tech Stack

| Technology | Purpose                          |
|------------|----------------------------------|
| Node.js    | JavaScript runtime               |
| Express    | Web framework for Node.js        |
| TypeScript | Static typing for JavaScript     |
| Nodemon    | Auto-restart during development  |

---

## 📄 License

This project is available for study and adaptation. Refer to the repository's license file for more information.

---

## 👨‍💻 Author

Developed by [georgemendesmarra](https://github.com/georgemendesmarra)

---

> ⚽ *"The Champions League is where legends are made."*
```
