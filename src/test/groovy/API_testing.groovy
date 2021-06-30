import groovy.json.JsonBuilder
import groovyx.net.http.RESTClient
import org.apache.http.client.HttpResponseException

import spock.lang.Specification
import spock.lang.Unroll

class API_testing extends Specification {

    RESTClient restClient = new RESTClient("https://reqres.in")

    @Unroll("Create an user #tag")
    def 'create user'() {
        when:
        def response
        def requestBody =
                [
                        "name": name,
                        "job" : job,

                ]
        println "request: " + new JsonBuilder(requestBody).toPrettyString()

        try {

            response = restClient.post(path: '/api/users',
                    body: requestBody,
                    requestContentType: 'application/json')
            System.out.println(response)
        } catch (HttpResponseException ex) {
            // default failure handler throws an exception:
            println "error response: ${ex.statusCode}"
            response = ex.response
        }

        then:
        response.status == 201
//        response.id == 201

        where:
        tag                                 | name       | job
        "with valid data for all parameter" | "Mr Ashik" | "Engineer"
        "without username and password"     | "Akash"    | "student"
        "with null"                         | null       | "student"

    }

}