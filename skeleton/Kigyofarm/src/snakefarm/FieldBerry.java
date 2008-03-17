package snakefarm;

public class FieldBerry extends Collidable {
	private static int lastid=0; 
	private int id=lastid;
	private SkeletonInterface si;
	private static final String type="FieldBerry";
	
	public FieldBerry(SkeletonInterface si) {
		this.si=si;
		lastid++;
		si.enterMethod(type, id, "FieldBerry()");
		si.exitMethod(type, id, "FieldBerry()");
	}
}