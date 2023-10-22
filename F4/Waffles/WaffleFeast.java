public class WaffleFeast {

    public static int NUM_WAFFLES = 10;
    public static int MAKING_TIME = 3000; // maxtid
    public static int EAT_TIME = 1200; // maxtid

    private ServingPlate plate = new ServingPlate();
    
    public WaffleFeast () {

		// Börja grädda 
        new WaffleMaker(plate).start();

        WaffleEater anna = new WaffleEater("Anna",plate);
        WaffleEater magnus = new WaffleEater("Magnus",plate);
        WaffleEater tomas = new WaffleEater("Tomas",plate);

		// Börja äta
		anna.start(); magnus.start(); tomas.start();

		// Vänta tills alla ätit klart
		try {
			anna.join();
			magnus.join();
			tomas.join();
		}
		catch (InterruptedException e) {}

		// Vi får veta hur många våfflor var och en fick
		System.out.println();
		System.out.println(anna);
		System.out.println(magnus);
		System.out.println(tomas);
    }

    public static void randomWait(int time_ms){
        try {
	    	Thread.sleep((int)(time_ms*Math.random()));
		}
        catch(InterruptedException e){
	    	System.err.println("Sömnbrott");
		}
    }

    public static void vanta(int ms){
        try {
	    	Thread.sleep(ms);}
        catch(InterruptedException e){
	    	System.err.println("Sömnbrott");
		}
    }

    public static void main(String[] args) {
		new WaffleFeast();
    }
}


/*
Graddar vaffla 1
Graddat vaffla 1
Graddar vaffla 2
Anna tar vaffla 1
Anna har atit upp vaffla 1
Graddat vaffla 2
Tomas tar vaffla 2
Graddar vaffla 3
Tomas har atit upp vaffla 2
Graddat vaffla 3
Anna tar vaffla 3
Graddar vaffla 4
Anna har atit upp vaffla 3
Graddat vaffla 4
Tomas tar vaffla 4
Graddar vaffla 5
Tomas har atit upp vaffla 4
Graddat vaffla 5

   << hoppar över en hel del >>

Tomas har atit upp vaffla 19
Graddat vaffla 20
Tomas atit klart
Magnus tar vaffla 20
Anna atit klart
Magnus har atit upp vaffla 20
Magnus atit klart

Anna fick 5 vafflor
Magnus fick 8 vafflor
Tomas fick 7 vafflor
*/