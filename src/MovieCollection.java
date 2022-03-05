import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.*;

public class MovieCollection
{
  private ArrayList<Movie> movies;
  private Scanner scanner;

  public MovieCollection(String fileName)
  {
    importMovieList(fileName);
    scanner = new Scanner(System.in);
  }

  public ArrayList<Movie> getMovies()
  {
    return movies;
  }
  
  public void menu()
  {
    String menuOption = "";
    
    System.out.println("Welcome to the movie collection!");
    System.out.println("Total: " + movies.size() + " movies");
    
    while (!menuOption.equals("q"))
    {
      System.out.println("------------ Main Menu ----------");
      System.out.println("- search (t)itles");
      System.out.println("- search (k)eywords");
      System.out.println("- search (c)ast");
      System.out.println("- see all movies of a (g)enre");
      System.out.println("- list top 50 (r)ated movies");
      System.out.println("- list top 50 (h)igest revenue movies");
      System.out.println("- (q)uit");
      System.out.print("Enter choice: ");
      menuOption = scanner.nextLine();
      
      if (!menuOption.equals("q"))
      {
        processOption(menuOption);
      }
    }
  }
  
  private void processOption(String option)
  {
    if (option.equals("t"))
    {
      searchTitles();
    }
    else if (option.equals("c"))
    {
      searchCast();
    }
    else if (option.equals("k"))
    {
      searchKeywords();
    }
    else if (option.equals("g"))
    {
      listGenres();
    }
    else if (option.equals("r"))
    {
      listHighestRated();
    }
    else if (option.equals("h"))
    {
      listHighestRevenue();
    }
    else
    {
      System.out.println("Invalid choice!");
    }
  }
  
  private void searchTitles()
  {
    System.out.print("Enter a title search term: ");
    String searchTerm = scanner.nextLine();
    
    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();
    
    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();
    
    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++)
    {
      String movieTitle = movies.get(i).getTitle();
      movieTitle = movieTitle.toLowerCase();
      
      if (movieTitle.indexOf(searchTerm) != -1)
      {
        //add the Movie objest to the results list
        results.add(movies.get(i));
      }
    }
    
    // sort the results by title
    sortResults(results);
    
    // now, display them all to the user    
    for (int i = 0; i < results.size(); i++)
    {
      String title = results.get(i).getTitle();
      
      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;
      
      System.out.println("" + choiceNum + ". " + title);
    }
    
    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");
    
    int choice = scanner.nextInt();
    scanner.nextLine();
    
    Movie selectedMovie = results.get(choice - 1);
    
