import java.util.Scanner;

public class MainWindow {
    private FamilyTree familyTree;
    private Scanner sc;

    public MainWindow(FamilyTree familyTree) {
        this.familyTree = familyTree;
        this.sc = new Scanner(System.in);
    }
    public void startMenu() {
        System.out.print("\033[H\033[2J");
        System.out.flush(); 
        sc = new Scanner(System.in);
        String input;
        System.out.println("Welcome to the Family Tree App!\n");
        System.out.println("Here is your current family tree: \n");
        displayFamilyTree();
        System.out.println("\nAvailable Actions: ");
        System.out.println("1. Add Member");
        System.out.println("2. Remove Member");
        System.out.println("3. Connect Members");
        System.out.println("4. Exit\n");
        System.out.print("Enter your choice: ");
        input = sc.nextLine();
        switch(input) {
            case "1" :
                addMemberPrompt();
                break;
            case "2" :
                removeMemberPrompt();
                break;
            case "3" :
                connectMenu();
                break;
            case "4":
                System.out.println("Exiting...");
                break;
            
            default: 
                System.out.println("Invalid option. Please choose a number between 1 and 5");
                startMenu();
                break;
                


        }

    }
    public void addMemberPrompt() {
        System.out.print("\033[H\033[2J"); 
        System.out.flush();
        System.out.print("Enter the name of the new member: ");
        String name = sc.nextLine();
        System.out.print("Enter the age of the new member: ");
        int age = Integer.valueOf(sc.nextLine());
        Person newMember = new Person(name, age);
        System.out.print("Enter this person's parent (If none, type none and they will be added to the end of the family tree):");
        String parentCheck = sc.nextLine();
        switch(parentCheck) {
            case "none":
            case "None":
            case "NONE":
                familyTree.addMember(newMember, null);
                break;
            default:
                Person parent = findPersonByName(parentCheck);
                familyTree.addMember(newMember, parent);
        }
        System.out.println("Member added successfully!");
        startMenu();
    }
    public void removeMemberPrompt() {
        System.out.print("Enter the name of the member to remove: ");
        String name = sc.nextLine();
        Person member = findPersonByName(name);
        if (member != null) {
            familyTree.removeMember(member);
            System.out.println("Member removed successfully.");
        } else {
            System.out.println("Member not found.");
        }
        startMenu();
    }
    private void displayFamilyTree() {
        familyTree.printFamilyTree(familyTree.getRoot(), 0);
        familyTree.printUnconnectedMembers();
    }
    public void connectMenu(){
        System.out.flush();
        System.out.println("\nConnection Types:");
        System.out.println("1. Set Parent");
        System.out.println("2. Set Spouse");
        System.out.print("Choose a connection type: ");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                connectParent();
                break;
            case "2":
                connectSpouse();
                break;
            default:
                System.out.println("Invalid Choice. Please choose either 1 or 2");
                connectMenu();
        }
    }
    private void connectParent() {
        System.out.flush();
        System.out.print("Enter the name of the parent: ");
        String parentName = sc.nextLine();
        Person parent = findPersonByName(parentName);

        System.out.print("Enter the name of the child: ");
        String childName = sc.nextLine();
        Person child = findPersonByName(childName);
        if (parent != null && child != null) {
            parent.addChild(child);
            if (familyTree.getUnconnectedMembers().contains(child)) {
                familyTree.getUnconnectedMembers().remove(child);
            }
            System.out.println("Connection Established!");
        } else {
            System.out.println("Parent or child not found.");
        }
        startMenu();
    }
    private void connectSpouse() {
        System.out.flush();
        System.out.print("Enter the name of the first spouse: ");
        String spouse1Name = sc.nextLine();
        Person spouse1 = findPersonByName(spouse1Name);

        System.out.print("Enter the name of the second spouse: ");
        String spouse2Name = sc.nextLine();
        Person spouse2 = findPersonByName(spouse2Name);

        if (spouse1 != null && spouse2 != null) {
            spouse1.addSpouse(spouse2);
            System.out.println("Spouses connected.");
        } else {
            System.out.println("One or both spouses not found.");
        }
        startMenu();
    }
    
    private Person findPersonByName(String name) {
        return findPersonRecursive(familyTree.getRoot(), name);
    }
    private Person findPersonRecursive(Person current, String name) {
        if (current == null) return null;
        if (current.getName().equalsIgnoreCase(name)) return current;
        for (Person child : current.getChildren()) {
            Person found = findPersonRecursive(child, name);
            if (found != null) return found;
        }
        return null;
    }
    
}