# House Occupancy Predictive Model Application

This program consists of a GUI that is capable of predicting the likelihood of a particular permutation occuring, and adding new permutations to the linked CSV file.

**Author:** Andrew Cotter --- **Student Number:** C237823019

**Link to Demonstration Video**: https://youtu.be/K7beBmCdb-Y

## Key Features:
- Allows the user to find the probability of a specific permutation appearing out of the dataset.
- Creation of new entries to the dataset.

## How it works:
- This program reads data from the CSV file names "IsHouseOccupied.csv" found in the directory.
- This data is used to create a predictive model.
- This model is then used to give the user the % chance of a specific permutation appearing.

## Functions:
- **calculateProbability()**:
  - This function queries the GUI's radio buttons to place in an arraylist that is used as a comparison to the other entries in the CSV file
- **addEntry()**:
  - This function uses the same code to take user input as 'calculateProbability()' and then appends the selected options to a new row in the CSV file
- **openFile()**:
  - This function opens a file based on the given filepath which can be changed in the super() at the start of the GUI class.=
- **readFile()**:
  - This function created an array that contains 5 entries, it will then copy the entry in each cell to the array, looking for a comma to know when it has moved from cell to cell. Once it has added 5 entries, it will then copy them to an arrayList which is then held to check against the user query.
- **writeFile()**:
  - This function takes the user selections and then appends them to the given CSV file. 

## Rubric:
- **Level 1:** _GUI and predictor functionality where the predictive rules are driven by values that you calculated for your dataset offline using a frequency table and hard coded in your code as variables._
  - This was depreciated when the code had to be rewritten due to it being lost, instead moved straight to making the code dynamic from the start.
 
- **Level 2:** _GUI and predictor functionality where the predictive rules are driven by values that are calculated dynamically from the training dataset in your code. This should include a button on your GUI to train your classifier (i.e. read in your dataset and calculate your rule)._
  - There is no training button present in my code, this is because I instead integrated the reading of the file to the calculateProbability() function. This was to reduce the amount of work needed for the user to do what they need.
 
- **Level 3:** _You can also use the GUI to enter in new rows to the dataset with feature values and known label values (i.e. expand your training dataset). This should then recalculate the various permutations totals for the rules (i.e. recalculate the classifier, based on pressing a button)._
  - the function addEntry() is used to add new entries to the end of the CSV file. The function first checks the selections that the user has made with the radio buttons, and then it appends the values to the end of a file based on the aforementioned selections.
 
- **Level 4:** _Your application is also able to calculate the accuracy of the predictor. This will involve training your predictor on 150 rows of the data and testing your data on 50 rows (where each of these rows is automatically put through the rules, and the predictive output automatically matched with the actual label)._
  - Due to running out of time on this project, I was unable to get this level completed. 
