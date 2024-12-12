create TABLE images(
    id BIGINT auto_increment PRIMARY KEY,
    path VARCHAR(255) NOT NULL,
    artile_id BIGINT,
    FOREIGN KEY (artile_id) REFERENCES articles(id)
);