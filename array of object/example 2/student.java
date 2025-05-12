class student{
    public static void main(String args[]){
        studentarray std[] = new studentarray[3];

        std[0] = new studentarray("Gurmit",21);
        std[1] = new studentarray("Mong",22);
        std[2] = new studentarray("Kok Lun",23);

        for(int i=0; i<std.length; i++){
            std[i].displaystudent();
        }
    }
}