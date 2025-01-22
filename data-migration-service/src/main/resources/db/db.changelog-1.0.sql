CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       telegram_id VARCHAR(50) UNIQUE NOT NULL,
                       city VARCHAR(100) NOT NULL,
                       frequency VARCHAR(20) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);


CREATE TABLE weather_data (
                              id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                              user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                              content JSONB NOT NULL,
                              recorded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

