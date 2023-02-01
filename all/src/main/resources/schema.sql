DROP TABLE IF EXISTS prices;

CREATE TABLE prices (
  price_list INT PRIMARY KEY,
  brand_id INT NOT NULL,
  product_id INT NOT NULL,
  priority INT NOT NULL,
  price DECIMAL(20, 2) NOT NULL,
  curr VARCHAR(10) NOT NULL,
  start_date TIMESTAMP NOT NULL,
  end_date TIMESTAMP NOT NULL
);