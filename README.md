# personal-balance-backend

The application can be built using Java, and SQL Server can be used as the database to store the personal financial data.

## Technologies Used
- Java 17 or higher
- Java Spring Boot v3.0.x
- MapStruct v1.5.3
- Lombok
- Microsoft JDBC Driver For SQL Server v12.x
- Docker v20.10.x
- Docker Compose 

## Configuration
The configuration for the application and its dependencies (SQL Server) can be found in the `./docker` folder.

## Getting Started
1. Clone the repository to your local machine and Navigate to the project directory.
```shell
git clone https://github.com/qawsedr87/personal-balance-backend.git

cd personal-balance-backend
```

2. Start the Docker containers

To build a Docker image using a Dockerfile within a Docker Compose file, here are the steps:

- In the `./docker/docker-compose.yaml` file, specify the SQL Server that you want to build and its configuration,
```shell
docker-compose -f ./docker/docker-compose.yaml build
```

- Once the image is built, you can start the containers using the docker-compose up command.
```shell
docker-compose -f ./docker/docker-compose.yaml up -d 
```
- After the container is working, you should access the Database `Finance`
```sql
CREATE DATABASE Finance;
GO
USE Finance;
GO
CREATE SCHEMA Personal;
GO
```

3. Run the application
```shell
./gradlew bootrun
```

4. Check that the api is running by visiting http://localhost:8080.

5. To stop the containers, press CTRL + C.

## RESTful API
1. Add a user
```shell
curl --request POST \
  --url http://localhost:8080/api/v1/users \
  --header 'Content-Type: application/json' \
  --data '{
	"name": "David"
}'

## Response 
{
	"id": "3aea1c5a-e261-4158-bcc1-cb9e2ac3b752",
	"name": "David",
	"ledgers": []
}
```
2. Get all users
```shell
curl --request GET \
  --url http://localhost:8080/api/v1/users
  
## Response
[
	{
		"id": "29b0446e-9f47-4c60-9bf3-acc8775f83d1",
		"name": "Anna",
		"ledgers": [
			{
				"id": "cb601dfb-830e-47c9-b661-82bf0c560f83",
				"name": "Anna_Ledger"
			}
		]
	},
	{
		"id": "3aea1c5a-e261-4158-bcc1-cb9e2ac3b752",
		"name": "David",
		"ledgers": []
	}
]
```
3. Add a ledger
```shell
curl --request POST \
  --url http://localhost:8080/api/v1/ledgers \
  --header 'Content-Type: application/json' \
  --data '{
	"name": "David_Ledger",
	"userId": "3aea1c5a-e261-4158-bcc1-cb9e2ac3b752"
}'

## Response 
{
	"id": "fd00bac9-418c-4eda-a2be-052c44dcd266",
	"name": "David_Ledger",
	"userId": "3aea1c5a-e261-4158-bcc1-cb9e2ac3b752"
}
```
4. Add a transaction
- currency: should be 3 characters, such as `TWD`, `USD`, etc.
- type: only in `INCOME` and `EXPENSE`
- source: among in `SALARY`, `CREDIT_CARD`, `DEBIT_CARD`, `CASH`, `SALARY`, `BONUS`, `INTEREST`, `OTHERS`

```shell
curl --request POST \
  --url http://localhost:8080/api/v1/ledgers/cb601dfb-830e-47c9-b661-82bf0c560f83/transactions \
  --header 'Content-Type: application/json' \
  --data '{
	"amount": "200",
	"currency": "USD",
	"type": "INCOME",
	"source": "SALARY",
	"memo": "school",
	"category": "parttime"
}'

## Response 
{
	"id": "e2b371ee-0e91-4eb1-b937-9303acdf15f8",
	"amount": 200,
	"currency": "USD",
	"type": "INCOME",
	"source": "SALARY",
	"memo": "school",
	"category": {
		"id": "6e2e64c2-847f-472e-b207-3402071311ec",
		"name": "parttime"
	},
	"ledgerId": "cb601dfb-830e-47c9-b661-82bf0c560f83",
	"userId": "29b0446e-9f47-4c60-9bf3-acc8775f83d1"
}
```
5. Get a transaction 
```shell
curl --request GET \
  --url http://localhost:8080/api/v1/ledgers/cb601dfb-830e-47c9-b661-82bf0c560f83/transactions/00e032f7-365d-4d74-8a84-248b6869dc6d
```

6. Get a transaction category
```shell
curl --request GET \
  --url http://localhost:8080/api/v1/tx_categories

## Response
[
	{
		"id": "350e89e5-dd8a-450f-9a5f-6fbb13c7a5a5",
		"name": "retirement"
	}, ....
]
```
7. Search Transactions 

To be continued.. 

## Todo
1. Add unit tests 
2. Api
   - Add transaction search api
   - Add `Pagenation` feature 
   - Add mistake-proofing checking on each field 
3. Refactor `service`
4. Integrate Swagger 
   - Update README