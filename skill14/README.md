# SKILL 14 - User Authentication & Session Management using React
## KL University | FSAD Course

### Project Structure
```
skill14/
├── backend/    → Spring Boot Auth API
└── frontend/   → React App with session management
```

### Backend Setup
```bash
cd backend
mvn spring-boot:run
# Runs at http://localhost:8080
```

### Frontend Setup
```bash
cd frontend
npm install
npm start
# Runs at http://localhost:3000
```

### API Endpoints
| Method | Endpoint                  | Description          |
|--------|---------------------------|----------------------|
| POST   | /api/auth/register        | Register new user    |
| POST   | /api/auth/login           | Login & get session  |
| GET    | /api/auth/profile/{id}    | Get user profile     |

### Flow
1. Register → POST /api/auth/register
2. Login → POST /api/auth/login → stores userId & username in sessionStorage
3. Home → reads sessionStorage, redirects to /login if empty
4. Profile → fetches /api/auth/profile/{userId} using stored userId
5. Logout → clears sessionStorage → redirects to /login

### Notes
- sessionStorage is cleared when the browser tab is closed
- In production, use JWT tokens (see Skill 15) instead of plain session storage
- Passwords are stored in plaintext here for demo — use BCrypt in production
