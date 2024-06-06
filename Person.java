import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    private String name;
    private int age;
    private List<Person> parents;
    private List<Person> children;
    private Person spouse;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public void addParent(Person parent) {
        if (!this.parents.contains(parent)) {  
            this.parents.add(parent);
            if (!parent.getChildren().contains(this)) {  
                parent.addChild(this);  
            }
        }
    }

    public void addChild(Person child) {
        if (!this.children.contains(child)) { 
            this.children.add(child);
            if (!child.getParents().contains(this)) { 
                child.addParent(this);  
            }
        }
    }

    public void addSpouse(Person spouse) {
        this.spouse = spouse;
        spouse.spouse = this;
    }

    public List<Person> getParents() {
        return parents;
    }

    public List<Person> getChildren() {
        return children;
    }

    public Person getSpouse() {
        return spouse;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
