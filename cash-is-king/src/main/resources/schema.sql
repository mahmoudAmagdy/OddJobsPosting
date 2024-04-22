CREATE TABLE users
(
user_id 	INT(10) UNSIGNED 	NOT NULL AUTO_INCREMENT,
 user_name 	CHARACTER VARYING(50) NOT NULL, 
 password	CHARACTER VARYING(20),
 roles 		CHARACTER VARYING(10),
 active		BOOLEAN, 
 user_nat_id CHARACTER VARYING(16), 
 user_first_name CHARACTER VARYING(20), 
 user_last_name CHARACTER VARYING(20),	
 user_phone_number CHARACTER VARYING(20), 
 user_rating 	DOUBLE(10),  
 user_type_type_id INTEGER(10)
)
CREATE TABLE user_type
(
type_id INT(3) UNSIGNED NOT NULL AUTO_INCREMENT,
type_name VARCHAR(20)
)
INSERT INTO user_type(type_id, type_name)
VALUES (1, 'Job Issuer'),
(2, 'Job Taker');
INSERT INTO users ( user_name, password, roles,active, user_nat_id, user_first_name, user_last_name,
					user_phone_number, user_rating,  user_type_type_id )
VALUES ('medo1', '1234', 'ADMIN', TRUE, '12345678901234', 'mo', 'said', '010012332', '8.3', 1),
('medo2', '1234', 'USER', TRUE, '12345678901233', 'ahmed', 'said', '010012312', '8.3', 1),
('medo3', '1234', 'USER', TRUE, '12345678901232', 'aly', 'said', '01001236', '8.3', 1),
('medo4', '1234', 'USER', TRUE, '12345678901231', 'mo', 'waleed', '01001235', '8.3', 2),
('medo5', '1234', 'USER', TRUE, '12345678901230', 'omar', 'wael', '01001234', '8.3', 2);

