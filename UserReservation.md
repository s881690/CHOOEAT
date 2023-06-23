FORMAT: 1A
HOST: http://localhost:8080

# Reservation
The Restaurant Platform Reservation API is an interface designed for managing restaurant reservations. It provides a set of functionalities that allow users to conveniently book services at restaurants.

### Get Business Day Information [GET] [/getBusinessDay]
This API endpoint allows you to retrieve business day information for a specific date and restaurant. It provides details such as the restaurant's operating hours, remaining seat availability for each hour, and reservation status for each hour.

* Request (application/json)
  * Parameters
    The API endpoint accepts the following query parameters:
    - date (required): The date for which you want to retrieve business day information. Format: MM/DD/YYYY.
    - acc_id (required): The ID of the account or member.
    - restaurantId (required): The ID of the restaurant.
    - Example: GET /getBusinessDay?date=07/05/2023&acc_id=1&restaurantId=2  
        In this example, the API is called with the query parameters date=07/05/2023, acc_id=1, and restaurantId=2. Make sure to replace the example values with the appropriate parameters for your API.


* Response 200 (application/json)
    + Body
       {
    "status": "BusinessDay",
    "resStartTime": "12:00:00",
    "resEndTime": "22:00:00",
    "HourlySeatlist": [
        {
            "hour": 18,
            "remainSeat": 5
        },
        {
            "hour": 19,
            "remainSeat": 0
        },
        {
            "hour": 12,
            "remainSeat": 73
        }
    ],
    "reservedList": [
        12
    ],
    "remainSeat": 80
}

    * The response body contains the following information:
      * status: The status of the business day.
      * resStartTime: The start time of the restaurant's reservation availability.
      * resEndTime: The end time of the restaurant's reservation availability.
      * hourlySeatList: A list of objects representing the remaining seat availability for each hour. Each object contains the hour and remainSeat properties.
      * reservedList: A list of hours that are already reserved.
      * remainSeat: The total number of remaining seats available for the specified date and restaurant.


## Reservation Management
| URL | method | **Description** |
| :--- | :--- | :--- |
| /reservationRedis | `POST` | Save the reservation data that has not been checked out into Redis |
| /reservation | `GET` | Save the booking information after checkout to the MySQL database |
| /reservationUpdate | `PUT` | update reservation details |



### Reservation Redis [POST] [/reservationRedis]
This API endpoint is used to input a successful reservation into Redis and proceed to the checkout page.

* Request (application/json)
    * Body
    {
  "date_time": "2023-07-05T18:00:00",
  "ppl": "4",
  "text": "Special requests",
  "acc_id": 1,
  "restaurantId": 2
}

    * The request body should contain the following parameters:
      * date_time (string): The date and time of the reservation in the format "YYYY-MM-DD HH:MM:SS".
      * ppl (string): The number of people for the reservation.
      * text (string): Any special requests or notes for the reservation (optional).
      * acc_id (integer): The ID of the account or member making the reservation.
      * restaurantId (integer): The ID of the restaurant.
      
* Response 200 (application/json)
    * Body
    {
  "status": "success",
  "index": "2023-07-05T18:00:00"
}

    * The request body should contain the following parameters:
      * status: The status of the reservation (success or failure).
      * index: The key used to store the reservation in Redis.
       
### Reserve [GET] [/reservation]
This API endpoint is used to confirm the checkout and make a reservation. If successful, the data is first inserted into the database, and then the corresponding data in Redis is deleted.
* Request (application/json)
  * Parameter：
    - index (string, query, required): The key representing the reservation in Redis.
* Response 200 (application/json)
    * Body  
    {
  "status": "success",
  "reservationId": 12345
}
    * The request body should contain the following parameters:
      * status: The status of the reservation (success or empty if unsuccessful). * ppl (string): The number of people for the reservation.
      * reservationId: The ID of the reservation in the database (if successful). * acc_id (integer): The ID of the account or member making the reservation.
     
### Update Reservation [PUT] [/reservationUpdate]
This API endpoint is used to modify reservation details.

* Request (application/json)
    * Body
    {
  "date_time": "2023-07-05T18:00:00",
  "ppl": "4",
  "text": "Updated special requests",
  "reservationId": "12345"
}

    * The request body should contain the following parameters:
      * date_time (string): The updated date and time of the reservation in the format "YYYY-MM-DD HH:MM:SS".
      * ppl (string): The updated number of people for the reservation.
      * text (string): The updated special requests or notes for the reservation (optional).
      * reservationId (string): The ID of the reservation to be updated.
