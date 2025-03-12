CREATE TABLE "User" (
  "id" int PRIMARY KEY,
  "name" varchar,
  "email" varchar,
  "image" varchar,
  "created_at" DATETIME
);

CREATE TABLE "Movies" (
  "id" int PRIMARY KEY,
  "name" varchar,
  "release_year" int,
  "cover" varchar,
  "cover2" varchar,
  "synopsis" text,
  "director_id" int,
  "created_at" DATETIME
);

CREATE TABLE "Actors" (
  "id" int PRIMARY KEY,
  "name" varchar,
  "surname" varchar,
  "image" varchar,
  "created_at" DATETIME
);

CREATE TABLE "Genres" (
  "id" int PRIMARY KEY,
  "name" varchar,
  "created_at" DATETIME
);

CREATE TABLE "Comment" (
  "id" int PRIMARY KEY,
  "user_id" int,
  "movie_id" int,
  "comment" text,
  "created_at" DATETIME
);

CREATE TABLE "Ratings" (
  "id" int PRIMARY KEY,
  "user_id" int,
  "movie_id" int,
  "rating" DECIMAL(2,1) NOT NULL,
  "created_at" DATETIME
);

CREATE TABLE "Notifications" (
  "id" int PRIMARY KEY,
  "user_id" int,
  "friend_id" int,
  "movie_id" int,
  "status" varchar,
  "created_at" DATETIME
);

CREATE TABLE "Friends" (
  "id" int PRIMARY KEY,
  "user_id" int,
  "friend_id" int,
  "status" enum(pending,accepted,rejected) DEFAULT 'pending',
  "created_at" DATETIME
);

CREATE TABLE "To_Watch" (
  "id" int PRIMARY KEY,
  "user_id" int,
  "movie_id" int,
  "likes" boolean,
  "watched" boolean,
  "created_at" DATETIME
);

CREATE TABLE "Lists" (
  "id" int PRIMARY KEY,
  "user_id" int,
  "name" varchar,
  "created_at" DATETIME
);

CREATE TABLE "Director" (
  "id" int PRIMARY KEY,
  "name" varchar,
  "surname" varchar,
  "created_at" DATETIME
);

CREATE TABLE "Movie_List" (
  "id" int PRIMARY KEY,
  "list_id" int,
  "movie_id" int,
  "created_at" DATETIME
);

CREATE TABLE "Movies_Actors" (
  "movie_id" int,
  "actor_id" int,
  "created_at" DATETIME
);

CREATE TABLE "Movies_Genres" (
  "movie_id" int,
  "genre_id" int,
  "created_at" DATETIME
);

CREATE TABLE "Chat" (
  "id" int PRIMARY KEY,
  "created_at" DATETIME
);

CREATE TABLE "User_Chat" (
  "id" int PRIMARY KEY,
  "user_id" int,
  "chat_id" int,
  "created_at" DATETIME
);

CREATE TABLE "Message" (
  "id" int PRIMARY KEY,
  "chat_id" int,
  "user_id" int,
  "text" text,
  "timestamp" DATETIME,
  "created_at" DATETIME
);

ALTER TABLE "Comment" ADD FOREIGN KEY ("user_id") REFERENCES "User" ("id");

ALTER TABLE "Comment" ADD FOREIGN KEY ("movie_id") REFERENCES "Movies" ("id");

ALTER TABLE "Ratings" ADD FOREIGN KEY ("user_id") REFERENCES "User" ("id");

ALTER TABLE "Ratings" ADD FOREIGN KEY ("movie_id") REFERENCES "Movies" ("id");

ALTER TABLE "Notifications" ADD FOREIGN KEY ("user_id") REFERENCES "User" ("id");

ALTER TABLE "Notifications" ADD FOREIGN KEY ("friend_id") REFERENCES "User" ("id");

ALTER TABLE "Notifications" ADD FOREIGN KEY ("movie_id") REFERENCES "Movies" ("id");

ALTER TABLE "Friends" ADD FOREIGN KEY ("user_id") REFERENCES "User" ("id");

ALTER TABLE "Friends" ADD FOREIGN KEY ("friend_id") REFERENCES "User" ("id");

ALTER TABLE "To_Watch" ADD FOREIGN KEY ("user_id") REFERENCES "User" ("id");

ALTER TABLE "To_Watch" ADD FOREIGN KEY ("movie_id") REFERENCES "Movies" ("id");

ALTER TABLE "Lists" ADD FOREIGN KEY ("user_id") REFERENCES "User" ("id");

ALTER TABLE "Movie_List" ADD FOREIGN KEY ("list_id") REFERENCES "Lists" ("id");

ALTER TABLE "Movie_List" ADD FOREIGN KEY ("movie_id") REFERENCES "Movies" ("id");

ALTER TABLE "Director" ADD FOREIGN KEY ("id") REFERENCES "Movies" ("director_id");

ALTER TABLE "Movies_Actors" ADD FOREIGN KEY ("movie_id") REFERENCES "Movies" ("id");

ALTER TABLE "Movies_Actors" ADD FOREIGN KEY ("actor_id") REFERENCES "Actors" ("id");

ALTER TABLE "Movies_Genres" ADD FOREIGN KEY ("movie_id") REFERENCES "Movies" ("id");

ALTER TABLE "Movies_Genres" ADD FOREIGN KEY ("genre_id") REFERENCES "Genres" ("id");

ALTER TABLE "User_Chat" ADD FOREIGN KEY ("user_id") REFERENCES "User" ("id");

ALTER TABLE "User_Chat" ADD FOREIGN KEY ("chat_id") REFERENCES "Chat" ("id");

ALTER TABLE "Message" ADD FOREIGN KEY ("chat_id") REFERENCES "Chat" ("id");

ALTER TABLE "Message" ADD FOREIGN KEY ("user_id") REFERENCES "User" ("id");
