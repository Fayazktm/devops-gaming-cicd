# \# 🎮 Gaming DevOps Hub

# 

# A containerized Java gaming application deployed through an automated CI/CD pipeline using GitHub, Jenkins, Maven, Docker, Docker Hub, and AWS EC2.

# 

# \## 🚀 Project Overview

# 

# This project demonstrates a complete DevOps CI/CD workflow where application source code is maintained in GitHub and automatically tested, packaged, containerized, published, and deployed to an AWS EC2 server.

# 

# \## 🏗️ CI/CD Architecture

# 

# Developer

# &#x20;  ↓

# GitHub

# &#x20;  ↓

# Jenkins

# &#x20;  ↓

# Maven Test

# &#x20;  ↓

# Maven Package

# &#x20;  ↓

# Docker Build

# &#x20;  ↓

# Docker Image

# &#x20;  ↓

# Docker Hub

# &#x20;  ↓

# AWS EC2

# &#x20;  ↓

# Docker Container

# &#x20;  ↓

# Health Check

# &#x20;  ↓

# Application Running

# 

# \## 🛠️ Technologies Used

# 

# \- AWS EC2

# \- Ubuntu Linux

# \- Git

# \- GitHub

# \- Jenkins

# \- Java 21

# \- Maven

# \- Docker

# \- Docker Hub

# \- Bash

# \- CI/CD

# 

# \## 🔄 Jenkins Pipeline

# 

# The Jenkins pipeline performs the following stages:

# 

# 1\. Checkout source code from GitHub

# 2\. Verify Git installation

# 3\. Verify Java installation

# 4\. Verify Maven installation

# 5\. Verify Docker installation

# 6\. Run Maven unit tests

# 7\. Package the Java application

# 8\. Build Docker image

# 9\. Run and validate the Docker container

# 10\. Tag Docker image as `latest`

# 11\. Push versioned image to Docker Hub

# 12\. Push `latest` image to Docker Hub

# 13\. Deploy the application on AWS EC2

# 14\. Perform application health check

# 

# \## 🐳 Docker

# 

# Docker is used to package the Java application together with its runtime environment.

# 

# Docker image:

# 

# `fayzhub/gaming-app`

# 

# Images are versioned using the Jenkins build number.

# 

# Example:

# 

# `fayzhub/gaming-app:28`

# 

# The application is deployed using:

# 

# `8081:8080`

# 

# \## ☁️ AWS Deployment

# 

# The application is deployed on an Ubuntu-based AWS EC2 instance.

# 

# The Docker container runs the Java application inside the EC2 server.

# 

# \## ❤️ Health Check

# 

# After deployment, Jenkins verifies that the application is responding through:

# 

# `http://localhost:8081/`

# 

# The pipeline retries the request and marks the build as failed if the application does not become available.

# 

# \## ✅ Final Pipeline Result

# 

# The final Jenkins pipeline completed successfully.

# 

# Example:

# 

# `Deployed fayzhub/gaming-app:28 successfully.`

# 

# `Finished: SUCCESS`

# 

# \## 📸 Screenshots

# 

# \### Jenkins Pipeline

# 

# !\[Jenkins Pipeline](screenshots/Jenkins-Pipeline-Success.png)

# 

# \### Docker Deployment

# 

# !\[Docker Deployment](screenshots/Docker-Deployment.png)

# 

# \### Application Running

# 

# !\[Application Running](screenshots/Application-Running.png)

# 

# \### Jenkins Dashboard

# 

# !\[Jenkins Dashboard](screenshots/Jenkins-Dashboard.png)

# 

# \### AWS EC2

# 

# !\[AWS EC2](screenshots/EC2-Instance.png)

# 

# \## 🔗 Project Repository

# 

# GitHub:

# 

# https://github.com/FayazKtm/devops-gaming-cicd

# 

# \## 👨‍💻 Author

# 

# Fayaz Ahamed Mohideen

# 

# Cloud \& DevOps Engineer

