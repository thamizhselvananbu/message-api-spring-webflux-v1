# message-api-spring-webflux-v1



Execute above docker compose up -d command to start kafka brokers and zookeepers

After started docker compose, execute below docker compose command to create topic messages

docker compose exec broker \
  kafka-topics --create \
    --topic messages \
    --bootstrap-server localhost:9092 \
    --replication-factor 1 \
    --partitions 1
