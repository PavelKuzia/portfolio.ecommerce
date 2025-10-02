echo "--- Starting Docker Command Sequence ---"

docker compose -f docker-compose-prod.yml down 

set ENV_AWS_RDS_ENDPOINT=edu-aws-db.cbu6mq86qkdr.eu-north-1.rds.amazonaws.com
set ENV_USERNAME_RDS=postgres
set ENV_PASSWORD_RDS=FmC0cThSogj0n23l8tJE
set ENV_FRONTEND_PORT=4200
set ENV_FRONTEND_URL=frontend

docker compose -f docker-compose-prod.yml up -d

echo "--- Successfully finished execution ---"