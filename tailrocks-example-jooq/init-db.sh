#!/usr/bin/env bash
ABSOLUTE_PATH=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)
cd "${ABSOLUTE_PATH}" || exit

# config
export PSQL_HOST="127.0.0.1"
export PSQL_PORT="5432"
export PSQL_USERNAME="postgres"

# FIXME replace table name tailrocks_example_dev with correct project name, for example tailrocks_payment_dev
export DATABASE_NAME="tailrocks_example_dev"
export DATASOURCE_URL="jdbc:postgresql://127.0.0.1:19001/${DATABASE_NAME}"

export GRADLEW_CMD="../gradlew"
export PSQL_CMD="kubectl exec -ti postgresql-0 -n postgresql -- psql"
export DROPDB_CMD="kubectl exec -ti postgresql-0 -n postgresql -- dropdb"
# @end config

printf "\n# \e[93mPreparing ${DATABASE_NAME} database\e[0m\n\n"

# test connections first
set -e
${PSQL_CMD} -h ${PSQL_HOST} --port=${PSQL_PORT} -U ${PSQL_USERNAME} -t -c "SELECT now();"

set +e
${PSQL_CMD} -h ${PSQL_HOST} --port=${PSQL_PORT} -U ${PSQL_USERNAME} -t -c "CREATE DATABASE ${DATABASE_NAME};"
set -e

if [ "$1" = "clean" ]; then
  ${PSQL_CMD} -h ${PSQL_HOST} --port=${PSQL_PORT} -U ${PSQL_USERNAME} -t -c "REVOKE CONNECT ON DATABASE ${DATABASE_NAME} FROM public;"
  ${PSQL_CMD} -h ${PSQL_HOST} --port=${PSQL_PORT} -U ${PSQL_USERNAME} -t -c "ALTER DATABASE ${DATABASE_NAME} CONNECTION LIMIT 0;"
  ${PSQL_CMD} -h ${PSQL_HOST} --port=${PSQL_PORT} -U ${PSQL_USERNAME} -t -c "SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE pid <> pg_backend_pid() AND datname='${DATABASE_NAME}';"

  ${DROPDB_CMD} -h ${PSQL_HOST} --port=${PSQL_PORT} -U ${PSQL_USERNAME} -e ${DATABASE_NAME}

  ${PSQL_CMD} -h ${PSQL_HOST} --port=${PSQL_PORT} -U ${PSQL_USERNAME} -t -c "CREATE DATABASE ${DATABASE_NAME};"
fi

${GRADLEW_CMD} flywayMigrate
