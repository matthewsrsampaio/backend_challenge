### Backend Challenge from Instituto Atlântico.
##
### First of all you need:
  - Java 17
  - Docker
  - Postman(or anything else you wish to send requests and get responses)
  - IDE of your preference

>[!WARNING]
>Pay attention to the instructions below.
### Images you need to pull from DockerHub.
  - ps1: *You need to be `logged in Docker` in order to be allowed to download the images of RabbitMQ and Postgres11.*
  - ps2: *Docker needs to be in `Linux version`.*
  - ps3: *The pull and installation will take place when you run:* `docker compose up --build` *however it's not time to run it yet.*

>[!NOTE]
>If you need password and some information about RabbitMQ, Postgres and API, visit a file named:*
  `application.yml`

### RUNNING
##
  - At first, do get into the main directory where docker-compose file is located and run the following command:
            
            docker compose up --build
>[!NOTE]
>What happened??? The command you have just inserted into the terminal will build Postgres11, RabbitMQ and our API on Docker.

  - If you want to run it again and the project is already built, run:

            docker compose up

### REQUESTS
##
  - Check API status

        http://localhost:8081/api/status

  - Check users

        http://localhost:8081/api/all_users

  - Check subscriptions
  
        http://localhost:8081/api/all_subscriptions

  - Check status

        http://localhost:8081/api/all_status

  - Check evets

        http://localhost:8081/api/all_events

  - Check user by id -> http://localhost:8081/api/user/{User_ID}
        
        http://localhost:8081/api/user/1000 
  
  - Check user by name -> http://localhost:8081/api/subscription/name/{User_Name}

        http://localhost:8081/api/subscription/name/Paulo

  - Check subscription by id -> http://localhost:8081/api/subscription/id/{Subscription_ID} 

        http://localhost:8081/api/subscription/id/1000

  - Purchase a subscription:

        http://localhost:8081/api/subscription/purchase

              Body: {
                        "name": "Paulo",
                        "status": "ACTIVATED",
                        "type": "PURCHASE"
                      }
  - Cancel a subscription -> http://localhost:8081/api/subscription/cancel/{id} 

        http://localhost:8081/api/subscription/cancel/1000

  - Restart a subscription -> http://localhost:8081/api/subscription/restart/{id} 

        http://localhost:8081/api/subscription/restart/1000
