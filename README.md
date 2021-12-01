# Meal Planner AI
Programming Language: Java
SDK: openjdk-17 version 17.0.1

Usage: ./MealPlanner [profile pathname] [meal #]

## Adding Ingredients
Put a string like the following in ./data/ingredients.txt:

Food Name;Unit of Measure;Amount;Dietary Restrictions in an Order;


Examples:

Bread;Slice;12;Vegetarian;Vegan;

Ground Beef;1/4 Pound;8;

## Adding Recipes
Put a string like the following in ./data/recipes.txt:

Recipe Name;Meal Type;Ingredient Name:Count;(Repeat for as many ingredients as necessary)


Examples:

Scrambled Eggs;Breakfast;Egg:2;Shredded Cheese:1;

Mac and Cheese;Lunch;Pasta:2;Shredded Cheese:1;

## Creating/Editing Profiles
2 Sample profiles are provided.

Mark each criteria with 'True' or 'False', and add dietary restrictions separated by semicolons.

##Expected Output
The program will output a comma separated list of recipes that fits the given constraints.

It will print up to 5 valid meal plans, if there are more than 5, it will choose 5 randomly from
all possible meal plans.