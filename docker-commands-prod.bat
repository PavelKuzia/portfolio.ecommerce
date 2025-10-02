echo "--- Starting Docker Command Sequence ---"

docker compose -f docker-compose-prod.yml down 

docker compose -f docker-compose-prod.yml up -d

echo "--- Successfully finished execution ---"