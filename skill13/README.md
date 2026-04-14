# SKILL 13 - Deployment of Full-Stack Application (Spring Boot + React)
## KL University | FSAD Course

### Project Structure
```
skill13/
├── backend/          → Spring Boot (packaged as JAR)
├── frontend/         → React App (built for production)
├── nginx.conf        → Nginx reverse proxy config
├── deploy.sh         → One-click deployment script
└── README.md
```

---

## Deployment Options

### Option A: Serve React from Spring Boot JAR (Recommended for simplicity)

1. **Build React:**
   ```bash
   cd frontend
   npm install && npm run build
   ```

2. **Copy build output to Spring Boot static folder:**
   ```bash
   cp -r frontend/build/* backend/src/main/resources/static/
   ```

3. **Package Spring Boot:**
   ```bash
   cd backend
   mvn clean package -DskipTests
   ```

4. **Run the JAR:**
   ```bash
   java -jar target/student-app-deploy-1.0.0.jar
   ```

5. Open http://localhost:8080 → Both frontend & backend served from one JAR ✅

---

### Option B: Nginx as Reverse Proxy

1. Build React and copy `build/` to `/var/www/student-app/`
2. Run Spring Boot JAR on port 8080
3. Copy `nginx.conf` to `/etc/nginx/sites-available/student-app`
4. Enable and reload: `sudo nginx -t && sudo systemctl reload nginx`
5. Access via http://localhost (port 80)

---

### Or: Use the deploy script
```bash
chmod +x deploy.sh
./deploy.sh
```

---

## Environment Variables

| Variable              | Default               | Description            |
|-----------------------|-----------------------|------------------------|
| SERVER_PORT           | 8080                  | Backend port           |
| REACT_APP_API_BASE_URL| http://localhost:8080 | API URL for React build|

Override at runtime:
```bash
SERVER_PORT=9090 java -jar target/student-app-deploy-1.0.0.jar
```

---

## API Endpoints
| Method | URL              | Description       |
|--------|------------------|-------------------|
| POST   | /students        | Add student       |
| GET    | /students        | Get all students  |
| PUT    | /students/{id}   | Update student    |
| DELETE | /students/{id}   | Delete student    |
