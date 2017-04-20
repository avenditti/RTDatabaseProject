CREATE TABLE movies (
	id INT NOT NULL,
    title VARCHAR(150),
    imdbID INT,
    spanishTitle VARCHAR(100),
    imdbPictureURL VARCHAR(200),
    releaseYear	INT,
    rtID VARCHAR(100),
    rtAllCriticsRating INT,
    rtAllCriticsNumReviews INT,
    rtAllCriticsNumFresh INT,
    rtAllCriticsNumRotten INT,
    rtAllCriticsScore INT,
    rtTopCriticsRating INT,
    rtTopCriticsNumReviews INT,
    rtTopCriticsNumFresh INT,
    rtTopCriticsNumRotten INT,
    rtTopCriticsScore INT,
    rtAudienceRating INT,
    rtAudienceNumRating INT,
    rtAudienceScore INT,
    rtPictureURL VARCHAR(200),
    PRIMARY KEY(id)
);
CREATE TABLE tags (		
	id			INT,
    tagValue	VARCHAR(80),
    PRIMARY KEY(id)
);
CREATE TABLE movie_actors (
	movieID		INT,
    actorID		VARCHAR(80),
    actorName	VARCHAR(80),
    ranking 	INT,
    FOREIGN KEY (movieID) REFERENCES movies(id)
);
CREATE TABLE movie_countries (
	movieID 	INT,
	country 	VARCHAR(40),
    FOREIGN KEY (movieID) REFERENCES movies(id)
);
CREATE TABLE movie_directors (
	movieID		INT,
	directorID 	VARCHAR(80),
    directorName VARCHAR(80),
    FOREIGN KEY (movieID) REFERENCES movies(id),
    PRIMARY KEY(movieID)
);
CREATE TABLE movie_genres (
	movieID		INT,
    genre		VARCHAR(30),
    FOREIGN KEY (movieID) REFERENCES movies(id)
);
CREATE TABLE movie_locations (
	movieID		INT,
    location1	VARCHAR(180),
    location2	VARCHAR(180),
    location3	VARCHAR(180),
    location4 	VARCHAR(180),
    FOREIGN KEY (movieID) REFERENCES movies(id) 	
);
CREATE TABLE movie_tags	(
	movieID		INT,
    tagID		INT,
    tagWeight	INT,
    FOREIGN KEY (movieID) REFERENCES movies(id),
    FOREIGN KEY (tagID) REFERENCES tags(id)
);
CREATE TABLE user_ratedmovies (
	userID		INT,
    movieID		INT,
    rating		INT,
    date_day	INT,
    date_month	INT,
    date_year	INT,
    date_hour	INT,
    date_minute	INT,
    date_second	INT,
    FOREIGN KEY (movieID) REFERENCES movies(id)
);
CREATE TABLE user_ratedmovies_timestamps (
	userID			INT,
    movieID			INT,
    rating			INT,
    movieTimeStamp	BIGINT,
    FOREIGN KEY (movieID) REFERENCES movies(id)
);
CREATE TABLE user_taggedmovies (
	userID			INT,
    movieID			INT,
	tagID			INT,
    date_day		INT,
    date_month		INT,
    date_year		INT,
    date_hour		INT,
    date_minute		INT,
    date_second		INT,
    FOREIGN KEY (movieID) REFERENCES movies(id),
    FOREIGN KEY (tagID) REFERENCES tags(id)
);
CREATE TABLE user_taggedmovies_timestamps (
	userID			INT,
    movieID			INT,
    tagID			INT,
    movieTimeStamp 	BIGINT,
    FOREIGN KEY (movieID) REFERENCES movies(id),
    FOREIGN KEY (tagID) REFERENCES tags(id)
);