echo "Waiting for Kafka to come online..."

cub kafka-ready -b kafka:9092 1 20

kafka-topics \
  --bootstrap-server kafka:9092 \
  --topic orders \
  --replication-factor 1 \
  --partitions 4 \
  --create

kafka-topics \
  --bootstrap-server kafka:9092 \
  --topic events \
  --replication-factor 1 \
  --partitions 4 \
  --create

sleep infinity