
CREATE TABLE messages (
	id INTEGER NOT NULL PRIMARY KEY ASC AUTOINCREMENT,
	service_id VARCHAR(32) NOT NULL,
	app_id VARCHAR(32) NOT NULL,
	user_id VARCHAR(256) NOT NULL,
	shortcode VARCHAR(64) NOT NULL,
	keyword VARCHAR(64) NOT NULL,
	pricecode VARCHAR(64),
	product VARCHAR(64),
	service_name VARCHAR(64),
	billing_status INTEGER NOT NULL,
	optin_status INTEGER NOT NULL,
	optin_keyword VARCHAR(256),
	optin_shortcode VARCHAR(64),
	created_at INTEGER NOT NULL,
	updated_at INTEGER NOT NULL
);
