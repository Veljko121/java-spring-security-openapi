DROP TABLE IF EXISTS public."_user";
CREATE TABLE public."_user" (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	"role" int2 NOT NULL,
	created_date_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	email varchar(255) NOT NULL UNIQUE,
	"password" varchar(255) NOT NULL,
	username varchar(255) NOT NULL UNIQUE,

	CONSTRAINT "_user_email_key" UNIQUE (email),
	CONSTRAINT "_user_pkey" PRIMARY KEY (id),
	CONSTRAINT "_user_role_check" CHECK (((role >= 0) AND (role <= 1))),
	CONSTRAINT "_user_username_key" UNIQUE (username)
);