# MovieFinder

Problem statement: Create a movie finder app using this api - http://omdbapi.com/

Spec: The app should accept a movie name from the user. Based on the name entered, the app should show the following details of the movie: Title, Genre, Release date, Plot (short version) and rating.

Accept multiple movie names from the user and display all search results along with the details. Allow user to specify a type (Movie or Serial) and then yield results accordingly.

Assumption1: User can enter multiple movies using comma seperator. Comma seperator is used for the ease of user, we can use any other delimiter for seperating movies.

Assumption2: In case of multiple movies, if a particular movie is not found, error is displayed and the app proceeds displaying other movies. 

Libraries: Volley for Network calls, Picasso for displaying images.

