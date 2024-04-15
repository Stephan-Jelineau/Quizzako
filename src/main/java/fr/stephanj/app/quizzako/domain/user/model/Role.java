package fr.stephanj.app.quizzako.domain.user.model;

public enum Role {
	USER("Standard User"), TEACHER("Teacher privilege"), STUDENT("Student privilege"), ADMIN("Administrator");
	
	private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
