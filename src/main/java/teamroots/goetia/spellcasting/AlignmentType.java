package teamroots.goetia.spellcasting;

public enum AlignmentType {
	DEMON(16729156),
	ANGEL(3006972),
	HUMAN(0xFFFFFF);
	
	public int color;
	
	private AlignmentType(int color){
		this.color = color;
	}
}
