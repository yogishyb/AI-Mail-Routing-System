# =========================
# CONTAINER COMMANDS
# =========================

docker ps
# List running containers

docker ps -a
# List all containers (running + stopped)

docker start <container>
# Start a stopped container

docker stop <container>
# Stop a running container

docker restart <container>
# Restart a container

docker rm <container>
# Remove a container

docker rm -f <container>
# Force remove a running container

docker logs <container>
# Show container logs

docker logs -f <container>
# Follow logs in real time

docker logs --tail 100 <container>
# Show last 100 log lines

docker exec -it <container> bash
# Open shell inside container

docker inspect <container>
# Show detailed container metadata

docker port <container>
# Show port mappings

docker stats
# Show CPU/Memory usage of running containers


# =========================
# IMAGE COMMANDS
# =========================

docker images
# List local images

docker pull <image>
# Download image from registry

docker build -t myapp:v1 .
# Build image from Dockerfile

docker rmi <image>
# Remove image

docker image prune
# Remove unused images


# =========================
# NETWORK COMMANDS
# =========================

docker network ls
# List Docker networks

docker network inspect <network>
# Show network details

docker network rm <network>
# Remove network


# =========================
# VOLUME COMMANDS
# =========================

docker volume ls
# List volumes

docker volume inspect <volume>
# Show volume details

docker volume rm <volume>
# Remove volume


# =========================
# DOCKER COMPOSE COMMANDS
# =========================

docker compose up
# Create and start services

docker compose up -d
# Start services in background

docker compose down
# Stop and remove services

docker compose stop
# Stop services

docker compose start
# Start existing services


docker compose down --remove-orphans


docker compose restart
# Restart services

docker compose logs
# Show logs from all services

docker compose logs -f
# Follow logs from all services

docker compose logs kafka
# Show logs for kafka service

docker compose ps
# Show compose-managed containers

docker compose pull
# Pull latest images

docker compose build
# Build services

docker compose config
# Validate and view merged compose config


# =========================
# SYSTEM CLEANUP
# =========================

docker system df
# Show Docker disk usage

docker system prune
# Remove unused containers/networks/images

docker system prune -a
# Remove all unused images and containers

docker volume prune
# Remove unused volumes

docker network prune
# Remove unused networks


# =========================
# USEFUL FOR SPRING BOOT + KAFKA
# =========================

docker compose up -d
# Start Kafka and Kafka UI

docker ps
# Verify containers are running

docker compose logs -f kafka
# Watch Kafka logs

docker compose logs -f kafka-ui
# Watch Kafka UI logs

docker exec -it kafka bash
# Enter Kafka container

# Create topic
/opt/kafka/bin/kafka-topics.sh \
--create \
--topic orders \
--bootstrap-server localhost:9092

# List topics
/opt/kafka/bin/kafka-topics.sh \
--list \
--bootstrap-server localhost:9092

# Consume messages
/opt/kafka/bin/kafka-console-consumer.sh \
--topic orders \
--bootstrap-server localhost:9092 \
--from-beginning

# Produce messages
/opt/kafka/bin/kafka-console-producer.sh \
--topic orders \
--bootstrap-server localhost:9092