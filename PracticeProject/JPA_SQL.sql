Create database JPA;

use JPA;

CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL
);

-- Feedback Table-- 
CREATE TABLE Feedback (
    id INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL,
    createdBy_id INT,
    issue VARCHAR(255) NOT NULL,
    FOREIGN KEY (createdBy_id) REFERENCES User(id)
);