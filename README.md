# Getting Started

### Reference Documentation
Basic rules assumed:

## Generals
* Documentation indicates that the name of Set<Reservation> is <b>[collection]</b>, for convenience for business the name was changed to <b>[reservations]</b>.

## Json Input
* All inputs are assumed to be well-formatted JSON.
* The field reservation_dates must have the format YYYY-MM-DD.
* The field reservation_dates will have max 2 values [start_date, end_date].
* The field reservation_dates must have [start_date <= end_date].
* The json input for add:
  {
  "client_full_name":"Client Full Name",
  "room_number": 10,
  "reservation_dates": ["2020-03-12", "2020-04-12"]
  }
* The URL for swagger-ui is: http://localhost:8080/swagger-ui.html

## File
* No need to validate if the file exist for creating a new one.
* The file name must be <b>reservationDB.txt<b> .
* In my last commit the file will be empty.
* I will save the reservations in JSON format.
  since if you need to send a real BD, it is easier to handle.
