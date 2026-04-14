# SKILL 16 - API Documentation using Swagger/OpenAPI
## KL University | FSAD Course

### Project Structure
```
skill16/
├── backend/    → Spring Boot + SpringDoc OpenAPI
└── frontend/   → React App (same CRUD as Skill 12)
```

### Run Backend
```bash
cd backend
mvn spring-boot:run
```

### Run Frontend
```bash
cd frontend
npm install && npm start
```

---

## Swagger UI Access

| URL | Description |
|-----|-------------|
| http://localhost:8080/swagger-ui.html     | Swagger UI (interactive docs) |
| http://localhost:8080/swagger-ui/index.html | Alternative URL |
| http://localhost:8080/api-docs            | Raw OpenAPI JSON spec |

---

## API Endpoints (documented in Swagger)

| Method | Endpoint           | Description          | Auth |
|--------|--------------------|----------------------|------|
| POST   | /students          | Add new student      | None |
| GET    | /students          | Get all students     | None |
| GET    | /students/{id}     | Get student by ID    | None |
| PUT    | /students/{id}     | Update student       | None |
| DELETE | /students/{id}     | Delete student       | None |

---

## How to Test via Swagger UI

1. Open http://localhost:8080/swagger-ui.html
2. Expand any endpoint (e.g., POST /students)
3. Click **"Try it out"**
4. Fill in the request body:
   ```json
   {
     "name": "Test Student",
     "email": "test@klu.ac.in",
     "course": "B.Tech CSE"
   }
   ```
5. Click **"Execute"** and observe the response

### Test Cases to Verify
- Add a student → 201 Created ✅
- Get all students → 200 OK with list ✅
- Get by valid ID → 200 OK ✅
- Get by invalid ID (e.g., 999) → 404 Not Found with error JSON ✅
- Update student → 200 OK ✅
- Delete student → 200 OK ✅
- Add with empty name → 400 Bad Request ✅

---

## Sample Students (pre-loaded)
| Name         | Email               | Course       |
|--------------|---------------------|--------------|
| Ravi Kumar   | ravi@klu.ac.in      | B.Tech CSE   |
| Priya Sharma | priya@klu.ac.in     | B.Tech ECE   |
| Arun Reddy   | arun@klu.ac.in      | B.Tech IT    |
| Sneha Patel  | sneha@klu.ac.in     | B.Tech AI&DS |
| Kiran Babu   | kiran@klu.ac.in     | M.Tech CSE   |
