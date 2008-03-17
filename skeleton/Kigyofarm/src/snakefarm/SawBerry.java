package snakefarm;

public class SawBerry extends Collidable {

	private static int lastid = 0;
	private int id = lastid;
	private SkeletonInterface si;
	private static final String type = "SawBerry";

	public SawBerry(SkeletonInterface si) {
		this.si = si;
		lastid++;
		si.enterMethod(type, id, "SawBerry()");
		si.exitMethod(type, id, "SawBerry()");
	}
}
