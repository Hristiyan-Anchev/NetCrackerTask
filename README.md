## This is a solution to an assignment I was given for an interview at NetCracker  for Junior Backend Developer position.

Assignment:


Object model: 
You have two classes - Agreement and Product.
Agreement class has fields :
	name - string that has the agreement name that it generates by rule "Agreement " + current date in format day/month/year.
	signed by - string with name of person who signed an agreement.
	products - collection of products that are included into this agreement. 
				This collection should be having only products that are directly under agreement.
				Products that have parent product shouldn't be in this collection.

Product class has fields :
	parent object - reference to agreement or parent product.
	products - collection of children products.
	name - string with the product name.
	price - number with product's price. Can be non integer.

Task - create an API that 
  1) receives Agreement object and stores it into a file with agreement's name.
  2) receives file path to agreement saved in previous point and creates Agreement object with all nested products.

All files should be readable in text editor so human could understand information inside.
Please add one generated test file to the results.


