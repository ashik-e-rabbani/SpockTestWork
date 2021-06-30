import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import spock.lang.Specification

class ApiAutomation extends Specification{



    private static def WebDriver driver ;
    def "cleanupSpec"() {
        if(driver != null) {
            driver.close()
        }
    }

    def "cleanup"(){
        driver.close()
    }

    def "selenium integration with spock"() {
        given:

        System.setProperty("webdriver.chrome.driver","C:\\chormeDdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://www.tallykhata.com/contact/")

        WebElement formName = driver.findElementByXPath("//*[@id=\"wpcf7-f288-p193-o1\"]/form/p/label[1]/span/input");
        WebElement formMobile = driver.findElementByXPath("//*[@id=\"wpcf7-f288-p193-o1\"]/form/p/label[2]/span/input")
        WebElement formRemarks = driver.findElementByXPath("//*[@id=\"wpcf7-f288-p193-o1\"]/form/p/label[3]/span/textarea")
        WebElement formSubmit = driver.findElementByXPath("//*[@id=\"wpcf7-f288-p193-o1\"]/form/p/input")
        WebElement onSubmitFeedback = driver.findElementByXPath("//*[@id=\"wpcf7-f288-p193-o1\"]/form/div[2]")

        when:

        formName.sendKeys("Ashik")
        formMobile.sendKeys("01774205200")
        formRemarks.sendKeys("TallyKhata is awesome")
        Thread.sleep(500)
        formSubmit.click()
        Thread.sleep(3000)
        def feedback = onSubmitFeedback.getText()

        then:
        feedback.contains(fdbk)

        where:
        fdbk << ["Ok","আপনার মূল্যবান মতামতের জন্য ধন্যবাদ।","Not ok","ধন্যবাদ।"]
    }
}
