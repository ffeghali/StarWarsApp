**Requirements**

1. Two-activity application using Java
2. Main Activity
   - Search Bar at the top
     - As user types, it displays live search results to user inside recycler view
   - Recycler view
     - Name and "Favorite" Status (See Below)
   - You will be searching for people from Star Wars using the free star wars API at https://swapi.dev/.
     - Use the Search functionality (ie “people/?search=”), but don’t worry about handling the pagination.  Just display the first page of the query.
   - When you tap on a person in the recycler view, open another activity (Detail Activity)
3. Detail Activity
   - Display this person's name, height, mass, hair color, skin color, eye color, birth year, and gender where applicable.
   - This page should also contain a "Favorite" button. 
   - Characters that are favored should be saved to shared preferences. 
   - You should be able to see that a character is favored on the recycler view cells (Main activity) and on the detail activity. 
   - You should be able to navigate back and forth between these 2 activities. 

 