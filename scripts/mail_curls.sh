#!/bin/bash

###############################################
# Script with the given curl commands on mail
# also checks the output
###############################################

function do_test() {
    URL="${1}"
    EXPECTED="${2}"

    echo -n " -> [${URL}]"
    GOT=$(curl -s --request POST "${URL}")
    if [[ "${GOT}" == "${EXPECTED}" ]]
      then
        echo " OK!"
      else
        echo " NOK!"
        echo "      Got:      [${GOT}]"
        echo "      Expected: [${EXPECTED}]"
      fi
}

do_test "http://localhost:8080/rest/mars/" "(0, 0, N)"

do_test "http://localhost:8080/rest/mars/MMRMMRMM" "(2, 0, S)"

do_test "http://localhost:8080/rest/mars/MML" "(0, 2, W)"

do_test "http://localhost:8080/rest/mars/MML" "(0, 2, W)"

do_test "http://localhost:8080/rest/mars/AAA" "400 Bad Request"

do_test "http://localhost:8080/rest/mars/MMMMMMMMMMMMMMMMMMMMMMMM" "400 Bad Request"
