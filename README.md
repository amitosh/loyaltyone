# loyaltyone

These end points are enabled -

1. https://localhost:8443/

	This is "Hello World" page. Covers use case "1".

2. https://localhost:8443/

	This is the echo page. Covers use case "2".
	
3 https://localhost:8443/saveandecho

	This is the display page for use cases 3 - 7.
	
The application redirects http://localhost:8080 to https://localhost:8443.

There is a small bug that the posts for a user are displayed on clicking 'Done' the second time, not the first time. I need to work on it. The application uses H2 in-memory database.