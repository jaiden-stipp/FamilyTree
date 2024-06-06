public class main {
    public static void main(String[] args){
        Person rootPerson= new Person("Jaiden", 19);
        FamilyTree tree = new FamilyTree(rootPerson);
        MainWindow app = new MainWindow(tree);
        app.startMenu();
    }
    
}
