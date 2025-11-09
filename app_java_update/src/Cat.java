public class Cat {
    private String name = null;
    private int age = 0;
    private Breed breed = Breed.DEFAULT;
    private SexCat sexCat = SexCat.DEFAULT;
    private static int catId;
    private int id;

    public Cat() {
       this.id = catId++;
    }

    public Cat(String name, int age, Breed breed, SexCat sexCat) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.sexCat = sexCat;
        this.id = catId++;

    }

    /*public Cat(String line ){
        String [] fields = {"name", "age", "breed", "sexcat"};
        this.name = fields[0];
        this.age = Integer.parseInt(fields[1]);
        this.breed = Breed.valueOf(fields[2]);
        this.sexCat = SexCat.valueOf(fields[3]);
    }*/

    public static int getCatId() {
        return catId;
    }

    public static void setCatId(int catId) {
        Cat.catId = catId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Breed getBreed() {
        return breed;
    }
    public void setBreed(Breed breed) {
        this.breed = breed;
    }
    public SexCat getSexCat(){
        return this.sexCat;
    }
    public void setSexCat(SexCat sexCat){
        this.sexCat=sexCat;
    }

    @Override
    public String toString() {
        return "Cat{" + "name=" + name + ", age=" + age + ",breed=" + breed + ",sex=" + sexCat + ",catId=" + catId + '}';
    }

    public Cat(String line){
        String[] parts = (line.split(";"));
        this.name = parts[0];
        this.age = Integer.parseInt(parts[1]);
        this.breed = Breed.valueOf(parts[2]);
        this.sexCat= SexCat.valueOf(parts[3]);
        catId = Integer.parseInt(parts[4]);
        this.id = catId++;

    }

    public int getId() {
        return id;
    }

    public String[] toRow() {
        String[] ret;
        ret = new String[]{this.name, this.age+"",this.breed+ "",this.sexCat+"",this.id+"" };
        return ret;
    }

    public String toLine() {
        return this.name + ";" + this.age + ";" + this.breed + ";" + this.sexCat+";" + this.id+";";
    }
}

