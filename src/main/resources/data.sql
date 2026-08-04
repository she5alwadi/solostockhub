INSERT INTO brands (brand_name, country, contact_email)
SELECT 'Nike', 'United States', 'support@nike.com'
WHERE NOT EXISTS (
    SELECT 1 FROM brands WHERE LOWER(brand_name) = 'nike'
);

INSERT INTO brands (brand_name, country, contact_email)
SELECT 'Adidas', 'Germany', 'support@adidas.com'
WHERE NOT EXISTS (
    SELECT 1 FROM brands WHERE LOWER(brand_name) = 'adidas'
);

INSERT INTO brands (brand_name, country, contact_email)
SELECT 'Puma', 'Germany', 'support@puma.com'
WHERE NOT EXISTS (
    SELECT 1 FROM brands WHERE LOWER(brand_name) = 'puma'
);

INSERT INTO brands (brand_name, country, contact_email)
SELECT 'Samsung', 'South Korea', 'support@samsung.com'
WHERE NOT EXISTS (
    SELECT 1 FROM brands WHERE LOWER(brand_name) = 'samsung'
);

INSERT INTO brands (brand_name, country, contact_email)
SELECT 'Apple', 'United States', 'support@apple.com'
WHERE NOT EXISTS (
    SELECT 1 FROM brands WHERE LOWER(brand_name) = 'apple'
);


INSERT INTO products
(name, description, price, size, color, quantity,
 category, brand_id, created_at)
SELECT
    'Nike Air Max Shoes',
    'Comfortable running shoes designed for everyday use.',
    129.99,
    '10',
    'Black',
    25,
    'FOOTWEAR',
    b.brand_id,
    CURRENT_TIMESTAMP
FROM brands b
WHERE LOWER(b.brand_name) = 'nike'
AND NOT EXISTS (
    SELECT 1 FROM products
    WHERE LOWER(name) = 'nike air max shoes'
);


INSERT INTO products
(name, description, price, size, color, quantity,
 category, brand_id, created_at)
SELECT
    'Adidas Training Shirt',
    'Lightweight athletic shirt suitable for gym training.',
    39.99,
    'Large',
    'Blue',
    40,
    'CLOTHING',
    b.brand_id,
    CURRENT_TIMESTAMP
FROM brands b
WHERE LOWER(b.brand_name) = 'adidas'
AND NOT EXISTS (
    SELECT 1 FROM products
    WHERE LOWER(name) = 'adidas training shirt'
);


INSERT INTO products
(name, description, price, size, color, quantity,
 category, brand_id, created_at)
SELECT
    'Puma Sports Cap',
    'Adjustable sports cap with breathable fabric.',
    24.99,
    'One Size',
    'Red',
    8,
    'ACCESSORIES',
    b.brand_id,
    CURRENT_TIMESTAMP
FROM brands b
WHERE LOWER(b.brand_name) = 'puma'
AND NOT EXISTS (
    SELECT 1 FROM products
    WHERE LOWER(name) = 'puma sports cap'
);


INSERT INTO products
(name, description, price, size, color, quantity,
 category, brand_id, created_at)
SELECT
    'Samsung Galaxy Tablet',
    'Portable tablet designed for work and entertainment.',
    499.99,
    '11 Inch',
    'Silver',
    12,
    'ELECTRONICS',
    b.brand_id,
    CURRENT_TIMESTAMP
FROM brands b
WHERE LOWER(b.brand_name) = 'samsung'
AND NOT EXISTS (
    SELECT 1 FROM products
    WHERE LOWER(name) = 'samsung galaxy tablet'
);


INSERT INTO products
(name, description, price, size, color, quantity,
 category, brand_id, created_at)
SELECT
    'Apple Wireless Headphones',
    'Wireless headphones with high-quality audio and charging case.',
    249.99,
    'Standard',
    'White',
    6,
    'ELECTRONICS',
    b.brand_id,
    CURRENT_TIMESTAMP
FROM brands b
WHERE LOWER(b.brand_name) = 'apple'
AND NOT EXISTS (
    SELECT 1 FROM products
    WHERE LOWER(name) = 'apple wireless headphones'
);


INSERT INTO products
(name, description, price, size, color, quantity,
 category, brand_id, created_at)
SELECT
    'Nike Running Shorts',
    'Breathable running shorts with secure side pockets.',
    44.99,
    'Medium',
    'Grey',
    30,
    'CLOTHING',
    b.brand_id,
    CURRENT_TIMESTAMP
FROM brands b
WHERE LOWER(b.brand_name) = 'nike'
AND NOT EXISTS (
    SELECT 1 FROM products
    WHERE LOWER(name) = 'nike running shorts'
);


INSERT INTO products
(name, description, price, size, color, quantity,
 category, brand_id, created_at)
SELECT
    'Adidas Backpack',
    'Durable backpack suitable for school, work, or travel.',
    59.99,
    '25 Litres',
    'Black',
    18,
    'ACCESSORIES',
    b.brand_id,
    CURRENT_TIMESTAMP
FROM brands b
WHERE LOWER(b.brand_name) = 'adidas'
AND NOT EXISTS (
    SELECT 1 FROM products
    WHERE LOWER(name) = 'adidas backpack'
);


INSERT INTO products
(name, description, price, size, color, quantity,
 category, brand_id, created_at)
SELECT
    'Puma Soccer Ball',
    'Training soccer ball suitable for indoor and outdoor use.',
    34.99,
    'Size 5',
    'White',
    9,
    'SPORTS',
    b.brand_id,
    CURRENT_TIMESTAMP
FROM brands b
WHERE LOWER(b.brand_name) = 'puma'
AND NOT EXISTS (
    SELECT 1 FROM products
    WHERE LOWER(name) = 'puma soccer ball'
);


INSERT INTO products
(name, description, price, size, color, quantity,
 category, brand_id, created_at)
SELECT
    'Samsung Smart Monitor',
    'High-resolution monitor with built-in smart applications.',
    399.99,
    '32 Inch',
    'Black',
    15,
    'ELECTRONICS',
    b.brand_id,
    CURRENT_TIMESTAMP
FROM brands b
WHERE LOWER(b.brand_name) = 'samsung'
AND NOT EXISTS (
    SELECT 1 FROM products
    WHERE LOWER(name) = 'samsung smart monitor'
);


INSERT INTO products
(name, description, price, size, color, quantity,
 category, brand_id, created_at)
SELECT
    'Apple Charging Cable',
    'Durable charging cable compatible with supported Apple devices.',
    29.99,
    '1 Metre',
    'White',
    50,
    'ELECTRONICS',
    b.brand_id,
    CURRENT_TIMESTAMP
FROM brands b
WHERE LOWER(b.brand_name) = 'apple'
AND NOT EXISTS (
    SELECT 1 FROM products
    WHERE LOWER(name) = 'apple charging cable'
);