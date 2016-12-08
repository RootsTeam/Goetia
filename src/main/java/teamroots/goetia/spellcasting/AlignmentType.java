package teamroots.goetia.spellcasting;

public enum AlignmentType {
	DEMON(16729156),
	ANGEL(3006972),
	HUMAN(0xFFFFFF);
	
	public int color;
	
	private AlignmentType(int color){
		this.color = color;
	}

	public static String getString(AlignmentType type){
		switch (type){
			case DEMON:
				return "demon";
			case ANGEL:
				return "angel";
			case HUMAN:
				return "human";
			default:
				return "human";
		}
	}
}
