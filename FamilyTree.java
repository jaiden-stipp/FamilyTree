import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FamilyTree implements Serializable {
    private Person root;
    private List<Person> unconnectedMembers;

    public FamilyTree(Person root) {
        this.root = root;
        this.unconnectedMembers = new ArrayList<>();
    }

    public Person getRoot() {
        return root;
    }

    public void addMember(Person newMember, Person parent) {
        if (parent != null) {
            parent.addChild(newMember);
        } else {
            if (root == null) {
                root = newMember;
            } else {
                root.addChild(newMember);
            }
        }
    }
    public List<Person> getUnconnectedMembers() {
        return unconnectedMembers;
    }
    public void removeMember(Person member) {
        for (Person parent : member.getParents()) {
            parent.getChildren().remove(member);
        }
        for (Person child : member.getChildren()) {
            child.getParents().remove(member);
        }
        unconnectedMembers.remove(member);
    }

    public void printFamilyTree(Person person, int depth) {
        if (person == null) return;
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println(person.getName() + " (" + person.getAge() + ")");
        for (Person child : person.getChildren()) {
            printFamilyTree(child, depth + 1);
        }
    }
    public void printUnconnectedMembers() {
        for (Person unconnected : unconnectedMembers) {
            System.out.println(unconnected.getName() + " (" + unconnected.getAge() + ")");
            
            printFamilyTree(unconnected, 0); 
        }
    }
    
}
