#!/bin/bash
# =============================================
# Deployment Script - Skill 13
# KL University FSAD
# =============================================

echo "=========================================="
echo " FSAD Skill 13 - Full-Stack Deployment"
echo "=========================================="

# Step 1: Build React production build
echo ""
echo "[1/4] Building React production build..."
cd frontend
npm install
npm run build
echo "React build complete. Output in frontend/build/"

# Step 2: Copy React build to Spring Boot static folder
echo ""
echo "[2/4] Copying React build to Spring Boot static resources..."
mkdir -p ../backend/src/main/resources/static
cp -r build/* ../backend/src/main/resources/static/
echo "React build copied to backend/src/main/resources/static/"

# Step 3: Package Spring Boot as JAR
echo ""
echo "[3/4] Packaging Spring Boot as executable JAR..."
cd ../backend
mvn clean package -DskipTests
echo "JAR created at: backend/target/student-app-deploy-1.0.0.jar"

# Step 4: Run the JAR
echo ""
echo "[4/4] Starting Spring Boot application..."
echo "Access the app at: http://localhost:8080"
echo "API endpoints at: http://localhost:8080/students"
echo ""
java -jar target/student-app-deploy-1.0.0.jar
