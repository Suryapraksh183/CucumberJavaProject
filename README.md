# 🥒 Cucumber Java Selenium Framework – Complete Guide for IDE Users

Hey! Welcome to the **Cucumber Java Selenium** automation framework.  
I built this with **IntelliJ** and **Eclipse** users in mind, so everything is designed to run smoothly inside your IDE with minimal setup.

This repo is perfect if you want to learn, practice, or demonstrate **UI test automation using Java + Selenium + Cucumber (BDD)**.

---

## 🔍 What This Framework Does

Here's what you get out of the box:

- ✅ **End-to-End UI Test Automation**  
  Automate browser flows like login, search, navigation, validations, etc.

- ✅ **BDD with Cucumber & Gherkin**  
  Write test cases in **plain English** (`.feature` files) so even non-technical people can understand.

- ✅ **Java + Selenium WebDriver**  
  Clean implementation for browser actions like click, type, select, wait, verify, etc.

- ✅ **Reusable Components**  
  Common utilities, hooks, drivers, and page interactions you can reuse across tests.

- ✅ **Professional Test Reports**  
  HTML or Extent-style reports to show **what passed, what failed, and why**.

- ✅ **IDE-Friendly Setup**  
  Just import → right-click the runner → **Run**. No complicated commands needed.

---

## 🧰 Tech Stack

| Technology           | Purpose                    |
|----------------------|----------------------------|
| **Java (8+ / 11+)**  | Programming language       |
| **Maven**            | Build & dependency manager |
| **Selenium WebDriver** | Browser automation       |
| **Cucumber (BDD)**   | Gherkin-based test design  |
| **JUnit / TestNG**   | Test runner + assertions   |
| **WebDriverManager** | Auto driver management (optional) |
| **Extent / Cucumber Report Plugin** | HTML reports |

> You can adapt Java version & dependencies in `pom.xml` based on your environment.

---

## 🚀 Getting Started in Your IDE (Super Easy)

### ✅ Prerequisites

- Java installed and configured (JDK 8/11 or above)
- Maven installed / bundled in IDE
- IntelliJ IDEA or Eclipse

### ⏱ Quick Start – In Under 1 Minute

1. **Clone the repo**  
   ```bash
   git clone https://github.com/Suryapraksh183/CucumberJavaProject.git
   ```

2. **Import as Maven Project into IntelliJ or Eclipse.**

3. **Locate the Cucumber test runner class (e.g.):**

   ```
   src/test/java/runner/TestRunner.java
   ```

4. **Right-click → Run 'TestRunner'**

   - In IntelliJ: Green play button near class name
   - In Eclipse: Run As → JUnit Test or TestNG Suite

5. **After execution, open the HTML report (if configured), typically under:**

   ```
   target/  
   └── cucumber-reports/ or extent-report/
   ```

That's it – you've just run your first Cucumber+Selenium test suite 🚀

---

## 🗂 Project Structure (Typical Layout)

Your actual package names may differ – adjust this section if needed.

```
CucumberJavaProject
├── pom.xml                             ← Maven configuration
└── src
    ├── test
    │   ├── java
    │   │   └── com
    │   │       └── cucumber
    │   │           ├── runner
    │   │           │   └── TestRunner.java        ← Main Cucumber runner
    │   │           ├── steps
    │   │           │   └── LoginSteps.java        ← Step definitions
    │   │           ├── hooks
    │   │           │   └── Hooks.java             ← Before/After scenario
    │   │           ├── pages
    │   │           │   └── LoginPage.java         ← Page Objects (if used)
    │   │           └── utils
    │   │               ├── DriverFactory.java     ← WebDriver init & teardown
    │   │               └── TestUtil.java          ← Helper methods (waits, etc.)
    │   └── resources
    │       ├── features
    │       │   └── Login.feature                  ← Gherkin scenarios
    │       └── config
    │           └── config.properties              ← URL, browser, timeouts, etc.
    └── main
        └── java (optional for shared lib code)
```

---

## 📁 What Each Folder Does

