public class WaffleEater extends Thread {

    private String name;
    private int wafflesEaten = 0;
    private ServingPlate plate;

    WaffleEater(String name, ServingPlate plate) {
		this.name = name;
		this.plate = plate;
    }
	
    @Override
	public void run(){
		while(!plate.outOfWaffles()){
			int waffleNum = plate.take();
			if(waffleNum == 0){
				break;
			}
			System.out.println(name + " tar våffla " + waffleNum);
			WaffleFeast.randomWait(WaffleFeast.EAT_TIME);
			System.out.println(name + " har ätit upp våffla " + waffleNum);
			wafflesEaten++;
		}
		System.out.println(name + " har ätit klart");
    }

    public String toString(){
		return name + " fick " + wafflesEaten + " våfflor";
    }
}