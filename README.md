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

Please run the applications first, and browser swagger ui, http://localhost:8080/swagger-ui/index.html

For more details about Apis, [Open API 3 - personal-balance-backend ](./doc/openapi3_backend.json) or browser the following link, http://localhost:8080/v3/api-docs 

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
	"createdAt": "2023-02-18T01:10:26.529+00:00",
	"updatedAt": "2023-02-18T01:10:26.529+00:00",
	"name": "Dane",
	"ledgers": [],
	"userId": "69348c3b-bec3-4db4-b12d-0d84d91867ee"
}
```
2. Get all users
```shell
curl --request GET \
  --url http://localhost:8080/api/v1/users
  
## Response
[
	{
		"createdAt": "2023-02-15T19:56:22.136+00:00",
		"updatedAt": "2023-02-15T19:56:22.136+00:00",
		"name": "Anna",
		"ledgers": [
			{
				"name": "Anna_Ledger",
				"ledgerId": "cb601dfb-830e-47c9-b661-82bf0c560f83"
			}
		],
		"userId": "29b0446e-9f47-4c60-9bf3-acc8775f83d1"
	},
	{
		"createdAt": "2023-02-15T19:56:29.219+00:00",
		"updatedAt": "2023-02-15T19:56:29.219+00:00",
		"name": "David",
		"ledgers": [],
		"userId": "3aea1c5a-e261-4158-bcc1-cb9e2ac3b752"
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
    "createdAt": "2023-02-15T21:18:21.048+00:00",
    "updatedAt": "2023-02-15T21:18:21.048+00:00",
    "name": "David_Ledger",
    "userId": "3aea1c5a-e261-4158-bcc1-cb9e2ac3b752",
    "ledgerId": "fd00bac9-418c-4eda-a2be-052c44dcd266"
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
	"transactionId": "e2b371ee-0e91-4eb1-b937-9303acdf15f8",
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
	"userId": "29b0446e-9f47-4c60-9bf3-acc8775f83d1",
	"createdAt": "2023-02-18T01:06:55.677+00:00",
	"updatedAt": "2023-02-18T01:06:55.677+00:00",
}
```
5. Get a trabsaction by `ledgerId`  (with Pagination)
- default parameters
   - page: 0
   - size: 10
  
```shell
curl --request GET \
  --url 'http://localhost:8080/api/v1/ledgers/cb601dfb-830e-47c9-b661-82bf0c560f83/transactions?page=0&size=1'
  
## response
[
	{
		"createdAt": "2023-02-18T01:06:55.677+00:00",
	    "updatedAt": "2023-02-18T01:06:55.677+00:00",
		"amount": 758.300,
		"currency": "TWD",
		"type": "EXPENSE",
		"source": "CASH",
		"memo": "personal_emergency_water bill",
		"category": {
			"name": "budgeting",
			"categoryId": "7cca6c14-5739-424c-a69d-d7465ce415df"
		},
		"ledgerId": "cb601dfb-830e-47c9-b661-82bf0c560f83",
		"userId": "29b0446e-9f47-4c60-9bf3-acc8775f83d1",
		"transactionId": "13721d17-42a8-4358-92a6-83e22da1bf09"
	}
]
```

6. Get a transaction 
```shell
curl --request GET \
  --url http://localhost:8080/api/v1/ledgers/cb601dfb-830e-47c9-b661-82bf0c560f83/transactions/00e032f7-365d-4d74-8a84-248b6869dc6d
```

7. Get a transaction category (with Pagination)
- default parameters 
  - page: 0
  - size: 10

```shell
curl --request GET \
  --url 'http://localhost:8080/api/v1/tx_categories?page=0&size=1'

## Response
[
	{
		"categoryId": "350e89e5-dd8a-450f-9a5f-6fbb13c7a5a5",
		"name": "retirement"
	}, ....
]
```
8. Search Transactions 

To be continued.. 

## Todo
1. Add unit tests 
2. Api
   - Add transaction search api
   - Add `Pagenation` feature 
   - Add mistake-proofing checking on each field 
3. Refactor `service`
4. <del>Integrate Swagger 
   - Update README</del>

