INSERT INTO brands (brand_name, country, contact_email)
VALUES ('Nike', 'United States', 'support@nike.com');

INSERT INTO brands (brand_name, country, contact_email)
VALUES ('Adidas', 'Germany', 'support@adidas.com');

INSERT INTO brands (brand_name, country, contact_email)
VALUES ('Puma', 'Germany', 'support@puma.com');

INSERT INTO brands (brand_name, country, contact_email)
VALUES ('Samsung', 'South Korea', 'support@samsung.com');

INSERT INTO brands (brand_name, country, contact_email)
VALUES ('Apple', 'United States', 'support@apple.com');


INSERT INTO products
(name, description, price, size, color, quantity, category, brand_id, created_at)
VALUES
(
    'Nike Air Max Shoes',
    'Comfortable running shoes designed for everyday use.',
    129.99,
    '10',
    'Black',
    25,
    'FOOTWEAR',
    1,
    CURRENT_TIMESTAMP
);

INSERT INTO products
(name, description, price, size, color, quantity, category, brand_id, created_at)
VALUES
(
    'Adidas Training Shirt',
    'Lightweight athletic shirt suitable for gym training.',
    39.99,
    'Large',
    'Blue',
    40,
    'CLOTHING',
    2,
    CURRENT_TIMESTAMP
);

INSERT INTO products
(name, description, price, size, color, quantity, category, brand_id, created_at)
VALUES
(
    'Puma Sports Cap',
    'Adjustable sports cap with breathable fabric.',
    24.99,
    'One Size',
    'Red',
    8,
    'ACCESSORIES',
    3,
    CURRENT_TIMESTAMP
);

INSERT INTO products
(name, description, price, size, color, quantity, category, brand_id, created_at)
VALUES
(
    'Samsung Galaxy Tablet',
    'Portable tablet designed for work and entertainment.',
    499.99,
    '11 Inch',
    'Silver',
    12,
    'ELECTRONICS',
    4,
    CURRENT_TIMESTAMP
);

INSERT INTO products
(name, description, price, size, color, quantity, category, brand_id, created_at)
VALUES
(
    'Apple Wireless Headphones',
    'Wireless headphones with high-quality audio and charging case.',
    249.99,
    'Standard',
    'White',
    6,
    'ELECTRONICS',
    5,
    CURRENT_TIMESTAMP
);

INSERT INTO products
(name, description, price, size, color, quantity, category, brand_id, created_at)
VALUES
(
    'Nike Running Shorts',
    'Breathable running shorts with secure side pockets.',
    44.99,
    'Medium',
    'Grey',
    30,
    'CLOTHING',
    1,
    CURRENT_TIMESTAMP
);

INSERT INTO products
(name, description, price, size, color, quantity, category, brand_id, created_at)
VALUES
(
    'Adidas Backpack',
    'Durable backpack suitable for school, work, or travel.',
    59.99,
    '25 Litres',
    'Black',
    18,
    'ACCESSORIES',
    2,
    CURRENT_TIMESTAMP
);

INSERT INTO products
(name, description, price, size, color, quantity, category, brand_id, created_at)
VALUES
(
    'Puma Soccer Ball',
    'Training soccer ball suitable for indoor and outdoor use.',
    34.99,
    'Size 5',
    'White',
    9,
    'SPORTS',
    3,
    CURRENT_TIMESTAMP
);

INSERT INTO products
(name, description, price, size, color, quantity, category, brand_id, created_at)
VALUES
(
    'Samsung Smart Monitor',
    'High-resolution monitor with built-in smart applications.',
    399.99,
    '32 Inch',
    'Black',
    15,
    'ELECTRONICS',
    4,
    CURRENT_TIMESTAMP
);

INSERT INTO products
(name, description, price, size, color, quantity, category, brand_id, created_at)
VALUES
(
    'Apple Charging Cable',
    'Durable charging cable compatible with supported Apple devices.',
    29.99,
    '1 Metre',
    'White',
    50,
    'ELECTRONICS',
    5,
    CURRENT_TIMESTAMP
);