# Stage 1: Build Angular app
FROM node:18 AS build

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json for caching npm install
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code
COPY . .

# Expose port 4200 for the Angular development server
EXPOSE 4200

# Start the Angular development server with `ng serve`
CMD ["npm", "run", "start", "--", "--host", "0.0.0.0", "--port", "4200"]