    displayMovieInfo(selectedMovie);
    
    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }
  
  private void sortResults(ArrayList<Movie> listToSort)
  {
    for (int j = 1; j < listToSort.size(); j++)
    {
      Movie temp = listToSort.get(j);
      String tempTitle = temp.getTitle();
      
      int possibleIndex = j;
      while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
      {
        listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
        possibleIndex--;
      }
      listToSort.set(possibleIndex, temp);
    }
  }

  
  private void displayMovieInfo(Movie movie)
  {
    System.out.println();
    System.out.println("Title: " + movie.getTitle());
    System.out.println("Tagline: " + movie.getTagline());
    System.out.println("Runtime: " + movie.getRuntime() + " minutes");
    System.out.println("Year: " + movie.getYear());
    System.out.println("Directed by: " + movie.getDirector());
    System.out.println("Cast: " + movie.getCast());
    System.out.println("Overview: " + movie.getOverview());
    System.out.println("User rating: " + movie.getUserRating());
    System.out.println("Box office revenue: " + movie.getRevenue());
  }
  
  private void searchCast()
  {
    System.out.print("Enter an actor: ");
    String castkey = scanner.nextLine();
    castkey = castkey.toLowerCase();
    ArrayList<String> castList = new ArrayList<String>();
    ArrayList<Movie> results = new ArrayList<Movie>();
    for(int i = 0; i < movies.size(); i++){
      String cast = movies.get(i).getCast();
      cast = cast.toLowerCase();
      if(cast.indexOf(castkey) != -1){
        String actors = movies.get(i).getCast();
        String[] actorlist = actors.split("\\|");
        for(int j = 0; j < actorlist.length;j++){
          if(actorlist[j].toLowerCase().indexOf(castkey) != -1){
            castList.add(actorlist[j]);
          }
        }
      }
    }
    String[] list = castList.toArray(new String[castList.size()]);
    castList.clear();
    for(int ii = 0; ii < list.length; ii++){
      if(!castList.contains(list[ii])){
        castList.add(list[ii]);
      }
    }
      Collections.sort(castList);
    for (int i = 0; i < castList.size(); i++)
    {
      String castp = castList.get(i);

      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + castp);
    }
    System.out.println("Which actor do you want to see?");
    System.out.print("Enter number: ");
    int Castchoice = scanner.nextInt();
    scanner.nextLine();
    String selectedCast = castList.get(Castchoice - 1);
    ArrayList<Movie> MoviesAct = new ArrayList<Movie>();
    for(int i = 0; i < movies.size(); i++)
    {
      if(movies.get(i).getCast().indexOf(selectedCast) != -1)
      {
        MoviesAct.add(movies.get(i));
      }
    }
    sortResults(MoviesAct);
    for(int i = 0; i < MoviesAct.size(); i++)
    {
      String castm = MoviesAct.get(i).getTitle();
      int choiceNum = i + 1;
      System.out.println("" + choiceNum + ". " + castm);
    }
    System.out.println("Which movie do you want to see?");
    System.out.print("Enter number: ");
    int moviechoice = scanner.nextInt();
    scanner.nextLine();
    Movie selectedmovie = MoviesAct.get(moviechoice - 1);
    displayMovieInfo(selectedmovie);
    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }

  private void searchKeywords()
  {
    System.out.print("Enter a keyword search term: ");
    String searchKey = scanner.nextLine();
    searchKey = searchKey.toLowerCase();
    ArrayList<Movie> results = new ArrayList<Movie>();
    for (int i = 0; i < movies.size(); i++) {
      String key = movies.get(i).getKeywords();
      key = key.toLowerCase();

      if (key.indexOf(searchKey) != -1) {
        results.add(movies.get(i));
      }
    }
      sortResults(results);
      for (int i = 0; i < results.size(); i++)
      {
        String title = results.get(i).getTitle();

        // this will print index 0 as choice 1 in the results list; better for user!
        int choiceNum = i + 1;

        System.out.println("" + choiceNum + ". " + title);
      }

      System.out.println("Which movie would you like to learn more about?");
      System.out.print("Enter number: ");

      int choice = scanner.nextInt();
      scanner.nextLine();

      Movie selectedMovie = results.get(choice - 1);

      displayMovieInfo(selectedMovie);

      System.out.println("\n ** Press Enter to Return to Main Menu **");
      scanner.nextLine();
    }




  
  private void listGenres()
  {
    ArrayList<Movie> movieNames = new ArrayList<>();
    ArrayList<String> allGenres = new ArrayList<>();
    for (int i = 0; i < movies.size(); i++) {
      String[] genres = movies.get(i).getGenres().split("\\|");
      for (int j = 0; j < genres.length; j++) {
        allGenres.add(genres[j]);
      }
    }
    for (int i = 0; i < allGenres.size(); i++) {
      for (int j = i + 1; j < allGenres.size(); j++) {
        if (allGenres.get(i).equals(allGenres.get(j))) {
          allGenres.remove(j);
          j--;
        }
      }
    }
    Collections.sort(allGenres);
    for (int i = 0; i < allGenres.size(); i++) {
      int choiceNum = i + 1;
      System.out.println("" + choiceNum + ". " + allGenres.get(i));
    }
    System.out.println("Which genre would you like to see?");
    System.out.print("Enter number: ");
    int choice = scanner.nextInt();
    scanner.nextLine();
    for (int i = 0; i < movies.size(); i++) {
      if (movies.get(i).getGenres().indexOf(allGenres.get(choice - 1)) != -1) {
        movieNames.add(movies.get(i));
      }
    }
    sortResults(movieNames);
    for (int i = 0; i < movieNames.size(); i++) {
      String movietitle = movieNames.get(i).getTitle();
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + movietitle);
    }
    System.out.println("Which movie do you want to see?");
    System.out.print("Enter number: ");
    int mchoice = scanner.nextInt();
    scanner.nextLine();
    Movie selectedMovie = movieNames.get(mchoice - 1);
    displayMovieInfo(selectedMovie);
    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }
  
  private void listHighestRated() {
    for (int i = 1; i < movies.size(); i++) {
      Movie t = movies.get(i);
      double rating = t.getUserRating();
      int index = i;
      while (index > 0 && rating > movies.get(index - 1).getUserRating()) {
        movies.set(index, movies.get(index - 1));
        index--;
      }
      movies.set(index, t);
    }
    for (int i = 0; i < 50; i++) {
      int choiceNum = i + 1;
      System.out.println("" + choiceNum + ". " + movies.get(i).getTitle() + ": " + movies.get(i).getUserRating());
    }
    System.out.println("Which movie do you want to see?");
    System.out.print("Enter number: ");
    int moviechoice = scanner.nextInt();
    scanner.nextLine();
    Movie selectedMovie = movies.get(moviechoice - 1);
    displayMovieInfo(selectedMovie);
    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }
  
  private void listHighestRevenue()
  {
    for (int j = 1; j < movies.size(); j++) {
      Movie t = movies.get(j);
      double revenue = t.getRevenue();
      int index = j;
      while (index > 0 && revenue > movies.get(index - 1).getRevenue()) {
        movies.set(index, movies.get(index - 1));
        index--;
      }
      movies.set(index, t);
    }
    for (int i = 0; i < 50; i++) {
      int choiceNum = i + 1;
      System.out.println("" + choiceNum + ". " + movies.get(i).getTitle() + ": " + movies.get(i).getRevenue());
    }
    System.out.println("Which movie do you want to see?");
    System.out.print("Enter number: ");
    int moviechoice = scanner.nextInt();
    scanner.nextLine();
    Movie selectedMovie = movies.get(moviechoice - 1);
    displayMovieInfo(selectedMovie);
    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }
  
  private void importMovieList(String fileName)
  {
    try
    {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line = bufferedReader.readLine();

      movies = new ArrayList<Movie>();

      while ((line = bufferedReader.readLine()) != null)
      {
        String[] movieFromCSV = line.split(",");

        String title = movieFromCSV[0];
        String cast = movieFromCSV[1];
        String director = movieFromCSV[2];
        String tagline = movieFromCSV[3];
        String keywords = movieFromCSV[4];
        String overview = movieFromCSV[5];
        int runtime = Integer.parseInt(movieFromCSV[6]);
        String genres = movieFromCSV[7];
        double userRating = Double.parseDouble(movieFromCSV[8]);
        int year = Integer.parseInt(movieFromCSV[9]);
        int revenue = Integer.parseInt(movieFromCSV[10]);

        Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
        movies.add(nextMovie);
      }
      bufferedReader.close();
    }
    catch(IOException exception)
    {
      // Print out the exception that occurred
      System.out.println("Unable to access " + exception.getMessage());
    }
  }
}