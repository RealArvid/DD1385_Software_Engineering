public class ServingPlate {
    private int waffleNum = 0;
    private boolean outOfWaffles = false;
	private boolean waffleOnPlate = false;

    public boolean outOfWaffles(){
		return outOfWaffles;
    }

    public synchronized int take(){
		while(!waffleOnPlate && !outOfWaffles()) {
			try{
				wait();
			}
			catch(Exception e){}
		}
		notify();
		waffleOnPlate = false;

		if(outOfWaffles == true)
			return 0;
		else if(waffleNum == WaffleFeast.NUM_WAFFLES)
			outOfWaffles = true;
		return waffleNum;
	}
    
    public synchronized void put(int newWaffleNum){
		while(waffleOnPlate) {
			try{
				wait();
			}
			catch(Exception e){}
		}
		waffleNum = newWaffleNum;
		waffleOnPlate = true;
		notify();
    }
}