# Stage 1: Build Angular app
FROM node:18-alpine AS build
 
# Set working directory
WORKDIR /app
 
# Copy package.json and package-lock.json
COPY package*.json ./
 
# Install dependencies
RUN npm ci
 
# Copy the rest of the application code
COPY . .
 
# Build the Angular app for production (for later use in the production stage)
RUN npm run build -- --configuration=development
 
# Stage 2: Development Stage with Node.js (only for development mode)
FROM node:18-alpine AS development
 
# Set working directory for the Angular app
WORKDIR /app
 
# Install dependencies for the Angular app (just in case any are needed for development)
COPY package*.json ./
RUN npm ci
 
# Copy the Angular source code into the container
COPY . .
 
# Expose the development port for the Angular app
EXPOSE 4200
 
# Default command: Run Angular development server (`ng serve`) for development mode
CMD ["/bin/sh", "-c", "npm start -- --host 0.0.0.0 --port 4200"]