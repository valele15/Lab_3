//Nota el algoritmo de Counting sort decidimos colocarlo en ortro archivo, ya que, no era parte como tal del codigo
// y solo se solicitaba para el analisis.

import java.util.*;   //Valentina Mella ; 21862702-1
                      //Valentina Díaz  ; 21871793-4


class Game{
 private String name;
 private String category;
 private int price;
 private int quality;


 public Game(String name,String category,int price,int quality){
     this.name = name;
     this.category = category;
     this.price = price;
     this.quality = quality;
 }


 public String getName(){
     return this.name;
 }


 public String getCategory(){
     return this.category;
 }


 public int getPrice(){
     return this.price;
 }


 public int getQuality(){
     return this.quality;
 }


 public String toString(){
     return "Game{Nombre ='" + this.name + "',Categoria ='" + this.category + "', Precio =" + this.price + ", Calidad =" + this.quality + "}";
 } 
}


class Dataset{
 private ArrayList<Game> data;
 private String sortedByAttribute;


public Dataset(ArrayList<Game> data){
     this.data =data;
     this.sortedByAttribute =null;}


public ArrayList<Game> getGamesByPrice(int price){
     ArrayList<Game> result= new ArrayList();
     if ("price".equals(this.sortedByAttribute)){
         int index=this.binarySearchByPrice(price,0,
         this.data.size()-1);
         if (index !=-1){
      result.add((Game)this.data.get(index));


   for(int left= index -1;left >=0 && ((Game)this.data.get(left)).getPrice()==price; --left){
      result.add((Game)this.data.get(left));
      }


    for(int right= index +1;right <this.data.size() && ((Game)this.data.get(right)).getPrice() ==price; ++right){
       result.add((Game)this.data.get(right));
      }
     } } else{ for(Game game : this.data){
             if (game.getPrice()==price){
                 result.add(game);
             }
            }
           }
     return result;
 }


 public ArrayList<Game> getGamesByPriceRange(int lowerPrice,int higherPrice){
     ArrayList<Game> result= new ArrayList();
     if("price".equals(this.sortedByAttribute)){
       int startIndex;
        for(startIndex =0;startIndex <this.data.size() && ((Game)this.data.get(startIndex)).getPrice()< lowerPrice; ++startIndex){
         }


   for(int i=startIndex; i< this.data.size() && ((Game)this.data.get(i)).getPrice() <=higherPrice; ++i) {
             result.add((Game)this.data.get(i));
         }
     } else{
         for(Game game : this.data){
             if(game.getPrice() >=lowerPrice && game.getPrice() <=higherPrice){
       result.add(game);}
      }
    }
     return result;}
public ArrayList<Game> getGamesByCategory(String category){
     ArrayList<Game> result= new ArrayList();
     if ("category".equals(this.sortedByAttribute)) {
         int index= this.binarySearchByCategory(category,0, this.data.size() -1);
         if(index != -1){
            result.add((Game)this.data.get(index));


        for(int left= index -1;left >=0 && ((Game)this.data.get(left)).getCategory().equals(category); --left){
                 result.add((Game)this.data.get(left));}


        for(int right= index + 1;right <this.data.size() && ((Game)this.data.get(right)).getCategory().equals(category);++right){
 result.add((Game)this.data.get(right));}
     }
    } else {
         for(Game game : this.data){
             if(game.getCategory().equals(category)){
                 result.add(game);}
         }
     }
     return result;}

public ArrayList<Game> getGamesByQuality(int quality) {
     ArrayList<Game> result= new ArrayList();

     if("quality".equals(this.sortedByAttribute)) {
         int index= this.binarySearchByQuality(quality,0, this.data.size() -1);


         if (index != -1){
             result.add((Game)this.data.get(index));


             for(int left= index -1;left >=0 && ((Game)this.data.get(left)).getQuality()==quality;--left){
                 result.add((Game)this.data.get(left));}


   for(int right =index +1; right<this.data.size() && ((Game)this.data.get(right)).getQuality()==quality; ++right){
                 result.add((Game)this.data.get(right));
             }
         }
     } else {for(Game game : this.data) {
             if(game.getQuality()==quality){
                 result.add(game);}
         }
     } return result;
 }


