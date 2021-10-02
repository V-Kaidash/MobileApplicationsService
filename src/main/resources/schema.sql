DROP TABLE IF EXISTS applications;

CREATE TABLE applications (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    app_name VARCHAR(128) NOT NULL,
    version VARCHAR(10) NOT NULL,
    content_rate INT NOT NULL
);