- **runner/** – Contains TestRunner.java which links feature files, glue (steps), plugins, and report setup.

- **steps/** – Java methods mapped to Gherkin steps (Given, When, Then, etc.).

- **hooks/** – Setup & teardown logic for scenarios (@Before, @After) – open browser, close browser, screenshots, etc.

- **pages/** – Page Object Model (if used): one class per page with locators & actions.

- **utils/** – WebDriver factory, waits, common functions.

- **features/** – .feature files written in Gherkin.

---

## 🧾 The Cucumber Flow – How Everything Works

Think of the framework like this:

```
Your .feature file (English)
          ↓
      Cucumber
          ↓
   Step Definitions (Java)
          ↓
    Page Objects / Utils
          ↓
    Selenium WebDriver
          ↓
    Real Browser Actions
          ↓
   Validations & Assertions
          ↓
   Reports (HTML, JSON, etc.)
```

### Example Feature (Gherkin)

```gherkin
Feature: Login functionality
  As a user
  I want to log into the application
  So that I can access my dashboard

  Scenario: Valid login with correct credentials
    Given I am on the login page
    When I enter valid username and password
    And I click on the login button
    Then I should be navigated to the home page
```

### Matching Step Definition (Java)

```java
@Given("I am on the login page")
public void i_am_on_the_login_page() {
    driver = DriverFactory.getDriver();
    driver.get(ConfigReader.getProperty("app.url"));
}

@When("I enter valid username and password")
public void i_enter_valid_username_and_password() {
    loginPage.enterUsername(ConfigReader.getProperty("valid.username"));
    loginPage.enterPassword(ConfigReader.getProperty("valid.password"));
}

@When("I click on the login button")
public void i_click_on_the_login_button() {
    loginPage.clickLoginButton();
}

@Then("I should be navigated to the home page")
public void i_should_be_navigated_to_the_home_page() {
    Assert.assertTrue(loginPage.isHomePageDisplayed());
}
```

Cucumber links Gherkin steps → Java methods based on the step text.

---

## 🧱 TestRunner Configuration

A typical Cucumber runner (JUnit-based) looks like this:

```java
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.cucumber.steps", "com.cucumber.hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-html-report",
        "json:target/cucumber.json"
        // "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"  // if using Extent
    },
    monochrome = true,
    dryRun = false
)
public class TestRunner {
}
```

- **features** – Path to your .feature files
- **glue** – Packages where step defs & hooks live
- **plugin** – Report formats (HTML, JSON, Extent, etc.)
- **monochrome** – Cleaner console output
- **dryRun** – true → only checks mapping, doesn't execute steps

---

## 🌐 Browser & Config Management

Use a config.properties file (example):

```properties
browser=chrome
app.url=https://example.com
valid.username=admin
valid.password=admin123
implicit.wait=10
page.load.timeout=30
```

Read these values in Java using a simple ConfigReader:

```java
public class ConfigReader {
    private static Properties prop;

    static {
        try (InputStream input = new FileInputStream("src/test/resources/config/config.properties")) {
            prop = new Properties();
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
```

---

## 🖥 Driver Factory (WebDriver Setup)

Centralize driver creation:

```java
public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getProperty("browser");
            if (browser.equalsIgnoreCase("chrome")) {
                // You can use WebDriverManager here
                // WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            // Add Firefox/Edge if needed
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
```

---

## 🔁 Hooks – Before & After Each Scenario

Use Cucumber hooks to manage setup/cleanup:

```java
public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();
        System.out.println("Starting scenario: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Capture screenshot & attach if needed
            // byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            // scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }
        DriverFactory.quitDriver();
    }
}
```

---

## 📊 Reports

Depending on your runner configuration, you may get:

- Default Cucumber HTML report → `target/cucumber-html-report`
- Cucumber JSON → `target/cucumber.json`
- Extent Reports (if configured) → `target/extent-report / test-output`

Check your `@CucumberOptions(plugin = {...})` and adjust paths if needed.

---

## 🧪 Writing Your Own Tests

### 1️⃣ Add a New Feature File

Create a file like:
```
src/test/resources/features/Search.feature
```

```gherkin
Feature: Search functionality

  Scenario: Search for a valid product
    Given I am on the home page
    When I search for "Laptop"
    Then I should see results related to "Laptop"
```

### 2️⃣ Implement Step Definitions

Create SearchSteps.java under steps:

```java
public class SearchSteps {

    WebDriver driver = DriverFactory.getDriver();
    HomePage homePage = new HomePage(driver);

    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        driver.get(ConfigReader.getProperty("app.url"));
    }

    @When("I search for {string}")
    public void i_search_for(String product) {
        homePage.enterSearchText(product);
        homePage.clickSearchButton();
    }

    @Then("I should see results related to {string}")
    public void i_should_see_results_related_to(String product) {
        Assert.assertTrue(homePage.getSearchResultsText().contains(product));
    }
}
```

### 3️⃣ (Optional) Use Page Objects

HomePage.java in pages:

```java
public class HomePage {

    private WebDriver driver;

    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    @FindBy(css = ".results")
    private WebElement resultsContainer;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterSearchText(String text) {
        searchBox.clear();
        searchBox.sendKeys(text);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public String getSearchResultsText() {
        return resultsContainer.getText();
    }
}
```

---

## 🧩 Running Tests – Different Ways

### From IDE

- **IntelliJ:** Right-click TestRunner.java → Run 'TestRunner'
- **Eclipse:** Right-click TestRunner.java → Run As → JUnit Test / TestNG Test

### From Command Line

```bash
mvn test
# or
mvn clean test
```

If you have multiple runners, you can configure Maven profiles or surefire plugins to target specific ones.

---

## 🛠 Troubleshooting – Common Issues

### 1. Browser not opening / driver error
- Ensure correct driver binaries / WebDriverManager setup.
- Check browser value in config.properties.
- Make sure you're using a compatible browser-driver combination.

### 2. "Undefined step" warnings
Cucumber can't find matching Java method.

Check that:
- Text in .feature matches annotation exactly.
- glue package in @CucumberOptions is correct.
- Methods are public.

### 3. Feature files not executed
- Verify features path in TestRunner points to the correct folder.
- Ensure .feature files have the Feature: keyword at the top.

### 4. Reports not generated
- Check plugin configuration in @CucumberOptions.
- Make sure target/ directory is not write-protected.
- Run mvn clean test for a fresh build.

---

## ✅ Summary

This CucumberJavaProject gives you:

- A ready-to-use BDD framework with Java + Selenium
- Proper structure for scalability & learning
- IDE-friendly execution with minimal configuration
- Real-world patterns you can talk about in interviews, Topmate sessions, and projects

Feel free to:

- Add new features & step definitions
- Integrate with CI/CD (Jenkins, GitHub Actions, etc.)
- Attach screenshots on failure
- Extend reports & add tags/filters for regression/smoke suites

---

**Created by:** Suriyapraksh  
**Status:** Active & Learnable ✅

**Happy Testing & Teaching! 💻🧪**
