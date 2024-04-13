BEGIN TRANSACTION;

DROP TABLE IF EXISTS user_custom_recipes, user_recipes, recipes_ingredients, ingredients, recipes, users, custom_recipes;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

create table recipes (
	recipe_id serial primary key,
	name varchar(150),
	ingredients text,
	instructions text,
	tags text,
	image text
);

create table custom_recipes (
	custom_recipe_id serial primary key,
	name varchar(150),
	ingredients text,
	instructions text,
	tags text,
	image text
);

create table ingredients (
	ingredient_id serial primary key,
	name varchar(50)
);
create table recipes_ingredients (
	recipe_id int not null,
	ingredient_id int not null,
	
	constraint pk_recipes_ingredients PRIMARY KEY (recipe_id, ingredient_id),
	constraint fk_recipes_ingredients_recipes FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id),
	constraint fk_recipes_ingredients_ingredients FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id)
);
create table user_recipes (
	user_id int not null,
	recipe_id int not null,
	
	constraint pk_user_recipes PRIMARY KEY (user_id, recipe_id),
	constraint fk_user_recipes_user FOREIGN KEY (user_id) REFERENCES users(user_id),
	constraint fk_user_recipes_recipes FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id)
);

create table user_custom_recipes (
	user_id int not null,
	custom_recipe_id int not null,
	
	constraint pk_user_custom_recipes PRIMARY KEY (user_id, custom_recipe_id),
	constraint fk_user_custom_recipes_user FOREIGN KEY (user_id) REFERENCES users(user_id),
	constraint fk_user_custom_recipes_recipes FOREIGN KEY (custom_recipe_id) REFERENCES custom_recipes(custom_recipe_id)
);


COMMIT TRANSACTION;
