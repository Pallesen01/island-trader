package core;

public class GameEnvironment {

	public static void main(String[] args) {
		/*File testFile = new File("");
	    String currentPath = testFile.getAbsolutePath();
	    System.out.println("current path is: " + currentPath);*/
		
		
		// Run generator
		
		//System.out.println(ObjectsListGenerator.generateItem().size());
		
		//Store Object
		
		/*Store newStore = new Store();
		System.out.println(newStore.getBuys());
		for (Item item : newStore.getBuys()) {
			System.out.println(item.getName() + " " + item.getPrice());
		}
		System.out.println("NEXT STORE");
		newStore.generateBuys();
		System.out.println(newStore.getBuys());
		for (Item item : newStore.getBuys()) {
			System.out.println(item.getName() + " " + item.getPrice());
		}*/
	
		//Route
		Route newRoute = new Route(new Island(), new Island(), 5, 50, 50, 50);
		System.out.println(newRoute.encouterLostSailors());
	
		
	}
	

}
