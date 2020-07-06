DROP TABLE IF EXISTS JobSeeker;
 
CREATE TABLE JobSeeker (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  fname VARCHAR(50) NOT NULL,
  lname VARCHAR(50) NOT NULL,
  contactnum VARCHAR(15) DEFAULT NULL,
  email VARCHAR(100) DEFAULT NULL,
  location VARCHAR(50) DEFAULT NULL,
  skillset VARCHAR(500) DEFAULT NULL
);
 
INSERT INTO JobSeeker (fname, lname, contactnum, email, location, skillset) VALUES
  ('G', 'Uma', '9100180137','guma@gmail.com','Hyderabad','java, j2ee, spring, xml, json, Hibernate'),
  ('G', 'Mahesh', '8185948759','guma@gmail.com','Hyderabad','java, j2ee, springBoot, xml, json, MongoDB'),
  ('GU', 'Mahesh', '829795446','guma@gmail.com','Hyderabad','C#, net, yml, xml, json');