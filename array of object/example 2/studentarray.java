class studentarray{
    String name;
    int age;

    studentarray(String name, int age){//constructor
        this.name = name;
        this.age = age;
    }

    public void displaystudent(){
        System.out.println("Name:" + name + " Age:" + age);
    }
}