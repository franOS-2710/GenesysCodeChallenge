# GenesysCodeChallenge

The test will launch Ryanair website (https://www.ryanair.com/), search for a flight, select a flight and select seats

To run the test:
Clone the project
Go to the docker folder (/src/main/resources/docker/) and open it in Terminal
Run the docker-compose up command
Check if selenium grid is up and running
Then you can execute the test using the command: mvn clean test -Denv=int -Dbrowser=chrome -Druntype=remote
