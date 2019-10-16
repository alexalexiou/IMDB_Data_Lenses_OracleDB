CREATE TABLE Movie(
	movieID varchar2(10) PRIMARY KEY,
	title varchar2(200),
	year Integer  -- change to integer
); 

CREATE TABLE Movie_Genre(
	movieID varchar2(10),
	genre varchar2(50),
	PRIMARY KEY(movieID,genre)
);

CREATE TABLE Movie_Country(
	movieID varchar2(10) ,
	country varchar2(50),
	PRIMARY KEY(movieID, country)
);

CREATE TABLE Cast(
	movieID varchar2(10),
	actorID varchar2(100),
	actorName varchar2(100),
	PRIMARY KEY(movieID, actorID)
);

CREATE TABLE Tag(
	tagID varchar2(10) PRIMARY KEY,
	tagValue varchar2(100)
);


CREATE TABLE Movie_Director(
	movieID varchar2(10),
	directorID varchar2(200),
	directorName varchar2(200),
	PRIMARY KEY(movieID, directorID)
);
	

CREATE TABLE Movie_Tag(
	movieID varchar2(10),
	tagID varchar2(10),
	mtweight INTEGER,
	PRIMARY KEY(movieID,tagID), 
	FOREIGN KEY(movieID) REFERENCES Movie(movieID) ON DELETE CASCADE,
	FOREIGN KEY(tagID) REFERENCES Tag(tagID) ON DELETE CASCADE
);

CREATE INDEX INDMOVIEID ON MOVIE(movieID);
CREATE INDEX INDTAGID ON Tag(TagID);
CREATE INDEX INDTAGVALUE ON Tag(tagValue);




	
	
	