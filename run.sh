#!/usr/bin/env bash
# shellcheck disable=SC2162
echo "mvn clean install ${DIR}"

mvn clean install -pl "${DIR}" -am
IFS=' '

read -a strarr <<< "${WAIT_SERVICE}"

for val in "${strarr[@]}";
do
  echo "waiting for ${val}"

  ./wait-for-it.sh -t 0 -s "${val}" -- echo "${val} is up"
done

# shellcheck disable=SC2164
cd "${DIR}"

java -Duser.timezone='Asia/Ho_Chi_Minh' -agentlib:jdwp=transport=dt_socket,server=y,address=*:1"${SERVER_PORT}",suspend=n -jar target/*.jar