INSERT INTO user_role (id,name) VALUES (1, 'ROLE_USER');
INSERT INTO user_role (id,name) VALUES (2, 'ROLE_OPERATER');
INSERT INTO user_role (id,name) VALUES (3, 'ROLE_REQUEST_MANAGER');
INSERT INTO user_role (id,name) VALUES (4, 'ROLE_INCIDENT_MANAGER');

INSERT INTO user (id, address, email, first_name, last_name, password, phone, username, role_id) VALUES
(1,'Ulica 123','emir@telenash.ba','Emir','Barucija','$2a$10$iTQ9MEqn4w7kjyTJJtFXnuk3FGIo4i37Xg1clB2esZEJ0dVsDOMr2','062-123-456','emirBarucija',1);
INSERT INTO user (id, address, email, first_name, last_name, password, phone, username, role_id) VALUES
(2,'Titova 1','amra@etf.unsa.ba','Amra','Mujcinovic','$2a$10$xLszqGZvWJrjtRorHOCfaOc9t5ALCf15QZJHFU1Ytg3ob4xrVFvEq','061-225-883','amraMujcinovic',2);
INSERT INTO user (id, address, email, first_name, last_name, password, phone, username, role_id) VALUES
(3,'Zmaja od Bosne 10','berns@etf.unsa.ba','Berina','Muhovic','$2a$10$IlyxuNiBQheykxrxuHnwN.680VFK0gPTb8dCsbMFU3c0YhqKc/Olq','061-987-123','berinaMuhovic',3);
INSERT INTO user (id, address, email, first_name, last_name, password, phone, username, role_id) VALUES
(4,'Armije RBiH','alem@alem.ba','Alem','Delic','$2a$10$AgtcP8ucL9xK5ptmry8yv.SKxadlu29m/lOZ7ttfUDcg634mAS6Na','033-111-111','alem',4);

INSERT INTO department (id, description, name) VALUES
(1,'Odjel za razvoj IT aplikacija','IT');
INSERT INTO department (id, description, name) VALUES
(2,'Odjel za menadzment finansijama','Finansije');
INSERT INTO department (id, description, name) VALUES
(3,'Odjel za telefoniju','Telefonija');


INSERT INTO priority (id,name) VALUES
(1,'Hitan');
INSERT INTO priority (id,name) VALUES
(2,'Znacajan');
INSERT INTO priority (id,name) VALUES
(3,'Srednji');
INSERT INTO priority (id,name) VALUES
(4,'Mali');

INSERT INTO status (id,name) VALUES
(1,'Podnesen');
INSERT INTO status (id,name) VALUES
(2,'U toku');
INSERT INTO status (id,name) VALUES
(3,'Rijesen');
INSERT INTO status (id,name) VALUES
(4,'Zatvoren');

INSERT INTO incident (id, date, description, name, way_of_response, way_of_submission, department_id, user_priority_id, admin_priority_id, status_id, user_id) VALUES
(1,'2018-04-25','Klijent je prijavio da mu internet ne radi, radnik na salteru je pokusao rijesiti problem, ali nije uspio.','Internet ne radi','WEB_APPLICATION','WEB_APPLICATION',1,1,1,1,1);
INSERT INTO incident (id, date, description, name, way_of_response, way_of_submission, department_id, user_priority_id, admin_priority_id, status_id, user_id) VALUES
(3,'2018-04-18','Racun za prosli mjesec je placen, ali na ovomjesecnom racunu stoji da korisnik ima dug za prosli mjesec.','Problem sa racunom od proslog mjeseca','WEB_APPLICATION','WEB_APPLICATION',2,2,2,2,2);
INSERT INTO incident (id, date, description, name, way_of_response, way_of_submission, department_id, user_priority_id, admin_priority_id, status_id, user_id) VALUES
(2,'2018-04-28','Klijent je prijavio da neki kanali na kablovskoj TV ne rade.','Neki kanali na kablovskoj TV ne rade','PHONE','PHONE',1,3,3,3,3);
INSERT INTO incident (id, date, description, name, way_of_response, way_of_submission, department_id, user_priority_id, admin_priority_id, status_id, user_id) VALUES
(4,'2018-05-22','Klijent je prijavio da mu telefon ne radi.','Telefon ne radi','WEB_APPLICATION','WEB_APPLICATION',3,2,3,3,3);
INSERT INTO incident (id, date, description, name, way_of_response, way_of_submission, department_id, user_priority_id, admin_priority_id, status_id, user_id) VALUES
(5,'2018-05-22','Klijent se žali na sporu internet konekciju.','Spora internet konekcija','WEB_APPLICATION','WEB_APPLICATION',1,1,2,2,4);
INSERT INTO incident (id, date, description, name, way_of_response, way_of_submission, department_id, user_priority_id, admin_priority_id, status_id, user_id) VALUES
(6,'2018-05-16','Klijent je prijavio da mu internet ne radi.','Internet ne radi','WEB_APPLICATION','WEB_APPLICATION',1,1,2,3,2);
INSERT INTO incident (id, date, description, name, way_of_response, way_of_submission, department_id, user_priority_id, admin_priority_id, status_id, user_id) VALUES
(7,'2018-05-18','Klijent je prijavio da mu dodatni HBO HD kanali ne rade.','Dodatni HBO HD kanali ne rade','PHONE','PHONE',1,3,3,3,2);
INSERT INTO incident (id, date, description, name, way_of_response, way_of_submission, department_id, user_priority_id, admin_priority_id, status_id, user_id) VALUES
(8,'2018-05-20','Klijent je prijavio da mu internet ne radi nakon prelaska na drugi paket.','Problem prilikom prelaska na drugi paket','PHONE','PHONE',1,3,3,3,1);



