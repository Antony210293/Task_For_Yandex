package pojo;

public class Store {

    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;

    public Store(int id,int petId, int quantity, String shipDate, String status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }
    public Store(){}

    public int getId() {
        return id;
    }

    public int getPetId() {
        return petId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public String getStatus() {
        return status;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

}
