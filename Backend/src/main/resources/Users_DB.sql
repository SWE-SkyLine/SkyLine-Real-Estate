CREATE DATABASE IF NOT EXISTS SKYLINE_DB;
USE SKYLINE_DB;
-- Users Table
CREATE TABLE Users (
    user_id INT PRIMARY KEY auto_increment,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    profile_photo BLOB,
    phone_number VARCHAR(20),
     account_type ENUM('ADMIN', 'SUPERADMIN', 'USER', 'COMPANY') NOT NULL
);

-- Companies Table
CREATE TABLE Companies (
   company_id INT ,
   client_id  INT,
   PRIMARY KEY(company_id, client_id),
   FOREIGN KEY (company_id) REFERENCES Users(user_id),
   FOREIGN KEY (client_id) REFERENCES Users(user_id)
);

-- Clients Table
-- CREATE TABLE Clients (
--     user_id INT PRIMARY KEY,
--     first_name VARCHAR(50) NOT NULL,
--     last_name VARCHAR(50) NOT NULL,
--     company_user_id INT,
--     FOREIGN KEY (user_id) REFERENCES Users(user_id),
--     FOREIGN KEY (company_user_id) REFERENCES Companies(user_id)
-- );

-- Admins Table
-- CREATE TABLE Admins (
--     user_id INT PRIMARY KEY,
--     first_name VARCHAR(50) NOT NULL,
--     last_name VARCHAR(50) NOT NULL,
--     FOREIGN KEY (user_id) REFERENCES Users(user_id)
-- );


-- Posts Table
CREATE TABLE Posts (
    post_id INT PRIMARY KEY,
    user_id INT,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    main_photo_ID INT, -- Assuming main_photo is a reference to AdditionalPhotos.photo_id
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    area INT,
    post_type VARCHAR(50),
    rooms CHAR CHECK (rooms > 0),
    bathrooms CHAR CHECK (bathrooms > 0),
    floors CHAR DEFAULT 0 CHECK (floors >= 0),
    pool BOOLEAN,
    garden_area INT DEFAULT 0 CHECK (garden_area >= 0),
    garage BOOLEAN,
    rent_or_sell BOOLEAN,
    price INT CHECK (price >= 0),
    contact_phone_number VARCHAR(20),
    address_for_meeting VARCHAR(255),
    date DATE,
    google_maps_link VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
 -- FOREIGN KEY (main_photo) REFERENCES AdditionalPhotos(photo_id)
-- AdditionalPhotos Table
CREATE TABLE AdditionalPhotos (
    photo_id INT PRIMARY KEY,
    post_id INT,
    photo_url VARCHAR(255),
    FOREIGN KEY (post_id) REFERENCES Posts(post_id)
);


-- Reports Table
CREATE TABLE Reports (
    report_id INT PRIMARY KEY,
    post_id INT,
    reporter_user_id INT,
    report_reason VARCHAR(255),
    report_date DATE,
    FOREIGN KEY (post_id) REFERENCES Posts(post_id),
    FOREIGN KEY (reporter_user_id) REFERENCES Users(user_id)
);

-- Notifications Table
CREATE TABLE Notifications (
    notification_id INT PRIMARY KEY auto_increment,
    requester_id INT not null,
    responder_id INT not null,
    candidate_id INT not null,
    date_requested DATE not null,
    date_answered DATE,
    approved BOOLEAN DEFAULT NULL,
    seen BOOLEAN DEFAULT false,
    answered BOOLEAN DEFAULT false,
    
    FOREIGN KEY (requester_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (responder_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (candidate_id) REFERENCES Users(user_id) ON DELETE CASCADE

);
-- CREATE TABLE SuperAdmins (
--     user_id INT PRIMARY KEY,
--     first_name VARCHAR(50) NOT NULL,
--     last_name VARCHAR(50) NOT NULL,
--     FOREIGN KEY (user_id) REFERENCES Users(user_id)
-- );
INSERT INTO Users (user_id, email,password,first_name,last_name, profile_photo, phone_number, account_type)
VALUES
    (1, 'user1@google.com', 'password1','Nagui', 'Mostafa', NULL, NULL, 'SuperAdmin'),
    (2, 'user2@google.com', 'password2', 'Kareem', 'Tarek',NULL, NULL, 'SuperAdmin'),
     (3, 'user3@egoogle.com', 'password3','Ahmed', 'Hisham', NULL, NULL, 'SuperAdmin'),
	 (4, 'user4@google.com', 'password4','Mohamed', 'Amin', NULL, NULL, 'SuperAdmin'),
    (5, 'user5@google.com', 'password5','Abdelrahman', 'Wael', NULL, NULL, 'SuperAdmin');


-- INSERT INTO SuperAdmins (user_id, first_name, last_name)
-- VALUES
--     (1, 'Nagui', 'Mostafa'),
--      (2, 'Kareem', 'Tarek'),
--       (3, 'Ahmed', 'Hisham'),
--        (4, 'Mohamed', 'Amin'),
--         (5, 'Abdelrahman', 'Wael');
