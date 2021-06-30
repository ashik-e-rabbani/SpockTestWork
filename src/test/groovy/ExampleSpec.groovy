import spock.lang.Specification
import spock.lang.Unroll

class ExampleSpec extends Specification {

    @Unroll("case value #varian")
    def "validation test"() {

//        expect:
//        1 == 0
//        given:
//        def str1 = "ashik"
//        def str = "rabbani"

        when:
        def sum = 5
        print(varian)

        then:
        sum == varian

        where:
        varian << [3, 5, 6, 9, 5]

    }
}