 public ArrayList<Game> sortByAlgorithm(String algorithm,String attribute){
     if(!"price".equals(attribute) && !"category".equals(attribute) && !"quality".equals(attribute)){
         attribute ="price";}
     this.sortedByAttribute =attribute;

     ArrayList<Game> sortedData = new ArrayList(this.data);
     switch (algorithm){

         case "bubbleSort":
             this.bubbleSort(sortedData,attribute);
             break;

         case "insertionSort":
             this.insertionSort(sortedData,attribute);
             break;

         case "selectionSort":
             this.selectionSort(sortedData,attribute);
             break;

         case "mergeSort":
             sortedData=this.mergeSort(sortedData,attribute);
             break;

         case "quickSort":
             this.quickSort(sortedData,0,sortedData.size() -1,attribute);
             break;

         default: if ("price".equals(attribute)) {
         Collections.sort(sortedData, Comparator.comparingInt(Game::getPrice));
   }else if("category".equals(attribute)){
                 Collections.sort(sortedData, Comparator.comparing(Game::getCategory));
} else if("quality".equals(attribute)){
                 Collections.sort(sortedData, Comparator.comparingInt(Game::getQuality));
             }
     }
     this.data = sortedData;
     return sortedData;
}
 private int binarySearchByPrice(int price,int left,int right){
     if(left > right){
         return -1;
     } else{
         int mid =left +(right-left)/2;
         if (((Game)this.data.get(mid)).getPrice()==price){
             return mid;
         } else{
             return ((Game)this.data.get(mid)).getPrice()> price ? this.binarySearchByPrice(price, left, mid -1): this.binarySearchByPrice(price, mid +1,right);}
   }
 }


 private int binarySearchByCategory(String category,int left,int right){
     if (left>right){
         return -1;
     }else {
         int mid =left +(right-left)/2;
         if (((Game)this.data.get(mid)).getCategory().equals(category)){
             return mid;
         }else{
       return ((Game)this.data.get(mid)).getCategory().compareTo(category) >0 ? this.binarySearchByCategory(category,left,mid -1):this.binarySearchByCategory(category,mid +1, right);}
     }
 }


 private int binarySearchByQuality(int quality,int left,int right){
     if (left>right){
         return -1;
     }else {
         int mid=left+(right-left)/2;
         if (((Game)this.data.get(mid)).getQuality()== quality){
             return mid;
         } else{
        return ((Game)this.data.get(mid)).getQuality()> quality ? this.binarySearchByQuality(quality,left,mid -1) : this.binarySearchByQuality(quality, mid +1,right);}
     }
 }
 private void bubbleSort(ArrayList<Game> list, String attribute) {
     int n =list.size();

     for(int i=0;i <n -1;++i){
         for(int j=0;j <n -i - 1;++j) {
             if (this.compare((Game)list.get(j),(Game)list.get(j +1),attribute) >0){
                 Game temp =(Game)list.get(j);
                 list.set(j,(Game)list.get(j +1));
                 list.set(j +1,temp);}
         }
     }
 }

 private void insertionSort(ArrayList<Game> list, String attribute){
     int n =list.size();
  for(int i=1;i<n;++i){
         Game key =(Game)list.get(i);
         int j;
         for(j=i - 1;j >=0 && this.compare((Game)list.get(j),key,attribute) > 0; --j) {
             list.set(j + 1, (Game)list.get(j));
         } list.set(j+1,key);
     }}
private void selectionSort(ArrayList<Game> list, String attribute){
     int n=list.size();
   for(int i =0; i < n-1; ++i){
         int minIndex =i;
   for(int j =i+1;j<n;++j){
if(this.compare((Game)list.get(j),(Game)list.get(minIndex),attribute)<0){
         minIndex=j;}
    }
         Game temp=(Game)list.get(minIndex);
         list.set(minIndex,(Game)list.get(i));
         list.set(i,temp);}
}