INSERT INTO feedback_incident (id, comment, date, incident_id, user_id) VALUES (1,'Incident je brzo rijesen','2018-02-28',1,1);
INSERT INTO feedback_incident (id, comment, date, incident_id, user_id) VALUES (2,'Sve pohvale!!! Problem sa telefonom rijesen u kratkom vremenskom periodu.','2018-05-22',4,3);
INSERT INTO feedback_incident (id, comment, date, incident_id, user_id) VALUES (3,'KATASTROFA','2018-05-22',5,4);
INSERT INTO feedback_incident (id, comment, date, incident_id, user_id) VALUES (4,'Brz odgovor. Incident brzo rijesen.','2018-05-10',2,3);
INSERT INTO feedback_incident (id, comment, date, incident_id, user_id) VALUES (5,'Uspjesno rijesen incident.','2018-05-16',6,2);



INSERT INTO product (id, description, name, price) VALUES
(1,'Kablovska televizija, preko 30 domacih i 50 stranih kanala, poput BBC, National Geographic, Animal Planet i ostalih.','Kablovska televizija',10);
INSERT INTO product (id, description, name, price) VALUES
(2,'Jeftina telefonska usluga, mogucnost povoljnog razgovora sa zemljom i inozemstvom','Telefonija',8.5);
INSERT INTO product (id, description, name, price) VALUES
(3,'Kablovska televizija + internet, da biste uzivali u velikom broju TV kanala, i surfali internetom velikim brzinama','Kablovska televizija + Internet',20);
INSERT INTO product (id, description, name, price) VALUES
(4,'Kablovska televizija + telefonija, da biste uzivali u velikom broju TV kanala, i pricali jeftino i sa zemljom i sa inozemstvom','Kablovska televizija + Telefonija',16);
INSERT INTO product (id, description, name, price) VALUES
(5,'Internet + Telefonija, da biste surfali internetom velikim brzinama, i pricali jeftino i sa zemljom i sa inozemstvom','Internet + Telefonija',16);
INSERT INTO product (id, description, name, price) VALUES
(6,'Trio paket, koji ce zadovoljiti sve vase potrebe','Kablovska televizija + Internet + Telefonija',38);
INSERT INTO product (id, description, name, price) VALUES
(7,'Dodatni HBO HD kanali, za one koji zele da uzivaju u najnovijim filmovima','Dodatni HBO kanali',3);
INSERT INTO product (id, description, name, price) VALUES
(8,'Odlicni i veoma brzi internet, sa brzinama do 25 Mb/s','Internet',12.5);

