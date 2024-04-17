BEGIN TRANSACTION;
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO custom_recipes (name, ingredients, instructions, image)
VALUES ('Spaghetti Carbonara', '200g spaghetti, 100g pancetta, 2 eggs, 50g Parmesan cheese, Salt, Black pepper', '1. Cook spaghetti according to package instructions. 2. In a pan, fry pancetta until crispy. 3. In a bowl, whisk together eggs and Parmesan cheese. 4. Drain spaghetti and add to the pan with pancetta. 5. Quickly stir in the egg and cheese mixture until it coats the spaghetti. 6. Season with salt and pepper. Serve immediately.', 'https://www.allrecipes.com/thmb/gTibTRJ8MW87L0jMhAvXPjIDD94=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/19402-quick-and-easy-alfredo-sauce-DDMFS-4x3-17abb2055c714807944172db9172b045.jpg');
INSERT INTO custom_recipes (name, ingredients, instructions, image)
VALUES ('Caprese Salad', '2 large tomatoes, 1 ball fresh mozzarella cheese, Fresh basil leaves, Extra virgin olive oil, Balsamic vinegar, Salt, Black pepper', '1. Slice tomatoes and mozzarella into 1/4 inch thick slices. 2. Arrange tomato and mozzarella slices alternately on a serving plate. 3. Tuck basil leaves between tomato and mozzarella slices. 4. Drizzle with olive oil and balsamic vinegar. 5. Season with salt and pepper. Serve immediately.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRO3gObnqYl3e4seIM6Fq8Of3EfjB5axRUDAtJujWN6OA&s');
INSERT INTO custom_recipes (name, ingredients, instructions, image)
VALUES ('Chicken Alfredo Pasta', '200g fettuccine pasta, 2 boneless, skinless chicken breasts, 1 cup heavy cream, 1/2 cup grated Parmesan cheese, 2 cloves garlic, minced, 2 tablespoons butter, Salt, Black pepper, Fresh parsley (optional)', '1. Cook fettuccine pasta according to package instructions. 2. Season chicken breasts with salt and pepper. 3. In a pan, heat butter over medium heat and cook chicken until golden brown and cooked through. 4. Remove chicken from pan and set aside. 5. In the same pan, add minced garlic and cook until fragrant. 6. Stir in heavy cream and Parmesan cheese, and simmer until sauce thickens. 7. Slice cooked chicken and add it back to the pan with the sauce. 8. Serve chicken and Alfredo sauce over cooked fettuccine. Garnish with fresh parsley if desired.', 'https://thecozycook.com/wp-content/uploads/2023/05/Creamy-Chicken-Pasta-f.jpg');
INSERT INTO user_custom_recipes(user_id, custom_recipe_id)
VALUES (1, 1);
INSERT INTO user_custom_recipes(user_id, custom_recipe_id)
VALUES (1, 2);
INSERT INTO user_custom_recipes(user_id, custom_recipe_id)
VALUES (1, 3);
COMMIT TRANSACTION;
