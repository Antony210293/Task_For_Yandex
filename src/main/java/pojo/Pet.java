package pojo;

public class Pet {

    private int id;
    private String name;
    private String status;

    public Pet(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Pet() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