 private ArrayList<Game> mergeSort(ArrayList<Game> list, String attribute){
     if(list.size() <=1){
         return list;
     }else{
         int mid= list.size()/2;
         ArrayList<Game> left= new ArrayList(list.subList(0,mid));
         ArrayList<Game> right= new ArrayList(list.subList(mid, list.size()));
         left =this.mergeSort(left,attribute);
         right =this.mergeSort(right,attribute);
         return this.merge(left,right,attribute);
     }
 }
private ArrayList<Game> merge(ArrayList<Game> left,ArrayList<Game> right,String attribute){
  ArrayList<Game> result=new ArrayList();
   int leftIndex=0;
   int rightIndex=0;
  
while(leftIndex <left.size() && rightIndex< right.size()){  if(this.compare(left.get(leftIndex),right.get(rightIndex),attribute) <=0){
  result.add(left.get(leftIndex));
     ++leftIndex;
   }else {
  result.add(right.get(rightIndex));
     ++rightIndex;}
   }
   result.addAll(left.subList(leftIndex, left.size()));
   result.addAll(right.subList(rightIndex, right.size()));
   return result;
}
 private void quickSort(ArrayList<Game> list,int low,int high,String attribute){
     if (low <high){
         int pivotIndex =this.partition(list,low,high,attribute);
         this.quickSort(list,low,pivotIndex -1,attribute);
         this.quickSort(list,pivotIndex +1,high,attribute);
     }
   }

private int partition(ArrayList<Game> list,int low,int high,String attribute){
     Game pivot =(Game)list.get(high);
     int i =low -1;
     for(int j = low; j < high; ++j) {
       if(this.compare((Game)list.get(j),pivot,attribute)<=0){
             ++i;
             Game temp =(Game)list.get(i);
             list.set(i,(Game)list.get(j));
             list.set(j,temp);
         }
        }
  Game temp=(Game)list.get(i+1);
     list.set(i +1,(Game)list.get(high));
     list.set(high,temp);
     return i +1;
}
 private int compare(Game game1,Game game2,String attribute) {
     switch (attribute){

 case "price":
  return Integer.compare(game1.getPrice(),game2.getPrice());

 case "category":
  return game1.getCategory().compareTo(game2.getCategory());

 case "quality":
  return Integer.compare(game1.getQuality(),game2.getQuality());
         
 default:
  return Integer.compare(game1.getPrice(),game2.getPrice());}
 }

 public ArrayList<Game> getData(){
     return this.data;}
}

class GenerateData{

 private static final String[] GAME_WORDS = new String[]{"Dragon", "Empire", "Quest", "Galaxy", "Legends", "Warrior", "Adventure", "Fantasy", "Kingdom", "Heroes", "Battle", "Space", "Magic", "Sword", "Shield", "Dungeon", "Castle", "Ninja", "Pirate", "Robot", "Zombie", "Alien", "Wizard", "Knight", "Titan", "Chaos", "Order", "Realm", "World", "Saga", "Chronicles", "Legacy"};
 private static final String[] CATEGORIES = new String[]{"Acción", "Aventura", "Estrategia", "RPG", "Deportes", "Simulación", "Puzzle", "Plataformas", "Carreras", "Shooter", "Horror"};

 private static final Random random = new Random();
 public GenerateData(){
 }

public static ArrayList<Game> generateGames(int size){
     ArrayList<Game> games=new ArrayList();

    for(int i =0;i<size; ++i) {
         String name =generateRandomName();
         String category = generateRandomCategory();
         int price=generateRandomPrice();
         int quality= generateRandomQuality();
         games.add(new Game(name,category,price,quality));
     }
     return games;}

 private static String generateRandomName(){
     String word1 = GAME_WORDS[random.nextInt(GAME_WORDS.length)];
     String word2 = GAME_WORDS[random.nextInt(GAME_WORDS.length)];
     return word1 + word2;
 }
private static String generateRandomCategory(){
     return CATEGORIES[random.nextInt(CATEGORIES.length)];
}
 private static int generateRandomPrice(){
     return random.nextInt(70001);}

 private static int generateRandomQuality(){
     return random.nextInt(101);
 }

