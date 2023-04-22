CREATE TABLE osoba (
  oib VARCHAR(11)  NOT NULL,
  ime VARCHAR(250)  NOT NULL,
  prezime VARCHAR(250)  NOT NULL,
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  inactive   TIMESTAMP NULL,
  PRIMARY KEY(oib),
  UNIQUE (oib)
);
CREATE TABLE datoteka(
 filename VARCHAR(250),
 oib VARCHAR(11)  NOT NULL,
 foreign key (oib) references osoba(oib),
 created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 inactive   TIMESTAMP NULL,
 PRIMARY KEY (filename),
 UNIQUE (filename)
);
CREATE INDEX IF NOT EXISTS oib_idx on osoba(
   oib
);
CREATE INDEX IF NOT EXISTS filename_idx on datoteka(
   filename
);