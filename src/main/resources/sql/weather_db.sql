DROP TABLE WEATHERS;
DROP SEQUENCE weather_sq;

CREATE SEQUENCE weather_sq INCREMENT BY 1;


CREATE TABLE WEATHERS(
  weather_id NUMBER PRIMARY KEY,
  temp_c VARCHAR2(10),
  temp_f VARCHAR2(10),
  wind_mph VARCHAR2(10),
  api_id VARCHAR2(30),
  insetr_date DATE
);

CREATE OR REPLACE TRIGGER WEATHER_TRIG
before INSERT ON WEATHERS
FOR EACH ROW
  DECLARE
  BEGIN
    if :new.weather_id is null then
      :new.weather_id := weather_sq.nextval;
    end if;
  END;
/

commit;
