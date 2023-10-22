public class WaffleMaker extends Thread{
    private ServingPlate plate;

    WaffleMaker(ServingPlate plate) {
		this.plate = plate;
    }

	@Override
    public void run(){
		for(int waffleCount=1; waffleCount <= WaffleFeast.NUM_WAFFLES; waffleCount++){
			System.out.println("Gräddar våffla " + waffleCount);
			WaffleFeast.randomWait(WaffleFeast.MAKING_TIME);
			plate.put(waffleCount);
			System.out.println("Gräddat våffla " + waffleCount);
		}
    }
}