INSERT INTO request (id, date, description, radni_nalog, name, way_of_response, way_of_submission, department_id, user_priority_id, admin_priority_id, product_id, status_id, user_id) VALUES
(1,'2018-04-10','Klijent zeli da omoguci paket sa dodatnim HBO HD kanalima','Radni nalog br. 1/2018.<br><br>Potrebno je da se u IT departmentu omoguci akrivacija dodatnog paketa klijenta, i po aktivaciji paketa, da se izvrseni radni nalog vrati administratoru','Zahtjev za dodatni HBO paket','web application','web application',1,1,1,7,1,1);
INSERT INTO request (id, date, description, radni_nalog, name, way_of_response, way_of_submission, department_id, user_priority_id, admin_priority_id, product_id, status_id, user_id) VALUES
(2,'2018-03-22','Klijent zeli da unaprijedi paket iz kablovska TV + internet, u kablovska TV + internet + telefonija','Radni nalog br. 2/2018.<br><br>Potrebno je promijeniti paket klijenta, te po promjeni paketa u sistemu, vratiti izvjestaj request menadzeru','Promjena paketa','telephone','telephone',1,2,2,3,2,2);
INSERT INTO request (id, date, description, radni_nalog, name, way_of_response, way_of_submission, department_id, user_priority_id, admin_priority_id, product_id, status_id, user_id) VALUES
(3,'2018-03-22','Klijent zeli da unaprijedi paket iz kablovska TV, u kablovska TV + internet + telefonija','Radni nalog br. 3/2018.<br><br>Potrebno je promijeniti paket klijenta, te po promjeni paketa u sistemu, vratiti izvjestaj request menadzeru','Promjena paketa','telephone','telephone',1,2,2,3,3,2);
INSERT INTO request (id, date, description, radni_nalog, name, way_of_response, way_of_submission, department_id, user_priority_id, admin_priority_id, product_id, status_id, user_id) VALUES
(4,'2018-04-22','Klijent zeli promjenu paketa iz internet u internet+telefonija','Radni nalog br. 4/2018.<br><br>Potrebno je promijeniti paket klijenta, te po promjeni paketa u sistemu, vratiti izvjestaj request menadzeru','Promjena paketa','telephone','telephone',1,2,2,3,4,2);
INSERT INTO request (id, date, description, radni_nalog, name, way_of_response, way_of_submission, department_id, user_priority_id, admin_priority_id, product_id, status_id, user_id) VALUES
(5,'2018-05-12','Klijent zeli promjenu paketa iz televizija + internet u internet','Radni nalog br. 5/2018.<br><br>Potrebno je promijeniti paket klijenta, te po promjeni paketa u sistemu, vratiti izvjestaj request menadzeru','Promjena paketa','telephone','telephone',1,2,2,8,4,3);
INSERT INTO request (id, date, description, radni_nalog, name, way_of_response, way_of_submission, department_id, user_priority_id, admin_priority_id, product_id, status_id, user_id) VALUES
(6,'2018-06-12','Klijent zeli promjenu paketa iz televizija + internet u internet','Radni nalog br. 5/2018.Potrebno je promijeniti paket klijenta, te po promjeni paketa u sistemu, vratiti izvjestaj request menadzeru','Promjena paketa','telephone','telephone',1,2,2,8,1,1);
INSERT INTO request (id, date, description, radni_nalog, name, way_of_response, way_of_submission, department_id, user_priority_id, admin_priority_id, product_id, status_id, user_id) VALUES
(7,'2018-07-12','Klijent zeli promjenu paketa iz televizija','Radni nalog br. 7/2018.Potrebno je promijeniti paket klijenta, te po promjeni paketa u sistemu, vratiti izvjestaj request menadzeru','Promjena paketa','telephone','telephone',1,2,2,7,1,1);

INSERT INTO feedback_request (id, comment, date, request_id, user_id) VALUES (1,'Sretna sam da je promjena paketa obavljena brzo i uspjesno','2018-03-24',2,2);
INSERT INTO feedback_request (id, comment, date, request_id, user_id) VALUES (2,'Brzo, jednostavno i uspjesno unapredenje paketa','2018-03-24',3,2);
INSERT INTO feedback_request (id, comment, date, request_id, user_id) VALUES (5,'Tek nakon par dana paket uspjesno promjenjen','2018-05-16',5,3);


ALTER TABLE `db_ppis_lambda`.`incident`
ADD COLUMN `radni_nalog` VARCHAR(1000) NULL DEFAULT NULL AFTER `name`;