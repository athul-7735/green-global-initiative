CREATE DATABASE IF NOT EXISTS nausicaa_global_green_db;

USE nausicaa_global_green_db;
 
-- nausicaa_global_green_db.grants definition
 
CREATE TABLE `grants` (

  `grant_id` int NOT NULL AUTO_INCREMENT,

  `grant_name` varchar(255) NOT NULL,

  `amount` varchar(255) NOT NULL,

  `description` varchar(255) NOT NULL,

  `eligibility` varchar(255) NOT NULL,

  PRIMARY KEY (`grant_id`)

) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 
 
-- nausicaa_global_green_db.user_details definition
 
CREATE TABLE `user_details` (

  `first_name` varchar(255) NOT NULL,

  `user_id` int NOT NULL AUTO_INCREMENT,

  `last_name` varchar(255) NOT NULL,

  `email` varchar(255) NOT NULL,

  `password` varchar(255) NOT NULL,

  `last_login` varchar(255) DEFAULT NULL,

  `is_admin` bit(1) NOT NULL,

  PRIMARY KEY (`user_id`),

  UNIQUE KEY `user_details_unique` (`email`)

) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 


-- nausicaa_global_green_db.application_details definition

CREATE TABLE `application_details` (
  `application_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `organization_name` varchar(255) NOT NULL,
  `grant_id` int NOT NULL,
  `application_status` varchar(255) NOT NULL,
  `approval_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `requested_amount` varchar(255) NOT NULL,
  `project_description` varchar(255) NOT NULL,
  `admin_comments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  KEY `application_details_user_details_FK` (`user_id`),
  KEY `application_details_grants_FK` (`grant_id`),
  CONSTRAINT `application_details_grants_FK` FOREIGN KEY (`grant_id`) REFERENCES `grants` (`grant_id`),
  CONSTRAINT `application_details_user_details_FK` FOREIGN KEY (`user_id`) REFERENCES `user_details` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- nausicaa_global_green_db.contact_us definition

CREATE TABLE `contact_us` (
  `query_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `message` varchar(255) NOT NULL,
  PRIMARY KEY (`query_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO nausicaa_global_green_db.grants
(grant_id, grant_name, amount, description, eligibility)
VALUES(1, 'Teto', '10000', 'For climate change Initiatives', 'NA');
INSERT INTO nausicaa_global_green_db.grants
(grant_id, grant_name, amount, description, eligibility)
VALUES(2, 'Pejite Innovation', '5000', 'For climate change Initiatives', 'NA');

INSERT INTO nausicaa_global_green_db.user_details
(first_name, user_id, last_name, email, password, last_login, is_admin)
VALUES('Athul', 1, 'S', 'athul@gmail.com', '3zCaAhu45v2Z9gImNSv9pGDut7Ch2Q+NFbM4xbxLNM0=', NULL, 1);
INSERT INTO nausicaa_global_green_db.user_details
(first_name, user_id, last_name, email, password, last_login, is_admin)
VALUES('helena', 2, 'g', 'helenag@gmail.com', '3zCaAhu45v2Z9gImNSv9pGDut7Ch2Q+NFbM4xbxLNM0=', '2025-01-29T00:26:37.240Z', 0);

INSERT INTO nausicaa_global_green_db.application_details
(application_id, user_id, organization_name, grant_id, application_status, approval_date, requested_amount, project_description)
VALUES(1, 2, 'ATU', 1, 'In Progress', '21/01/2024', '10000', '');