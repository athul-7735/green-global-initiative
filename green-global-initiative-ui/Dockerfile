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

# Build the Angular app for production
RUN npm run build -- --configuration=production

# Stage 2: Serve using Nginx (Production) or Node.js (Development)
FROM nginx:alpine AS serve

# Set working directory
WORKDIR /usr/share/nginx/html

# Copy built files from the build stage
COPY --from=build /app/dist . 

# Copy default nginx configuration
COPY nginx.conf /etc/nginx/nginx.conf

# Expose ports for both development and production
EXPOSE 80 4200

# If in development mode, install Node.js and dependencies
RUN apk add --no-cache nodejs npm

# Set working directory for development mode
WORKDIR /app

# Copy Angular source code (only for development mode)
COPY --from=build /app .

# Default command: Run Nginx for production, or ng serve for development
CMD ["/bin/sh", "-c", "if [ \"$APP_ENV\" = 'development' ]; then npm start -- --host 0.0.0.0 --port 4200; else exec nginx -g 'daemon off;'; fi"]
