# Step 1: Build
FROM node:18 as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Step 2: Serve with Vite preview
EXPOSE 5173
CMD ["npm", "run", "preview", "--", "--host"]
