INSERT INTO USERS (ID_USER, FULL_NAME, CREATED_AT) VALUES (1000, 'JOA KIM', CURRENT_TIMESTAMP);

INSERT INTO STATUS (ID_STATUS, STATUS_NAME) VALUES (1000, 'ACTIVATED');

INSERT INTO SUBSCRIPTION (ID_SUBSCRIPTION, ID_USER_FK, ID_STATUS_FK, CREATED_AT, UPDATED_AT) VALUES (1000, 1000, 1000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO EVENTS (ID_EVENT, ID_SUB_FK, TYPE, CREATED_AT) VALUES (1000, 1000, 'PURCHASE', CURRENT_TIMESTAMP);
