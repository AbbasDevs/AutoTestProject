# AutoTestProject

UI and API automation project using Java, TestNG, Maven, Selenium, and Rest Assured.

Primary emphasis: API automation with Rest Assured for endpoint validation, status/assertion coverage, and negative scenarios.

## Project Scope

- API automation suite (Rest Assured) for products and authentication endpoints
- UI automation test suites for core e-commerce flows
- Suite-driven execution using TestNG XML files

## API Automation (Rest Assured)

- Main API suite file: `api-testng.xml`
- API test packages:
  - `src/test/java/api/tests/products`
  - `src/test/java/api/tests/authentication`
- Focus areas:
  - status code and response validation
  - authentication and user lifecycle endpoints
  - positive and negative test scenarios

## Tech Stack

- Java 8+ (project currently compiles with source/target 1.8)
- Maven
- TestNG
- Selenium WebDriver
- Rest Assured

## Repository Structure

```text
AutoTestProject/
├── pom.xml
├── Master_Suite.xml
├── api-testng.xml
├── Authentication_Suite.xml
├── Products_Suite.xml
├── Cart_Suite.xml
├── Checkout_Suite.xml
├── Subscription_Suite.xml
├── UI_Suite.xml
└── src/
    ├── main/java/pages/
    └── test/
        ├── java/
        └── resources/
```

## Prerequisites

1. Install Java (JDK) and set `JAVA_HOME`
2. Install Maven and ensure `mvn` is on `PATH`
3. Install Google Chrome (used by Selenium UI tests)
4. Use internet access for target test site and API calls

## How To Run

From project root:

```powershell
Set-Location "C:\Users\mabbas\Downloads\Assignment\AutoTestProject"
```

### Run API suite only (recommended first)

```powershell
mvn "-Dsurefire.suiteXmlFiles=api-testng.xml" test
```

### Run all UI sub-suites via master suite

```powershell
mvn "-Dsurefire.suiteXmlFiles=Master_Suite.xml" test
```

### Run a single suite

```powershell
mvn "-Dsurefire.suiteXmlFiles=Authentication_Suite.xml" test
mvn "-Dsurefire.suiteXmlFiles=Products_Suite.xml" test
mvn "-Dsurefire.suiteXmlFiles=Cart_Suite.xml" test
mvn "-Dsurefire.suiteXmlFiles=Checkout_Suite.xml" test
mvn "-Dsurefire.suiteXmlFiles=Subscription_Suite.xml" test
mvn "-Dsurefire.suiteXmlFiles=UI_Suite.xml" test
```

## Test Reports

After each run, reports are generated in:

- `target/surefire-reports/index.html`
- `target/surefire-reports/emailable-report.html`
- `target/surefire-reports/testng-results.xml`

For API-focused review, run `api-testng.xml` and then open `target/surefire-reports/index.html` to inspect API class and method-level results.

## Notes

- `target/` is build output and intentionally ignored by Git.
- Local IDE files are ignored in `.gitignore`.
- Each sub-suite is configured with one TestNG `<test>` per class for clearer reporting granularity.

## Suggested GitHub Upload Steps

```powershell
git status
git add .
git commit -m "Prepare project for GitHub upload"
git branch -M main
git remote add origin <your-repo-url>
git push -u origin main
```

Replace `<your-repo-url>` with your GitHub repository URL.
