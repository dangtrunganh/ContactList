package anhdt.com.contactlist;

/**
 * Created by admin on 8/8/2017.
 */

public class Contact {
    private String firstName;
    private String lastName;
    private String profileImage;
    private String email;
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProfileName() {
        return profileImage;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Contact(String firstName, String lastName, String profileImage, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImage = profileImage;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