 public static void saveToFile(ArrayList<Game> games,String filename){
     System.out.println("Guardando " + games.size() + " juegos en" + filename);
 }
  
 public static void main(String[] args){
     ArrayList<Game> smallDataset =generateGames(100);
     ArrayList<Game> mediumDataset =generateGames(10000);
     ArrayList<Game> largeDataset =generateGames(1000000);
     saveToFile(smallDataset, "small_dataset.txt");
     saveToFile(mediumDataset, "medium_dataset.txt");
     saveToFile(largeDataset, "large_dataset.txt");
     System.out.println("Sample of small dataset:");


     for(int i =0; i< Math.min(5,smallDataset.size());++i){
         System.out.println(smallDataset.get(i));
     }
 }
}

public class Main {

 public static void main(String[] args){
     System.out.println("Starting Game Dataset Application");
     System.out.println("\nGenerating dataset...");
     ArrayList<Game> games = GenerateData.generateGames(20);
     System.out.println("\nGenerated Games:");
     for(Game game : games) {
         System.out.println(game);}

 Dataset dataset = new Dataset(games);

     System.out.println("\nTesting sorting algorithms:");
     System.out.println("\nBubble Sort by Price:");


     ArrayList<Game> bubbleSortedByPrice = dataset.sortByAlgorithm("bubbleSort", "price");
     printFirstFiveGames(bubbleSortedByPrice);
   
     System.out.println("\nInsertion Sort by Category:");
     ArrayList<Game> insertionSortedByCategory = dataset.sortByAlgorithm("insertionSort", "category");
     printFirstFiveGames(insertionSortedByCategory);
   
     System.out.println("\nSelection Sort by Quality:");
     ArrayList<Game> selectionSortedByQuality = dataset.sortByAlgorithm("selectionSort", "quality");
     printFirstFiveGames(selectionSortedByQuality);
   
     System.out.println("\nMerge Sort by Price:");
     ArrayList<Game> mergeSortedByPrice = dataset.sortByAlgorithm("mergeSort", "price");
     printFirstFiveGames(mergeSortedByPrice);
   
     System.out.println("\nQuick Sort by Category:");
     ArrayList<Game> quickSortedByCategory = dataset.sortByAlgorithm("quickSort", "category");
     printFirstFiveGames(quickSortedByCategory);
   
     System.out.println("\nDefault Sort by Quality:");
     ArrayList<Game> defaultSortedByQuality = dataset.sortByAlgorithm("default", "quality");
     printFirstFiveGames(defaultSortedByQuality);
   
     System.out.println("\nTesting search methods:");
     dataset.sortByAlgorithm("quickSort", "price");
   
     int priceToSearch = games.get(0).getPrice();
     System.out.println("\nGames with price " + priceToSearch + ":");
   
  for(Game game : dataset.getGamesByPrice(priceToSearch)) {
         System.out.println(game);
     }

     int lowerPrice = 10000;
     int higherPrice = 60000;
     System.out.println("\nJuegos con precios entre " + lowerPrice + " y " + higherPrice + ":");

  for(Game game : dataset.getGamesByPriceRange(lowerPrice, higherPrice)) {
         System.out.println(game);
     }
 dataset.sortByAlgorithm("quickSort", "category");
     String categoryToSearch = games.get(0).getCategory();
     System.out.println("\nGames with category " +categoryToSearch + ":");
     for(Game game : dataset.getGamesByCategory(categoryToSearch)){
         System.out.println(game);
     }
     dataset.sortByAlgorithm("quickSort", "quality");
     int qualityToSearch=games.get(0).getQuality();
     System.out.println("\nJuegos con calidad " + qualityToSearch + ":");
 for(Game game : dataset.getGamesByQuality(qualityToSearch)) {
         System.out.println(game);
     }
     System.out.println("\ntodas las condiciones completadas de manera efectiva!");
 }

 private static void printFirstFiveGames(ArrayList<Game> games){
     for(int i=0;i<Math.min(5,games.size());++i){
         System.out.println(games.get(i));}
 }
}
