CREATE TABLE weather_data (
                              id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                              city VARCHAR(100) NOT NULL,
                              temperature DOUBLE PRECISION,
                              cloudiness INTEGER,
                              humidity INTEGER,
                              condition VARCHAR(100),
                              is_day BOOLEAN,
                              recorded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

