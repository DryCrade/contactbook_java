import java.util.LinkedList;
import java.util.Scanner;

public class ContactBook {

    public static void main(String[] args) {
        System.out.println("*****  Contact List Manager  *****");
        System.out.println("(A)dd");
        System.out.println("(D)elete");
        System.out.println("(E)mail Search");
        System.out.println("(P)rint List");
        System.out.println("(S)earch");
        System.out.println("(Q)uit");
        System.out.println("**********************************");

        LinkedList<String> contactList = new LinkedList<>();
        LinkedList<String> contactNames = new LinkedList<>();
        LinkedList<String> contactNumbers = new LinkedList<>();
        LinkedList<String> lc_contactNames = new LinkedList<>();
        LinkedList<String> contactEmails = new LinkedList<>();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Please choose a command: ");
            char cmd = cmdValidation(sc); // Input validation so that it only accepts the commands

            switch (cmd) {
                case 'A':
                case 'a':
                    System.out.println("You have chosen 'Add'.\n");
                    System.out.print("Please enter a name you wish to add: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    contactNames.add(name);

                    System.out.println("Enter their phone number: ");
                    String phoneNum = sc.nextLine();

                    if (phoneNum.matches("\\+?\\d+")) {
                        contactNumbers.add(phoneNum);
                    } else {
                        System.out.println("Invalid input, must only contain digits.");
                        contactNames.removeLast();
                        break;
                    }

                    System.out.println("Enter their email: ");
                    String email = sc.next();
                    sc.nextLine();

                    // Email Validation, ensuring an @ and a period (.) after the @
                    int atIndex = email.indexOf('@');
                    int dotIndex = email.indexOf('.', atIndex);

                    if (atIndex == -1 || dotIndex == -1) {
                        System.out.println("Invalid email, must contain '@' and a period ('.') after '@'.");
                        contactNames.removeLast();
                        contactNumbers.removeLast();
                        break;
                    } else
                        contactEmails.add(email);

                    String contact = (name + "  |  " + phoneNum + "  |  " + email);
                    System.out.println(contact);

                    contactList.add(contact);

                    // Successful message, letting the user know the command went through
                    System.out.println("Added successfully. This is your updated contact: ");
                    int numbering = 1;
                    System.out.println("--- Contact List ---");
                    for (String s : contactList) {
                        System.out.println(numbering + ". " + s);
                        numbering++;
                    }
                    System.out.println("--------------------");
                    break;
                case 'D':
                case 'd':
                    int numbering2 = 1;
                    System.out.println("--- Contact List ---");
                    for (String s : contactList) {
                        System.out.println(numbering2 + ". " + s);
                        numbering2++;
                    }
                    System.out.println("--------------------");


                    System.out.println("You have chosen 'Delete'.\n");
                    System.out.print("Please enter a number you wish to delete: ");
                    int nameDel = sc.nextInt();

                    if (contactList.remove(nameDel - 1) != null)
                        System.out.println("~~ Successfully deleted! ~~\n");
                    else
                        System.out.println("Error");
                    System.out.println("Here is your updated contact list: ");
                    int numbering3 = 1;
                    System.out.println("--- Contact List ---");
                    for (String s : contactList) {
                        System.out.println(numbering3 + ". " + s);
                        numbering3++;
                    }
                    if (contactList.peek() == null)
                        System.out.println("You have no contacts. It's time to add some!");
                    System.out.println("--------------------");
                    break;

                case 'E':
                case 'e':
                    System.out.println("You have chosen 'Email Search'.\n");
                    System.out.println("Enter the email: ");
                    String emailSearch = sc.next();

                    System.out.println("--------------------");
                    System.out.println("Here is the search containing " + "'" + emailSearch + "':");
                    if (contactEmails.contains(emailSearch)) {
                        // Gets index of the search and using it to find the corresponding name
                        int emailIndex = contactEmails.indexOf(emailSearch);
                        System.out.println("Name: " + contactNames.get(emailIndex));
                        System.out.println("Phone Number: " + contactNumbers.get(emailIndex));
                    } else
                        System.out.println("Name is not found with this email.");
                    System.out.println("--------------------");

                    break;

                case 'P':
                case 'p':
                    System.out.println("You have chosen 'Print List'.\n");
                    System.out.println("This is your contact list:");
                    int numbering4 = 1;
                    System.out.println("--- Contact List ---");
                    for (String s : contactList) {
                        System.out.println(numbering4 + ". " + s);
                        numbering4++;
                    }
                    System.out.println("--------------------");
                    break;

                case 'S':
                case 's':
                    System.out.println("You have chosen 'Search'.\n");
                    int identicalNames = 0;
                    System.out.println("Input your search: ");
                    String userSearch = sc.next();

                    for (String str : contactNames) {
                        lc_contactNames.add(str.toLowerCase());
                    }

                    boolean found = false;

                    System.out.println("--------------------");
                    System.out.println("Here is the search containing " + "'" + userSearch + "':");
                    for (int i = 0; i < lc_contactNames.size(); i++) {
                        if ((lc_contactNames.get(i).contains(userSearch.toLowerCase())) || contactNumbers.get(i).contains(userSearch)) { // Ensuring the search is case-insensitive
                            System.out.println(contactList.get(i));
                            found = true;
                            identicalNames++;
                        }
                    }

                    if (!found)
                        System.out.println("Name not found.");
                    System.out.println("--------------------");
                    if (identicalNames == 1)
                        System.out.println("I found 1 match!\n");
                    else
                        System.out.println("I found " + identicalNames + " matches!\n");

                    lc_contactNames.clear();

                    break;

                case 'Q':
                case 'q':
                    System.out.println("Quitting...");
                    return;
            }
        }
    }
    private static Character cmdValidation(Scanner sc) {
        while (true) {
            String input = sc.next().trim();
            if (input.length() == 1) {
                char cmd = input.charAt(0);
                if ("ADEPSQadepsq".indexOf(cmd) != -1) {
                    return cmd;
                }
            }
            System.out.print("Invalid input, please try again: ");
        }
    }
}