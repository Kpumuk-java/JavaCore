package java2.lesson3;

public class Contact implements Comparable<Contact> {

    private String lastName;
    private String phoneNumber;

    public Contact(String lastName, String phoneNumber) {
        if (lastName != null && phoneNumber != null && !lastName.isEmpty() && !phoneNumber.isEmpty()) {
            if (checkStringToNumber(phoneNumber.trim())) {
                this.lastName = lastName.trim();
                this.phoneNumber = phoneNumber.trim();
            }
        }
    }

    public boolean checkStringToNumber (String number) {
        for (int i = 0; i < number.length(); i++) {
            if (!(number.charAt(i) >= '0' && number.charAt(i) <= '9')) {
                return false;
            }
        }
        return true;
    }


    @Override
    public int compareTo(Contact contact) {
        return this.lastName.compareTo(contact.getLastName());
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "" +
                "" + lastName +
                "..............." + phoneNumber
                ;
    }
}
