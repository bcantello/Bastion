# `onboarding`

Example java project used for onboarding. There is a [`Jenkinsfile`](https://jenkins.io/doc/book/pipeline/syntax/)
for CI.

## QuickStart Backend

1. Clean and Assemble the project
        
        ./gradlew clean assemble

2. Run the project
        
        ./gradlew run
        
3. Run the JUnit tests

        ./gradlew test
        
4. Do everything at once
        
        ./gradlew clean assemble test run

## Backend Documentation
                          
* [style-guide](https://wiki.payjunction.com/display/DEV/Java+Style+Guide)       

## QuickStart Frontend
1. Use the nvm version defined in the project
   ```
   nvm use
   ```
2. Install all dependencies
   ```
   npm imstall
   ```
1. Build the project and watch for changes

        npm start  
        
1. Run the unit tests

        npm test     
             
