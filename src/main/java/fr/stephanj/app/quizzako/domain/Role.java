package fr.stephanj.app.quizzako.domain;

public enum Role {
	USER("Standard User"), TEACHER("Teacher privilege"), STUDENT("Student privilege"), ADMIN("Administrator");
	
	private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    
    public static boolean isRequestableRole(Role role) {
    	if(role.equals(TEACHER) || role.equals(STUDENT))
    		return true;
    	return false;
    }
}
