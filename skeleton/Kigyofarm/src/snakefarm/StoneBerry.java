package snakefarm;

public class StoneBerry extends Collidable {

	private static int lastid = 0;
	private int id = lastid;
	private SkeletonInterface si;
	private static final String type = "StoneBerry";

	public StoneBerry(SkeletonInterface si) {
		this.si = si;
		lastid++;
		si.enterMethod(type, id, "StoneBerry()");
		si.exitMethod(type, id, "StoneBerry()");
	}
}
