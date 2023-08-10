# Zip-Code API
API to retrieve Zip Codes in Mexico.

## Tools and Technologies Used
- [IntelliJ IDEA 2023.2](https://www.jetbrains.com/idea/)
- [Spring Boot 3](https://spring.io/projects/spring-boot)
- [Spring Web](https://spring.io/guides/gs/rest-service/)
- [Lombok](https://projectlombok.org/)
- [Java 17](https://openjdk.java.net/projects/jdk/17/)

## API Endpoints

### Retrieve Zip Code

**Endpoint:**  
`GET http://127.0.0.1:8080/zip-code/{zipcode}`

**Example Request:**  
`GET http://127.0.0.1:8080/zip-code/06140`

**Example Response:**
```json
{
  "zip_code": "06140",
  "locality": "Ciudad de México",
  "federal_entity": "Ciudad de México",
  "settlements": [
    {
      "name": "Condesa",
      "zone_type": "Urbano",
      "settlement_type": "Colonia"
    }
  ],
  "municipality": "Cuauhtémoc"
}
```

## Error Response

If a postal code is not found, an HTTP 404 code will be returned, and the following error message will be displayed:

```json
{
  "error": "No postal code found:100000000"
}
```

## Project Setup and Execution

- To test the project, clone the repository and open it locally.
- Ensure you have the `CPdescarga.txt` file in the `/home/` directory of your server. You can download it [here](https://www.correosdemexico.gob.mx/SSLServicios/ConsultaCP/CodigoPostal_Exportar.aspx).



## Performance Test Results with Apache Bench

Performance tests were conducted on the endpoint `http://127.0.0.1:8080/zip-code/70000`.

### Test Details:

- **Server Hostname**: 127.0.0.1
- **Server Port**: 8080
- **Document Path**: /zip-code/70000
- **Document Length**: 686 bytes
- **Concurrency Level**: 1000
- **Total Requests**: 100000

### Results:

- **Time taken for tests**: 50.704 seconds
- **Complete requests**: 100000
- **Failed requests**: 0
- **Total transferred**: 88000000 bytes
- **HTML transferred**: 68600000 bytes
- **Requests per second (mean)**: 1972.22 [#/sec]
- **Time per request (mean)**: 507.043 [ms]
- **Time per request (across all concurrent requests)**: 0.507 [ms]
- **Transfer rate**: 1694.88 [Kbytes/sec] received

### Connection Times (ms):

| Metric | Min | Mean | Median | Max |
|--------|-----|------|--------|-----|
| Connect | 0 | 0 | 0 | 8 |
| Processing | 95 | 504 | 524 | 606 |
| Waiting | 2 | 275 | 271 | 593 |
| Total | 95 | 505 | 524 | 606 |

### Percentage of the Requests Served within a Certain Time (ms):

- **50%**: 524 ms
- **66%**: 528 ms
- **75%**: 532 ms
- **80%**: 534 ms
- **90%**: 548 ms
- **95%**: 557 ms
- **98%**: 571 ms
- **99%**: 588 ms
- **100%**: 606 ms (longest request)

### Conclusions:

- The server successfully handled 100,000 requests without any errors.
- Despite the high concurrency, the server responded efficiently with a rate of 1972.22 requests per second.
- 99% of the requests were completed in 588 ms or less.

