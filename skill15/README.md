# SKILL 15 - JWT-Based Authentication & Role Authorization
## KL University | FSAD Course

### Project Structure
```
skill15/
└── backend/    → Spring Boot + Spring Security + JWT
```

### Run
```bash
cd backend
mvn spring-boot:run
```

### Default Seeded Users
| Username  | Password     | Role          |
|-----------|-------------|---------------|
| admin     | admin123    | ROLE_ADMIN    |
| employee  | employee123 | ROLE_EMPLOYEE |

---

## Testing with Postman

### Step 1 – Login (get JWT token)
```
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```
Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "admin",
  "role": "ROLE_ADMIN",
  "message": "Login successful!"
}
```

### Step 2 – Use token in secured requests
Add header:
```
Authorization: Bearer <token>
```

### Secured Endpoints
| Method | Endpoint               | Required Role           |
|--------|------------------------|-------------------------|
| POST   | /api/auth/login        | Public                  |
| POST   | /api/auth/register     | Public                  |
| POST   | /admin/add             | ROLE_ADMIN only         |
| DELETE | /admin/delete/{id}     | ROLE_ADMIN only         |
| GET    | /admin/employees       | ROLE_ADMIN only         |
| GET    | /employee/profile      | ROLE_EMPLOYEE or ADMIN  |
| GET    | /employee/dashboard    | ROLE_EMPLOYEE or ADMIN  |

### Test Cases
1. Login as admin → call /admin/add → should succeed ✅
2. Login as employee → call /admin/add → should get 403 Forbidden ✅
3. Call any secured endpoint without token → should get 403 ✅
4. Use expired/invalid token → should get 403 ✅
