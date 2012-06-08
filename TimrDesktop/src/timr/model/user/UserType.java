package timr.model.user;

public enum UserType {
	Teacher, Student;
	public static UserType fromString(String userType){
		return UserType.valueOf(userType.toUpperCase());
	}
	public static UserType fromInt(int userType){
		switch (userType){
		case 1:
			return UserType.Teacher;
		case 2:
			//$FALL-THROUGH$
		default:
			return UserType.Student;
		}
	}
}
